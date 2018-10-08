
// MazeManager.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* Read in a maze plan and create two maze representations:
      * a BranchGroup for the 3D maze in the scene
      * a 2D image for the bird's eye diagram

   The input maze plan is a text file showing the position
   of blocks, cylinders, and the starting point. It is generated
   by MazeGen or MazeMaker in /MazeGen.

   The Branchgroup contains a collection of cylinders and blocks,
   transformed to the places marked in the maze plan.
 
   When viewed from above, the top-left corner of the maze is
   located at (0,0) on the XZ plane.
   This means that the (x,y) coords in the maze plan map onto
   (x,**Z**) coords in the 3D scene

   Manages the starting position info.
   Checks for collision detection.
 */

import java.awt.*;
import java.io.*;
import java.awt.image.*;

import javax.vecmath.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;


public class MazeManager 
{
  private static final int LEN = 40;   // max sides of maze plan (should be even)
									   // the maze can be smaller than 40*40
  private static final double USER_HEIGHT = 1.0;  // where the user's eyes are

  private static final int IMAGE_LEN = 240;   
		 // size of 2D maze image (should be a multiple of LEN)
  private static final int IMAGE_STEP = IMAGE_LEN/LEN;
        // one char in the maze plan will be IMAGE_STEP wide/deep in the 2D image

  // obstacle dimensions (for both blocks and cylinders)
  private final static float RADIUS = 0.5f;
     // for a block, RADIUS == length of a side/2
  private final static float HEIGHT = 3.0f;

  // colours for obstacle material
  private final static Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
  private final static Color3f specular = new Color3f(1.0f, 1.0f, 1.0f);
  private final static Color3f blue = new Color3f(0.0f, 0.0f, 1.0f); 
  private final static Color3f medgreen = new Color3f(0.0f, 0.5f, 0.1f);

  // textures used for the obstacles
  private final static String BLOCK_TEX = "images/plate.jpg";
  private final static String CYL_TEX = "images/cobbles.jpg";


  private Appearance blockApp, cylApp;   // obstacle appearances

  private char[][] maze;    // stores the input maze plan;
  /*  The coords are maze[Z]{X] 
      i.e. x-coords along row, z-coords running down
      Remember that the maze plan's y-coords have been mapped to
      3D scene z-coords.  */

  private int xStartPosn, zStartPosn;    // the player's starting coordinate

  private BranchGroup mazeBG;      // scene subgraph for the maze
  private BufferedImage mazeImg;   // holds the 2D image for the maze

  
  public MazeManager(String fn) 
  { initialiseVars();
    readFile(fn);
    buildMazeReps();
  } // end of MazeManager()


  private void initialiseVars()
  {
    maze = new char[LEN][LEN];
    for(int z=0; z<LEN; z++)    // an empty maze
      for(int x=0; x<LEN; x++)
        maze[z][x] = ' '; 

    xStartPosn = LEN/2;    // default position (top row, in the middle)
    zStartPosn = 0;

    // initialise the two maze representations
    mazeBG = new BranchGroup();
    mazeImg = new BufferedImage(IMAGE_LEN, IMAGE_LEN,
								BufferedImage.TYPE_INT_ARGB);

    // the Appearance nodes used by all the blocks and cylinders
    blockApp = makeApp(blue, BLOCK_TEX);    // blue texture for blocks
    cylApp = makeApp(medgreen, CYL_TEX);    // green texture for cylinders
  }  // end of initialiseVars()


  private Appearance makeApp(Color3f colObs, String texFnm)
  {
    Appearance app = new Appearance();

    // mix the texture and the material colour
    TextureAttributes ta = new TextureAttributes();
    ta.setTextureMode(TextureAttributes.MODULATE);
    app.setTextureAttributes(ta);

    // load and set the texture
    System.out.println("Loading obstacle texture from " + texFnm);
    TextureLoader loader = new TextureLoader(texFnm, null);
    Texture2D texture = (Texture2D) loader.getTexture();
    app.setTexture(texture);      // set the texture

    // add a coloured material
    Material mat = new Material(colObs, black, colObs, specular, 20.f);
    mat.setLightingEnable(true);
    app.setMaterial(mat);
    return app;
  }  // end of makeApp()



