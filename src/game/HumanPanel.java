package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class HumanPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer woodSize=2000;
	Integer ironSize=2000;
	Integer health=100;
	boolean display;
	JLabel showWood;
	JLabel showIron;
	JLabel showHealth;
	
	ImageIcon wood = new ImageIcon("wood.png");
	ImageIcon iron = new ImageIcon("iron.png");
	ImageIcon healt = new ImageIcon("health.png");
	ImageIcon pic = new ImageIcon("human.png");
	
	public HumanPanel(boolean display,Integer woodSize,Integer ironSize,Integer health) {
		this.woodSize=woodSize;
		this.ironSize=ironSize;
		this.health=health;
		this.display=display;
		
		setSize(getToolkit().getScreenSize().width, 200);
		setLocation(0, getToolkit().getScreenSize().height-200);
		setLayout(null);
		
		showWood= new JLabel();
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 5);
		showWood.setBorder(border);
		showWood.setLocation(350,60);
		showWood.setSize(woodSize/20,5);
		add(showWood);
		
		showIron= new JLabel();
		Border border1 = BorderFactory.createLineBorder(Color.GRAY, 5);
		showIron.setBorder(border1);
		showIron.setLocation(350, 110);
		showIron.setSize(ironSize/20,5);
		add(showIron);
		
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
	
	public Integer getWoodSize() {
		return woodSize;
	}
	public void setWoodSize(Integer woodSize) {
		this.woodSize = woodSize;
	}
	public Integer getIronSize() {
		return ironSize;
	}
	public void setIronSize(Integer ironSize) {
		this.ironSize = ironSize;
	}
	public Integer getHealth() {
		return health;
	}
	public void setHealth(Integer health) {
		this.health = health;
	}

	@Override
	public void setVisible(boolean display) {
		// TODO Auto-generated method stub
		super.setVisible(display);
	}
	@Override
	public void paintComponent(Graphics e) {

		super.paintComponent(e);
		e.drawImage(pic.getImage(), 10, 10, null);
		e.drawImage(healt.getImage(), 300, 10,30,30, null);
		e.drawImage(wood.getImage(), 300, 60,30,30, null);
		e.drawImage(iron.getImage(), 300, 110,30,30, null);
		
		e.drawString(health.toString(), 400, 30);
		e.drawString(woodSize.toString(), 400, 80);
		e.drawString(ironSize.toString(), 400, 130);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			showHealth.setSize(health/2,5);
			showIron.setSize(ironSize/20,5);
			showWood.setSize(woodSize/20,5);
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
