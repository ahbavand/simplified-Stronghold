package projeh;

import game.ChooseMap;
import game.GameDisplayPanel;
import game.StartGame;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JLabel;

public class Client implements Runnable {

	InputStream input;
	OutputStream output;
	Socket socket;
	public int mapSender=0  ;
	Map map ;
	String mapCode;
	int mapSize;
	private int id;
	String rightClick;
	String userName;
	Image userImage;
	JLabel lable ;
	public boolean click=false,isWaterfront=false;
	public int mousex,mousey,selectedId,taskNumber;
	public Client(String hostname,Image userImage , String userName) {

		this.userImage=userImage;
		this.userName=userName;
		
		try {
			socket = new Socket(InetAddress.getByName(hostname),5005);
			input = socket.getInputStream();
			output = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte [] I = new byte[10];//getting id from the server
		try {
			input.read(I);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setId(Integer.parseInt(new String(I).trim()));

	}


	public void sendClick(String s){
		try {
			output.write((s).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void sendMap(){//I'm sure it's right
		
		String mapCode = "0" ;
		System.out.println(map.width);
		for(int i = 0 ; i < map.width ; i++){
			for(int j = 0 ; j < map.height ; j++){
				mapCode +=Integer.toString(map.getLocationIJ(i, j).D1)+Integer.toString(map.getLocationIJ(i, j).D3)+Integer.toString(map.getLocationIJ(i, j).D6)+Integer.toString(map.getLocationIJ(i, j).jazire);
			}
		}
		try {
			output.write(( mapCode.substring(1) + "\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recieveClick(String r){
		String[] s = r.split("-");
		if(s.length==3){
			isWaterfront = false;
			selectedId = Integer.parseInt(s[0]);
			mousex =  Integer.parseInt(s[1]);
			mousey =  Integer.parseInt(s[2]);
		}
		else {
			isWaterfront = true;
			selectedId = Integer.parseInt(s[0]);
			taskNumber = Integer.parseInt(s[1]);
		}
	}
	
	public synchronized Map recieveMap(){//I'm sure it's right
		int i = 0;
		String rm = null;
			byte [] b = new byte[160000];
			try {
				i = input.read(b);
				System.out.println(i);
				if (i != -1){
					rm = new String(b).trim();
					mapSize=rm.length();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		mapSize = (int) Math.sqrt(mapSize/4);
		System.out.println("mapSize : "+ mapSize);
		String now;
		Map m = new Map(mapSize, mapSize);
		for(int k = 0 ; k < mapSize*4 ; k+=4){
			for(int l = 0 ; l < mapSize*4 ; l+=4){
				now = rm.substring(k*mapSize+l, k*mapSize+l+4);
				m.getLocations()[k/4][l/4].D1=Integer.parseInt(now.substring(0,1));
				m.getLocations()[k/4][l/4].D3=Integer.parseInt(now.substring(1,2));
				m.getLocations()[k/4][l/4].D6=Integer.parseInt(now.substring(2,3));
				m.getLocations()[k/4][l/4].jazire=Integer.parseInt(now.substring(3,4));
			}
		}
		return m ;
	}

	@Override
	public void run() {
			
		if(mapSender==1){
			while(Server.lastElement<ChooseMap.countrynumber||map==null){
				System.out.println("Waiting for all players to join or the map is not set now !");
			}
			sendMap();
		}
		
		setMap(recieveMap());
		try {
			new StartGame(getMap(), new GameDisplayPanel(50,getMap(),this));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			
		int i = 0;
		while (i != -1){
			byte [] b = new byte[1600];
			try {
				i = input.read(b);
				if (i != -1)recieveClick(new String(b).trim());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			click=true;
		}
		
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Map getMap() {
		return map;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public void setClick(boolean click) {
		this.click = click;
	}


}
