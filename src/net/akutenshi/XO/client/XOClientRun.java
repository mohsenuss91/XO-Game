package net.akutenshi.XO.client;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class XOClientRun {

	public static void main(String[] args) throws UnknownHostException, IOException {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XOClientMainFrame frame = new XOClientMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		/*if (args.length > 0)
		{
			String host = args[0];
			InetAddress[] addresses = InetAddress.getAllByName(host);
			for (InetAddress a : addresses)
				System.out.println(a);
		}
		else {
			InetAddress localHostAddress = InetAddress.getLocalHost();
			System.out.println(localHostAddress);
		}*/

	}

}
