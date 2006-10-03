import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.net.*;


import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;


import javax.swing.*;
import javax.swing.event.*;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.image.TextureLoader;

public class Startup extends JFrame implements ActionListener, ChangeListener

{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	int HEIGHT = (int)d.getHeight();
	int WIDTH = (int)d.getWidth();
	
	JButton JoinGame = new JButton("J o i n  G a m e");
	JButton Join = new JButton("J o i n");
	JButton lookSens = new JButton("M o u s e  S e n s e t i v i t y");
	JButton startGame = new JButton("N e w  G a m e");
	JButton options = new JButton("O p t i o n s");
	JButton quit = new JButton("Q u i t");
	JButton CTFMode = new JButton("C a p t u r e  T h e  F l a g");
	JButton DeathMatchMode = new JButton("D e a t h  M a t c h");
	JButton MapMaker = new JButton("C r e a t e  M a p");
	JButton backButton = new JButton("M a i n  M e n u");
	JLabel nameHere = new JLabel("Your Name Here: ");
	JTextField enterName = new JTextField("name");
	JTextField serverIP = new JTextField();
	JLabel enterServer = new JLabel("Enter Server IP: ");
	JComboBox mapSelect = new JComboBox();
	JSlider mouseSensetivity = new JSlider();
	
	/**
	 * @param args
	 */
	
