package objects;

public class SmallFish {
	private int food=500;
	public int x,y;
	public SmallFish(int x,int y){
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