package net.akutenshi.XO.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class XOChatServerThread extends Thread {
	private ServerSocket chatServer; //������-����� ����
	private boolean isLaunch; //������� �� ������ ����
	private XOServer parent; //������ �� ������ �������, ����������� ����� ��� ������� ����
	
	@Override
	public void run() {
		//���� ������ ������� ����
		XOChatClientProcessingThread aThread = null;
		while (isLaunch) {
			try {
				//���� ������ �������
				addToMainServerLog("Chat server waiting for client...\n");
				Socket aClientSocket = chatServer.accept();
				//��� �������� - ���� � �������
				addToMainServerLog("Find new client!\n");
				XOClientForm aClient = new XOClientForm(aClientSocket);
				//� � ������ �������
				parent.setNewClient(aClient);
				//������ ����� ����� ��� ������ � ��������
				aThread = new XOChatClientProcessingThread(aClient, this);
				aThread.start();
			} catch (IOException e) {
				addToMainServerLog("Chat server can't connecting with client!\n");
				e.printStackTrace();
			}
		}
	}

	public XOChatServerThread(ServerSocket inServer, XOServer inParent) {
		chatServer = inServer;
		parent = inParent;
		isLaunch = true;
	}
	
	public void	offChatServer() {
		isLaunch = false;
	}
	
	public void addToMainServerLog(String in) {
		parent.addToLog(in);
	}
	
	public XOClientForm findClient(String name) {
		int res = parent.findClientIndex(name);
		if (res != -1) {
			return parent.getClient(res);
		} else {
			return null;
		}
	}
	
}
