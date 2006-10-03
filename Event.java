/** this defines an event
 * not too much description necessary. 
 * it has a sender, data, and a type
 * it has get methods for each
 * author Charles Crossan
 */
import java.io.*;
public class Event implements Serializable
{
	private Object data;
	private String fromPlayer;
	private String type;
	public Event(String tType, String fPlayer, Object tData)
	{
		data=tData;
		fromPlayer=fPlayer;
		type=tType;
	}
	public String getType()
	{
		return type;
	}
	public String getSender()
	{
		return fromPlayer;
	}
	public Object getData()
	{
		return data;
	}
	public String toString()
	{
		return "Event: "+fromPlayer+": "+type+" "+data;
	}
}
