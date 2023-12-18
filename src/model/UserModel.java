package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import database.UserDataSingleton;

public class UserModel {

	Connect con = Connect.getInstance();
	
	public ArrayList<User> getUserData() {
		ResultSet resultSet = con.selectData("SELECT * FROM user");
		ArrayList<User> userList = new ArrayList<>();

		try {
			while (resultSet.next()) {
				userList.add(new User(null, resultSet.getString("UserName"), resultSet.getString("UserPassword"),
						resultSet.getInt("UserAge"), resultSet.getString("UserRole")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return userList;
	}

	public void registerNewUser(User user) {
		try {
			String query = String.format("INSERT INTO `user` VALUES (?, ?, ?, ?, ?)");
			PreparedStatement preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, null);
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getUserpassword());
			preparedStatement.setInt(4, user.getUserage());
			preparedStatement.setString(5, user.getUserrole());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isUsernameAvailable(String username) {
		try {
			return con.checkUsername(username);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAlphanum(String password) {
		boolean hasChar = false;
		boolean hasDigit = false;
		for (Character ch : password.toCharArray()) {
			if (Character.isLetter(ch)) {
				hasChar = true;
			}
			if (Character.isDigit(ch)) {
				hasDigit = true;
			}
		}

		return hasChar && hasDigit;
	}

	public void loginUser(String username, String userpassword) {
		ResultSet resultSet = con.selectData("SELECT * FROM user");

		try {
			while (resultSet.next()) {
				Integer userID = resultSet.getInt("UserID");
				String userName = resultSet.getString("UserName");
				String userPassword = resultSet.getString("UserPassword");
				String userRole = resultSet.getString("UserRole");

				if (username.equals(userName) && userpassword.equals(userPassword)) {
					User userLogin = new User(userID, userName, null, null, userRole);
					
					UserDataSingleton.getInstance().saveUserData(userLogin);
					
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public boolean isUserExists(String username, String userpassword) {

		ArrayList<User> userList = getUserData();

		boolean userExists = false;

		for (User user : userList) {
			if (user.getUsername().equals(username) && user.getUserpassword().equals(userpassword)) {
				userExists = true;
				break;
			}
		}

		return userExists;
	}

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

	public boolean isStaffExists(String userID) {

		ArrayList<User> staffList = getStaffData();

		boolean staffExists = false;

		for (User user : staffList) {
			if (user.getUserID().toString().equals(userID)) {
				staffExists = true;
				break;
			}
		}

		return staffExists;
	}
}
