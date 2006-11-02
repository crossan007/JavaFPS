import javax.media.j3d.*;
import javax.vecmath.*;

public class Player 
{
	private TransformGroup tg;
	private String name;
	public Player(String Name, TransformGroup start)
	{
		tg=start;
		name=Name;
	}
	public void setTransform(Transform3D newT)
	{
		tg.setTransform(newT);
	}
	public String getName()
	{
		return name;
		
	}
	public TransformGroup getTransformGroup()
	{
		return tg;
	}

}
