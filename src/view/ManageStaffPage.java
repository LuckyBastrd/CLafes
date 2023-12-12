package view;

import java.util.ArrayList;

import app.Main;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.User;

public class ManageStaffPage {
	
	UserController userController = new UserController();

	public class ManageStaffPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, staffTableVB, contentVB;
		public TableView<User> staffTable;
		Label titleLabel, userIDLabel, userRoleLabel;
		public TextField userIDTF, userRoleTF;
		public Button submitButton;
		public Alert alert1, alert2;
	}

	private void setStaffTableData(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.staffTable = new TableView<>();
		manageStaffPageVariables.staffTableVB = new VBox();

		TableColumn<User, Integer> idColumn = new TableColumn<>("Staff ID");
		TableColumn<User, String> nameColumn = new TableColumn<>("Staff Name");
		TableColumn<User, String> roleColumn = new TableColumn<>("Staff Role");

		manageStaffPageVariables.staffTable.getColumns().addAll(idColumn, nameColumn, roleColumn);

		ArrayList<User> staffList = userController.getStaffDataHandling();

		for (User user : staffList) {
			manageStaffPageVariables.staffTable.getItems().add(user);
		}

		idColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		roleColumn.setCellValueFactory(new PropertyValueFactory<>("userrole"));

		manageStaffPageVariables.staffTable.setMinHeight(70);
		idColumn.setMinWidth(300);
		nameColumn.setMinWidth(426);
		roleColumn.setMinWidth(426);

		manageStaffPageVariables.staffTableVB.getChildren().add(manageStaffPageVariables.staffTable);

		manageStaffPageVariables.staffTableVB.setPadding(new Insets(20, 30, 30, 30));
	}

	private void initializeManageStaffPage(ManageStaffPageVariables manageStaffPageVariables) {
		setStaffTableData(manageStaffPageVariables);

		manageStaffPageVariables.borderPane = new BorderPane();
		manageStaffPageVariables.titleVB = new VBox();
		manageStaffPageVariables.contentVB = new VBox();
		manageStaffPageVariables.gridPane = new GridPane();

		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		manageStaffPageVariables.borderPane.setTop(menuBar);

		manageStaffPageVariables.titleLabel = new Label("MANAGE STAFF");

		manageStaffPageVariables.userIDLabel = new Label("Staff ID");
		manageStaffPageVariables.userIDTF = new TextField();

		manageStaffPageVariables.userRoleLabel = new Label("New Staff Role");
		manageStaffPageVariables.userRoleTF = new TextField();

		manageStaffPageVariables.submitButton = new Button("Submit");

		manageStaffPageVariables.titleVB.getChildren().add(manageStaffPageVariables.titleLabel);
		manageStaffPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		manageStaffPageVariables.contentVB.getChildren().addAll(manageStaffPageVariables.userIDLabel,
				manageStaffPageVariables.userIDTF, manageStaffPageVariables.userRoleLabel,
				manageStaffPageVariables.userRoleTF, manageStaffPageVariables.submitButton);
		manageStaffPageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		manageStaffPageVariables.contentVB.setSpacing(13);

		manageStaffPageVariables.gridPane.add(manageStaffPageVariables.titleVB, 0, 0);
		manageStaffPageVariables.gridPane.add(manageStaffPageVariables.staffTableVB, 0, 1);
		manageStaffPageVariables.gridPane.add(manageStaffPageVariables.contentVB, 0, 2);

		manageStaffPageVariables.borderPane.setCenter(manageStaffPageVariables.gridPane);

		manageStaffPageVariables.scene = new Scene(manageStaffPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.alert1 = new Alert(AlertType.ERROR);

		manageStaffPageVariables.alert1.setTitle("Error");
		manageStaffPageVariables.alert1.setContentText("User ID Doesn't Exist !!!");
		
		manageStaffPageVariables.alert2 = new Alert(AlertType.ERROR);

		manageStaffPageVariables.alert2.setTitle("Error");
		manageStaffPageVariables.alert2.setContentText("Invalid new role !!!. Please choose between Admin, Operator, or Computer Technician(Case Sensitive)");
	}

	private void UpdateStaffHandler(ManageStaffPageVariables manageStaffPageVariables) {
		userController.updateStaffHandling(manageStaffPageVariables);
	}

	private void setStyle(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		manageStaffPageVariables.staffTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	
	public Scene startManageStaffPage() {
		ManageStaffPageVariables manageStaffPageVariables = new ManageStaffPageVariables();
		
		initializeManageStaffPage(manageStaffPageVariables);
		initializeAlert(manageStaffPageVariables);
		UpdateStaffHandler(manageStaffPageVariables);
		setStyle(manageStaffPageVariables);
		
		Main.stage.setTitle("Manage Staff Page");
		
		return manageStaffPageVariables.scene;
	}
}
