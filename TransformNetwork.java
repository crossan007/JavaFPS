import javax.media.j3d.Transform3D;
import java.io.Serializable;
public class TransformNetwork implements Serializable

{
	double[] a;
	public TransformNetwork()
	{
		a=new double[16];
	}
	public void setTransform3D(Transform3D g)
	{
		g.get(a);
	}
	public Transform3D getTransform3D()
	{
		Transform3D b = new Transform3D();
		b.set(a);
		return  b;
	}
}
