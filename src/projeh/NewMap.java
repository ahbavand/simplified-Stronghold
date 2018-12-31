package projeh;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class NewMap extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton submit;
	JButton back;
	Items items;
	Integer mapsize=50;
	JRadioButton[] radio={new JRadioButton(),new JRadioButton(),new JRadioButton(),new JRadioButton()};	
	JLabel[] label={new JLabel("50 * 50"),new JLabel("100 * 100"),new JLabel("150 * 150"),new JLabel("200 * 200")};
	ButtonGroup bt=new ButtonGroup();
	JSlider slid= new JSlider();
	public NewMap(Items items) {
		this.setUndecorated(true);
        this.setResizable(false);
		setSize(1*getToolkit().getScreenSize().width/7,2*getToolkit().getScreenSize().height/7);
		setLayout(null);
		setLocationRelativeTo(null);
		
		specifyRadioButton(radio[0], 20, 10,"1");
		specifyRadioButton(radio[1], 20, 40,"2");
		specifyRadioButton(radio[2], 20, 70,"3");
		specifyRadioButton(radio[3], 20, 100,"4");
		
		specifyLabel(label[0], 60, 2,100);
		specifyLabel(label[1], 60, 30,100);
		specifyLabel(label[2], 60, 60,100);
		specifyLabel(label[3], 60, 90,100);
		
		
		submit= new JButton("submit");
		submit.setLocation(110, 160);
		submit.setSize(70,28);
		submit.addActionListener(this);
		getContentPane().add(submit);
		
		back= new JButton("back");
		back.setLocation(10, 160);
		back.setSize(70,28);
		back.addActionListener(this);
		getContentPane().add(back);
		this.items=items;
		setVisible(true);
	}
	private void specifyRadioButton(JRadioButton temp,int x, int y,String name) {

		temp.setSize(25, 25);
		temp.setName(name);
		temp.setLocation(x, y);
		getContentPane().add(temp);
		bt.add(temp);
		temp.addActionListener(this);
		
	}
	private void specifyLabel(JLabel temp, int x, int y,int length) {
		temp.setSize(length, 40);
		temp.setLocation(x, y);
		temp.setForeground(Color.DARK_GRAY);
		getContentPane().add(temp);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

			if(e.getSource()==radio[0]){
				mapsize=50;
				System.out.println("salam");
				items.map=new Map(50,50);
				items.mapDisplayPanel.setMap(items.map);
				items.miniMapEditor.setmap(items.map);
				items.miniMapEditor.setsx(200/items.map.locations.length);
				items.miniMapEditor.sety(200/items.map.locations[0].length);
				items.mapDisplayPanel.x=0;
				items.mapDisplayPanel.y=0;

				
			}
			if(e.getSource()==radio[1]){
				mapsize=100;
				items.map=new Map(100,100);
				items.mapDisplayPanel.setMap(items.map);
				items.miniMapEditor.setmap(items.map);
				items.miniMapEditor.setsx(200/items.map.locations.length);
				items.miniMapEditor.sety(200/items.map.locations[0].length);
				items.mapDisplayPanel.x=0;
				items.mapDisplayPanel.y=0;

				
				
			}
			if(e.getSource()==radio[2]){
				mapsize=150;
				items.map=new Map(150,150);
				items.mapDisplayPanel.setMap(items.map);
				items.miniMapEditor.setmap(items.map);
				items.miniMapEditor.setsx(200/items.map.locations.length);
				items.miniMapEditor.sety(200/items.map.locations[0].length);
				items.mapDisplayPanel.x=0;
				items.mapDisplayPanel.y=0;

			}
			if(e.getSource()==radio[3]){
				mapsize=200;

				items.map=new Map(200,200);
				items.mapDisplayPanel.setMap(items.map);
				items.miniMapEditor.setmap(items.map);
				items.miniMapEditor.setsx(200/items.map.locations.length);
				items.miniMapEditor.sety(200/items.map.locations[0].length);
				items.mapDisplayPanel.x=0;
				items.mapDisplayPanel.y=0;

			}
			if(e.getSource()==back)
				dispose();
			if(e.getSource()==submit)
				if(mapsize==null)
					JOptionPane.showMessageDialog(null, "choose a size");
				else
					dispose();
			
			
					
	}
//	public static void main(String[] args) {
//		new NewMap();
//	}
}
