package objects;


import game.GameDisplayPanel;
import game.Jangi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import projeh.Map;

public class Waterfront implements MouseListener {
	private int shomareh;/////shomareh keshvar
	public int x;////x makan eskeleh
	public int y;////y makan eskeleh
	private Vector<Human> adams=new Vector<Human>();
	public Vector<Bar> bars=new Vector<Bar>();
	public Vector<Mahigir> mahi=new Vector<Mahigir>();
	public Vector<Jangi> jangi=new Vector<Jangi>();
	public boolean isinhoosh=false;
	int startTime;
	int [][]rah;
	Vector<Tree>trees;
	Vector<Iron>irons;
	Vector<Waterfront>waterfronts;
	Map map;
	public int jazirenumber;
	private int woodSize=20000;
	private int ironSize=20000;
	private int health=1000;
	private int foodsize=0;
	GameDisplayPanel gdp;
	public Waterfront(int shomareh,int x,int y,int [][]rah,int jazirenumber, GameDisplayPanel gdp,Map map,Vector<Tree>trees,Vector<Iron>irons,Vector<Waterfront>waterfronts,int startTime ){
		this.jazirenumber=jazirenumber;
		this.setShomareh(shomareh);
		this.x=x;
		this.y=y;
		this.rah=rah;
		for(int i=0 ;i <5;i++)
			gdp.makeHuman(this);
		this.map=map;
		this.trees=trees;
		this.irons=irons;
		this.waterfronts=waterfronts;
		this.startTime=startTime;

		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public synchronized int getwoodSize() {
		return woodSize;
	}
	public synchronized void setwoodSize(int woodSize) {
		this.woodSize = woodSize;
	}
	public synchronized int getIronSize() {
		return ironSize;
	}
	public synchronized void setIronSize(int ironSize) {
		this.ironSize = ironSize;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	public synchronized Vector<Human> getAdams() {
		return adams;
	}
	public synchronized void setAdams(Vector<Human> adams) {
		this.adams = adams;
	}
	public int getShomareh() {
		return shomareh;
	}
	public void setShomareh(int shomareh) {
		this.shomareh = shomareh;
	}
	public synchronized void makeadam(){
	//	for(int i=0;i<14-getAdams().size();i++){
			int size=getAdams().size()+1;
			if(map.getLocations()[getX()+1][getY()].getD1()==3)
				
				getAdams().addElement(new Human((getX()+1)*50,getY()*50, rah,getShomareh()*1000+100+size ,this,trees,irons,bars,waterfronts,startTime,jazirenumber,gdp));
			
			else if(map.getLocations()[getX()-1][getY()].getD1()==3)
				
				getAdams().addElement(new Human((getX()-1)*50,getY()*50,rah,getShomareh()*1000+size+100 ,this,trees,irons,bars,waterfronts,startTime,jazirenumber,gdp));

			else if(map.getLocations()[getX()][getY()+1].getD1()==3)
			    
				getAdams().addElement(new Human(getX()*50,(getY()+1)*50, rah,getShomareh()*1000+size+100 ,this,trees,irons,bars,waterfronts,startTime,jazirenumber,gdp));

			else if(map.getLocations()[getX()][getY()-1].getD1()==3)
				
				getAdams().addElement(new Human(getX()*50,(getY()-1)*50,rah,getShomareh()*1000+size+100 ,this,trees,irons,bars,waterfronts,startTime,jazirenumber,gdp));

//		}

		
	}

	
	
	
	public synchronized void killHuman(Human h){
	//	System.out.println(h.getId());
		int s = 0;
		for(int i=0;i<getAdams().size();i++)
			if(getAdams().elementAt(i).getId()==h.getId()){
				s=i;
				
			}
		for(int i=0;i<getAdams().size();i++)
			if(h.getId()==getAdams().elementAt(i).getId()){
			
				for(int j=i+1;j<getAdams().size();j++){
					getAdams().elementAt(j).setId(getAdams().elementAt(j).getId()-1);
				}
				break;
				
			}
		h.t.stop();
		adams.removeElementAt(s);
		for(int j=0;j<getAdams().size();j++){
			System.out.println(getAdams().elementAt(j).getId()+"salam"+j);
		}
	}
	public synchronized int getFoodsize() {
		
		return foodsize;
	}
	public synchronized void setFoodsize(int foodsize) {
		this.foodsize = foodsize;
	}
	
	
	public boolean takefood(int x){
		if(x<=getFoodsize()){
			setFoodsize(getFoodsize()-x);
			return true;
		}
		else
			return false;
			
		
	}

}

	














