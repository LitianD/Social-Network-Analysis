package CellMechine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//这个类表示活着的人
public class live implements Cell{
	public int resource;	//资源
	public int skill;	//社交能力
	public List<State> record = new ArrayList<State>();


	public live()
	{
		this.resource = 0;
		this.skill = 0;
	}

	public void refresh(int Resource, int skill){
		this.resource = Resource;
		this.skill = skill;
	}

	//返回表示这个细胞的资源的颜色
	public Color getColor()
	{
		int degree = 255 - this.resource * 2;	//资源越少越白
		if(degree > 255)
			degree = 255;
		if(degree < 0)
			degree = 0;

		return new Color(degree, 255, degree);
	}

	public Color getSkillColor(){
		int degree = 255 - this.skill * 2;	//资源越少越白
		if(degree > 255)
			degree = 255;
		if(degree < 0)
			degree = 0;

		return new Color(255, degree, degree);
	}

	//isDie表示这个细胞是否已经死亡（用于一次刷新之后判断是否要把这个细胞变成Die
	// ）资源没了就死了
	public boolean isLive()
	{
		if(this.resource > 0)
			return true;
		else
			return false;
	}


	public State getLastElement(){
		System.out.println("index : " + this.record.size());
		return this.record.get(this.record.size() - 1);
	}

}
