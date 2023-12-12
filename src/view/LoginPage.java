package view;

import app.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.User;
import model.UserDataSingleton;

public class LoginPage {

	public class LoginVar {
		Scene scene;
		BorderPane bp;
		GridPane gp;
		VBox vb1;
		public Button button_login;
	}
	
	private void initialize(LoginVar loginVar) {
		loginVar.bp = new BorderPane();
		loginVar.gp = new GridPane();
		loginVar.vb1 = new VBox();

		loginVar.button_login = new Button("Login");
;
		loginVar.vb1.getChildren().addAll(loginVar.button_login);

		loginVar.gp.add(loginVar.vb1, 0, 0);

		loginVar.bp.setCenter(loginVar.gp);

		loginVar.scene = new Scene(loginVar.bp, 600, 600);
	}
	
	private void handle(LoginVar loginVar) {
		loginVar.button_login.setOnAction(e -> {
			Integer userID = 1;
			String userName = "Lucky";
			String userRole = "Admin";
			
			User user = new User(userID, userName, "123", 12, userRole);
			
			UserDataSingleton.getInstance().saveUserData(user);
			
			Main.setScene(new HomePage().startHomePage());
		});
	}
	


	public Scene startLoginPage() {
		
		LoginVar loginVar = new LoginVar();
		
		initialize(loginVar);
		handle(loginVar);
		
		return loginVar.scene;
	}

}
