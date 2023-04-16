package ServerPackage;

public class member{
	//기본 정보
	private String name=null;
	private String id=null;
	private String password=null;
	private int score;
	
	//게임중 정보
	private int count; //주사위 카운트 한바퀴 돌았느냐
	private int money;//돈
	private int turn;//차례
	private int loan;//옵션 : 대출금
	private int rank;
	private String state;//플레이어 상태
	private int island;
	
	public member(String name, String id, String password, int score)
	{
		this.name = name;
		this.id = id;
		this.password = password;
		this.score = score;
	}
	
	public void memberReset(int money, int turn) {
		this.count = 0; //주사위 카운트
		this.money = money;//돈
		this.turn = turn;//차례
		this.loan = 0;//옵션 : 대출금
		this.rank = 0;
		this.state ="플레이중";//플레이어 상태
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
			this.state = "플레이중";
		return this.island;
	}

}