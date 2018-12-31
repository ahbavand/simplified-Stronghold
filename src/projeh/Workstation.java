package projeh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Workstation extends Thread {

	Server s;
	Socket client;
	InputStream input;
	OutputStream output;

	public Workstation(Socket client, Server s) {
		this.client = client;
		System.out.println(client.getInetAddress().getHostAddress());
		
		this.s = s;
		try {
			input = client.getInputStream();
			output = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		boolean flag = false;
		while (!flag) {
			byte[] b = new byte[160000];
			try {
				int i = input.read(b);
				if (i == -1) {
					flag = true;
					continue;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					input.close();
					output.close();
					client.close();
					flag = true ;
					break;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}
				s.sendToAll((new String(b)).trim());

		}
	}
}
