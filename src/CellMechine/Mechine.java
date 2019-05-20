package CellMechine;

import java.awt.Color;

public class Mechine {
	private int Size;
	
	private Cell[][] mechine;
	
	
	public Mechine(int size)
	{
		this.Size = size;
		
		mechine= new Cell[Size][Size];//初始化细胞数组
		
		for(int i = 0; i < this.Size; i++)//随机生成活细胞和死细胞
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				if(Math.random() < 0.2)
				{
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
				if(mechine[i][j] instanceof live)
				{
					instance = (live)mechine[i][j];

					if(i > 0 && (mechine[i - 1][j] instanceof live)){
						trade(mechine[i][j], mechine[i - 1][j]);
					}
					if(j > 0 && (mechine[i][j - 1] instanceof live)){
						trade(mechine[i][j], mechine[i][j - 1]);
					}
					if((i < (Size - 1) && mechine[i + 1][j] instanceof live)){
						trade(mechine[i][j], mechine[i + 1][j]);
					}
					if(j < (Size - 1) && mechine[i][j + 1] instanceof live){
						trade(mechine[i][j], mechine[i][j + 1]);
					}

					if(!instance.isLive())//如果寿命为0，则变为一个死细胞
					{
						mechine[i][j] = new Die();
					}

					//判断某个细胞周围又空地
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

	//第一个cell一定是live，第二个是die
	private void born(Cell oldCell, Cell newCell)
	{
		if(Math.random() < 0.15)	//这个几率要改
		{
			/**
			 * 在这个地方写生孩子的函数
			 */
		}
	}
	//两个cell都是live
	private void trade(Cell cellOne, Cell cellTwo){
		if(Math.random() < 0.15)	//这个几率要改
		{
			/**
			 * 在这个地方写交易的函数
			 */
		}
	}
	
}
