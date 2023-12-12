package view;

import app.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.User;
import model.UserDataSingleton;

public class MenuBarBuilder {
	
	static User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
    public static MenuBar createMenuBar() {
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
                createMenuItem("View All PC", null),
                createMenuItem("View All Staff", e ->  Main.setScene(new ManageStaffPage().startManageStaffPage())),
                createMenuItem("PC Management", null),
                createMenuItem("Job Management", e -> Main.setScene(new JobManagementPage().startJobManagementPage())),
                createMenuItem("Transaction History", e -> Main.setScene(new ViewAllTrHistoryPage().startViewAllTrHistoryPage())),
                createMenuItem("View All Report", e -> Main.setScene(new ViewAllReportPage().startViewAllReportPageVariables()))
        );
    }

    private static void addCustomerMenuItems(Menu menu) {
        menu.getItems().addAll(
        		createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("View All PC", null),
                createMenuItem("Transaction History", null),
                createMenuItem("Book PC", null),
                createMenuItem("Make Report", e -> Main.setScene(new MakeReportPage().startMakeReportPage()))
        );
    }

    private static void addOperatorMenuItems(Menu menu) {
        menu.getItems().addAll(
        		createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("View All PC", null),
                createMenuItem("Make Report", e -> Main.setScene(new MakeReportPage().startMakeReportPage())),
                createMenuItem("Booked PC", null),
                createMenuItem("Transfer PC", null)
        );
    }

    private static void addTechnicianMenuItems(Menu menu) {
        menu.getItems().addAll(
        		createMenuItem("Home Page", e -> Main.setScene(new HomePage().startHomePage())),
                createMenuItem("View All PC", null),
                createMenuItem("View All Job", e -> Main.setScene(new TechnicianJobPage().startTechnicianJobPage()))
        );
    }
}
