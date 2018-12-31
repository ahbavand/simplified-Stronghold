package game;

import projeh.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import objects.Bar;
import objects.Bigfish;
import objects.Human;
import objects.Iron;
import objects.Mahigir;
import objects.SmallFish;
import objects.Tree;
import objects.Waterfront;
                 //
public class GameDisplayPanel extends JPanel implements Runnable,MouseMotionListener,MouseListener,KeyListener {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage[][] textures = new BufferedImage[5][16];
	BufferedImage[] iron = new BufferedImage[2];
	BufferedImage[] tree = new BufferedImage[5];
	BufferedImage bigFish;
	BufferedImage smallFish;
	BufferedImage transship;
	BufferedImage port;

	private int startTime;
	static int TimeforSeason=20;
	private Vector<Waterfront> waterfront=new Vector<Waterfront>();
	private Vector<Tree> trees=new Vector<Tree>();
	private Vector<Iron>irons = new Vector<Iron>();
	Vector<Bigfish>bigfishs=new Vector<Bigfish>();
	Vector<SmallFish>smallfishs=new Vector<SmallFish>();
	int[][] rah;
	int[][]shiprah;
	int[][]winterTable;
	
	public int selectid=0;
	int mousex,mousey;
	int numberjazire=1;
	int number=1;
	int treeNumber=0;
	int ironNumber=0;
	int x,y,xd,yd;
	int blockSize=50;
	boolean is=true;
	boolean isshabake=false;
	Client client;
	StartGame startGame;
	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	Map map;
	int  numberwaterfront=0;
	
	
	HumanPanel humanPanel;
	TreePanel treePanel;
	IronPanel ironPanel;
	WaterfrontPanel waterfrontPanel;
	CarrierShipPanel carrierShipPanel;
	FishingShipPanel fishingShipPanel;
	MiliteryShipPanel militeryShipPanel;
	
	public GameDisplayPanel(int blockSize,Map map,Client client) throws IOException {
		setStartTime((int) (System.currentTimeMillis()/1000));
		this.client=client;
		setLayout(null);
		setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		addMouseMotionListener(this);
		this.addKeyListener(this);
		addMouseListener(this);
		transship=ImageIO.read(new File("o.png"));
		isshabake=true;
		setFocusable(true);
		this.map=map;
		rah=new int[map.width][map.height];
		shiprah=new int[map.width][map.height];
		winterTable=new int[map.width][map.height];
		preparebase();
		this.blockSize=blockSize;
		x=0;
		y=0;
		for(int i=0;i<16;i++){
			String sa ="000"+Integer.toBinaryString(i); 
			sa = (sa.substring(sa.length()-4, sa.length()));
			textures[4][i]=ImageIO.read(new File("pic\\ice\\"+sa+".png"));
		}
		for(int i=0;i<16;i++){
			String sa ="000"+Integer.toBinaryString(i); 
			sa = (sa.substring(sa.length()-4, sa.length()));
			textures[3][i]=ImageIO.read(new File("pic\\island\\"+sa+".png"));
		}
		
		for(int i=0;i<16;i++){
			String sa ="000"+Integer.toBinaryString(i); 
			sa = (sa.substring(sa.length()-4, sa.length()));
			textures[2][i]=ImageIO.read(new File("pic\\low sea\\"+sa+".png"));
		}
		for (int i = 0; i < 16; i++) {
			String sa = "000" + Integer.toBinaryString(i);
			sa = (sa.substring(sa.length() - 4, sa.length()));
			textures[1][i] = ImageIO.read(new File("pic\\sea\\sea52.jpg"));
		}

		tree[0] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_1.png"));
		tree[1] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_2.png"));
		tree[2] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_3.png"));
		tree[3] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_4.png"));

		iron[0] = ImageIO.read(new File("pic\\resource\\iron\\iron mine.png"));
		
		bigFish = ImageIO.read(new File("pic\\resource\\fish\\vall2.png"));
		smallFish = ImageIO.read(new File("pic\\resource\\fish\\smallFish.png"));
		
		humanPanel= new HumanPanel(false, 2000, 2000, 100); 
		add(humanPanel);
		
		treePanel= new TreePanel(false, 2000);
		add(treePanel);
		
		ironPanel= new IronPanel(false,2000); 
		add(ironPanel);
		
		waterfrontPanel= new WaterfrontPanel(false, 0, 0,0, 1000,this);
		add(waterfrontPanel);
		
		carrierShipPanel= new CarrierShipPanel(false, 0, 0, 1000);
		add(carrierShipPanel);
		
		fishingShipPanel = new FishingShipPanel(false, 0, 1000);
		add(fishingShipPanel);
		
		militeryShipPanel = new MiliteryShipPanel(false, 1000);
		add(militeryShipPanel);
		
		port=ImageIO.read(new File("pic\\port.png"));
		
		matrisrahship();
		
		setVisible(true);
//		for(int i=0;i<waterfront.size()-1;i++)
		//new Hoosh(waterfront.elementAt(i+1), map, trees, bigfishs, smallfishs, shiprah,rah,irons,startTime,this,waterfront);
		new Khordan(waterfront);
		new Thread(this).start();
	}
	public GameDisplayPanel(int blockSize,Map map) throws IOException {
		setStartTime((int) (System.currentTimeMillis()/1000));
		setLayout(null);
		setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		addMouseMotionListener(this);
		this.addKeyListener(this);
		addMouseListener(this);
		transship=ImageIO.read(new File("o.png"));
		
		setFocusable(true);
		this.map=map;
		rah=new int[map.width][map.height];
		shiprah=new int[map.width][map.height];
		winterTable=new int[map.width][map.height];
		preparebase();
		this.blockSize=blockSize;
		x=0;
		y=0;
		
		for(int i=0;i<16;i++){
			String sa ="000"+Integer.toBinaryString(i); 
			sa = (sa.substring(sa.length()-4, sa.length()));
			textures[3][i]=ImageIO.read(new File("pic\\island\\"+sa+".png"));
		}
		
		for(int i=0;i<16;i++){
			String sa ="000"+Integer.toBinaryString(i); 
			sa = (sa.substring(sa.length()-4, sa.length()));
			textures[2][i]=ImageIO.read(new File("pic\\low sea\\"+sa+".png"));
		}
		for (int i = 0; i < 16; i++) {
			String sa = "000" + Integer.toBinaryString(i);
			sa = (sa.substring(sa.length() - 4, sa.length()));
			textures[1][i] = ImageIO.read(new File("pic\\sea\\sea52.jpg"));
		}

		tree[0] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_1.png"));
		tree[1] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_2.png"));
		tree[2] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_3.png"));
		tree[3] = ImageIO.read(new File("pic\\resource\\tree\\1\\1_4.png"));

		iron[0] = ImageIO.read(new File("pic\\resource\\iron\\iron mine.png"));
		
		bigFish = ImageIO.read(new File("pic\\resource\\fish\\vall2.png"));
		smallFish = ImageIO.read(new File("pic\\resource\\fish\\smallFish.png"));
		
		humanPanel= new HumanPanel(false, 2000, 2000, 100); 
		add(humanPanel);
		
		treePanel= new TreePanel(false, 2000);
		add(treePanel);
		
		ironPanel= new IronPanel(false,2000); 
		add(ironPanel);
		
		waterfrontPanel= new WaterfrontPanel(false, 0, 0,0, 1000,this);
		add(waterfrontPanel);
		
		carrierShipPanel= new CarrierShipPanel(false, 0, 0, 1000);
		add(carrierShipPanel);
		
		fishingShipPanel = new FishingShipPanel(false, 0, 1000);
		add(fishingShipPanel);
		
		militeryShipPanel = new MiliteryShipPanel(false, 1000);
		add(militeryShipPanel);
		
		port=ImageIO.read(new File("pic\\port.png"));
		
		matrisrahship();
		
		setVisible(true);
		for(int i=0;i<waterfront.size()-1;i++)
			new Hoosh(waterfront.elementAt(i+1), map, trees, bigfishs, smallfishs, shiprah,rah,irons,startTime,this,waterfront,winterTable);
		new Khordan(waterfront);

		new Thread(this).start();
	}

	

