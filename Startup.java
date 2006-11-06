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
	JButton controls = new JButton("C o n t r o l s");
	JButton startGame = new JButton("N e w  G a m e");
	JButton options = new JButton("O p t i o n s");
	JButton quit = new JButton("Q u i t");
	JButton CTFMode = new JButton("C a p t u r e  T h e  F l a g");
	JButton DeathMatchMode = new JButton("D e a t h  M a t c h");
	JButton MapMaker = new JButton("C r e a t e  M a p");
	JButton backButton = new JButton("M a i n  M e n u");
	JLabel nameHere = new JLabel("Your Name Here : ");
	JLabel up = new JLabel("up : ");
	JLabel down = new JLabel("down : ");
	JLabel left = new JLabel("left : ");
	JLabel right = new JLabel("right : ");
	JLabel forward = new JLabel("forward : ");
	JLabel backward = new JLabel("backward : ");
	JLabel selectCharacter = new JLabel("Select Character : ");
	JLabel selectMap = new JLabel("Select Map : ");
	JTextField enterUp = new JTextField("q");
	JTextField enterDown = new JTextField("e");
	JTextField enterLeft = new JTextField("a");
	JTextField enterRight = new JTextField("d");
	JTextField enterForward = new JTextField("w");
	JTextField enterBackward = new JTextField("s");
	JTextField enterName = new JTextField("name");
	JTextField serverIP = new JTextField();
	JLabel enterServer = new JLabel("Enter Server IP : ");
	JComboBox mapSelect = new JComboBox();
	JComboBox characterSelect = new JComboBox();
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
		 add(selectCharacter);
		 add(selectMap);
		 add(enterName);
		 add(serverIP);
		 add(enterServer);
		 add(backButton);
		 add(mapSelect);
		 add(characterSelect);
		 add(mouseSensetivity);
		 add(controls);
		 add(lookSens);
		 add(up);
		 add(down);
		 add(left);
		 add(right);
		 add(forward);
		 add(backward);
		 add(enterUp);
		 add(enterDown);
		 add(enterLeft);
		 add(enterRight);
		 add(enterForward);
		 add(enterBackward);
		 getContentPane().add(gameName);
		 getContentPane().add(bigV);
		 getContentPane().add(startGame);
		 
		 add(backgroundLabel);
		 
		 
		 
		 CTFMode.setVisible(false);
		 DeathMatchMode.setVisible(false);
		 nameHere.setVisible(false);
         enterName.setVisible(false);
         selectCharacter.setVisible(false);
         selectMap.setVisible(false);
         serverIP.setVisible(false);
         enterServer.setVisible(false);
         backButton.setVisible(false);
         Join.setVisible(false);
         mapSelect.setVisible(false);
         mapSelect.setVisible(false);
         characterSelect.setVisible(false);
         mouseSensetivity.setVisible(false);
         lookSens.setVisible(false);
         controls.setVisible(false);
         up.setVisible(false);
         down.setVisible(false);
         left.setVisible(false);
         right.setVisible(false);
         forward.setVisible(false);
         backward.setVisible(false);
         enterUp.setVisible(false);
         enterDown.setVisible(false);
         enterLeft.setVisible(false);
         enterRight.setVisible(false);
         enterForward.setVisible(false);
         enterBackward.setVisible(false);
         
         
        
      
       

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
            
            controls.setFont(new Font("futuraBlack BT",Font.PLAIN,15)); 
            controls.setLocation(new Point((int)(d.getWidth()/2.4),(int)(d.getHeight()/1.8))); 
            controls.setSize(new Dimension(200,50)); 
            controls.setBackground(new Color(00,00,00)); 
            controls.setForeground(new Color (80,80,80)); 
            controls.setBorder(BorderFactory.createRaisedBevelBorder()); 
            controls.addActionListener(this);
            	
	            nameHere.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            nameHere.setLocation(new Point((int)(d.getWidth()/1.4),(int)(d.getHeight()/1.1)));
	            nameHere.setSize(new Dimension(200,50));
	            nameHere.setBackground(new Color(11,11,11));
	            nameHere.setForeground(new Color (80,80,80));
	            
	            selectCharacter.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            selectCharacter.setLocation(new Point((int)(d.getWidth()/1.4),(int)(d.getHeight()*.78)));
	            selectCharacter.setSize(new Dimension(200,50));
	            selectCharacter.setBackground(new Color(11,11,11));
	            selectCharacter.setForeground(new Color (80,80,80));
	            
	            selectMap.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            selectMap.setLocation(new Point((int)(d.getWidth()/1.4),(int)(d.getHeight()*.85)));
	            selectMap.setSize(new Dimension(200,50));
	            selectMap.setBackground(new Color(11,11,11));
	            selectMap.setForeground(new Color (80,80,80));
	            
	            mapSelect.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
	            mapSelect.setLocation(new Point((int)(d.getWidth()/1.2),(int)(d.getHeight()*.85)));
	            mapSelect.setSize(new Dimension(150,40));
	            mapSelect.setBackground(new Color(11,11,11));
	            mapSelect.setForeground(new Color (80,80,80));
	            mapSelect.addActionListener(this);
	            		
		            up.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            up.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.75)));
		            up.setSize(new Dimension(200,50));
		            up.setBackground(new Color(11,11,11));
		            up.setForeground(new Color (80,80,80));
			            
		            	enterUp.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			            enterUp.setLocation(new Point((int)(d.getWidth()/1.95),(int)(d.getHeight()/1.7)));
			            enterUp.setSize(new Dimension(50,20));
			            enterUp.setBackground(new Color(11,11,11));
			            enterUp.setForeground(new Color (80,80,80));

		            down.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            down.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.68)));
		            down.setSize(new Dimension(200,50));
		            down.setBackground(new Color(11,11,11));
		            down.setForeground(new Color (80,80,80));
		            
			            enterDown.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			            enterDown.setLocation(new Point((int)(d.getWidth()/1.95),(int)(d.getHeight()/1.63)));
			            enterDown.setSize(new Dimension(50,20));
			            enterDown.setBackground(new Color(11,11,11));
			            enterDown.setForeground(new Color (80,80,80));
		            
		            left.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            left.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.61)));
		            left.setSize(new Dimension(200,50));
		            left.setBackground(new Color(11,11,11));
		            left.setForeground(new Color (80,80,80));
		            
			            enterLeft.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			            enterLeft.setLocation(new Point((int)(d.getWidth()/1.95),(int)(d.getHeight()/1.56)));
			            enterLeft.setSize(new Dimension(50,20));
			            enterLeft.setBackground(new Color(11,11,11));
			            enterLeft.setForeground(new Color (80,80,80));	
		            
		            right.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            right.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.54)));
		            right.setSize(new Dimension(200,50));
		            right.setBackground(new Color(11,11,11));
		            right.setForeground(new Color (80,80,80));
		            
			            enterRight.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			            enterRight.setLocation(new Point((int)(d.getWidth()/1.95),(int)(d.getHeight()/1.49)));
			            enterRight.setSize(new Dimension(50,20));
			            enterRight.setBackground(new Color(11,11,11));
			            enterRight.setForeground(new Color (80,80,80));
		            
		            forward.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            forward.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.47)));
		            forward.setSize(new Dimension(200,50));
		            forward.setBackground(new Color(11,11,11));
		            forward.setForeground(new Color (80,80,80));
		            
			            enterForward.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			            enterForward.setLocation(new Point((int)(d.getWidth()/1.95),(int)(d.getHeight()/1.43)));
			            enterForward.setSize(new Dimension(50,20));
			            enterForward.setBackground(new Color(11,11,11));
			            enterForward.setForeground(new Color (80,80,80));
		            
		            backward.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            backward.setLocation(new Point((int)(d.getWidth()/2.2),(int)(d.getHeight()/1.4)));
		            backward.setSize(new Dimension(200,50));
		            backward.setBackground(new Color(11,11,11));
		            backward.setForeground(new Color (80,80,80));
		            
			            enterBackward.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
			            enterBackward.setLocation(new Point((int)(d.getWidth()/1.95),(int)(d.getHeight()/1.36)));
			            enterBackward.setSize(new Dimension(50,20));
			            enterBackward.setBackground(new Color(11,11,11));
			            enterBackward.setForeground(new Color (80,80,80));
	            for(int x=0;x<MapManager.getAllMaps().length;x++)
	            {
	            	mapSelect.addItem(MapManager.getAllMaps()[x]);
	            }
	            
		            characterSelect.setFont(new Font("futuraBlack BT",Font.PLAIN,15));
		            characterSelect.setLocation(new Point((int)(d.getWidth()/1.2),(int)(d.getHeight()*.78)));
		            characterSelect.setSize(new Dimension(150,40));
		            characterSelect.setBackground(new Color(11,11,11));
		            characterSelect.setForeground(new Color (80,80,80));
		            characterSelect.addActionListener(this);
		            for(int x=0;x<MapManager.getAllCharacters().length;x++)
		            {
		            	characterSelect.addItem(MapManager.getAllCharacters()[x]);
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
               characterSelect.setVisible(true);
               DeathMatchMode.setVisible(true);
               CTFMode.setVisible(true);
               nameHere.setVisible(true);
               selectCharacter.setVisible(true);
               selectMap.setVisible(true);
               nameHere.setVisible(true);
               enterName.setVisible(true);
               backButton.setVisible(true);
               Join.setVisible(false);
               mouseSensetivity.setVisible(false);
               controls.setVisible(false);
               lookSens.setVisible(false);
               up.setVisible(false);
               down.setVisible(false);
               left.setVisible(false);
               right.setVisible(false);
               forward.setVisible(false);
               backward.setVisible(false);
               enterUp.setVisible(false);
               enterDown.setVisible(false);
               enterLeft.setVisible(false);
               enterRight.setVisible(false);
               enterForward.setVisible(false);
               enterBackward.setVisible(false);
               GameSettings.setIsServer(true);
              
               
               
               
               
         } 
         if(e.getActionCommand()=="M a i n  M e n u") 
         { 
               
               startGame.setVisible(true);
               JoinGame.setVisible(true);
               mapSelect.setVisible(false);
               characterSelect.setVisible(false);
               options.setVisible(true);
               DeathMatchMode.setVisible(false);
               CTFMode.setVisible(false);
               nameHere.setVisible(false);
               selectCharacter.setVisible(false);
               selectMap.setVisible(false);
               enterName.setVisible(false);
               backButton.setVisible(false);
               serverIP.setVisible(false);
               enterServer.setVisible(false);
               MapMaker.setVisible(false);
               Join.setVisible(false);
               mouseSensetivity.setVisible(false);
               controls.setVisible(false);
               lookSens.setVisible(false);
               up.setVisible(false);
               down.setVisible(false);
               left.setVisible(false);
               right.setVisible(false);
               forward.setVisible(false);
               backward.setVisible(false);
               enterUp.setVisible(false);
               enterDown.setVisible(false);
               enterLeft.setVisible(false);
               enterRight.setVisible(false);
               enterForward.setVisible(false);
               enterBackward.setVisible(false);
              
               
               
               
               
         } 
  
              
              if(e.getActionCommand()=="J o i n  G a m e") 
              { 
                    
                    startGame.setVisible(false);
                    JoinGame.setVisible(false);
                    options.setVisible(false);
                    DeathMatchMode.setVisible(false);
                    CTFMode.setVisible(false);
                    nameHere.setVisible(true);
                    selectCharacter.setVisible(true);
                    selectMap.setVisible(false);
                    enterName.setVisible(true);
                    serverIP.setVisible(true);
                    enterServer.setVisible(true);
                    backButton.setVisible(true);
                    Join.setVisible(true);
                    mouseSensetivity.setVisible(false);
                    controls.setVisible(false);
                    up.setVisible(false);
                    down.setVisible(false);
                    left.setVisible(false);
                    right.setVisible(false);
                    forward.setVisible(false);
                    backward.setVisible(false);
                    enterUp.setVisible(false);
                    enterDown.setVisible(false);
                    enterLeft.setVisible(false);
                    enterRight.setVisible(false);
                    enterForward.setVisible(false);
                    enterBackward.setVisible(false);
                    lookSens.setVisible(false);
                    GameSettings.setIsServer(false);
                    characterSelect.setVisible(true);
                    
                   
                    
                    
                    
                    
              } 
              if(e.getActionCommand()=="J o i n") 
              { 
                    
            	  this.dispose();
                  GameSettings.setServerIP(serverIP.getText());
                  GameSettings.setPlayerName(enterName.getText());
                  KeyBindings.setKeyBindings(enterUp.getText().charAt(0),enterDown.getText().charAt(0),enterLeft.getText().charAt(0),enterRight.getText().charAt(0),enterForward.getText().charAt(0),enterBackward.getText().charAt(0));
                  GameSettings.setAvatarFileName((String)characterSelect.getSelectedItem());

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
             KeyBindings.setKeyBindings(enterUp.getText().charAt(0),enterDown.getText().charAt(0),enterLeft.getText().charAt(0),enterRight.getText().charAt(0),enterForward.getText().charAt(0),enterBackward.getText().charAt(0));
             MapManager.setMap((String)mapSelect.getSelectedItem()); 
             GameSettings.setAvatarFileName((String)characterSelect.getSelectedItem());

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
               controls.setVisible(true);
                
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
               controls.setVisible(false);
                
         }  
         if(e.getActionCommand()=="C o n t r o l s")  
         {  
                
               startGame.setVisible(false); 
               JoinGame.setVisible(false); 
               options.setVisible(false); 
               MapMaker.setVisible(false); 
               backButton.setVisible(true);
               Join.setVisible(false);
               mouseSensetivity.setVisible(false);
               lookSens.setVisible(false);
               controls.setVisible(false);
               up.setVisible(true);
               down.setVisible(true);
               left.setVisible(true);
               right.setVisible(true);
               forward.setVisible(true);
               backward.setVisible(true);
               enterUp.setVisible(true);
               enterDown.setVisible(true);
               enterLeft.setVisible(true);
               enterRight.setVisible(true);
               enterForward.setVisible(true);
               enterBackward.setVisible(true);
                
         }  
         if(e.getActionCommand()=="C r e a t e  M a p")   
         {  
             MapMaker mapMaker = new MapMaker();
        	 backButton.setVisible(true);
        	 Join.setVisible(false);         
               
         }
         
    }
	public static void main(String[] args) 
	{
		new Startup();

	}

}
