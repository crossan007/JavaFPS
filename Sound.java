import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.net.URL;
/** this class allows the client code to implement a class that accepts a Network object
 * and allows the user to implement a basic VIOP system
 * @author Charles Crossan
 *
 */
public class Sound implements Runnable
{
	Thread runner;
	Network networkClass;
	static boolean isBroadcasting = false;
	byte[] tempBuffer = new byte[8000];
	
	AudioFormat audioFormat = new AudioFormat(8000,8,1,true,false);
	TargetDataLine targetDataLine=null;
	AudioInputStream 
	audioIn=null;
	SourceDataLine sourceDataLine = null;
	AudioClip frog;
	
	
	// this starts the thread, and initializes the sound system
	public void start()
	{
		if(runner==null)
		{
			runner= new Thread(this);
			try
			{
				DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class,audioFormat);
				targetDataLine=(TargetDataLine)AudioSystem.getLine(dataLineInfo);
				targetDataLine.open(audioFormat);
				targetDataLine.start();	
				
				DataLine.Info dataLineInfoTwo = new DataLine.Info(SourceDataLine.class,audioFormat);
				sourceDataLine=(SourceDataLine)AudioSystem.getLine(dataLineInfoTwo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				
			}catch(Exception e)
			{
				Debug.print("Error on sound start(), "+e);
			}
			runner.start();
		}
	}
	//this takes an array of bytes, and converts it to a string, for use in 
	//transmitting through the Event class.
	private String byteToString(byte[] bytes) 
	{
		try
		{
			return new String(bytes);
		}
		catch(Exception e)
		{
			Debug.print("Couldnt encode bytes "+e);
			return "";
		}
	}
	//this does the opposite of the byteToString class
	private byte[] stringToByte(String a)
	{
		return a.getBytes();
	}
	//if the client code receives an event that is marked as VIOP, then this 
	//method will play the incoming sound
	public void actionPreformed(Event e)
	{
		tempBuffer = stringToByte((String)e.getData());
		
		sourceDataLine.write(tempBuffer,0,tempBuffer.length);
	}
	//this reads in the sound from the microphone, and sends it over the network
	// if PTT is currently on
	public void run()
	{
		while(true)
		{
			if(isBroadcasting)
			{
				targetDataLine.read(tempBuffer,0,tempBuffer.length);	//read one buffer full from mic.
				networkClass.sendEvent(new Event("Sound",GameSettings.getPlayerName(),byteToString(tempBuffer)));  //send that buffer over the network as an event
			}
			try
			{
				runner.sleep(200);		//sleep a little
			
			}
			catch(Exception e)
			{
				Debug.print("Couldnt Sleep");
			}
		}
	}
	//associates the sound class with a network class, so it can send events.
	public void addNetworkCommunications(Network myNet)
	{
		networkClass = myNet;
	}
	//this sets the send sound variable to true, so when the while loop in
	//run is executed, the conditional is ture, and sound is sent
	public static void sendSound()
	{
		isBroadcasting = true;
		
	}
	//self explanitory
	public static void stopSound()
	{
		isBroadcasting = false;
	}
}
