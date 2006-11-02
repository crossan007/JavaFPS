import java.net.*;
import java.util.ArrayList;

/** this class takes care of handling all of the clients, deciding what to do with new clients
 * and alerting its client classes if something happens
 * It is the backbone of the network communications for the game
 * @author Charles Crossan
 *
 */

public class Network implements Runnable,SocketListener,EventListener
{
	Thread runner;
	
	EventListener clientCodeActionListener;
	SecondListener mySec;
	ArrayList allClients = new ArrayList();
	Client tempClient;
	boolean isConnected=false ;
	
	public Network()
	{
	}
	
	
	public void start()
	{
		if(runner==null)
		{
			//Debug.turnOn();
			Debug.print("Creating Connection Listener");
			mySec = new SecondListener();		//create a new second listener so we can accept connections
			mySec.addSocketListener(this);		//add this the the second listener so we get alerts when people connect
			mySec.start();				//start the listener
			runner = new Thread(this);		//start this...
			runner.start();
		}
	}	
	public void run()		//we dont really do anything on a continuing basis here....
							//its all event inspired
	{
		
	}
	public void addActionListener(EventListener a)
	{
		clientCodeActionListener = a;
	}
	//if we get a new connection, this is where we handle it
	public void newConnection(Socket newSock)
	{
		Debug.print("New Connection "+newSock.getRemoteSocketAddress());
		tempClient= new Client(newSock);		//make a new client
		tempClient.addActionListener(this);			//add this to the clients action sender
		tempClient.start();			//start the client
		allClients.add(tempClient);		//add this to the array of lcients
		isConnected = true;			//we are connected
			}
	
	//oh boy, something happened :-D
	public void actionPerformed(Event e)
	{
		
		if(e.getType().equals("Network"))		//was it related to the network?
		{
			int fromIndex=findPlayer(e.getSender());
			if(e.getData().equals("Get Player List"))		//someone probably just joined, and wants to know who is all playing
			{
				Debug.print(e.getSender()+" Requested list of players");
				for(int x=0;x<allClients.size();x++)		//go thru all the players
				{
					if(x!=fromIndex)
					{
					((Client)allClients.get(fromIndex)).send(new Event("Network-SOn",((Client)allClients.get(x)).getName(),((Client)allClients.get(x)).getIPAddress().toString()));		//tell the bew client that this player has connected.
				
					}
				}
			}
		}
		if(e.getType().equals("Network-SOn"))		//yay, someone signed on :-D
		{
			tempClient= new Client((String)e.getData(),5000);	//connect to them
			tempClient.addActionListener(this);			//add this class to the clients action sender
			tempClient.start();				//start the client
			allClients.add(tempClient);		//add this client to the list of connected ones
			Debug.print(e.toString());
		}
		if(e.getType().equals("Network-SOff"))		//awww man, somebody disconnected
		{
			int index = findPlayer(e.getSender());		//find their index
			allClients.remove(index);				//remove them from the client list
		}
		clientCodeActionListener.actionPerformed(e);		//tell the client code whatever happens here, regardless of weather or not it was processed here
	}
	public void sendEvent(Event e)		//send an event over the network to all players
	{
		if(isConnected)		//make sure we are connected
		{
			//Debug.print("Event recieved from client code: "+e.toString());
			for(int x=0;x<allClients.size();x++)		
			{
				((Client)allClients.get(x)).send(e); // send the even to every client
			}
		}
	}
	public int findPlayer(String name)		//return the index number of the player with name Name
	{
		for(int x=0;x<allClients.size();x++)
		{
			if(((Client)allClients.get(x)).getName().equals(name))
			{
				return x;
			}
		}
		return -1;
	}
	public void connect(String ipAddress)  //connect to an Ip address
	{
		Debug.print("Connecting to: "+ipAddress);
		try
		{
			System.out.println("1");
			tempClient = new Client(ipAddress,5000);  //make the client
			System.out.println("2");
			tempClient.addActionListener(this);		//add this class to the clients action sender
			System.out.println("3");
			tempClient.start();					//start the client
			System.out.println("4");
			tempClient.send(new Event("Network",GameSettings.getPlayerName(),"Get Player List"));		//get the list, since this is the first computer we are connecting to
			System.out.println("5");
			allClients.add(tempClient);			//add this ot the client list
			System.out.println("6");
			isConnected=true;			//we are now connected
			System.out.println("7");
		}
		catch(Exception e)
		
		{
			Debug.print(" Error in connect:Network.java: "+e);
		}
		
	}
	

}
