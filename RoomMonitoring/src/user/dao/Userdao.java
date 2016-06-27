package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import user.model.User;

public class Userdao {
	/*
	 * 用户登录验证
	 * @param conn
	 * @param user
	 * @return
	 * @throw  exception
	 * @author WZH
	 *
	 */
	public User login(Connection conn,User user) throws Exception{
		
		User resultUser=null;
		ResultSet rs = null;
		
		String sql="select * from users where name=? and password=? " ;
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user.getUser_name());
		pstmt.setString(2, user.getPassword());
	    rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUser_name(rs.getString("name"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
		
		
		
	}

}