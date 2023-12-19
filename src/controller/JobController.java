package controller;

import java.util.ArrayList;

import app.Main;
import database.UserDataSingleton;
import model.Job;
import model.JobModel;
import model.User;
import view.TechnicianJobPage;
import view.JobManagementPage;
import view.JobManagementPage.JobManagementPageVariables;
import view.TechnicianJobPage.TechnicianJobPageVariables;

public class JobController {
	
	JobModel jobModel = new JobModel();
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public ArrayList<Job> getTechJobDataHandling() {
		ArrayList<Job> techJobList = new ArrayList<>();

		String userrole = currentUser.getUserrole();
		Integer userid = currentUser.getUserID();

		if (userrole.equals("Admin")) {
			techJobList = jobModel.getAllTechJobData();
		}

		else if (userrole.equals("Computer Technician")) {
			techJobList = jobModel.getTechJobData(userid);
		}

		return techJobList;
	}
	
	public void addStaffJobHandling(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.addJobButton.setOnAction(e -> {
			String userID = jobManagementPageVariables.userIDTF.getText();
		    String pcID = jobManagementPageVariables.pcIDTF.getText();
		    
		    if (!jobModel.checkUserRoleIsTechnician(userID)) {
		    	jobManagementPageVariables.alert1.showAndWait();
		        return;
		    }
		    
		    if (!jobModel.checkPcCondition(pcID) && !jobModel.isPcReported(pcID)) {
		    	jobManagementPageVariables.alert2.showAndWait();
		        return;
		    }

		    if (jobModel.isTechnicianAlreadyDoingJob(pcID)) {
		    	jobManagementPageVariables.alert3.showAndWait();
		        return;
		    }
		    
		    jobModel.addStaffJob(userID, pcID);
		    
		    Main.setScene(new JobManagementPage().startJobManagementPage());
		});
	}
	
	public void amdinUpdateJobStatusHandling(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.updateStatusButton.setOnAction(e -> {
		    String pcID = jobManagementPageVariables.pcID2TF.getText();
		    String jobStatus = jobManagementPageVariables.jobStatusTF.getText();
			
			if (jobModel.isJobExists(pcID)) {
				
				if (jobStatus.equals("Complete") || jobStatus.equals("UnComplete")) {
					jobModel.adminUpdateJobStatus(pcID, jobStatus);
					
					Main.setScene(new JobManagementPage().startJobManagementPage());
				}
				
				else {
					jobManagementPageVariables.alert5.showAndWait();
				}
				
			}
			
			else {
				jobManagementPageVariables.alert4.showAndWait();
			}
		});
	}

	public void updateJobStatusHandling(TechnicianJobPageVariables technicianJobPageVariables) {
		technicianJobPageVariables.completeButton.setOnAction(e -> {
			String pcID = technicianJobPageVariables.pcIDTF.getText();

			if (jobModel.isTechJobExists(currentUser.getUserID(), pcID)) {
				
				if (jobModel.isJobComplete(pcID)) {
					technicianJobPageVariables.alert2.showAndWait();
				} 
				
				else {
					jobModel.updateJobStatus(pcID);
					
					Main.setScene(new TechnicianJobPage().startTechnicianJobPage());
				}
			}
			else {
				technicianJobPageVariables.alert1.showAndWait();
			}
		});
	}

}
