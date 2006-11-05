import javax.media.j3d.TransformGroup;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

public class Loader {
	public static TransformGroup loadObject(String s1) {
		TransformGroup tg = new TransformGroup();
		System.out.println("Loadong" + s1);
		try {
			Scene s = null;
			ObjectFile f = new ObjectFile();
			f.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE
					| ObjectFile.STRIPIFY);
			s = f.load(s1);
			tg.addChild(s.getSceneGroup());

		} catch (java.io.FileNotFoundException ex) {
			System.out.println(ex);
		}

		return tg;
	}
}
