package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FishingShipPanel extends JPanel implements Runnable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer FoodSize=2000;
	Integer health=100;
	boolean display;
	JLabel showFood;
	JLabel showHealth;
	
	ImageIcon Food = new ImageIcon("Food.png");
	ImageIcon healt = new ImageIcon("health.png");
	ImageIcon pic = new ImageIcon("human.png");
	
	public FishingShipPanel(boolean display,Integer FoodSize,Integer health) {
		this.FoodSize=FoodSize;
		this.health=health;
		this.display=display;
		
		setSize(getToolkit().getScreenSize().width, 200);
		setLocation(0, getToolkit().getScreenSize().height-200);
		setLayout(null);
		
		showFood= new JLabel();
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 5);
		showFood.setBorder(border);
		showFood.setLocation(350,60);
		showFood.setSize(FoodSize/20,5);
		add(showFood);
		
	
		
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




	
	
	public Integer getFoodSize() {
		return FoodSize;
	}
	public void setFoodSize(Integer FoodSize) {
		this.FoodSize = FoodSize;
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
		e.drawImage(Food.getImage(), 300, 60,30,30, null);
		
		e.drawString(health.toString(), 400, 30);
		e.drawString(FoodSize.toString(), 400, 80);
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			showHealth.setSize(health/2,5);
			showFood.setSize(FoodSize/20,5);
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
