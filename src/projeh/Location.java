package projeh;
public class Location implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int D1;//SEA OR ISLAND 1=sea-high 2=sea-low 3=island
	public int D3;//WHA THE TYPE OF RESOURCE 0=null 1=bigFish 2=smallFish 3=tree 4=iron 
	public int jazire=0;/// -1=highsea 0=low sea 1=jazire avval 2=.....
	public int D6=0;
	public int D7=0;//////0=adam hast   1=adam nist
	public Location(int d1, int d3) {
		super();
		D1 = d1;
		D3 = d3;
		
		
	}
	public int getD1() {
		return D1;
	}
	public void setD1(int d1) {
		D1 = d1;
	}
	public int getD3() {
		return D3;
	}
	public void setD3(int d3) {
		D3 = d3;
	}
	public int getID(){
		return (D1*10+D3);
	}
	public int getJazire() {
		return jazire;
	}
	public void setJazire(int jazire) {
		this.jazire = jazire;
	}
	public int getD6() {
		return D6;
	}
	public void setD6(int d6) {
		D6 = d6;
	}
	
}
