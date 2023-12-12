package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import view.HomePage;
import view.JobManagementPage;
import view.MakeReportPage;
import view.ManageStaffPage;
import view.TechnicianJobPage;
import view.ViewAllReportPage;
import view.ViewAllTrHistoryPage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Integer userID = 5;
		String userName = "Lucky";
		String userRole = "Admin";
		
		User user = new User(userID, userName, "123", 12, userRole);

	    new HomePage(primaryStage, user);
		//new JobManagementPage(primaryStage, user);
		//new TechnicianJobPage(primaryStage, user);
		//new ManageStaffPage(primaryStage, user);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}