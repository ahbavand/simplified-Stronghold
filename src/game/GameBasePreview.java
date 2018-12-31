package game;

import projeh.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameBasePreview extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map map;
	int x,y;
	int sx,sy;
	int mx,my;
	BufferedImage pic=new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
	BufferedImage[][] textures = new BufferedImage[4][16];
	BufferedImage[] iron = new BufferedImage[2];
	BufferedImage[] tree = new BufferedImage[5];
	BufferedImage bigFish;
	BufferedImage smallFish;



	public GameBasePreview(Map map) throws IOException {
		setLayout(null);
		setSize(200,200);
		setLocation(20,20);
		this.map=map;
		sx=200/map.getLocations().length;
		sy=200/map.getLocations()[0].length;
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

		setVisible(true);
		
	}
	@Override
	public void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.drawImage(pic, 0,0, null);
		
	}
	public void painting() {
		
		Graphics2D g2d=pic.createGraphics();

		for(int i=0;i<map.getLocations().length;i++)
			for(int j=0;j<map.getLocations()[0].length;j++){
				g2d.drawImage(textures[1][15], i * sx, j * sy,sx, sy, null);
				if (map.getLocationIJ(i , j ).getD1() != 1) {
					int besideCode  = Integer.parseInt(map.ckeckBeside( i,  j,map.getLocationIJ(i, j).getD1()),2);
					g2d.drawImage(textures[map.getLocationIJ(i , j ).getD1()][besideCode], i * sx, j * sy,sx,sy, null);
				}
				
			}
		for(int i=0;i<map.getLocations().length;i++)
			for(int j=0;j<map.getLocations()[0].length;j++){

				switch (map.getLocations()[i ][j ].getD3()) {
				case 1:
					g2d.drawImage(bigFish, i * sx, j *sy-sy/2, sx,
							sy, null);
					break;
				case 2:
					g2d.drawImage(smallFish, i *sx, j * sy, sx,
							sy, null);
					break;
				case 3:
					g2d.drawImage(tree[0], i * sx, j * sy, sx,
						sy, null);
					
					break;
				case 4:
					g2d.drawImage(iron[0], i * sx, j * sy, sx,
						sy, null);
					break;
				default:
					break;
				}
			}
   

	}



}