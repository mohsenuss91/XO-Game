package net.akutenshi.XO.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class XOChatClientProcessingThread extends Thread {
	private XOClientForm client;
	private XOChatServerThread parent;
	
	public XOChatClientProcessingThread(XOClientForm aClient, XOChatServerThread parent) {
		client = aClient;
		this.parent = parent;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		//потоки для общения с клиентом
		DataInputStream in = null;
		DataOutputStream out = null;
		try {
			in = new DataInputStream(client.getClientSocket().getInputStream());
			out = new DataOutputStream(client.getClientSocket().getOutputStream());
		} catch (IOException e) {
			parent.addToMainServerLog("Error: socket closed, can't open streams!\n");
			e.printStackTrace();
			return;
		}
		//при первом соединении он посылает свое имя
		parent.addToMainServerLog("Client name :");
		try {
			client.setName(in.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message;
		char c;
		int i;
		String destinationName;
		XOClientForm destination;
		while (client.getClientSocket().isConnected()) {
			destinationName = "";
			i = 0;
			c = ' ';
			try {
				message = in.readLine();	
				//определяем получателя, формуируем отправляемое сообщение	
				while (c != '$') {
					destinationName += message.charAt(i);
					i++;
				}
				message = message.substring(i + 1);
				//ищем адресата в списке
				if ((destination = parent.findClient(destinationName)) != null) {
					destination.getClientSocket().getOutputStream().write(message.getBytes());
					parent.addToMainServerLog("From" + client.getName() 
							+ " to "+ destinationName + " send: " + message + '\n');
				} else {
					out.writeUTF("Server: destination not found\n");
				}
			} catch (IOException e) {
				parent.addToMainServerLog("Can't read from " + client.getName() + '\n');
				e.printStackTrace();
				break;
			}
		}
		parent.addToMainServerLog(client.getName() + " is disconnect\n");
	}
	
	
}
