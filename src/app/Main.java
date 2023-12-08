package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.HomePage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Integer userID = 1;
		String userName = "John Doe";
		String userRole = "Computer Technician";

	    new HomePage(primaryStage, userID, userName, userRole);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
