import java.io.*;
public class ObjectInfo implements Serializable
{
	private String fName;
	private double xPos,yPos,zPos;
	private double xRot,yRot,zRot;
	public ObjectInfo(String filename,double xP,double yP,double zP,double xR,double yR,double zR)
	{
		fName=filename;
		xPos=xP;
		yPos=yP;
		zPos=zP;
		xRot=xR;
		yRot=yR;
		zRot=zR;
		
	}
	public String getFileName()
	{
		return fName;
	}
	public double getXPosition()
	{
		return xPos;
	}
	public double getYPosition()
	{
		return yPos;
	}
	public double getZPosition()
	{
		return xPos;
	}
	public double getXRotation()
	{
		return xPos;
	}
	public double getYRotation()
	{
		return yPos;
	}
	public double getZRotation()
	{
		return xPos;
	}
	public String toString()
	{
		return fName;
	}
}
