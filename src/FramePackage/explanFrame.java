package FramePackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

//���� ������
public class explanFrame extends JFrame {
	public explanFrame() {
		
		setSize(600,400);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 562, 343);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>"+"1. �÷��̾� ���� ���� ȭ�� �Ȱ��� ������ ���´�."+
				"<br/>"+ 
				"2. ���������� ���ʸ��� �ֻ��� �� ���� ������ ���ݸ�ŭ �̵��Ѵ�."+
				"<br/>"+
				"3. ������ ���� ���ö�� �ش� ���ø� �����Ѵ�." + 
				"<br/>" + 
				"4. �Ĺ������� ������ ���� ��Ģ�� ���� �ٸ� �÷��̾�� ȭ�� �޴´�." + 
				"<br/>" + 
				"5. �Ļ����� ���� ������ �÷��̾� 1���� �����");
		lblNewLabel.setBackground(new Color(135, 206, 250));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 538, 323);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("�ݱ�");
		btnNewButton.setBounds(411, 295, 139, 38);
		btnNewButton.addActionListener((e)->{setVisible(false);});
		panel.add(btnNewButton);
		
		setVisible(true);
	}
}
