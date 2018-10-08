
// KeyBehavior.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* Key functionality:
     forward, back, left, right, up, down,
     rotate 90 left or right.

   The keys affect three things: the front-facing camera,
   the back-facing camera, and the user image in the 
   bird's eye view.
*/


import java.awt.AWTEvent;
import java.awt.event.*;
import java.util.Enumeration;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.vp.*;


public class KeyBehavior extends ViewPlatformBehavior
{
  // move and rotation constants for comms. with BirdsEye object
  private static final int FORWARD = 0;
  private static final int LEFT = 1;
  private static final int BACK = 2;
  private static final int RIGHT = 3;

  private final static double MOVE_AMT = 1.0;
  private final static double ROT_AMT = Math.PI / 2.0;  // 90 degrees

  /* Hardwired movement vectors.
     These moves are for the viewpoint where forward means moving
     into the scene, making the z-axis value smaller. Left, right,
     back, up, and down are all relative to that orientation.
  */
  private static final Vector3d VFWD = new Vector3d(0,0,-MOVE_AMT);
  private static final Vector3d VBACK = new Vector3d(0,0,MOVE_AMT);
  private static final Vector3d VLEFT = new Vector3d(-MOVE_AMT,0,0);
  private static final Vector3d VRIGHT = new Vector3d(MOVE_AMT,0,0);
  private static final Vector3d VDOWN = new Vector3d(0, -MOVE_AMT ,0);
  private static final Vector3d VUP = new Vector3d(0, MOVE_AMT ,0);

  // key names
  private int forwardKey = KeyEvent.VK_UP;
  private int backKey = KeyEvent.VK_DOWN;
  private int leftKey = KeyEvent.VK_LEFT;
  private int rightKey = KeyEvent.VK_RIGHT;

  private WakeupCondition keyPress;

  private MazeManager mm;    // for checking moves
  private BirdsEye be;       // player moves/turns are sent to be
  private int zOffset;       // to stop movement down below the floor
  private TransformGroup camera2TG;   // for affecting the back-facing camera

  // for repeated calcs
  private Transform3D t3d = new Transform3D();
  private Transform3D toMove = new Transform3D();
  private Transform3D toRot = new Transform3D();
  private Vector3d trans = new Vector3d();



  public KeyBehavior(MazeManager mazeMan, BirdsEye bird, TransformGroup c2)
  {
    keyPress = new WakeupOnAWTEvent( KeyEvent.KEY_PRESSED );
    mm = mazeMan;
    be = bird;
    camera2TG = c2;
    zOffset = 0;
  } // end of KeyBehavior()


  public void initialize()
  { wakeupOn( keyPress ); }


  public void processStimulus( Enumeration criteria )
  {
    WakeupCriterion wakeup;
    AWTEvent[] event;

    while( criteria.hasMoreElements() ) {
      wakeup = (WakeupCriterion) criteria.nextElement();
      if( wakeup instanceof WakeupOnAWTEvent ) {
        event = ((WakeupOnAWTEvent)wakeup).getAWTEvent();
        for( int i = 0; i < event.length; i++ ) {
          if( event[i].getID() == KeyEvent.KEY_PRESSED )
            processKeyEvent((KeyEvent)event[i]);
        }
      }
    }
    wakeupOn( keyPress );
  } // end of processStimulus()



  private void processKeyEvent(KeyEvent eventKey)
  {
    int keyCode = eventKey.getKeyCode();
    // System.out.println(keyCode);
    if( eventKey.isAltDown() )   // key + <alt>
      altMove(keyCode);
    else
      standardMove(keyCode);
  } // end of processKeyEvent()


  private void standardMove(int keycode)
  /* A move for the main camera (e.g. VFWD for forward) is paired
     with an opposite move for the back-facing camera (e.g. VBACK).

     This approach means that the relative orientation of the two
     cameras are hardwired into this class.

     A rotation is the same for both cameras. So a rotation left causes
     a rotaion of ROT_AMT for both cameras.
  */
  {
    if(keycode == forwardKey)
      moveBy(VFWD, FORWARD, VBACK);  // args: main camera, bird's eye, back camera
    else if(keycode == backKey)
      moveBy(VBACK, BACK, VFWD);
    else if(keycode == leftKey)
      doRotateY(ROT_AMT, LEFT);
    else if(keycode == rightKey)
      doRotateY(-ROT_AMT, RIGHT);
  } // end of standardMove()


  private void altMove(int keycode)
  {
    if(keycode == backKey) {  // move down
      if (zOffset > 0) {
        doMove(VDOWN);  // no testing using moveBy()
        doMoveC2(VDOWN);
        zOffset--;
      }
    }
    else if(keycode == forwardKey) {  // move up
      doMove(VUP);   // no testing using moveBy()
      doMoveC2(VUP);
      zOffset++;
    }
    else if(keycode == leftKey)
      moveBy(VLEFT, LEFT, VRIGHT);   // left for main camera, right for back camera
    else if(keycode == rightKey)
      moveBy(VRIGHT, RIGHT, VLEFT);
  } // end of altMove()


  
  // ------------------ moves -----------------------------

  private void moveBy(Vector3d theMove, int dir, Vector3d theMoveC2)
  /* Calculate the next move and test if there is an obstacle there.
     If there isn't then carry out the move, otherwise issue a
     warning. */
  {
    Point3d nextLoc = possibleMove(theMove);
    if (mm.canMoveTo(nextLoc.x, nextLoc.z)) {    // no obstacle there?
      targetTG.setTransform(t3d);   
         // efficient: t3d is a global set in possibleMove()
      // doMove(theMove, theMoveC2);   // inefficient recalc
      doMoveC2(theMoveC2);
      be.setMove(dir);
    }
    else  // there is an obstacle
      be.bangAlert();  // tell BirdsEye object, so a warning can be shown    
  }  // end of moveBy()



  private Point3d possibleMove(Vector3d theMove)
  /* Calculate the effect of the given translation but
     do not update the object's position until it's been
     tested. */
  { 
    targetTG.getTransform(t3d);   // targetTG is the ViewPlatform's tranform
    toMove.setTranslation(theMove);
    t3d.mul(toMove);
    t3d.get(trans);
    return new Point3d( trans.x, trans.y, trans.z);
  }  // end of possibleMove()


  private void doMove(Vector3d theMove)
  // move the main, forward-facing camera
  {
    targetTG.getTransform(t3d);
    toMove.setTranslation(theMove);
    t3d.mul(toMove);
    targetTG.setTransform(t3d);
  } // end of doMove()


  private void doMoveC2(Vector3d theMoveC2)
  // move the back-facing second camera
  {
    camera2TG.getTransform(t3d);
    toMove.setTranslation(theMoveC2);
    t3d.mul(toMove);
    camera2TG.setTransform(t3d);
  } // end of doMoveC2()


  // ---------------- rotations -----------------------

  private void doRotateY(double radians, int dir)
  // rotate three things
  {
    targetTG.getTransform(t3d);   // rotate main camera
    toRot.rotY(radians);
    t3d.mul(toRot);
    targetTG.setTransform(t3d);

    camera2TG.getTransform(t3d);  // rotate back-facing camera
    t3d.mul(toRot);  // reuse toRot value
    camera2TG.setTransform(t3d);

    be.setRotation(dir);  // rotate bird's eye view
  } // end of doRotateY()


}  // end of KeyBehavior class
