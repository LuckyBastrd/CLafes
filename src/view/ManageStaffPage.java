package view;

import java.util.ArrayList;

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
import javafx.stage.Stage;
import model.User;

public class ManageStaffPage {

	public class ManageStaffPageVariables {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, staffTableVB, contentVB;
		public TableView<User> staffTable;
		Label titleLabel, userIDLabel, userRoleLabel;
		public TextField userIDTF, userRoleTF;
		Button submitButton;
		public Alert alert1, alert2;
	}

	private void setStaffTableData(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.staffTable = new TableView<>();
		manageStaffPageVariables.staffTableVB = new VBox();

		TableColumn<User, Integer> idColumn = new TableColumn<>("Staff ID");
		TableColumn<User, String> nameColumn = new TableColumn<>("Staff Name");
		TableColumn<User, String> roleColumn = new TableColumn<>("Staff Role");

		manageStaffPageVariables.staffTable.getColumns().addAll(idColumn, nameColumn, roleColumn);

		UserController userController = new UserController();

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

	private void initializeManageStaffPage(ManageStaffPageVariables manageStaffPageVariables, Stage stage, User user) {
		setStaffTableData(manageStaffPageVariables);

		manageStaffPageVariables.borderPane = new BorderPane();
		manageStaffPageVariables.titleVB = new VBox();
		manageStaffPageVariables.contentVB = new VBox();
		manageStaffPageVariables.gridPane = new GridPane();

		MenuBar menuBar = MenuBarBuilder.createMenuBar(stage, user);
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

	private void UpdateStaffHandler(ManageStaffPageVariables manageStaffPageVariables, Stage stage, User user) {
		UserController userController = new UserController();
		
		manageStaffPageVariables.submitButton.setOnAction(e -> {
			userController.updateStaffHandling(manageStaffPageVariables);
			new ManageStaffPage(stage, user);
		});
	}

	private void setStyle(ManageStaffPageVariables manageStaffPageVariables) {
		manageStaffPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		manageStaffPageVariables.staffTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public ManageStaffPage(Stage stage, User user) {
		ManageStaffPageVariables manageStaffPageVariables = new ManageStaffPageVariables();
		initializeManageStaffPage(manageStaffPageVariables, stage, user);
		initializeAlert(manageStaffPageVariables);
		UpdateStaffHandler(manageStaffPageVariables, stage, user);
		setStyle(manageStaffPageVariables);

		manageStaffPageVariables.stage = stage;
		manageStaffPageVariables.stage.setResizable(false);
		manageStaffPageVariables.stage.setScene(manageStaffPageVariables.scene);
		manageStaffPageVariables.stage.setTitle("Manage Staff Page");
		manageStaffPageVariables.stage.show();
	}
}
