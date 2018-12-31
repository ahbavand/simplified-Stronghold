package objects;


public class Iron {
	int x,y;
	int id;
	int ironSize;
	public int jazirenumber;
	public Iron(int x, int y, int id,int jazirenumber) {
		this.x=x;
		this.y=y;
		this.id=id;
		this.jazirenumber=jazirenumber;
		ironSize=10000;
		
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIronSize() {
		return ironSize;
	}
	public void setIronSize(int ironSizee) {
		this.ironSize = ironSizee;
	}
	public synchronized int takeIron(int taken){
		if(ironSize>taken){
			ironSize-=taken;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return taken;
		}
		else{
			int temp=ironSize;
			ironSize=0;
			return temp;
		}
	}
	public void updatePerYear(){
		ironSize += ironSize*0.1;
			if(ironSize>10000)
				ironSize=10000;
	}
	
}