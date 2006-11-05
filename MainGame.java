import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.*;
import com.sun.j3d.utils.image.TextureLoader;

import javax.swing.*;


import java.util.Enumeration;

public class MainGame implements EventListener
{
	   
	static SimpleUniverse simpleU; //our simple universe object
	
	CameraMover cm;
	MapManager mapManager;
	NetworkManager networkManager;
	PlayerManager playerManager;
	Sound VOIPManager;
	
	
	public void actionPerformed(Event e)
	{
		
		if(e.getType().equals("Sound"))
		{
			VOIPManager.actionPreformed(e);
		}
		
		PlayerManager.actionPerformed(e);
	}
	public MainGame() // constructor for main game. initialized full screen
						// mode
	{
		Debug.turnOn();
		Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		
		simpleU = new SimpleUniverse(c); // setup the SimpleUniverse, attach
		BranchGroup scene = new BranchGroup();
		
		playerManager = new PlayerManager();
		networkManager = new NetworkManager(this);
		
		
		
		VOIPManager = new Sound();
		VOIPManager.addNetworkCommunications(networkManager);
		VOIPManager.start();
		
		if(!GameSettings.getIsServer())
		{
			networkManager.connect();
		}
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		Frame f = new Frame("Vendetta Online");
		Frame HUDframe = new Frame();
		f.setUndecorated(true);
		HUDframe.setUndecorated(true);
		f.setAlwaysOnTop(false);
		HUDframe.setAlwaysOnTop(true);
		
		
		
		mapManager=new MapManager();
		
		
		
		
		
		TransformGroup gun = Loader.loadObject("Models\\gun.obj");
		gun.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D tempCam = new Transform3D();
		tempCam.setTranslation(new Vector3d(0,1,0));
		simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(tempCam);

		cm = new CameraMover(gun, simpleU.getViewingPlatform().getViewPlatformTransform());
		
		BoundingSphere bounds = new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 500.0);
      	Color3f ambientColor = new Color3f (1.0f, 1.0f, 1.0f);
      	AmbientLight ambientLightNode = new AmbientLight (ambientColor);
      	ambientLightNode.setInfluencingBounds (bounds);
      	scene.addChild (ambientLightNode);
      
		
      	scene.addChild(gun);
      	
		scene.compile();

		simpleU.addBranchGraph(scene); // add your SceneGraph to the
		simpleU.addBranchGraph(mapManager.getBranchGraph());
		
	
		c.addKeyListener(cm);
		c.addMouseMotionListener(cm);
		c.setFocusable(true);
		c.requestFocus();
		f.add(c);
		
		
		f.setSize(d);
		f.setResizable(false);
		f.setVisible(true);
		f.setCursor(Cursor.CROSSHAIR_CURSOR);
		
		HUDframe.setSize((int)d.getWidth(),50);
		HUDframe.setLocation(0,((int)d.getHeight()-50));
		HUDframe.setBackground(Color.BLACK);
		HUDframe.setVisible(true);
		HUD playerHUD = new HUD(HUDframe.getGraphics());
		playerHUD.start();
		

	}
	 
	
	

	
}
