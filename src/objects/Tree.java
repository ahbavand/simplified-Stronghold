package objects;

public class Tree {
	private int woodSize ;
	int x;
	int y;
	public int jazirenumber;
	int id;
	public  Tree(int x,int y, int id,int jazirenumber){
		this.x=x;
		this.y=y;
		this.id=id;
		woodSize=2000;
		this.jazirenumber=jazirenumber;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public synchronized int getwoodSize() {
		return woodSize;
	}

	public synchronized void setwoodSize(int woodSize) {
		this.woodSize = woodSize;
	}
	public synchronized int takeWood(int meghdar){
		if((woodSize>=20)||(woodSize<20&&meghdar<=woodSize)){
			woodSize=woodSize-meghdar;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return meghdar;}
			else{
				
			int	temp=woodSize;
				woodSize=0;
				return temp; 
			}
		}
	
	public void updatePerYear(){
		woodSize += woodSize*0.1;
			if(woodSize>10000)
				woodSize=10000;
	}

	}
