package projeh;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class panel1 extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton exit,ssitting,gsitting,game;
	ImageIcon back;
	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	JLabel jla,jla1;
	Panel2 panel2;
	public panel1(Panel2 panel2) {
		super();
		this.panel2=panel2;
		setLayout(null);
		setSize(d.width,d.height);
		setLocation(0,0);
		 exit=new JButton("exit");
		 ssitting=new JButton("sound setting");
		 gsitting=new JButton("game setting");
		 game=new JButton("new game");
		 back=new ImageIcon("pic\\panel1\\wallpaper.jpg");
		 jla=new JLabel("IslandWars");
		 jla.setForeground(Color.RED);
		 jla.setSize(800,400);
		 jla.setLocation(770,-100);
		 this.add(jla);
		 Font f = new Font("Dialog", Font.PLAIN, 36);
		 Font f1 = new Font("Dialog", Font.PLAIN, 72);
		 jla.setFont(f1);
		 jla.setForeground(Color.orange);

		 jla1=new JLabel("Modified by M.M.Samiee paghale,E.Mehralian,A.H.Sohrabbeig,A.H.Bavand");
		 jla1.setSize(12000, 300);
		 jla1.setLocation(360, 800);
		 jla1.setFont(f);
		 jla1.setForeground(Color.orange);
		 this.add(jla1);
		 
		 specifyButton(exit, 550,new ImageIcon("pic\\panel1\\exit.png"));
		 specifyButton(ssitting, 400,new ImageIcon("pic\\panel1\\soundSetting.png"));
		 specifyButton(gsitting, 250,new ImageIcon("pic\\panel1\\gameSetting.png"));
		 specifyButton(game, 100,new ImageIcon("pic\\panel1\\newGame.png"));
	

		 JLabel jl=new JLabel();
		 jl.setSize(d.width,d.height);
		 jl.setLocation(0, 0);
		 jl.setIcon(back);
		 this.add(jl);

	}
	private void specifyButton(JButton temp, int pos,ImageIcon pic) {
		temp.setSize(350, 100);
		temp.setLocation(100, pos);
		temp.addActionListener(this);
		Font f = new Font("Dialog", Font.PLAIN, 36);
		temp.setFont(f);
		temp.setIcon(pic);
		temp.setDisabledIcon(null);
		temp.setBorder(null);
		temp.setContentAreaFilled(false);
		
		this.add(temp);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==exit)
			System.exit(0);
		if(arg0.getSource()==game)
			new AskUserName();
	}

}