  private void readFile(String fn)
  // Initialise maze[][] by reading the maze plan from fn
  {
    System.out.println("Reading maze plan from " + fn);
    try {
      BufferedReader br = new BufferedReader( new FileReader(fn));
      String line;
      char charLine[];
      int numRows = 0;
      while((numRows < LEN) && ((line = br.readLine()) != null)) { 
        charLine = line.toCharArray();
        int x=0;
        while((x < LEN) && (x < charLine.length)) { // ignore any extra chars
          maze[numRows][x] = charLine[x];
          x++;
        }
        numRows++;
      }
      br.close();
    } 
    catch (IOException e) 
    { System.out.println("Error reading maze plan from " + fn);
      System.exit(0);
    }
  } // end of readFile()



  private void buildMazeReps() 
  /* Create a BranchGroup and BufferedImage to represent the maze.

     The maze plan array will contain spaces, a single 's' (starting
     point), and many 'c's and 'b's (cylinders, blocks).
  */
  {
    System.out.println("Building maze representations... please wait");

    char ch;
    Graphics g = (Graphics) mazeImg.createGraphics();
    g.setColor(Color.white);

    for (int z=0; z<LEN; z++) { 
      for (int x=0; x<LEN; x++) {
        ch = maze[z][x];
        if (ch == 's') {    // starting position
          xStartPosn = x; zStartPosn = z;
          maze[z][x] = ' ';   // clear cell
        }
        else if (ch == 'b') {   // block
          mazeBG.addChild( makeObs(ch, x, z, blockApp) );
          drawBlock(g, x, z);
        }
        else if (ch == 'c') {  // cylinder
          mazeBG.addChild( makeObs(ch, x, z, cylApp) );
          drawCylinder(g, x, z);
        }
      }
    }
    g.dispose();
  } // end of buildMazeReps()



  private TransformGroup makeObs(char ch, int x, int z, Appearance app)
  // place an obstacle (block/cylinder) at (x,z) with appearance app
  {
    Primitive obs;
    if (ch == 'b')  // blue textured block
      obs = new Box(RADIUS, HEIGHT/2, RADIUS, 
				Primitive.GENERATE_TEXTURE_COORDS |
				Primitive.GENERATE_NORMALS, app );
    else   // green textured cylinder
      obs = new Cylinder(RADIUS, HEIGHT, 
				Primitive.GENERATE_TEXTURE_COORDS |
				Primitive.GENERATE_NORMALS, app );

    // position the obstacle so its base is resting on the floor at (x,z)
    TransformGroup posnTG = new TransformGroup();
    Transform3D trans = new Transform3D();
    trans.setTranslation( new Vector3d(x, HEIGHT/2, z) );  // move up
    posnTG.setTransform(trans);
    posnTG.addChild(obs); 
    return posnTG;
  }  // end of makeObs()


  private void drawBlock(Graphics g, int i, int j)
  // draw a blue box in the 2D image
  { g.setColor(Color.blue);
    g.fillRect(i*IMAGE_STEP, j*IMAGE_STEP, IMAGE_STEP, IMAGE_STEP);
  }

  private void drawCylinder(Graphics g, int i, int j)
  // draw a green circle in the 2D image
  { g.setColor(Color.green);
    g.fillOval(i*IMAGE_STEP, j*IMAGE_STEP, IMAGE_STEP, IMAGE_STEP);
  }


  public BranchGroup getMaze()
  // called by WrapMaze3D to get a reference to the 3D maze
  {  return mazeBG;  }


  public Vector3d getMazeStartPosn()
  // called by WrapMaze3D
  { return new Vector3d( xStartPosn, USER_HEIGHT, zStartPosn); }


  public BufferedImage getMazeImage()
  // called by BirdsEye to get a reference to the 2D maze
  {  return mazeImg;  }


  public Point getImageStartPosn()
  // called by BirdsEye
  {  return new Point( xStartPosn*IMAGE_STEP, zStartPosn*IMAGE_STEP);  }


  public int getImageStep()
  // called by BirdsEye. It will use IMAGE_STEP to
  // convert a user's move into a move in terms of pixels
  {  return IMAGE_STEP;  }



  public boolean canMoveTo(double xWorld, double zWorld)
  // is (xWorld, zWorld) free of obstacles?
  // Called by the KeyBehavior object to test a possible move.
  {
    int x = (int) Math.round(xWorld);
    int z = (int) Math.round(zWorld);

    if ((x < 0) || (x >= LEN) || (z < 0) || (z >= LEN))
      return true;    // since outside the possible maze diemensions

    if ((maze[z][x] == 'b') || (maze[z][x] == 'c'))
      return false;    // since location occupied by block or cylinder

    return true;
  }  // end of canMoveTo()


} // end of MazeManager class
