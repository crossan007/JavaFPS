import java.net.*;
import java.util.ArrayList;
/** this class takes care of accepting new connections from people
 * it does not care about the protocol for the game, it simply
 * listens for connections, and calls the client class with an event containing 
 * the socket of the accepted connection.
 * @author Charles Crossan
 *
 */
public class SecondListener implements Runnable
{
	Thread runner = null;
	ServerSocket myServ;
	SocketListener clientCodeActionListener;
	public void start()
	{
		if(runner == null)
		{
			runner=new Thread(this);
			runner.start();
		}
	}
	public void run()
	{
		
		
			try
			{
				myServ = new ServerSocket(5000);		//listen for connections on port 5000
				while(true)		//do this for a long long time :>
				{
					clientCodeActionListener.newConnection(myServ.accept());		//just pass off the socket of the new connnection to the client class when one comes in
				}
			}
			catch(Exception e)
			{
				Debug.print(" Error in run in SecondListener:"+ e);
			}
		
		
	}
	public void addSocketListener(SocketListener e)		//associate with a SocketListnener
	{
		clientCodeActionListener=e;
		
	}
}