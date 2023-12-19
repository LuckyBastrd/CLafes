package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class PCModel {
	
	Connect con = Connect.getInstance();
	
	public ArrayList<PC> getAllPCData() {
		String query = "SELECT * FROM PC";
		
		ArrayList<PC> allPCList = new ArrayList<>();
		
		ResultSet rs = con.selectData(query);
		
		try {
			while (rs.next()) {
				Integer pid = rs.getInt("PC_ID");
				String pc = rs.getString("PC_Condition");

				allPCList.add(new PC(pid, pc));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allPCList;
	}
	
	public void addPC(String pcid, String pccondition) {
		String updateQuery = "INSERT INTO `pc`(`PC_ID`, `PC_Condition`) VALUES (?, ?)";
		
		PreparedStatement psAdd = con.prepareStatement(updateQuery);
		
		try {
			psAdd.setString(1, pcid);
			psAdd.setString(2, pccondition);
			
			psAdd.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isPCIDExists(String pcid) {
		ArrayList<PC> allpcList = getAllPCData();
		
		boolean pcIDExists = false;
		
		for (PC pc : allpcList) {
			if (pc.getPcID().toString().equals(pcid)) {
				pcIDExists = true;
				break;
			}
		}
		
		return pcIDExists;
	}
	
	public void updatePC(String pcid, String pccondition) {
		String updateQuery = "UPDATE `pc` SET `PC_Condition`= ? WHERE PC_ID = ?";
		
		PreparedStatement psAdd = con.prepareStatement(updateQuery);
		
		try {
			psAdd.setString(1, pccondition);
			psAdd.setString(2, pcid);
			
			psAdd.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deletePC(String pcid) {
		String updateQuery = "DELETE FROM `pc` WHERE PC_ID = ?";
		
		PreparedStatement psAdd = con.prepareStatement(updateQuery);
		
		try {
			psAdd.setString(1, pcid);
			
			psAdd.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isPCIDBooked(String pcid) {
		
		PCBookModel pcBookKModel = new PCBookModel();
		
		ArrayList<PCBook> allPCBookedList = pcBookKModel.getAllPCBookData();
		
		boolean pcIDBooked = false;
		
		for (PCBook pcBook : allPCBookedList) {
			if (pcBook.getPcID().toString().equals(pcid)) {
				pcIDBooked = true;
				break;
			}
		}
		
		return pcIDBooked;
	}
}
