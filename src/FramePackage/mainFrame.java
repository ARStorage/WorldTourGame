package FramePackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mainFrame extends JFrame{
	JButton login, join, remove,rank;
	JPanel buttonPanel, logoPanel;
	JLabel logoLabel;
	int flag = -1;
	
	public mainFrame(){
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		//로고 패널
		logoLabel = new JLabel("월드 투어 보드 게임");
		logoLabel.setForeground(SystemColor.activeCaption);
		logoLabel.setFont(new Font("타이포_쌍문동 B", Font.PLAIN, 39));
		logoLabel.setBounds(128, 10, 366, 111);
		logoPanel = new JPanel();
		logoPanel.setBackground(Color.WHITE);
		logoPanel.setForeground(Color.WHITE);
		logoPanel.setBounds(12,10,642,126);
		logoPanel.setLayout(null);
		logoPanel.add(logoLabel);
		
		//버튼
		join = new JButton("회원가입");
		join.setBounds(255, 28, 114, 40);
		join.setBackground(new Color(176, 224, 230));
		join.addActionListener((e)->{new joinFrame();setVisible(false);});
		
		remove = new JButton("회원 탈퇴");
		remove.setBackground(new Color(176, 224, 230));
		remove.setBounds(255, 122, 114, 40);
		remove.addActionListener((e)->{new removeFrame();setVisible(false);});
		
		rank = new JButton("랭킹");
		rank.setBackground(new Color(176, 224, 230));
		rank.setBounds(573, 178, 69, 30);
		rank.addActionListener((e)->{rankFrame r = new rankFrame();});
		
		login = new JButton("로그인");
		login.setBackground(new Color(176, 224, 230));
		login.setBounds(255, 75, 114, 40);
		login.addActionListener((e)->{new loginFrame();setVisible(false);});
		
		//버튼 패널
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setForeground(Color.WHITE);
		buttonPanel.setLayout(null);
		buttonPanel.add(join);
		buttonPanel.add(login);
		buttonPanel.add(remove);
		buttonPanel.add(rank);
		buttonPanel.setBounds(12,158,654,225);
		
		//프레임
		getContentPane().setLayout(null);
		getContentPane().add(logoPanel);
		getContentPane().add(buttonPanel);
		
		JButton explanButton = new JButton("게임 설명");
		explanButton.setBackground(new Color(176, 224, 230));
		explanButton.setBounds(255, 172, 114, 40);
		explanButton.addActionListener((e)->{explanFrame ef = new explanFrame ();});
		buttonPanel.add(explanButton);
		setSize(680,430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}