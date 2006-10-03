import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.*;
public class Rotator implements Runnable
{
	private Thread runner = null;
	private TransformGroup t = null;
	Transform3D tTemp = null;
	Transform3D tChange = null;

	public void start()
	{
		if(runner==null)
		{
			runner=  new Thread(this);
			runner.start();
		}
	}
	public Rotator(TransformGroup tg)
	{
		t=tg;
		tTemp = new Transform3D();
		tChange = new Transform3D();
		
	}
	public  void run()
	{
		int x=0;
		
		while(true)
		{
			t.getTransform(tTemp);
			tChange.setIdentity();
			tChange.rotX(Math.PI/8);
			tTemp.mul(tChange);
			t.setTransform(tTemp);
			
			//System.out.println("Rotating "+x);
			x=x+1;
			try
			{
				runner.sleep(50);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
