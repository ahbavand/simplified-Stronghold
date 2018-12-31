package objects;


import game.FindRout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import objects.Human;
import objects.Waterfront;
import projeh.Map;
public class Bar implements Runnable {
	private int zarfiat;
	private int gonjayesh;
	public int id;
	public int jazirenumber;
	private int health=1000;
	Waterfront eskeleh;
	int i=0;
	 Map map;
	 boolean harkat=false;
	Vector<String> masir = new Vector<String>();
	public Vector<Human> human=new Vector<Human>();
	int maghsadx;
	int maghsady;
	int[][]table;
	public int x;
//	int blockx;
//	int blocky;
	public int y;
	
	private BufferedImage nowImage;
	BufferedImage[] going;
	int dir;
	
	private int woodSize;
	private int ironSize;
	private int Humanapacity;
	
	int startTime;
	int[][] winterTable;
	
	public Bar(int x,int y,int[][] table,int id,Waterfront eskeleh,Map map,int jazirenumber,int startTime,int [][]winterTable){
		this.map=map;
		this.id=id;
		this.eskeleh=eskeleh;
		this.jazirenumber=jazirenumber;
		this.table=table;
		gonjayesh=500;
		this.x=x;
		this.y=y;
		maghsadx=x/50;
		maghsady=y/50;
		
		this.startTime=startTime;
		this.winterTable=winterTable;
		going = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			try {
				going[i] = ImageIO.read(new File(
						"pic\\ship\\shipBar\\"+eskeleh.getShomareh()%3+ "\\schiff100" + i + ".png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setNowImage(going[0]);
		
		
		
		new Thread(this).start();

	}

	public int getZarfiat() {
		return zarfiat;
	}
	
	

	public void setZarfiat(int zarfiat) {
		this.zarfiat = zarfiat;
	}
	
	
	public void setmaghsad(int maghsadx,int maghsady){
		this.maghsadx=maghsadx;
		this.maghsady=maghsady;

	}

	
	

	public void harkatasli(){
		this.jazirenumber=0;////yani inke in keshti ya dar hale harkat ast ya kollan vasat ab ast

		for ( i = 0; i < masir.size(); i++) {
			if (masir.elementAt(i).equals("R")) {
				dir=0;
				setNowImage(going[dir]);
				for(int j=0;j<10;j++){
					x += 50/10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setX(human.elementAt(s).getX()+ 50/10);

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
//				blockx++;
				
			}
			if(harkat==false){
				break;
			}
			if (masir.elementAt(i).equals("L")) {
				dir=4;
				setNowImage(going[dir]);
				for(int j=0;j<5;j++){
					x -= 10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setX(human.elementAt(s).getX()-10);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
//				blockx--;
//
				
			}
			if(harkat==false){
//				blockx++;
				break;
			}
			if (masir.elementAt(i).equals("U")) {
				dir=2;
				setNowImage(going[dir]);
				for(int j=0;j<5;j++){
					y -=10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setY(human.elementAt(s).getY()-10);

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
	//			blocky--;

			}
			if(harkat==false){
	//			blocky++;
				break;
			}
			if (masir.elementAt(i).equals("D")) {
				dir=6;
				setNowImage(going[dir]);
				for(int j=0;j<5;j++){
					y += 10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setY(human.elementAt(s).getY()+10);

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
	//			blocky++;

			}
			 if (masir.elementAt(i).equals("LU")) {
				 dir=3;
					setNowImage(going[dir]);
				for (int j = 0; j < 50 / 10; j++) {
					x-=10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setX(human.elementAt(s).getX()-10);

					y-=10;
							for(int s=0;s<human.size();s++)
								human.elementAt(s).setY(human.elementAt(s).getY()-10);
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
					x-=10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setX(human.elementAt(s).getX()-10);

					y+=10;
							for(int s=0;s<human.size();s++)
								human.elementAt(s).setY(human.elementAt(s).getY()+10);
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
					x+=10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setX(human.elementAt(s).getX()+10);

					y-=10;
							for(int s=0;s<human.size();s++)
								human.elementAt(s).setY(human.elementAt(s).getY()-10);
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
					x+=10;
					for(int s=0;s<human.size();s++)
						human.elementAt(s).setX(human.elementAt(s).getX()+10);

					y+=10;
							for(int s=0;s<human.size();s++)
								human.elementAt(s).setY(human.elementAt(s).getY()+10);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							


				}

		}




		}
		try {
			if(map.getLocations()[x/50+1][y/50].getD1()==3)
				this.jazirenumber=map.getLocations()[x/50+1][y/50].jazire;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try{
			 if(map.getLocations()[x/50-1][y/50].getD1()==3)
				this.jazirenumber=map.getLocations()[x/50-1][y/50].jazire;
		}
	 catch (Exception e) {
		// TODO: handle exception
	}
		try{
			 if(map.getLocations()[x/50][y/50+1].getD1()==3)
				this.jazirenumber=map.getLocations()[x/50][y/50+1].jazire;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try{

			 if(map.getLocations()[x/50][y/50-1].getD1()==3)
				this.jazirenumber=map.getLocations()[x/50][y/50-1].jazire;
			 
			

			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		if(jazirenumber==eskeleh.jazirenumber){
			eskeleh.setwoodSize(eskeleh.getwoodSize()+woodSize);
			woodSize=0;
			eskeleh.setIronSize(eskeleh.getIronSize()+ironSize);
			ironSize=0;
		}

			
	}
	public void vazifeh(){
		int season = (int) (((System.currentTimeMillis()/1000-startTime)/20)%4);
		FindRout f;
		if(season!=3)
		 f=new FindRout(table.length, table[0].length, table);
		else
			 f=new FindRout(table.length, table[0].length, winterTable);
		try {
			setmasir(f.findRout(x/50,y/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
	}
	
	
	
	public void setmasir(Vector<String> s) {
		masir = s;
		i=0;
		if(masir.size()==0)
			harkat=false;
		else
			harkat=true;
		
	}

	@Override
	public void run() {
		while(true){
		vazifeh();
		harkat = false;
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		// TODO Auto-generated method stub
		
	}

	public boolean isHarkat() {
		return harkat;
	}

	public void setHarkat(boolean harkat) {
		this.harkat = harkat;
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

	public int getJazirenumber() {
		return jazirenumber;
	}

	public void setJazirenumber(int jazirenumber) {
		this.jazirenumber = jazirenumber;
	}

	public int getWoodSize() {
		return woodSize;
	}

	public synchronized int setWoodSize(int woodSize) {
	//	this.woodSize = woodSize;
		if(gonjayesh-ironSize-this.woodSize-human.size()*100>=woodSize){

			this.woodSize=this.woodSize+woodSize;
		return 0;}
		else{
			int temp=woodSize-(gonjayesh-ironSize-this.woodSize-human.size()*100);
			
			this.woodSize=gonjayesh-ironSize-human.size()*100;
			return temp;
		}
			
		
		
		
	}


	public int getIronSize() {
		return ironSize;
	}

	public synchronized int setIronSize(int ironSize) {
	//	this.ironSize = ironSize;
		if(gonjayesh-this.ironSize-woodSize-human.size()*100>=ironSize){
			this.ironSize=this.ironSize+ironSize;
		return 0;}
		else{
			int temp=ironSize-(gonjayesh-this.ironSize-woodSize-human.size()*100);
			this.ironSize=gonjayesh-woodSize-human.size()*100;
			return temp;
		}
			

	}
	


	public int getHumanapacity() {
		return Humanapacity;
	}

	public void setHumanapacity(int humanapacity) {
		Humanapacity = humanapacity;
	}

	public BufferedImage getNowImage() {
		return nowImage;
	}

	public void setNowImage(BufferedImage nowImage) {
		this.nowImage = nowImage;
	}

	public synchronized int getHealth() {
		return health;
	}

	public synchronized void  setHealth(int health) {
		this.health = health;
	}
	public synchronized int getkhali(){
		
		return(gonjayesh-ironSize-woodSize-human.size()*100);
	}

	
	
}

