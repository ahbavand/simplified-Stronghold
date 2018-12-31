package game;

import java.util.Vector;

import objects.Waterfront;

public class Khordan implements Runnable {
	Vector<Waterfront> waterfronts;
	
	public Khordan(Vector<Waterfront> waterfronts){
		this.waterfronts=waterfronts;
		new Thread(this).start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("salam");

			for(int i=0;i<waterfronts.size();i++)
			//	synchronized (this) {
				
				
				for(int j=5;j<waterfronts.elementAt(i).getAdams().size();j++)
					try {
					//	waterfronts.elementAt(i).getAdams().elementAt(j).sethealth();
						if(waterfronts.elementAt(i).takefood(5)==true)
							;
						else
							waterfronts.elementAt(i).getAdams().elementAt(j).sethealth();
						
					} catch (Exception e) {
						// TODO: handle exception
					}
		//		}
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		}
		
		
	}

}



















