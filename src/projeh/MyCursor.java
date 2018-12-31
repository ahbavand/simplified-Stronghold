package projeh;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class MyCursor {
	Image image;
	public MyCursor(Image image) {
		this.image=image;
	}
	public java.awt.Cursor setMyCursor() {
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
		  Point hotSpot = new Point(0,0);
		  java.awt.Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Pencil");
		  return cursor;
	}

}
