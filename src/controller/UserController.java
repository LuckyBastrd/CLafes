package controller;

import java.util.ArrayList;

import model.User;
import model.UserModel;
import view.ManageStaffPage.ManageStaffPageVariables;

public class UserController {

	public ArrayList<User> getStaffDataHandling() {
		UserModel userModel = new UserModel();
		
		ArrayList<User> staffList = userModel.getStaffData();

		return staffList;
	}

	public void updateStaffHandling(ManageStaffPageVariables manageStaffPageVariables) {
		String userID = manageStaffPageVariables.userIDTF.getText();
		String newRole = manageStaffPageVariables.userRoleTF.getText();
		
		UserModel userModel = new UserModel();

		ArrayList<User> staffList = userModel.getStaffData();

		boolean userExists = false;
		for (User user : staffList) {
			if (user.getUserID().toString().equals(userID)) {
				userExists = true;
				break;
			}
		}

		if (userExists) {
			
			if (newRole.equals("Admin") || newRole.equals("Operator") || newRole.equals("Computer Technician")) {
				
				userModel.updateStaff(newRole, userID);
				
			} else {
				manageStaffPageVariables.alert2.showAndWait();
			}

		} else {
			manageStaffPageVariables.alert1.showAndWait();
		}
	}
}
