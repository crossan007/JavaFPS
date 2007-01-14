import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.Font;
import java.util.*;
import com.sun.j3d.utils.geometry.Text2D
;public class PlayerManager 
{

	static BranchGroup playersBranchGroup;
	static ArrayList playerList = new ArrayList();
	static Transform3D blah = new Transform3D();
	
	public PlayerManager()
	{
		playersBranchGroup=new BranchGroup();
		playersBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND | BranchGroup.ALLOW_CHILDREN_WRITE);

		
	}
	
	
	public static void actionPerformed(Event e)
	{
		if(e.getType().equals("Position"))
		{
			int sender = findPlayer(e.getSender());
			//System.out.println("Found sender");
			blah.set((double[])(e.getData()));
			//System.out.println("Trans formSet");
			((Player)(playerList.get(sender))).setTransform(blah);
			//System.out.println("Transform Made");
			
		}
	}
	public static void addPlayer(String name,String ipAddress,String Avatar)
	{
		System.out.println("Loadong" + Avatar);
		TransformGroup tt = Loader.loadObject(Avatar);
		
		Text2D text = new Text2D(name,new Color3f(1,0,0),"Times New Roman",24,0);
		text.setRectangleScaleFactor(1.0f);
		tt.addChild(text);
		Player tempPlayer = new Player(name,tt,ipAddress);
		System.out.println("New Player Added "+tempPlayer);
		HUD.addNews("New Player Added "+tempPlayer);
		playerList.add(tempPlayer);
		MainGame.simpleU.addBranchGraph(tempPlayer.getBranchGroup());
	}
	public static int findPlayer(String Name)
	{
		Player temp;
		for(int x=0;x<playerList.size();x++)
		{
			temp = (Player)playerList.get(x);
			if(temp.getName().equals(Name))
			{
				return x;
			}
		}
		return -1;
	}
	public static ArrayList getAllPlayers()
	{
		return playerList;
	}


}
