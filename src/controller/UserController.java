package controller;

import java.util.ArrayList;

import app.Main;
import model.User;
import model.UserDataSingleton;
import model.UserModel;
import view.ManageStaffPage;
import view.ManageStaffPage.ManageStaffPageVariables;

public class UserController {
	
	UserModel userModel = new UserModel();
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public ArrayList<User> getStaffDataHandling() {
		ArrayList<User> staffList = userModel.getStaffData();

		return staffList;
	}

	public void updateStaffHandling(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.submitButton.setOnAction(e -> {
			String userID = manageStaffPageVariables.userIDTF.getText();
			String newRole = manageStaffPageVariables.userRoleTF.getText();

			if (userModel.isUserExists(userID)) {
				
				if (newRole.equals("Admin") || newRole.equals("Operator") || newRole.equals("Computer Technician")) {
					
					userModel.updateStaff(newRole, userID);
					
					Main.setScene(new ManageStaffPage().startManageStaffPage());
				} else {
					manageStaffPageVariables.alert2.showAndWait();
				}

			} else {
				manageStaffPageVariables.alert1.showAndWait();
			}
		});
	}
}
