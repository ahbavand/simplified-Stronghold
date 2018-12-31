package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import projeh.Map;
import projeh.Panel2;
import projeh.Store;

public class ChooseMap1 extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField address ;
	JButton submit;
	JButton browse;
	JButton start;
	JButton back;
	//Panel2 panel2;
	
	boolean submited=false;
	boolean choosemap=false;
	
	JRadioButton[] radio={new JRadioButton(),new JRadioButton(),new JRadioButton(),new JRadioButton()};
	JLabel[] label={new JLabel("map1"),new JLabel("map2"),new JLabel("map3"),new JLabel("choose from hard"),new JLabel("number of countries")};
	ButtonGroup bt=new ButtonGroup();
	
	Map map= new Map(50,50);
	GameBasePreview gameBasePreview;
	int countrynumber;
	TashkhisEskeleh portpos;
	public ChooseMap1() {
		
		this.setUndecorated(true);
        this.setResizable(false);
		setSize(2*getToolkit().getScreenSize().width/5,1*getToolkit().getScreenSize().height/2);
		setLayout(null);
		setLocationRelativeTo(null);
		
        String[] quantities1 = { "0", "1", "2", "3", "4" };
        JComboBox quantitiesCB = new JComboBox(quantities1);
        quantitiesCB.setLocation(250, 270);
        quantitiesCB.setSize(70, 30);
        quantitiesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox) e.getSource();
                String currentQuantity = (String) combo.getSelectedItem();
                
                try {
                	countrynumber = Integer.valueOf(currentQuantity);
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                }
            }
        });

        getContentPane().add(quantitiesCB);
		
		specifyRadioButton(radio[0], 340, 30,"1");
		specifyRadioButton(radio[1], 340, 60,"2");
		specifyRadioButton(radio[2], 340, 90,"3");
		specifyRadioButton(radio[3], 340, 120,"4");
		
		specifyLabel(label[0], 360, 25,100);
		specifyLabel(label[1], 360, 55,100);
		specifyLabel(label[2], 360, 85,100);
		specifyLabel(label[3], 360, 115,100);
		specifyLabel(label[4], 120, 270,150);
		
		address = new JTextField();
		address.setLocation(340,170);
		address.setSize(170, 30);
		address.setVisible(false);
		getContentPane().add(address);
		
		submit= new JButton("submit");
		submit.setLocation(310, 300);
		submit.setSize(80,28);
		submit.addActionListener(this);
		getContentPane().add(submit);

		browse= new JButton("Browse");
		browse.setLocation(350, 210);
		browse.setSize(80,28);
		browse.addActionListener(this);
		browse.setVisible(false);
		getContentPane().add(browse);
		
		start= new JButton("Start");
		start.setLocation(400, 300);
		start.setSize(80,28);
		start.addActionListener(this);
		getContentPane().add(start);
		
		back= new JButton("back");
		back.setLocation(20, 300);
		back.setSize(80,28);
		back.addActionListener(this);
		getContentPane().add(back);
		
		try {
			gameBasePreview = new GameBasePreview(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().add(gameBasePreview);
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
	
	public int getCountrynumber() {
		return countrynumber;
	}
	
	public void setCountrynumber(int countrynumber) {
		this.countrynumber = countrynumber;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==radio[0])
		{

			browse.setVisible(false);
			address.setVisible(false);
			map=load("firstmap");

			try {
				
				gameBasePreview = new GameBasePreview(map);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameBasePreview.painting();
			add(gameBasePreview);
			gameBasePreview.repaint();
			choosemap=true;
		}
		
		if(e.getSource()==radio[1])
		{

			browse.setVisible(false);
			address.setVisible(false);
			map=load("map1");

			try {
				
				gameBasePreview = new GameBasePreview(map);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameBasePreview.painting();
			add(gameBasePreview);
			gameBasePreview.repaint();
			choosemap=true;
		}
		
		if(e.getSource()==radio[2])
		{

			browse.setVisible(false);
			address.setVisible(false);
			map=load("map2");

			try {
				
				gameBasePreview = new GameBasePreview(map);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameBasePreview.painting();
			add(gameBasePreview);
			gameBasePreview.repaint();
			choosemap=true;
		}
		
		if(radio[3].isSelected())
		{
			
			browse.setVisible(true);
			address.setVisible(true);
			
		}
//		if(!radio[3].isSelected())
//		{
//			
//			browse.setVisible(false);
//			address.setVisible(false);
//		}
		
		if(e.getSource()==browse){
			map=null;
			Store store= new Store();
			map=store.load();
			address.setText(store.getSelFile().getAbsolutePath());
			choosemap=true;
			try {
				
				gameBasePreview = new GameBasePreview(map);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameBasePreview.painting();
			add(gameBasePreview);
			gameBasePreview.repaint();
		}
		
		if(e.getSource()==submit)
		{
			submited=true;
			if(getCountrynumber()==0)
				JOptionPane.showMessageDialog(null, "choose number of countries!");
			else if(choosemap==false||map==null)
				JOptionPane.showMessageDialog(null, "choose a map!");
			else{	
			portpos=new TashkhisEskeleh(map, getCountrynumber());
			try {
				
				gameBasePreview = new GameBasePreview(portpos.map);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameBasePreview.painting();
			add(gameBasePreview);
			gameBasePreview.repaint();
			}
			
		}

		
		if(e.getSource()==start)
		{
			if(choosemap==false||map==null)
				JOptionPane.showMessageDialog(null, "choose a map!");
			else if(submited==false)
				JOptionPane.showMessageDialog(null, "prees 'submit' to add countries!");
			else if(getCountrynumber()==0)
				JOptionPane.showMessageDialog(null, "choose number of countries!");
			else
			{
				try {
					new StartGame1(portpos.map);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource()==back)
		{
			dispose();
		}
		
	}		

	private Map load(String filename) {
		Map m = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(filename);
	         if(fileIn.available() != 0)
	         {
	        	 ObjectInputStream in = new ObjectInputStream(fileIn);
		         m = (Map) in.readObject();
		         in.close();
		         fileIn.close();
	        
	         }
	        }catch(IOException i)
	      {
	         i.printStackTrace();
	         System.err.println("Wrong!");
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println(" not found");
	         c.printStackTrace();
	      } 
	      return m;		
	}


}