package projeh;

public interface Messages {
	static int DRAW_ISLAND=1;
	static int DRAW_LOW_SEA=2;
	static int DRAW_HIGH_SEA=3;
	static int DRAW_IRON=4;
	static int DRAW_TREE=5;
	static int DRAW_BIG_FISH=7;
	static int DRAW_SMALL_FISH=8;
	static int PREVIEW=6;
	public void sms(int message,Object sender) ;
}
