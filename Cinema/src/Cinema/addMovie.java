package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class addMovie extends JFrame{
	private static addMovie Instance;
	public static addMovie getInstance(){
		if(Instance==null){
			Instance=new addMovie();
		}
		Instance.setVisible(true);
		return Instance;
	}
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,30);
	JLabel lMovid=new JLabel("��Ӱ���");
	JLabel lMovname=new JLabel("��Ӱ����");
	JLabel lMovcid=new JLabel("���ͱ��");
	JLabel lMovc=new JLabel("ӰƬ����");
	JLabel lMovdate=new JLabel("��ӳ����");
	JLabel lMovtime=new JLabel("ӰƬʱ��");
	JTextField movidTF=new JTextField(20);
	JTextField movnameTF=new JTextField(20);
	JTextField movcTF=new JTextField(20);
	JTextField movcidTF=new JTextField(20);
	JTextField movdateTF=new JTextField(20);
	JTextField movtimeTF=new JTextField(20);
	JButton yesBtn=new JButton("ȷ��");
	JPanel pMain=new JPanel();
	JPanel pMovid=new JPanel();
	JPanel pMovname=new JPanel();
	JPanel pMovc=new JPanel();
	JPanel pMovcid=new JPanel();
	JPanel pMovdate=new JPanel();
	JPanel pMovtime=new JPanel();
	JPanel pBtn=new JPanel();
	JPanel pTips=new JPanel();
	JLabel lTips=new JLabel("ע�⣺�޸�ʱ�Ѵ��ڵĵ�Ӱ������Ӱ���ơ����ͱ��������������һ��");
	//��ť������
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String movid=movidTF.getText();
			String movname=movnameTF.getText();
			String movc=movcTF.getText();
			String movcid=movcidTF.getText();
			String movdate=movdateTF.getText();
			String movtime=movtimeTF.getText();
			try {
				int rtn=sqlHelper.getInstance().addMovies(movid, movname, movc,movcid, movdate, movtime);
				System.out.println("rtn"+rtn);
				if(rtn==0){
					lTips.setText("�������ͱ����ӰƬ���ͳ�ͻ");
				}
				else if(rtn==1){
					lTips.setText("ӰƬ���³ɹ�");
				}
				else if(rtn==2){
					lTips.setText("���󣺵�Ӱ������Ӱ���Ʋ���");
				}
				else if(rtn==3){
					lTips.setText("�����ӰƬ�ɹ�");
				}
				Movie.getInstance().getMovie(sqlHelper.getInstance().getMovie());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			Category.getInstance().myupdate();
		}
	};
	
	addMovie(){
		setTitle("���ӰƬ");
		setLocation(1400,300);
		setSize(1000,1000);
		//��������
		lMovid.setFont(f);
		lMovname.setFont(f);
		lMovc.setFont(f);
		lMovcid.setFont(f);
		lMovdate.setFont(f);
		lMovtime.setFont(f);
		movidTF.setFont(f);
		movnameTF.setFont(f);
		movcTF.setFont(f);
		movcidTF.setFont(f);
		movdateTF.setFont(f);
		movtimeTF.setFont(f);
		yesBtn.setFont(f);
		//�����ʼ��
		pMovid.setLayout(new FlowLayout());
		pMovname.setLayout(new FlowLayout());
		pMovcid.setLayout(new FlowLayout());
		pMovc.setLayout(new FlowLayout());
		pMovdate.setLayout(new FlowLayout());
		pMovtime.setLayout(new FlowLayout());
		pBtn.setLayout(new FlowLayout());
		pMovid.add(lMovid);
		pMovname.add(lMovname);
		pMovcid.add(lMovcid);
		pMovc.add(lMovc);
		pMovdate.add(lMovdate);
		pMovtime.add(lMovtime);
		pMovid.add(movidTF);
		pMovname.add(movnameTF);
		pMovcid.add(movcidTF);
		pMovc.add(movcTF);
		pMovdate.add(movdateTF);
		pMovtime.add(movtimeTF);
		pBtn.add(yesBtn);
		yesBtn.setPreferredSize(new Dimension(120,80));
		yesBtn.addActionListener(btnAL);
		
		pTips.setPreferredSize(new Dimension(1000,60));
		lTips.setFont(f);
		pTips.add(lTips);
		//�������ʼ��
		pMain.setLayout(new GridLayout(7,1,0,30));
		pMain.setPreferredSize(new Dimension(1000,850));
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(1000,50));
		pMain.add(pMovid);
		pMain.add(pMovname);
		pMain.add(pMovcid);
		pMain.add(pMovc);
		pMain.add(pMovdate);
		pMain.add(pMovtime);
		pMain.add(pBtn);
		add("Center",pMain);
		add("North",p3);
		add("South",pTips);
		//���ÿɼ�
		setVisible(true);
	}
}
