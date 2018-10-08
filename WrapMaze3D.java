
// WrapMaze3D.java
// Andrew Davison, April 2005, ad@fivedots.coe.psu.ac.th

/* Illustrates a FPS (without shooting). Travel round a maze
   with two cameras (front, back), and a bird's eye overview
   diagram.

   The Background is a sphere with a texture wrapped around the
   inside of it. The floor is textured, and a maze is placed on 
   top of it.
   The lighting is reduced -- no ambient lights.

   The main user viewpoint has a spotlight added. (There is
   also some unused code to add a cone at the viewpoint 
   position, to act as an avatar.)

   The viewpoint is adjusted to have a wider field-of-vision (FOV),
   and changed forward/backward clip distances. The viewpoint is 
   placed at the designated starting point in the maze, and rotated
   to point in the positive z- direction.

   Keyboard controls move the viewpoint.
*/

import javax.swing.*;
import java.awt.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.image.*;


public class WrapMaze3D extends JPanel
{
  private static final int PWIDTH = 512;   // size of panel
  private static final int PHEIGHT = 512; 

  private static final int BOUNDSIZE = 100;  // larger than world
  private static final String SKY_TEX = "images/lava.jpg";  // sky texture

  private SimpleUniverse su;
  private BranchGroup sceneBG;
  private BoundingSphere bounds;   // for environment nodes

  private MazeManager mazeMan;      // maze manager
  private TransformGroup camera2TG; // back-facing camera


  public WrapMaze3D(MazeManager mm, BirdsEye be, TransformGroup c2TG)
  // construct the scene and the main camera
  {
    mazeMan = mm;       // ref to maze manager
    camera2TG = c2TG;   // ref to second back-facong camera

    setLayout( new BorderLayout() );
    setOpaque( false );
    setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

    GraphicsConfiguration config =
					SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas3D = new Canvas3D(config);
    add("Center", canvas3D);
    canvas3D.setFocusable(true); 
    canvas3D.requestFocus();
    su = new SimpleUniverse(canvas3D);

    createSceneGraph();
    prepareViewPoint(be);

    su.addBranchGraph( sceneBG );
  } // end of WrapMaze3D()


  void createSceneGraph()
  // initilise the scene 
  { 
    sceneBG = new BranchGroup();
    bounds = new BoundingSphere(new Point3d(0,0,0), BOUNDSIZE); 

    lightScene();     // add the lights
    addBackground();  // add the sky
    
    // add the textured floor
    TexturedFloor floor = new TexturedFloor();
    sceneBG.addChild( floor.getBG() );

    sceneBG.addChild( mazeMan.getMaze() );  // add maze, using MazeManager
    sceneBG.addChild( camera2TG );          // add second camera

    sceneBG.compile();   // fix the scene
  } // end of createSceneGraph()



  private void lightScene()
  // *No* ambient light, 2 directional lights
  {
    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

    // Set up the ambient light
    AmbientLight ambientLightNode = new AmbientLight(white);
    ambientLightNode.setInfluencingBounds(bounds);
    // sceneBG.addChild(ambientLightNode);   // ambient commented out

    // Set up the directional lights
    Vector3f light1Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);
       // left, down, backwards 
    Vector3f light2Direction  = new Vector3f(1.0f, -1.0f, 1.0f);
       // right, down, forwards

    DirectionalLight light1 = 
            new DirectionalLight(white, light1Direction);
    light1.setInfluencingBounds(bounds);
    sceneBG.addChild(light1);

    DirectionalLight light2 = 
        new DirectionalLight(white, light2Direction);
    light2.setInfluencingBounds(bounds);
    sceneBG.addChild(light2);
  }  // end of lightScene()


