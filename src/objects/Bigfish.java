package objects;
public class Bigfish {
	private int food=1000;
	public int x,y;
	public Bigfish(int x,int y){
		this.x=x;
		this.y=y;
		
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}
	public synchronized int takefood(int meghdar){
		if(food>=meghdar){
			food=food-meghdar;
			return meghdar;}
			else{
				
			int	temp=food;
				food=0;
				return temp; 
			}
		}
	

	

	
	
	

}
