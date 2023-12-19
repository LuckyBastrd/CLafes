package model;

import java.sql.Date;

public class PCBook {
	private Integer bookID;
	private Integer pcID;
	private Integer userID;
	private Date bookedDate;
	
	public PCBook(Integer bookID, Integer pcID, Integer userID, Date bookedDate) {
		super();
		this.bookID = bookID;
		this.pcID = pcID;
		this.userID = userID;
		this.bookedDate = bookedDate;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public Integer getPcID() {
		return pcID;
	}

	public void setPcID(Integer pcID) {
		this.pcID = pcID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
}
