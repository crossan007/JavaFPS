import java.awt.*;
import java.net.InetAddress;

public class GameSettings 
{
	public final int GAME_DEATHMATCH=0;
	public final int GAME_CTF=1;
	
	private static String playerName;
	private static Dimension screenSize;
	private static int gameType;
	private static double mouseSensetivity;
	private static int killLimit;
	private static boolean isServer;
	private static String serverIP;
	private static int listeningPort=3997;
	private static int remotePort=3997;
	private static String avatarFileName;
	
	public static void setIsServer(boolean a)
	{
		isServer=a;
	}
	public static void setAvatarFileName(String fName)
	{
		avatarFileName=fName;
	}
	public static void setServerIP(String ip)
	{
		serverIP=ip;
	}
	public static void setListeningPort(int p)
	{
		listeningPort=p;
	}
	public static void setRemotePort(int p)
	{
		remotePort=p;
	}
	public static void setPlayerName(String pn)
	{
		playerName=pn;
		
	}
	public static void setScreenSize(Dimension d)
	{
		screenSize=d;
	}
	public static void setGameType(int t)
	{
		gameType=t;
	}
	public static void setMouseSensetivity(double d)
	{
		mouseSensetivity=d;
	}
	public static void setKillLimit(int kl)
	{
		killLimit=kl;
	}
	
	
	
	public static String getAvatarFileName()
	{
		return avatarFileName;
	}
	public static int getRemotePort()
	{
		return remotePort;
	}
	public static int getListeningPort()
	{
		return listeningPort;
	}
	public static boolean getIsServer()
	{
		return isServer;
	}
	public static String getServerIP()
	{
		return serverIP;
	}
	public static String getPlayerName()
	{
		return playerName;
	}
	public static Dimension getScreenSize()
	{
		return screenSize;
	}
	public static int getGameType()
	{
		return gameType;
	}
	public static double getMouseSensetivity()
	{
		return mouseSensetivity;
	}
	public static int getKillLimit()
	{
		return killLimit;
	}
}
