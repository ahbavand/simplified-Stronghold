package projeh;

import game.ChooseMap;
import game.ChooseMap1;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AskUserName extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	Dimension d  = Toolkit.getDefaultToolkit().getScreenSize();
	JButton Exit , Submit , Browse;
	JRadioButton def , browse , play1 , play2 ,beServer,beClient;
	ButtonGroup bg = new ButtonGroup();
	ButtonGroup bg1 = new ButtonGroup();
	ButtonGroup bg2 = new ButtonGroup();
	JTextField Username , Picture,playerNumber,HostName;
	JLabel question1 , question2 ,question3,question4,question5,question6;
	ImageIcon exit,submit,br;
	Image userImage ;  
	boolean minimized=true;
	
	public AskUserName() {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
		setTitle("UserName");
		setSize(d.width/4, d.height/2);
		setLocationRelativeTo(null);
		setLayout(null);
		
		exit = new ImageIcon("exit-button.png");
		submit = new ImageIcon("submit.png");
		br = new ImageIcon("file-explorer-icon.png");
		Exit = new JButton(exit);
		Submit = new JButton(submit);
		Browse = new JButton(br);
		Exit.setSize(74, 34);
		Submit.setSize(74, 34);
		Browse.setSize(28, 28);
		Exit.setLocation(0, this.getHeight()-64);
		Submit.setLocation(this.getWidth()-81,this.getHeight()-64);
		Browse.setLocation(300, 140);
		getContentPane().add(Exit);
		getContentPane().add(Submit);
		getContentPane().add(Browse);
		Exit.setBorderPainted(false);
		Submit.setBorderPainted(false);
		Browse.setBorderPainted(false);
		Browse.setVisible(false);
		
		Username = new JTextField();
		Username.setSize(150, 30);
		Username.setLocation(150, 25);
		getContentPane().add(Username);
		
		Picture = new JTextField();
		Picture.setSize(150, 30);
		Picture.setLocation(147, 140);
		getContentPane().add(Picture);
		Picture.setVisible(false);
		
		HostName = new JTextField();
		HostName.setSize(150, 30);
		HostName.setLocation(170, 330);
		getContentPane().add(HostName);
		HostName.setVisible(false);
		
		playerNumber = new JTextField();
		playerNumber.setSize(50, 30);
		playerNumber.setLocation(170, 380);
		getContentPane().add(playerNumber);
		playerNumber.setVisible(false);
		
		
		question1 = new JLabel("Enter Your Username :");
		question1.setSize(150, 30);
		question1.setLocation(10, 25);
		getContentPane().add(question1);

		question2 = new JLabel("Choose A Picture :");
		question2.setSize(150, 30);
		question2.setLocation(10, 75);
		getContentPane().add(question2);
		
		question3 = new JLabel("Choose How To Play :");
		question3.setSize(150, 30);
		question3.setLocation(10, 175);
		getContentPane().add(question3);
		
		question4 = new JLabel("To Be The Server:");
		question4.setSize(150, 30);
		question4.setLocation(10, 280);
		getContentPane().add(question4);
		question4.setVisible(false);
		
		question5 = new JLabel("Number Of the Players :");
		question5.setSize(150, 30);
		question5.setLocation(10, 380);
		getContentPane().add(question5);
		question5.setVisible(false);
		
		question6 = new JLabel("HostName :");
		question6.setSize(150, 30);
		question6.setLocation(10, 330);
		getContentPane().add(question6);
		question6.setVisible(false);
		
		
		def = new JRadioButton("Default",true);
		def.setSize(100, 30);
		def.setLocation(120, 75);
		getContentPane().add(def);
		
		browse = new JRadioButton("From A File");
		browse.setSize(150, 30);
		browse.setLocation(120, 100);
		getContentPane().add(browse);
		
		play1 = new JRadioButton("Play with Pc",true);
		play1.setSize(150, 30);
		play1.setLocation(120, 205);
		getContentPane().add(play1);
		
		play2 = new JRadioButton("MultiPlayer");
		play2.setSize(150, 30);
		play2.setLocation(120, 230);
		getContentPane().add(play2);
		
		beServer = new JRadioButton("Yes",true);
		beServer.setSize(50, 30);
		beServer.setLocation(170, 280);
		getContentPane().add(beServer);
		beServer.setVisible(false);
		
		beClient = new JRadioButton("No",true);
		beClient.setSize(50, 30);
		beClient.setLocation(220, 280);
		getContentPane().add(beClient);
		beClient.setVisible(false);
		
		bg.add(def);
		bg.add(browse);
		
		bg1.add(play1);
		bg1.add(play2);
		
		bg2.add(beServer);
		bg2.add(beClient);
		
		setVisible(true);
		
		Exit.addActionListener(this);
		Submit.addActionListener(this);
		def.addActionListener(this);
		browse.addActionListener(this);
		Browse.addActionListener(this);
		play1.addActionListener(this);
		play2.addActionListener(this);
		beServer.addActionListener(this);
		beClient.addActionListener(this);
		
		try {
			userImage =  ImageIO.read(new File("default-user.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new AskUserName();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==Exit)System.exit(0);
		if(arg0.getSource()==Submit&&Username.getText().length()!=0){
			dispose();

			if(play2.isSelected()){
				if(beServer.isSelected()){
					new Thread((new Server(Integer.parseInt(playerNumber.getText())))).start();	
				}
				Client client = new Client(HostName.getText(),userImage,Username.getText());
				if(beServer.isSelected()){
					new ChooseMap(Integer.parseInt(playerNumber.getText()),client);
					client.mapSender=1;
				}
				new Thread(client).start();
			}
			else  if(play1.isSelected()){
				new ChooseMap1();
				
				
			}
			
		}
		if(arg0.getSource()==Submit&&Username.getText().length()==0)JOptionPane.showMessageDialog(null,"You Should Choose A Username !");
		if(arg0.getSource()==browse){
			Picture.setVisible(true);
			Browse.setVisible(true);
		}
		if(arg0.getSource()==def){
			Picture.setVisible(false);
			Browse.setVisible(false);
		}
		if(arg0.getSource()==Browse){
			userImage =  load();
		}
		if(arg0.getSource()==play2){
			if(minimized){
				Submit.setLocation(this.getWidth()-81,this.getHeight()+36);
				Exit.setLocation(0, this.getHeight()+36);
				setSize(getWidth(), getHeight()+100);
				question4.setVisible(true);
				question5.setVisible(true);
				question6.setVisible(true);
				beServer.setVisible(true);
				beClient.setVisible(true);
				playerNumber.setVisible(true);
				HostName.setVisible(true);
				minimized = !minimized;
			}
		
		}
		if(arg0.getSource()==play1){
			if(!minimized){
				setSize(getWidth(), getHeight()-100);
				Submit.setLocation(this.getWidth()-81,this.getHeight()-64);
				Exit.setLocation(0, this.getHeight()-64);
				question4.setVisible(false);
				question5.setVisible(false);
				question6.setVisible(false);
				beServer.setVisible(false);
				beClient.setVisible(false);
				playerNumber.setVisible(false);
				HostName.setVisible(false);
				minimized = !minimized;
			}
		}
		if(arg0.getSource()==beServer){
		playerNumber.setVisible(true);
		question5.setVisible(true);
		}
		if(arg0.getSource()==beClient){
			playerNumber.setVisible(false);
			question5.setVisible(false);
		}
	}
	
	public Image load() {
	      BufferedImage  image = null;
			String filename = File.separator;
		    JFileChooser fc = new JFileChooser(new File(filename));
		    fc.showOpenDialog(null);

		try {
			image  =  ImageIO.read(new File(fc.getSelectedFile().getAbsolutePath()));
			Picture.setText(fc.getSelectedFile().getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			def.setSelected(true);
//			e.printStackTrace();
			
		} 
	      return image;
	}
	
	
}
