package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.PC;
import model.Report;

public class PCController {
	
	Connect con = Connect.getInstance();
	
	// INI BUAT AMBIL DATA UNTUK NAMPILIN VIEW ALL PC
	public ArrayList<PC> getAllReportData() {
		
		ArrayList<PC> allPCList = new ArrayList<>();
		
		String query = "SELECT * FROM PC";
		
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
