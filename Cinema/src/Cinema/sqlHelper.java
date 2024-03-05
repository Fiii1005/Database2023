package Cinema;

import java.sql.*;

public class sqlHelper{
	private static sqlHelper Instance;
	public static sqlHelper getInstance(){
		if(Instance==null){
			Instance=new sqlHelper();
		}
		return Instance;
	}
	//连接
	Connection conn;
	//添加用户函数
	void addUser(String user,String pw) throws Exception{
		String sql="insert into users values(?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user);
		pstmt.setString(2, pw);
		pstmt.executeUpdate();
		pstmt.close();
	}
	//登录判定
	boolean Login(String user,String pw) throws Exception{
		String sql="select * from users where account=? AND pw=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user);
		pstmt.setString(2, pw);
		ResultSet rst=pstmt.executeQuery();
		if(rst.first()){
			pstmt.close();
			return true;
		}
		pstmt.close();
		return false;
	}
	//获得影片列表函数
	int getMovie() throws Exception{
		String sql="select * from movc";
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery(sql);
		int num=0;
		while(rst.next()){
			String movid=rst.getString(1);
			String movname=rst.getString(2);
			String movcid=rst.getString(3);
			String movcname=rst.getString(4);
			String movdate=rst.getString(5);
			String movtime=rst.getString(6);
			MovieInfor.setMovie(num++, movid, movname, movcid,movcname, movdate, movtime);
		}
		stmt.close();
		MovieInfor.inforNum=num;
		return num;
	}
	//获得排片列表函数
	int getArrange() throws Exception{
		String sql="select * from arr";
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery(sql);
		int num=0;
		while(rst.next()){
			String movname=rst.getString(1);
			String cname=rst.getString(2);
			String hallid=rst.getString(3);
			String stime=rst.getString(4);
			String movid=rst.getString(5);
			ArrangeInfor.setArrange(num++, movname, cname, hallid, stime, movid);
		}
		stmt.close();
		ArrangeInfor.inforNum=num;
		return num;
	}
	//搜索影片函数
	int searchMovie(String key) throws Exception{
		if(key.equals("")){
			return getMovie();
		}
		else{
			String sql="select * from movc where movname like '%"+key+"%'";
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery(sql);
			System.out.println(sql);
			int num=0;
			while(rst.next()){
				String movid=rst.getString(1);
				String movname=rst.getString(2);
				String movcid=rst.getString(3);
				String movcname=rst.getString(4);
				String movdate=rst.getString(5);
				String movtime=rst.getString(6);
				MovieInfor.setMovie(num++, movid, movname, movcid,movcname, movdate, movtime);
			}
			stmt.close();
			MovieInfor.inforNum=num;
			return num;
		}
	}
	//搜索排片函数
	int searchArrange(String key) throws Exception{
		if(key.equals("")){
			return getArrange();
		}
		else{
			String sql="select * from arr where movname like '%"+key+"%'";
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery(sql);
			System.out.println(sql);
			int num=0;
			while(rst.next()){
				String movname=rst.getString(1);
				String cname=rst.getString(2);
				String hallid=rst.getString(3);
				String stime=rst.getString(4);
				String movid=rst.getString(5);
				ArrangeInfor.setArrange(num++, movname, cname, hallid, stime,movid);
			}
			stmt.close();
			ArrangeInfor.inforNum=num;
			return num;
		}
	}
	//删除影片函数
	boolean delMovie() throws SQLException{
		conn.setAutoCommit(false);
		boolean flag=false;
		String sql1="delete from arrange where movid=?";
		String sql2="delete from movie where movid=?";
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2=null;
		try {
			pstmt1=conn.prepareStatement(sql1);
			pstmt2=conn.prepareStatement(sql2);
			for(int i=0;i<MovieInfor.inforNum;i++){
				if(MovieInfor.movList[i].isSel.isSelected()){
					pstmt1.setString(1, MovieInfor.movList[i].lMovid.getText());
					pstmt2.setString(1, MovieInfor.movList[i].lMovid.getText());
					System.out.println(MovieInfor.movList[i].lMovid.getText());
					pstmt1.addBatch();
					pstmt2.addBatch();
				}
			}
			pstmt1.executeBatch();//批量执行
			pstmt2.executeBatch();//批量执行
			conn.commit();//提交事务
			flag=true;
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conn.rollback();//失败就进行回滚操作
				System.out.println("失败");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			pstmt1.close();
			pstmt2.close();
		}
		conn.setAutoCommit(true);
		return flag;
	}
	//删除排片函数
	boolean delArrange() throws SQLException{
		int num=0;
		conn.setAutoCommit(false);
		boolean flag=false;
		String sql="delete from arrange where movid=? and hallid=? and stime=?";
		PreparedStatement pstmt = null;
		try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<ArrangeInfor.inforNum;i++){
				if(ArrangeInfor.arrangeList[i].isSel.isSelected()){
					pstmt.setString(1, ArrangeInfor.arrangeList[i].movid);
					pstmt.setString(2, ArrangeInfor.arrangeList[i].lHall.getText());
					pstmt.setString(3, ArrangeInfor.arrangeList[i].lStime.getText());
					pstmt.addBatch();
					num++;
				}
			}
			pstmt.executeBatch();//批量执行
			conn.commit();//提交事务
			System.out.println(num);
			flag=true;
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conn.rollback();//失败就进行回滚操作
				System.out.println("失败");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			pstmt.close();
		}
		conn.setAutoCommit(true);
		return flag;
	}
	//添加或修改影片函数
	int addMovies(String movid,String movname,String movc,String movcid,String movdate,String movtime){
		System.out.println(movid);
		System.out.println(movname);
		String sql0="set @movid='"+movid+"'";
		String sql1="set @movname='"+movname+"'";
		String sql2="set @cid='"+movcid+"'";
		String sql3="set @movdate='"+movdate+"'";
		String sql4="set @movtime='"+movtime+"'";
		String sql5="set @cname='"+movc+"'";
		String sql6="call addmov(@movid,@movname,@cid,@movdate,@movtime,@cname,@rtn)";
		String sql7="select @rtn";
		Statement stmt;
		int rtn=0;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sql0);
			stmt.executeQuery(sql1);
			stmt.executeQuery(sql2);
			stmt.executeQuery(sql3);
			stmt.executeQuery(sql4);
			stmt.executeQuery(sql5);
			stmt.executeQuery(sql6);
			ResultSet rst=stmt.executeQuery(sql7);
			if(rst.next()){
				rtn=rst.getInt(1);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtn;
	}
	//添加排片函数
	boolean addArranges(String movid,String hallid,String stime){
		String sql="insert into arrange values(?,?,?)";
		PreparedStatement pstmt=null;
		int row=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movid);
			pstmt.setString(2, hallid);
			pstmt.setString(3, stime);
			row=pstmt.executeUpdate();
			addArrange.getInstance().lTips.setText("添加排片成功");
			
		} catch (SQLException e) {
			addArrange.getInstance().lTips.setText("错误：不存在该影片或影厅排片冲突");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(row==1){
			return true;
		}
		return false;
	}
	//获取category函数
	int getCategory() throws SQLException{
		String sql="select * from category";
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery(sql);
		int num=0;
		while(rst.next()){
			String cid=rst.getString(1);
			String cname=rst.getString(2);
			CatInfor.setCat(num++, cid, cname);
		}
		stmt.close();
		CatInfor.inforNum=num;
		return num;
	}
	//获取hall函数
		int getHall() throws SQLException{
			String sql="select hall.hallid,hallsize,vhstyle from hall left join viphall on hall.hallid=viphall.hallid order by hall.hallid";
			Statement stmt=conn.createStatement();
			ResultSet rst=stmt.executeQuery(sql);
			int num=0;
			while(rst.next()){
				String hallid=rst.getString(1);
				String hallsize=rst.getString(2);
				String hsty=rst.getString(3);
				HallInfor.setHall(num++, hallid, hallsize,hsty);
			}
			stmt.close();
			HallInfor.inforNum=num;
			return num;
		}
	
	sqlHelper(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("加载驱动失败");
		}
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema?useUnicode=true&characterEncoding=UTF-8","root","");
			System.out.println("连接数据库成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}
	}
}
