import javax.vecmath.*;
import javax.media.j3d.*;
public class Projectile 
{
	private Transform3D motionVector;
	private TransformGroup projectileGroup;
	private Transform3D projectileTransform3D;
	private BranchGroup projectileBranch;
	private String playerName;
	private boolean hasAlerted = false;
	public Projectile(TransformGroup tg,String pName,Transform3D ff)
	{
		projectileBranch=new BranchGroup();
		projectileBranch.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		motionVector = new Transform3D();
		motionVector.setTranslation(new Vector3d(0,0,-.5));
		projectileGroup=(TransformGroup)tg.cloneTree();
		projectileGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		playerName=pName;
		projectileTransform3D = new Transform3D();
		projectileTransform3D.set(ff);
		projectileGroup.setTransform(projectileTransform3D);
		projectileBranch.addChild(projectileGroup);
		
		MainGame.simpleU.addBranchGraph(projectileBranch);
	}
	public void update()
	{
		projectileTransform3D.mul(motionVector);
		projectileGroup.setTransform(projectileTransform3D);
		if(playerName.equals(GameSettings.getPlayerName()))
		{
		for(int x=0;x<PlayerManager.getAllPlayers().size();x++)
		{
			Player rmtP = (Player)(PlayerManager.getAllPlayers().get(x));
			Transform3D blah = rmtP.getTransform();
			double[] check = new double[16];
			double[] check2 = new double[16];
			blah.get(check);
			projectileTransform3D.get(check2);
			double xRmt = check[3];
			double yRmt = check[6];
			double zRmt =check[9];
			double xHere = check2[3];
			double yHere = check2[6];
			double zHere =check2[9];
			
			
			double xtmp,ytmp,ztmp;
			xtmp=xRmt-xHere;
			ytmp=yRmt-yHere;
			ztmp=zRmt-zHere;
			
			xtmp=(xtmp+ytmp+ztmp)/3;
			if(xtmp<.08 && xtmp>0)
			{
				if(!hasAlerted)
				{
					HUD.addNews("Hit "+rmtP.getName());
					NetworkManager.sendEvent(new Event("PlayerHit",GameSettings.getPlayerName(),(Object)rmtP.getName()));
					hasAlerted=true;
				}
			}
			
		}
		}
	}
	public BranchGroup getBranch()
	{
		return projectileBranch;
	}
}
