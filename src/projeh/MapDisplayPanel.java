package projeh;

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

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapDisplayPanel extends JPanel implements MouseListener, Messages,
		MouseMotionListener, Runnable , KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map map;
	int x, y, xd, yd;
	int blockSize;
	BufferedImage[][] textures = new BufferedImage[5][16];
	BufferedImage[] iron = new BufferedImage[2];
	BufferedImage[] tree = new BufferedImage[5];
	BufferedImage bigFish;
	BufferedImage smallFish;
	boolean previewFlag=false;
	int startTime;
	Items items;
	int draw;// 0=seahigh 1=sealow 2=island
	int put;// 4=iron 3=tree
	int season;

	public MapDisplayPanel(int blockSize,Map map) throws IOException {
		this.setMap(map);
		this.blockSize = blockSize;
		x = 0;
		y = 0;
		draw = 0;
		put = 0;
		items = new Items (this,map);
		add(items);
		setSize(getToolkit().getScreenSize().width+20,getToolkit().getScreenSize().height);
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.addKeyListener(this);
		
		
		
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
		
		//
		
	
		new Thread(this).start();
		setOpaque(false);
		setVisible(true);
		
	}

	public void setMap(Map map) {
		this.map = map;
	}


	@Override
	public void paint(Graphics arg0) {
		if (previewFlag)
			season = (((int) (System.currentTimeMillis() / 1000) - startTime) / 2) % 4;
		else
			season=1;
		for (int i = 0; i < getToolkit().getScreenSize().width/blockSize; i++)
			for (int j = 0; j < getToolkit().getScreenSize().height/blockSize; j++) {
				arg0.drawImage(textures[1][15], i * blockSize, j * blockSize,
						blockSize, blockSize, null);
				if (getMap().getLocationIJ(i + x, j + y).D1 != 1) {
					int besideCode  = Integer.parseInt(getMap().ckeckBeside(x + i, y + j,getMap().getLocationIJ(x+i, y+j).D1),2);
					if(getMap().getLocationIJ(i + x, j + y).D1==2 && season ==3){
						arg0.drawImage(textures[4][besideCode], i * blockSize, j * blockSize,50,50, null);
					//System.out.println("sasd");
					}else
					arg0.drawImage(textures[getMap().getLocationIJ(i + x, j + y).D1][besideCode], i * blockSize, j * blockSize,50,50, null);
				}
			}
		
		for (int i = 0; i < getToolkit().getScreenSize().width/blockSize; i++)
			for (int j = 0; j < getToolkit().getScreenSize().height/blockSize; j++) {
				switch (getMap().locations[i + x][j + y].D3) {
				case 1:
					arg0.drawImage(bigFish, i * blockSize, j * blockSize-blockSize/2, 75,
							75, null);
					break;
				case 2:
					arg0.drawImage(smallFish, i * blockSize, j * blockSize-blockSize/2, 65,
							65, null);
					break;
				case 3:
					arg0.drawImage(tree[season], i * blockSize, j * blockSize-blockSize/2, 65,
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
		
		super.paint(arg0);

	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

		int xc, yc;
		xc = arg0.getX() / blockSize;
		yc = arg0.getY() / blockSize;
		getMap().getLocationIJ(x + xc, y + yc).setD1(draw);
		getMap().getLocationIJ(x + xc, y + yc).setD3(put);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		requestFocus();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		xd=250;
		yd=250;
		

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	
	}

	@Override
	public void sms(int message, Object sender) {
		switch (message) {
		case Messages.DRAW_HIGH_SEA:
			draw = 1;
			put=0;
			break;
		case Messages.DRAW_LOW_SEA:
			draw = 2;
			put=0;
			break;
		case Messages.DRAW_ISLAND:
			draw = 3;
			put=0;
			break;
		case Messages.DRAW_IRON:
			put = 4;
			draw=3;
			break;
		case Messages.DRAW_TREE:
			put = 3;
			draw=3;
			break;
		case Messages.PREVIEW:
			previewFlag=!previewFlag;
			if(previewFlag)
				startTime=(int) (System.currentTimeMillis()/1000);
			break;
		case Messages.DRAW_SMALL_FISH:
			draw=2;
			put=2;
			break;
		case Messages.DRAW_BIG_FISH:
			draw=1;
			put=1;
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int xc, yc;
		xc = e.getX() / blockSize;
		yc = e.getY() / blockSize;
		getMap().getLocationIJ(x + xc, y + yc).setD1(draw);
		getMap().getLocationIJ(x + xc, y + yc).setD3(put);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		xd = e.getX();
		yd = e.getY();
	}

	public void moveScreen(int dir) {
		Dimension d = getToolkit().getScreenSize();
		switch (dir) {
		case 1:
			if (x < getMap().width - d.getWidth()/blockSize-1) {
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

			if (y < getMap().height - d.getHeight()/blockSize-1) {
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
	public void run() {
		while (true) {
			if (xd > getToolkit().getScreenSize().getWidth()-50 ) {
				moveScreen(1);
			}
			if (xd < 50) {
				moveScreen(2);
			}
			if (yd > getToolkit().getScreenSize().getHeight()-180 ) {
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
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()== 27){
			MainFrame.itemVisibility=!MainFrame.itemVisibility;
			items.setVisible(MainFrame.itemVisibility);
		}
		
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public Map getMap() {
		return map;
	}

}