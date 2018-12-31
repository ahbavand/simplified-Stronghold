package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import projeh.Map;

public class StartGame extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameDisplayPanel gameDisplayPanel;
	MiniMap minimap;
	Map map;
	
	public StartGame(Map map,GameDisplayPanel gameDisplayPanel) {
		// TODO Auto-generated constructor stub
		setLayout(null);
		this.map=map;
		this.gameDisplayPanel=gameDisplayPanel;
		JLayeredPane  layer= getLayeredPane();  
		try {
			minimap=new MiniMap(map,gameDisplayPanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		minimap.painting();
		gameDisplayPanel.setLocation(0,0);
		setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		this.setUndecorated(true);
	    this.setResizable(false);       
	    setVisible(true);
	    layer.add(gameDisplayPanel,new Integer(1));
	    layer.add(minimap, new Integer(2));
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE)
			getContentPane().add(new Pause(this));
	}

}
