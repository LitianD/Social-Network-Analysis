package CellMechine;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Main extends JFrame{
	private JPanel resourceP;
	private JPanel skillP;
	private JPanel centerP;
	private JPanel[][] panels;//每个panel对应一个细胞
	private JPanel[][] panels2;//每个panel对应一个细胞
	private Mechine mechine;
	private int Size;
	private int time;

	public Main(int size)
	{
		this.Size = size;
		time = 10000;
		mechine = new Mechine(Size);
		panels = new JPanel[Size][Size];
		panels2 = new JPanel[Size][Size];

		this.setLayout(new BorderLayout());
		this.skillP = new JPanel();
		this.skillP.setLayout(new GridLayout(Size, Size));
		this.resourceP = new JPanel();
		this.resourceP.setLayout(new GridLayout(Size, Size));

		this.centerP = new JPanel();
		centerP.setPreferredSize(new Dimension(50, 20));//设置合适大小
		centerP.setBackground(Color.white);//设置颜色
		this.add(centerP, BorderLayout.CENTER);


		for(int i = 0; i < this.Size; i++)//对每个panel进行初始化
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				panels[i][j] = new JPanel();//声明一个新的panel
				panels2[i][j] = new JPanel();//声明一个新的panel
				this.skillP.add(panels[i][j]);//加入到JFrame中
				this.resourceP.add(panels2[i][j]);
				this.add(skillP, BorderLayout.WEST);
				this.add(resourceP, BorderLayout.EAST);

				panels[i][j].setPreferredSize(new Dimension(20, 20));//设置合适大小
				panels[i][j].setBackground(mechine.getColor(i, j));//设置颜色
				panels[i][j].setBorder(new EtchedBorder(EtchedBorder.RAISED));//设置边界

				panels2[i][j].setPreferredSize(new Dimension(20, 20));//设置合适大小
				panels2[i][j].setBackground(mechine.getColor(i, j));//设置颜色
				panels2[i][j].setBorder(new EtchedBorder(EtchedBorder.RAISED));//设置边界
			}
		}
	}

	public void run()
	{
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);


		for(int count = 1 ; count < this.time; count ++)
		{
			this.mechine.loop(count);//遍历所有人

			try {
				Thread.sleep(10);//暂停一会
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for(int i = 0; i < this.Size; i++)
			{
				for(int j = 0 ; j < this.Size; j++)
				{
					panels[i][j].setBackground(mechine.getColor(i, j));//重新赋值颜色
					panels2[i][j].setBackground(mechine.getSkillColor(i, j));
				}
			}

			this.repaint();//刷新屏幕
		}

	}

	public static void main(String[] args)
	{
		new Main(20).run();
	}
}
