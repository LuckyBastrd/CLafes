package controller;

import java.util.ArrayList;

import app.Main;
import database.UserDataSingleton;
import model.User;
import model.UserModel;
import view.HomePage;
import view.LoginPage;
import view.LoginPage.LoginVar;
import view.ManageStaffPage;
import view.ManageStaffPage.ManageStaffPageVariables;
import view.RegisterPage;
import view.RegisterPage.RegisterVar;

public class UserController {

	UserModel userModel = new UserModel();
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();

	public void registHandle(RegisterVar registerVar) {
		registerVar.registerSubmit.setOnAction(e->{
            String username = registerVar.usernameTf.getText();
            String userpassword = registerVar.passworPf.getText();
            String userConfirmPassword = registerVar.confirmPassPf.getText();
            int userage = registerVar.ageSpin.getValue();
            String userrole = "Customer";
            
            if(username.isEmpty()) {
                registerVar.alert1.showAndWait();
                return;
            }
            if(username.length() < 7) {
                registerVar.alert2.showAndWait();
                return;
            }
            if(userModel.isUsernameAvailable(username)) {
                registerVar.alert3.showAndWait();
                return;
            }
            if(userpassword.isEmpty()) {
                registerVar.alert4.showAndWait();
                return;
            }
            if(userpassword.length() < 6) {
                registerVar.alert5.showAndWait();
                return;
            }
            if(!userModel.isAlphanum(userpassword)) {
                registerVar.alert6.showAndWait();
                return;
            }
            if(userConfirmPassword.isEmpty()) {
                registerVar.alert7.showAndWait();
                return;
            }
            if(!userConfirmPassword.equals(userpassword)) {
                registerVar.alert8.showAndWait();
                return;
            }
            if(userage < 13 || userage > 65) {
                registerVar.alert9.showAndWait();
                return;
            }
            else {
            	userModel.registerNewUser(new User(null, username, userpassword, userage, userrole));
            	
            	Main.setScene(new LoginPage().startLoginPage());
            }
            
        });

		registerVar.menuItemLogin.setOnAction(e -> {
			try {
				new LoginPage().startLoginPage();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

			Main.setScene(new LoginPage().startLoginPage());
		});
	}

	public void loginHandle(LoginVar loginVar) {
		loginVar.loginSubmit.setOnAction(e -> {
			String username = loginVar.usernameTf.getText();
			String userpassword = loginVar.passwordPf.getText();

			if (!username.isEmpty() && !userpassword.isEmpty()) {
				
				if (userModel.isUserExists(username, userpassword)) {
					
					userModel.loginUser(username, userpassword);
					
					Main.setScene(new HomePage().startHomePage());
				}

				else {
					loginVar.alert2.showAndWait();
				}

			} 
			
			else {
				loginVar.alert1.showAndWait();
			}
		});

		loginVar.menuItemRegister.setOnAction(e -> {
			try {
				new RegisterPage().startRegisterPage();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			Main.setScene(new RegisterPage().startRegisterPage());
		});
	}

	public ArrayList<User> getStaffDataHandling() {
		ArrayList<User> staffList = userModel.getStaffData();

		return staffList;
	}

	public void updateStaffHandling(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.submitButton.setOnAction(e -> {
			String userID = manageStaffPageVariables.userIDTF.getText();
			String newRole = manageStaffPageVariables.userRoleTF.getText();

			if (userModel.isStaffExists(userID)) {

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
