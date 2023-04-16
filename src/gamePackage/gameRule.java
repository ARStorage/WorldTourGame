package gamePackage;
import ServerPackage.member;

public class gameRule {
	private int turn;
	private int count;
	private int rank;
	
	public gameRule(int count) {
		this.turn = 0;
		this.count = count;
		this.rank = count;//게임 정보를 초기화 한다.
	}
	
	public void changeTurn() {
		
		turn++;//턴을 바꾼다
		
		if(turn==count)
			turn = 0;//다 돌았으면 다시 처음 사람으로 돌아간다.
	}
	
	public int getTurn() {
		return this.turn;//턴 확인
	}
	
	public int checkRule(member player, destination d) {
		//플레이어 상태 확인
		if(player.getState().equals("파산"))
			return -1;
		
		
		//특수부지가 아닐때
		if(d.getID()<100)
		{
			if(d.getBuilding()==-1)
			{
				System.out.println("빈부지입니다. 별장, 호텔, 빌딩, 대지"
			+d.getVillaPrice()+"   "
			+d.getHotelPrice()+"   "
			+d.getBuildingPrice()+"   "
			+d.getTollPrice());
				
				return 0;//빈건물
			}
			
			//건물을 지을 수 있음
			else if(d.getBuilding()==4&&d.getOwnerPlayer()==player)//자기 부지
			{
				System.out.println("건물을 짓겠습니까?");
				return 1;
			}
			
			//본인 부지일 때
			else if(d.getOwnerPlayer()==player)
			{
				System.out.println("본인 부지");
				return 6;
			}
			
			//자신의 부지가 아니므로 통행료를 내야함
			else {
				System.out.println("통행료를 내야 합니다.");
				switch(d.getBuilding())
				{
				case -1:System.out.println("오류");
					return -1;
					
				case 1 :System.out.println("별장료 : "+d.getVillaFee());
					return 2;

				case 2:System.out.println("빌딩료 : "+d.getBuildingFee());
					return 3;
				
				case 3:System.out.println("호텔료 : "+d.getHotelFee());
					return 4;
				
				case 4: System.out.println("통행료 : "+d.getTollFee());
					return 5;
				}
			}
		}
		
		else if(d.getID()<300)//황금 열쇠
			return 21;
		
		else if(d.getID()<400)//무인도
			return 31;
		else if(d.getID()<500)//사회복지기금
			return 41;
		else if(d.getID()<600)//여객기
			return 51;
		
		else if(d.getID()==-1)
			return -2;
		return -1;
		
	}
	
	public boolean checkRank() {

				
		if(this.rank==1)
		{
			return true;//게임끝
		}
		
		return false;//게임 계속
}
	
	public boolean checkMoney(member[] player,destination[] d,buildObject OB) {
		
		for(int i = 0; i<player.length; i++)//상태 확인
		{
			//최근에 파산을 한 플레이어가 있으면
			if(player[i].getMoney()<0&&!player[i].getState().equals("파산"))
			{
				//상태를 바꾸고
				player[i].setState("파산");
				
				//그 플레이어가 가진 모든 건물과 부지 해제
				for(int i1 = 0; i1<40; i1++)
				{
					if(d[i1].getOwnerPlayer()==player[i])
					{
						d[i1].setOwnerPlayer(null);
						d[i1].setBuilding(-1);
					}
				}
				
				if(OB.villaOBIndex()!=0)
				{
					for(int i1 = 0; i1<OB.villaOBIndex();i1++)
					{
						if(OB.getVillaOwner(i1)==player[i])
							OB.removeVilla(i1);
					}
				}
			
				if(OB.hotelOBIndex()!=0)
				{
					for(int i1 = 0; i1<OB.hotelOBIndex();i1++)
					{	if(OB.getHotelOwner(i1)==player[i])
							OB.removeHotel(i1);
					}
				}
				if(OB.buildingOBIndex()!=0)
				{
					for(int i1 = 0; i1<OB.buildingOBIndex();i1++)
					{
						if(OB.getBuildingOwner(i1)==player[i])
							OB.removeBuilding(i1);
					}
				}
			
				if (this.rank==1)
				{
					player[i].setRank(this.rank);
					return true;
				}
				
				else
				{
					player[i].setRank(this.rank);
					this.rank--;
					return false;
				}
			}
			
			else if (player[i].getMoney()>0&&this.rank==1)
			{
				System.out.println("실행됨");
				player[i].setRank(this.rank);
				return true;
			}
			
		}
		return false;
		
	}
	
	

}
