package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import view.HomePage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Integer userID = 1;
		String userName = "John Doe";
		String userRole = "Admin";
		
		User user = new User(userID, userName, "123", 12, userRole);
		
	    new HomePage(primaryStage, user);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
