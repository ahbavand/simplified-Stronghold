package objects;

import objects.Tree;
import objects.Waterfront;
import game.FindRout;
import game.GameDisplayPanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Human implements Runnable{
	int startTime;
	private int x;
	private int y;
	int blockx;
	int woodSize=0;
	int ironSize;
	int blocky;
	double speed;
	int health;
	private int activity;
	boolean darjangbood=false;
	int i;
	int [][]table;
	private int duty=0;///1=movee addi 2=movee motenaveb be samte tree 3=movee motenaveb be samte iron
	private boolean move = false;
	Vector<String> rout = new Vector<String>();
	int maghsadx;
	int maghsady;
	Vector<Tree> tree;
	Vector<Iron>iron;
	Waterfront waterfront;
	Vector<Waterfront> waterfronts;
	BufferedImage [][]  running;
	BufferedImage [] stopped;
	public int jazirenumber;
	int gonjayesh=20;
	Vector<Bar> bars=new Vector<Bar>();
	public boolean isinkeshti=false;
	private BufferedImage nowImage;
	
//	JLabel showHealth;
	
	BufferedImage[][] war;
	
	int idImage;
	private int id;
	int dir;
	Thread t;
	GameDisplayPanel gdp;
	public Human(int x, int y,int[][] table ,int id,Waterfront waterfront,Vector<Tree> tree,Vector<Iron>iron,Vector<Bar>bars,Vector<Waterfront> waterfronts,int startTime,int jazirenumber,GameDisplayPanel gdp) {
		this.startTime=startTime;
		this.gdp=gdp;
		this.jazirenumber=jazirenumber;
		this.setX(x);
		this.setY(y);
		blockx = (x / 50) + 1;
		blocky = (y / 50) + 1;
		this.setId(id);
		this.table=table;
		this.waterfront=waterfront;
		this.tree=tree;
		this.iron=iron;
		this.bars=bars;
		this.waterfronts=waterfronts;
		speed=10;
		health=100;
		running = new BufferedImage[8][8];
		war = new BufferedImage[8][8];
		for (int i = 0; i < 8; i++) {
			try {
				running[0][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running e000" + i + ".png"));
				running[1][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running ne000" + i + ".png"));
				running[2][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running n000" + i + ".png"));
				running[3][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running nw000" + i + ".png"));
				running[4][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running w000" + i + ".png"));
				running[5][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running sw000" + i + ".png"));
				running[6][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running s000" + i + ".png"));
				running[7][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\running se000" + i + ".png"));
				war[0][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking e000" + i + " copy.png"));
				war[1][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking ne000" + i + " copy.png"));
				war[2][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking n000" + i + " copy.png"));
				war[3][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking nw000" + i + " copy.png"));
				war[4][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking w000" + i + " copy.png"));
				war[5][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking sw000" + i + " copy.png"));
				war[6][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking s000" + i + " copy.png"));
				war[7][i] = ImageIO.read(new File(
						"pic\\human\\"+waterfront.getShomareh()%3+"\\talking se000" + i + " copy.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		stopped = new BufferedImage[8];
		try {
			stopped[0] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0000.png"));
			stopped[1] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0001.png"));
			stopped[2] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0002.png"));
			stopped[3] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0003.png"));
			stopped[4] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0004.png"));
			stopped[5] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0005.png"));
			stopped[6] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0006.png"));
			stopped[7] = ImageIO.read(new File("pic\\human\\"+waterfront.getShomareh()%3+"\\stopped0007.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		
		setNowImage(running[0][0]);
		idImage = 0;
		dir=0;
		nowImage=stopped[0];
		
		
		
		t = new Thread(this);
		t.start();
	}
	
		
		
	
	@Override
	public void run() {
		while (true) {
			if(!isinkeshti){
			SearchEnemy();
			}
			switch (duty) {
			case 1:
				move=true;

				duty1();
				setMove(false);
				break;
			case 2:
				move=true;

				duty2();
				break;
			case 3:
				if(SearchEnemy()==0){
				move=true;

				duty3();}
				break;
//			case 4:
//				duty4();
//				break;
			case 5:
				move=true;

				duty5();
				break;
			
			case 6:
				move=true;
				duty6();
				break;
			case 7:
				move=true;
				duty7();
				break;
			case 8:
				move=true;
				duty8();
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch ((int)(System.currentTimeMillis()/1000-startTime)/20%4) {
			case 0:
				speed=8;
				break;
			case 1:
				speed=10;
				break;
			case 2:
				speed=5;
				break;
			case 3:
				speed=2;
				break;
			default:
				break;
			}


		}
	}
	public void setrout(Vector<String> s) {
		rout = s;
		i=0;
//		if(rout.size()==0)
//			setMove(false);
//		else
//			setMove(true);
//		
	}
	public void setmaghsad(int maghsadx,int maghsady){
		this.maghsadx=maghsadx;
		this.maghsady=maghsady;

	}
	
	public int SearchEnemy() {
		for (Waterfront w : waterfronts) {
			if (w != waterfront)
				for (Human h : w.getAdams()) {
					if(!(h.isinkeshti)){

					if (Math.abs((h.getX()-x)) < 51  && Math.abs((h.getY()-y))<51) {
						attack(h);
						setDuty(10000);
						return 1;
					}

					else if (Math.abs(h.getX() - x) + Math.abs(h.getY() - y) < 1000) {
						setMove(false);
						setmaghsad(h.getX() / 50, h.getY() / 50);
						setDuty(1);
						return 1;
					}
				}
				}
		}
		return 0;
	}


	public void duty2(){
		FindRout f=new FindRout(table.length, table[0].length, table);
		try {
			setrout(f.findRout(getX()/50,getY()/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		 for(int j=0;j<tree.size();j++){
			 if(tree.elementAt(j).x==getX()/50&&tree.elementAt(j).y==getY()/50){
			//	 woodSize+=tree.elementAt(j).takeWood(gonjayesh-woodSize-ironSize);
				 for(int s=0;s<5;s++){
				//	 woodSize+=tree.elementAt(j).takeWood(meghdar)
					 if((woodSize+ironSize)==gonjayesh)
						 woodSize+=tree.elementAt(j).takeWood(0);
						 else
							woodSize+=tree.elementAt(j).takeWood(4);
					 if(move==false)
						 break;
						 
				 }
				 
				 
				 break;
			 }
		 }

		if(move==false)
			return;
		FindRout s=new FindRout(table.length, table[0].length, table);
		try {
			setrout(s.findRout(getX()/50,getY()/50, waterfront.getX(), waterfront.getY()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		if(move==false)
			return;
		waterfront.setwoodSize(woodSize+waterfront.getwoodSize());
		woodSize=0;
		waterfront.setIronSize(ironSize+waterfront.getIronSize());
		ironSize=0;
		//System.out.println(waterfront.getwoodSize());

		//System.out.println(woodSize+waterfront.getwoodSize());
	}
	public void duty5(){

		FindRout f=new FindRout(table.length, table[0].length, table);
		try {
			setrout(f.findRout(getX()/50,getY()/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		harkatasli();
		 for(int j=0;j<tree.size();j++){
			 if(tree.elementAt(j).x==getX()/50&&tree.elementAt(j).y==getY()/50){
			//	 woodSize+=tree.elementAt(j).takeWood(gonjayesh-woodSize-ironSize);
				 for(int s=0;s<5;s++){
				//	 woodSize+=tree.elementAt(j).takeWood(meghdar)
					 if((woodSize+ironSize)==gonjayesh)
						 woodSize+=tree.elementAt(j).takeWood(0);
						 else
							woodSize+=tree.elementAt(j).takeWood(4);
					 if(move==false)
						 break;

				 }
				 
				 
				 
				 
				 
				 
				 
				 
				 //
				 break;
			 }
		 }


		if(move==false)
			return;

		
		FindRout s=new FindRout(table.length, table[0].length, table);
		for(int j=0;j<bars.size();j++){
			if(bars.elementAt(j).getJazirenumber()==jazirenumber){
				int keshtix=bars.elementAt(j).getX()/50;
				int keshtiy=bars.elementAt(j).getY()/50;
				if(table[keshtix+1][keshtiy]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix+1,keshtiy));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							
							ironSize=bars.elementAt(h).setIronSize(ironSize);


							break;
							

						}
					

				}
				else if(table[keshtix-1][keshtiy]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix-1,keshtiy));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);


							break;

						}
					

				}
				else	if(table[keshtix][keshtiy+1]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix,keshtiy+1));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);


							break;

						}
					


				}
				else	if(table[keshtix][keshtiy-1]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix,keshtiy-1));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);

							break;

						}
					


				}

					
			}
		}
	}
	public void duty6(){
		FindRout f=new FindRout(table.length, table[0].length, table);
		try {
			setrout(f.findRout(getX()/50,getY()/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		 for(int j=0;j<iron.size();j++){
			 if(iron.elementAt(j).x==getX()/50&&iron.elementAt(j).y==getY()/50){
		//		 ironSize+=iron.elementAt(j).takeIron(gonjayesh-woodSize-ironSize);
				 for(int s=0;s<5;s++){
					 if((woodSize+ironSize)==gonjayesh)
						 ironSize+=iron.elementAt(j).takeIron(0);
						 else
							ironSize+=tree.elementAt(j).takeWood(4);
					 if(move==false)
						 break;
				 }
				 break;
			 }
		 }


		if(move==false)
			return;
		FindRout s=new FindRout(table.length, table[0].length, table);
		try {
			setrout(s.findRout(getX()/50,getY()/50, waterfront.getX(), waterfront.getY()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		if(move==false)
			return;

//		waterfront.setIronSize(ironSize+waterfront.getIronSize());
//		ironSize=0;
		waterfront.setwoodSize(woodSize+waterfront.getwoodSize());
		woodSize=0;
		waterfront.setIronSize(ironSize+waterfront.getIronSize());
		ironSize=0;


		//System.out.println(ironSize+waterfront.getIronSize());
	}
	
	
	public void duty7(){
		FindRout f=new FindRout(table.length, table[0].length, table);
		try {
			setrout(f.findRout(getX()/50,getY()/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		if(move==false)
			return;

		 for(int j=0;j<iron.size();j++){
			 if(iron.elementAt(j).x==getX()/50&&iron.elementAt(j).y==getY()/50){
			//	 ironSize+=iron.elementAt(j).takeIron(gonjayesh-woodSize-ironSize);
				 for(int s=0;s<5;s++){
				//	 woodSize+=tree.elementAt(j).takeWood(meghdar)
					 if((woodSize+ironSize)==gonjayesh)
						 ironSize+=iron.elementAt(j).takeIron(0);
						 else
							ironSize+=tree.elementAt(j).takeWood(4);
					 if(move==false)
						 break;
				 }

				 break;
			 }
		 }



		if(move==false)
			return;

		
		FindRout s=new FindRout(table.length, table[0].length, table);
		for(int j=0;j<bars.size();j++){
			if(bars.elementAt(j).getJazirenumber()==jazirenumber){
				int keshtix=bars.elementAt(j).getX()/50;
				int keshtiy=bars.elementAt(j).getY()/50;
				if(table[keshtix+1][keshtiy]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix+1,keshtiy));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);
							break;

						}
					

				}
				else if(table[keshtix-1][keshtiy]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix-1,keshtiy));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);

							break;

						}
					

				}
				else	if(table[keshtix][keshtiy+1]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix,keshtiy+1));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);

							break;

						}
					


				}
				else	if(table[keshtix][keshtiy-1]==1){
					try {
						setrout(s.findRout(getX()/50,getY()/50, keshtix,keshtiy-1));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					harkatasli();
					if(move==false)
						return;

					for(int h=0;h<bars.size();h++)
						if((bars.elementAt(h).getX()/50-x/50==1||bars.elementAt(h).getX()/50-x/50==0||bars.elementAt(h).getX()/50-x/50==-1)&&(bars.elementAt(h).getY()/50-y/50==-1||bars.elementAt(h).getY()/50-y/50==0||bars.elementAt(h).getY()/50-y/50==1)){
							woodSize=bars.elementAt(h).setWoodSize(woodSize);
							ironSize=bars.elementAt(h).setIronSize(ironSize);

							break;

						}
				}
			}
		}
	}
	public void duty8() {
		FindRout s=new FindRout(table.length, table[0].length, table);
		try {
			setrout(s.findRout(getX()/50,getY()/50, waterfront.getX(), waterfront.getY()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		if(move==false)
			return;
		
	}
	public void duty1(){
		FindRout f=new FindRout(table.length, table[0].length, table);
		try {
			setrout(f.findRout(getX()/50,getY()/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		if(move==false)
			return;
		duty=0;
	}
	public void duty3(){/////raftan be keshtie bari
		FindRout f=new FindRout(table.length, table[0].length, table);
		try {
			setrout(f.findRout(getX()/50,getY()/50, maghsadx, maghsady));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		harkatasli();
		for(int j=0;j<bars.size();j++)
			if((bars.elementAt(j).getX()/50-x/50==1||bars.elementAt(j).getX()/50-x/50==0||bars.elementAt(j).getX()/50-x/50==-1)&&(bars.elementAt(j).getY()/50-y/50==1||bars.elementAt(j).getY()/50-y/50==0||bars.elementAt(j).getY()/50-y/50==-1)&&bars.elementAt(j).getkhali()>=100){
				x=bars.elementAt(j).getX();
				
				y=bars.elementAt(j).getY();
				bars.elementAt(j).human.addElement(this);
				if(waterfront.isinhoosh==false)
				gdp.selectid=bars.elementAt(j).getId();
				isinkeshti=true;
				setDuty(0);
				break;
			}
		

		
	}
	public void harkatasli() {
		for (i = 0; i < rout.size(); i++) {
			if (i % 2 == 1 || i % 3 == 1)
				if (SearchEnemy() == 1)
					return;
			switch ((int) (System.currentTimeMillis() / 1000 - startTime) / 20 % 4) {
			case 0:
				speed = 5;
				break;
			case 1:
				speed = 5;
				break;
			case 2:
				speed = 2;
				break;
			case 3:
				speed = 1;
				break;
			default:
				break;
			}

			if (rout.elementAt(i).equals("R")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=0;
					idImage = (idImage + 1) % 8;
					setNowImage(running[0][idImage]);
					x += speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blockx++;
			}
			if(move==false)
				break;
			else if (rout.elementAt(i).equals("L")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=4;
					idImage = (idImage + 1) % 8;
					setNowImage(running[4][idImage]);
					x -= speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blockx--;

			}
			if(move==false)
				break;

			else if (rout.elementAt(i).equals("U")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=2;
					idImage = (idImage + 1) % 8;
					setNowImage(running[2][idImage]);
					y -= speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blocky--;

			}
			if(move==false)
				break;

			else if (rout.elementAt(i).equals("D")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=6;
					idImage = (idImage + 1) % 8;
					setNowImage(running[6][idImage]);
					y += speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blocky++;

			}
			if(move==false)
				break;

			else if (rout.elementAt(i).equals("LU")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=3;
					idImage = (idImage + 1) % 8;
					setNowImage(running[3][idImage]);
					y -= speed;
					x -= speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				blocky--;
				blockx--;
			}
			if(move==false)
				break;

			else if (rout.elementAt(i).equals("LD")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=5;
					idImage = (idImage + 1) % 8;
					setNowImage(running[5][idImage]);
					y += speed;
					x -= speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blocky++;
				blockx--;
			}
			if(move==false)
				break;

			else if (rout.elementAt(i).equals("RU")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=1;
					idImage = (idImage + 1) % 8;
					setNowImage(running[1][idImage]);
					y -= speed;
					x += speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blocky--;
				blockx++;
			}
			if(move==false)
				break;

			else if (rout.elementAt(i).equals("RD")) {
				for (int j = 0; j < 50 / speed; j++) {
					if (i % 2 == 1 || i % 3 == 1)
						if (SearchEnemy() == 1)
							return;
					dir=7;
					idImage = (idImage + 1) % 8;
					setNowImage(running[7][idImage]);
					y += speed;
					x += speed;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				blocky++;
				blockx++;
			}
			
			if(move==false){
				nowImage=stopped[dir];
			}
			// nowImage=stopped[3];
		}
		x=(x/50)*50;
		y=(y/50)*50;
	}


	public void attack(Human h) {
		duty=0;
		idImage= (idImage+1)%8;
		setDirToPoint(h.x, h.y);
		nowImage=war[dir][idImage];
		if (Math.random() < 0.8) {
		//	h.setHealth(h.getHealth() - 5);
			h.sethealth();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public synchronized int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	public   int getHealth() {
		return health;
	}
//	public synchronized void setHealth(int health) {
//		System.out.println(this.id+"salam"+health);
//		if (health == 0) {
//			waterfront.killHuman(this);
//		} else
//			this.health = health;
//	}
	public synchronized void sethealth(){
//		System.out.println(this.id+"salam"+health);
		health=health-5;
		if(health==0)
			waterfront.killHuman(this);
			
	}
	public int getIronSize() {
		return ironSize;
	}
	public void setIronSize(int ironSize) {
		this.ironSize = ironSize;
	}
	public int getWoodSize() {
		return woodSize;
	}
	public void setWoodSize(int woodSize) {
		this.woodSize = woodSize;
	}
	public int getDuty() {
		return duty;
	}
	public void setDuty(int duty) {
		this.duty = duty;
	}
	public boolean isMove() {
		return move;
	}
	public void setMove(boolean move) {
		this.move = move;
	}
	public BufferedImage getNowImage() {
		return nowImage;
	}
	public void setNowImage(BufferedImage nowImage) {
		this.nowImage = nowImage;
	}

	public void setDirToPoint(int xp,int yp){
		int tempx=xp-x;
		int tempy=yp-y;
		if(tempx>0 && tempy==0)
			dir=0;
		else if (tempx>0 && tempy<0)
			dir=1;
		else if (tempx==0 && tempy<0)
			dir=2;
		else if (tempx<0 && tempy<0)
			dir=3;
		else if (tempx<0 && tempy==0)
			dir=4;
		else if (tempx<0 && tempy>0)
			dir=5;
		else if (tempx==0 && tempy>0)
			dir=6;
		else if (tempx>0 && tempy>0)
			dir=7;
	}




	public int getActivity() {
		return activity;
	}




	public void setActivity(int activity) {
		this.activity = activity;
	}
	



}