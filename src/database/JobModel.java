//package database;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import model.Job;
//
//public class JobModel {
//	Connect con = Connect.getInstance();
//
//	public ArrayList<Job> getJobData(Integer userID) {
//		ArrayList<Job> jobList = new ArrayList<>();
//		
//		String query = "SELECT PC_ID, JobStatus FROM Job WHERE UserID = ?";
//		
//        try (PreparedStatement ps = con.prepareStatment(query)) {
//            ps.setInt(1, 3);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    // Retrieve data from the result set and populate your Job object or ArrayList
//                	Integer pcId = rs.getInt("PC_ID");
//                    String jobStatus = rs.getString("JobStatus");
//
//                    // Create a Job object or do something with the data
//                    jobList.add(new Job(null, null, pcId, jobStatus));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//		return jobList;
//	}
//}
