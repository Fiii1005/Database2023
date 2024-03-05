package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Register extends JFrame{
	private static Register Instance;
	public static Register getInstance(){
		if(Instance==null){
			Instance=new Register();
		}
		return Instance;
	}
	//����
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,40);
	//�ı���
	JPanel p1=new JPanel();
	JLabel Accounts=new JLabel("�˺�");
	JLabel Password=new JLabel("����");
	JTextField inputAccounts=new JTextField(20);
	JPasswordField inputPassword=new JPasswordField(20);
	//ע�ᰴť
	JPanel pBtn=new JPanel();
	JButton registerBtn=new JButton("ע��");
	//Tips
	JLabel Tips=new JLabel();
	//��ť�����������ע���û���
	private ActionListener alBtn=new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String user=inputAccounts.getText();
			String pw=new String(inputPassword.getPassword());
			try {
				sqlHelper.getInstance().addUser(user, pw);
				Tips.setText("ע��ɹ�");
				Thread.sleep(1000);
				Login.getInstance();
			} catch (Exception e) {
				Tips.setText("�˺��ظ���ע��ʧ��");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	Register(){
		setTitle("�˺�ע��");
		setLocation(600,300);
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//�ı�������ʼ��
		Accounts.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Accounts.setFont(f);
		Password.setFont(f);
		inputAccounts.setFont(f);
		inputPassword.setFont(f);
		p1.setLayout(new GridLayout(7,1,0,0));
		p1.add(Accounts);
		p1.add(inputAccounts);
		p1.add(Password);
		p1.add(inputPassword);
		p1.add(new JPanel());
		pBtn.setLayout(new FlowLayout());
		registerBtn.setPreferredSize(new Dimension(200,100));
		registerBtn.addActionListener(alBtn);
		registerBtn.setFont(f);
		pBtn.add(registerBtn);
		p1.add(pBtn);
		Tips.setFont(f);
		p1.add(Tips);
		//����հ����
		p1.setPreferredSize(new Dimension(700,1000));
		JPanel p0=new JPanel();
		p0.setPreferredSize(new Dimension(150,1000));
		JPanel p2=new JPanel();
		p2.setPreferredSize(new Dimension(150,1000));
		setLayout(new BorderLayout());
		add("Center",p1);
		add("West",p0);
		add("East",p2);
		//����ɼ�
		setVisible(true);
	}
}
