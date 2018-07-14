package src;
import java.sql.*;
public class Execute {
	Connection con = null;
	ResultSet rs = null;
	String username = null;
	String usergroup = null;
	public Execute(String name, String group) {
		username = name;
		usergroup = group;
	}
	public void ExecuteQuery(String sql){
		rs = null;
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs  = st.executeQuery(sql);
		}
		catch(Exception e){
			new Msgbox("查询失败！");
		}
	}
	public void ExecuteModify(String sql) {
		try {
			Statement st = con.createStatement();
			int i = st.executeUpdate(sql);
			if(i==0)
				new Msgbox("操作失败！");
		}
		catch(Exception e) {
			new Msgbox("操作失败！");
		}
	}
	public void Database(String username, String password, String group) {
		con = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=AirportMgr";
		String usr ="sa";
		String psw ="123456";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(Exception e) {
			e.printStackTrace();
			new Msgbox("加载驱动失败！");
		}
		try {  
			con = DriverManager.getConnection(url,usr,psw);
		}catch(Exception e) {
			e.printStackTrace();
			new Msgbox("连接数据库失败！");
		}
		try {
			Statement sql = con.createStatement();
			ResultSet rs  = sql.executeQuery("select usr_psw from Userlist where usr_id = '"+username+"' and usr_group = '"+group+"'");
			if(rs.next()==false) {
				new Msgbox("用户名不存在或用户组不匹配！");
				rs  = null;
				sql = null;
				con.close();
				con = null;
				return;
			}
			else
			{
				if(rs.getString("usr_psw").equals(password)) {
					return;
				}
				else {
					new Msgbox("密码错误，请重新输入！");
					rs  = null;
					sql = null;
					con.close();
					con = null;
					return;
				}
			}
		}catch(SQLException e) {
			con = null;
		}
	}
	public void Register(String username, String password, String phonenbr) {
		con = null;
		String url = "jdbc:sqlserver://localhost:1433;databaseName=AirportMgr";
		String usr ="sa";
		String psw ="123456";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(Exception e) {
			e.printStackTrace();
			new Msgbox("加载驱动失败！");
		}
		try {  
			con = DriverManager.getConnection(url,usr,psw);
		}catch(Exception e) {
			e.printStackTrace();
			new Msgbox("连接数据库失败！");
		}
		try {
			Statement sql = con.createStatement();
			ResultSet rs  = sql.executeQuery("select COUNT(*) from Userlist where usr_id = '"+username+"'");
			rs.next();
			if(rs.getString(1).equals("1"))
				new Msgbox("用户名已被注册！");
			else {
				int i = sql.executeUpdate("insert into Userlist values ('"+username+"','"+password+"','"+phonenbr+"','Individual')");
				if(i==0)
					new Msgbox("注册失败！");
				else
					new Msgbox("注册成功！");
				sql = null;
				con.close();
				con = null;
			}
			return;
		}catch(SQLException e) {
			con = null;
		}
	}
}
