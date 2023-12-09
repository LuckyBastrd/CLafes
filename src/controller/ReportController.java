package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.Report;

public class ReportController {
	
	Connect con = Connect.getInstance();
	
	public ArrayList<Report> getAllReportData() {
		
		ArrayList<Report> allReportList = new ArrayList<>();
		
		String query = "SELECT * FROM Report";
		
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
}
