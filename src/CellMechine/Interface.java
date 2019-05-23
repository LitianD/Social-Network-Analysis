package CellMechine;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Interface extends JFrame{
	private JPanel resourceP;
	private JPanel skillP;
	private JPanel centerP;
	private JPanel[][] panels;//ÿ��panel��Ӧһ��ϸ��
	private JPanel[][] panels2;//ÿ��panel��Ӧһ��ϸ��
	private Mechine mechine;
	private int Size;
	private int time;

	public Interface(int size)
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
		centerP.setPreferredSize(new Dimension(50, 20));//���ú��ʴ�С
		centerP.setBackground(Color.white);//������ɫ
		this.add(centerP, BorderLayout.CENTER);


		for(int i = 0; i < this.Size; i++)//��ÿ��panel���г�ʼ��
		{
			for(int j = 0 ; j < this.Size; j++)
			{
				panels[i][j] = new JPanel();//����һ���µ�panel
				panels2[i][j] = new JPanel();//����һ���µ�panel
				this.skillP.add(panels[i][j]);//���뵽JFrame��
				this.resourceP.add(panels2[i][j]);
				this.add(skillP, BorderLayout.WEST);
				this.add(resourceP, BorderLayout.EAST);

				panels[i][j].setPreferredSize(new Dimension(20, 20));//���ú��ʴ�С
				panels[i][j].setBackground(mechine.getColor(i, j));//������ɫ
				panels[i][j].setBorder(new EtchedBorder(EtchedBorder.RAISED));//���ñ߽�

				panels2[i][j].setPreferredSize(new Dimension(20, 20));//���ú��ʴ�С
				panels2[i][j].setBackground(mechine.getColor(i, j));//������ɫ
				panels2[i][j].setBorder(new EtchedBorder(EtchedBorder.RAISED));//���ñ߽�
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
			this.mechine.loop(count);//����������

			try {
				Thread.sleep(10);//��ͣһ��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for(int i = 0; i < this.Size; i++)
			{
				for(int j = 0 ; j < this.Size; j++)
				{
					panels[i][j].setBackground(mechine.getColor(i, j));//���¸�ֵ��ɫ
					panels2[i][j].setBackground(mechine.getSkillColor(i, j));
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
