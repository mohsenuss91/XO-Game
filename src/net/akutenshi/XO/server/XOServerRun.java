package net.akutenshi.XO.server;

import java.awt.EventQueue;

public class XOServerRun {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XOServerFrame frame = new XOServerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
