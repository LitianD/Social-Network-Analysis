package CellMechine;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Interface extends JFrame{
	private JPanel[][] panels;//每个panel对应一个细胞
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
		
		for(int i = 0; i < this.Size; i++)//对每个panel进行初始化
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				panels[i][j] = new JPanel();//声明一个新的panel
				this.add(panels[i][j]);//加入到JFrame中
				panels[i][j].setPreferredSize(new Dimension(20, 20));//设置合适大小
				panels[i][j].setBackground(mechine.getColor(i, j));//设置颜色
				panels[i][j].setBorder(new EtchedBorder(EtchedBorder.RAISED));//设置边界
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
			this.mechine.loop();//遍历所有人
			
			try {
				Thread.sleep(100);//暂停一会
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < this.Size; i++)
			{
				for(int j = 0 ; j < this.Size; j++)
				{
					panels[i][j].setBackground(mechine.getColor(i, j));//重新赋值颜色
				}
			}
			
			this.repaint();//刷新屏幕
		}
		
	}
	
	public static void main(String[] args)
		{
			new Interface(20).run();
	}
}
