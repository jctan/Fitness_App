package javagui.views;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {	
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/FITNESS";
	private String dbusername = "root"; 
	private String dbpassword = "";
	
	//get the connection
	public Connection getConnection() {
		Connection conn = null;
	    try {
	    	Class.forName(driver);
	    	conn = DriverManager.getConnection(url, dbusername, dbpassword);
	    }//end of try
	    catch (Exception e) {
	    	e.printStackTrace();	    	
	    }//end of catch
	    return conn;
	}//end of getConnection
	
	//close the connection
    public void closeConnection(Connection conn) {
	    if (conn != null) {
	    	try { 
	    		conn.close(); 
	    	}//end of try
	    	catch (SQLException e) {
	    		e.printStackTrace(); 
	    	}//end of catch
	    }//end of if
    }//end of closeConnection
 
	
	//get User 
	public DataBean getUser(String userID) {
		DataBean dataBean = new DataBean();
		Connection conn = getConnection();
		
		if (conn != null) {
			ResultSet rs = null;
	    	PreparedStatement ps = null;
			try {
				// Query semester with year, and then set it into dataBean				
				String sql2 = "SELECT * FROM LOGIN_INFORMATION WHERE user_ID = ?";
				
				ps = conn.prepareStatement(sql2);
				ps.setString(1, userID);
				rs = ps.executeQuery();
								
				while(rs.next()) {
				    	dataBean.setUserID(rs.getInt("user_ID"));
				    	dataBean.setUserPassword(rs.getString("userPassword"));
				    	dataBean.setUserRole(rs.getString("userRole"));
				}//end of while
			}//end of try
			catch(SQLException ex){
				ex.printStackTrace();}//end of catch
			finally {
				try {
	        		rs.close();
	        		ps.close();
	        	}
	          	catch (SQLException e) {e.printStackTrace();}
	          	closeConnection(conn);	        
	        } //end of finally
	    } //end of if
		return dataBean;
	} //end of getExercise
	
	
	
}
