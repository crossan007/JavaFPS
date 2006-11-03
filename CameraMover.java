import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

public class CameraMover implements Runnable, KeyListener, MouseMotionListener {
	private int VK_FOREWARD = 'w';
	private int VK_BACKWARD = 's';
	private int VK_LEFT = 'a';
	private int VK_RIGHT = 'd';

	private final double MOVE_AMT = .1;
	private final Vector3d VFWD = new Vector3d(0, 0, -MOVE_AMT);
	int x=0;
	int y=0;
	private final Vector3d VBACK = new Vector3d(0, 0, MOVE_AMT);
	private final Vector3d VLEFT = new Vector3d(-MOVE_AMT, 0, 0);
	private final Vector3d VRIGHT = new Vector3d(MOVE_AMT, 0, 0);
	private final Vector3d VDOWN = new Vector3d(0, -MOVE_AMT, 0);
	private final Vector3d VUP = new Vector3d(0, MOVE_AMT, 0);
	private final Vector3d NOYMOVE = new Vector3d(1,0,1);
	
	
	private boolean isForeward,isBack,isLeft,isRight;
	private boolean isFree = false;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();

	private TransformGroup cameraTransform;
	private TransformGroup gunTransform;
	private Transform3D temp1 = new Transform3D();
	private Transform3D temp2 = new Transform3D();
	private Thread runner = null;
	private Robot robot;

	public CameraMover(TransformGroup gun, TransformGroup camera) 
	{
		cameraTransform=camera;
		gunTransform=gun;
		setGun();
		
		try {
			robot = new Robot();
		} catch (Exception e) {
			System.out.println(e);
		}
		start();
	}
	private void setGun()
	{
		Transform3D tttt = new Transform3D();
		Transform3D ttt = new Transform3D();
		cameraTransform.getTransform(tttt);
		ttt.setTranslation(new Vector3d(.2,-.2,-.2));
		tttt.mul(ttt);
		gunTransform.setTransform(tttt);
	}

	public void setIsFree(boolean ifF)
	{
		isFree=ifF;
	}
	public void start() {
		if (runner == null) {
			runner = new Thread(this);
			runner.start();
		}
		
	}
	public TransformGroup getTransformGroup()
	{
		return cameraTransform;
	}

	public void run() {
		temp2.setIdentity();
		
		
		while(true)
		{
			cameraTransform.getTransform(temp1);
			
			
			if(isForeward)
			{
				temp2.setTranslation(VFWD);
				temp1.mul(temp2);
				
			}
				
			if(isBack)
			{
				
					temp2.setTranslation(VBACK);
					temp1.mul(temp2);
				
			}
			if(isRight)
			{
				temp2.setTranslation(VRIGHT);
				temp1.mul(temp2);
				
				
			}
			if(isLeft)
			{
				temp2.setTranslation(VLEFT);
				temp1.mul(temp2);
				
			}
			if(isFree)
			{
				/*Vector3d my = new Vector3d();
				temp1.get(my);
				temp1.setTranslation(my);*/
				
			}
			cameraTransform.setTransform(temp1);
			setGun();
			NetworkManager.sendEvent(new Event("Position",GameSettings.getPlayerName(),new TransformNetwork(temp1)));
			try
			{
				runner.sleep(50);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

		
		Transform3D t3 = new Transform3D();
		Transform3D t4 = new Transform3D();
		Transform3D t2 = new Transform3D();
		Transform3D t1 = new Transform3D();

		cameraTransform.getTransform(t2);
		
		
		
		t3.rotY(-1*Math.toRadians(GameSettings.getMouseSensetivity()*((double) e.getX() - d.getWidth()/2)));
		t4.rotX(-1*Math.toRadians(GameSettings.getMouseSensetivity()*((double) e.getY() - d.getHeight()/2)));
		t2.mul(t3);
		t2.mul(t4);
		
		

		cameraTransform.setTransform(t2);
		setGun();
		NetworkManager.sendEvent(new Event("Position",GameSettings.getPlayerName(),new TransformNetwork(temp1)));
		robot.mouseMove(((int)d.getWidth())/2,((int)d.getHeight())/2);
		

	}

	public void keyPressed(KeyEvent e) {
		//System.out.println(e);
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (e.getKeyChar() == VK_FOREWARD) {
			isForeward = true;
		} else if (e.getKeyChar() == VK_BACKWARD) {
			isBack = true;
		} else if (e.getKeyChar() == VK_LEFT) {
			isLeft = true;
		} else if (e.getKeyChar() == VK_RIGHT) {
			isRight = true;
		} else if (e.getKeyChar() =='v')	{
			Sound.sendSound();
		}
		
		

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (e.getKeyChar() == VK_FOREWARD) {
			isForeward = false;
		} else if (e.getKeyChar() == VK_BACKWARD) {
			isBack = false;
		} else if (e.getKeyChar() == VK_LEFT) {
			isLeft = false;
		} else if (e.getKeyChar() == VK_RIGHT) {
			isRight = false;
		}else if (e.getKeyChar() =='v')	{
			Sound.stopSound();
		}
	}
}
