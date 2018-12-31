package projeh;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class MainFrame extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean itemVisibility=true;
	ImageIcon pic=new ImageIcon("hold.jpg");
	panel1 pan1;
	Panel2 pan2;
	
	AudioInputStream as;
	public static Clip clip ;
	
	public MainFrame() {
		super("Our Game!");
		setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		this.setUndecorated(true);
        this.setResizable(false);       
		JTabbedPane tab=new JTabbedPane();
		pan2=new Panel2();
		pan1=new panel1(pan2);
		tab.addTab("Menu ", null,pan1,"first panel");
		
		tab.addTab("MapEditor ", null,pan2,"second panel");
		add(tab);
		
	    ChangeListener changeListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent changeEvent) {
	          JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
	          if(sourceTabbedPane.getSelectedIndex()==0)
	          {
	          	JOptionPane.showMessageDialog(null,"your map has been automatically saved in temp","Auto Save",JOptionPane.WARNING_MESSAGE);
	        	save(pan2.map);
	          }
	        }
	      };
	      
	      tab.addChangeListener(changeListener);
	      
	      try {
				File music = new File("Background music.wav");
				as = AudioSystem.getAudioInputStream(music);
				clip = AudioSystem.getClip();
				clip.open(as);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				e.printStackTrace();
			}

	      setVisible(true);
		
	      try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		
	public void save(Map m) {
	      try
	      {
	         FileOutputStream fileOut = new FileOutputStream("temp");
	         
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         
	         out .writeObject(m);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
}
