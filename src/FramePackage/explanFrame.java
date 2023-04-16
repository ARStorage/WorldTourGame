package FramePackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

//설명 프레임
public class explanFrame extends JFrame {
	public explanFrame() {
		
		setSize(600,400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 562, 343);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>"+"1. 플레이어 각자 게임 화폐를 똑같이 나누어 갖는다."+
				"<br/>"+ 
				"2. 전반전에는 차례마다 주사위 두 개를 굴려서 눈금만큼 이동한다."+
				"<br/>"+
				"3. 도착한 곳이 도시라면 해당 도시를 구입한다." + 
				"<br/>" + 
				"4. 후반전에는 게임의 여러 규칙에 따라 다른 플레이어에게 화폐를 받는다." + 
				"<br/>" + 
				"5. 파산하지 않은 마지막 플레이어 1명이 우승자");
		lblNewLabel.setBackground(new Color(135, 206, 250));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 538, 323);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("닫기");
		btnNewButton.setBounds(411, 295, 139, 38);
		btnNewButton.addActionListener((e)->{setVisible(false);});
		panel.add(btnNewButton);
		
		setVisible(true);
	}
}
