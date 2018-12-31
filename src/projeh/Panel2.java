package projeh;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Panel2 extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private MapDisplayPanel mapDisplayPanel;
	ImageIcon back;
	Items items;
	Map map;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image;
	MiniMapEditor miniMapEditor;
	public Panel2() {
		back=new ImageIcon("hold.jpg");
		setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		map=new Map(50	,50);
	
		try {
			setMapDisplayPanel(new MapDisplayPanel(50,map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(getMapDisplayPanel());
		
		
		//miniMapEditor=new MiniMapEditor(mapDisplayPanel.map, mapDisplayPanel);
		items = new Items (getMapDisplayPanel(),map);
		//add(items);
		setLayout(null);
		

		JLabel jl=new JLabel();
		jl.setSize(d.width,d.height);
		jl.setIcon(back);
		
		setVisible(true);
		getMapDisplayPanel().setFocusable(true);
	}

	public void setmap(Map map){
		this.map=map;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

			
	}




	public MapDisplayPanel getMapDisplayPanel() {
		return mapDisplayPanel;
	}


	public void setMapDisplayPanel(MapDisplayPanel mapDisplayPanel) {
		this.mapDisplayPanel = mapDisplayPanel;
	}

}
