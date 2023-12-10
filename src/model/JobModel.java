package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class JobModel {
	Connect con = Connect.getInstance();
	
	public ArrayList<Job> getAllTechJobData() {
		String query = "SELECT * FROM Job";
		
		ArrayList<Job> allTechJobList = new ArrayList<>();

		ResultSet rs = con.selectData(query);

		try {
			while (rs.next()) {
				Integer jid = rs.getInt("Job_ID");
				Integer uid = rs.getInt("UserID");
				Integer pid = rs.getInt("PC_ID");
				String jstatus = rs.getString("JobStatus");

				allTechJobList.add(new Job(jid, uid, pid, jstatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allTechJobList;
	}
	
	public ArrayList<Job> getTechJobData(Integer userid) {
		String query = "SELECT * FROM job WHERE UserID = ?";
		
		ArrayList<Job> techJobList = new ArrayList<>();
		
		PreparedStatement ps = con.prepareStatement(query);

		try {
			ps.setInt(1, userid);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Integer jid = rs.getInt("Job_ID");
					Integer uid = rs.getInt("UserID");
					Integer pid = rs.getInt("PC_ID");
					String jstatus = rs.getString("JobStatus");

					techJobList.add(new Job(jid, uid, pid, jstatus));
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return techJobList;
	}
	
	public void addStaffJob(String userID, String pcID) {
		String insertJobQuery = "INSERT INTO job(Job_ID, UserID, PC_ID, JobStatus) VALUES (NULL, ?, ?, 'UnComplete')";
		
	    PreparedStatement ps = con.prepareStatement(insertJobQuery); 
		
	    try {
			ps.setString(1, userID);
			ps.setString(2, pcID);
			
            ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	
	public boolean checkUserRoleIsTechnician(String userID) {
	    String checkRoleQuery = "SELECT UserRole FROM user WHERE UserID = ?";
	    
	    PreparedStatement psRoleCheck = con.prepareStatement(checkRoleQuery);
	    
	    try {
			psRoleCheck.setString(1, userID);
			
			ResultSet rsRole = psRoleCheck.executeQuery();
			
			boolean isComputerTechnician = false;
			while (rsRole.next()) {
				String role = rsRole.getString("UserRole");
				
                if (role.equals("Computer Technician")) {
                    isComputerTechnician = true;
                    break;
                }
			}
			
			return isComputerTechnician;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}      
	}

	public boolean isTechnicianAlreadyDoingJob(String pcID) {
		
	    String checkJobQuery = "SELECT PC_ID FROM pc WHERE PC_ID = ?";
	    
	    PreparedStatement psJobCheck = con.prepareStatement(checkJobQuery);

	    try {
			psJobCheck.setString(1, pcID);
			
	        ResultSet rsJob = psJobCheck.executeQuery();
	        
	        boolean isJobExist = false;
	        
			while (rsJob.next()) {
				String pd = rsJob.getString("PC_ID");
				
                if (pd.equals(pcID)) {
                	isJobExist = true;
                    break;
                }
			}
			
	        return isJobExist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public void updateJobStatus(String pcID) {
		String updateQuery = "UPDATE Job SET JobStatus = 'Complete' WHERE PC_ID = ?";
		
		PreparedStatement psUpdate = con.prepareStatement(updateQuery);
		
		try {
			psUpdate.setString(1, pcID);
			
			psUpdate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isJobComplete(String pcID) {
	    String checkQuery = "SELECT JobStatus FROM Job WHERE PC_ID = ?";
	    
	    PreparedStatement psCheck = con.prepareStatement(checkQuery);
	    
	    try {
			psCheck.setString(1, pcID);
			
			ResultSet rsJobComplete = psCheck.executeQuery();
			
			boolean isJobComplete = false;
	        
			while (rsJobComplete.next()) {
				String js = rsJobComplete.getString("JobStatus");
				
                if (js.equals("Complete")) {
                	isJobComplete = true;
                    break;
                }
			}
			return isJobComplete;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
}
