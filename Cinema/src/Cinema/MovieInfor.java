package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class MovieInfor extends JPanel{
	public static int inforNum;
	public static MovieInfor[] movList=new MovieInfor[15];//先假定最多15条
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	JCheckBox isSel=new JCheckBox();
	JLabel lMovid;
	JLabel lMovname;
	JLabel lMovcid;
	JLabel lMovcname;
	JLabel lMovdate;
	JLabel lMovtime;
	//电影列表初始化
	public static void setMovie(int index,String movid,String movname,String movcid,String movcname,String movdate,String movtime){
		movList[index]=new MovieInfor(movid, movname, movcid,movcname, movdate, movtime);
	}
	
	MovieInfor(String movid,String movname,String movcid,String movcname, String movdate,String movtime){
		lMovid=new JLabel(movid);
		lMovname=new JLabel(movname);
		lMovcid=new JLabel(movcid);
		lMovcname=new JLabel(movcname);
		lMovdate=new JLabel(movdate);
		lMovtime=new JLabel(movtime+"分钟");
		lMovid.setFont(f);
		lMovname.setFont(f);
		lMovcid.setFont(f);
		lMovcname.setFont(f);
		lMovdate.setFont(f);
		lMovtime.setFont(f);
		lMovid.setHorizontalAlignment(JLabel.CENTER);
		lMovname.setHorizontalAlignment(JLabel.CENTER);
		lMovcid.setHorizontalAlignment(JLabel.CENTER);
		lMovcname.setHorizontalAlignment(JLabel.CENTER);
		lMovdate.setHorizontalAlignment(JLabel.CENTER);
		lMovtime.setHorizontalAlignment(JLabel.CENTER);
		//界面初始化
		setPreferredSize(new Dimension(900,50));
		setLayout(new GridLayout(1,7,10,0));
		add(isSel);
		add(lMovid);
		add(lMovname);
		add(lMovcid);
		add(lMovcname);
		add(lMovdate);
		add(lMovtime);
	}
}
