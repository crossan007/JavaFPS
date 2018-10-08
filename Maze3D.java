
// Maze3D.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* A FPS without shooting. Traverse a maze. 
   Features:
      * maze read in from separately generated maze plan text file
            -- uses a MazeManager object
      * textured floor, blocks and columns
      * background is a textured sphere
      * little lighting, viewer has a spotlight
      * forward- and backward- facing cameras
      * bird's eye view diagram
      * Swing GUI with three JPanels showing multiple views
      * MazeManager creates 3D and 2D representations of the maze
      * collision detection of maze walls
      * keyboard control of movement, with updates to all views
*/

import javax.swing.*;
import java.awt.*;


public class Maze3D extends JFrame
{
  public Maze3D(String args[]) 
  {
    super("3D Maze");

    String fnm = null;
    if (args.length == 1)
      fnm = args[0];
    else if (args.length == 0) 
      fnm = "maze.txt";  // default maze file
    else {
      System.out.println( "Usage: java Maze3D <fileName>");
      System.exit(0);
    }

    MazeManager mm = new MazeManager(fnm);
    BirdsEye be = new BirdsEye(mm);      // bird's eye view over the maze
    SecondViewPanel secondVP = new SecondViewPanel(mm);  
										// panel with back-facing camera

    WrapMaze3D w3d = new WrapMaze3D(mm, be, secondVP.getCamera2TG() );

    /* The main camera is shown on the left/center. On the right hand side
       is the back facing camera above the bird's eye view (both
       windows are quarter-size of the main one.)
    */
    Container c = getContentPane();
    c.setLayout( new BoxLayout(c, BoxLayout.X_AXIS) );
    c.add(w3d);   // main camera pane
    c.add( Box.createRigidArea( new Dimension(8,0)) ); // some space

    Box vertBox = Box.createVerticalBox();
    vertBox.add( secondVP );   // back-facing camera pane
    vertBox.add( Box.createRigidArea( new Dimension(0,8)) );  // space
    vertBox.add(be);           // bird's eyeview pane
    c.add(vertBox);

    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    pack();
    setResizable(false);    // fixed size display
    setVisible(true);
  } // end of Maze3D()


// -----------------------------------------

  public static void main(String[] args) 
  {  new Maze3D(args); }

} // end of Maze3D class

