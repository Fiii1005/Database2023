package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ArrangeInfor extends JPanel{
	public static int inforNum;
	public static ArrangeInfor[] arrangeList=new ArrangeInfor[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	public JCheckBox isSel=new JCheckBox();
	String movid=new String();
	JLabel lMovid;
	JLabel lMovname;
	JLabel lCname;
	JLabel lHall;
	JLabel lStime;
	JPanel pCon=new JPanel();
	//排片列表初始化
	public static void setArrange(int index,String movname,String cname,String hallid,String stime,String movID){
		arrangeList[index]=new ArrangeInfor(movname, cname, hallid, stime);
		arrangeList[index].movid=movID;
		arrangeList[index].lMovid.setText(movID);
	}
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("删除")){
				
			}
		}
	};
	ArrangeInfor(String movname,String cname,String hallid,String stime){
		lMovid=new JLabel(movid);
		lMovname=new JLabel(movname);
		lCname=new JLabel(cname);
		lHall=new JLabel(hallid);
		lStime=new JLabel(stime);
		lMovid.setFont(f);
		lMovname.setFont(f);
		lCname.setFont(f);
		lHall.setFont(f);
		lStime.setFont(f);
		lMovid.setHorizontalAlignment(JLabel.CENTER);
		lMovname.setHorizontalAlignment(JLabel.CENTER);
		lCname.setHorizontalAlignment(JLabel.CENTER);
		lHall.setHorizontalAlignment(JLabel.CENTER);
		lStime.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(1200,50));
		setLayout(new GridLayout(1,6,10,0));
		add(isSel);
		add(lMovid);
		add(lMovname);
		add(lCname);
		add(lHall);
		add(lStime);
	}
}
