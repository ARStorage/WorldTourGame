package FramePackage;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import java.awt.Font;
import javax.swing.JTable;

//랭킹 프레임
class myTableModel extends AbstractTableModel{

	private String[] columnNames = {"아이디","점수"};
	private int row = 30, col = 3;
	Object[][] data = new String[row][col];
	
	private Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int col) {
		return columnNames[col].toString();
	}
	
	public void fillTable() {
		try {
			try {
				socket = new Socket("localhost",9999);
				out = new PrintWriter(socket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.println("RANK");
			if(in.readLine().equals("START"))
			for(int i = 0; i<30; i++)
			{
				String tmp = in.readLine();
				if(tmp.equals("END"))
					break;
				data[i][0] = tmp;
				data[i][1] = in.readLine();//서버에서 결과를 받아 테이블에 출력한다.
				
			}
			//this.data = m.getTable();
			System.out.println(data[0][0]);
		}catch(Exception e) {e.printStackTrace();}
		fireTableDataChanged();
	}
	
	
	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableCellUpdated(row,col);
	}
	
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}
}
public class rankFrame extends JFrame {
	private JTable table;

	public rankFrame() {
		setSize(450,600);
		getContentPane().setLayout(null);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(12, 10, 412, 91);
		titlePanel.setBackground(Color.WHITE);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("랭킹");
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 388, 71);
		titlePanel.add(lblNewLabel);
		
		JPanel rankpanel = new JPanel();
		rankpanel.setBounds(12, 111, 412, 407);
		rankpanel.setBackground(Color.WHITE);
		getContentPane().add(rankpanel);
		rankpanel.setLayout(null);
		
		myTableModel model = new myTableModel();
		model.fillTable();
	
		
		table = new JTable(model);
		table.setBounds(12, 10, 388, 387);
		rankpanel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 10, 388, 387);
		rankpanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 528, 412, 25);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton closeButton = new JButton("닫기");
		closeButton.setBackground(SystemColor.activeCaption);
		closeButton.setBounds(317, 0, 95, 23);
		closeButton.addActionListener((e)->{setVisible(false);});
		panel.add(closeButton);
		
		setVisible(true);
	}
}
