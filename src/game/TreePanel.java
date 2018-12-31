package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TreePanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer woodSize=2000;
	JLabel showWood;
	boolean display;
	
	ImageIcon wood = new ImageIcon("wood.png");
	ImageIcon pic = new ImageIcon("human.png");
	
	public TreePanel(boolean display,Integer woodSize) {
		this.woodSize=woodSize;
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
	@Override
	public void setVisible(boolean display) {
		// TODO Auto-generated method stub
		super.setVisible(display);
	}
	@Override
	public void paintComponent(Graphics e) {

		super.paintComponent(e);
		e.drawImage(pic.getImage(), 10, 10, null);
		e.drawImage(wood.getImage(), 300, 60,30,30, null);
		
		e.drawString(woodSize.toString(), 400, 80);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
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
