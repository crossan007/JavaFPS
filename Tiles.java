
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickTool;

import java.util.ArrayList;
import com.sun.j3d.utils.image.*;


public class Tiles extends Shape3D 
{
  private QuadArray plane;
  
  

  public Tiles(ArrayList coords, Color3f col) 
  {
	Color3f clear = new Color3f(1.0f, 1.0f, 1.0f);
	
    plane = new QuadArray(coords.size(), 
				GeometryArray.COORDINATES | GeometryArray.COLOR_3 );
    createGeometry(coords, col);
    createAppearance();
    String TileTexture = "Images\\cobbles.jpg";
    Appearance TileApp;
    TileApp = makeApp(clear, TileTexture);    // clear texture for tile
    

  }    


  private void createGeometry(ArrayList coords, Color3f col)
  { 
    int numPoints = coords.size();

    Point3f[] points = new Point3f[numPoints];
    coords.toArray( points );
    plane.setCoordinates(0, points);

    Color3f cols[] = new Color3f[numPoints];
    for(int i=0; i < numPoints; i++)
      cols[i] = col;
    plane.setColors(0, cols);

    setGeometry(plane);
  }  // end of createGeometry()


  private void createAppearance()
  {
    Appearance app = new Appearance();

    PolygonAttributes pa = new PolygonAttributes();
    pa.setCullFace(PolygonAttributes.CULL_NONE);   
 
    app.setPolygonAttributes(pa);

    setAppearance(app);
  }  // end of createAppearance()
  private Appearance  makeApp(Color3f colTile, String texFnm)
  {
    Appearance app = new Appearance();

//  mix the texture and the material colour
    TextureAttributes ta = new TextureAttributes();
    ta.setTextureMode(TextureAttributes.MODULATE);
    app.setTextureAttributes(ta);

    // load and set the texture
    System.out.println("Loading tile texture from " + texFnm);
    TextureLoader loader = new TextureLoader(texFnm, null);
    Texture2D texture = (Texture2D) loader.getTexture();
    app.setTexture(texture);      // set the texture

    // add a coloured material
    Material mat = new Material(colTile, colTile, colTile, colTile, 20.f);
    mat.setLightingEnable(true);
    app.setMaterial(mat);
 
    return app;

  }  // end of makeApp()


} // end of Tiles class
