package game;

import java.util.Vector;

import objects.Bar;
import objects.Bigfish;
import objects.Human;
import objects.Iron;
import objects.Mahigir;
import objects.SmallFish;
import objects.Tree;
import objects.Waterfront;
import projeh.Map;
public class Hoosh implements Runnable{
	Waterfront waterfront;
	Map map;
	int[][]shiprah;
	int[][]rah;
	 int idjangi=1;

	Vector<Tree>trees;
	boolean khoshshans=false;
	boolean ischoob=false;
	boolean isiron=false;
	Vector<Bigfish>bigfishs=new Vector<Bigfish>();
	Vector<SmallFish>smallfishs=new Vector<SmallFish>();
	Vector<Waterfront> waterfronts=new Vector<Waterfront>();
	Vector<Iron>irons = new Vector<Iron>();
	int startTime;
	GameDisplayPanel gdp;

	int[][] winterTable;
	
	Mahigir mahigir;

		public Hoosh(Waterfront waterfront,Map map,Vector<Tree> trees,Vector<Bigfish>bigfishs,Vector<SmallFish>smallfishs,int[][]shiprah,int[][]rah,Vector<Iron>irons,int startTime,GameDisplayPanel gdp,Vector<Waterfront> waterfronts,int[][]table2){
			this.waterfront=waterfront;
			this.trees=trees;
			this.map=map;
			this.bigfishs=bigfishs;
			this.smallfishs=smallfishs;
			this.irons=irons;
			this.shiprah=shiprah;
			this.rah=rah;
			this.startTime=startTime;
			this.waterfronts=waterfronts;
			this.gdp=gdp;
			this.winterTable=table2;
			waterfront.isinhoosh=true;
			 newkardaneavval();
			 new Thread(this).start();
				for(int j=0;j<trees.size();j++)
				if(waterfront.jazirenumber==trees.elementAt(j).jazirenumber){
					khoshshans=true;
					ischoob=true;
				}
				for(int j=0;j<irons.size();j++)
					if(waterfront.jazirenumber==irons.elementAt(j).jazirenumber){
						khoshshans=true;
						isiron=true;
					}

			
		}
		public void newkardaneavval(){

				if(map.getLocations()[waterfront.getX()+1][waterfront.getY()].getD1()!=3){
					
					waterfront.mahi.addElement(new Mahigir((waterfront.getX()+1)*50, waterfront.getY()*50, shiprah, waterfront.getShomareh()*10000, map, waterfront,bigfishs,smallfishs,startTime,winterTable));
					waterfront.jangi.addElement(new Jangi((waterfront.getX()+1)*50,  waterfront.getY()*50, shiprah, 100000*waterfront.getShomareh(), waterfronts, waterfront,startTime,winterTable));
				}
				else if(map.getLocations()[waterfront.getX()-1][waterfront.getY()].getD1()!=3){
					
					waterfront.mahi.addElement(new Mahigir((waterfront.getX()-1)*50, waterfront.getY()*50, shiprah, waterfront.getShomareh()*10000, map, waterfront,bigfishs,smallfishs,startTime,winterTable));
					
					waterfront.jangi.addElement(new Jangi((waterfront.getX()-1)*50,  waterfront.getY()*50, shiprah, 100000*waterfront.getShomareh(), waterfronts, waterfront,startTime,winterTable));

				}
				


				else if(map.getLocations()[waterfront.getX()][waterfront.getY()+1].getD1()!=3){
					
					waterfront.mahi.addElement(new Mahigir(waterfront.getX()*50,( waterfront.getY()+1)*50, shiprah, waterfront.getShomareh()*10000, map, waterfront,bigfishs,smallfishs,startTime,winterTable));
					waterfront.jangi.addElement(new Jangi((waterfront.getX())*50, ( waterfront.getY()+1)*50, shiprah, 100000*waterfront.getShomareh(), waterfronts, waterfront,startTime,winterTable));

				}
				
				else if(map.getLocations()[waterfront.getX()][waterfront.getY()-1].getD1()!=3){
				
					
					waterfront.mahi.addElement(new Mahigir(waterfront.getX()*50,( waterfront.getY()-1)*50, shiprah, waterfront.getShomareh()*10000, map, waterfront,bigfishs,smallfishs,startTime,winterTable));
					waterfront.jangi.addElement(new Jangi((waterfront.getX())*50, ( waterfront.getY()-1)*50, shiprah, 100000*waterfront.getShomareh(), waterfronts, waterfront,startTime,winterTable));

				}
			
		}
		//@Override
		public void run() {

			while(true){
				synchronized (this) {
					if(waterfront.getAdams().size()<14)
						waterfront.makeadam();

				}
			if(khoshshans){
					if(ischoob){
				int najjar=0;
				for(int i=0;i<waterfront.getAdams().size();i++)
					if(waterfront.getAdams().elementAt(i).getDuty()==2)
						najjar++;
		
				if(najjar<=3){
					int d=najjar;
						for(int j=0;j<waterfront.getAdams().size();j++){
							if((waterfront.getAdams().elementAt(j).getDuty()==0||waterfront.getAdams().elementAt(j).getDuty()==10000)&&!waterfront.getAdams().elementAt(j).isinkeshti&&waterfront.getAdams().elementAt(j).jazirenumber==waterfront.jazirenumber){
								d++;
								try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							
								waterfront.getAdams().elementAt(j).setDuty(2);
								for(int z=0;z<trees.size();z++)
									if(trees.elementAt(z).jazirenumber==waterfront.jazirenumber)
										waterfront.getAdams().elementAt(j).setmaghsad(trees.elementAt(z).getX(), trees.elementAt(z).getY());
								
							}
						if(d==4)
							break;
				}	
				}
			}
					if(isiron){
						int madankar=0;
						for(int i=0;i<waterfront.getAdams().size();i++)
							if(waterfront.getAdams().elementAt(i).getDuty()==6)
								madankar++;
					
						if(madankar<=3){
							int d=madankar;
								for(int j=0;j<waterfront.getAdams().size();j++){
									if((waterfront.getAdams().elementAt(j).getDuty()==0||waterfront.getAdams().elementAt(j).getDuty()==10000)&&!waterfront.getAdams().elementAt(j).isinkeshti&&waterfront.getAdams().elementAt(j).jazirenumber==waterfront.jazirenumber){
										d++;
										try {
											Thread.sleep(200);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									
										waterfront.getAdams().elementAt(j).setDuty(6);
										for(int z=0;z<irons.size();z++)
											if(irons.elementAt(z).jazirenumber==waterfront.jazirenumber)
												waterfront.getAdams().elementAt(j).setmaghsad(irons.elementAt(z).getX(), irons.elementAt(z).getY());
										
									}
								if(d==4)
									break;
						}	
						}
					}
			}
					if(waterfront.bars.size()==0)
						makebari();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int s=0;
					for(int i=0;i<waterfront.getAdams().size();i++)
						if(waterfront.getAdams().elementAt(i).jazirenumber!=waterfront.jazirenumber)
							s++;
					if(waterfront.bars.lastElement().jazirenumber==waterfront.jazirenumber)
						
					//	int s=5-waterfront.bars.lastElement().human.size();
						for(int i=0;i<5-s;i++){
							sendadam();
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
//					
					if(waterfront.bars.lastElement().human.size()==5&&waterfront.bars.lastElement().jazirenumber==waterfront.jazirenumber){
												sendbari();
												try {
													Thread.sleep(100);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
					}
					if(waterfront.bars.lastElement().jazirenumber==waterfronts.elementAt(0).jazirenumber)
						receiveadam();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					
					if((waterfront.bars.lastElement().getX()/50-waterfronts.elementAt(0).x==1||waterfront.bars.lastElement().getX()/50-waterfronts.elementAt(0).x==0||waterfront.bars.lastElement().getX()/50-waterfronts.elementAt(0).x==-1)&&(waterfront.bars.lastElement().getY()/50-waterfronts.elementAt(0).y==1||waterfront.bars.lastElement().getY()/50-waterfronts.elementAt(0).y==0||waterfront.bars.lastElement().getY()/50-waterfronts.elementAt(0).y==-1)&&waterfront.bars.lastElement().human.size()==0)
					receivebari();
					if(waterfront.jangi.size()<=1)
						makejangi();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(waterfront.jangi.size()<=2)
						sendjangi();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if((waterfront.jangi.lastElement().x/50-waterfronts.elementAt(0).x==1||waterfront.jangi.lastElement().x/50-waterfronts.elementAt(0).x==0||waterfront.jangi.lastElement().x/50-waterfronts.elementAt(0).x==-1)&&(waterfront.jangi.lastElement().y/50-waterfronts.elementAt(0).y==1||waterfront.jangi.lastElement().y/50-waterfronts.elementAt(0).y==0||waterfront.jangi.lastElement().y/50-waterfronts.elementAt(0).y==-1)){
						receivejangi();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					

					
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
//			if(khoshshans==false){
//					if(map.getLocations()[waterfront.getX()+1][waterfront.getY()].getD1()!=3)
//					
//					waterfront.bars.addElement(new Bar((waterfront.getX()+1)*50,waterfront.getY()*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber));
//				else if(map.getLocations()[waterfront.getX()-1][waterfront.getY()].getD1()!=3)
//					
//				waterfront.bars.addElement(new Bar((waterfront.getX()-1)*50,waterfront.getY()*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber));
//	
//				else if(map.getLocations()[waterfront.getX()][waterfront.getY()+1].getD1()!=3)
//					
//					waterfront.bars.addElement(new Bar(waterfront.getX()*50,(waterfront.getY()+1)*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber));
//				else if(map.getLocations()[waterfront.getX()][waterfront.getY()-1].getD1()!=3)
//				
//					
//					waterfront.bars.addElement(new Bar(waterfront.getX()*50,(waterfront.getY()-1)*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber));
//					
//					
//					for(int i=0;i<5;i++){
//						if(map.locations[waterfront.bars.lastElement().x/50+1][ waterfront.bars.lastElement().y/50].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50+1, waterfront.bars.lastElement().y/50);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						}
//									
//					if(map.locations[waterfront.bars.lastElement().x/50-1][ waterfront.bars.lastElement().y/50].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50-1, waterfront.bars.lastElement().y/50);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						
//					}
//						if(map.locations[waterfront.bars.lastElement().x/50][ waterfront.bars.lastElement().y/50+1].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50, waterfront.bars.lastElement().y/50+1);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						
//					}
//						if(map.locations[waterfront.bars.lastElement().x/50][ waterfront.bars.lastElement().y/50-1].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50, waterfront.bars.lastElement().y/50-1);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						
//					}
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//						
//					} 
//					
//					
//					
//					
//					
//					if(map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x+1,waterfronts.elementAt(0).y);
//					
//					else if(map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x-1,waterfronts.elementAt(0).y);
//					
//					else if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y+1);
//					
//					else if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y-1);
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					
//					
//
//					for(int i=0;i<waterfront.getAdams().size();i++){
//						if(map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].D1==3){
//							
//							waterfront.getAdams().elementAt(i).setDuty(1);
//							waterfront.getAdams().elementAt(i).setmaghsad(waterfronts.elementAt(0).x+1,waterfronts.elementAt(0).y);
//							waterfront.getAdams().elementAt(i).jazirenumber=map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].jazire;
//							waterfront.getAdams().elementAt(i).isinkeshti=false;
//							waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
//							waterfront.bars.lastElement().human.removeElementAt(0);
//						}
//						else if(map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(1);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfronts.elementAt(0).x-1,waterfronts.elementAt(0).y);
//						waterfront.getAdams().elementAt(i).jazirenumber=map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].jazire;
//						waterfront.getAdams().elementAt(i).isinkeshti=false;
//						waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
//						waterfront.bars.lastElement().human.removeElementAt(0);
//					}
//					    else  if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(1);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y+1);
//						waterfront.getAdams().elementAt(i).jazirenumber=map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].jazire;
//						waterfront.getAdams().elementAt(i).isinkeshti=false;
//						waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
//						waterfront.bars.lastElement().human.removeElementAt(0);
//					}
//					    else  if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].D1==3){
//							
//						waterfront.getAdams().elementAt(i).setDuty(1);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y-1);
//						waterfront.getAdams().elementAt(i).jazirenumber=map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].jazire;
//						waterfront.getAdams().elementAt(i).isinkeshti=false;
//						waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
//						waterfront.bars.lastElement().human.removeElementAt(0);
//					}
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//
//					
//					}
//					
//					
//					for(int i=0;i<waterfront.getAdams().size();i++){
//						if(map.locations[waterfront.bars.lastElement().x/50+1][ waterfront.bars.lastElement().y/50].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50+1, waterfront.bars.lastElement().y/50);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						}
//									
//					if(map.locations[waterfront.bars.lastElement().x/50-1][ waterfront.bars.lastElement().y/50].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50-1, waterfront.bars.lastElement().y/50);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						
//					}
//						if(map.locations[waterfront.bars.lastElement().x/50][ waterfront.bars.lastElement().y/50+1].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50, waterfront.bars.lastElement().y/50+1);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						
//					}
//						if(map.locations[waterfront.bars.lastElement().x/50][ waterfront.bars.lastElement().y/50-1].D1==3){
//						
//						waterfront.getAdams().elementAt(i).setDuty(3);
//						waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50, waterfront.bars.lastElement().y/50-1);
//						waterfront.getAdams().elementAt(i).isinkeshti=true;
//						
//					}
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//						
//					} 
//			}
//					
					
//					if(map.locations[waterfront.x+1][waterfront.y].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfront.x+1,waterfront.y);
//					
//					else if(map.locations[waterfront.x-1][waterfront.y].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfront.x-1,waterfront.y);
//					
//					else if(map.locations[waterfront.x][waterfront.y+1].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfront.x,waterfront.y+1);
//					
//					else if(map.locations[waterfront.x][waterfront.y-1].D1!=3)
//						
//						waterfront.bars.lastElement().setmaghsad(waterfront.x,waterfront.y-1);
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					

					

					
			}
					
					

					
				
			
				
				
						
				
				
				
			
			
		}
//	public synchronized void makeadam(){
//		for(int i=0;i<14-waterfront.getAdams().size();i++){
//			int size=waterfront.getAdams().size();
//			if(map.getLocations()[waterfront.getX()+1][waterfront.getY()].getD1()==3)
//				
//				waterfront.getAdams().addElement(new Human((waterfront.getX()+1)*50,waterfront.getY()*50, rah,waterfront.getShomareh()*100+size ,waterfront,trees,irons,waterfront.bars,waterfronts,startTime,waterfront.jazirenumber,gdp));
//			
//			else if(map.getLocations()[waterfront.getX()-1][waterfront.getY()].getD1()==3)
//				
//				waterfront.getAdams().addElement(new Human((waterfront.getX()-1)*50,waterfront.getY()*50,rah,waterfront.getShomareh()*100+size ,waterfront,trees,irons,waterfront.bars,waterfronts,startTime,waterfront.jazirenumber,gdp));
//
//			else if(map.getLocations()[waterfront.getX()][waterfront.getY()+1].getD1()==3)
//			    
//				waterfront.getAdams().addElement(new Human(waterfront.getX()*50,(waterfront.getY()+1)*50, rah,waterfront.getShomareh()*100+size ,waterfront,trees,irons,waterfront.bars,waterfronts,startTime,waterfront.jazirenumber,gdp));
//
//			else if(map.getLocations()[waterfront.getX()][waterfront.getY()-1].getD1()==3)
//				
//				waterfront.getAdams().addElement(new Human(waterfront.getX()*50,(waterfront.getY()-1)*50,rah,waterfront.getShomareh()*100+size ,waterfront,trees,irons,waterfront.bars,waterfronts,startTime,waterfront.jazirenumber,gdp));
//			try {
//				Thread.sleep(300);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//		
//	}
	
	public void makebari(){

		if(map.getLocations()[waterfront.getX()+1][waterfront.getY()].getD1()!=3)
		
		waterfront.bars.addElement(new Bar((waterfront.getX()+1)*50,waterfront.getY()*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber,startTime,winterTable));
	else if(map.getLocations()[waterfront.getX()-1][waterfront.getY()].getD1()!=3)
		
	waterfront.bars.addElement(new Bar((waterfront.getX()-1)*50,waterfront.getY()*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber,startTime,winterTable));

	else if(map.getLocations()[waterfront.getX()][waterfront.getY()+1].getD1()!=3)
		
		waterfront.bars.addElement(new Bar(waterfront.getX()*50,(waterfront.getY()+1)*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber,startTime,winterTable));
	else if(map.getLocations()[waterfront.getX()][waterfront.getY()-1].getD1()!=3)
		
		waterfront.bars.addElement(new Bar(waterfront.getX()*50,(waterfront.getY()-1)*50,shiprah,waterfront.getShomareh()*1000,waterfront,map,waterfront.jazirenumber,startTime,winterTable));

	}
	public void  makejangi(){
		if(map.getLocations()[waterfront.getX()+1][waterfront.getY()].getD1()!=3)
			
			waterfront.jangi.addElement(new Jangi((waterfront.getX()+1)*50,  waterfront.getY()*50, shiprah, 100000*waterfront.getShomareh()+idjangi, waterfronts, waterfront,startTime,winterTable));
	else if(map.getLocations()[waterfront.getX()-1][waterfront.getY()].getD1()!=3)
		
		waterfront.jangi.addElement(new Jangi((waterfront.getX()-1)*50,  waterfront.getY()*50, shiprah, 100000*waterfront.getShomareh()+idjangi, waterfronts, waterfront,startTime,winterTable));

	else if(map.getLocations()[waterfront.getX()][waterfront.getY()+1].getD1()!=3)
		
		waterfront.jangi.addElement(new Jangi((waterfront.getX())*50, ( waterfront.getY()+1)*50, shiprah, 100000*waterfront.getShomareh()+idjangi, waterfronts, waterfront,startTime,winterTable));
	else if(map.getLocations()[waterfront.getX()][waterfront.getY()-1].getD1()!=3)
		
		waterfront.jangi.addElement(new Jangi((waterfront.getX())*50, ( waterfront.getY()-1)*50, shiprah, 100000*waterfront.getShomareh()+idjangi, waterfronts, waterfront,startTime,winterTable));


		idjangi++;
		
		
		
	}
	public void sendbari(){
		if(waterfront.bars.size()!=0){
		if(map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].D1!=3)
			
		waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x+1,waterfronts.elementAt(0).y);
	
	else if(map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x-1,waterfronts.elementAt(0).y);
	
	else if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y+1);
	
	else if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y-1);
	}

	}
	public void sendjangi(){
		if(waterfront.jangi.size()>=1){
		if(map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].D1!=3)
			
		waterfront.jangi.lastElement().setmaghsad(waterfronts.elementAt(0).x+1,waterfronts.elementAt(0).y);
	
	else if(map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfronts.elementAt(0).x-1,waterfronts.elementAt(0).y);
	
	else if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y+1);
	
