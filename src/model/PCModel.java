package model;

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
}
