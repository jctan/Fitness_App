package javagui.views;

import java.io.File;
import java.sql.*;

public class Login {
	private Connection conn = null; 
	private PreparedStatement ps = null; 
	private ResultSet rs = null; 
	
	private boolean validated = false; 
	private String userRole = null; 
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/FITNESS";
	private String dbusername = "root"; 
	private String dbpassword = "";
	
	protected boolean loginValidate(String userID, String userPassword){
		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
			
			String sql1 = "SELECT * FROM LOGIN_INFORMATION WHERE user_ID = ? AND userPassword = ?";
			ps = conn.prepareStatement(sql1);
			ps.setString(1, userID);
			ps.setString(2, userPassword);
			rs = ps.executeQuery();
			
			if(rs.next()){
				validated = true;
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return validated;
	}
	
	
	protected String userRole(String userID, String userPassword){
		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			
			String sql1 = "SELECT * FROM LOGIN_INFORMATION WHERE user_ID = ? AND userPassword = ?";
			ps = conn.prepareStatement(sql1);
			ps.setString(1, userID);
			ps.setString(2, userPassword);
			rs = ps.executeQuery();
			
			if(rs.next()){
				userRole = rs.getString("userRole");
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userRole;
	}
	

}
