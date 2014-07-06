package net.akutenshi.XO.server;

import java.awt.List;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class XOServer {
	private ArrayList<XOClientForm> clientList = new ArrayList<>(0);
	private int chatPort;
	private int gamePort;
	private String log = new String("");
	private XOChatServerThread chatServerThread = null;
	
	public XOServer() {
		this.chatPort = 4444;
		this.gamePort = 4445;
	}
	
	public XOServer(int somePort) {
		this.chatPort = somePort;
		this.gamePort = somePort + 1;
	}
	
	public XOServer(int chatPort, int gamePort) {
		this.chatPort = chatPort;
		this.gamePort = gamePort;
	}
	
	public void LaunchChatServer() {
		log += "Launching chat server...\n";		
		try {
			ServerSocket chatServer = new ServerSocket(chatPort, 0, InetAddress.getLocalHost());
			log += "Chat server is launching!\n";
			chatServerThread = new XOChatServerThread(chatServer, this);
		} catch (IOException e) {
			log += "Can't launching server! Port " + Integer.toString(chatPort) + " is using now.\n";
			e.printStackTrace();
		}
	}
	
	public void setNewClient(XOClientForm aClient) {
		clientList.add(aClient);
	}
	
	public int findClientIndex(String name) {
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i).getName() == name) {
				return i;
			}
		}
		return -1;
	}
	
	public void removeClientFromList(String name) {
		clientList.remove(findClientIndex(name));
	}
	
	public void addToLog(String in) {
		log += in;
	}
	
	public String sendLog() {
		String out = log;
		log = "";
		return out;
	}
	
	public XOClientForm getClient(int index) {
		return clientList.get(index);
	}
	
}
