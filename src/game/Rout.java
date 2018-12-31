package game;

import java.util.Vector;

public class Rout {
	Vector<String> str;

	// har masir majmooehi az L,R,U,D hast ke dar in vector negahdari mishavad
	public Rout() {
		str = new Vector<String>();
	}

	public Rout(Vector<String> str) {
		super();
		this.str = str;
	}

	public Rout(Rout rout, String s) {// in constructor baraye ijad yek masir ba
										// yek harkat jadid dar entehast masaln
										// masir LLLRLLU rad darim va dar Akhar
										// mikhahim be samt L harkat konim
		super();
		Vector<String> st = (Vector<String>) (rout.str).clone();
		st.addElement(s);
		this.str = st;
	}

}

