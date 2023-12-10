package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.User;

public class MenuBarBuilder {
	
	public static MenuBar createMenuBar(Stage stage, User user) {
        MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Menu");
        
        menuBar.getMenus().addAll(menu);
        
        switch (user.getUserrole()) {
		case "Admin":
			menu.getItems().addAll(
					HomePage(stage, user),
					ViewAllPC(), 
					ViewAllStaff(stage, user), 
					PCManagement(), 
					JobManagement(), 
					TransactionHistory(stage, user), 
					ViewAllReport(stage, user)
			);
			break;

		case "Customer":
			menu.getItems().addAll(
					HomePage(stage, user),
					ViewAllPC(), 
					TransactionHistory(stage, user), 
					BookPC(), 
					MakeReport(stage, user)
			);
			break;
			
		case "Operator":
			menu.getItems().addAll(
					HomePage(stage, user),
					ViewAllPC(), 
					MakeReport(stage, user), 
					ViewPCBooked(), 
					AssignUsertoAnotherPC()
			);
			break;
			
		case "Computer Technician":
			menu.getItems().addAll(
					HomePage(stage, user),
					ViewAllPC(), 
					ViewTechnicianJob(stage, user)
			);
			break;
		}

        return menuBar;
    }
	
	// All Menu Item
    private static MenuItem HomePage(Stage stage, User user) {
        MenuItem homePage = new MenuItem("Home Page");
        
        homePage.setOnAction(e -> {
    		new HomePage(stage, user);
    	});
        
        return homePage;
    }
	
    private static MenuItem ViewAllPC() {
        MenuItem viewAllPC = new MenuItem("View All PC");
        
        return viewAllPC;
    }
    
    private static MenuItem TransactionHistory(Stage stage, User user) {
        MenuItem transactionHistory = new MenuItem("Transaction History");
        
        transactionHistory.setOnAction(e -> {
        	new ViewAllTrHistoryPage(stage, user);
        });
        
        return transactionHistory;
    }
    
    private static MenuItem BookPC() {
        MenuItem bookPC = new MenuItem("Book PC");
        
        return bookPC;
    }
    
    private static MenuItem MakeReport(Stage stage, User user) {
    	MenuItem Makereport = new MenuItem("Make Report");
    	
    	Makereport.setOnAction(e -> {
    		new MakeReportPage(stage, user);
    	});
    	
    	return Makereport;
    }
    
    private static MenuItem ViewTechnicianJob(Stage stage, User user) {
    	MenuItem viewTechnicianJob = new MenuItem("View All Job");
    	
    	viewTechnicianJob.setOnAction(e -> {
    		new TechnicianJobPage(stage, user);
    	});
    	
    	return viewTechnicianJob;
    }
    
    private static MenuItem ViewPCBooked() {
    	MenuItem viewPCBooked = new MenuItem("Booked PC");
    	
    	return viewPCBooked;
    }
    
    private static MenuItem AssignUsertoAnotherPC() {
    	MenuItem assignUsertoAnotherPC = new MenuItem("Transfer PC");
    	
    	return assignUsertoAnotherPC;
    }
    
    private static MenuItem ViewAllStaff(Stage stage, User user) {
    	MenuItem viewAllStaff = new MenuItem("View All Staff");
    	
    	viewAllStaff.setOnAction(e -> {
    		new ManageStaffPage(stage, user);
    	});
    	
    	return viewAllStaff;
    }
    
    private static MenuItem PCManagement() {
    	MenuItem pcManagement = new MenuItem("PC Management");
    	
    	return pcManagement;
    }
    
    private static MenuItem JobManagement() {
    	MenuItem jobManagement = new MenuItem("Job Management");
    	
    	return jobManagement;
    }
 
    
    private static MenuItem ViewAllReport(Stage stage, User user) {
    	MenuItem viewAllReport = new MenuItem("View All Report");
    	
    	viewAllReport.setOnAction(e -> {
    		new ViewAllReportPage(stage, user);
    	});
    	
    	return viewAllReport;
    }
   
}
