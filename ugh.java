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

public class ugh {

	/**
	 * @param args
	 */
	static SimpleUniverse simpleU;
	public static void main(String[] args)
	{
		Frame window = new Frame();
		window.setSize(800,600);
		window.setVisible(true);
		Canvas3D myCanvas  = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		simpleU=new SimpleUniverse(myCanvas);
		
		myCanvas.setSize(800,600);
		myCanvas.setVisible(true);
		window.add(myCanvas);
		
		
		BranchGroup aa = new BranchGroup();
		TransformGroup p;
		p=Loader.loadObject("Models\\gun.obj");
		p.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		aa.addChild(p);
		BoundingSphere bounds = new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 500.0);
      	Color3f ambientColor = new Color3f (1.0f, 1.0f, 1.0f);
      	AmbientLight ambientLightNode = new AmbientLight (ambientColor);
      	ambientLightNode.setInfluencingBounds (bounds);
      	aa.addChild (ambientLightNode);
		Rotator r = new Rotator(p);
		r.start();
		simpleU.addBranchGraph(aa);
	
		
		simpleU.getViewingPlatform().setNominalViewingTransform();
		
	}

}