	private void preparebase() throws IOException {

		
		for (int i = 0; i < map.width; i++)
			for (int j = 0; j < map.height; j++) {
				if(map.getLocationIJ(i, j).getD1()==3)
					rah[i][j]=1;////rah vojood darad
				else
					rah[i][j]=0;////rah vojood nadarad
				if(map.getLocationIJ(i, j).getD1()==1)
					winterTable[i][j]=1;////rah vojood darad
				else
					winterTable[i][j]=0;////rah vojood nadarad
					
				if (map.getLocationIJ(i , j ).getD6() == 1) {
				//	getWaterfront().addElement(new Waterfront(numberwaterfront, i, j, rah,map.getLocations()[i][j].jazire,this));
					getWaterfront().addElement(new Waterfront(numberwaterfront, i, j, rah,map.getLocations()[i][j].jazire,this,map,trees,irons,waterfront,startTime));
					numberwaterfront++;
				}
				switch (map.getLocations()[i ][j ].getD3()) {
				case 1:
				{
					bigfishs.addElement(new Bigfish(i, j));
				}
					break;
				
				case 2:
				{
					smallfishs.addElement(new SmallFish(i, j));
				}
					break;
				case 3:
				{
					Tree tree= new Tree(i, j,treeNumber,map.locations[i][j].jazire);
					getTrees().addElement(tree);
					treeNumber++;
				}	
					break;
				case 4:{
					Iron iron= new Iron(i,j,ironNumber,map.locations[i][j].jazire);
					getIrons().addElement(iron);
					ironNumber++;
				
				}
				
					break;
				default:
					break;
				}
			}
		
	}
	
	
	public void matrisrahship(){
		for(int i=0;i<map.width;i++)
			for(int j=0;j<map.height;j++){
				if(map.getLocations()[i][j].getD1()!=3)
					shiprah[i][j]=1;/////rah baraye keshti vojoud darad
				else
					shiprah[i][j]=0;//////rah baraye keshti vojoud nadarad
				
			}
	}
	
