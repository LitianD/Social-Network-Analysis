package CellMechine;

import java.awt.Color;

public class Mechine {
	private int Size;
	//private int Resource = 2000;

	private Cell[][] mechine;
	
	public Mechine(int size)
	{
		this.Size = size;
		
		mechine= new Cell[Size][Size];//��ʼ��ϸ������

        //int usedRes = 0;
        for (int count = 0; count < this.Size * this.Size / 4; count++){
            int i = (int)(Math.random() * this.Size);
            int j = (int)(Math.random() * this.Size);
            int res = (int)(Math.random() * 50);
            if (mechine[i][j] instanceof live){
                count--;
            }
            else {
                mechine[i][j] = new live(res, (int)(Math.random() * 50));
            }
        }


		for(int i = 0; i < this.Size; i++)//������ɻ�ϸ������ϸ��
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				if(! (mechine[i][j] instanceof live))
				{
					mechine[i][j] = new Die();
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

					if(!instance.isLive())//�������Ϊ0�����Ϊһ����ϸ��
					{
						mechine[i][j] = new Die();
					}

					//�ж�ĳ��ϸ����Χ�ֿյ�
					if(i > 0 && (mechine[i - 1][j] instanceof Die)){
						born(mechine[i][j], i - 1, j);
					}
					if(j > 0 && (mechine[i][j - 1] instanceof Die)){
						born(mechine[i][j], i ,j - 1);
					}
					if((i < (Size - 1) && mechine[i + 1][j] instanceof Die)){
						born(mechine[i][j], i + 1, j );
					}
					if(j < (Size - 1) && mechine[i][j + 1] instanceof Die){
						born(mechine[i][j], i, j + 1);
					}

				}
				
			}
		}
	}

	//��һ��cellһ����live���ڶ�����die
	private void born(Cell oldCell, int i, int j)
	{
		if(((live)oldCell).resource > 100)	//�������Ҫ��
		{
			/**
			 * ������ط�д�����ӵĺ���
			 */
            ((live)oldCell).resource = ((live)oldCell).resource / 2;
            mechine[i][j] = new live(((live)oldCell).resource / 2, ((live)oldCell).skill/2);
		}
	}

	//����cell����live
	private void trade(Cell cellOne, Cell cellTwo){
		if(Math.random() < 0.15)	//�������Ҫ��
		{
			/**
			 * ������ط�д���׵ĺ���
			 */
		}
	}
	
}
