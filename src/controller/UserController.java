//package controller;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import database.Connect;
//import view.LoginPage.LoginVariable;
//
//public class UserController {
//	
//	Connect con = Connect.getInstance();
//	
//	public void handlingLogin(LoginVariable loginVariable) {
//		loginVariable.LoginButton.setOnAction(e -> {
//			
//			ResultSet rs = con.selectData("SELECT * FROM User");
//			
//			String username = loginVariable.UsernameTF.getText();
//			String password = loginVariable.PasswordPF.getText();
//			
//			
//			try {
//				while(rs.next()) {
//					String u = rs.getString("UserName");
//					String p = rs.getString("UserPassword");
//					
//					if(username.equals(u) && password.equals(p)) {
//						System.out.println("Berhasil");
//					}
//					
//				}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		});
//	}
//
//}
