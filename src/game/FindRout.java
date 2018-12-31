package game;

import java.awt.Dimension;
import java.util.Vector;

import sun.misc.Queue;


public class FindRout {
	Vector<Rout> routs = new Vector<Rout>();
	int[][] table;	//har khane do vaz darad ya ghabel raftan ast =1 ya nemitavan dar aan vared shod =0
	int[][] wasHere;//aya ghablan dar in khaneh boode am? baraye masiryabi mored estefadeh gharar migirad
	int width;//toole matris
	int height;//arze matris
	Vector<String> str = new Vector<String>();
	int minLength = 10000;

	public FindRout(int m, int n, int[][] table) { //constructor
		routs = new Vector<Rout>();
		this.table = table;
		width = m;
		height = n;
		wasHere = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				wasHere[i][j] = 0;
	}
		
	
	//in tike asli hastesh
	public Vector<String> findRout(int x1, int y1, int x2, int y2) throws InterruptedException {
		Queue<Dimension> s = new Queue<Dimension>();//safi az gereh ha
		Queue<Rout> routs = new Queue<Rout>();//safi az masir ha
		s.enqueue(new Dimension(x1, y1));
		routs.enqueue(new Rout());
		while (!s.isEmpty()) {
			Dimension d = s.dequeue();
			Rout r = routs.dequeue();
			int x = d.width;
			int y = d.height;
			if (x == x2 && y == y2){
				return r.str;
			}
			if (wasHere[x][y] != 1) {
				wasHere[x][y] = 1;
				
				
				
				
				if (x != 0 && table[x-1][y]==1) {
					s.enqueue(new Dimension(x - 1, y));
					routs.enqueue(new Rout(r, "L"));
				}
				if (x != width - 1 && table[x+1][y]==1) {
					s.enqueue(new Dimension(x + 1, y));
					routs.enqueue(new Rout(r, "R"));
				}
				if (y != 0 && table[x][y-1]==1) {
					s.enqueue(new Dimension(x, y - 1));
					routs.enqueue(new Rout(r, "U"));
				}
				if (y != height - 1 && table[x][y+1]==1) {
					s.enqueue(new Dimension(x, y + 1));
					try {
						routs.enqueue(new Rout(r, "D"));          
					} catch (Exception e) {
					}
				}
				
				if (x != 0 && y!=0 && table[x-1][y-1]==1) {
					s.enqueue(new Dimension(x - 1, y-1));
					routs.enqueue(new Rout(r, "LU"));
				}
				if (x != 0 && y!=height-1 && table[x-1][y+1]==1) {
					s.enqueue(new Dimension(x - 1, y+1));
					routs.enqueue(new Rout(r, "LD"));
				}
				if (x != width-1 && y!=0 && table[x+1][y-1]==1) {
					s.enqueue(new Dimension(x + 1, y-1));
					routs.enqueue(new Rout(r, "RU"));
				}
				if (x != width-1 && y!=height-1 && table[x+1][y+1]==1) {
					s.enqueue(new Dimension(x + 1, y+1));
					routs.enqueue(new Rout(r, "RD"));
				}
				
			}
		}
		Vector<String> masir = new Vector<String>(0);
		return masir;
	}
//	public static void main(String[] args) throws InterruptedException {
//		int[][] table = new int[1000][1000];
//		// dar inja shoma bayad yek matris az 0,1 ha dorost konid ke 0 yani nemitavan vared shod 1 yani mitavan vared shod
//		//ideh hayi baraye dorost kardan 0,1 dashtam ke be jay An behesh kollan location ra bedahim agar zendeh mandam amali mikonam va agar nah amali konid
//		for (int i = 0; i < 1000; i++)
//			for (int j = 0; j < 1000; j++)
//				table[i][j] = 1;//masalan hame ash 1 hastand
//		FindRout test = new FindRout(400, 400, table);//yek object az find rout dorost mishavad
//		//System.out.println(System.currentTimeMillis());
//		System.out.println(test.findRout(399, 399, 0, 0));//khorouji in findrout vectori az string hast be sort RLRLRLUDR
//		//System.out.println(System.currentTimeMillis());
//	}
}
