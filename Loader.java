import javax.media.j3d.*;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import java.util.*;

public class Loader {
	static Appearance a;
	static TransformGroup tg;
	public static TransformGroup loadObject(String s1) {
		ObjectFile f = new ObjectFile();
		tg=new TransformGroup();
		Scene s = null;
		try {
			f.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE
					| ObjectFile.STRIPIFY);
			s = f.load(s1);
			tg.addChild(s.getSceneGroup());
			
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}

		// FIGUR TEXTURIEREN
		a= new Appearance();
		try
		{
		TextureLoader loader = new TextureLoader(s1 + ".bmp", null);
		
		Texture2D texture = (Texture2D) loader.getTexture();
		a.setTexture(texture);
		
		Enumeration en = s.getSceneGroup().getAllChildren();
		
		traverse(en);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return tg;
	}

	protected static void traverse(Enumeration e) {
		while (e.hasMoreElements()) {
			Object o = e.nextElement();
			if (o instanceof Shape3D) {
				Shape3D shape = (Shape3D) o;
				shape.setAppearance(a);
				
			}
			if (o instanceof Group) {
				Group g = (Group) o;
				traverse(g.getAllChildren());
			}
		}
	}
}