	public void moveScreen(int dir) {

		Dimension d = getToolkit().getScreenSize();
		switch (dir) {
		case 1:
			if (x < map.width - d.getWidth()/blockSize-1) {
				x += 1;
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			break;
		case 2:
			if (x > 0) {
				x -= 1;
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case 3:

			if (y < map.height - d.getHeight()/blockSize-1) {
				y += 1;
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case 4:

			if (y > 0) {
				y -= 1;
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);

		int time = (int) (System.currentTimeMillis()/1000)-getStartTime();
		int season = (((int) (System.currentTimeMillis() / 1000) - startTime) / 20) % 4;
		for (int i = 0; i < getToolkit().getScreenSize().width/blockSize+1; i++)
			for (int j = 0; j < getToolkit().getScreenSize().height/blockSize+1; j++) {
				arg0.drawImage(textures[1][15], i * blockSize, j * blockSize,
						blockSize, blockSize, null);
				if (map.getLocationIJ(i + x, j + y).getD1() != 1) {
					int besideCode  = Integer.parseInt(map.ckeckBeside(x + i, y + j,map.getLocationIJ(x+i, y+j).getD1()),2);
					
					if(map.getLocationIJ(i + x, j + y).D1==2 && season ==3)
						arg0.drawImage(textures[4][besideCode], i * blockSize, j * blockSize,50,50, null);
					else
					arg0.drawImage(textures[map.getLocationIJ(i + x, j + y).D1][besideCode], i * blockSize, j * blockSize,50,50, null);
				}
				if (map.getLocationIJ(i + x, j + y).getD6() == 1) {
				/*arg0.drawRect(i*blockSize, j*blockSize,  (i+1)*blockSize,  (j+1)*blockSize);
				arg0.fillRect(i*blockSize, j*blockSize,  (i+1)*blockSize,  (j+1)*blockSize);
				*/arg0.drawImage(port, i*blockSize, j*blockSize, 50, 50, null);
				}
			}
		
		for(int i=0;i<getWaterfront().size();i++)
			for(int j=0;j<getWaterfront().elementAt(i).getAdams().size();j++)
				if(getWaterfront().elementAt(i).getAdams().elementAt(j).getX()>x*blockSize)
				{
					arg0.drawImage(getWaterfront().elementAt(i).getAdams().elementAt(j).getNowImage(),  getWaterfront().elementAt(i).getAdams().elementAt(j).getX()-x*blockSize,  getWaterfront().elementAt(i).getAdams().elementAt(j).getY()-y*blockSize,50,50, null);
					
				}	
		for (int i = 0; i < getToolkit().getScreenSize().width/blockSize; i++)
			for (int j = 0; j < getToolkit().getScreenSize().height/blockSize; j++) {
				switch (map.getLocations()[i + x][j + y].getD3()) {
				case 1:
					arg0.drawImage(bigFish, i * blockSize, j * blockSize-blockSize/2, 75,
							75, null);
					break;
				case 2:
					arg0.drawImage(smallFish, i * blockSize, j * blockSize-blockSize/2, 65,
							65, null);
					break;
				case 3:
					arg0.drawImage(tree[time/TimeforSeason%4], i * blockSize, j * blockSize-blockSize/2, 65,
							65, null);
					break;
				case 4:
					arg0.drawImage(iron[0], i * blockSize, j * blockSize-blockSize/2, 50,
							50, null);
					break; 
				default:
					break;
				}
			}

		
		for(int i=0;i<getWaterfront().size();i++)
			for(int j=0;j<getWaterfront().elementAt(i).bars.size();j++)
				if(getWaterfront().elementAt(i).bars.elementAt(j).getX()>=x*blockSize)
					arg0.drawImage(getWaterfront().elementAt(i).bars.elementAt(j).getNowImage(),  getWaterfront().elementAt(i).bars.elementAt(j).getX()-x*blockSize,  getWaterfront().elementAt(i).bars.elementAt(j).getY()-y*blockSize,50,50, null);

		for(int i=0;i<getWaterfront().size();i++)
			for(int j=0;j<getWaterfront().elementAt(i).mahi.size();j++)
				if(getWaterfront().elementAt(i).mahi.elementAt(j).getX()>=x*blockSize)
					arg0.drawImage(getWaterfront().elementAt(i).mahi.elementAt(j).getNowImage(),  getWaterfront().elementAt(i).mahi.elementAt(j).getX()-x*blockSize,  getWaterfront().elementAt(i).mahi.elementAt(j).getY()-y*blockSize,50,50, null);
//		arg0.drawImage(transship,  getWaterfront().elementAt(i).mahi.elementAt(j).getX()-x*blockSize,  getWaterfront().elementAt(i).mahi.elementAt(j).getY()-y*blockSize,50,50, null);


		for(int i=0;i<waterfront.size();i++)
			for(int j=0;j<waterfront.elementAt(i).jangi.size();j++)
				if(waterfront.elementAt(i).jangi.elementAt(j).x>=x*blockSize)
					arg0.drawImage(getWaterfront().elementAt(i).jangi.elementAt(j).nowImage,  waterfront.elementAt(i).jangi.elementAt(j).x-x*blockSize,  waterfront.elementAt(i).jangi.elementAt(j).y-y*blockSize,50,50, null);

		
	}

	public void run() {
		while (true) {
			for(int i=0;i<waterfront.size();i++)
				if(waterfront.elementAt(i).getHealth()<=0){
					waterfront.removeElementAt(i);
					i++;
				}
			for(int i=0;i<getWaterfront().size();i++)
				for(int j=0;j<getWaterfront().elementAt(i).getAdams().size();j++)
				{
					if(getSelectid()==getWaterfront().elementAt(i).getAdams().elementAt(j).getId())
					{
						humanPanel.setWoodSize(getWaterfront().elementAt(i).getAdams().elementAt(j).getWoodSize());
						humanPanel.setIronSize(getWaterfront().elementAt(i).getAdams().elementAt(j).getIronSize());
						humanPanel.setHealth(getWaterfront().elementAt(i).getAdams().elementAt(j).getHealth());
					}
				}
			
			for(int i=0;i<getWaterfront().size();i++)
				if(getSelectid()==getWaterfront().elementAt(i).getShomareh())
				{
					waterfrontPanel.setIronSize(getWaterfront().elementAt(i).getIronSize());
					waterfrontPanel.setWoodSize(getWaterfront().elementAt(i).getwoodSize());
					waterfrontPanel.setHealth(getWaterfront().elementAt(i).getHealth());
					waterfrontPanel.setFoodSize(getWaterfront().elementAt(i).getFoodsize());
				}
			for(int i=0;i<trees.size();i++)
				if(getSelectid()==trees.elementAt(i).getId())
					treePanel.setWoodSize(trees.elementAt(i).getwoodSize());
			for(int i=0;i<irons.size();i++)
				if(getSelectid()==irons.elementAt(i).getId())
					ironPanel.setironSize(irons.elementAt(i).getIronSize());
			
			for(int i=0;i<getWaterfront().size();i++)
				for(int j=0;j<getWaterfront().elementAt(i).jangi.size();j++)
					if(getSelectid()==getWaterfront().elementAt(i).jangi.elementAt(j).id){
						 militeryShipPanel.setHealth(getWaterfront().elementAt(i).jangi.elementAt(j).getHealth());
					}
			for(int i=0;i<getWaterfront().size();i++)
				for(int j=0;j<getWaterfront().elementAt(i).bars.size();j++)
					if(getSelectid()==getWaterfront().elementAt(i).bars.elementAt(j).getId()){
						 carrierShipPanel.setWoodSize(waterfront.elementAt(i).bars.elementAt(j).getWoodSize());
						 carrierShipPanel.setIronSize(waterfront.elementAt(i).bars.elementAt(j).getIronSize());
						 carrierShipPanel.setHealth(getWaterfront().elementAt(i).bars.elementAt(j).getHealth());

					}
			
						if (xd > getToolkit().getScreenSize().getWidth()-50 ) {
							moveScreen(1);
						}
						if (xd < 50) {
							moveScreen(2);
						}
						if (yd > getToolkit().getScreenSize().getHeight()-50 ) {
							moveScreen(3);
						}
						if (yd < 20) {
							moveScreen(4);
						}
						repaint();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(isshabake){
						if(client.click){
							if(!client.isWaterfront)rightClick(client.mousex, client.mousey, client.selectedId);
							else makeSth(client.selectedId, client.taskNumber);
							client.click=false;	
						}
		}}
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		xd = arg0.getX();
		yd = arg0.getY();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moveScreen(4);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moveScreen(3);
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moveScreen(2);
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moveScreen(1);
		}
		if(e.getKeyCode()==KeyEvent.VK_Z)
		{
			if(is){
				if(x==0)
					x=10;
				else
					x=10+x;
				if(y==0)
					y=6;
				else
					y=6+y;
			blockSize=100;
			
			is=false;
			}
			else{
				if(x<=10)
					x=0;
				else
					try {
						x=x-10;

					} catch (Exception e1) {
						x=x-20;
						// TODO: handle exception
					}
				if(y<=6)
					y=0;
				else
					y=y-6;
				blockSize=50;
				is=true;
				
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			new Pause(startGame);
			
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void makeHuman(Waterfront wf) {
			
			int size=wf.getAdams().size();
			if(map.getLocations()[wf.getX()+1][wf.getY()].getD1()==3)
				wf.getAdams().addElement(new Human((wf.getX()+1)*50,wf.getY()*50, rah,wf.getShomareh()*1000+100+size ,wf,getTrees(),getIrons(),wf.bars,getWaterfront(),getStartTime(),wf.jazirenumber,this));
				
			else if(map.getLocations()[wf.getX()-1][wf.getY()].getD1()==3)
				wf.getAdams().addElement(new Human((wf.x-1)*50,wf.y*50,rah,wf.getShomareh()*1000+100+size ,wf,getTrees(),getIrons(),wf.bars,getWaterfront(),getStartTime(),wf.jazirenumber,this));
				
			else if(map.getLocations()[wf.getX()][wf.getY()+1].getD1()==3)
				wf.getAdams().addElement(new Human(wf.getX()*50,(wf.getY()+1)*50, rah,wf.getShomareh()*1000+100+size ,wf,getTrees(),getIrons(),wf.bars,getWaterfront(),getStartTime(),wf.jazirenumber,this));
				
			else if(map.getLocations()[wf.getX()][wf.getY()-1].getD1()==3)
				wf.getAdams().addElement(new Human(wf.getX()*50,(wf.getY()-1)*50,rah,wf.getShomareh()*1000+100+size ,wf,getTrees(),getIrons(),wf.bars,getWaterfront(),getStartTime(),wf.jazirenumber,this));
			
	}

	public void makeCarrierShip(Waterfront wf) {
	
		int size=wf.bars.size();	
		if(map.getLocations()[wf.getX()+1][wf.getY()].getD1()!=3)
			wf.bars.addElement(new Bar((wf.getX()+1)*50,wf.getY()*50,shiprah,wf.getShomareh()*1000+200+size,wf,map,wf.jazirenumber,startTime,winterTable));
		else if(map.getLocations()[wf.getX()-1][wf.getY()].getD1()!=3)
			wf.bars.addElement(new Bar((wf.getX()-1)*50,wf.getY()*50,shiprah,wf.getShomareh()*1000+200+size,wf,map,wf.jazirenumber,startTime,winterTable));

		else if(map.getLocations()[wf.getX()][wf.getY()+1].getD1()!=3)
			wf.bars.addElement(new Bar(wf.getX()*50,(wf.getY()+1)*50,shiprah,wf.getShomareh()*1000+200+size,wf,map,wf.jazirenumber,startTime,winterTable));

		else if(map.getLocations()[wf.getX()][wf.getY()-1].getD1()!=3)
			wf.bars.addElement(new Bar(wf.getX()*50,(wf.getY()-1)*50,shiprah,wf.getShomareh()*1000+200+size,wf,map,wf.jazirenumber,startTime,winterTable));
		for(int i=0;i<wf.getAdams().size();i++)
			if(wf.getAdams().elementAt(i).getDuty()==8&&wf.getAdams().elementAt(i).getActivity()==1)
				wf.getAdams().elementAt(i).setDuty(0);
	}
	
	public void makeFishingShip(Waterfront wf) {
		
		int size=wf.mahi.size();
		if(map.getLocations()[wf.getX()+1][wf.getY()].getD1()!=3)
			wf.mahi.addElement(new Mahigir((wf.getX()+1)*50, wf.getY()*50, shiprah, wf.getShomareh()*1000+300+size, map, wf,bigfishs,smallfishs,startTime,winterTable));
		else if(map.getLocations()[wf.getX()-1][wf.getY()].getD1()!=3)
			wf.mahi.addElement(new Mahigir((wf.getX()-1)*50, wf.getY()*50, shiprah, wf.getShomareh()*1000+300+size, map, wf,bigfishs,smallfishs,startTime,winterTable));

		else if(map.getLocations()[wf.getX()][wf.getY()+1].getD1()!=3)
			wf.mahi.addElement(new Mahigir(wf.getX()*50,( wf.getY()+1)*50, shiprah, wf.getShomareh()*1000+300+size, map, wf,bigfishs,smallfishs,startTime,winterTable));

		else if(map.getLocations()[wf.getX()][wf.getY()-1].getD1()!=3)
			wf.mahi.addElement(new Mahigir(wf.getX()*50,( wf.getY()-1)*50, shiprah, wf.getShomareh()*1000+300+size, map, wf,bigfishs,smallfishs,startTime,winterTable));
		for(int i=0;i<wf.getAdams().size();i++)
			if(wf.getAdams().elementAt(i).getDuty()==8&&wf.getAdams().elementAt(i).getActivity()==1)
				wf.getAdams().elementAt(i).setDuty(0);

	}
	
	public void makeMilliteryShip(Waterfront wf) {
		int size=wf.jangi.size();
		if(map.getLocations()[wf.getX()+1][wf.getY()].getD1()!=3)
			
			wf.jangi.addElement(new Jangi((wf.getX()+1)*50, wf.getY()*50, shiprah, wf.getShomareh()*1000+400+size, waterfront, wf,startTime,winterTable));
		
		else if(map.getLocations()[wf.getX()-1][wf.getY()].getD1()!=3)
			
			wf.jangi.addElement(new Jangi((wf.getX()-1)*50, wf.getY()*50, shiprah, wf.getShomareh()*1000+400+size, waterfront, wf,startTime,winterTable));

		else if(map.getLocations()[wf.getX()][wf.getY()+1].getD1()!=3)
			
			wf.jangi.addElement(new Jangi(wf.getX()*50, (wf.getY()+1)*50, shiprah, wf.getShomareh()*1000+400+size, waterfront, wf,startTime,winterTable));

		else if(map.getLocations()[wf.getX()][wf.getY()-1].getD1()!=3)
			
			wf.jangi.addElement(new Jangi(wf.getX()*50, (wf.getY()-1)*50, shiprah, wf.getShomareh()*1000+400+size, waterfront, wf,startTime,winterTable));
		for(int i=0;i<wf.getAdams().size();i++)
			if(wf.getAdams().elementAt(i).getDuty()==8&&wf.getAdams().elementAt(i).getActivity()==1)
				wf.getAdams().elementAt(i).setDuty(0);

		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mousex=arg0.getX()+x*blockSize;
		mousey=arg0.getY()+y*blockSize;
		humanPanel.setVisible(false);
		treePanel.setVisible(false); 
		ironPanel.setVisible(false);
		waterfrontPanel.setVisible(false);
		carrierShipPanel.setVisible(false);

		if(SwingUtilities.isLeftMouseButton(arg0))
		{
			if(isshabake)
			leftClick(mousex, mousey, client.getId());//humanSelect
									  			 	  //cargoShipSelect
									  			 	  //fishingShipSelet
									             	  //militaryShipSelect
									  	         	  //waterfrontSelect
			else
				leftClick(mousex, mousey, waterfront.elementAt(0).getShomareh());
		}

	    if(SwingUtilities.isRightMouseButton(arg0))
	    {
	    	if(isshabake){
	    	client.sendClick(Integer.toString(getSelectid())+"-"+Integer.toString(mousex)+"-"+Integer.toString(mousey));
//	    	rightClick(mousex, mousey,client.getId(),getSelectid());//humanMove
	    	}		   //cargoShipMove
	    	else
	    		rightClick(mousex, mousey, selectid);
			    					   			  	                //militaryShipMove
				    				   	          	                //catchWood
					    			   			  	                //catchIron
	    						       			  	                //toCargoShip  
	    						       			  	                //outCargoShip
	    }
	    
	}
	
	void treeSelect(int mousex , int mousey,int currentCountry){
		for(int i=0;i<trees.size();i++)//select tree
			if(trees.elementAt(i).getX()==mousex/blockSize&&trees.elementAt(i).getY()==mousey/blockSize)
			{
				selectid=trees.elementAt(i).getId();
				humanPanel.setVisible(false);
				treePanel.setVisible(true);
				ironPanel.setVisible(false);
				waterfrontPanel.setVisible(false);
				carrierShipPanel.setVisible(false);
				militeryShipPanel.setVisible(false);
				
			}
		for(int i=0;i<irons.size();i++)
			if(irons.elementAt(i).getX()==mousex/blockSize&&irons.elementAt(i).getY()==mousey/blockSize)
			{
				selectid=irons.elementAt(i).getId();
				humanPanel.setVisible(false);
				treePanel.setVisible(false);
				ironPanel.setVisible(true);
				waterfrontPanel.setVisible(false);
				carrierShipPanel.setVisible(false);
				fishingShipPanel.setVisible(false);
				militeryShipPanel.setVisible(false);
				
			}
	}
	
	void humanSelect(int mousex , int mousey,int currentCountry){
		
		for(int j=0;j<getWaterfront().elementAt(currentCountry).getAdams().size();j++)//select human
			if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getX()/blockSize==mousex/blockSize&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getY()/blockSize==mousey/blockSize){
				selectid=getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getId();
				humanPanel.setVisible(true);
				treePanel.setVisible(false);
				ironPanel.setVisible(false);
				waterfrontPanel.setVisible(false);
				carrierShipPanel.setVisible(false);
				militeryShipPanel.setVisible(false);
				break;
			}
	}
	
	void cargoShipSelect(int mousex , int mousey,int currentCountry){
		for(int j=0;j<getWaterfront().elementAt(currentCountry).bars.size();j++)//select  CargoShip(bar)
			if(getWaterfront().elementAt(currentCountry).bars.elementAt(j).getX()/blockSize==mousex/blockSize&&getWaterfront().elementAt(currentCountry).bars.elementAt(j).getY()/blockSize==mousey/blockSize){
					selectid=getWaterfront().elementAt(currentCountry).bars.elementAt(j).getId();
					humanPanel.setVisible(false);
					treePanel.setVisible(false);
					ironPanel.setVisible(false);
					waterfrontPanel.setVisible(false);
					carrierShipPanel.setVisible(true);
					militeryShipPanel.setVisible(false);
					
					break;
			}
	}
	
	void fishingShipSelect(int mousex , int mousey,int currentCountry){
		for(int j=0;j<getWaterfront().elementAt(currentCountry).mahi.size();j++)////select FishingShip
			if(getWaterfront().elementAt(currentCountry).mahi.elementAt(j).getX()/blockSize==mousex/blockSize&&getWaterfront().elementAt(currentCountry).mahi.elementAt(j).getY()/blockSize==mousey/blockSize){
				selectid=getWaterfront().elementAt(currentCountry).mahi.elementAt(j).getId();
				humanPanel.setVisible(false);
				treePanel.setVisible(false);
				ironPanel.setVisible(false);
				waterfrontPanel.setVisible(false);
				carrierShipPanel.setVisible(false);
				fishingShipPanel.setVisible(true);
				militeryShipPanel.setVisible(false);
				break;
			}
	}
	
	void militaryShipSelect(int mousex , int mousey,int currentCountry){
		
		for(int j=0;j<waterfront.elementAt(currentCountry).jangi.size();j++)///select MilitaryShip
			if(waterfront.elementAt(currentCountry).jangi.elementAt(j).x/blockSize==mousex/blockSize&&waterfront.elementAt(currentCountry).jangi.elementAt(j).y/blockSize==mousey/blockSize){
				selectid=waterfront.elementAt(currentCountry).jangi.elementAt(j).id;
				humanPanel.setVisible(false);
				treePanel.setVisible(false);
				ironPanel.setVisible(false);
				waterfrontPanel.setVisible(false);
				carrierShipPanel.setVisible(false);
				fishingShipPanel.setVisible(false);
				militeryShipPanel.setVisible(true);
				break;
			}
	}
	
	void waterfrontSelect(int mousex , int mousey,int currentCountry){
		//select Waterfront
		if(getWaterfront().elementAt(currentCountry).getX()==mousex/blockSize&&getWaterfront().elementAt(currentCountry).getY()==mousey/blockSize){
			selectid=getWaterfront().elementAt(currentCountry).getShomareh()*1000;
			humanPanel.setVisible(false);
			treePanel.setVisible(false);
			ironPanel.setVisible(false);
			waterfrontPanel.setVisible(true);
			carrierShipPanel.setVisible(false);
			militeryShipPanel.setVisible(false);
			waterfrontPanel.setWaterfront(getWaterfront().elementAt(currentCountry));
			
		}

	}
	
	void leftClick(int mousex , int mousey,int currentCountry){
		treeSelect(mousex, mousey, currentCountry);
		waterfrontSelect(mousex, mousey, currentCountry);

		humanSelect(mousex, mousey, currentCountry);
		cargoShipSelect(mousex, mousey, currentCountry);
		fishingShipSelect(mousex, mousey, currentCountry);
		militaryShipSelect(mousex, mousey, currentCountry);
	}
	
	
	synchronized void rightClick(int mousex , int mousey ,int selectedId){
		int currentCountry = selectedId/1000;
		cargoShipMove(mousex, mousey, currentCountry, selectedId);
		militaryShipMove(mousex, mousey, currentCountry, selectedId);
		humanMove(mousex, mousey, currentCountry,selectedId);
		catchWood(mousex, mousey, currentCountry, selectedId);

		catchIron(mousex, mousey, currentCountry,selectedId);


		toCargoShip(mousex, mousey, currentCountry, selectedId);
		outCargoShip(mousex, mousey, currentCountry, selectedId);
	}
	
	void humanMove(int mousex , int mousey,int currentCountry,int selectedId){
		for(int j=0;j<getWaterfront().elementAt(currentCountry).getAdams().size();j++)
			if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getId()==selectedId&&map.getLocations()[mousex/blockSize][mousey/blockSize].D6==0&&map.getLocations()[mousex/blockSize][mousey/blockSize].D1==3){
				getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setDuty(1);
				getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
				getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setMove(false);
			}
	}
	
	void cargoShipMove(int mousex , int mousey,int currentCountry,int selectedId){
	
		for(int j=0;j<getWaterfront().elementAt(currentCountry).bars.size();j++)
			if(getWaterfront().elementAt(currentCountry).bars.elementAt(j).getId()==selectedId&&map.getLocations()[mousex/blockSize][mousey/blockSize].D6==0){
				getWaterfront().elementAt(currentCountry).bars.elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
				getWaterfront().elementAt(currentCountry).bars.elementAt(j).setHarkat(false);
				break;
			}
	
	}
	
	void militaryShipMove(int mousex , int mousey,int currentCountry,int selectedId){
		for(int j=0;j<waterfront.elementAt(currentCountry).jangi.size();j++)
			if(waterfront.elementAt(currentCountry).jangi.elementAt(j).id==selectedId&&map.getLocations()[mousex/blockSize][mousey/blockSize].D6==0){
				waterfront.elementAt(currentCountry).jangi.elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
				waterfront.elementAt(currentCountry).jangi.elementAt(j).harkat=false;
				
			}
	}
	
	void catchWood(int mousex , int mousey,int currentCountry,int selectedId){
		if(map.getLocations()[mousex/blockSize][mousey/blockSize].getD3()==3){

			for(int j=0;j<getWaterfront().elementAt(currentCountry).getAdams().size();j++)
					if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).jazirenumber==getWaterfront().elementAt(currentCountry).jazirenumber){
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setDuty(2);
						System.out.println(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getDuty());
						System.out.println(	getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getDuty()+"salam");
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setMove(false);
						
					}///dar zir zamani ast ke adam mikhahad az derakhti ke dar jazireye khid nist choob bbardasht konad
					else if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).jazirenumber!=getWaterfront().elementAt(currentCountry).jazirenumber)	{
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setDuty(5);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setMove(false);

						
					}
			
		}
	}
	
