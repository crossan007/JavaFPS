import java.awt.*;
import java.io.*;

import javax.swing.*;

public class MapMaker extends JFrame
{
	File models = new File("Models");
	JComboBox modelSelector = new JComboBox();
	JTextArea xPosition = new JTextArea();
	JTextArea yPosition = new JTextArea();
	JTextArea zPosition = new JTextArea();
	
	JTextArea xRotation = new JTextArea();
	JTextArea yRotation = new JTextArea();
	JTextArea zRotation = new JTextArea();
	
	public MapMaker()
	{
		super("VO: Map Maker");
		setSize(800,600);
		setVisible(true);
		setLayout(null);
		
		modelSelector.setSize(200,25);
		modelSelector.setVisible(true);
		modelSelector.setLocation(10,30);
		
		xPosition.setSize(50,25);
		xPosition.setLocation(35,10);
		xPosition.setVisible(true);
		
		
		String[] children = models.list();
		for(int x=0;x<children.length;x++)
		modelSelector.addItem(children[x]);
		
		
		add(modelSelector);
		add(xPosition);
		
	
	}
	

}
