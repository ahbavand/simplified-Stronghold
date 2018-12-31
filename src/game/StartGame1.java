package game;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import projeh.Map;

public class StartGame1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameDisplayPanel gameDisplayPanel;
	MiniMap minimap;
	Map map;
	public StartGame1(Map map) throws IOException  {
		setLayout(null);
		this.map=map;
	    JLayeredPane  layer= getLayeredPane();
	    gameDisplayPanel=new GameDisplayPanel(50,map);
	    minimap=new MiniMap(map,gameDisplayPanel);
	    minimap.painting();
		gameDisplayPanel.setLocation(0,0);
		setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		this.setUndecorated(true);
        this.setResizable(false);       
        setVisible(true);
        layer.add(gameDisplayPanel,new Integer(1));
        layer.add(minimap, new Integer(2));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

}
