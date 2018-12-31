package projeh;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class  GameMusic extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	boolean PM = true ; 
	boolean PS = true ;
	float s = 0.0f ;
	AudioInputStream as;
	Clip clip,clip1 ;
	ImageIcon music , muteMusic , sound , muteSound;
	JButton jMusic,jSound,positive,negative;
	FloatControl gainControl;
	
	public GameMusic (){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			File music = new File("music\\music1.Wav");
			as = AudioSystem.getAudioInputStream(music);
			clip = AudioSystem.getClip();
			clip.open(as);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		music = new ImageIcon("music.png");
		muteMusic = new ImageIcon("music mute2.png");
		sound = new ImageIcon("sound.png");
		muteSound = new ImageIcon("sound mute2.png");
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
		positive = new JButton("positive");
		negative = new JButton("negative");
		jMusic.setSize(40, 40);
		jSound.setSize(40, 40);
		positive.setSize(100, 40);
		negative.setSize(100, 40);
		jMusic.setLocation(100,100);
		jSound.setLocation(200,100);
		positive.setLocation(300, 100);
		negative.setLocation(400, 100);
		getContentPane().add(jMusic);
		getContentPane().add(jSound);
		getContentPane().add(jMusic);
		getContentPane().add(jSound);
		getContentPane().add(positive);
		getContentPane().add(negative);
		jMusic.addActionListener(this);
		jSound.addActionListener(this);
		positive.addActionListener(this);
		negative.addActionListener(this);
		setTitle("Playing Background Music");
		setSize(500, 500);
		setLocation(100, 100);
		setLayout(null);
		setVisible(true);

	}
	
	public static void main(String[] args) {
		
		new GameMusic();
	
	}
	
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource()==jMusic){
			if(PM==true){
				jMusic.setIcon(muteMusic);
				clip.stop();
				PM=false;
			}
			else {
				jMusic.setIcon(music);
				clip.loop(clip.LOOP_CONTINUOUSLY);
				PM=true;
			}
		}
		
		if(arg0.getSource()==jSound){
			if(PS==true){
				jSound.setIcon(muteSound);
				PS=false;
			}
			else {
				jSound.setIcon(sound);
				PS=true;
			}
		}
		if(arg0.getSource()==positive){
			if(s<0)s+=5;
			else{
				if(s<6)s+=1;
				else;
			}
			gainControl.setValue(s);
		}
		if(arg0.getSource()==negative){
			if(s>0)s-=1;
			else{
				if(s>-24)s-=4;
				else;
			}
			gainControl.setValue(s);
		}
		
	}
}

