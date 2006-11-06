
public class KeyBindings 
{
	private static char up='q';
	private static char down='e';
	private static char left='a';
	private static char right='d';
	private static char foreward='w';
	private static char backward='s';
	
	public static void setKeyBindings(char u, char d, char l, char r,char f,char b)
	{
		up=u;
		down=d;
		left=l;
		right=r;
		foreward = f;
		backward=b;
		
	}
	public static char getUpKey()
	{
		return up;
	}
	public static char getDownKey()
	{
		return down;
	}
	public static char getLeftKey()
	
	{
		return left;
	}
	public static char getRightKey()
	{
		return right;
	}
	public static char getForewardKey()
	{
		return foreward;
	}
	public static char getBackwardKey()
	{
		return backward;
	}

}
