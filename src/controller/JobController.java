package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.Job;
import model.User;
import view.TechnicianJobPage.TechnicianJobPageVariables;

public class JobController {

	Connect con = Connect.getInstance();

	public ArrayList<Job> getAllJobData() {
		ArrayList<Job> allJobList = new ArrayList<>();

		String query = "SELECT * FROM Job";

		ResultSet rs = con.selectData(query);

		try {
			while (rs.next()) {
				Integer jid = rs.getInt("Job_ID");
				Integer uid = rs.getInt("UserID");
				Integer pid = rs.getInt("PC_ID");
				String jstatus = rs.getString("JobStatus");

				allJobList.add(new Job(jid, uid, pid, jstatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allJobList;

	}

	public ArrayList<Job> getTechJobData(User user) {
		ArrayList<Job> techJobList = new ArrayList<>();

		String query = "SELECT * FROM job WHERE UserID = ?";

		PreparedStatement ps = con.prepareStatement(query);

		try {
			ps.setInt(1, user.getUserID());

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

	public void HandlingUpdateJobStatus(TechnicianJobPageVariables technicianJobPageVariables, User user) {
		String checkQuery = "SELECT JobStatus FROM Job WHERE PC_ID = ?";
		String updateQuery = "UPDATE Job SET JobStatus = 'Complete' WHERE PC_ID = ?";

		String pcID = technicianJobPageVariables.pcIDTF.getText();

		ArrayList<Job> techJobList = getTechJobData(user);

		boolean jobExists = false;
		for (Job job : techJobList) {
			if (job.getPcID().toString().equals(pcID)) {
				jobExists = true;
				break;
			}
		}

		if (jobExists) {
			try {
				PreparedStatement psSelect = con.prepareStatement(checkQuery);
				
				psSelect.setString(1, pcID);
				
				ResultSet rs = psSelect.executeQuery();

				if (rs.next()) {
					String jobStatus = rs.getString("JobStatus");

					if (jobStatus.equals("Complete")) {
						
						technicianJobPageVariables.alert2.showAndWait();
						
					} else {
						PreparedStatement psUpdate = con.prepareStatement(updateQuery);

						psUpdate.setString(1, pcID);

						psUpdate.executeUpdate();
					}
				}
			} catch (SQLException e) {
				// Log the exception or handle it appropriately
				e.printStackTrace();
			}
		}

		else {
			technicianJobPageVariables.alert1.showAndWait();
		}

	}

}