	public void stateChanged(ChangeEvent e) 
	{
		
		if(!mouseSensetivity.getValueIsAdjusting())
		{
			GameSettings.setMouseSensetivity(((double)mouseSensetivity.getValue())/100);
		}
	}
	public Startup()
	{
		super("Startup");
		
	
		setLayout(null);
		setSize(d);
		setUndecorated(true);
		GameSettings.setMouseSensetivity(1.0);
		
		
		

		JPanel myPanel= new JPanel();
		myPanel.setSize(d); 
		myPanel.setVisible(true);	
		
		
		JLabel gameName = new JLabel("V   e   n   d   e   t   t   a     O   n   l   i   n   e");
		JLabel bigV = new JLabel("V");
		
		JLabel backgroundLabel = new JLabel("");

		 
		 add(startGame);
		 add(JoinGame);
		 add(Join);
		 add(options);
		 add(quit);
		 add(CTFMode);
		 add(DeathMatchMode);
		 add(MapMaker);
		 add(nameHere);
		 add(enterName);
		 add(serverIP);
		 add(enterServer);
		 add(backButton);
		 add(mapSelect);
		 add(mouseSensetivity);
		 add(lookSens);
		 getContentPane().add(gameName);
		 getContentPane().add(bigV);
		 getContentPane().add(startGame);
		 
		 add(backgroundLabel);
		 
		 
		 
		 CTFMode.setVisible(false);
		 DeathMatchMode.setVisible(false);
		 nameHere.setVisible(false);
         enterName.setVisible(false);
         serverIP.setVisible(false);
         enterServer.setVisible(false);
         backButton.setVisible(false);
         Join.setVisible(false);
         mapSelect.setVisible(false);
         mouseSensetivity.setVisible(false);
         lookSens.setVisible(false);
         
         
        
      
       

		 backgroundLabel.setBackground(new Color(00,00,00));
		 backgroundLabel.setLocation(0,0);
		 backgroundLabel.setSize((int)d.getWidth(),(int)d.getHeight());
		 backgroundLabel.setVisible(true);
		 backgroundLabel.setOpaque(true);
		 
		 	gameName.setFont(new Font("futuraBlack BT",Font.PLAIN,20));  //cool font
			gameName.setSize(1024,200);
			gameName.setForeground(new Color(80,80,80));
			gameName.setBackground(new Color(00,00,00));
			gameName.setLocation((int)(d.getWidth()/2.9),(int)(d.getHeight()/3.8));
			
			bigV.setFont(new Font("MingLiU",Font.PLAIN,300));  //cool font
			bigV.setSize(1024,200);
			bigV.setForeground(new Color(80,80,80));
			bigV.setLocation((int)(d.getWidth()/2.3),(int)(d.getHeight()/3.8));
			
			startGame.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			startGame.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.8)));
			startGame.setSize(new Dimension(200,50));
			startGame.setBackground(new Color(00,00,00));
			startGame.setForeground(new Color (80,80,80));
			startGame.setBorder(BorderFactory.createRaisedBevelBorder());
			startGame.addActionListener(this);
			
					
					DeathMatchMode.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
					DeathMatchMode.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.8)));
					DeathMatchMode.setSize(new Dimension(200,50));
					DeathMatchMode.setBackground(new Color(00,00,00));
					DeathMatchMode.setForeground(new Color (80,80,80));
					DeathMatchMode.setBorder(BorderFactory.createRaisedBevelBorder());
					DeathMatchMode.addActionListener(this);
					
			
			JoinGame.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			JoinGame.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.6)));
			JoinGame.setSize(new Dimension(200,50));
			JoinGame.setBackground(new Color(00,00,00));
			JoinGame.setForeground(new Color (80,80,80));
			JoinGame.setBorder(BorderFactory.createRaisedBevelBorder());
			JoinGame.addActionListener(this);
			
			Join.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			Join.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.8)));
			Join.setSize(new Dimension(200,50));
			Join.setBackground(new Color(00,00,00));
			Join.setForeground(new Color (80,80,80));
			Join.setBorder(BorderFactory.createRaisedBevelBorder());
			Join.addActionListener(this);
			
			
					CTFMode.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
					CTFMode.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.6)));
					CTFMode.setSize(new Dimension(200,50));
					CTFMode.setBackground(new Color(00,00,00));
					CTFMode.setForeground(new Color (80,80,80));
					CTFMode.setBorder(BorderFactory.createRaisedBevelBorder());
					
					
			options.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			options.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.4)));
			options.setSize(new Dimension(200,50));
			options.setBackground(new Color(00,00,00));
			options.setForeground(new Color (80,80,80));
			options.setBorder(BorderFactory.createRaisedBevelBorder());
			options.addActionListener(this);
			
			
				quit.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
				quit.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.15)));
				quit.setSize(new Dimension(200,50));
				quit.setBackground(new Color(00,00,00));
				quit.setForeground(new Color (80,80,80));
				quit.setBorder(BorderFactory.createEtchedBorder());
				quit.addActionListener(this);
			
			
			
			
			MapMaker.setFont(new Font("futuraBlack BT",Font.PLAIN,15)); 
            MapMaker.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.4))); 
            MapMaker.setSize(new Dimension(200,50)); 
            MapMaker.setBackground(new Color(00,00,00)); 
            MapMaker.setForeground(new Color (80,80,80)); 
            MapMaker.setBorder(BorderFactory.createRaisedBevelBorder()); 
            MapMaker.setVisible(false); 
            MapMaker.addActionListener(this);
           
            backButton.setFont(new Font("futuraBlack BT",Font.PLAIN,15)); 
            backButton.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.25))); 
            backButton.setSize(new Dimension(200,50)); 
            backButton.setBackground(new Color(00,00,00)); 
            backButton.setForeground(new Color (80,80,80)); 
            backButton.setBorder(BorderFactory.createRaisedBevelBorder()); 
            backButton.setVisible(false); 
            backButton.addActionListener(this);
            
            lookSens.setFont(new Font("futuraBlack BT",Font.PLAIN,15)); 
            lookSens.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.6))); 
            lookSens.setSize(new Dimension(200,50)); 
            lookSens.setBackground(new Color(00,00,00)); 
            lookSens.setForeground(new Color (80,80,80)); 
            lookSens.setBorder(BorderFactory.createRaisedBevelBorder()); 
            lookSens.addActionListener(this);
            	
	            nameHere.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            nameHere.setLocation(new Point((int)(d.getWidth()/1.4),(int)(d.getHeight()/1.1)));
	            nameHere.setSize(new Dimension(200,50));
	            nameHere.setBackground(new Color(11,11,11));
	            nameHere.setForeground(new Color (80,80,80));
	            
	            mapSelect.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            mapSelect.setLocation(new Point((int)(d.getWidth()/1.2),(int)(d.getHeight()*.85)));
	            mapSelect.setSize(new Dimension(150,40));
	            mapSelect.setBackground(new Color(11,11,11));
	            mapSelect.setForeground(new Color (80,80,80));
	            mapSelect.addActionListener(this);
	            for(int x=0;x<MapManager.getAllMaps().length;x++)
	            {
	            	mapSelect.addItem(MapManager.getAllMaps()[x]);
	            }
            
            enterName.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
            enterName.setLocation(new Point((int)(d.getWidth()/1.2),(int)(d.getHeight()/1.1)));
            enterName.setSize(new Dimension(150,40));
            enterName.setBackground(new Color(11,11,11));
            enterName.setForeground(new Color (80,80,80));
           
	            enterServer.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            enterServer.setLocation(new Point((int)(d.getWidth()/1.4),(int)(d.getHeight()/1.2)));
	            enterServer.setSize(new Dimension(200,50));
	            enterServer.setBackground(new Color(11,11,11));
	            enterServer.setForeground(new Color (80,80,80));
	            
            serverIP.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
            serverIP.setLocation(new Point((int)(d.getWidth()/1.2),(int)(d.getHeight()/1.2)));
            serverIP.setSize(new Dimension(150,40));
            serverIP.setBackground(new Color(11,11,11));
            serverIP.setForeground(new Color (80,80,80));
            
          
            mouseSensetivity.setSize(100,20);
            mouseSensetivity.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.6))); 
            mouseSensetivity.setMinimum(1);
            mouseSensetivity.setMaximum(200);
            mouseSensetivity.setValue(100);
            
            mouseSensetivity.addChangeListener(this);
			
			
			

			getContentPane().setLayout(null);			//make it so I can position all the items i need to
			

			
			
			
		 setVisible(true);
		 
		 add(myPanel);
		 getContentPane().setBackground(new Color(00,00,00));		//make the background a nice pretty blue
	}
	
	
	public void actionPerformed(ActionEvent e) 
    { 
         
         if(e.getActionCommand()=="N e w  G a m e") 
         { 
               
               startGame.setVisible(false);
               JoinGame.setVisible(false);
               options.setVisible(false);
               mapSelect.setVisible(true);
               DeathMatchMode.setVisible(true);
               CTFMode.setVisible(true);
               nameHere.setVisible(true);
               enterName.setVisible(true);
               backButton.setVisible(true);
               Join.setVisible(false);
               mouseSensetivity.setVisible(false);
               lookSens.setVisible(false);
               GameSettings.setIsServer(true);
              
               
               
               
               
         } 
         if(e.getActionCommand()=="M a i n  M e n u") 
         { 
               
               startGame.setVisible(true);
               JoinGame.setVisible(true);
               mapSelect.setVisible(false);
               options.setVisible(true);
               DeathMatchMode.setVisible(false);
               CTFMode.setVisible(false);
               nameHere.setVisible(false);
               enterName.setVisible(false);
               backButton.setVisible(false);
               serverIP.setVisible(false);
               enterServer.setVisible(false);
               MapMaker.setVisible(false);
               Join.setVisible(false);
               mouseSensetivity.setVisible(false);
               lookSens.setVisible(false);
              
               
               
               
               
         } 
  
              
              if(e.getActionCommand()=="J o i n  G a m e") 
              { 
                    
                    startGame.setVisible(false);
                    JoinGame.setVisible(false);
                    options.setVisible(false);
                    DeathMatchMode.setVisible(false);
                    CTFMode.setVisible(false);
                    nameHere.setVisible(true);
                    enterName.setVisible(true);
                    serverIP.setVisible(true);
                    enterServer.setVisible(true);
                    backButton.setVisible(true);
                    Join.setVisible(true);
                    mouseSensetivity.setVisible(false);
                    lookSens.setVisible(false);
                    GameSettings.setIsServer(false);
                    
                   
                    
                    
                    
                    
              } 
              if(e.getActionCommand()=="J o i n") 
              { 
                    
            	  this.dispose();
                  GameSettings.setServerIP(serverIP.getText());
                  GameSettings.setPlayerName(enterName.getText());
                  MainGame mg = new MainGame(); 
                    
                   
                    
                    
                    
                    
              } 

         if(e.getActionCommand()=="Q u i t") 
         { 
        	 System.exit(0);
        	 
         }
       
         if(e.getActionCommand()=="D e a t h  M a t c h") 
         { 
        	 this.dispose(); 
             
             GameSettings.setPlayerName(enterName.getText());
             MainGame mg = new MainGame(); 
        	 
         }
         if(e.getActionCommand()=="O p t i o n s")  
         {  
                
               startGame.setVisible(false); 
               JoinGame.setVisible(false); 
               options.setVisible(false); 
               MapMaker.setVisible(true); 
               backButton.setVisible(true);
               Join.setVisible(false);
               mouseSensetivity.setVisible(false);
               lookSens.setVisible(true);
                
         }  
         if(e.getActionCommand()=="M o u s e  S e n s e t i v i t y")  
         {  
                
               startGame.setVisible(false); 
               JoinGame.setVisible(false); 
               options.setVisible(false); 
               MapMaker.setVisible(false); 
               backButton.setVisible(true);
               Join.setVisible(false);
               mouseSensetivity.setVisible(true);
               lookSens.setVisible(false);
                
         }  
         if(e.getActionCommand()=="C r e a t e  M a p")   
         {  
             MapMaker mapMaker = new MapMaker();
        	 backButton.setVisible(true);
        	 Join.setVisible(false);         
               
         }
         if(e.getActionCommand()=="comboBoxChanged")
         {
        	 MapManager.setMap((String)mapSelect.getSelectedItem()); 
         }
         
    }
	public static void main(String[] args) 
	{
		new Startup();

	}

}
