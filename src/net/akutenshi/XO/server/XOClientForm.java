package net.akutenshi.XO.server;

import java.net.Socket;

public class XOClientForm {
	private String clientName;
	private Socket clientSocket;
	
	public XOClientForm(Socket client) {
		clientSocket = client;
	}
	public XOClientForm() {
		clientName = null;
		clientSocket = null;
	}
	
	public void setName(String name) {
		clientName = name;
	}
	
	public String getName() {
		return clientName;
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}

}
