import java.io.*;
import java.net.*;
public class NetworkClient implements Runnable
{
	private Thread runner;
	EventListener listener;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public NetworkClient(EventListener e)
	{
		listener=e;
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
		while(true)
		{
			//listener.actionPreformed();
		}
	}
}
