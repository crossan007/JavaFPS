import java.net.*;
import java.io.*;
public class SocketManager implements Runnable
{
	Thread runner;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket client;
	EventListener listener;
	public SocketManager(EventListener e,Socket a, ObjectInputStream is, ObjectOutputStream os)
	{
		oos=os;
		ois=is;
		client=a;
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
		try
		{
			while(client.isConnected())
			{
				
				listener.actionPerformed((Event)ois.readObject());
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void sendEvent(Event e)
	{
		try
		{
		
			oos.writeObject(e);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
}
