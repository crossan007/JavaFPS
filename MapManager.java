import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.*;
import javax.swing.*;
import java.io.*;


public  class MapManager
{
	static String mapName;
	static ArrayList objects = new ArrayList();
	private static String SKY_TEX;  // sky texture
	
	public MapManager()
	{

		//setMap(getAllMaps()[0]);
		
		
	}

	
	public static String[] getAllMaps()
	{
		File maps = new File("Maps");
		String[] children = maps.list();
		return children;
		
	}
	
	public static void setMap(String mName)
	{
		mapName = mName;
		System.out.println(mapName);
		objects.clear();
		try
		{
			FileInputStream fis = new FileInputStream("Maps\\"+mapName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			SKY_TEX="Images\\"+(String)ois.readObject();
			
			
			while(objects.add(ois.readObject()))
			{
				System.out.println("Loading Map Entry for: "+(ObjectInfo)objects.get(objects.size()-1));
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public static String getMap()
	{
		return mapName;
	}
	public BranchGroup getBranchGraph()
	{
		
		 BranchGroup objRoot = new BranchGroup();
		

		 addBackground(objRoot);  // add the sky
		
		
		
		TransformGroup tempObjectTransform = new TransformGroup();
		Transform3D positionTransform = new Transform3D();
		Transform3D rotationTransformX = new Transform3D();
		Transform3D rotationTransformY = new Transform3D();
		Transform3D rotationTransformZ = new Transform3D();
		
		for(int x=0;x<objects.size();x++)
		{
			ObjectInfo tempObject = (ObjectInfo)objects.get(x);
			tempObjectTransform = Loader.loadObject(tempObject.getFileName());
			positionTransform.setTranslation(new Vector3d(tempObject.getXPosition(),tempObject.getYPosition(),tempObject.getZPosition()));
			rotationTransformX.rotX(tempObject.getXRotation());
			rotationTransformX.rotY(tempObject.getYRotation());
			rotationTransformX.rotZ(tempObject.getZRotation());
			positionTransform.mul(rotationTransformX);
			positionTransform.mul(rotationTransformY);
			positionTransform.mul(rotationTransformZ);
			tempObjectTransform.setTransform(positionTransform);
			objRoot.addChild(tempObjectTransform);
			
		}
		return objRoot;
		
	}
	private void addBackground(BranchGroup scene)
	  // add a geometric background using a Background node
	  {
		System.out.println("Loading sky texture: " + SKY_TEX);
	    TextureLoader tex = new TextureLoader(SKY_TEX, null);

	    // create an appearance and assign the texture
	    Appearance app =  new Appearance();
		app.setTexture( tex.getTexture() );

	    Sphere sphere = new Sphere(58.0f,    // radius to extend to edge of scene
				   Sphere.GENERATE_NORMALS_INWARD |
				   Sphere.GENERATE_TEXTURE_COORDS, 1, app);   // default divs = 15

	    scene.addChild( sphere );
	  }  // end of addBackground()
}
