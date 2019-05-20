package CellMechine;

import java.awt.Color;

public class Mechine {
	private int Size;
	
	private Cell[][] mechine;
	
	
	public Mechine(int size)
	{
		this.Size = size;
		
		mechine= new Cell[Size][Size];//��ʼ��ϸ������
		
		for(int i = 0; i < this.Size; i++)//������ɻ�ϸ������ϸ��
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				if(Math.random() < 0.4)//����Ҫ��
				{
					//������д��ʼ����صĲ���

					mechine[i][j] = new live((int)(Math.random() * 50),(int)(Math.random() * 50));
				}
				else
					mechine[i][j] = new Die();
				
			}
		}
	}
	
	public void setSize(int Size)
	{
		this.Size = Size;
	}
	public int getSize()
	{
		return Size;
	}
	
	public Color getColor(int i, int j)//����λ��i��j��ϸ������ɫ
	{
		return mechine[i][j].getColor();
	}
	
	public void loop()//һ��ѭ����������ÿ���˶���һ���罻����
	{
		live instance;
		
		for(int i = 0; i < this.Size; i++)
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				if(mechine[i][j] instanceof live)
				{
					if(i > 0 && (mechine[i - 1][j] instanceof live)){
						trade(mechine[i][j], mechine[i - 1][j]);
						instance = (live)mechine[i - 1][j];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i - 1][j] = new Die();
						}
					}
					if(j > 0 && (mechine[i][j - 1] instanceof live)){
						trade(mechine[i][j], mechine[i][j - 1]);
						instance = (live)mechine[i][j - 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i][j - 1] = new Die();
						}
					}
					if((i < (Size - 1) && mechine[i + 1][j] instanceof live)){
						trade(mechine[i][j], mechine[i + 1][j]);
						instance = (live)mechine[i + 1][j];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i + 1][j] = new Die();
						}
					}
					if(j < (Size - 1) && mechine[i][j + 1] instanceof live){
						trade(mechine[i][j], mechine[i][j + 1]);
						instance = (live)mechine[i][j + 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i][j + 1] = new Die();
						}
					}

					instance = (live)mechine[i][j];
					if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
					{
						mechine[i][j] = new Die();
					}

					//�ж�ĳ��ϸ����Χ�ֿյ�
					if(i > 0 && (mechine[i - 1][j] instanceof Die)){
						born(mechine[i][j], mechine[i - 1][j]);
					}
					if(j > 0 && (mechine[i][j - 1] instanceof Die)){
						born(mechine[i][j], mechine[i][j - 1]);
					}
					if((i < (Size - 1) && mechine[i + 1][j] instanceof Die)){
						born(mechine[i][j], mechine[i + 1][j]);
					}
					if(j < (Size - 1) && mechine[i][j + 1] instanceof Die){
						born(mechine[i][j], mechine[i][j + 1]);
					}

				}
				
			}
		}
	}

	//��һ��cellһ����live���ڶ�����die
	private void born(Cell oldCell, Cell newCell)
	{


		if(Math.random() < 0.15)	//�������Ҫ��
		{
			/**
			 * ������ط�д�����ӵĺ���
			 */


		}
	}
	//����cell����live
	private void trade(Cell cellOne, Cell cellTwo){

		live liveOne = (live)cellOne;
		live liveTwo = (live)cellTwo;
		live liveBig;
		live liveSmall;
		if(liveOne.skill > liveTwo.skill){
			liveBig = liveOne;
			liveSmall = liveTwo;
		}else{
			liveBig = liveTwo;
			liveSmall = liveOne;
		}
		double tradeRate = (double)(liveBig.skill)/(double)(liveBig.skill + liveSmall.skill);



		if(Math.random() < tradeRate)	//�������Ҫ��
		{
			if(Math.random() < tradeRate){
				liveBig.resource += (liveBig.skill - liveSmall.skill);
				liveSmall.resource -= (liveBig.skill - liveSmall.skill);
			}else {
				liveSmall.resource += (liveBig.skill - liveSmall.skill);
				liveBig.resource -= (liveBig.skill - liveSmall.skill);
			}

			liveBig.skill += 1;
			liveSmall.skill +=2;
		}
		else{
			liveBig.skill -= 1;
			// TODO: 2019/5/20 С���罻������μ��ٴ�ȷ�ϡ�
			liveSmall.skill -= 0;

			return;
		}
	}
	
}
