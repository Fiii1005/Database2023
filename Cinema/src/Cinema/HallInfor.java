package Cinema;

import java.awt.*;

import javax.swing.*;

public class HallInfor extends JPanel{
	public static int inforNum;
	public static HallInfor[] hallList=new HallInfor[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	JLabel lHallid;
	JLabel lHsize;
	JLabel lHsty;
	public static void setHall(int index,String hallid,String hallsize,String hsty){
		if(hsty==null){
			hsty="无";
		}
		hallList[index]=new HallInfor(hallid,hallsize,hsty);
	}
	HallInfor(String hallid,String hallsize,String hsty){
		lHallid=new JLabel(hallid);
		lHsize=new JLabel(hallsize);
		lHsty=new JLabel(hsty);
		lHallid.setFont(f);
		lHsize.setFont(f);
		lHsty.setFont(f);
		lHallid.setHorizontalAlignment(JLabel.CENTER);
		lHsize.setHorizontalAlignment(JLabel.CENTER);
		lHsty.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(900,50));
		setLayout(new GridLayout(1,7,20,0));
		add(lHallid);
		add(lHsize);
		add(lHsty);
	}
}
