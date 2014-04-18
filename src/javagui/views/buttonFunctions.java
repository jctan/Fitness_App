package javagui.views;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class buttonFunctions {
	
	private Connection conn = null; 
	private PreparedStatement ps = null; 
	private PreparedStatement ps1 = null;
	private ResultSet rs = null; 
 
	private String addButton = null;
	private String updateButton = null; 
	private String deleteButton = null; 
	private String selectMuscleGroup = null;
	
	private String sets = null; 
	private String reps = null ;
	private String weight= null; 
	private String muscleGroup= null;
	
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/FITNESS";
	private String dbusername = "root"; 
	private String dbpassword = "";
	
	protected  void addButton(String muscleGroup, String sets, String reps, String weight){
	
		Statement stmt1 = null;
		ResultSet countRs = null;
		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
			
			String countSql = "SELECT COUNT(*) FROM EX_SETS";
			stmt1 = conn.createStatement();
			countRs = stmt1.executeQuery(countSql);
			
			String sql = "INSERT INTO EX_SETS (EXERCISE, SETS, REPS, WEIGHT) VALUES (?,?,?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, muscleGroup);
			ps.setString(2, sets);
			ps.setString(3, reps);
			ps.setString(4, weight);
			ps.executeUpdate();
			
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	protected void updateButton(String ex_sets_id, String muscleGroup, String sets, String reps, String weight){

		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			
			String sql = "UPDATE EX_SETS SET EXERCISE = ?, SETS = ?, REPS = ?, WEIGHT = ? WHERE EX_SETS_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, muscleGroup);
			ps.setString(2, sets);
			ps.setString(3, reps);
			ps.setString(4, weight);
			ps.setString(5, ex_sets_id);
			ps.executeUpdate();
			
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void deleteButton(String numOfData){
		
		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			
			String sql = "DELETE FROM EX_SETS WHERE EX_SETS_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, numOfData);
			ps.executeUpdate();
			
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

	
	protected ArrayList<DataBean> show(){
		ArrayList<DataBean> result = new ArrayList<DataBean>();
		Statement stmt = null;
		ResultSet localRs = null;

		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,dbusername,dbpassword);
		
		stmt = conn.createStatement();
		String sql1 = "SELECT * FROM EX_SETS";
		localRs = stmt.executeQuery(sql1);
		
		while(localRs.next()){
			DataBean rsList = new DataBean();
			rsList.setEx_Sets_ID(localRs.getString("Ex_Sets_ID"));
			rsList.setMuscleGroup(localRs.getString("Exercise"));
			rsList.setSets(localRs.getString("Sets"));
			rsList.setReps(localRs.getString("Reps"));
			rsList.setWeight(localRs.getString("Weight"));
			result.add(rsList);
		}
		conn.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return result;
	}
	

}
