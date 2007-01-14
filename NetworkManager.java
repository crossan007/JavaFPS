import java.net.*;
import java.io.*;
import java.util.*;
public class NetworkManager implements Runnable, EventListener
{
	private Thread runner;
	static ArrayList connectedClients = new ArrayList();
	EventListener listener;
	ObjectInputStream tempOis;
	ObjectOutputStream tempOos;
	
	public NetworkManager(EventListener e)
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
	public void actionPerformed(Event e)
	{
		listener.actionPerformed(e);
	}
	public void run()
	{
		try
		{
			ServerSocket connectionListener = new ServerSocket(GameSettings.getListeningPort());
			Socket tempSocket;
			while(true)
			{
					tempSocket = connectionListener.accept();
					
					tempOos = new ObjectOutputStream(tempSocket.getOutputStream());
					tempOis = new ObjectInputStream(tempSocket.getInputStream());
					
					tempOos.writeObject(new String(GameSettings.getPlayerName()));
					tempOos.writeObject(new String(GameSettings.getAvatarFileName()));
					tempOos.writeObject(new String(MapManager.getMap()));
					
					
					PlayerManager.addPlayer((String)tempOis.readObject(),tempSocket.getRemoteSocketAddress().toString(),(String)tempOis.readObject());
					
					connectedClients.add(new SocketManager(this,tempSocket,tempOis,tempOos));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public void connect()
	{
		try
		{
			Socket newConnection = new Socket(GameSettings.getServerIP(),GameSettings.getRemotePort());
			System.out.println("a");
			tempOis=new ObjectInputStream(newConnection.getInputStream());
			tempOos=new ObjectOutputStream(newConnection.getOutputStream());
			System.out.println("a");
			PlayerManager.addPlayer((String)tempOis.readObject(),newConnection.getRemoteSocketAddress().toString(),(String)tempOis.readObject());
			System.out.println("a");
			MapManager.setMap((String)tempOis.readObject());
			System.out.println("b");
			tempOos.writeObject(new String(GameSettings.getPlayerName()));
			System.out.println("c");
			tempOos.writeObject(new String(GameSettings.getAvatarFileName()));
			System.out.println("d");
			
			connectedClients.add(new SocketManager(this,newConnection,tempOis,tempOos));
			System.out.println("e");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void sendEvent(Event e)
	{
		for(int x=0;x<connectedClients.size();x++)
		{
			((SocketManager)connectedClients.get(x)).sendEvent(e);
		}
		
	}

}
