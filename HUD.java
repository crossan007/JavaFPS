
import java.awt.*;

import java.awt.Dimension;


public class HUD implements Runnable
{

	int kills=0;	//number of kills 
	int playerHealth=100;  //Health
	String playerName;
	Graphics g;
	Thread runner;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	
	/*
	 * constructor
	 */
	public HUD(Graphics myGraphics)
	{
		playerName=GameSettings.getPlayerName();
		g=myGraphics;

		
		
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
			g.setColor(new Color (80,80,80));
			g.setFont((new Font("futuraBlack BT",Font.PLAIN,20)));
			g.drawString(playerName,0,(int)d.getHeight()/25);
			g.drawString("Kills: "+kills,(int)d.getWidth()/2,(int)d.getHeight()/25);
			g.drawString("Ammo: x"+kills,(int)d.getWidth()/3,(int)d.getHeight()/25);
			g.drawString("Health: ",(int)d.getWidth()/7,(int)d.getHeight()/25);
			g.setColor(Color.RED);
			g.fillRect((int)d.getWidth()/5,(int)d.getHeight()/46,playerHealth,16);
			try
			{
			runner.sleep(50);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
	}
}