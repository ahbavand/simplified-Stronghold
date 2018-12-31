package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import objects.Waterfront;

public class WaterfrontPanel extends JPanel implements Runnable , ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int freeAdam;
	int worker;
	Integer woodSize=2000;
	Integer ironSize=2000;
	Integer foodSize=2000;
	Integer health=100;
	boolean display;
	JLabel showWood;
	JLabel showIron;
	JLabel showHealth;
	JLabel showFood;
	JButton makeHuman;
	JButton makeShip1;
	JButton makeShip2;
	JButton makeShip3;
	int waterfrontTask;
	
	Waterfront waterfront;
	
	ImageIcon wood = new ImageIcon("wood.png");
	ImageIcon iron = new ImageIcon("iron.png");
	ImageIcon healt = new ImageIcon("health.png");
	ImageIcon food= new ImageIcon("fish.png");
	ImageIcon pic = new ImageIcon("pic\\port.png");
	
	GameDisplayPanel gdp;
	
	public WaterfrontPanel(boolean display,Integer woodSize,Integer ironSize,Integer health,Integer foodSize, GameDisplayPanel gdp) {
		this.woodSize=woodSize;
		this.ironSize=ironSize;
		this.foodSize=foodSize;
		this.health=health;
		
		
		this.display=display;
		this.gdp=gdp;
		
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
		
		showFood= new JLabel();
		Border border2 = BorderFactory.createLineBorder(Color.ORANGE, 5);
		showFood.setBorder(border2);
		showFood.setLocation(350,170);
		showFood.setSize(woodSize/20,5);
		add(showFood);
		
		showHealth= new JLabel();
		Border border3 = BorderFactory.createLineBorder(Color.RED, 5);
		showHealth.setBorder(border3);
		showHealth.setLocation(350, 10);
		showHealth.setSize(health/2,5);
		add(showHealth);
		
		makeHuman= new JButton("Human");
		makeHuman.setSize(80, 30);
		makeHuman.setLocation(700, 70);
		makeHuman.addActionListener(this);
		add(makeHuman);
		
		makeShip1= new JButton("Carrier Ship");
		makeShip1.setSize(100, 30);
		makeShip1.setLocation(600, 120);
		makeShip1.addActionListener(this);
		add(makeShip1);
		
		makeShip2= new JButton("Military Ship");
		makeShip2.setSize(100, 30);
		makeShip2.setLocation(720, 120);
		makeShip2.addActionListener(this);
		add(makeShip2);
		
		makeShip3= new JButton("Fishing Ship");
		makeShip3.setSize(100, 30);
		makeShip3.setLocation(840, 120);
		makeShip3.addActionListener(this);
		add(makeShip3);
		
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
	public void setFoodSize(Integer foodSize) {
		this.foodSize = foodSize;
	}
	public Integer getFoodSize() {
		return foodSize;
	}
	
	
	public void setWaterfront(Waterfront waterfront) {
		this.waterfront = waterfront;
	}
	public Waterfront getWaterfront() {
		return waterfront;
	}
	
	@Override
	public void paintComponent(Graphics e) {

		super.paintComponent(e);
		e.drawImage(pic.getImage(), 10, 10,200,200, null);
		e.drawImage(healt.getImage(), 300, 10,30,30, null);
		e.drawImage(wood.getImage(), 300, 60,30,30, null);
		e.drawImage(iron.getImage(), 300, 110,30,30, null);
		e.drawImage(food.getImage(), 300, 170,30,30, null);
		e.drawString(health.toString(), 400, 30);
		e.drawString(woodSize.toString(), 400, 80);
		e.drawString(ironSize.toString(), 400, 130);
		e.drawString(foodSize.toString(), 400, 180);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			showHealth.setSize(getHealth()/2,5);
			showIron.setSize(getIronSize()/20,5);
			showWood.setSize(getWoodSize()/20,5);
			showFood.setSize(getFoodSize()/20, 5);
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		freeAdam=0;
		
		for(int i=0;i<waterfront.getAdams().size();i++)
			if((waterfront.getAdams().elementAt(i).getDuty()==0||waterfront.getAdams().elementAt(i).getDuty()==10000)&&waterfront.getAdams().elementAt(i).jazirenumber==waterfront.jazirenumber&&waterfront.getAdams().elementAt(i).isinkeshti==false)
				freeAdam++;
		
		if(e.getSource()==makeHuman)
		{	
			int dialogResult = JOptionPane.showConfirmDialog(null, "It will cost 400 units wood and 200 units iron. are you agree?","Make Human",JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION){
	
				if(waterfront.getwoodSize()>400&&waterfront.getIronSize()>200)
				{
					waterfront.setwoodSize(waterfront.getwoodSize()-400);
					waterfront.setIronSize(waterfront.getIronSize()-200);
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            @Override
					            public void run() {
					            	
					            	gdp.makeHuman(waterfront);
					            	
					            }
					        }, 
					        10000
					);
  
					gdp.makeHuman(waterfront);
				}
				else
					JOptionPane.showMessageDialog(null, "you don't have sufficent recources");
					
			}
			
			waterfrontTask = 1;
		}
		if(e.getSource()==makeShip1)
		{	

			int dialogResult = JOptionPane.showConfirmDialog(null, "It will cost 800 units wood and 400 units iron. are you agree?","Make Carrier Ship",JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				if(waterfront.getwoodSize()>=800&&waterfront.getIronSize()>=400)
				{
					
					if(freeAdam==0){
						JOptionPane.showMessageDialog(null, "you have no Worker to build your ship");
				
					}
					else
					{
						String str=JOptionPane.showInputDialog(null, "you have"+freeAdam+" free humans in your base island. how many do you hire to create your ship?");
						if(str==null){
							JOptionPane.showMessageDialog(null, "Operation failed!");
							

						}
						else
						{
							worker=Integer.valueOf(str);
							if(worker>freeAdam){
								JOptionPane.showMessageDialog(null, "you don't have enough workers");
								
							}
							else
							{
								for(int i=0;i<worker;i++)
								{
									for(int j=0; j<waterfront.getAdams().size();j++)
										if(waterfront.getAdams().elementAt(j).getDuty()==0)
										{
											waterfront.getAdams().elementAt(j).setDuty(8);
											waterfront.getAdams().elementAt(j).setActivity(1);
											break;
											
										}
										
								}
								new java.util.Timer().schedule( 
								        new java.util.TimerTask() {
								            @Override
								            public void run() {
								            	if(gdp.isshabake==false)
								            		gdp.makeCarrierShip(waterfront);
								            	waterfront.setwoodSize(waterfront.getwoodSize()-800);
												waterfront.setIronSize(waterfront.getIronSize()-400);
								            
								            }
								        }, 
								        30000/worker 
								);
							}
						}
					}
					
				}
				else 
					JOptionPane.showMessageDialog(null, "you don't have sufficent recources");
			}
			waterfrontTask=2;
		}
		
		
		
		
		
		
		
		if(e.getSource()==makeShip2)
		{
			int dialogResult=JOptionPane.showConfirmDialog(null,  "It will cost 1000 wood and 1000 iron. are you agree?","Make Military Ship",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				if(waterfront.getwoodSize()>=1000&&waterfront.getIronSize()>=1000)
				{
					
					if(freeAdam==0){
						JOptionPane.showMessageDialog(null, "you have no Worker to build your ship");
					
					}
					else
					{
						String str=JOptionPane.showInputDialog(null, "you have"+freeAdam+" free humans in your base island. how many do you hire to create your ship?");
						if(str==null){
							JOptionPane.showMessageDialog(null, "Operation failed!");
						

						}
						else
						{
							worker=Integer.valueOf(str);
							if(worker>freeAdam){
								JOptionPane.showMessageDialog(null, "you don't have enough workers");
								
							}
							else
							{
								for(int i=0;i<worker;i++)
								{
									for(int j=0; j<waterfront.getAdams().size();j++)
										if(waterfront.getAdams().elementAt(j).getDuty()==0)
										{
											waterfront.getAdams().elementAt(j).setDuty(8);
											waterfront.getAdams().elementAt(j).setActivity(2);
											break;
											
										}
										
								}
								new java.util.Timer().schedule( 
								        new java.util.TimerTask() {
								            @Override
								            public void run() {
								            	if(gdp.isshabake==false)
								            		gdp.makeMilliteryShip(waterfront);
								            	waterfront.setwoodSize(waterfront.getwoodSize()-1000);
												waterfront.setIronSize(waterfront.getIronSize()-1000);
								            }
								        }, 
								        30000/worker 
								);
							}
						}
					}
					
				}
				else 
					JOptionPane.showMessageDialog(null, "you don't have sufficent recources");
			}
			waterfrontTask=4;
		}	
		
		
		
		
		if(e.getSource()==makeShip3)
		{
			int dialogResult=JOptionPane.showConfirmDialog(null,  "It will cost 800 wood and 200 iron. are you agree?","Make Fishing Ship",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				if(waterfront.getwoodSize()>=800&&waterfront.getIronSize()>=200)
				{
			
					if(freeAdam==0){
						JOptionPane.showMessageDialog(null, "you have no Worker to build your ship");
				
					}
					else
					{
						String str=JOptionPane.showInputDialog(null, "you have"+freeAdam+" free humans in your base island. how many do you hire to create your ship?");
						if(str==null){
							JOptionPane.showMessageDialog(null, "Operation failed!");
				

						}
						else
						{
							worker=Integer.valueOf(str);
							if(worker>freeAdam){
								JOptionPane.showMessageDialog(null, "you don't have enough workers");
						
							}
							else
							{
								for(int i=0;i<worker;i++)
								{
									for(int j=0; j<waterfront.getAdams().size();j++)
										if(waterfront.getAdams().elementAt(j).getDuty()==0)
										{
											waterfront.getAdams().elementAt(j).setDuty(8);
											waterfront.getAdams().elementAt(j).setActivity(3);
											break;
											
										}
										
								}
								new java.util.Timer().schedule( 
								        new java.util.TimerTask() {
								            @Override
								            public void run() {
								        		waterfront.setwoodSize(waterfront.getwoodSize()-800);
												waterfront.setIronSize(waterfront.getIronSize()-200);
												if(gdp.isshabake==false)
													gdp.makeFishingShip(waterfront);
								            }
								        }, 
								        30000/worker 
								);
							}
						}
					}
					
				}
				else 
					JOptionPane.showMessageDialog(null, "you don't have sufficent recources");
			}
			waterfrontTask = 3; 
		}
		if(gdp.isshabake==true)
		{
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	
			            	gdp.client.sendClick(Integer.toString(gdp.selectid)+"-"+Integer.toString(waterfrontTask));
			            	waterfront.setwoodSize(waterfront.getwoodSize()-1000);
							waterfront.setIronSize(waterfront.getIronSize()-1000);
			            }
			        }, 
			        30000/worker 
			);
			
		}
	}
	

}
