package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class UserModel {
	
	Connect con = Connect.getInstance();
	
	public ArrayList<User> getStaffData() {
		ArrayList<User> staffList = new ArrayList<>();

		String query = "SELECT `UserID`, `UserName`, `UserRole` FROM `user` WHERE `UserRole` != 'Customer'";

		ResultSet rs = con.selectData(query);

		try {
			while (rs.next()) {
				Integer id = rs.getInt("UserID");
				String name = rs.getString("UserName");
				String role = rs.getString("UserRole");
				staffList.add(new User(id, name, null, null, role));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return staffList;
	}
	
	public void updateStaff(String newRole, String userID) {
		String query = "UPDATE User SET UserRole = ? WHERE UserID = ?";
		
		PreparedStatement ps = con.prepareStatement(query);

		try {
			ps.setString(1, newRole);
			ps.setString(2, userID);

			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
