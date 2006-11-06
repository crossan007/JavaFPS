import java.util.ArrayList;
public class ProjectileManager implements Runnable
{
	private Thread runner;
	public static ArrayList allProjectiles;
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
		for(int x=0;x<allProjectiles.size();x++)
		{
			((Projectile)allProjectiles.get(x)).update();
		}
		
	}
	public void fire()
	{
		
	}
}
