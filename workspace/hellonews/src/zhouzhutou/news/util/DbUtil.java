package zhouzhutou.news.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	public Connection getCon()throws Exception{
		Class.forName(PropertiesUtil.getProperty("jdbcName"));
		//System.out.println(PropertiesUtil.getProperty("jdbcName"));
		Connection con=DriverManager.getConnection(PropertiesUtil.getProperty("dbUrl"), PropertiesUtil.getProperty("dbUserName"), PropertiesUtil.getProperty("dbPassword"));
		return con;
	}
	
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}
