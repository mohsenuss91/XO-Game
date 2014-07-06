package net.akutenshi.XO.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XOClientMainFrame extends JFrame {

	private JPanel contentPane = null;
	private XOClientActionPanel actionPanel = null;
	private JTextField portNumField;
	private JTextField hostNameField;
	private JTextField textField_2;
	private JTextField txtNoname;
	private XOClientSocket clientSocket;

	/**
	 * Create the frame.
	 */
	public XOClientMainFrame() {
		//Создание фрейма и контейнера объектов, куда будем добавлять элементы
		setResizable(false);
		setTitle("XO Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//Панель для игры
		actionPanel = new XOClientActionPanel();
		actionPanel.setBounds(10, 11, 550, 550);
		contentPane.add(actionPanel);
		
		//Поле имени игрока
		JLabel lblPlayerName = new JLabel("Player name:");
		lblPlayerName.setBounds(570, 14, 68, 14);
		contentPane.add(lblPlayerName);
		
		txtNoname = new JTextField();
		txtNoname.setText("Noname");
		txtNoname.setBounds(648, 11, 136, 20);
		contentPane.add(txtNoname);
		txtNoname.setColumns(10);
		
		//Номер порта
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(570, 89, 33, 14);
		contentPane.add(lblPort);
		portNumField = new JTextField();
		portNumField.setText("4444");
		portNumField.setBounds(613, 86, 164, 20);
		contentPane.add(portNumField);
		portNumField.setColumns(10);
		//Имя хоста
		JLabel lblHost = new JLabel("Host:");
		lblHost.setBounds(570, 58, 33, 14);
		contentPane.add(lblHost);	
		hostNameField = new JTextField();
		hostNameField.setText("172.18.30.28");
		hostNameField.setBounds(613, 55, 164, 20);
		contentPane.add(hostNameField);
		hostNameField.setColumns(10);
		
		//Окно чата
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(570, 356, 214, 171);
		
		// TODO: Проверить скролл!
		JScrollPane scroll = new JScrollPane (textArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				   JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scroll);
		//contentPane.add(textArea);
		
		//Строка ввода сообщения
		textField_2 = new JTextField();
		textField_2.setBounds(570, 541, 163, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		//Кнопки
		//Кнопка соединения с сервером
		JButton connectButton = new JButton("Connect...");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientSocket = new XOClientSocket(hostNameField.getText(),
						Integer.parseInt(portNumField.getText()), 
						txtNoname.getText());
			}
		});
		connectButton.setBounds(570, 117, 214, 23);
		contentPane.add(connectButton);
		//Отправка сообщения в чат 
		JButton sendMessageButton = new JButton("\u003E");
		sendMessageButton.setBounds(743, 538, 41, 23);
		contentPane.add(sendMessageButton);
		
		//разделительная черта
		JSeparator separator = new JSeparator();
		separator.setBounds(570, 42, 214, 2);
		contentPane.add(separator);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientSocket.disconnect();
			}
		});
		btnDisconnect.setBounds(570, 151, 214, 23);
		contentPane.add(btnDisconnect);
	}
}
