package CellMechine;

import java.awt.Color;

public class Mechine {
	private int Size;
	//private int Resource = 2000;

	private live[][] mechine;


	public Mechine(int size)
	{
		this.Size = size;
		
		mechine= new live[Size][Size];//初始化细胞数组
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


		for(int i = 0; i < this.Size; i++)//随机生成活细胞和死细胞
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
	
	public Color getColor(int i, int j)//返回位于i，j出细胞的颜色
	{
		return mechine[i][j].getColor();
	}
	
	public void loop()//一次循环遍历，对每个人都是一个社交周期
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
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i - 1][j].refresh(0, 0);
						}
					}
					if(j > 0 && (mechine[i][j - 1].isLive())){
						trade(mechine[i][j], mechine[i][j - 1]);
						instance = (live)mechine[i][j - 1];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i][j - 1].refresh(0, 0);
						}
					}
					if((i < (Size - 1) && mechine[i + 1][j].isLive())){
						trade(mechine[i][j], mechine[i + 1][j]);
						instance = (live)mechine[i + 1][j];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i + 1][j].refresh(0, 0);
						}
					}
					if(j < (Size - 1) && mechine[i][j + 1].isLive()){
						trade(mechine[i][j], mechine[i][j + 1]);
						instance = (live)mechine[i][j + 1];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i][j + 1].refresh(0, 0);
						}
					}
					if(i > 0 && j > 0 && (mechine[i - 1][j - 1].isLive())){
						trade(mechine[i][j], mechine[i - 1][j - 1]);
						instance = (live)mechine[i - 1][j - 1];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i - 1][j - 1].refresh(0, 0);
						}
					}
					if(i > 0 && j < (Size - 1) && (mechine[i - 1][j + 1].isLive())){
						trade(mechine[i][j], mechine[i - 1][j + 1]);
						instance = (live)mechine[i - 1][j + 1];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i - 1][j + 1].refresh(0, 0);
						}
					}
					if(j > 0 && i < (Size - 1) &&(mechine[i + 1][j - 1].isLive())){
						trade(mechine[i][j], mechine[i + 1][j- 1]);
						instance = (live)mechine[i + 1][j - 1];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i + 1][j - 1].refresh(0, 0);
						}
					}
					if(i < (Size - 1) && j < (Size - 1) && (mechine[i + 1][j + 1].isLive())){
						trade(mechine[i][j], mechine[i + 1][j + 1]);
						instance = (live)mechine[i + 1][j + 1];
						if(!instance.isLive())//如果寿命为0，则变为一个死细胞
						{
							mechine[i + 1][j + 1].refresh(0, 0);
						}
					}

					instance = (live)mechine[i][j];
					if(!instance.isLive())//如果寿命为0，则变为一个死细胞
					{
						mechine[i][j].refresh(0, 0);
						continue;
					}

					//判断某个细胞周围又空地
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

	//第一个cell一定是live，第二个是die
	private void born(live oldCell, int i, int j)
	{
		if((oldCell).resource > 30 && Math.random()<0.25)	//这个几率要改
		{
			/**
			 * 在这个地方写生孩子的函数
			 */
            ((live)oldCell).resource = ((live)oldCell).resource / 2;
            mechine[i][j].refresh(((live)oldCell).resource / 2, ((live)oldCell).skill/2);
		}
	}

	//两个cell都是live
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



		if(Math.random() < tradeRate)	//这个几率要改
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
			// TODO: 2019/5/20 小的社交能力如何减少待确认。
			liveSmall.skill -= 0;

			return;
		}
	}
	
}
