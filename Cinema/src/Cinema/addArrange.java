package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class addArrange extends JFrame{
	private static addArrange Instance;
	public static addArrange getInstance(){
		if(Instance==null){
			Instance=new addArrange();
		}
		Instance.setVisible(true);
		return Instance;
	}
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,30);
	JLabel lMovid=new JLabel("��Ӱ���");
	JLabel lHallid=new JLabel("Ӱ�����");
	JLabel lStime=new JLabel("��ʼʱ��");
	JTextField movidTF=new JTextField(20);
	JTextField hallidTF=new JTextField(20);
	JTextField stimeTF=new JTextField(20);
	JButton yesBtn=new JButton("ȷ��");
	JPanel pMain=new JPanel();
	JPanel pMovid=new JPanel();
	JPanel pHallid=new JPanel();
	JPanel pStime=new JPanel();
	JPanel pBtn=new JPanel();
	JPanel pTips=new JPanel();
	JLabel lTips=new JLabel();
	//��ť������
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String movid=movidTF.getText();
			String hallid=hallidTF.getText();
			String stime=stimeTF.getText();
			System.out.println(stime);
			sqlHelper.getInstance().addArranges(movid, hallid, stime);
			try {
				Arrange.getInstance().getArrange(sqlHelper.getInstance().getArrange());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	
	addArrange(){
		setTitle("�����Ƭ");
		setLocation(1400,300);
		setSize(1000,1000);
		//��������
		lMovid.setFont(f);
		lHallid.setFont(f);
		lStime.setFont(f);
		movidTF.setFont(f);
		hallidTF.setFont(f);
		stimeTF.setFont(f);
		yesBtn.setFont(f);
		//�����ʼ��
		pMovid.setLayout(new FlowLayout());
		pHallid.setLayout(new FlowLayout());
		pStime.setLayout(new FlowLayout());
		pBtn.setLayout(new FlowLayout());
		pMovid.add(lMovid);
		pHallid.add(lHallid);
		pStime.add(lStime);
		pMovid.add(movidTF);
		pHallid.add(hallidTF);
		pStime.add(stimeTF);
		pBtn.add(yesBtn);
		yesBtn.setPreferredSize(new Dimension(120,80));
		yesBtn.addActionListener(btnAL);
		
		pTips.setPreferredSize(new Dimension(1000,50));
		lTips.setFont(f);
		pTips.add(lTips);
		//�������ʼ��
		pMain.setLayout(new GridLayout(5,1,0,30));
		pMain.setPreferredSize(new Dimension(1000,900));
		JPanel p3=new JPanel();
		p3.setPreferredSize(new Dimension(1000,50));
		pMain.add(pMovid);
		pMain.add(pHallid);
		pMain.add(pStime);
		pMain.add(pBtn);
		add("Center",pMain);
		add("North",p3);
		add("South",pTips);
		//���ÿɼ�
		setVisible(true);
	}
}
