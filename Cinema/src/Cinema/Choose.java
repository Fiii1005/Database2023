package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Choose extends JFrame{
	private static Choose Instance;
	public static Choose getInstance(){
		if(Instance==null){
			Instance=new Choose();
		}
		return Instance;
	}
	//组件初始化
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,40);
	JPanel pBtn=new JPanel();
	JButton amBtn=new JButton("管理影片");
	JButton arrBtn=new JButton("管理排片");
	JButton catBtn=new JButton("影片类型");
	JButton hallBtn=new JButton("查看影厅");
	
	//按钮触发器
	private ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("管理影片")){
				Movie.getInstance();
			}
			else if(command.equals("管理排片")){
				Arrange.getInstance();
			}
			else if(command.equals("影片类型")){
				Category.getInstance();
			}
			else if(command.equals("查看影厅")){
				Hall.getInstance();
			}
		}
	};
	
	Choose(){
		setTitle("选择操作");
		setLocation(600,300);
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//按钮大小设置
		amBtn.addActionListener(btnAL);
		arrBtn.addActionListener(btnAL);
		catBtn.addActionListener(btnAL);
		hallBtn.addActionListener(btnAL);
		amBtn.setFont(f);
		arrBtn.setFont(f);
		catBtn.setFont(f);
		hallBtn.setFont(f);
		amBtn.setPreferredSize(new Dimension(200,100));
		arrBtn.setPreferredSize(new Dimension(200,100));
		catBtn.setPreferredSize(new Dimension(200,100));
		hallBtn.setPreferredSize(new Dimension(200,100));
		//界面初始化
		pBtn.setLayout(new GridLayout(4,1,0,100));
		pBtn.add(amBtn);
		pBtn.add(arrBtn);
		pBtn.add(catBtn);
		pBtn.add(hallBtn);
		//界面空白填充
		pBtn.setPreferredSize(new Dimension(700, 800));
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(150,1000));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(150,1000));
		setLayout(new BorderLayout());
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(700,100));
		JPanel p4=new JPanel();
		p4.setPreferredSize(new Dimension(700,100));
		add("Center",pBtn);
		add("West",p0);
		add("East",p2);
		add("North",p3);
		add("South",p4);
		
		//设置可见
		setVisible(true);
	}
}
