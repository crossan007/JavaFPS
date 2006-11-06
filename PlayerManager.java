import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.*;

public class PlayerManager 
{

	static BranchGroup playersBranchGroup;
	static ArrayList playerList = new ArrayList();
	
	public PlayerManager()
	{
		playersBranchGroup=new BranchGroup();
		playersBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND | BranchGroup.ALLOW_CHILDREN_WRITE);

		
	}
	public static BranchGroup getPlayerBranchGroup()
	{
		return playersBranchGroup;
	}
	
	public static void actionPerformed(Event e)
	{
		if(e.getType().equals("Position"))
		{
			((Player)playerList.get(findPlayer(e.getSender()))).setTransform((TransformNetwork)e.getData());
		}
	}
	public static void addPlayer(String name,String ipAddress,String Avatar)
	{
		System.out.println("Loadong" + Avatar);
		TransformGroup tt = Loader.loadObject(Avatar);
		Player tempPlayer = new Player(name,tt,ipAddress);
		System.out.println("New Player Added"+tempPlayer);
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


}
