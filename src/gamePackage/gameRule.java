package gamePackage;
import ServerPackage.member;

public class gameRule {
	private int turn;
	private int count;
	private int rank;
	
	public gameRule(int count) {
		this.turn = 0;
		this.count = count;
		this.rank = count;//���� ������ �ʱ�ȭ �Ѵ�.
	}
	
	public void changeTurn() {
		
		turn++;//���� �ٲ۴�
		
		if(turn==count)
			turn = 0;//�� �������� �ٽ� ó�� ������� ���ư���.
	}
	
	public int getTurn() {
		return this.turn;//�� Ȯ��
	}
	
	public int checkRule(member player, destination d) {
		//�÷��̾� ���� Ȯ��
		if(player.getState().equals("�Ļ�"))
			return -1;
		
		
		//Ư�������� �ƴҶ�
		if(d.getID()<100)
		{
			if(d.getBuilding()==-1)
			{
				System.out.println("������Դϴ�. ����, ȣ��, ����, ����"
			+d.getVillaPrice()+"   "
			+d.getHotelPrice()+"   "
			+d.getBuildingPrice()+"   "
			+d.getTollPrice());
				
				return 0;//��ǹ�
			}
			
			//�ǹ��� ���� �� ����
			else if(d.getBuilding()==4&&d.getOwnerPlayer()==player)//�ڱ� ����
			{
				System.out.println("�ǹ��� ���ڽ��ϱ�?");
				return 1;
			}
			
			//���� ������ ��
			else if(d.getOwnerPlayer()==player)
			{
				System.out.println("���� ����");
				return 6;
			}
			
			//�ڽ��� ������ �ƴϹǷ� ����Ḧ ������
			else {
				System.out.println("����Ḧ ���� �մϴ�.");
				switch(d.getBuilding())
				{
				case -1:System.out.println("����");
					return -1;
					
				case 1 :System.out.println("����� : "+d.getVillaFee());
					return 2;

				case 2:System.out.println("������ : "+d.getBuildingFee());
					return 3;
				
				case 3:System.out.println("ȣ�ڷ� : "+d.getHotelFee());
					return 4;
				
				case 4: System.out.println("����� : "+d.getTollFee());
					return 5;
				}
			}
		}
		
		else if(d.getID()<300)//Ȳ�� ����
			return 21;
		
		else if(d.getID()<400)//���ε�
			return 31;
		else if(d.getID()<500)//��ȸ�������
			return 41;
		else if(d.getID()<600)//������
			return 51;
		
		else if(d.getID()==-1)
			return -2;
		return -1;
		
	}
	
	public boolean checkRank() {

				
		if(this.rank==1)
		{
			return true;//���ӳ�
		}
		
		return false;//���� ���
}
	
	public boolean checkMoney(member[] player,destination[] d,buildObject OB) {
		
		for(int i = 0; i<player.length; i++)//���� Ȯ��
		{
			//�ֱٿ� �Ļ��� �� �÷��̾ ������
			if(player[i].getMoney()<0&&!player[i].getState().equals("�Ļ�"))
			{
				//���¸� �ٲٰ�
				player[i].setState("�Ļ�");
				
				//�� �÷��̾ ���� ��� �ǹ��� ���� ����
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
				System.out.println("�����");
				player[i].setRank(this.rank);
				return true;
			}
			
		}
		return false;
		
	}
	
	

}
