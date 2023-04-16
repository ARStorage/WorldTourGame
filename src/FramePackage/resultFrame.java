package FramePackage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.Font;

import ServerPackage.member;
import gamePackage.gameBoard;

//���� ��� ������
public class resultFrame extends JFrame{
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public resultFrame(member[] players) {
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("RESULT");
			
			for(int i = 0; i<players.length; i++)
			{
				int score = players[i].getscore()+(1000-200*players[i].getRank());
				players[i].setScore(score);
				System.out.println("���� :"+score);
				output.println("START");
				output.println(players[i].getId());
				output.println(score);
			}
			output.println("END");//����� ������ ������.
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSize(600,500);
		getContentPane().setLayout(null);
		
		JPanel Rankpanel = new JPanel();
		Rankpanel.setBackground(new Color(245, 245, 220));
		Rankpanel.setBounds(12, 102, 568, 267);
		getContentPane().add(Rankpanel);
		Rankpanel.setLayout(null);
		
		JLabel idLabel = new JLabel("���̵�");
		idLabel.setFont(new Font("����ü", Font.BOLD, 12));
		idLabel.setBackground(Color.WHITE);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(212, 10, 172, 15);
		Rankpanel.add(idLabel);
		
		JLabel scoreLabel = new JLabel("�� ����");
		scoreLabel.setFont(new Font("����ü", Font.BOLD, 12));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(396, 10, 160, 15);
		Rankpanel.add(scoreLabel);
		
		JLabel nameLabel = new JLabel("�̸�");
		nameLabel.setFont(new Font("����ü", Font.BOLD, 12));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(76, 10, 124, 15);
		Rankpanel.add(nameLabel);
		
		JLabel rankLabel = new JLabel("����");
		rankLabel.setFont(new Font("����ü", Font.BOLD, 12));
		rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rankLabel.setBounds(12, 10, 52, 15);
		Rankpanel.add(rankLabel);
		
		System.out.println("���� :"+players.length);
		JLabel[] rankLabels = new JLabel[players.length];
		for(int i =0; i<players.length; i++)
		{
			rankLabels[i] = new JLabel();
			rankLabels[i].setText(players[i].getRank()+"");
			rankLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			rankLabels[i].setBounds(12, 45+i*57, 52, 38);
			Rankpanel.add(rankLabels[i]);
			
		}
		JLabel[] nameLabels = new JLabel[players.length];
		for(int i = 0; i<players.length; i++)
		{
			nameLabels[i] = new JLabel();
			nameLabels[i].setText(players[i].getName());
			nameLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			nameLabels[i].setBounds(76, 45+i*57, 124, 38);
			Rankpanel.add(nameLabels[i]);
		}
		
		JLabel[] idLabels = new JLabel[players.length];
		for(int i = 0; i<players.length; i++)
		{
			idLabels[i] = new JLabel();
			idLabels[i].setText(players[i].getId());
			idLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			idLabels[i].setBounds(212, 45+i*57, 172, 38);
			Rankpanel.add(idLabels[i]);
		}
		
		JLabel[] scoreLabels = new JLabel[players.length];
		for(int i = 0; i<players.length; i++)
		{
			scoreLabels[i] = new JLabel();
			scoreLabels[i].setText(players[i].getscore()+"");
			scoreLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			scoreLabels[i].setBounds(396, 45+i*57, 160, 38);
			Rankpanel.add(scoreLabels[i]);
		}
		

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBounds(12, 10, 562, 82);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("���� ���");
		titleLabel.setFont(new Font("���� ��� Semilight", Font.BOLD, 30));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(12, 10, 538, 62);
		titlePanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBounds(12, 379, 562, 74);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton restart = new JButton("�ٽ� ����");
		restart.setBounds(12, 5, 208, 59);
		restart.setForeground(SystemColor.activeCaption);//�ٽý��� ��ư�� ������ �ٽ� �����Ѵ�.
		restart.addActionListener((e)->{
			System.out.println("�ٽý���");
			gameBoard g = new gameBoard(players,players.length,1);
			setVisible(false);});
		buttonPanel.add(restart);
		
		JButton exit = new JButton("���� ����");
		exit.setBounds(342, 5, 208, 59);
		exit.setForeground(SystemColor.activeCaption);
		exit.addActionListener((e)->{
			setVisible(false);
			System.exit(1);});
		buttonPanel.add(exit);
		
		setVisible(true);
		
	}
}
