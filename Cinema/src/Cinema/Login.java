package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login extends JFrame{
	private static Login Instance;
	public static Login getInstance(){
		if(Instance==null){
			Instance=new Login();
		}
		Instance.setVisible(true);
		return Instance;
	}
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,40);
	JLabel Accounts=new JLabel("账号");
	JLabel Password=new JLabel("密码");
	JTextField inputAccounts=new JTextField(20);
	JPasswordField inputPassword=new JPasswordField(20);
	JButton registerBtn=new JButton("注册");
	JButton loginBtn=new JButton("登录");
	JLabel Tips=new JLabel();
	JPanel p1=new JPanel();
	//按钮监听器
	private ActionListener alBtn=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			//注册界面
			if(command.equals("注册")){
				Register.getInstance();
			}
			//登陆界面
			else if(command.equals("登录")){
				String getAcc=inputAccounts.getText();
				String getPw=new String(inputPassword.getPassword());
				try {
					if(sqlHelper.getInstance().Login(getAcc, getPw)){
						Tips.setText("登录成功");
						Choose.getInstance();
						dispose();
					}
					else{
						Tips.setText("账号或密码错误");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	Login(){
		setTitle("登陆界面");
		setLocation(600,300);
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//文本框界面初始化
		JPanel pnull=new JPanel();
		JPanel pnull0=new JPanel();
		p1.setLayout(new GridLayout(7,1,0,0));
		JPanel p11=new JPanel();
		p11.setLayout(new GridLayout(1,3,0,0));
		//按钮界面初始化
		registerBtn.setFont(f);
		loginBtn.setFont(f);
		registerBtn.addActionListener(alBtn);
		loginBtn.addActionListener(alBtn);
		p11.add(registerBtn);
		p11.add(pnull0);
		p11.add(loginBtn);
		//界面初始化
		Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Accounts.setFont(f);
		Password.setFont(f);
		inputAccounts.setFont(f);
		inputPassword.setFont(f);
		p1.add(Accounts);
		p1.add(inputAccounts);
		p1.add(Password);
		p1.add(inputPassword);
		p1.add(pnull);
		p1.add(p11);
		Tips.setFont(f);
		p1.add(Tips);
		//空白界面填充
		p1.setPreferredSize(new Dimension(700, 600));
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(150,1000));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(150,1000));
		setLayout(new BorderLayout());
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(700,200));
		JPanel p4=new JPanel();
		p4.setPreferredSize(new Dimension(700,200));
		add("Center",p1);
		add("West",p0);
		add("East",p2);
		add("North",p3);
		add("South",p4);
		//设置可见
		setVisible(true);
	}
}
