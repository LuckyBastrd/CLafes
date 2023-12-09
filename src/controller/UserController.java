package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.User;
import view.ManageStaffPage.ManageStaffPageVariables;

public class UserController {

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

	public void handlingUpdateStaff(ManageStaffPageVariables manageStaffPageVariables) {

		String query = "UPDATE User SET UserRole = ? WHERE UserID = ?";

		String userID = manageStaffPageVariables.userIDTF.getText();
		String newRole = manageStaffPageVariables.userRoleTF.getText();

		ArrayList<User> staffList = getStaffData();

		boolean userExists = false;
		for (User user : staffList) {
			if (user.getUserID().toString().equals(userID)) {
				userExists = true;
				break;
			}
		}

		if (userExists) {

			if (newRole.equals("Admin") || newRole.equals("Operator") || newRole.equals("Computer Technician")) {
				PreparedStatement ps = con.prepareStatment(query);

				try {
					ps.setString(1, newRole);
					ps.setString(2, userID);

					ps.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				manageStaffPageVariables.alert2.showAndWait();
			}

		} else {
			manageStaffPageVariables.alert1.showAndWait();
		}
	}
}
