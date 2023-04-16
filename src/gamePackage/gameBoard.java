package gamePackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import FramePackage.resultFrame;
import ServerPackage.member;

//이미지 객체
class GraphicObject{
	BufferedImage img = null;
	int x = 0, y= 0;
	Graphics g;
	member owner;
	
	public GraphicObject(String name) {
		try {img = ImageIO.read(new File(name));}
		catch (IOException e) {
			System.out.println(name);
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {g.drawImage(img,x,y,null);}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
}

//플레이어 말
class playersObject{//수정

	private GraphicObject[] playerObject;
	
	public playersObject() {
		playerObject = new GraphicObject[4];
		
		playerObject[0] = new GraphicObject("player1.png");
		playerObject[1] = new GraphicObject("player2.png");
		playerObject[2] = new GraphicObject("player3.png");
		playerObject[3] = new GraphicObject("player4.png");
		
		playerObject[0].update(900, 675);
		playerObject[1].update(950, 675);
		playerObject[2].update(920, 730);
		playerObject[3].update(950, 730);
		
	}
	
	public GraphicObject getPlayerOB(int index)
	{return this.playerObject[index];}
	
}

//플레이어가 지은 건물들
class buildObject{
	private ArrayList<GraphicObject> villaOB = new ArrayList<GraphicObject>();
	private ArrayList<GraphicObject> hotelOB = new ArrayList<GraphicObject>();
	private ArrayList<GraphicObject> buildingOB = new ArrayList<GraphicObject>();
	
	public void addVillaOB(int x, int y,member player) {
		GraphicObject tmp = new GraphicObject("villa.png");
		tmp.update(x,y);
		tmp.owner=player;
		villaOB.add(tmp);
	}
	
	public void addhotelOB(int x, int y,member player) {
		GraphicObject tmp = new GraphicObject("hotel.png");
		tmp.update(x,y);
		tmp.owner=player;
		hotelOB.add(tmp);
	}
	
	public void addbuildingOB(int x, int y,member player) {
		GraphicObject tmp = new GraphicObject("building.png");
		tmp.update(x,y);
		tmp.owner=player;
		buildingOB.add(tmp);
	}
	
	public int villaOBIndex() {return villaOB.size();}
	public int hotelOBIndex() {return hotelOB.size();}
	public int buildingOBIndex() {return buildingOB.size();}
	
	public GraphicObject getvillaOB(int i) {return villaOB.get(i);}
	public GraphicObject gethotelOB(int i) {return hotelOB.get(i);}
	public GraphicObject getbuildingOB(int i) {return buildingOB.get(i);}
	
	public member getVillaOwner(int i) {return villaOB.get(i).owner;}
	public member getHotelOwner(int i) {return hotelOB.get(i).owner;}
	public member getBuildingOwner(int i) {return buildingOB.get(i).owner;}
	
	public void removeVilla(int i) {villaOB.remove(i);}
	public void removeHotel(int i) {hotelOB.remove(i);}
	public void removeBuilding(int i) {buildingOB.remove(i);}
	
	public int villaGetX(int i) {return villaOB.get(i).x;}
	public int hotelGetX(int i) {return hotelOB.get(i).x;}
	public int buildingGetX(int i) {return buildingOB.get(i).x;}
	
	public int villaGetY(int i) {return villaOB.get(i).y;}
	public int hotelGetY(int i) {return hotelOB.get(i).y;}
	public int buildingGetY(int i) {return buildingOB.get(i).y;}
	
	
}

//보드 정보
class destination{
	private int id;
	private int ordernum;
	public int getOrdernum() {return ordernum;}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public int getTollPrice() {
		return tollPrice;
	}

	public void setTollPrice(int tollPrice) {
		this.tollPrice = tollPrice;
	}

	public int getBuildingPrice() {
		return buildingPrice;
	}

	public void setBuildingPrice(int buildingPrice) {
		this.buildingPrice = buildingPrice;
	}

	public int getVillaPrice() {
		return villaPrice;
	}

	public void setVillaPrice(int villaPrice) {
		this.villaPrice = villaPrice;
	}

	public int getHotelPrice() {
		return hotelPrice;
	}

	public void setHotelPrice(int hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getTollFee() {
		return tollFee;
	}

	public void setTollFee(int tollFee) {
		this.tollFee = tollFee;
	}

	public int getBuildingFee() {
		return buildingFee;
	}

	public void setBuildingFee(int buildingFee) {
		this.buildingFee = buildingFee;
	}

	public int getVillaFee() {
		return villaFee;
	}

	public void setVillaFee(int villaFee) {
		this.villaFee = villaFee;
	}

	public int getHotelFee() {
		return hotelFee;
	}

	public void setHotelFee(int hotelFee) {
		this.hotelFee = hotelFee;
	}
	
	public int getID() {return this.id;}

	private String name;
	private member ownerPlayer = null;//플레이어 id, null이면 주인이 없음
	private int building =-1;//-1:빌딩이 없음, 1. 별장, 2. 빌딩, 3. 호텔  4.대지
	private String nation;
	private int tollPrice, buildingPrice, villaPrice,hotelPrice;
	private String color;//아시아, 아프리카 노란색
	private int tollFee;//대지료
	private int buildingFee;//빌딩료
	private int villaFee;//별장료
	private int hotelFee;//호텔료
	private Graphics g; 
	
	private int x,y;
	
	public destination(int id, int ordernum,String nation,
			String name, int tollPrice, int buildingPrice,
			int villaPrice, int hotelPrice, String color,
			int tollFee,int buildingFee, int villaFee, int hotelFee) {
		this.id = id;
		this.ordernum = ordernum;
		this.nation = nation;
		this.name = name;
		this.tollPrice = tollPrice;
		this.buildingPrice = buildingPrice;
		this.villaPrice = villaPrice;
		this.hotelPrice = hotelPrice;
		this.color = color;
		this.tollFee = tollFee;
		this.buildingFee = buildingFee;
		this.villaFee = villaFee;
		this.hotelFee = hotelFee;
	}
	
	public void setOwnerPlayer(member player) {
		this.ownerPlayer=player;
	}
	
	public member getOwnerPlayer() {
		return this.ownerPlayer;
	}
	
	public void setBuilding(int building) {
		this.building = building;
	}
	
	public int getBuilding() {
		return this.building;
	}
	
	public void setGraphisc(Graphics g) {
		this.g = g;
	}
	
	public String toString() {
		return id+"  "+ordernum+"  "+nation+"  "+name
				+"  "+tollPrice+"  "+buildingPrice+"  "+villaPrice
				+"  "+hotelPrice+"  "+color+"  "+tollFee
				+"  "+buildingFee+"  "+villaFee+"  "+hotelFee;
	}
	
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {return this.x;}
	public int getY() {return this.y;}
}
//보드 정보 셋팅

class settingNation{
	private destination[] d = new destination[40];
	public settingNation(){
		boardDB bd = new boardDB();
		
		try {
			d = bd.setBoard();
			
			/*for(destination e:d)
			{
				System.out.println(e);
			}*/
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public destination[]  getDestinadion() {
		return d;
	}
}
//게임 보드 초기화
public class gameBoard extends JFrame implements ActionListener{
	private JPanel boardPanel, myPanel, buttonPanel,memberPanel,itempanel;
	private member me;
	private destination[] d = new destination[40];
	private JLabel boardLabel[];
	private JButton bombButton, internetButton, RollTheDice;
	private JButton[] playersButtons;
	private JLabel info;
	private member[] players;
	private playersObject playersMark;
	private gameRule rule;
	private JButton tollButton;
	private JButton pass;
	private JLabel info2;
	private JComponent panel;
	private JButton okButton;
	private int turn;
	private int playerPosition;
	private JButton buildVilla;
	private JButton buildhotel;
	private JButton buildBuilding;
	private buildObject OB;
	private int membercount;
	private boolean clicked = true;
	private JComboBox comboBox;
	private JButton internetOK;
	private JComboBox comboBox2;
	private JButton bombOK;
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public gameBoard(member[] users,int count,int option)
	{
		try {
			socket = new Socket("localhost",9999);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			output.println("GAME");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		OB  = new buildObject();
		settingNation sn = new settingNation();
		d = sn.getDestinadion();
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
			
		boardPanel = new JPanel();
		boardPanel.setForeground(new Color(248, 248, 255));
		boardPanel.setBackground(new Color(176, 224, 230));
		boardPanel.setBounds(12, 10,1040,740);
		getContentPane().add(boardPanel);
		boardPanel.setLayout(null);
		
		//버튼
		int i = 0;
		boardLabel = new JLabel[40];
		boardLabel[i] = new JLabel(d[i].getName(),JLabel.CENTER);//리턴값 연결하기
		boardLabel[i].setOpaque(true);
		boardLabel[i].setBackground(new Color(119, 136, 153));
		boardLabel[i].setBounds(880, 640, 160, 100);
		d[i].setXY(880, 640);
		boardPanel.add(boardLabel[i++]);
		EtchedBorder eborder=new EtchedBorder(EtchedBorder.RAISED);
		for(int j = 0; j<9;j++)
		{
			if(d[i].getID()<200) {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>"+d[i].getTollPrice()+"</html>",JLabel.CENTER);}
			else {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>",JLabel.CENTER);}
			boardLabel[i].setOpaque(true);
			boardLabel[i].setBackground(new Color(154, 205, 50));
			boardLabel[i].setBounds(800-80*j, 640, 80, 100);
			//평면에 끌로 판듯이 외곽선 효과를 내는 것이고 양각의 효과를 준다.
			boardLabel[i].setBorder(eborder);
			d[i].setXY(800-80*j, 640);
			boardPanel.add(boardLabel[i++]);
		}
		
		boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>",JLabel.CENTER);
		boardLabel[i].setOpaque(true);
		boardLabel[i].setBackground(new Color(119, 136, 153));
		boardLabel[i].setBounds(0, 639, 160, 100);
		d[i].setXY(0, 639);
		boardPanel.add(boardLabel[i++]);
		
		for(int j = 0; j<9;j++)
		{
			if(d[i].getID()<200) {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>"+d[i].getTollPrice()+"</html>",JLabel.CENTER);}
			else {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>",JLabel.CENTER);}
			boardLabel[i].setOpaque(true);
			boardLabel[i].setBackground(new Color(154, 205, 50));
			boardLabel[i].setBounds(0, 580-j*60,160, 60);
			boardLabel[i].setBorder(eborder);
			d[i].setXY(0, 580-j*60);
			boardPanel.add(boardLabel[i++]);
		}
		
		
		boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>",JLabel.CENTER);
		boardLabel[i].setOpaque(true);
		boardLabel[i].setBackground(new Color(119, 136, 153));
		boardLabel[i].setBounds(0, 0, 160, 100);
		d[i].setXY(0,0);
		boardPanel.add(boardLabel[i++]);
		
		for(int j = 0; j<9;j++)
		{
			if(d[i].getID()<200) {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>"+d[i].getTollPrice()+"</html>",JLabel.CENTER);}
			else {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>");}
			boardLabel[i].setOpaque(true);
			boardLabel[i].setBackground(new Color(154, 205, 50));
			boardLabel[i].setBounds(160+80*j, 0, 80, 100);
			boardLabel[i].setBorder(eborder);
			d[i].setXY(160+80*j, 0);
			boardPanel.add(boardLabel[i++]);
		}
	
		boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>",JLabel.CENTER);
		boardLabel[i].setOpaque(true);
		boardLabel[i].setBackground(new Color(119, 136, 153));
		boardLabel[i].setBounds(880, 0, 160, 100);
		d[i].setXY(880, 0);
		boardPanel.add(boardLabel[i++]);
		
		for(int j = 0; j<9;j++)
		{
			if(d[i].getID()<200) {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>"+d[i].getTollPrice()+"</html>",JLabel.CENTER);}
			else {boardLabel[i] = new JLabel("<html>"+d[i].getName()+"<br/>",JLabel.CENTER);}
			boardLabel[i].setOpaque(true);
			boardLabel[i].setBackground(new Color(154, 205, 50));
			boardLabel[i].setBounds(880, 100+60*j,160,60);
			boardLabel[i].setBorder(eborder);
			d[i].setXY(880, 100+60*j);
			boardPanel.add(boardLabel[i++]);
		}

		
		RollTheDice = new JButton("주사위 던지기");
		RollTheDice.setForeground(new Color(255, 255, 255));
		RollTheDice.setBackground(new Color(147, 112, 219));
		RollTheDice.setBounds(694, 436, 148, 162);
		RollTheDice.addActionListener(this);
		boardPanel.add(RollTheDice);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(242, 163, 439, 249);
		boardPanel.add(panel);
		panel.setLayout(null);
		
		info2 = new JLabel();
		info2.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 9));
		info2.setHorizontalAlignment(SwingConstants.CENTER);
		info2.setBounds(12, 29, 390, 46);
		panel.add(info2);
		
		buildVilla = new JButton("별장");
		buildVilla.setBackground(new Color(221, 160, 221));
		buildVilla.setBounds(119, 108, 95, 34);
		buildVilla.addActionListener((e)->{
			villaPurchase(players[turn],d[playerPosition]);
			OB.addVillaOB(d[playerPosition].getX()+20,d[playerPosition].getY()+50,players[turn]);
			repaint();
			clicked=true;
		});
		
		buildhotel = new JButton("호텔");
		buildhotel.setBackground(new Color(221, 160, 221));
		buildhotel.setBounds(226, 108, 95, 34);
		buildhotel.addActionListener((e)->{
			hotelPurchase(players[turn],d[playerPosition]);
			OB.addhotelOB(d[playerPosition].getX()+20,d[playerPosition].getY()+50,players[turn]);
			repaint();
			clicked=true;
		});
		
		buildBuilding = new JButton("빌딩");
		buildBuilding.setBackground(new Color(221, 160, 221));
		buildBuilding.setBounds(333, 108, 95, 34);
		buildBuilding.addActionListener((e)->{
			buildingPurchase(players[turn],d[playerPosition]);
			OB.addbuildingOB(d[playerPosition].getX()+20,d[playerPosition].getY()+50,players[turn]);
			repaint();
			clicked=true;
		});

		tollButton = new JButton("대지 매입");
		tollButton.setBackground(new Color(221, 160, 221));
		tollButton.setBounds(12, 108, 95, 34);
		tollButton.addActionListener((e)->{
			tollPurchase(players[turn],d[playerPosition]);
			clicked=true;
		});
	
		pass = new JButton("지나가기");
		pass.setBackground(new Color(216, 191, 216));
		pass.setBounds(177, 180, 95, 40);
		pass.addActionListener((e)->{
			panel.removeAll();
			clicked=true;
		});
		
		memberPanel = new JPanel();
		memberPanel.setBounds(1062, 130, 230, 430);
		memberPanel.setBackground(Color.WHITE);
		getContentPane().add(memberPanel);
		memberPanel.setLayout(null);
		
		//**********************************************************멤버 설정

		players = new member[count];
		System.out.println(count);
		playersMark = new playersObject();
		for(int i1 = 0; i1<players.length; i1++)
		{
			this.players[i1] = users[i1];
			this.players[i1].memberReset(11720000/players.length,i1);
		}
		
		rule = new gameRule(players.length);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		playersButtons = new JButton[4];
		
		for(i = 0; i<players.length;i++)
		{
			playersButtons[i] =new JButton("<html>"+"player : "+(i+1)+" 번"+"<br/>"
		+players[i].getName()+"    "+players[i].getState()+"<br/>"
		+"자산 : "+players[i].getMoney());//멤버 정보 추가하기
			playersButtons[i].setBackground(new Color(230, 230, 250));
			playersButtons[i].setBounds(12, 10+105*i, 206, 95);
			memberPanel.add(playersButtons[i]);
		}

		info = new JLabel("월드 투어 게임을 시작합니다.");
		info.setFont(new Font("타이포_쌍문동 B", Font.PLAIN, 12));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBackground(new Color(255, 255, 255));
		info.setBounds(1063, 10, 229, 110);
		
		okButton = new JButton("확인");
		okButton.setBackground(new Color(72, 209, 204));
		okButton.setBounds(278, 177, 124, 40);
		okButton.addActionListener((e)->{
			FeePurchase(players[turn], d[playerPosition]);
			info2.setText("비용을 지불하셨습니다.");
			clicked=true;
		});
		
		
		//************************************************** 아이템
		itempanel = new JPanel();
		itempanel.setBackground(new Color(240, 248, 255));
		itempanel.setBounds(1062, 570, 230, 180);
		if(option==1)
			getContentPane().add(itempanel);
		itempanel.setLayout(null);
		
		bombButton = new JButton("Bomb");
		bombButton.setBackground(new Color(72, 209, 204));
		bombButton.setBounds(12, 10, 95, 160);
		bombButton.addActionListener(this);
		itempanel.add(bombButton);
		
		internetButton = new JButton("Internet");
		internetButton.setBackground(new Color(72, 209, 204));
		internetButton.setBounds(123, 10, 95, 160);
		internetButton.addActionListener(this);
		itempanel.add(internetButton);
		
		internetOK = new JButton("구매");
		internetOK.setBounds(332, 196, 95, 23);
		internetOK.addActionListener(this);
		
		comboBox = new JComboBox();
		comboBox.setEditable(false);
		comboBox.setBounds(22, 86, 405, 82);
		
		
		comboBox2 = new JComboBox();
		comboBox2.setEditable(false);
		comboBox2.setBounds(22, 86, 405, 82);
		
		bombOK = new JButton("터뜨리기");
		bombOK.setBounds(332, 196, 95, 23);
		bombOK.addActionListener(this);
		//플레이어 설정
		getContentPane().add(info);
		setSize(1330,800);
		setVisible(true);
		Thread t = new gameThread();
		t.start();

	}
	
	//게임 플레이 스레드
	class gameThread extends Thread{
		public void run() {
			while(true)
			{
				rule.checkMoney(players, d, OB);//돈을 확인하고
				if(rule.checkRank())//자신이 혼자 남았다면
	 
				{
					rule.checkMoney(players, d, OB);
					setVisible(false);
					resultFrame r = new resultFrame(players);//결과 확인
					break;
					
				}
				
				for(int i = 0; i<players.length;i++)//멤버 정보 수정
				{
					playersButtons[i].setText("<html>"+"player : "+(i+1)+" 번"+"<br/>"
				+players[i].getName()+"    "+players[i].getState()+"<br/>"
				+"자산 : "+players[i].getMoney());//멤버 정보 추가하기
				}
				
				if(!clicked)//하고 내턴이 아닐때
				{
					RollTheDice.setEnabled(false);
					bombButton.setEnabled(false);
					internetButton.setEnabled(false);
				
				}
				else//내 턴일 때
				{
					RollTheDice.setEnabled(true);
					bombButton.setEnabled(true);
					internetButton.setEnabled(true);
				
				}
				
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
				}
			}
		}
	}

	//변경 사항 다시 그리기
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i = 0; i<2; i++)
			playersMark.getPlayerOB(i).draw(g);
		
		if(OB.villaOBIndex()!=0)
		{for(int i = 0; i<OB.villaOBIndex();i++)
			OB.getvillaOB(i).draw(g);}
		
		if(OB.hotelOBIndex()!=0)
		{for(int i = 0; i<OB.hotelOBIndex();i++)
			OB.gethotelOB(i).draw(g);}
		if(OB.buildingOBIndex()!=0)
		{for(int i = 0; i<OB.buildingOBIndex();i++)
			OB.getbuildingOB(i).draw(g);}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==RollTheDice) 
		{
			clicked=false;
			bombButton.setEnabled(false);
			internetButton.setEnabled(false);
			
			turn = rule.getTurn();

			if(!players[turn].getState().equals("파산")&&!players[turn].getState().equals("무인도"))//파산을 하지 않고 무인도라면 3턴을 기다림
			{
				Random rand = new Random();
				int res = rand.nextInt(6)+1;
				System.out.println(res);
				
				int tmp = players[turn].getCount();
				players[turn].setCount(tmp+res);	
				playerPosition = players[turn].getCount();
				System.out.println(players[turn].getCount());
				
				playersMark.getPlayerOB(turn).update(d[playerPosition].getX()+20,d[playerPosition].getY()+20);
				int flag = rule.checkRule(players[turn],d[playerPosition]);
				repaint();
				
				if(flag==0)
				{
					panel.removeAll();
					panel.add(tollButton);
					panel.add(buildBuilding);
					panel.add(buildhotel);
					panel.add(buildVilla);
					panel.add(pass);
					panel.add(info2);
					//아이템 추가하기
					info2.setText("빈 부지입니다. 무엇을 하시겠습니까?\n"
						+"별장 :"+d[playerPosition].getVillaPrice()+"호텔 :"+d[playerPosition].getVillaPrice()+
						"\n빌딩 : "+d[playerPosition].getBuildingPrice()+ "대지 : "+d[playerPosition].getTollPrice());

					panel.repaint();
					
					
				}
				
				//통행료 지불 상황
				if(flag==2)
				{
					panel.removeAll();
					panel.add(okButton);
					panel.add(info2);
					info2.setText(d[playerPosition].getOwnerPlayer().getName()+"의 별장입니다. 통행료를 지불해야 합니다."+
					d[playerPosition].getVillaFee());
					panel.repaint();
				}
				
				if(flag==3)
				{
					panel.removeAll();
					panel.add(okButton);
					panel.add(info2);
					info2.setText(d[playerPosition].getOwnerPlayer().getName()+"의 빌딩입니다. 통행료를 지불해야 합니다."+
					d[playerPosition].getBuildingFee());
					panel.repaint();
				}
				
				if(flag==4)
				{
					panel.removeAll();
					panel.add(okButton);
					panel.add(info2);
					info2.setText(d[playerPosition].getOwnerPlayer().getName()+"의 호텔입니다. 통행료를 지불해야 합니다."+
					d[playerPosition].getHotelFee());
					panel.repaint();
				}
				if(flag==5)
				{
					panel.removeAll();
					panel.add(okButton);
					panel.add(info2);
					info2.setText(d[playerPosition].getOwnerPlayer().getName()+"의 부지입니다. 통행료를 지불해야 합니다."+
					d[playerPosition].getTollFee());
					panel.repaint();
				}
				
				//본인 부지일 때
				if(flag==6) {
					panel.removeAll();
					panel.add(info2);
					info2.setText("본인 부지입니다.");
					panel.repaint();
					clicked=true;
				}
				
				if(flag==1)
				{
					panel.removeAll();
					panel.add(buildBuilding);
					panel.add(buildhotel);
					panel.add(buildVilla);
					panel.add(pass);
					panel.add(info2);
					info2.setText("이 대지의 소유자 입니다. 건물을 지으시겠습니까?"+"별장 :"+d[playerPosition].getVillaPrice()+"호텔 :"+d[playerPosition].getVillaPrice()+
							"\n빌딩 : "+d[playerPosition].getBuildingPrice());
					panel.repaint();
				}
				
				//황금 열쇠
				if(flag==21)
				{
					panel.removeAll();
					panel.add(info2);
					info2.setText("황금열쇠를 얻으셨습니다.");
					panel.repaint();
					clicked=true;
				}
				
				//무인도
				if(flag==31)
				{
					panel.removeAll();
					panel.add(info2);
					info2.setText("무인도에 갇히셨습니다! 3턴 동안 쉽니다.");
					panel.repaint();
					players[turn].setState("무인도");
					clicked=true;
				}
				
				//사회 복지기금
				if(flag==41)
				{
					panel.removeAll();
					panel.add(info2);
					info2.setText("사회 복지 기금 20만원이 입금됩니다.");
					panel.repaint();
					players[turn].setMoney(200000);
					clicked=true;
				}
				
				//여객선
				if(flag==51)
				{
					panel.removeAll();
					panel.add(okButton);
					panel.add(info2);
					panel.add(tollButton);
					info2.setText("여객선입니다.통행료를 내거나 구매해야합니다.");
					panel.repaint();
				}
				
				//한 바퀴를 보두 돌았을 때
				if(flag==-1)
				{
					panel.removeAll();
					panel.add(info2);
					info2.setText("월드 투어를 하셨습니다. 월급 10만원을 받습니다.");
					panel.repaint();
					players[turn].setMoney(100000);
					clicked=true;
				}
			}
			
			//무인도 상태일 때
			else if(players[turn].getState().equals("무인도"))
			{		players[turn].getIsland();
				clicked=true;
			}
			
			rule.changeTurn();
			info.setText(players[rule.getTurn()].getName()+"  차례입니다.");
			
		}
		
		//인터넷
		if(e.getSource()==internetButton)
		{
			clicked = false;
			panel.removeAll();
			//버튼 보이고 클릭하면 true
			panel.add(comboBox);
			panel.add(internetOK);
			panel.add(info2);
			
			comboBox.removeAllItems();
			info2.setText("인터넷을 사용하여 부지를 구매합니다. 도시를 선택하세요.");//결제
			internetPurchase(players[turn]);
			for(int i = 0; i<40; i++)
			{
				if(d[i].getOwnerPlayer()==null&&d[i].getID()<100&&d[i].getID()>0)
				{
					comboBox.addItem(d[i].getName());
				}
			}
			
			repaint();

		}
		
		//폭탄
		if(e.getSource()==bombButton)
		{
			clicked = false;
			panel.removeAll();
			turn = rule.getTurn();
			panel.add(comboBox2);
			panel.add(bombOK);
			panel.add(info2);
			bombPurchase(players[turn]);
			comboBox2.removeAllItems();
			info2.setText("폭탄을 사용하여 건물을 터뜨립니다.");//결제
			internetPurchase(players[turn]);
			for(int i = 0; i<40; i++)
			{
				if(d[i].getOwnerPlayer()!=players[turn]&&d[i].getOwnerPlayer()!=null&&d[i].getID()<100&&d[i].getID()>0)
				{
					comboBox2.addItem(d[i].getName());
				}
			}
			repaint();
		}
		
		if(e.getSource()==internetOK)
		{
			turn = rule.getTurn();
			info2.setText(comboBox.getSelectedItem()+"구매 완료");
			String name = (String)comboBox.getSelectedItem();
			for(int i = 0; i<40; i++)
			{
				if(d[i].getName().equals(name))
				{
					d[i].setOwnerPlayer(players[turn]);
					tollPurchase(players[turn],d[i]);
				}
			}
			
			rule.changeTurn();
			clicked = true;
		}
		
		if(e.getSource()==bombOK)
		{
			turn = rule.getTurn();
			info2.setText(comboBox.getSelectedItem()+" 터뜨렸습니다.");
			String name = (String)comboBox.getSelectedItem();
			for(int i = 0; i<40; i++)
			{
				if(d[i].getName().equals(name))
				{
					switch(d[i].getBuilding())
					{
					case 1:{
						for(int i1 = 0; i1<OB.villaOBIndex();i1++)
						{
							if(OB.villaGetX(i1)==d[i].getX()+20&&OB.villaGetY(i1)==d[i].getY()+50)
								OB.removeVilla(i1);
						}
					}
						break;
					case 2:{
						if(OB.buildingOBIndex()!=0)
						{
							for(int i1 = 0; i1<OB.buildingOBIndex();i1++)
							{
								if(OB.buildingGetX(i1)==d[i].getX()+20&&OB.buildingGetY(i1)==d[i].getY()+50)
									OB.removeBuilding(i1);
							}
						}
						
					}
						break;
					case 3:{
						if(OB.hotelOBIndex()!=0)
						{
							for(int i1 = 0; i1<OB.hotelOBIndex();i1++)
							{	if(OB.hotelGetX(i1)==d[i].getX()+20&&OB.hotelGetY(i1)==d[i].getY()+50)
									OB.removeHotel(i1);
							}
						}
					}
						break;
					}
					d[i].setOwnerPlayer(null);
				}
			}
			
			
			rule.changeTurn();
			clicked = true;
		}
		
	}
	
	
	//구매 메소드
	public void internetPurchase(member player)
	{
		player.setMoney(-300000);
	}
	
	public void bombPurchase(member player)
	{
		player.setMoney(-400000);

	}
	
	public void tollPurchase(member player, destination d) {
		player.setMoney(-d.getTollPrice());
		d.setOwnerPlayer(player);
		d.setBuilding(4);
		info2.setText("대지 매입 완료하셨습니다.");
	}
	
	public void villaPurchase(member player, destination d) {
		player.setMoney(-d.getVillaPrice());
		d.setOwnerPlayer(player);
		d.setBuilding(1);
		info2.setText("별장 건설 완료하셨습니다.");
	}
	
	public void hotelPurchase(member player, destination d) {
		player.setMoney(-d.getHotelPrice());
		d.setOwnerPlayer(player);
		d.setBuilding(3);
		info2.setText("호텔 건설 완료하셨습니다.");
	}
	
	public void buildingPurchase(member player, destination d) {
		player.setMoney(-d.getBuildingPrice());
		d.setOwnerPlayer(player);
		d.setBuilding(2);
		info2.setText("빌딩 건설 완료하셨습니다.");
	}
	
	public void FeePurchase(member player, destination d)
	{
		switch(d.getBuilding())
		{
		
		case 1 :System.out.println("별장료 : "+d.getVillaFee());
			int money = d.getVillaFee();
			player.setMoney(-money);
			d.getOwnerPlayer().setMoney(money);
			break;

		case 2:System.out.println("빌딩료 : "+d.getBuildingFee());
			int money1 =d.getBuildingFee();
			player.setMoney(-money1);
			d.getOwnerPlayer().setMoney(money1);
			break;
		
		case 3:System.out.println("호텔료 : "+d.getHotelFee());
			int money2 = d.getHotelFee();
			player.setMoney(-money2);
			d.getOwnerPlayer().setMoney(money2);
			break;
		
		case 4: System.out.println("통행료 : "+d.getTollFee());
			int money3= d.getTollFee();
			player.setMoney(-money3);
			d.getOwnerPlayer().setMoney(money3);
			break;
		default : System.out.println("통행료 : "+d.getTollFee());
			int money4= d.getTollFee();
			player.setMoney(-money4);
			break;
		}
	}

}
