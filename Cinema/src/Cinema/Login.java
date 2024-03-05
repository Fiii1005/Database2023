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
	JLabel Accounts=new JLabel("�˺�");
	JLabel Password=new JLabel("����");
	JTextField inputAccounts=new JTextField(20);
	JPasswordField inputPassword=new JPasswordField(20);
	JButton registerBtn=new JButton("ע��");
	JButton loginBtn=new JButton("��¼");
	JLabel Tips=new JLabel();
	JPanel p1=new JPanel();
	//��ť������
	private ActionListener alBtn=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			//ע�����
			if(command.equals("ע��")){
				Register.getInstance();
			}
			//��½����
			else if(command.equals("��¼")){
				String getAcc=inputAccounts.getText();
				String getPw=new String(inputPassword.getPassword());
				try {
					if(sqlHelper.getInstance().Login(getAcc, getPw)){
						Tips.setText("��¼�ɹ�");
						Choose.getInstance();
						dispose();
					}
					else{
						Tips.setText("�˺Ż��������");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	Login(){
		setTitle("��½����");
		setLocation(600,300);
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//�ı�������ʼ��
		JPanel pnull=new JPanel();
		JPanel pnull0=new JPanel();
		p1.setLayout(new GridLayout(7,1,0,0));
		JPanel p11=new JPanel();
		p11.setLayout(new GridLayout(1,3,0,0));
		//��ť�����ʼ��
		registerBtn.setFont(f);
		loginBtn.setFont(f);
		registerBtn.addActionListener(alBtn);
		loginBtn.addActionListener(alBtn);
		p11.add(registerBtn);
		p11.add(pnull0);
		p11.add(loginBtn);
		//�����ʼ��
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
		//�հ׽������
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
		//���ÿɼ�
		setVisible(true);
	}
}
