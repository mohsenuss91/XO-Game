package net.akutenshi.XO.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ProcessBuilder.Redirect;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class XOServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField portField;
	private JTextField hostIPField;
	private XOServer server = null;
	private JTextArea logArea;
	private Timer timer;

	/**
	 * Create the frame.
	 */
	public XOServerFrame() {
		//фрейм и контейнер объектов на ней. Без лейаутов
		setResizable(false);
		setTitle("XO Game Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//Поля для старта сервера
		//Порт
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 39, 46, 14);
		contentPane.add(lblPort);
		
		portField = new JTextField();
		portField.setText("4444");
		portField.setBounds(66, 36, 35, 20);
		contentPane.add(portField);
		portField.setColumns(10);
		
		//Хост-IP
		JLabel lblHostIp = new JLabel("host IP: ");
		lblHostIp.setBounds(10, 14, 46, 14);
		contentPane.add(lblHostIp);
		
		hostIPField = new JTextField();
		hostIPField.setEnabled(false);
		hostIPField.setBounds(66, 11, 197, 20);
		contentPane.add(hostIPField);
		hostIPField.setColumns(10);
		try {
			hostIPField.setText(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Текстовые поля
		//Лог сервера
		JLabel lblLog = new JLabel("Log:");
		lblLog.setBounds(10, 78, 46, 14);
		contentPane.add(lblLog);	
		
		logArea = new JTextArea();
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		logArea.setBounds(10, 103, 253, 449);
		contentPane.add(logArea);
		
		//Лист игроков на сервере
		JLabel lblPlayerList = new JLabel("Player list:");
		lblPlayerList.setBounds(273, 78, 92, 14);
		contentPane.add(lblPlayerList);
		
		JTextArea playerListArea = new JTextArea();
		playerListArea.setBounds(273, 103, 246, 449);
		contentPane.add(playerListArea);
		
		//Кнопки
		//Старт сервера
		JButton startButton = new JButton("Start server");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server = new XOServer(Integer.parseInt(portField.getText()));
				server.LaunchChatServer();
				refreshLog();
			}
		});
		startButton.setBounds(273, 10, 246, 23);
		contentPane.add(startButton);
		
		//Остановка сервера
		JButton stopButton = new JButton("Stop server");
		stopButton.setBounds(273, 44, 246, 23);
		contentPane.add(stopButton);
		
		timer = new Timer(500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				refreshLog();
				timer.restart();
			}
		});
		timer.start();
	}
	
	private void refreshLog() {
		if (server != null) {
			logArea.append(server.sendLog());
		}
	}

	
}
