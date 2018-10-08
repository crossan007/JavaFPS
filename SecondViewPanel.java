
// SecondViewPanel.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* This panel displays the back-facing camera which
   views the scene created in WrapMaze3D.
 
   The TG for the camera is linked to the main scene
   by WrapMaze3D getting a reference to camera2TG by 
   calling getCamera2TG().
*/

import java.awt.*;
import javax.swing.*;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;


public class SecondViewPanel extends JPanel
{
  private static final int PWIDTH = 256;   // size of panel
  private static final int PHEIGHT = 256; 

  private MazeManager mazeMan;
  private TransformGroup camera2TG;  
     // the TG for the camera; this will be linked to the main scene


  public SecondViewPanel(MazeManager mm)
  {  
    mazeMan = mm;

    setLayout(new BorderLayout());
    setOpaque(false);
    setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
    GraphicsConfiguration config =
					SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    add("Center", canvas3D);    // the JPanel contains its own Canvas3D object

    initView(canvas3D);
  } // end of createPanel()


   private void initView(Canvas3D canvas3D)
   // create the viewpoint for the Canvas3D node
   {
     ViewPlatform vp = new ViewPlatform();

     // create a View node for the ViewPlatform
     // it has the same attributes as the main camera View
     View view = new View();
     view.setPhysicalBody( new PhysicalBody() );
     view.setPhysicalEnvironment( new PhysicalEnvironment() );
     view.addCanvas3D(canvas3D);
     view.attachViewPlatform(vp);   // attach the ViewPlatform

     view.setFieldOfView( Math.toRadians(90.0));  // wider FOV
     // 10 and 0.1; keep ratio between 100-1000
     view.setBackClipDistance(20);      // can see a long way
     view.setFrontClipDistance(0.05);   // can see close things

     camera2TG = setCameraPosition();
 	 camera2TG.addChild(vp);     // add ViewPlatform to camera TG
   }  // end of makeView()


  private TransformGroup setCameraPosition()
  /* Create the TG for the camera. Stand in the same place as the
     main camera, but face backwards. We know that 'front' is facing
     in the positive z direction. So back should be pointing in the
     negative z direction. */
  {
    Vector3d startVec = mazeMan.getMazeStartPosn();

    Transform3D t3d = new Transform3D( );
    // args are: viewer posn, where looking, up direction
    t3d.lookAt( new Point3d(startVec.x, startVec.y, startVec.z), 
				new Point3d(startVec.x, startVec.y, -10),  
                           // any -z direction will do
				new Vector3d(0,1,0));
    t3d.invert();

    TransformGroup tg = new TransformGroup(t3d);
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);  // will move
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    return tg;
  }  // end of setCameraPosition()


  public TransformGroup getCamera2TG()
  // caled by WrapMaze3D to attach this TG to the scene
  {  return camera2TG;  }

} // end of SecondViewPanel class