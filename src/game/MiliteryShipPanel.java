package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MiliteryShipPanel extends JPanel implements Runnable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Integer health=100;
	boolean display;
	JLabel showHealth;
	ImageIcon healt = new ImageIcon("health.png");
	ImageIcon pic = new ImageIcon("human.png");
	
	public MiliteryShipPanel(boolean display,Integer health) {
		this.health=health;
		this.display=display;
		
		setSize(getToolkit().getScreenSize().width, 200);
		setLocation(0, getToolkit().getScreenSize().height-200);
		setLayout(null);
		
		showHealth= new JLabel();
		Border border2 = BorderFactory.createLineBorder(Color.RED, 5);
		showHealth.setBorder(border2);
		showHealth.setLocation(350, 10);
		showHealth.setSize(health/2,5);
		add(showHealth);
		
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		setVisible(false);
		new Thread(this).start();
		
	}
	
	
	public Integer getHealth() {
		return health;
	}
	public void setHealth(Integer health) {
		this.health = health;
	}
	
	
	
	@Override
	public void paintComponent(Graphics e) {

		super.paintComponent(e);
		e.drawImage(pic.getImage(), 10, 10, null);
		e.drawImage(healt.getImage(), 300, 10,30,30, null);
		
		e.drawString(health.toString(), 400, 30);
		
	}

	@Override
	public void run() {

		// TODO Auto-generated method stub
		while(true){
			showHealth.setSize(health/2,5);
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
