package model;

import java.sql.Date;

public class Transaction {
	private Integer transactionID;
	private Integer staffID;
	private String staffName;
	private Date transactiondate;
	private Integer pcID;
	private String customerName;
	private Date bookeddate;
	
	public Transaction(Integer transactionID, Integer staffID, String staffName, Date transactiondate, Integer pcID,
			String customerName, Date bookeddate) {
		super();
		this.transactionID = transactionID;
		this.staffID = staffID;
		this.staffName = staffName;
		this.transactiondate = transactiondate;
		this.pcID = pcID;
		this.customerName = customerName;
		this.bookeddate = bookeddate;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getStaffID() {
		return staffID;
	}

	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}

	public Integer getPcID() {
		return pcID;
	}

	public void setPcID(Integer pcID) {
		this.pcID = pcID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getBookeddate() {
		return bookeddate;
	}

	public void setBookeddate(Date bookeddate) {
		this.bookeddate = bookeddate;
	}
}
