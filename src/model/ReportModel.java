package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class ReportModel {
	
	Connect con = Connect.getInstance();
	
	public ArrayList<Report> getAllReportData() {
		String query = "SELECT * FROM report rt JOIN user us ON rt.UserID = us.UserID";
		
		ArrayList<Report> allReportList = new ArrayList<>();
		
		ResultSet rs = con.selectData(query);

		try {
			while (rs.next()) {
				Integer rid = rs.getInt("Report_ID");
				String urole = rs.getString("UserRole");
				Integer pid = rs.getInt("PC_ID");
				String rnote = rs.getString("ReportNote");
				Date rdate = rs.getDate("ReportDate");
	            
				allReportList.add(new Report(rid, urole, pid, rnote, rdate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allReportList;
	}
	
	public void makeReport(User user, String pcID, String reportNote) {
		String query = "INSERT INTO `report`(`Report_ID`, `UserID`, `PC_ID`, `ReportNote`, `ReportDate`) VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(query);

		try {

			ps.setString(1, null);
			ps.setInt(2, user.getUserID());
			ps.setString(3, pcID);
			ps.setString(4, reportNote);
			ps.setDate(5, new Date(System.currentTimeMillis()));
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}