package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class TransactionModel {
	
	Connect con = Connect.getInstance();
	
	public ArrayList<Transaction> getAllTrHistoryData() {	
		String query = "SELECT " + 
				"    th.TransactionID AS transactionID, " + 
				"    th.StaffID AS staffID, " + 
				"    staff.UserName AS staffName, " + 
				"    th.TransactionDate AS transactiondate, " + 
				"    td.PC_ID AS pcID, " + 
				"    cust.UserName AS customerName, " + 
				"    td.BookedDate AS bookeddate " + 
				"FROM " + 
				"    transactionheader th " + 
				"JOIN " + 
				"    transactiondetail td ON td.TransactionID = th.TransactionID " + 
				"JOIN " + 
				"    user staff ON staff.UserID = th.StaffID " + 
				"JOIN " + 
				"    user cust ON cust.UserID = td.UserID";
		
		ArrayList<Transaction> allTrHistoryList = new ArrayList<>();
		
		ResultSet rs = con.selectData(query);
		
		try {
			while (rs.next()) {
				Integer trid = rs.getInt("transactionID");
				Integer sfid = rs.getInt("staffID");
				String sfname = rs.getString("staffName");
				Date trdate = rs.getDate("transactionDate");
				Integer pid = rs.getInt("pcID");
				String csname = rs.getString("customerName");
				Date bkdate = rs.getDate("bookedDate");
				
				allTrHistoryList.add(new Transaction(trid, sfid, sfname, trdate, pid, csname, bkdate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allTrHistoryList;
	}
	
	public ArrayList<Transaction> getCustTrHistoryData(Integer userid) {	
		String query = "SELECT " + 
				"    td.TransactionID AS transactionID, " + 
				"    td.PC_ID AS pcID, " + 
				"    cust.UserName AS customerName, " + 
				"    td.BookedDate AS bookeddate " + 
				"FROM " + 
				"    transactiondetail td " + 
				"    JOIN user cust ON cust.UserID = td.UserID " +
				"    WHERE td.UserID = ?";
		
		ArrayList<Transaction> custTrHistoryList = new ArrayList<>();
		
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userid);
			
			try (ResultSet rs = ps.executeQuery()) {
				
				while (rs.next()) {
					Integer trid = rs.getInt("transactionID");
					Integer pid = rs.getInt("pcID");
					String csname = rs.getString("customerName");
					Date bkdate = rs.getDate("bookedDate");
					
					custTrHistoryList.add(new Transaction(trid, null, null, null, pid, csname, bkdate));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return custTrHistoryList;
	}
	
	public void addTransaction(ArrayList<PCBook> pcBooks, Integer staffID) {
		
		String thQuery = "INSERT INTO `transactionheader` (`TransactionID`, `StaffID`, `TransactionDate`) VALUES (NULL, ?, ?)";
		PreparedStatement thPs = con.prepareStatement(thQuery);
		
		String selectLastIdQuery = "SELECT LAST_INSERT_ID() AS TransactionID";
		PreparedStatement selectPs = con.prepareStatement(selectLastIdQuery);
		
		try {
			thPs.setInt(1, staffID);
			thPs.setDate(2, new Date(System.currentTimeMillis()));
			
			thPs.execute();
			
			ResultSet thRs = selectPs.executeQuery();
			Integer transactionId = null;
			
			if(thRs.next()) {
				transactionId = thRs.getInt("TransactionID");
			} 
			
			for (PCBook pcBook : pcBooks) {
				String tdQuery = "INSERT INTO `transactiondetail` (`TransactionID`, `PC_ID`, `UserID`, `BookedDate`) VALUES (?, ?, ?, ?)";
				
				PreparedStatement tdPs = con.prepareStatement(tdQuery);
				
				tdPs.setInt(1, transactionId);
				tdPs.setInt(2, pcBook.getPcID());
				tdPs.setInt(3, pcBook.getUserID());
				tdPs.setDate(4, pcBook.getBookedDate());
				
				tdPs.execute();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
