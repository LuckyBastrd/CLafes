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
					TransactionHistory(), 
					ViewAllReport()
			);
			break;

		case "Customer":
			menu.getItems().addAll(ViewAllPC(), TransactionHistory(), BookPC(), 
					MakeReport()
			);
			break;
			
		case "Operator":
			menu.getItems().addAll(ViewAllPC(), MakeReport(), ViewPCBooked(), 
					AssignUsertoAnotherPC()
			);
			break;
			
		case "Computer Technician":
			menu.getItems().addAll(ViewAllPC(), ViewTechnicianJob());
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
    
    private static MenuItem TransactionHistory() {
        MenuItem transactionHistory = new MenuItem("Transaction History");
        
        return transactionHistory;
    }
    
    private static MenuItem BookPC() {
        MenuItem bookPC = new MenuItem("Book PC");
        
        return bookPC;
    }
    
    private static MenuItem MakeReport() {
    	MenuItem Makereport = new MenuItem("Make Report");
    	
    	return Makereport;
    }
    
    private static MenuItem ViewTechnicianJob() {
    	MenuItem viewTechnicianJob = new MenuItem("View All Job");
    	
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
 
    
    private static MenuItem ViewAllReport() {
    	MenuItem viewAllReport = new MenuItem("View All Report");
    	
    	return viewAllReport;
    }
   
}
