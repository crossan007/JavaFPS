import javax.media.j3d.*;
import javax.vecmath.*;

import sun.reflect.generics.tree.ReturnType;

public class Player 
{
	private TransformGroup tg;
	private String name;
	public Player(String Name, TransformGroup start,String IPAddress)
	{
		tg=start;
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		name=Name;
	}
	public void setTransform(Transform3D newT)
	{
		//System.out.println("TransformChanging");
		tg.setTransform(newT);
		//System.out.println(newT);
		//System.out.println("TransformChanged");
	}
	public Transform3D getTransform()
	{	
		Transform3D temp = new Transform3D();
		tg.getTransform(temp);
		return temp;
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
