package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.*;

public class Arrange extends JFrame{
	private static Arrange Instance;
	public static Arrange getInstance(){
		if(Instance==null){
			Instance=new Arrange();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//字体
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//搜索模块
	JPanel pSearch=new JPanel();
	JLabel lSearch=new JLabel("电影名称");
	JTextField searchTF=new JTextField(20);
	JButton searchBtn=new JButton("搜索");
	JButton addBtn=new JButton("添加");
	JButton delBtn=new JButton("删除");
	//显示电影信息模块
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JCheckBox allSel=new JCheckBox();
	JLabel lMovid=new JLabel("电影编号");
	JLabel lMovname=new JLabel("电影名称");
	JLabel lCname=new JLabel("影片类型");
	JLabel lHall=new JLabel("影厅号");
	JLabel lStime=new JLabel("开始时间");
	//获取排片列表函数
	void getArrange(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(ArrangeInfor.arrangeList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	//搜索按钮监听函数
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("搜索")){
				String key=searchTF.getText();
				try {
					getArrange(sqlHelper.getInstance().searchArrange(key));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(command.equals("添加")){
				addArrange.getInstance();
			}
			else if(command.equals("删除")){
				try {
					sqlHelper.getInstance().delArrange();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					getArrange(sqlHelper.getInstance().getArrange());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	//全选按钮监听器
	ItemListener btnIL=new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox command=(JCheckBox)e.getItem();
			if(command.isSelected()){
				for(int i=0;i<ArrangeInfor.inforNum;i++){
					ArrangeInfor.arrangeList[i].isSel.setSelected(true);
				}
			}
			else{
				for(int i=0;i<ArrangeInfor.inforNum;i++){
					ArrangeInfor.arrangeList[i].isSel.setSelected(false);
				}
			}
		}
	};
	Arrange(){
		setTitle("管理排片");
		setLocation(400,300);
		setSize(1800,1000);
		//搜索模块初始化
		pInfor.setPreferredSize(new Dimension(1000,130));
		lSearch.setFont(f);
		searchTF.setFont(f);
		searchBtn.setFont(f);
		addBtn.setFont(f);
		delBtn.setFont(f);
		pSearch.setLayout(new FlowLayout());
		pSearch.add(delBtn);
		pSearch.add(lSearch);
		pSearch.add(searchTF);
		pSearch.add(searchBtn);
		pSearch.add(addBtn);
		searchBtn.addActionListener(btnAL);
		addBtn.addActionListener(btnAL);
		delBtn.addActionListener(btnAL);
		allSel.addItemListener(btnIL);
		//展示信息面板初始化
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,6,10,0));
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
		pInforInit.add(allSel);
		pInforInit.add(lMovid);
		pInforInit.add(lMovname);
		pInforInit.add(lCname);
		pInforInit.add(lHall);
		pInforInit.add(lStime);
		
		try {
			getArrange(sqlHelper.getInstance().getArrange());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//界面初始化
		add("North",pSearch);
		add("South",pInfor);
		
		//设置可见
		setVisible(true);
	}
}
