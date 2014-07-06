package net.akutenshi.XO.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
		//������ ��� ������� � ��������
		BufferedReader in = null;
		DataOutputStream out = null;
		try {
			in = new BufferedReader(new InputStreamReader(client.getClientSocket().getInputStream()));
			out = new DataOutputStream(client.getClientSocket().getOutputStream());
		} catch (IOException e) {
			parent.addToMainServerLog("Error: socket closed, can't open streams!\n");
			e.printStackTrace();
			return;
		}
		//��� ������ ���������� �� �������� ���� ���
		String tmp = "";
		parent.addToMainServerLog("Client name :");
		try {
			tmp = in.readLine();
			client.setName(tmp);
			parent.addToMainServerLog(client.getName() + '\n');
		} catch (IOException e) {
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
				if (message == null) {
					continue;
				}
				//���������� ����������, ���������� ������������ ���������	
				while (c != '$') {
					destinationName += message.charAt(i);
					i++;
				}
				message = message.substring(i + 1);
				//���� �������� � ������
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
