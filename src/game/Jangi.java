package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import objects.Waterfront;

public class Jangi implements Runnable{ 
	Vector<Waterfront>waterfronts=new Vector<Waterfront>();
	Waterfront waterfront;
	int maghsadx;
	int maghsady;
	Vector<String> masir = new Vector<String>();
	int i=0;
	private int health=1000;
	int[][]table;
	 int x,y;
	 boolean harkat=false;
	int blockx,blocky;
	int id;
	
	
	BufferedImage nowImage;
	BufferedImage[] going;
	int dir;

	int startTime;
	int[][] winterTable;
	
	
	public Jangi(int x,int y,int[][]table,int id,Vector<Waterfront>waterfronts,Waterfront waterfront,int startTime,int[][]winterTable){
	     this.x=x;
	     this.y=y;
	     this.table=table;
	     this.id=id;
	     this.waterfront=waterfront;
	     this.waterfronts=waterfronts;
	     
			maghsadx=x/50;
			maghsady=y/50;
			

	     new Thread(this).start();
	     
	     this.startTime=startTime;
	     this.winterTable=winterTable;
	     
	     going = new BufferedImage[8];
			for (int i = 0; i < 8; i++) {
				try {
					going[i] = ImageIO.read(new File(
							"pic\\ship\\shipJangi\\schwimmt000" + i + ".png"));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			nowImage = going[0];
	     
	     
	}
	public void setmaghsad(int maghsadx,int maghsady){
		this.maghsadx=maghsadx;
		this.maghsady=maghsady;

	}
	public void setmasir(Vector<String> s) {
		masir = s;
		i=0;
		if(masir.size()==0)
			harkat=false;
		else
			harkat=true;
		
	}

	public void harkatasli(){

		for ( i = 0; i < masir.size(); i++) {
			dir=0;
			nowImage=going[dir];
			if (masir.elementAt(i).equals("R")) {
				for(int j=0;j<10;j++){
					x += 50/10;
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
				dir=4;
				nowImage=going[dir];
				for(int j=0;j<5;j++){
					x -= 10;
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
				nowImage=going[dir];
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
				nowImage=going[dir];
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
					nowImage=going[dir];
				for (int j = 0; j < 50 / 10; j++) {
					x-=10;

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
					nowImage=going[dir];
				for (int j = 0; j < 50 / 10; j++) {
					x-=10;
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
					nowImage=going[dir];
				for (int j = 0; j < 50 / 10; j++) {
					x+=10;
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
					nowImage=going[dir];
				for (int j = 0; j < 50 / 10; j++) {
					x+=10;
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){ 

			for(int j=0;j<waterfronts.size();j++){

				if(Math.sqrt((waterfronts.elementAt(j).x-x/50)*(waterfronts.elementAt(j).x-x/50)+(waterfronts.elementAt(j).y-y/50)*(waterfronts.elementAt(j).y-y/50))<=8&&waterfronts.elementAt(j).getShomareh()!=waterfront.getShomareh()){
					waterfronts.elementAt(j).setHealth(	waterfronts.elementAt(j).getHealth()-1);
				}
				for(int k=0;k<waterfronts.elementAt(j).bars.size();k++)
					if(Math.sqrt((waterfronts.elementAt(j).bars.elementAt(k).getX()/50-x/50)*(waterfronts.elementAt(j).bars.elementAt(k).getX()/50-x/50)+(waterfronts.elementAt(j).bars.elementAt(k).getY()/50-y/50)*(waterfronts.elementAt(j).bars.elementAt(k).getY()/50-y/50))<=8&&waterfronts.elementAt(j).getShomareh()!=waterfront.getShomareh())
						waterfronts.elementAt(j).bars.elementAt(k).setHealth(	waterfronts.elementAt(j).bars.elementAt(k).getHealth()-1);
				for(int h=0;h<waterfronts.elementAt(j).mahi.size();h++)
					if(Math.sqrt((waterfronts.elementAt(j).mahi.elementAt(h).getX()/50-x/50)*(waterfronts.elementAt(j).mahi.elementAt(h).getX()/50-x/50)+(waterfronts.elementAt(j).mahi.elementAt(h).getY()/50-y/50)*(waterfronts.elementAt(j).mahi.elementAt(h).getY()/50-y/50))<=8&&waterfronts.elementAt(j).getShomareh()!=waterfront.getShomareh())
						waterfronts.elementAt(j).mahi.elementAt(h).setHealth(	waterfronts.elementAt(j).mahi.elementAt(h).getHealth()-1);
					for(int w=0;w<waterfronts.elementAt(j).jangi.size();w++)	
						if(Math.sqrt((waterfronts.elementAt(j).jangi.elementAt(w).x/50-x/50)*(waterfronts.elementAt(j).jangi.elementAt(w).x/50-x/50)+(waterfronts.elementAt(j).jangi.elementAt(w).y/50-y/50)*(waterfronts.elementAt(j).jangi.elementAt(w).y/50-y/50))<=8&&waterfronts.elementAt(j).getShomareh()!=waterfront.getShomareh())
							waterfronts.elementAt(j).jangi.elementAt(w).setHealth(	waterfronts.elementAt(j).jangi.elementAt(w).getHealth()-1);

			
				
			}
		vazifeh();
		harkat = false;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	


	
  
}
