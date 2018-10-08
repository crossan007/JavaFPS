
// TexturedPlane.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* Create a QuadArray with a texture mapping and lighting effects.

   The array may contain any number of verticies.
   The texture is applied to each quad in the array, to
   give a tiled texture effect. A user-supplied normal
   is applied to every vertex (we are assuming that all the
   verticies are part of a plane).
*/

import java.util.ArrayList;

import javax.vecmath.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;


public class TexturedPlane extends Shape3D 
{
  private QuadArray plane;
  private int numPoints;


  public TexturedPlane(ArrayList coords, String fnm, Vector3f normal) 
  {
    numPoints = coords.size();
    plane = new QuadArray(numPoints, 
                     GeometryArray.COORDINATES | 
					 GeometryArray.TEXTURE_COORDINATE_2 |
					 GeometryArray.NORMALS  );
    createGeometry(coords, normal);
    createAppearance(fnm);
  } // end of TexturedPlane()


  private void createGeometry(ArrayList coords, Vector3f normal)
  {
    // set coordinates
    Point3f[] points = new Point3f[numPoints];
    coords.toArray( points );
    plane.setCoordinates(0, points);

    // assign texture coords to each quad
    // counter-clockwise, from bottom left
    TexCoord2f[] tcoords = new TexCoord2f[numPoints];
    for(int i=0; i < numPoints; i=i+4) {
      tcoords[i] = new TexCoord2f(0.0f, 0.0f);   // for 1 point
      tcoords[i+1] = new TexCoord2f(1.0f, 0.0f);
      tcoords[i+2] = new TexCoord2f(1.0f, 1.0f);
      tcoords[i+3] = new TexCoord2f(0.0f, 1.0f);
    }
    plane.setTextureCoordinates(0, 0, tcoords);

    // all the coords are given the same normal since they are in a plane
    for(int i=0; i < numPoints; i++)
      plane.setNormal(i, normal);

    setGeometry(plane);
  }  // end of createGeometry()


  private void createAppearance(String fnm)
  {
    Appearance app = new Appearance();

    // mix the texture and the material colour
    TextureAttributes ta = new TextureAttributes();
    ta.setTextureMode(TextureAttributes.MODULATE);
    app.setTextureAttributes(ta);

    // load and set the texture
    System.out.println("Loading texture for plane from " + fnm);
    TextureLoader loader = new TextureLoader(fnm, null);
    Texture2D texture = (Texture2D) loader.getTexture();
    app.setTexture(texture);      // set the texture

    // set a default white material
    Material mat = new Material();
    mat.setLightingEnable(true);    // lighting switched on
    app.setMaterial(mat);

    setAppearance(app);
  }  // end of createAppearance()

} // end of TexturedPlane class

