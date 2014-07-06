package net.akutenshi.XO.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class XOClientSocket {
	private Socket clientChatSocket = null;
	private String name;
	
	public XOClientSocket(String host, int port, String name) {
		this.name = name;
		try {
			clientChatSocket = new Socket(host, port);			
		} catch (IOException e) {
			System.out.print("Can't find server\n");
			e.printStackTrace();
		}
		
		if (clientChatSocket != null) {
			try {
				DataInputStream in = new DataInputStream(clientChatSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientChatSocket.getOutputStream());
				out.writeUTF(name + "\n");
				out.flush();
				//clientChatSocket.close();
			} catch (IOException e) {
				System.out.print("Can't open stream\n");
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect() {
		if (clientChatSocket != null) {
			try {
				clientChatSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
