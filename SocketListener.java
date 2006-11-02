import java.net.*;
/** this interface allows me to use my network class as a socket listener
 * and i can call newConnection from my secondListener class, and alert Network that 
 * someone else is connecting.
 * @author Charles Crossan
 *
 */
public interface SocketListener 
{
	public void newConnection(Socket a);
}
