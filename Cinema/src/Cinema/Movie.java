package Cinema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.*;


public class Movie extends JFrame{
	private static Movie Instance;
	public static Movie getInstance(){
		if(Instance==null){
			Instance=new Movie();
		}
		Instance.setVisible(true);
		return Instance;
	}
	//����
	Font f=new Font("Microsoft Yahei UI",Font.BOLD,20);
	//����ģ��
	JPanel pSearch=new JPanel();
	JLabel lSearch=new JLabel("��Ӱ��");
	JTextField searchTF=new JTextField(20);
	JButton searchBtn=new JButton("����");
	JButton addBtn=new JButton("��ӻ��޸�");
	JButton delBtn=new JButton("ɾ��");
	JCheckBox allSel=new JCheckBox();
	
	//��ʾ��Ӱ��Ϣģ��
	JPanel pInfor=new JPanel();
	JPanel pInforInit=new JPanel();
	JLabel lMovid=new JLabel("��Ӱ���");
	JLabel lMovname=new JLabel("��Ӱ����");
	JLabel lMovcid=new JLabel("���ͱ��");
	JLabel lMovcname=new JLabel("ӰƬ����");
	JLabel lMovdate=new JLabel("��ӳ����");
	JLabel lMovtime=new JLabel("ӰƬʱ��");

	
	//��ȡ��Ӱ�б���
	void getMovie(int num) throws Exception{
		pInfor.removeAll();
		pInfor.setLayout(new BoxLayout(pInfor, BoxLayout.Y_AXIS));
		pInfor.add(pInforInit);
		System.out.println(num);
		for(int i=0;i<num;i++){
			pInfor.add(MovieInfor.movList[i]);
		}
		JPanel pNull=new JPanel();
		pNull.setPreferredSize(new Dimension(900,800-num*100));
		pInfor.add(pNull);
		pInfor.updateUI();
	}
	//������ť��������
	ActionListener btnAL=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("����")){
				String key=searchTF.getText();
				try {
					getMovie(sqlHelper.getInstance().searchMovie(key));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(command.equals("��ӻ��޸�")){
				addMovie.getInstance();
			}
			else if(command.equals("ɾ��")){
				try {
					sqlHelper.getInstance().delMovie();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					getMovie(sqlHelper.getInstance().getMovie());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Arrange.getInstance().getArrange(sqlHelper.getInstance().getArrange());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};
	//ȫѡ��ť������
	ItemListener btnIL=new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox command=(JCheckBox)e.getItem();
			if(command.isSelected()){
				for(int i=0;i<MovieInfor.inforNum;i++){
					MovieInfor.movList[i].isSel.setSelected(true);
				}
			}
			else{
				for(int i=0;i<MovieInfor.inforNum;i++){
					MovieInfor.movList[i].isSel.setSelected(false);
				}
			}
		}
	};
	
	Movie(){
		setTitle("����ӰƬ");
		setLocation(500,300);
		setSize(1200,1000);
		//����ģ���ʼ��
		pInfor.setPreferredSize(new Dimension(1200,130));
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
		//չʾ��Ϣ����ʼ��
		pInfor.setPreferredSize(new Dimension(1200,870));
		pInfor.setBackground(Color.white);
		pInforInit.setPreferredSize(new Dimension(1200,50));
		pInforInit.setLayout(new GridLayout(1,7,10,0));
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
		pInforInit.add(allSel);
		pInforInit.add(lMovid);
		pInforInit.add(lMovname);
		pInforInit.add(lMovcid);
		pInforInit.add(lMovcname);
		pInforInit.add(lMovdate);
		pInforInit.add(lMovtime);
		
		
		try {
			getMovie(sqlHelper.getInstance().getMovie());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�����ʼ��
		add("North",pSearch);
		add("Center",pInfor);
		
		//���ÿɼ�
		setVisible(true);
	}
}