	else if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y-1);
	}

	}

	public void receivebari(){
		if(waterfront.bars.size()!=0){

		
		if(map.locations[waterfront.x+1][waterfront.y].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfront.x+1,waterfront.y);
	
	else if(map.locations[waterfront.x-1][waterfront.y].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfront.x-1,waterfront.y);
	
	else if(map.locations[waterfront.x][waterfront.y+1].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfront.x,waterfront.y+1);
	
	else if(map.locations[waterfront.x][waterfront.y-1].D1!=3)
		
		waterfront.bars.lastElement().setmaghsad(waterfront.x,waterfront.y-1);
	}
	}
	public void receivejangi(){
		if(waterfront.jangi.size()>1){

			
		if(map.locations[waterfront.x+1][waterfront.y].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfront.x+1,waterfront.y);
	
	else if(map.locations[waterfront.x-1][waterfront.y].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfront.x-1,waterfront.y);
	
	else if(map.locations[waterfront.x][waterfront.y+1].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfront.x,waterfront.y+1);
	
	else if(map.locations[waterfront.x][waterfront.y-1].D1!=3)
		
		waterfront.jangi.lastElement().setmaghsad(waterfront.x,waterfront.y-1);
	}

		
	}
	public void sendadam(){
		int d=gdp.selectid;
		
		if(map.locations[waterfront.bars.lastElement().x/50+1][ waterfront.bars.lastElement().y/50].D1==3&&waterfront.bars.lastElement().jazirenumber==waterfront.jazirenumber){
			for(int i=0;i<waterfront.getAdams().size();i++)
				if((waterfront.getAdams().elementAt(i).getDuty()==0||waterfront.getAdams().elementAt(i).getDuty()==10000)&&!waterfront.getAdams().elementAt(i).isinkeshti&&waterfront.jazirenumber==waterfront.getAdams().elementAt(i).jazirenumber){
		
		waterfront.getAdams().elementAt(i).setDuty(3);
		waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50+1, waterfront.bars.lastElement().y/50);
		break;
				}
		}
					
		else if(map.locations[waterfront.bars.lastElement().x/50-1][ waterfront.bars.lastElement().y/50].D1==3&&waterfront.bars.lastElement().jazirenumber==waterfront.jazirenumber){
		for(int i=0;i<waterfront.getAdams().size();i++)
			if((waterfront.getAdams().elementAt(i).getDuty()==0||waterfront.getAdams().elementAt(i).getDuty()==10000)&&!waterfront.getAdams().elementAt(i).isinkeshti&&waterfront.jazirenumber==waterfront.getAdams().elementAt(i).jazirenumber){

		waterfront.getAdams().elementAt(i).setDuty(3);
		waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50-1, waterfront.bars.lastElement().y/50);
		break;
			}
	}
		else	if(map.locations[waterfront.bars.lastElement().x/50][ waterfront.bars.lastElement().y/50+1].D1==3&&waterfront.bars.lastElement().jazirenumber==waterfront.jazirenumber){
			for(int i=0;i<waterfront.getAdams().size();i++)
				if((waterfront.getAdams().elementAt(i).getDuty()==0||waterfront.getAdams().elementAt(i).getDuty()==10000)&&!waterfront.getAdams().elementAt(i).isinkeshti&&waterfront.jazirenumber==waterfront.getAdams().elementAt(i).jazirenumber){

		waterfront.getAdams().elementAt(i).setDuty(3);
		waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50, waterfront.bars.lastElement().y/50+1);
		break;
				}	
	}
		else	if(map.locations[waterfront.bars.lastElement().x/50][ waterfront.bars.lastElement().y/50-1].D1==3&&waterfront.bars.lastElement().jazirenumber==waterfront.jazirenumber){
			for(int i=0;i<waterfront.getAdams().size();i++)
				if((waterfront.getAdams().elementAt(i).getDuty()==0||waterfront.getAdams().elementAt(i).getDuty()==10000)&&!waterfront.getAdams().elementAt(i).isinkeshti&&waterfront.jazirenumber==waterfront.getAdams().elementAt(i).jazirenumber){

		waterfront.getAdams().elementAt(i).setDuty(3);
		waterfront.getAdams().elementAt(i).setmaghsad(waterfront.bars.lastElement().x/50, waterfront.bars.lastElement().y/50-1);
		break;
				}
	}

	}
	public void receiveadam(){
		for(int i=0;i<waterfront.bars.lastElement().human.size();i++){
		if(map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].D1==3){
			
			waterfront.bars.lastElement().human.elementAt(0).setDuty(1);
			waterfront.bars.lastElement().human.elementAt(0).setmaghsad(waterfronts.elementAt(0).x+1,waterfronts.elementAt(0).y);
			waterfront.bars.lastElement().human.elementAt(0).jazirenumber=map.locations[waterfronts.elementAt(0).x+1][waterfronts.elementAt(0).y].jazire;
			waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
			waterfront.bars.lastElement().human.removeElementAt(0);
		}
		else if(map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].D1==3){
		
			waterfront.bars.lastElement().human.elementAt(0).setDuty(1);
		waterfront.bars.lastElement().human.elementAt(0).setmaghsad(waterfronts.elementAt(0).x-1,waterfronts.elementAt(0).y);
		waterfront.bars.lastElement().human.elementAt(0).jazirenumber=map.locations[waterfronts.elementAt(0).x-1][waterfronts.elementAt(0).y].jazire;
		waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
		waterfront.bars.lastElement().human.removeElementAt(0);
	}
	    else  if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].D1==3){
		
			waterfront.bars.lastElement().human.elementAt(0).setDuty(1);
		waterfront.bars.lastElement().human.elementAt(0).setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y+1);
		waterfront.bars.lastElement().human.elementAt(0).jazirenumber=map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y+1].jazire;
		waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
		waterfront.bars.lastElement().human.removeElementAt(0);
	}
	    else  if(map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].D1==3){
			
			waterfront.bars.lastElement().human.elementAt(0).setDuty(1);
		waterfront.bars.lastElement().human.elementAt(0).setmaghsad(waterfronts.elementAt(0).x,waterfronts.elementAt(0).y-1);
		waterfront.bars.lastElement().human.elementAt(0).jazirenumber=map.locations[waterfronts.elementAt(0).x][waterfronts.elementAt(0).y-1].jazire;
		waterfront.bars.lastElement().human.elementAt(0).isinkeshti=false;
		waterfront.bars.lastElement().human.removeElementAt(0);
	}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


		
		
	

}





}











