package CellMechine;

import java.awt.Color;

//������ʾ���ŵ���
public class live implements Cell{
	protected int resource;	//��Դ
	protected int skill;	//�罻����
	
	public live(int Resource, int skill)
	{
		this.resource = Resource;
		this.skill = skill;
	}
	
	//���ر�ʾ���ϸ������ɫ
	public Color getColor()
	{
		int degree = 255 - this.resource * 4;	//��ԴԽ��Խ��
		return new Color(degree, degree, degree);
	}
	
	//isDie��ʾ���ϸ���Ƿ��Ѿ�����������һ��ˢ��֮���ж��Ƿ�Ҫ�����ϸ�����Die
	// ����Դû�˾�����
	public boolean isLive()
	{
		if(this.resource > 0)
			return true;
		else
			return false;
	}
	
	
}
