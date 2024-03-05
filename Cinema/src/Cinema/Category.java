package Cinema;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

public class Category extends JFrame{
	private static Category Instance;
	public static Category getInstance(){
		if(Instance==null){
			Instance=new Category();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//����
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//��ʾ��Ӱ��Ϣģ��
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JLabel lCid=new JLabel("���ͱ��");
	JLabel lCname=new JLabel("��������");
	
	//��ȡ�б���
	void getCat(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(CatInfor.catList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	public static void myupdate(){
		try {
			Instance.getCat(sqlHelper.getInstance().getCategory());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Category(){
		setTitle("��Ӱ����");
		setLocation(200,200);
		setSize(700,700);
		//չʾ��Ϣ����ʼ��
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,7,65,0));
		lCid.setFont(f);
		lCname.setFont(f);
		lCid.setHorizontalAlignment(JLabel.CENTER);
		lCname.setHorizontalAlignment(JLabel.CENTER);
		pInforInit.add(lCid);
		pInforInit.add(lCname);
		
		try {
			getCat(sqlHelper.getInstance().getCategory());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����ʼ��
		add("Center",pInfor);
		setVisible(true);
	}
}
