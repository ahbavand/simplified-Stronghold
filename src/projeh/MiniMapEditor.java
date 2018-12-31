package projeh;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MiniMapEditor extends JPanel implements MouseListener,Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage[][] textures = new BufferedImage[4][16];
	BufferedImage[] iron = new BufferedImage[2];
	BufferedImage[] tree = new BufferedImage[5];
	BufferedImage bigFish;
	BufferedImage smallFish;

	Map map;
	int x,y;
	int sx,sy;
	int mx,my;
	MapDisplayPanel mapdisplaypanel;
	int xdisplay,ydisplay;
	BufferedImage pic=new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image;
	public MiniMapEditor(Map map,MapDisplayPanel mapdisplaypanel) throws IOException {
		setLayout(null);
		this.mapdisplaypanel=mapdisplaypanel;
		setSize(200,200);
		setLocation(getToolkit().getScreenSize().width-220,10);
		this.map=map;
		sx=200/map.locations.length;
		sy=200/map.locations[0].length;
		
		setVisible(true);
		addMouseListener(this);

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
		//iron[0] = ImageIO.read(new File("pic\\resource\\tree\\1\\man.png"));
		
		 bigFish = ImageIO.read(new File("pic\\resource\\fish\\vall2.png"));
		smallFish = ImageIO.read(new File("pic\\resource\\fish\\smallFish.png"));

		new Thread(this).start();
		
	}
	public void setmap(Map mp){
	this.map=mp;
	}
	public void setsx(int sx){
		this.sx=sx;
	}
	public void sety(int sy){
		this.sy=sy;
	}
	@Override
	public void paintComponent(Graphics arg0) {
	//	super.paint(arg0);
		super.paintComponent(arg0);

		arg0.drawImage(pic,0 , 0, null);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mx=arg0.getX();
		my=arg0.getY();
		x=mx/sx+1;
		y=my/sy+1;
		if(x<20){
			mapdisplaypanel.x=0;
		}
		if(x>20&&x<map.locations.length-21)
			mapdisplaypanel.x=x-20;
		if(x>=map.locations.length-21)
			mapdisplaypanel.x=map.locations.length-41;
		if(y<10){
			mapdisplaypanel.y=0;
		}
		if(y>10&&y<map.locations.length-13)
			mapdisplaypanel.y=y-10;
		if(y>=map.locations.length-13)
			mapdisplaypanel.y=map.locations.length-23;

		
	}
	public void painting() {
		Graphics2D g2d=pic.createGraphics();

		for(int i=0;i<map.locations.length;i++)
			for(int j=0;j<map.locations[0].length;j++){
				g2d.drawImage(textures[1][15], i * sx, j * sy,sx, sy, null);
				if (map.getLocationIJ(i , j ).D1 != 1) {
					int besideCode  = Integer.parseInt(map.ckeckBeside( i,  j,map.getLocationIJ(i, j).D1),2);
					g2d.drawImage(textures[map.getLocationIJ(i , j ).D1][besideCode], i * sx, j * sy,sx,sy, null);
				}
			}
		for(int i=0;i<map.locations.length;i++)
			for(int j=0;j<map.locations[0].length;j++){

				switch (map.locations[i ][j ].D3) {
				case 1:
					g2d.drawImage(bigFish, i * sx, j *sy-sy/2, sx,
							sy, null);
					break;
				case 2:
					g2d.drawImage(smallFish, i * sx, j *sy-sy/2, sx,
							sy, null);
					break;
				case 3:
					g2d.drawImage(tree[1], i * sx, j *sy-sy/2, sx,
							sy, null);
					
					break;
				case 4:
					g2d.drawImage(iron[0],  i * sx, j *sy-sy/2, sx,
							sy, null);
					break;
				default:
					break;
				}
			}

		xdisplay=mapdisplaypanel.x;
		ydisplay=mapdisplaypanel.y;
		g2d.drawRect(sx*xdisplay, sy*ydisplay, sx*39, (sy)*21);
	    g2d.setColor(Color.orange);  
	    g2d.fillRect(sx*xdisplay, sy*ydisplay, sx*39, (sy)*21);
	    
	}
	

//////////
	@Override
	public void mouseEntered(MouseEvent arg0) {
		image = toolkit.getImage("pic\\cursor\\cursor.png");
		Cursor c=  new MyCursor(image).setMyCursor();
		this.setCursor(c);

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
	@Override
	public void run() {
		while(true){
			painting();
		repaint();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}}
	

}
