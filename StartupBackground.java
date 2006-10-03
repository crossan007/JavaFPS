import java.awt.*;
import java.util.Random;

public class StartupBackground implements Runnable
{
	Thread runner = null;
	Graphics g;
	Random generator = new Random ();

	public StartupBackground(Graphics graphics)
	{
		g=graphics;
		g.setColor(new Color(80,80,80,80));
		
		
		
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

		while(true)
		{
			
			int size=3000;
			int numOne = generator.nextInt(3000);
			int numThree = generator.nextInt(3000);
			int numTwo = generator.nextInt(100);
			int x=numOne;

			while(size>0)
			{
				size=size-50;
				if(numTwo<50)
				{	
					g.drawOval(numOne,numOne,size,size);

				}
				else
				{	
					g.drawOval(x,numOne,size,size);
					x=x+10;
				
				}

				
			}
			try
			{
				runner.sleep(100);
			}
			catch(Exception e)
			{
				
			}

		}
	}
}
