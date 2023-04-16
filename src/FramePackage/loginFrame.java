package FramePackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import ServerPackage.member;
import ServerPackage.memberSystem;

//�α��� ������
public class loginFrame extends JFrame implements ActionListener{
	JButton okButton, exitButton;
	JTextField idText;
	JPasswordField passwordText;
	JLabel idLabel, passwordLabel, infoLabel;
	JPanel loginPanel;
	member user=null;
	int flag;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public loginFrame() {
		
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("LOGIN");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ư
		getContentPane().setLayout(null);
		okButton = new JButton("Ȯ��");
		okButton.setBackground(new Color(221, 160, 221));
		okButton.setBounds(320, 208, 88, 23);
		okButton.addActionListener(this);
		
		exitButton = new JButton("����");
		exitButton.setBackground(new Color(221, 160, 221));
		exitButton.setBounds(448, 301, 65, 30);
		exitButton.addActionListener(this);
		
		//�ؽ�Ʈ�ʵ�
		idText = new JTextField();
		idText.setBounds(216, 123, 192, 21);
		passwordText = new JPasswordField();
		passwordText.setBounds(216, 165, 192, 21);
		
		//��
		idLabel = new JLabel("���̵�");
		idLabel.setBounds(139, 126, 37, 15);
		passwordLabel = new JLabel("��й�ȣ");
		passwordLabel.setBounds(139, 168, 65, 18);
		infoLabel = new JLabel("���̵�� ��й�ȣ�� �Է��ϼ���.");
		infoLabel.setBounds(163, 46, 184, 15);
		
		//�г�
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 255, 255));
		loginPanel.setBounds(0, 0, 525, 358);
		loginPanel.setLayout(null);
		
		loginPanel.add(okButton);
		loginPanel.add(exitButton);
		loginPanel.add(idText);
		loginPanel.add(passwordText);
		loginPanel.add(idLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(infoLabel);
		
		getContentPane().add(loginPanel);
		setSize(535,389);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("�α���");
	}
	

	public loginFrame(member[] users,int index) {
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("LOGIN");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ư
		getContentPane().setLayout(null);
		memberSystem m = new memberSystem();
		okButton = new JButton("Ȯ��");
		okButton.setBackground(new Color(221, 160, 221));
		okButton.setBounds(320, 208, 88, 23);
		okButton.addActionListener((e)->{
			String id = new String(idText.getText());
			String password = new String(passwordText.getPassword());
			
			if(!id.equals(""))//�� �ؽ�Ʈ�ʵ尡 ������
			{
				if(!password.equals(""))
				{
					try {
						output.println("START");
						output.println(id);//������ ������
						output.println(password);
						output.println("END");
						
						if(input.readLine().equals("START"))//������ ����� ������
						{
							String name = input.readLine();
							int score = Integer.parseInt(input.readLine());
							users[index] = new member(name,id,password,score);//��� ������ �����Ѵ�.
							setVisible(false);
							
						}
						
						else {//�ƴϸ� �α��� ����
							infoLabel.setText("�α��� ������ Ʋ���ϴ�.");
						}
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				
				else {infoLabel.setText("��й�ȣ�� �Է��ϼ���.");}
			}
			
			else
			{infoLabel.setText("���̵� �Է��ϼ���.");}
		});
		
		exitButton = new JButton("����");
		exitButton.setBackground(new Color(221, 160, 221));
		exitButton.setBounds(448, 301, 65, 30);
		exitButton.addActionListener(this);
		
		//�ؽ�Ʈ�ʵ�
		idText = new JTextField();
		idText.setBounds(216, 123, 192, 21);
		passwordText = new JPasswordField();
		passwordText.setBounds(216, 165, 192, 21);
		
		//��
		idLabel = new JLabel("���̵�");
		idLabel.setBounds(139, 126, 37, 15);
		passwordLabel = new JLabel("��й�ȣ");
		passwordLabel.setBounds(139, 168, 65, 18);
		infoLabel = new JLabel("���̵�� ��й�ȣ�� �Է��ϼ���.");
		infoLabel.setBounds(163, 46, 184, 15);
		
		//�г�
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 255, 255));
		loginPanel.setBounds(0, 0, 525, 358);
		loginPanel.setLayout(null);
		
		loginPanel.add(okButton);
		loginPanel.add(exitButton);
		loginPanel.add(idText);
		loginPanel.add(passwordText);
		loginPanel.add(idLabel);
		loginPanel.add(passwordLabel);
		loginPanel.add(infoLabel);
		
		getContentPane().add(loginPanel);
		setSize(535,389);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("�α���");
	}
	
	public void setUser(member user)
	{
		this.user = user;
	}
	
	public member getUser()
	{
		return this.user;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==okButton)
		{
			String id = new String(idText.getText());
			String password = new String(passwordText.getPassword());
			
			if(!id.equals(""))
			{
				if(!password.equals(""))
				{
					try {
					
					output.println("START");
					output.println(id);
					output.println(password);
					output.println("END");
					
					if(input.readLine().equals("START"))
					{
						String name = input.readLine();
						int score = Integer.parseInt(input.readLine());
						user = new member(name,id,password,score);
						setVisible(false);
						waitFrame w = new waitFrame(user);
						
					}
					
					else {
						infoLabel.setText("�α��� ������ Ʋ���ϴ�.");
					}}catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				else {infoLabel.setText("��й�ȣ�� �Է��ϼ���.");}
			}
			
			else
			{infoLabel.setText("���̵� �Է��ϼ���.");}
		}
		
		else
		{
			setVisible(false);
			System.exit(1);
		}
		
	}
}