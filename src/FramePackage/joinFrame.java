package FramePackage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

//회원가입 프레임
public class joinFrame extends JFrame implements ActionListener{
	private JButton okButton, exitButton;
	private JTextField idText,nameText;
	private JPasswordField passwordText,passwordCheck;
	private JLabel idLabel, passwordLabel,pCheckLabel,nameLabel,infoLabel;
	private JPanel joinPanel;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public joinFrame() {
		try {//소켓 연결
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("JOIN");
		} catch (IOException e) {
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		//버튼
		okButton = new JButton("확인");
		okButton.setBackground(new Color(221, 160, 221));
		okButton.setBounds(379, 308, 74, 40);
		okButton.addActionListener(this);
		
		exitButton = new JButton("종료");
		exitButton.setBackground(new Color(221, 160, 221));
		exitButton.setBounds(465, 411, 65, 30);
		exitButton.addActionListener(this);
		
		//텍스트
		idText = new JTextField();
		idText.setBounds(226, 171, 192, 21);
		passwordText = new JPasswordField();
		passwordText.setBounds(226, 211, 192, 21);
		
		//라벨
		idLabel = new JLabel("아이디");
		idLabel.setBounds(131, 174, 37, 15);
		passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(131, 212, 65, 18);
		infoLabel = new JLabel("회원가입 창입니다.");
		infoLabel.setBounds(216, 71, 184, 15);
		
		nameText = new JTextField();
		nameText.setBounds(226, 130, 192, 21);
		passwordCheck = new JPasswordField();
		passwordCheck.setBounds(226, 257, 192, 21);
		
		pCheckLabel = new JLabel("비밀번호 확인");
		pCheckLabel.setBounds(131, 257, 192, 21);
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(131, 130, 192, 21);
		
		joinPanel = new JPanel();
		joinPanel.setBackground(new Color(255, 255, 255));
		joinPanel.setBounds(0, 0, 554, 451);
		joinPanel.setLayout(null);
		
		joinPanel.add(okButton);
		joinPanel.add(exitButton);
		joinPanel.add(idText);
		joinPanel.add(passwordText);
		joinPanel.add(idLabel);
		joinPanel.add(passwordLabel);
		joinPanel.add(infoLabel);
		
		joinPanel.add(nameLabel);
		joinPanel.add(nameText);
		joinPanel.add(passwordCheck);
		joinPanel.add(pCheckLabel);
		
		getContentPane().add(joinPanel);
		setSize(568,484);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("회원가입");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==okButton)//확인 버튼을 누르면
		{
			String id = new String(idText.getText());
			String password = new String(passwordText.getPassword());
			String checkPassword = new String(passwordCheck.getPassword());
			String name = new String(nameText.getText());
			
			if(!name.equals(""))
			{
				if(!id.equals(""))//빈 곳이 없고
				{
					if(!password.equals("")&&password.equals(checkPassword))//확인 pw와 pw가 동일하면
					{
						
						output.println("START");
						output.println(id);//서버에 보내서 저장한다.
						output.println(password);
						output.println(name);
						output.println("END");
						new mainFrame();
						setVisible(false);
					}

					else {infoLabel.setText("비밀번호를 제대로 입력하세요.");}
				}
				
				else
				{infoLabel.setText("아이디를 입력하세요.");}
			}
			
			else
			{infoLabel.setText("이름을 입력하세요.");}
		}
		
		else
		{
			setVisible(false);
			System.exit(1);
		}
	
		
	}
}