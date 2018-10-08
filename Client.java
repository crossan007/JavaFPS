/** this class takes care of the protocol between computers
 * it also psases off any events to the client code.
 * author Charles Crossan
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class Client implements Runnable
{
	
	Thread runner;
	Socket clientConnection = null;
	ArrayList eventArray = new ArrayList();
	ObjectOutputStream objSckOut;
	ObjectInputStream objSckIn;
	EventListener clientCodeEventListener;
	String playerName;
	
	public Client(Socket conn,String pName)  // this constructor takes a socket,
	//and establishes a object stream and gets the name and stuff like that
	{
		clientConnection = conn;	
		try
		{
			Debug.print("Initializing");
			objSckOut = new ObjectOutputStream(clientConnection.getOutputStream());
			Debug.print("Object output Stream Created");
			objSckIn = new ObjectInputStream(clientConnection.getInputStream());
			Debug.print("Object input Stream Created");
			playerName=(String)objSckIn.readObject();
			Debug.print("Name read");
			objSckOut.writeObject(pName);
			Debug.print("Name Written");
			Debug.print("new Player: "+playerName);
			
			
		}
		catch(Exception e)
		{
			Debug.print("Error in initializeConnection:Client.java" + e);
		}
		
	}
	public Client(String ipAddress,int port,String pName)//this constructor allows you to connect to another client with an ip address and a name
	//mainly used to make the initial connection
	{
		try
		{
			clientConnection = new Socket(ipAddress,port);
			Debug.print("Socket Created");
			objSckIn = new ObjectInputStream(clientConnection.getInputStream());
			Debug.print("Objecet Input stream created");
			objSckOut = new ObjectOutputStream(clientConnection.getOutputStream());
			Debug.print("Object Output stream created");
			objSckOut.writeObject(pName);
			Debug.print("Name written");
			playerName = (String)objSckIn.readObject();
			Debug.print("new Player "+playerName);
		}
		catch(Exception e)
		{
			Debug.print("Error in Client.java, constructor" + e);
		}
	}
	public String getName()
	{
		return playerName;
	}
	public void addActionListener(EventListener a)		//associates the client code with this class
	{
		clientCodeEventListener = a;
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
			while(clientConnection.isConnected())		//do this for a long time
			{
				clientCodeEventListener.actionPreformed((Event)objSckIn.readObject());  //if an event is read, its is passed off to the client code
			}
						
		}
		catch(Exception e)		//oops error, kill the client
		{
			Debug.print("Client "+getIPAddress()+" Gave Error, Killing."+e);
			clientCodeEventListener.actionPreformed(new Event("Network-SOff",playerName," "));

			kill();
		}
		
		
	}
	
	public void send(Event e)		//send an event to the client, pretty simple
	{
		try
		{
			objSckOut.writeObject(e);  
		}
		catch(Exception ex)
		{
			Debug.print("Error in send of Client.java: "+ex);
		}
	}
	public String getIPAddress()		//gets the IP address of this client
	{
		return clientConnection.getInetAddress().toString().substring(1);
	}
	public void kill()  //kill the connection.
	{
		try
		{
			clientConnection.close();
		}
		catch(Exception e)
		{
			Debug.print("Error in kill of Client.java: "+e);
		}
	}
	
}
