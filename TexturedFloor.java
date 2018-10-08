
// TexturedFloor.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* A TexturedFloor is a single TexuredPlane, filled with
   quads whose coordinates go from
   FLOOR_LEN/2 to -FLOOR_LEN/2 in steps of STEP along
   the x- and z- axes.

   The texture comes from FLOOR_IMG.
   The floor coordinates all have an up-facing normal.
*/

import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.ArrayList;


public class TexturedFloor
{
  private final static int FLOOR_LEN = 80;  // should be even
  private final static int STEP = 4;  // should divide into FLOOR_LEN

  private final static String FLOOR_IMG = "images/stone.jpg";

  private BranchGroup floorBG;  // parent for the TexturedPlane node


  public TexturedFloor()
  // create quad coords, make TexturedPlane, add to floorBG
  {
    ArrayList coords = new ArrayList();
    floorBG = new BranchGroup();

    // create coords for the quad
    for(int z=FLOOR_LEN/2; z >= (-FLOOR_LEN/2)+STEP; z-=STEP) {  // front to back
      for(int x=-FLOOR_LEN/2; x <= (FLOOR_LEN/2)-STEP; x+=STEP)  // left to right
          createCoords(x, z, coords);
    }

    Vector3f upNormal = new Vector3f(0.0f, 1.0f, 0.0f);   // pointing upwards
    floorBG.addChild( new TexturedPlane(coords, FLOOR_IMG, upNormal) );
  }  // end of TexturedFloor()


  private void createCoords(int x, int z, ArrayList coords)
  {
    // points created in counter-clockwise order, from front left
    // of length STEP
    Point3f p1 = new Point3f(x, 0.0f, z);   
    Point3f p2 = new Point3f(x+STEP, 0.0f, z);
    Point3f p3 = new Point3f(x+STEP, 0.0f, z-STEP);
    Point3f p4 = new Point3f(x, 0.0f, z-STEP);
    coords.add(p1); coords.add(p2); 
    coords.add(p3); coords.add(p4);
  }  // end of createCoords()


  public BranchGroup getBG()
  {  return floorBG;  }

}  // end of TexturedFloor class

