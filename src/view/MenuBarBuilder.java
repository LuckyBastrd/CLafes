package view;

import app.Main;
import database.UserDataSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.User;

public class MenuBarBuilder {
	
    public static MenuBar createMenuBar() {
    	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
    	
        MenuBar menuBar = new MenuBar();
        
        Menu menu = new Menu("Menu");
        
        menuBar.getMenus().addAll(menu);

        switch (currentUser.getUserrole()) {
            case "Admin":
                addAdminMenuItems(menu);
                break;

            case "Customer":
                addCustomerMenuItems(menu);
                break;

            case "Operator":
                addOperatorMenuItems(menu);
                break;

            case "Computer Technician":
                addTechnicianMenuItems(menu);
                break;
        }

        return menuBar;
    }
    
    
    private static MenuItem createMenuItem(String label, EventHandler<ActionEvent> eventHandler) {
        MenuItem menuItem = new MenuItem(label);
        
        if (eventHandler != null) {
            menuItem.setOnAction(eventHandler);
        }
        
        return menuItem;
    }

    // Menu items for different user roles
    private static void addAdminMenuItems(Menu menu) {
        menu.getItems().addAll(
                createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("View All Staff", e ->  Main.setScene(new ManageStaffPage().startManageStaffPage())),
                createMenuItem("PC Management", e -> Main.setScene(new PCManagementPage().startPCManagementPageVariables())),
                createMenuItem("Job Management", e -> Main.setScene(new JobManagementPage().startJobManagementPage())),
                createMenuItem("Transaction History", e -> Main.setScene(new ViewAllTrHistoryPage().startViewAllTrHistoryPage())),
                createMenuItem("View All Report", e -> Main.setScene(new ViewAllReportPage().startViewAllReportPageVariables())),
                createMenuItem("Log Out", e -> {
                	UserDataSingleton.getInstance().clearUserData();
                    Main.setScene(new LoginPage().startLoginPage());
                })
        );
    }

    private static void addCustomerMenuItems(Menu menu) {
        menu.getItems().addAll(
        		createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("Transaction History", e -> Main.setScene(new ViewCustomerTrHistoryPage().startViewCustomerTrHistoryPage())),
                createMenuItem("Book PC", e -> Main.setScene(new BookPCPage().startBookPCPage())),
                createMenuItem("Make Report", e -> Main.setScene(new MakeReportPage().startMakeReportPage())),
                createMenuItem("Log Out", e -> {
                	UserDataSingleton.getInstance().clearUserData();
                    Main.setScene(new LoginPage().startLoginPage());
                })
        );
    }

    private static void addOperatorMenuItems(Menu menu) {
        menu.getItems().addAll(
        		createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("Make Report", e -> Main.setScene(new MakeReportPage().startMakeReportPage())),
                createMenuItem("Booked PC", null),
                createMenuItem("Assign User To Other PC", e -> Main.setScene(new AssignUserPage().startAssignUserPage())),
                createMenuItem("Log Out", e -> {
                	UserDataSingleton.getInstance().clearUserData();
                    Main.setScene(new LoginPage().startLoginPage());
                })
        );
    }

    private static void addTechnicianMenuItems(Menu menu) {
        menu.getItems().addAll(
        		createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("View All Job", e -> Main.setScene(new TechnicianJobPage().startTechnicianJobPage())),
                createMenuItem("Log Out", e -> {
                	UserDataSingleton.getInstance().clearUserData();
                    Main.setScene(new LoginPage().startLoginPage());
                })
        );
    }
}
