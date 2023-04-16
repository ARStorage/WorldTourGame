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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ServerPackage.member;

//탈퇴 프레임
public class removeFrame extends JFrame implements ActionListener{
	private JTextField idText;
	private JPasswordField pwText;
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	private JLabel infoLabel;
	
	
	public removeFrame() {
		
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("REMOVE");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 565, 291);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		idText = new JTextField();
		idText.setBounds(250, 108, 208, 21);
		panel.add(idText);
		idText.setColumns(10);
		
		pwText = new JPasswordField();
		pwText.setBounds(250, 150, 208, 21);
		panel.add(pwText);
		pwText.setColumns(10);
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(144, 111, 52, 15);
		panel.add(idLabel);
		
		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(144, 153, 52, 15);
		panel.add(pwLabel);
		
		JButton okButton = new JButton("탈퇴");
		okButton.setBackground(new Color(135, 206, 250));
		okButton.setBounds(458, 241, 95, 23);
		okButton.addActionListener(this);
		panel.add(okButton);
		
		infoLabel = new JLabel("아이디와 비밀번호를 입력하세요.");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(12, 45, 541, 15);
		panel.add(infoLabel);
		
		setSize(600,348);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = new String(idText.getText());
		String password = new String(pwText.getPassword());
		
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
						String flag = input.readLine();
						if(flag.equals("OK"))
							infoLabel.setText("계정이 삭제되었습니다.");
						
						else {
							infoLabel.setText("정보가 틀립니다.");
						}
						
					}
					
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
			
			else {infoLabel.setText("비밀번호를 입력하세요.");}
		}
	}
}
