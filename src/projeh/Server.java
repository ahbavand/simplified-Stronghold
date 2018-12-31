package projeh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {

	InputStream input;
	OutputStream output;
	ServerSocket ss;
	Workstation [] v;
	static int lastElement=0;
	public Server(int playerNumber) {
		v = new Workstation[playerNumber];
		try {
			ss = new ServerSocket(5005);// port number, MaxClients
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void execute() {
		while (lastElement<v.length) {
			try {
				Socket client = ss.accept();
				Workstation w = new Workstation(client, this);
				v[lastElement] = w ;
				lastElement++;
				w.output.write(lastElement+47);
				w.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void sendToAll(String msg) {
		for (int i = 0 ; i <lastElement ;i++) {
			try {
				v[i].output.write(msg.getBytes());
			} catch (SocketException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Server(4).execute();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}

}	