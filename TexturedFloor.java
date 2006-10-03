
// CheckerFloor.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* The floor is a blue and green chessboard, with a small red square
   at the (0,0) position on the (X,Z) plane, and with numbers along
   the X- and Z- axes.

   Changes to the Shooter3D version:
      * 2D text in makeText() is set unpickable

*/

import java.awt.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.Text2D;
import javax.vecmath.*;

import java.util.ArrayList;


public class TexturedFloor
{
  private final static int Floor_Len = 20;  // should be even


  private final static Color3f clear = new Color3f(1.0f, 1.0f, 1.0f);
  private final static Color3f other = new Color3f(0.0f, 0.5f, 1.0f);


  private BranchGroup floorBG;


  public TexturedFloor() //sets colors
  {
    ArrayList blueCoords = new ArrayList();
    ArrayList greenCoords = new ArrayList();
    floorBG = new BranchGroup();

    boolean isBlue;
    for(int z=-Floor_Len; z <= (Floor_Len)-1; z++) 
    {
      isBlue = (z%2 == 0);
      for(int x=-Floor_Len; x <= (Floor_Len)-1; x++) 
      {
        if (isBlue)
          createCoords(x, z, blueCoords);
        else 
          createCoords(x, z, greenCoords);
      }
    }
    floorBG.addChild( new Tiles(blueCoords, other) );
    floorBG.addChild( new Tiles(greenCoords, clear) );

  }  // end of CheckerFloor()


  private void createCoords(int x, int z, ArrayList coords)
  // Coords for a single blue or green square, 
  // its left hand corner at (x,0,z)
  {
    // points created in counter-clockwise order
    Point3f p1 = new Point3f(x, 0.0f, z+1.0f);
    Point3f p2 = new Point3f(x+1.0f, 0.0f, z+1.0f);
    Point3f p3 = new Point3f(x+1.0f, 0.0f, z);
    Point3f p4 = new Point3f(x, 0.0f, z);   
    coords.add(p1); coords.add(p2); 
    coords.add(p3); coords.add(p4);
  }  // end of createCoords()


  public BranchGroup getBG()
  {  return floorBG;  }


}  // end of CheckerFloor class

