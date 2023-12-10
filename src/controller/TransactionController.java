package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.Transaction;
import model.User;

public class TransactionController {
	Connect con = Connect.getInstance();
	
	public ArrayList<Transaction> getAllTransactionData(User user) {
		
		ArrayList<Transaction> allTransactionList = new ArrayList<>();
		
		String userrole = user.getUserrole();
		
		//Untuk memanggil semua transaction history
		if (userrole.equals("Admin")) {
			String query ="SELECT " + 
					"    th.TransactionID AS transactionID, " + 
					"    th.StaffID AS staffID, " + 
					"    staff.UserName AS staffName," + 
					"    th.TransactionDate AS transactiondate, " + 
					"    pb.PC_ID AS pcID, " + 
					"    cust.UserName AS customerName, " + 
					"    pb.BookedDate AS bookeddate " + 
					"FROM " + 
					"    transactionheader th " + 
					"JOIN transactiondetail td ON td.TransactionID = th.TransactionID " + 
					"JOIN user staff ON staff.UserID = th.StaffID " + 
					"JOIN pcbook pb ON pb.BookID = td.BookID " + 
					"JOIN user cust ON cust.UserID = pb.UserID;";
			
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
					
					allTransactionList.add(new Transaction(trid, sfid, sfname, trdate, pid, csname, bkdate));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Untuk memanggil transaction history customer berdasarkan user id nya
		else if (userrole.equals("Customer")) {
			String query ="SELECT " + 
					"    td.TransactionID AS transactionID, " + 
					"    pb.PC_ID AS pcID, " + 
					"    cust.UserName AS customerName, " + 
					"    pb.BookedDate AS bookeddate " + 
					"FROM transactiondetail td " + 
					"JOIN pcbook pb ON pb.BookID = td.BookID " + 
					"JOIN user cust on cust.UserID = pb.UserID " + 
					"WHERE pb.UserID = ?;";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			try {
				ps.setInt(1, user.getUserID());
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Integer trid = rs.getInt("transactionID");
						Integer pid = rs.getInt("pcID");
						String csname = rs.getString("customerName");
						Date bkdate = rs.getDate("bookedDate");
						
						allTransactionList.add(new Transaction(trid, null, null, null, pid, csname, bkdate));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allTransactionList;
	}
}
