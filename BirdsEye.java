
// BirdsEye.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* This class handles the overview display, which contains
   an unchanging image of the maze, a sprite that moves 
   and changes heading, and the occasional display of
   a warning when the player hits an obstacle.

   The sprite may move outside of the display region since
   the 3D area includes a wide floor that extends beyond the maze.
*/

import java.awt.*;
import javax.swing.*;


public class BirdsEye extends JPanel
{
  private static final int PWIDTH = 256;   // size of panel
  private static final int PHEIGHT = 256; 

  private static final int NUM_DIRS = 4;
  // these dirs have counter-clockwise ordering when viewed from above
  private static final int FORWARD = 0;
  private static final int LEFT = 1;
  private static final int BACK = 2;
  private static final int RIGHT = 3;

  private static final String BANG_MSG = "BANG!";
       // warning message when the player hits an obstacle


  private MazeManager mm;

  private Image mazeIm;    // for the maze image
  private Image userIm;    // for the user's arrow
  private Image arrowIms[];   // the range of possible arrows
  private int arrowWidth, arrowHeight;
  private Point moves[];

  private Point currPosn;     // player current position in image
  private int step;           // distance a player moves in the image
  private int compass;        // the current compass heading

  private boolean showBang;   // true if player tried to move through a wall
  private Font msgFont;



  public BirdsEye(MazeManager mazeMan)
  {
    mm = mazeMan;
    setBackground(Color.white);
    setPreferredSize( new Dimension(PWIDTH, PHEIGHT));
    msgFont = new Font("SansSerif", Font.BOLD, 24);

    mazeIm = mm.getMazeImage();   // get the maze image
    initMoves();
    loadArrows();
    initPosition();
    repaint();
  } // end of BirdsEye()


  private void initMoves()
  /* Moves in (x,y) in 4 directions. The sprite starts by pointing
     downwards (when viewed from above), and that is set to be forward.
     Moving downwards (forward) means increasing the y-axis value.

     LEFT, RIGHT, BACK are relative to the initial FORWARD direction.
  */
  {
    moves = new Point[NUM_DIRS];
    step = mm.getImageStep();
    moves[FORWARD] = new Point(0, step);  // move downwards on-screen
    moves[LEFT] = new Point(step, 0);     // right on-screen
    moves[BACK] = new Point(0, -step);    // up on-screen
    moves[RIGHT] = new Point(-step, 0);   // left on-screen
  }  // end of initMoves()



  private void loadArrows()
  /* The arrows represent the four directions in which the
     sprite can move. */
  {
    arrowIms = new Image[NUM_DIRS];

    ImageIcon imIcon = new ImageIcon("images/arrowFwd.gif");
    arrowIms[FORWARD] = imIcon.getImage();
    arrowWidth = imIcon.getIconWidth();
    arrowHeight = imIcon.getIconHeight();

    arrowIms[LEFT] = new ImageIcon("images/arrowLeft.gif").getImage();
    arrowIms[BACK] = new ImageIcon("images/arrowBack.gif").getImage();
    arrowIms[RIGHT] = new ImageIcon("images/arrowRight.gif").getImage();
  } // end of loadArrows()


  private void initPosition()
  /* The initial position is the start position obtained from
     the maze manager, and the default heading, which is FORWARD
     (down the screen). */
  {
    currPosn = mm.getImageStartPosn();
    compass = FORWARD;
    userIm = arrowIms[FORWARD];    
        // the sprite starts facing down the screen
    showBang = false;
  } // end of initPosition()

  
  public void setMove(int dir)
  /* dir = 0-3 (FORWARD, LEFT, BACK, or RIGHT)
     The actual heading depends on combining the current compass
     value with dir. */
  {
    int actualHd = (compass + dir) % NUM_DIRS;
    Point move = moves[actualHd];
    currPosn.x += move.x;
    currPosn.y += move.y;
    repaint();
  } // end of setMove()


  public void setRotation(int dir)
  /* Rotations affect the compass heading, which will then 
     affect future movements. dir is LEFT or RIGHT. */
  { 
    compass = (compass + dir) % NUM_DIRS;
    userIm = arrowIms[compass];
    repaint();
  } // end of setRotation()


  public void bangAlert()
  // Request a redraw so that the bang message will be displayed
  { showBang = true; 
    repaint();
  }


  public void paintComponent(Graphics g)
  /* Paint the maze image, then the player, then the 
     bang message if it is set. */
  { 
    super.paintComponent(g);   // repaint standard stuff first
    g.drawImage(mazeIm, 0, 0, null);   // draw the maze

    int xPos = currPosn.x + step/2 - arrowWidth/2;
    int yPos = currPosn.y + step/2 - arrowHeight/2;
    g.drawImage(userIm, xPos, yPos, null);    // draw the player

    if (showBang) {    // show the bang message
      g.setColor(Color.red);
      g.setFont(msgFont);
      g.drawString(BANG_MSG, PWIDTH/2, PHEIGHT/2);
      showBang = false;
    }
  }  // end of paintComponent()


} // end of BirdsEye class