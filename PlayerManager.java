import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.*;

public class PlayerManager 
{

	static BranchGroup playersBranchGroup;
	static ArrayList playerList = new ArrayList();
	
	public PlayerManager()
	{
		playersBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
	}
	public static BranchGroup getPlayerBranchGroup()
	{
		return playersBranchGroup;
	}
	
	public static void actionPerformed(Event e)
	{
		if(e.getType().equals("Network-ICON"))
		{
			Player temp = new Player(e.getSender(),Loader.loadObject("Models\\"+(String)e.getData()));
			playerList.add(temp);
			playersBranchGroup.addChild(temp.getTransformGroup());
		}
		else if(e.getType().equals("Pos-Update"))
		{
			((Player)playerList.get(findPlayer(e.getSender()))).setTransform((Transform3D)e.getData());
		}
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
