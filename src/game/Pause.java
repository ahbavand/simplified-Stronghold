package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import projeh.MainFrame;

public class Pause extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StartGame startgame;
	JButton resum = new JButton("resume");
	JButton setting = new JButton("setting");
	JButton exit = new JButton("exit");
	JButton save= new JButton("save");
	JButton help = new JButton("help");
	JButton quit = new JButton("quit");
	
	boolean PM = true ; 
	boolean PS = true ;
	float s = 0.0f ;
	ImageIcon music , muteMusic , sound , muteSound , plus,minus;
	JButton jMusic,jSound,positive,negative;
	FloatControl gainControl;
	
	public Pause(StartGame startgame) {
		this.startgame=startgame;
		this.setUndecorated(true);
        this.setResizable(false);
		setSize(1*getToolkit().getScreenSize().width/5,3*getToolkit().getScreenSize().height/5);
		setLayout(null);
		setLocationRelativeTo(null);
		specifyButton(resum, 10);
		specifyButton(setting, 60);
		specifyButton(help, 110);
		specifyButton(save, 160);
		specifyButton(quit, 210);
		specifyButton(exit,260);
		gainControl = (FloatControl) MainFrame.clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		music = new ImageIcon("music.png");
		muteMusic = new ImageIcon("music mute2.png");
		sound = new ImageIcon("sound.png");
		muteSound = new ImageIcon("sound mute2.png");
		plus = new ImageIcon("plus.png");
		minus = new ImageIcon("minus.png");
		jMusic = new JButton(music);
		jMusic.setBorderPainted(false); 
		jMusic.setContentAreaFilled(false); 
		jMusic.setFocusPainted(false); 
		jMusic.setOpaque(false);
		jSound = new JButton(sound);
		jSound.setBorderPainted(false); 
		jSound.setContentAreaFilled(false); 
		jSound.setFocusPainted(false); 
		jSound.setOpaque(false);
		positive = new JButton(plus);
		positive.setBorderPainted(false); 
		positive.setContentAreaFilled(false); 
		positive.setFocusPainted(false); 
		positive.setOpaque(false);
		negative = new JButton(minus);
		negative.setBorderPainted(false); 
		negative.setContentAreaFilled(false); 
		negative.setFocusPainted(false); 
		negative.setOpaque(false);
		jMusic.setSize(40, 40);
		jSound.setSize(40, 40);
		positive.setSize(40, 40);
		negative.setSize(40, 40);
		jMusic.setLocation(80,300);
		jSound.setLocation(130,300);
		positive.setLocation(80, 350);
		negative.setLocation(130, 350);
		add(jMusic);
		add(jSound);
		add(jMusic);
		add(jSound);
		add(positive);
		add(negative);
		jMusic.addActionListener(this);
		jSound.addActionListener(this);
		positive.addActionListener(this);
		negative.addActionListener(this);
	}
	private void specifyButton(JButton temp, int pos) {
		temp.setSize(100, 45);
		temp.setLocation(85, pos);
		temp.addActionListener(this);
		this.add(temp);
//		temp.setIcon(pic);
//		temp.setDisabledIcon(null);
//		temp.setBorder(null);
		temp.setContentAreaFilled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==exit)
			System.exit(0);
		if(e.getSource()==resum)
			dispose();
		if(e.getSource()==quit)
		{
			dispose();
			startgame.dispose();
		}
		if(e.getSource()==jMusic){
			if(PM==true){
				jMusic.setIcon(muteMusic);
				MainFrame.clip.stop();
				PM=false;
			}
			else {
				jMusic.setIcon(music);
				MainFrame.clip.loop(Clip.LOOP_CONTINUOUSLY);
				PM=true;
			}
		}
		
		if(e.getSource()==jSound){
			if(PS==true){
				jSound.setIcon(muteSound);
				PS=false;
			}
			else {
				jSound.setIcon(sound);
				PS=true;
			}
		}
		if(e.getSource()==positive){
			if(s<0)s+=5;
			else{
				if(s<6)s+=1;
				else;
			}
			gainControl.setValue(s);
		}
		if(e.getSource()==negative){
			if(s>0)s-=1;
			else{
				if(s>-24)s-=4;
				else;
			}
			gainControl.setValue(s);
		}
			
			
	}
}
