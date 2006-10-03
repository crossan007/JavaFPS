/*
 * Created on Jan 11, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * simple debug class
 */
public class Debug 
{
	private static boolean isOn;
	public static void print(String a)
	{
		if (isOn)
		{
			System.out.println("DEBUG: "+a);
			
		}
	}
	public static void turnOn()
	{
		isOn=true;
	}
	public static void turnOff()
	{
		isOn=false;
	}
}
