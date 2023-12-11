package controller;

import java.util.ArrayList;

import model.Job;
import model.JobModel;
import model.PC;
import model.User;
import view.JobManagementPage.JobManagementPageVariables;
import view.TechnicianJobPage.TechnicianJobPageVariables;

public class JobController {

	public ArrayList<Job> getTechJobDataHandling(User user) {
		ArrayList<Job> techJobList = new ArrayList<>();

		String userrole = user.getUserrole();
		Integer userid = user.getUserID();
		
		JobModel jobmodel = new JobModel();

		if (userrole.equals("Admin")) {
			techJobList = jobmodel.getAllTechJobData();
		}

		else if (userrole.equals("Computer Technician")) {
			techJobList = jobmodel.getTechJobData(userid);
		}

		return techJobList;
	}
	
	public void addStaffJobHandling(JobManagementPageVariables jobManagementPageVariables) {

	    String userID = jobManagementPageVariables.userIDTF.getText();
	    String pcID = jobManagementPageVariables.pcIDTF.getText();
	    
	    JobModel jobmodel = new JobModel();
	    
	    if (!jobmodel.checkUserRoleIsTechnician(userID)) {
	    	jobManagementPageVariables.alert1.showAndWait();
	        return;
	    }
	    
//		PCController pcController = new PCController();
//
//		ArrayList<PC> pcList = pcController.getAllPCDataHandling();
//
//		boolean pcExists = false;
//		for (PC pc : pcList) {
//			if (pc.getPcID().toString().equals(pcID)) {
//				pcExists = true;
//				break;
//			}
//		}
//	    
//	    if (!pcExists) {
//	    	jobManagementPageVariables.alert2.showAndWait();
//	        return;
//	    }
	    
	    if (!jobmodel.checkPcCondition(pcID) && !jobmodel.isPcReported(pcID)) {
	    	jobManagementPageVariables.alert2.showAndWait();
	        return;
	    }

	    if (jobmodel.isTechnicianAlreadyDoingJob(pcID)) {
	    	jobManagementPageVariables.alert3.showAndWait();
	        return;
	    }
	    
	    jobmodel.addStaffJob(userID, pcID);
	}
	
	public void amdinUpdateJobStatusHandling(JobManagementPageVariables jobManagementPageVariables) {
	    String pcID = jobManagementPageVariables.pcID2TF.getText();
	    String jobStatus = jobManagementPageVariables.jobStatusTF.getText();
	    
		JobModel jobmodel = new JobModel();

		ArrayList<Job> allTechJobList = jobmodel.getAllTechJobData();

		boolean jobExists = false;
		for (Job job : allTechJobList) {
			if (job.getPcID().toString().equals(pcID)) {
				jobExists = true;
				break;
			}
		}
		
		if (jobExists) {
			
			if (jobStatus.equals("Complete") || jobStatus.equals("UnComplete")) {
				jobmodel.adminUpdateJobStatus(pcID, jobStatus);
			}
			
			else {
				jobManagementPageVariables.alert5.showAndWait();
			}
			
		}
		
		else {
			jobManagementPageVariables.alert4.showAndWait();
		}
		
	}

	public void updateJobStatusHandling(TechnicianJobPageVariables technicianJobPageVariables, User user) {
		String pcID = technicianJobPageVariables.pcIDTF.getText();
		
		Integer userid = user.getUserID();
		
		JobModel jobmodel = new JobModel();

		ArrayList<Job> techJobList = jobmodel.getTechJobData(userid);

		boolean jobExists = false;
		for (Job job : techJobList) {
			if (job.getPcID().toString().equals(pcID)) {
				jobExists = true;
				break;
			}
		}

		if (jobExists) {
			
			if (jobmodel.isJobComplete(pcID)) {
				technicianJobPageVariables.alert2.showAndWait();
			} 
			
			else {
				jobmodel.updateJobStatus(pcID);
			}
		}
		else {
			technicianJobPageVariables.alert1.showAndWait();
		}

	}

}
