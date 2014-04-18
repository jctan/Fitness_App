package javagui.views;

public class DataBean {
	private int userID; 
	private String Ex_Sets_ID;
	private String sets;
	private String reps; 
	private String weight;
	private String userRole;
	private String userPassword;
	private String muscleGroup;
	
	public void setUserRole(String userRole){
		this.userRole = userRole;
	}
	
	public String getUserRole(){
		return userRole;
	}
	
	public void setUserID(int userID){
		this.userID = userID;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	public String getUserPassword(){
		return userPassword;
	}
	
	public void setEx_Sets_ID(String Ex_Sets_ID){
		this.Ex_Sets_ID = Ex_Sets_ID;
	}
	
	public String getEx_Sets_ID(){
		return Ex_Sets_ID;
	}
	
	public void setSets(String sets){
		this.sets = sets; 
	}
	
	public String getSets(){
		return sets; 
	}
	
	public void setReps(String reps){
		this.reps = reps;
	}
	
	public String getReps(){
		return reps;
	}
	
	public void setWeight(String weight){
		this.weight = weight; 
	}
	
	public String getWeight(){
		return weight;
	}
	
	public void setMuscleGroup(String muscleGroup){
		this.muscleGroup = muscleGroup;
	}
	
	public String getMuscleGroup(){
		return muscleGroup;
	}

}
