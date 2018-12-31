package objects;


import game.FindRout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;



import objects.Bigfish;
import objects.SmallFish;
import objects.Waterfront;
import projeh.Map;
public class Mahigir implements Runnable {	Map map;
	boolean harkat=false;
	Vector<String> masir = new Vector<String>();
	int maghsadx;
	int gonjayesh=300;
	int ispor=0;
	int maghsady;
	private int health;
	int[][]table;
	Vector<Bigfish>big;
	Vector<SmallFish>small;
	int what=0;////hichentekhabinist=0   mikhahim az big bardaraim=1  mikhahim az small bardarim=2
	int kodamkhane=0;
	 private int x;
	int y;
	 int originalx,originaly;/////x,y ke bayad hamvare dar an langar begirad
	 int minfasele=100000;////minimom fasele az yek manbae ghazi
	 int s;
	 int i=0;
	int blockx;
	int blocky;
	Waterfront eskeleh;
	int id;
	
	BufferedImage[] going;
	private BufferedImage nowImage;
	int dir;
	
	int startTime;
	int[][]winterTable;
	
	
	public Mahigir(int x,int y,int[][] table,int id,Map map,Waterfront eskeleh,Vector<Bigfish>big,Vector<SmallFish>small,int startTime,int[][]winterTable){
		this.setX(x);
		originalx=x/50;
		this.y=y;
		originaly=y/50;
		this.table=table;
		this.id=id;
		this.map=map;
		this.big=big;
		this.small=small;
		this.eskeleh=eskeleh;
		maghsadx=x/50;
		maghsady=y/50;
//		int faseleazeskeleh=0;
		new Thread(this).start();
		
		
		this.startTime=startTime;
		this.winterTable=winterTable;
		
		going = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			try {
				going[i] = ImageIO.read(new File(
						"pic\\ship\\shipMahi\\0\\wschiff fahrt00" + i + ".png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dir=0;
		setNowImage(going[0]);
		
	}

	
	
	public void harkatasli(){

		for ( i = 0; i < masir.size(); i++) {
			if (masir.elementAt(i).equals("R")) {
				dir=0;
				setNowImage(going[dir]);
				for(int j=0;j<10;j++){
					setX(getX() + 50/10);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				blockx++;
				
			}
			if(harkat==false){
				break;
			}
			if (masir.elementAt(i).equals("L")) {
				for(int j=0;j<5;j++){
					dir=4;
					setNowImage(going[dir]);
					setX(getX() - 10);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				blockx--;

				
			}
			if(harkat==false){
				blockx++;
				break;
			}
			if (masir.elementAt(i).equals("U")) {
				dir=2;
				setNowImage(going[dir]);
				for(int j=0;j<5;j++){
					y -=10;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				blocky--;


			}
			if(harkat==false){
				blocky++;
				break;
			}
			if (masir.elementAt(i).equals("D")) {
				dir=6;
				setNowImage(going[dir]);
				for(int j=0;j<5;j++){
					y += 10;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				blocky++;

			}
			if (masir.elementAt(i).equals("LU")) {
				dir=3;
				setNowImage(going[dir]);
				for (int j = 0; j < 50 / 10; j++) {
					setX(getX() - 10);

					y-=10;
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							


				}

		}
			if (masir.elementAt(i).equals("LD")) {
				dir=5;
				setNowImage(going[dir]);
				for (int j = 0; j < 50 / 10; j++) {
					setX(getX() - 10);
					y+=10;
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							


				}

		}
			if (masir.elementAt(i).equals("RU")) {
				dir=1;
				setNowImage(going[dir]);
				for (int j = 0; j < 50 / 10; j++) {
					setX(getX() + 10);
					y-=10;
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							


				}

		}
			if (masir.elementAt(i).equals("RD")) {
				dir=7;
				setNowImage(going[dir]);
				for (int j = 0; j < 50 / 10; j++) {
					setX(getX() + 10);
					y+=10;
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							


				}

		}





		}
		

			
	}
	
	public void setmasir(Vector<String> s) {
		masir = s;
		i=0;
		if(masir.size()==0)
			harkat=false;
		else
			harkat=true;
		
	}

	public void vazifeh(){
		minfasele=1000000;
		what=0;
		for(int j=0;j<big.size();j++){
			 s=(int) Math.sqrt((originalx-big.elementAt(j).x)*(originalx-big.elementAt(j).x)+(originaly-big.elementAt(j).y)*(originaly-big.elementAt(j).y));
			if(s<=minfasele&&big.elementAt(j).getFood()!=0){
				minfasele=s;
				maghsadx=big.elementAt(j).x;
				maghsady=big.elementAt(j).y;
				kodamkhane=j;
				what=1;


			}
		}
		for(int j=0;j<small.size();j++){
			 s=(int) Math.sqrt((originalx-small.elementAt(j).x)*(originalx-small.elementAt(j).x)+(originaly-small.elementAt(j).y)*(originaly-small.elementAt(j).y));
			if(s<=minfasele&&small.elementAt(j).getFood()!=0){
				minfasele=s;
				maghsadx=small.elementAt(j).x;
				maghsady=small.elementAt(j).y;
				kodamkhane=j;
				what=2;

			}
		}
		int season = (int) (((System.currentTimeMillis()/1000-startTime)/20)%4);
		FindRout f;
		if(season!=3)
		 f=new FindRout(table.length, table[0].length, table);
		else
			 f=new FindRout(table.length, table[0].length, winterTable);
		try {
			setmasir(f.findRout(getX()/50,y/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();		
		if(what==1){
		ispor+=big.elementAt(kodamkhane).takefood(gonjayesh-ispor);
		//System.out.println(big.elementAt(kodamkhane).getFood());

		}
		else if(what==2){
		ispor+=small.elementAt(kodamkhane).takefood(gonjayesh-ispor);
		//System.out.println(small.elementAt(kodamkhane).getFood());

	}

		FindRout s=new FindRout(table.length, table[0].length, table);
		try {
			setmasir(s.findRout(getX()/50,y/50,originalx,originaly));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		eskeleh.setFoodsize(eskeleh.getFoodsize()+ispor);
		ispor=0;


		
		
	}
	@Override
	public void run() {
		while(true){
			vazifeh();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		// TODO Auto-generated method stub
		
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



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public BufferedImage getNowImage() {
		return nowImage;
	}



	public void setNowImage(BufferedImage nowImage) {
		this.nowImage = nowImage;
	}



	public synchronized int  getHealth() {
		return health;
	}



	public synchronized void setHealth(int health) {
		this.health = health;
	}



}
