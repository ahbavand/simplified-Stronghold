package projeh;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Items extends JPanel implements ActionListener,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage islandbuffer = null;
	ImageIcon islandpic;
	BufferedImage lowseabuffer = null;
	ImageIcon lowseapic;
	BufferedImage highseabuffer = null;
	ImageIcon highseapic;
	BufferedImage ironbuffer = null;
	ImageIcon ironpic;
	BufferedImage treebuffer = null;
	ImageIcon treepic;
	BufferedImage smallfishbuffer = null;
	ImageIcon smallfishpic;
	BufferedImage bigfishbuffer = null;
	ImageIcon bigfishpic;
	JButton island= new JButton("island");
	JButton seaLow=new JButton("Lowsea");
	JButton highSea= new JButton("highsea");
	JButton iron=new JButton("iron");
	JButton newmap=new JButton("new Map");
	JButton save= new JButton("save");
	JButton load=new JButton("load");
	JButton tree = new JButton("tree");
	JButton preview = new JButton("priview");
	JButton smallFish = new JButton("smallFish");
	JButton bigFish = new JButton("bigFish");
	MapDisplayPanel mapDisplayPanel;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image;
	Map map;
	boolean is=false;
	MiniMapEditor miniMapEditor;
	public Items(MapDisplayPanel mapDisplayPanel, Map map) {
		try {
			islandbuffer = ImageIO.read(new File("pic\\cursor\\island.png"));
			lowseabuffer = ImageIO.read(new File("pic\\cursor\\lawsea.png"));
			highseabuffer = ImageIO.read(new File("pic\\cursor\\highsea.png"));
			ironbuffer = ImageIO.read(new File("pic\\cursor\\iron mine.png"));
			treebuffer = ImageIO.read(new File("pic\\cursor\\tree.png"));
			smallfishbuffer = ImageIO.read(new File("pic\\cursor\\smallFish.png"));
			bigfishbuffer = ImageIO.read(new File("pic\\cursor\\bigfish.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		islandpic = new ImageIcon( islandbuffer);
		lowseapic = new ImageIcon( lowseabuffer);
		highseapic = new ImageIcon( highseabuffer);
		ironpic = new ImageIcon( ironbuffer);
		treepic = new ImageIcon( treebuffer);
		smallfishpic = new ImageIcon(smallfishbuffer);
		bigfishpic = new ImageIcon( bigfishbuffer);
		this.map = map;
		setBackground(new Color(100, 100, 100, 100));
		this.mapDisplayPanel=mapDisplayPanel;
		try {
			miniMapEditor=new MiniMapEditor(mapDisplayPanel.getMap(), mapDisplayPanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(miniMapEditor);
		setSize(getToolkit().getScreenSize().width, 220);
		setLocation(0,getToolkit().getScreenSize().height-220);
		setLayout(null);
		specifyButton(island,100,islandpic);
		specifyButton(seaLow,200,lowseapic);
		specifyButton(highSea,300,highseapic);
		specifyButton(iron,400,ironpic);
		specifyButton1(newmap,800);
		specifyButton1(save,900);
		specifyButton1(load,1000);
		specifyButton(tree,500,treepic);
		specifyButton1(preview,1100);
		specifyButton(smallFish,600,smallfishpic);
		specifyButton(bigFish,700,bigfishpic);
		
		setVisible(true);
	}
	private void specifyButton(JButton temp, int pos, ImageIcon pic) {
		temp.setSize(100, 50);
		temp.setLocation(pos, 40);
		temp.addActionListener(this);
		temp.addKeyListener(this);
		this.add(temp);
		temp.setIcon(pic);
		temp.setDisabledIcon(null);
		temp.setBorder(null);
		temp.setContentAreaFilled(false);
	}
	private void specifyButton1(JButton temp, int pos) {
		temp.setSize(100, 50);
		temp.setLocation(pos, 40);
		temp.addActionListener(this);
		temp.addKeyListener(this);
		this.add(temp);

	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==island){
			image = toolkit.getImage("pic\\island\\50\\0000.png");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_ISLAND, this);
		}
		if(e.getSource()==seaLow){
			image = toolkit.getImage("pic\\low sea\\0000.png");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_LOW_SEA, this);
		}
		if(e.getSource()==highSea)
		{
			image = toolkit.getImage("pic\\sea\\sea52.jpg");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_HIGH_SEA, this);
		}
		if(e.getSource()==iron)
		{
			image = toolkit.getImage("pic\\resource\\iron\\iron mine.png");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_IRON, this);
		}
		if(e.getSource()==tree)
		{
			image = toolkit.getImage("pic\\resource\\tree\\1\\1_2.png");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_TREE, this);
		}
		if(e.getSource()==newmap)
		{
			int s=200,d=200;
			System.out.println(s);
			NewMap n =new NewMap(this);
			System.out.println(s);
			if(n.mapsize==50)
//			{
//				
//				s=50;
//				d=50;
//			}
//			if(n.mapsize==100)
//			{
//				s=100;
//				d=100;
//			}
//			if(n.mapsize==150)
//			{
//				s=150;
//				d=150;
//			}
//			if(n.mapsize==200)
//			{
//				s=200;
//				d=200;
//			}
//			
			System.out.println(s);
//			map=new Map(s,d);
//			mapDisplayPanel.setMap(map);
//			miniMapEditor.setmap(map);
//			miniMapEditor.setsx(200/map.locations.length);
//			miniMapEditor.sety(200/map.locations[0].length);
//			mapDisplayPanel.x=0;
//			mapDisplayPanel.y=0;
			//miniMapEditor=new MiniMapEditor(mapDisplayPanel.map, mapDisplayPanel);
		}
		if(e.getSource()==save)
		{
			Store s= new Store();
			s.save(map);
		}
		if(e.getSource()==load){
			Store s= new Store();
			Map p=s.load();
			mapDisplayPanel.setMap(p);
			miniMapEditor.setmap(p);
			miniMapEditor.setsx(200/map.locations.length);
			miniMapEditor.sety(200/map.locations[0].length);
		}
		if(e.getSource()==preview){
			mapDisplayPanel.sms(Messages.PREVIEW, this);
		}
		if(e.getSource()==smallFish){
			image = toolkit.getImage("pic\\resource\\fish\\smallFish.png");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_SMALL_FISH, this);
		}
		if(e.getSource()==bigFish){
			image = toolkit.getImage("pic\\resource\\fish\\vall2.png");
			Cursor c=  new MyCursor(image).setMyCursor();
			mapDisplayPanel.setCursor(c);
			mapDisplayPanel.sms(Messages.DRAW_BIG_FISH, this);
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

