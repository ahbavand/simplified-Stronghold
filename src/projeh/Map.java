package projeh;

import java.io.Serializable;

public class Map implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int size;
	public Location[][] locations;// = new Location[][200];
	public int height;
	public int width;

	public Map(int width, int height) {
		this.height = height;
		this.width = width;
		locations = new Location[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				locations[i][j] = new Location(1, 0);
	}

	public Location[][] getLocations() {
		return locations;
	}

	public void setLocations(Location[][] locations) {
		this.locations = locations;
	}
	public Location getLocationIJ(int i, int j) {
		return locations[i][j];
	}

	public void setLocationIJ(int i, int j, Location l) {
		locations[i][j] = l;
	}

	public String ckeckBeside(int i, int j,int textureState) {
		char au = '1', ar = '1', ad = '1', al = '1';								
			if (j % 199 != 0 && locations[i][j - 1].D1 != textureState)
				au = '0';
		if (locations[i][j + 1].D1 != textureState)
			ad = '0'; 
		if (i%199 !=0 && locations[i - 1][j].D1 != textureState)
			al = '0';
		if (locations[i + 1][j].D1 != textureState)
			ar = '0';
		return au + "" + ar + "" + ad + "" + al;
	}
}