/*
  private void addBackground()
  // add a geometric background using a Background node
  {
    System.out.println("Loading sky texture: " + SKY_TEX);
    TextureLoader tex = new TextureLoader(SKY_TEX, null);

    Sphere sphere = new Sphere(1.0f, Sphere.GENERATE_NORMALS |
			      Sphere.GENERATE_NORMALS_INWARD |
				  Sphere.GENERATE_TEXTURE_COORDS, 4);   // default = 15
    Appearance backApp = sphere.getAppearance();
	backApp.setTexture( tex.getTexture() );

	BranchGroup backBG = new BranchGroup();
    backBG.addChild(sphere);

    Background bg = new Background();
    bg.setApplicationBounds(bounds);
    bg.setGeometry(backBG);

    sceneBG.addChild(bg);
  }  // end of addBackground()
*/

  private void addBackground()
  // Add backdrop painted onto a inward facing sphere.
  // No use made of Background.
  // Seems more reliable on some older machines (?) 
  {
    System.out.println("Loading sky texture: " + SKY_TEX);
    TextureLoader tex = new TextureLoader(SKY_TEX, null);

    // create an appearance and assign the texture
    Appearance app =  new Appearance();
	app.setTexture( tex.getTexture() );

    Sphere sphere = new Sphere(100.0f,    // radius to extend to edge of scene
			   Sphere.GENERATE_NORMALS_INWARD |
			   Sphere.GENERATE_TEXTURE_COORDS, 4, app);   // default divs = 15

    sceneBG.addChild( sphere );
 } // end of addBackground()



// --------------------- user controls ----------------------------------


  private void prepareViewPoint(BirdsEye be)
  {
    // adjust viewpoint parameters
    View userView = su.getViewer().getView();
    userView.setFieldOfView( Math.toRadians(90.0));  // wider FOV
    // 10 and 0.1; keep ratio between 100-1000
    userView.setBackClipDistance(20);      // can see a long way
    userView.setFrontClipDistance(0.05);   // can see close things

    ViewingPlatform vp = su.getViewingPlatform();

    // add a spotlight and avatar to viewpoint
    PlatformGeometry pg = new PlatformGeometry();
    pg.addChild( makeSpot() );
    // pg.addChild( makeAvatar() );    // avatar not used here
    vp.setPlatformGeometry( pg );

    // fix starting position and orientation of viewpoint
    TransformGroup steerTG = vp.getViewPlatformTransform();
    initViewPosition(steerTG);

    // set up keyboard controls
    KeyBehavior keybeh = new KeyBehavior(mazeMan, be, camera2TG);
    keybeh.setSchedulingBounds(bounds);
    vp.setViewPlatformBehavior(keybeh);
  } // end of prepareViewPoint()


  private void initViewPosition(TransformGroup steerTG)
  // rotate and move the viewpoint
  {
    Transform3D t3d = new Transform3D();
    steerTG.getTransform(t3d);
    Transform3D toRot = new Transform3D();
    toRot.rotY(-Math.PI);   
    // rotate 180 degrees around Y-axis, so facing along positive z-axis

    t3d.mul(toRot);
	t3d.setTranslation( mazeMan.getMazeStartPosn() );  // place at maze start
    steerTG.setTransform(t3d); 
  }  // end of initViewPosition()


  private SpotLight makeSpot()
  // a spotlight to help the user see in the (relative) darkness
  {
    SpotLight spot = new SpotLight();
    spot.setPosition(0.0f, 0.5f, 0.0f);      // a bit above the user
    spot.setAttenuation(0.0f, 1.2f, 0.0f);   // linear attentuation
    spot.setSpreadAngle( (float)Math.toRadians(30.0));  // smaller angle
    spot.setConcentration(5.0f);            // reduce strength quicker
    spot.setInfluencingBounds(bounds);
    return spot;
  }  // end of makeSpot()


  private TransformGroup makeAvatar()
  /* Not used in this code, since there is no view *of* the user
     which requires an avatar to be visible. */
  {
    Transform3D t3d = new Transform3D();
    t3d.rotX(Math.PI/2);    // rotate so top of cone is facing front
    TransformGroup userTG = new TransformGroup(t3d);

    userTG.addChild( new Cone(0.35f, 1.0f) );  // avatar is a thin cone
    return userTG;
  }  // end of makeAvatar()

} // end of WrapMaze3D class

