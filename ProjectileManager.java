import java.io.File;
import java.util.ArrayList;
import javax.media.j3d.*;
public class ProjectileManager implements Runnable
{
	private Thread runner;
	public static ArrayList allProjectiles;
	public static ArrayList projectiles;
	private static BranchGroup projectileBranch;
	private static boolean canFire=true;
	public ProjectileManager()
	{
		allProjectiles = new ArrayList();
		projectileBranch = new BranchGroup();
		projectileBranch.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND | BranchGroup.ALLOW_CHILDREN_READ | BranchGroup.ALLOW_CHILDREN_WRITE);
		projectiles = new ArrayList();
		File maps = new File("Projectiles");
		String[] children = maps.list();
		for(int x=0;x<children.length;x++)
		{
			if(children[x].endsWith("obj"))
			{
				System.out.println(children[x]);
				projectiles.add(Loader.loadObject("Projectiles\\"+children[x]));
			}
		}
		start();
	}
	public void start()
	{
		if(runner==null)
		{
			runner=new Thread(this);
			runner.start();
		}
	}
	public void run()
	{
		int cf=0;
		while(true)
		{
			for(int x=0;x<allProjectiles.size();x++)
			{
				((Projectile)allProjectiles.get(x)).update();
				
			}
			cf++;
			if(cf==100)
			{
				cf=0;
				
				canFire=true;
			}
			try{
				runner.sleep(10);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
	}
	public static void fire(String pName, Transform3D firedFrom, int fireType)
	{
		if(canFire)
		{
			Projectile tempP = new Projectile( (TransformGroup)(projectiles.get(1)),pName,firedFrom);
			allProjectiles.add(tempP);
			canFire=false;
		}
		
	}
	public BranchGroup getBranchGroup()
	{
		return projectileBranch;
	}
}
