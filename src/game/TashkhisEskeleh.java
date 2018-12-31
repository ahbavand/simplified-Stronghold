package game;

import java.util.Vector;

import projeh.Map;
import projeh.hashie;

public class TashkhisEskeleh {
	Map map;
	int n;
	int numberjazire=1;
	Vector<hashie> hashies=new Vector<hashie>(); 
	Vector<Integer> eskele=new Vector<Integer>();
	int number=1;



	public TashkhisEskeleh(Map map,int n) {
		this.map=map;
		this.n=n;
		setjazireadad(map);
        setkenar();
        setvec();
        if(n<=numberjazire-1)
		islandset(n);
        else
        	ilandset(n);

		
	}
	public void setjazireadad(Map map){
		for(int i=1;i<map.getLocations().length;i++)
			for(int j=1;j<map.getLocations()[0].length;j++)
				map.locations[i][j].jazire=0;
		
		for(int i=1;i<map.getLocations().length;i++)
			for(int j=1;j<map.getLocations()[0].length;j++){

				if(map.getLocations()[i][j].getD1()==3&&map.getLocations()[i][j].getJazire()==0){
					recer(i,j);
					numberjazire++;
				}
			}
		
		
	}
	public void recer(int i,int j){
		if(map.getLocations()[i][j].getD1()==3&&map.getLocations()[i][j].getJazire()==0){
			map.getLocations()[i][j].jazire=numberjazire;
			try {

				recer(i-1,j-1);
				recer(i-1,j);
				recer(i-1,j+1);
				recer(i,j-1);
				recer(i,j+1);
				recer(i+1,j-1);
				recer(i+1,j);
				recer(i+1,j+1);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
	}
	public void setkenar(){
		System.out.println(numberjazire);
		for(int i=1;i<map.getLocations().length-1;i++)
			for(int j=1;j<map.getLocations()[0].length-1;j++){
				try {
					if(map.getLocations()[i][j].getD1()==3&&(map.getLocations()[i][j-1].getD1()!=3||map.getLocations()[i][j+1].getD1()!=3||map.getLocations()[i-1][j].getD1()!=3||map.getLocations()[i+1][j].getD1()!=3)){
						hashies.addElement(new hashie(i, j,map.getLocations()[i][j].getJazire()));
						number++;

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
	}
	public void setvec(){
		for(int i=0;i<hashies.size();i++)
			for(int j=0;j<hashies.size();j++){
				int s=(int) Math.sqrt((hashies.elementAt(i).getX()-hashies.elementAt(j).getX())*(hashies.elementAt(i).getX()-hashies.elementAt(j).getX())+(hashies.elementAt(i).getY()-hashies.elementAt(j).getY())*(hashies.elementAt(i).getY()-hashies.elementAt(j).getY()));
				hashies.elementAt(i).getMasafat().addElement(s);
			}
	}
	public void islandset(int n){
		int max=0;
		boolean ispor=true;
		int l=0;
		int gav=0;
		if(n==1){
			eskele.addElement(0);
		map.getLocations()[hashies.elementAt(eskele.lastElement()).getX()][hashies.elementAt(eskele.lastElement()).getY()].D6=1;
		}
		else {
			islandset(n-1);
			for(int i=0;i<hashies.size();i++){
				for(int q=0;q<eskele.size();q++){
					if(hashies.elementAt(eskele.elementAt(q)).getNumber()==hashies.elementAt(i).getNumber())
						ispor=false;
				}
				if(ispor){
				for(int j=0;j<eskele.size();j++){
					l=l+hashies.elementAt(i).getMasafat().elementAt(eskele.elementAt(j));
				}
				if(l>max){
					max=l;
					gav=i;
				}
				l=0;
				}
				else
					ispor=true;

			}
			eskele.addElement(gav);
			map.getLocations()[hashies.elementAt(eskele.lastElement()).getX()][hashies.elementAt(eskele.lastElement()).getY()].setD6(1);
			
			
		}
	
	}
	public void ilandset(int  n){
		int max=0;
		int l=0;
		int gav=0;
		if(n==1){
			eskele.addElement(0);
		map.getLocations()[hashies.elementAt(eskele.lastElement()).getX()][hashies.elementAt(eskele.lastElement()).getY()].D6=1;
		}
		else {
			ilandset(n-1);
			for(int i=0;i<hashies.size();i++){
				for(int q=0;q<eskele.size();q++){
				for(int j=0;j<eskele.size();j++){
					l=l+hashies.elementAt(i).getMasafat().elementAt(eskele.elementAt(j));
				}
				if(l>max){
					max=l;
					gav=i;
				}
				l=0;
				}
			}
			eskele.addElement(gav);
			map.getLocations()[hashies.elementAt(eskele.lastElement()).getX()][hashies.elementAt(eskele.lastElement()).getY()].setD6(1);
			
			
		}

	}
	

	


	


}
