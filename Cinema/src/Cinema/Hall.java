package Cinema;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

public class Hall extends JFrame{
	private static Hall Instance;
	public static Hall getInstance(){
		if(Instance==null){
			Instance=new Hall();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//����
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//��ʾ��Ӱ��Ϣģ��
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JLabel lHallid=new JLabel("Ӱ����");
	JLabel lHsize=new JLabel("Ӱ������");
	JLabel lHsty=new JLabel("Ӱ����ɫ");
	
	//��ȡ�б���
	void getHall(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(HallInfor.hallList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	public static void myupdate(){
		try {
			Instance.getHall(sqlHelper.getInstance().getHall());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Hall(){
		setTitle("Ӱ����Ϣ");
		setLocation(200,200);
		setSize(700,700);
		//չʾ��Ϣ����ʼ��
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,7,65,0));
		lHallid.setFont(f);
		lHsize.setFont(f);
		lHsty.setFont(f);
		lHallid.setHorizontalAlignment(JLabel.CENTER);
		lHsize.setHorizontalAlignment(JLabel.CENTER);
		lHsty.setHorizontalAlignment(JLabel.CENTER);
		pInforInit.add(lHallid);
		pInforInit.add(lHsize);
		pInforInit.add(lHsty);
		
		try {
			getHall(sqlHelper.getInstance().getHall());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����ʼ��
		add("Center",pInfor);
		setVisible(true);
	}
}
