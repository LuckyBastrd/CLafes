package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
                addAdminMenuItems(menu, stage, user);
                break;

            case "Customer":
                addCustomerMenuItems(menu, stage, user);
                break;

            case "Operator":
                addOperatorMenuItems(menu, stage, user);
                break;

            case "Computer Technician":
                addTechnicianMenuItems(menu, stage, user);
                break;
        }

        return menuBar;
    }

    // Menu items for different user roles
    private static void addAdminMenuItems(Menu menu, Stage stage, User user) {
        menu.getItems().addAll(
                createMenuItem("Home Page", e -> new HomePage(stage, user)),
                createMenuItem("View All PC", null),
                createMenuItem("View All Staff", e -> new ManageStaffPage(stage, user)),
                createMenuItem("PC Management", null),
                createMenuItem("Job Management", e -> new JobManagementPage(stage, user)),
                createMenuItem("Transaction History", e -> new ViewAllTrHistoryPage(stage, user)),
                createMenuItem("View All Report", e -> new ViewAllReportPage(stage, user))
        );
    }

    private static void addCustomerMenuItems(Menu menu, Stage stage, User user) {
        menu.getItems().addAll(
                createMenuItem("Home Page", e -> new HomePage(stage, user)),
                createMenuItem("View All PC", null),
                createMenuItem("Transaction History", e -> new ViewAllTrHistoryPage(stage, user)),
                createMenuItem("Book PC", null),
                createMenuItem("Make Report", e -> new MakeReportPage(stage, user))
        );
    }

    private static void addOperatorMenuItems(Menu menu, Stage stage, User user) {
        menu.getItems().addAll(
                createMenuItem("Home Page", e -> new HomePage(stage, user)),
                createMenuItem("View All PC", null),
                createMenuItem("Make Report", e -> new MakeReportPage(stage, user)),
                createMenuItem("Booked PC", null),
                createMenuItem("Transfer PC", null)
        );
    }

    private static void addTechnicianMenuItems(Menu menu, Stage stage, User user) {
        menu.getItems().addAll(
                createMenuItem("Home Page", e -> new HomePage(stage, user)),
                createMenuItem("View All PC", null),
                createMenuItem("View All Job", e -> new TechnicianJobPage(stage, user))
        );
    }

    private static MenuItem createMenuItem(String label, EventHandler<ActionEvent> eventHandler) {
        MenuItem menuItem = new MenuItem(label);
        
        if (eventHandler != null) {
            menuItem.setOnAction(eventHandler);
        }
        
        return menuItem;
    }
}
