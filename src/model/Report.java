package model;

import java.sql.Date;

public class Report {
	private Integer reportID;
	private String userrole;
	private Integer pcID;
	private String reportnote;
	private Date reportdate;
	
	public Report(Integer reportID, String userrole, Integer pcID, String reportnote, Date reportdate) {
		super();
		this.reportID = reportID;
		this.userrole = userrole;
		this.pcID = pcID;
		this.reportnote = reportnote;
		this.reportdate = reportdate;
	}

	public Integer getReportID() {
		return reportID;
	}

	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public Integer getPcID() {
		return pcID;
	}

	public void setPcID(Integer pcID) {
		this.pcID = pcID;
	}

	public String getReportnote() {
		return reportnote;
	}

	public void setReportnote(String reportnote) {
		this.reportnote = reportnote;
	}

	public Date getReportdate() {
		return reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
}