package CellMechine;

import java.awt.Color;

public class Mechine {
	private int Size;
	//private int Resource = 2000;

	private live[][] mechine;


	public Mechine(int size)
	{
		this.Size = size;
		
		mechine= new live[Size][Size];//��ʼ��ϸ������
		for(int i = 0; i < this.Size; i++){
			for(int j = 0 ; j < this.Size; j++){
				mechine[i][j] = new live();
			}
		}


        //int usedRes = 0;
        for (int count = 0; count < this.Size * this.Size / 2; count++){
            int i = (int)(Math.random() * this.Size);
            int j = (int)(Math.random() * this.Size);
            int res = (int)(Math.random() * 200);
            if (mechine[i][j].isLive()){
                count--;
            }
            else {
                mechine[i][j].refresh(res, (int)(Math.random() * 50));
            }
        }


		for(int i = 0; i < this.Size; i++)//������ɻ�ϸ������ϸ��
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				if(! (mechine[i][j].isLive()))
				{
					mechine[i][j].refresh(0, 0);
				}
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
				if(mechine[i][j].isLive())
				{
					instance = (live)mechine[i][j];

					if(i > 0 && (mechine[i - 1][j].isLive())){
						trade(mechine[i][j], mechine[i - 1][j]);
						instance = (live)mechine[i - 1][j];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i - 1][j].refresh(0, 0);
						}
					}
					if(j > 0 && (mechine[i][j - 1].isLive())){
						trade(mechine[i][j], mechine[i][j - 1]);
						instance = (live)mechine[i][j - 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i][j - 1].refresh(0, 0);
						}
					}
					if((i < (Size - 1) && mechine[i + 1][j].isLive())){
						trade(mechine[i][j], mechine[i + 1][j]);
						instance = (live)mechine[i + 1][j];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i + 1][j].refresh(0, 0);
						}
					}
					if(j < (Size - 1) && mechine[i][j + 1].isLive()){
						trade(mechine[i][j], mechine[i][j + 1]);
						instance = (live)mechine[i][j + 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i][j + 1].refresh(0, 0);
						}
					}
					if(i > 0 && j > 0 && (mechine[i - 1][j - 1].isLive())){
						trade(mechine[i][j], mechine[i - 1][j - 1]);
						instance = (live)mechine[i - 1][j - 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i - 1][j - 1].refresh(0, 0);
						}
					}
					if(i > 0 && j < (Size - 1) && (mechine[i - 1][j + 1].isLive())){
						trade(mechine[i][j], mechine[i - 1][j + 1]);
						instance = (live)mechine[i - 1][j + 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i - 1][j + 1].refresh(0, 0);
						}
					}
					if(j > 0 && i < (Size - 1) &&(mechine[i + 1][j - 1].isLive())){
						trade(mechine[i][j], mechine[i + 1][j- 1]);
						instance = (live)mechine[i + 1][j - 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i + 1][j - 1].refresh(0, 0);
						}
					}
					if(i < (Size - 1) && j < (Size - 1) && (mechine[i + 1][j + 1].isLive())){
						trade(mechine[i][j], mechine[i + 1][j + 1]);
						instance = (live)mechine[i + 1][j + 1];
						if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
						{
							mechine[i + 1][j + 1].refresh(0, 0);
						}
					}

					instance = (live)mechine[i][j];
					if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
					{
						mechine[i][j].refresh(0, 0);
						continue;
					}

					//�ж�ĳ��ϸ����Χ�ֿյ�
					if(i > 0 && (!mechine[i - 1][j].isLive())){
						born(mechine[i][j], i - 1, j);
					}
					if(j > 0 && (!mechine[i][j - 1].isLive())){
						born(mechine[i][j], i ,j - 1);
					}
					if((i < (Size - 1) && !mechine[i + 1][j].isLive())){
						born(mechine[i][j], i + 1, j );
					}
					if(j < (Size - 1) && !mechine[i][j + 1].isLive()){
						born(mechine[i][j], i, j + 1);
					}
					if(i > 0 && j > 0 && !(mechine[i - 1][j - 1].isLive())){
						born(mechine[i][j], i - 1, j - 1);
					}
					if(i > 0 && j < (Size - 1) && !(mechine[i - 1][j + 1].isLive())){
						born(mechine[i][j], i - 1, j + 1);
					}
					if(j > 0 && i < (Size - 1) && !(mechine[i + 1][j - 1].isLive())){
						born(mechine[i][j], i + 1, j - 1);
					}
					if(i < (Size - 1) && j < (Size - 1) && !(mechine[i + 1][j + 1].isLive())){
						born(mechine[i][j], i + 1, j + 1);
					}

				}
				
			}
		}
	}

	//��һ��cellһ����live���ڶ�����die
	private void born(live oldCell, int i, int j)
	{
		if((oldCell).resource > 30 && Math.random()<0.25)	//�������Ҫ��
		{
			/**
			 * ������ط�д�����ӵĺ���
			 */
            ((live)oldCell).resource = ((live)oldCell).resource / 2;
            mechine[i][j].refresh(((live)oldCell).resource / 2, ((live)oldCell).skill/2);
		}
	}

	//����cell����live
	private void trade(live liveOne, live liveTwo){

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
