package zhouzhutou.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import zhouzhutou.news.model.Admin;

public class AdminDao {
 
	public Admin findAdmin(Connection con,Admin admin) throws Exception
    {
    	Admin resultUser=null;
		String sql="select * from t_admin where name=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, admin.getName());
		pstmt.setString(2, admin.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new Admin();
			resultUser.setAdminId(rs.getInt("adminId"));
			resultUser.setName(rs.getString("name"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
    }
}
