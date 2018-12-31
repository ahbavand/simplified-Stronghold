package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class IronPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer ironSize=2000;
	JLabel showiron;
	boolean display;
	
	ImageIcon iron = new ImageIcon("iron.png");
	ImageIcon pic = new ImageIcon("human.png");
	
	public IronPanel(boolean display,Integer ironSize) {
		this.ironSize=ironSize;
		this.display=display;
		
		setSize(getToolkit().getScreenSize().width, 200);
		setLocation(0, getToolkit().getScreenSize().height-200);
		setLayout(null);
		
		showiron= new JLabel();
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 5);
		showiron.setBorder(border);
		showiron.setLocation(350,60);
		showiron.setSize(ironSize/20,5);
		add(showiron);
		
		setBorder(BorderFactory.createLineBorder(Color.red));
		
		setVisible(false);
		new Thread(this).start();
	}
	public Integer getironSize() {
		return ironSize;
	}
	public void setironSize(Integer ironSize) {
		this.ironSize = ironSize;
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
		e.drawImage(iron.getImage(), 300, 60,30,30, null);
		
		e.drawString(ironSize.toString(), 400, 80);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			showiron.setSize(ironSize/20,5);
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
