package ServerPackage;

public class member{
	//�⺻ ����
	private String name=null;
	private String id=null;
	private String password=null;
	private int score;
	
	//������ ����
	private int count; //�ֻ��� ī��Ʈ �ѹ��� ���Ҵ���
	private int money;//��
	private int turn;//����
	private int loan;//�ɼ� : �����
	private int rank;
	private String state;//�÷��̾� ����
	private int island;
	
	public member(String name, String id, String password, int score)
	{
		this.name = name;
		this.id = id;
		this.password = password;
		this.score = score;
	}
	
	public void memberReset(int money, int turn) {
		this.count = 0; //�ֻ��� ī��Ʈ
		this.money = money;//��
		this.turn = turn;//����
		this.loan = 0;//�ɼ� : �����
		this.rank = 0;
		this.state ="�÷�����";//�÷��̾� ����
		//this.img = img;
	}
	
	//public GraphicObject getImg() {return this.img;}
	
	public void setName(String name){this.name = name;}
	
	public void setScore(int score){this.score = score;}
	
	public void setPassword(String password){this.password = password;}
	
	public String getId(){return this.id;}
	
	public String getPassword() {return this.password;}
	
	public String getName() {return this.name;}
	
	public int getscore() {return this.score;}
	
	public void setCount(int count) {
		this.count = count;
		if(count>=40)
		{this.count = this.count-40;}
	}
	public int getCount() {return this.count;}
	
	public void setMoney(int money) {
		this.money+= money;}
	public int getMoney() {return this.money;}
	
	public void setTurn(int turn) {this.turn = turn;}
	public int getTurn() {return this.turn;}
	
	public void setLoan(int loan) {this.loan+=loan;}
	public int getLaon() {return this.loan;}
	
	public void setRank(int rank) {this.rank = rank;}
	public int getRank() {return this.rank;}
	
	public void setState(String state) {this.state = state;}
	public String getState() {return this.state;}
	
	public void setIsland() {this.island = 3;}
	public int getIsland() {
		this.island--;
		if(island<0)
			this.state = "�÷�����";
		return this.island;
	}

}