package projeh;

import java.util.Vector;

public class hashie {
	private int x;
	private int y;
	private int number;
	private Vector<Integer> masafat=new Vector<Integer>();
	
	
	

	public hashie(int x,int y,int number) {
		this.setX(x);
		this.setY(y);
		this.setNumber(number);
		// TODO Auto-generated constructor stub
	}




	public Vector<Integer> getMasafat() {
		return masafat;
	}




	public void setMasafat(Vector<Integer> masafat) {
		this.masafat = masafat;
	}




	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}




	public int getNumber() {
		return number;
	}




	public void setNumber(int number) {
		this.number = number;
	}

}