	void catchIron(int mousex , int mousey,int currentCountry,int selectedId){
    	if (map.getLocations()[mousex/blockSize][mousey/blockSize].getD3()==4){
			for(int j=0;j<getWaterfront().elementAt(currentCountry).getAdams().size();j++)
				if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).jazirenumber==getWaterfront().elementAt(currentCountry).jazirenumber){
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setDuty(6);
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setMove(false);
					
				}///dar zir zamani ast ke adam mikhahad az madan iron ke dar jazireye khid nist choob bbardasht konad
				else if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).jazirenumber!=getWaterfront().elementAt(currentCountry).jazirenumber)	{
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setDuty(7);
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setmaghsad(mousex/blockSize, mousey/blockSize);
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(j).setMove(false);

					
				}
    	}
	}
	
	void toCargoShip(int mousex , int mousey,int currentCountry,int selectedId){
		
		///bordane adam be keshtie bari
		for(int j=0;j<getWaterfront().elementAt(currentCountry).bars.size();j++)
			if(getWaterfront().elementAt(currentCountry).bars.elementAt(j).getX()/blockSize==mousex/blockSize&&getWaterfront().elementAt(currentCountry).bars.elementAt(j).getY()/blockSize==mousey/blockSize){
					if(map.locations[mousex/blockSize+1][mousey/blockSize].D1==3){
							for(int h=0;h<getWaterfront().elementAt(currentCountry).getAdams().size();h++)
								if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getDuty()!=5&&(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getX()/50!=mousex/blockSize||getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getY()/50!=mousey/blockSize)){
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setDuty(3);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setmaghsad(mousex/blockSize+1, mousey/blockSize);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).isinkeshti=true;
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setMove(false);
								}
					}
						
					else if(map.locations[mousex/blockSize-1][mousey/blockSize].D1==3){
							for(int h=0;h<getWaterfront().elementAt(currentCountry).getAdams().size();h++)
								if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getDuty()!=5&&(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getX()/50!=mousex/blockSize||getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getY()/50!=mousey/blockSize)){

						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setDuty(3);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setmaghsad(mousex/blockSize-1, mousey/blockSize);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).isinkeshti=true;

						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setMove(false);

					}
					}
					else if(map.locations[mousex/blockSize][mousey/blockSize+1].D1==3){	
							for(int h=0;h<getWaterfront().elementAt(currentCountry).getAdams().size();h++)
								if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getDuty()!=5&&(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getX()/50!=mousex/blockSize||getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getY()/50!=mousey/blockSize)){
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setDuty(3);
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setmaghsad(mousex/blockSize, mousey/blockSize+1);
					getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).isinkeshti=true;

					getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setMove(false);
								}
					}
					
					else if(map.locations[mousex/blockSize][mousey/blockSize-1].D1==3){
							for(int h=0;h<getWaterfront().elementAt(currentCountry).getAdams().size();h++)
								if(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getId()==selectedId&&getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getDuty()!=5&&(getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getX()/50!=mousex/blockSize||getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).getY()/50!=mousey/blockSize)){
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setDuty(3);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setmaghsad(mousex/blockSize, mousey/blockSize-1);
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).isinkeshti=true;
						getWaterfront().elementAt(currentCountry).getAdams().elementAt(h).setMove(false);


					}
						
					}


					break;

	
			}
	}
	
	void outCargoShip(int mousex , int mousey,int currentCountry,int selectedId){
		System.out.println("okokok");
		System.out.println(selectid);
	    	if((selectid%1000)/100==2&&(map.locations[mousex/blockSize][mousey/blockSize].D1==3)){
	    //		if((map.locations[mousex/blockSize][mousey/blockSize].D1==3)){
	    		System.out.println("slkdfhbaldfhvablsufy");
	    			for(int j=0;j<getWaterfront().elementAt(currentCountry).bars.size();j++)
	    				if(selectedId==getWaterfront().elementAt(currentCountry).bars.elementAt(j).getId()&&(getWaterfront().elementAt(currentCountry).bars.elementAt(j).getJazirenumber()==map.locations[mousex/blockSize][mousey/blockSize].jazire)){
	    				int barx=getWaterfront().elementAt(currentCountry).bars.elementAt(j).getX()/blockSize;
	    				int bary=getWaterfront().elementAt(currentCountry).bars.elementAt(j).getY()/blockSize;
	    					if(map.locations[barx][bary-1].D1==3||map.locations[barx][bary+1].D1==3||map.locations[barx-1][bary].D1==3||map.locations[barx+1][bary].D1==3){
    						try {
	    						getWaterfront().elementAt(currentCountry).bars.elementAt(j).human.elementAt(0).setDuty(1);
								getWaterfront().elementAt(currentCountry).bars.elementAt(j).human.elementAt(0).setmaghsad(mousex/blockSize, mousey/blockSize);
							getWaterfront().elementAt(currentCountry).bars.elementAt(j).human.elementAt(0).setMove(false);
								getWaterfront().elementAt(currentCountry).bars.elementAt(j).human.elementAt(0).jazirenumber=	getWaterfront().elementAt(currentCountry).bars.elementAt(j).getJazirenumber();
								getWaterfront().elementAt(currentCountry).bars.elementAt(j).human.elementAt(0).isinkeshti=false;
								getWaterfront().elementAt(currentCountry).bars.elementAt(j).human.removeElementAt(0);
	    						
							} catch (Exception e) {
								// TODO: handle exception
							}
	    						


	    			}
	    	}}
	}
	
	void makeSth (int selectedId , int task){
		Waterfront wf = waterfront.elementAt(selectedId/1000);
		switch (task) {
			
		case 1 :{
			makeHuman(wf);
			break;
		} 
		case 2 :{
			makeCarrierShip(wf);
			break;
		}
		case 3 :{
			makeFishingShip(wf);
			break;
		}
		case 4 :{
			makeMilliteryShip(wf);
			break;
		}
	
		}
	}
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public int getSelectid() {
		return selectid;
	}


	public void setSelectid(int selectid) {
		this.selectid = selectid;
	}


	public Vector<Tree> getTrees() {
		return trees;
	}


	public void setTrees(Vector<Tree> trees) {
		this.trees = trees;
	}


	public Vector<Iron> getIrons() {
		return irons;
	}


	public void setIrons(Vector<Iron> irons) {
		this.irons = irons;
	}


	public int getStartTime() {
		return startTime;
	}


	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}


	public Vector<Waterfront> getWaterfront() {
		return waterfront;
	}


	public void setWaterfront(Vector<Waterfront> waterfront) {
		this.waterfront = waterfront;
	}


}
