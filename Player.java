import javax.media.j3d.*;
import javax.vecmath.*;

public class Player 
{
	private TransformGroup tg;
	private String name;
	public Player(String Name, TransformGroup start,String IPAddress)
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
	public BranchGroup getBranchGroup()
	{
		BranchGroup bg = new BranchGroup();
		bg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND| BranchGroup.ALLOW_CHILDREN_WRITE);
		
		bg.addChild(tg);
		return bg;
	}
	public String toString()
	{
		return name;
	}

}
