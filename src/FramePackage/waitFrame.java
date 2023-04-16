package FramePackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import ServerPackage.member;
import gamePackage.gameBoard;

//���ȭ�� ������
public class waitFrame extends JFrame{
	private member[] players;
	private loginFrame l;
	private JButton player4;
	private JComponent player3;
	private JButton player2;
	private JButton startButton;
	private int count = 0;
	private JRadioButton bankRadio;
	private JRadioButton itemRadio;
	private JRadioButton wellfareRadio;
	private int option = 0;
	private ButtonGroup group;
	
	public waitFrame(member me) {
		players = new member[4];//�÷��̾�� ����Ʈ
		players[0] = me;//�� �ڽ�
		count++;//�α����� �÷��̾� ��
		setSize(700,400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 662, 343);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton player1 = new JButton(me.getName());
		player1.setBackground(SystemColor.activeCaption);
		player1.setBounds(12, 50, 150, 283);
		panel.add(player1);
		
		player4 = new JButton("+");
		player4.setBackground(SystemColor.activeCaption);
		player4.setBounds(500, 50, 150, 283);
		panel.add(player4);
		
		player3 = new JButton("+");
		player3.setBackground(SystemColor.activeCaption);
		player3.setBounds(338, 50, 150, 283);
		panel.add(player3);
		
		player2 = new JButton("+");
		player2.setBackground(SystemColor.activeCaption);
		player2.setBounds(176, 50, 150, 283);
		player2.addActionListener((e)->{
			l = new loginFrame(players,1);
		});
		panel.add(player2);
		
		JLabel infoLabel = new JLabel("�ٸ� �÷��̾ ��ٸ��ϴ�.");
		infoLabel.setFont(new Font("���� ��� Semilight", Font.BOLD, 16));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(12, 10, 216, 30);
		panel.add(infoLabel);
		
		startButton = new JButton("���� ����");
		startButton.setBackground(new Color(64, 224, 208));
		startButton.setBounds(500, 17, 150, 23);
		startButton.setEnabled(false);
		startButton.addActionListener((e)->{
			gameBoard g = new gameBoard(players,count,option);//���� ���� ��ư�� ������, �÷��̾� ��, �ɼ�, �÷��̾� ����Ʈ�� �ѱ��.
			setVisible(false);
		});
		panel.add(startButton);
		
		itemRadio = new JRadioButton("������");
		itemRadio.setBackground(Color.WHITE);
		itemRadio.setBounds(228, 17, 67, 23);
		itemRadio.addActionListener((e)->{this.option = 1;});
		panel.add(itemRadio);
		
		bankRadio = new JRadioButton("�����");
		bankRadio.setBackground(Color.WHITE);
		bankRadio.setBounds(299, 17, 67, 23);
		panel.add(bankRadio);
		
		wellfareRadio = new JRadioButton("��ȸ����");
		wellfareRadio.setBackground(Color.WHITE);
		wellfareRadio.setBounds(369, 17, 119, 23);
		panel.add(wellfareRadio);
		
		group = new ButtonGroup();
		group.add(itemRadio);
		group.add(bankRadio);
		group.add(wellfareRadio);
		
		setVisible(true);
		
		Thread t = new waitingThread();
		t.start();
	}
	
	class waitingThread extends Thread{
		public void run() {
			while(true)
			{
				if(players[1]!=null)
				{
					player2.setText(players[1].getName());//�÷��̾ ������
					startButton.setEnabled(true);//���۹�ư Ȱ��ȭ�ϰ�
					count = 2;//ī��Ʈ �߰�
				}

				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
				}
			}
		}
	}
}
