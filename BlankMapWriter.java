import java.io.*;
public class BlankMapWriter 
	{


	public static void main(String[] args)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("Maps\\map4.vom");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new String("stars4.gif"));
			for(int x=0;x<14;x++)
			{
			ObjectInfo nif = new ObjectInfo("robot.obj",(int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10),(Math.PI/3),0,0);
			oos.writeObject(nif);
			}
		
			oos.close();
			fos.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
