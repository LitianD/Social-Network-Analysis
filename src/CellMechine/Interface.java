package CellMechine;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Interface extends JFrame{
	private JPanel[][] panels;//ÿ��panel��Ӧһ��ϸ��
	private Mechine mechine;
	private int Size;
	private int time;
	
	public Interface(int size)
	{
		this.Size = size;
		time = 10000;
		mechine = new Mechine(Size);
		panels = new JPanel[Size][Size];
		
		this.setLayout(new GridLayout(Size, Size));
		
		for(int i = 0; i < this.Size; i++)//��ÿ��panel���г�ʼ��
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				panels[i][j] = new JPanel();//����һ���µ�panel
				this.add(panels[i][j]);//���뵽JFrame��
				panels[i][j].setPreferredSize(new Dimension(20, 20));//���ú��ʴ�С
				panels[i][j].setBackground(mechine.getColor(i, j));//������ɫ
				panels[i][j].setBorder(new EtchedBorder(EtchedBorder.RAISED));//���ñ߽�
			}
		}
	}
	
	public void run()
	{	
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		for(int count = 0 ; count < this.time; count ++)
		{
			this.mechine.loop();//����������
			
			try {
				Thread.sleep(100);//��ͣһ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < this.Size; i++)
			{
				for(int j = 0 ; j < this.Size; j++)
				{
					panels[i][j].setBackground(mechine.getColor(i, j));//���¸�ֵ��ɫ
				}
			}
			
			this.repaint();//ˢ����Ļ
		}
		
	}
	
	public static void main(String[] args)
		{
			new Interface(20).run();
	}
}
