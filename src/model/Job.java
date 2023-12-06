package model;

public class Job {
	private Integer jobID;
	private Integer userID;
	private Integer pcID;
	private String JobStatus;
	
	public Job(Integer jobID, Integer userID, Integer pcID, String jobStatus) {
		super();
		this.jobID = jobID;
		this.userID = userID;
		this.pcID = pcID;
		JobStatus = jobStatus;
	}
	
	public Integer getJobID() {
		return jobID;
	}
	
	public void setJobID(Integer jobID) {
		this.jobID = jobID;
	}
	
	public Integer getUserID() {
		return userID;
	}
	
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	public Integer getPcID() {
		return pcID;
	}
	
	public void setPcID(Integer pcID) {
		this.pcID = pcID;
	}
	
	public String getJobStatus() {
		return JobStatus;
	}
	
	public void setJobStatus(String jobStatus) {
		JobStatus = jobStatus;
	}
}
