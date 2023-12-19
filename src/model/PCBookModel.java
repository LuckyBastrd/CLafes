package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.PCController;
import database.Connect;

public class PCBookModel {
	
	Connect con = Connect.getInstance();
	
	public ArrayList<PCBook> getAllPCBookData() {
		String query = "SELECT * FROM pcbook";
		
		ArrayList<PCBook> allAllPCBookList = new ArrayList<>();
		
		ResultSet rs = con.selectData(query);

		try {
			while (rs.next()) {
				Integer bid = rs.getInt("BookID");
				Integer pid = rs.getInt("PC_ID");
				Integer uid = rs.getInt("UserID");
				Date bdate = rs.getDate("BookedDate");
	            
				allAllPCBookList.add(new PCBook(bid, pid, uid, bdate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allAllPCBookList;
	}
	
	public void bookPC(String pcID, Integer userID, Date bookDate) {
		String query = "INSERT INTO `pcbook`(`BookID`, `PC_ID`, `UserID`, `BookedDate`) VALUES (?, ?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		try {

			ps.setString(1, null);
			ps.setString(2, pcID);
			ps.setInt(3, userID);
			ps.setDate(4, bookDate);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public boolean isPcExists(String pcID) {
    	
    	PCController pcController = new PCController();
    	
    	ArrayList<PC> pcList = pcController.getAllPCDataHandling();
    	
		boolean isPCExists = false;
		
		for (PC pc : pcList) {
			if (pc.getPcID().toString().equals(pcID)) {
				isPCExists = true;
				break;
			}
		}
        
        return isPCExists;
    } 
    
	public boolean isBooked(String pcID, java.sql.Date bookDate) {
	    String checkBookedDate = "SELECT * FROM PCBook WHERE PC_ID = ? AND BookedDate = ?";
	    
	    PreparedStatement psBookedDate = con.prepareStatement(checkBookedDate);
	    
	    try {
	    	psBookedDate.setString(1, pcID);
	    	psBookedDate.setDate(2, bookDate);
			
			ResultSet rsBooked = psBookedDate.executeQuery();
			
			boolean isBooked = false;
			while (rsBooked.next()) {
				String pcid = rsBooked.getString("PC_ID");
				java.sql.Date bookeddate = rsBooked.getDate("BookedDate");
				
                if (pcid.equals(pcID) && bookeddate.toLocalDate().equals(bookDate.toLocalDate())) {
                	isBooked = true;
                    break;
                }
			}
			
			return isBooked;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}      
	}
    
    
    
}
