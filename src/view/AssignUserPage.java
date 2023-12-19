package view;

import java.sql.Date;
import java.util.ArrayList;

import app.Main;
import controller.PCBookController;
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
import model.PCBook;

public class AssignUserPage {

	PCBookController pcBookController = new PCBookController();

	public class AssignUserPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, tableVB, contentVB;
		TableView<PCBook> bookedPCTable;
		Label titleLabel, subTitleLabel, bookIDLabel, pcIDLabel;
		public TextField bookIDTF, pcIDTF;
		public Button updateButton;
		public Alert alert1, alert2, alert3;
	}

	private void setBookedPCTableData(AssignUserPageVariables assignUserPageVariables) {
		assignUserPageVariables.bookedPCTable = new TableView<>();
		assignUserPageVariables.tableVB = new VBox();

		assignUserPageVariables.subTitleLabel = new Label("All Booked PC on Internet CLafe");
		assignUserPageVariables.subTitleLabel
				.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 15px;");

		TableColumn<PCBook, Integer> bookIDColumn = new TableColumn<>("Book ID");
		TableColumn<PCBook, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<PCBook, Integer> userIDColumn = new TableColumn<>("User ID");
		TableColumn<PCBook, Date> bookedDateColumn = new TableColumn<>("Boked Date");

		assignUserPageVariables.bookedPCTable.getColumns().addAll(bookIDColumn, pcIDColumn, userIDColumn,
				bookedDateColumn);

		ArrayList<PCBook> bookedPCList = pcBookController.getBookedPCDataHandling();

		for (PCBook pcBook : bookedPCList) {
			assignUserPageVariables.bookedPCTable.getItems().add(pcBook);
		}

		bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
		bookedDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookedDate"));

		assignUserPageVariables.bookedPCTable.setMaxHeight(270);
		bookIDColumn.setMinWidth(20);
		pcIDColumn.setMinWidth(20);
		userIDColumn.setMinWidth(20);
		bookedDateColumn.setMinWidth(970);

		assignUserPageVariables.tableVB.getChildren().addAll(assignUserPageVariables.subTitleLabel,
				assignUserPageVariables.bookedPCTable);

		assignUserPageVariables.tableVB.setPadding(new Insets(20, 30, 30, 30));
	}

	private void initializeAssignUserPage(AssignUserPageVariables assignUserPageVariables) {
		setBookedPCTableData(assignUserPageVariables);

		assignUserPageVariables.borderPane = new BorderPane();
		assignUserPageVariables.gridPane = new GridPane();
		assignUserPageVariables.titleVB = new VBox();
		assignUserPageVariables.contentVB = new VBox();

		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		assignUserPageVariables.borderPane.setTop(menuBar);

		assignUserPageVariables.titleLabel = new Label("ASSIGN USER TO ANOTHER PC");

		assignUserPageVariables.bookIDLabel = new Label("Book ID");
		assignUserPageVariables.bookIDTF = new TextField();

		assignUserPageVariables.pcIDLabel = new Label("PC ID");
		assignUserPageVariables.pcIDTF = new TextField();

		assignUserPageVariables.updateButton = new Button("Update");

		assignUserPageVariables.titleVB.getChildren().add(assignUserPageVariables.titleLabel);
		assignUserPageVariables.titleVB.setAlignment(Pos.CENTER);

		assignUserPageVariables.contentVB.getChildren().addAll(assignUserPageVariables.bookIDLabel,
				assignUserPageVariables.bookIDTF, assignUserPageVariables.pcIDLabel, assignUserPageVariables.pcIDTF,
				assignUserPageVariables.updateButton);
		assignUserPageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		assignUserPageVariables.contentVB.setSpacing(13);

		assignUserPageVariables.gridPane.add(assignUserPageVariables.titleVB, 0, 0);
		assignUserPageVariables.gridPane.add(assignUserPageVariables.tableVB, 0, 1);
		assignUserPageVariables.gridPane.add(assignUserPageVariables.contentVB, 0, 2);

		assignUserPageVariables.borderPane.setCenter(assignUserPageVariables.gridPane);

		assignUserPageVariables.scene = new Scene(assignUserPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(AssignUserPageVariables assignUserPageVariables) {
		assignUserPageVariables.alert1 = new Alert(AlertType.ERROR);

		assignUserPageVariables.alert1.setTitle("Error");
		assignUserPageVariables.alert1.setContentText("Book ID Doesnt Exists !!!");
		
		assignUserPageVariables.alert2 = new Alert(AlertType.ERROR);

		assignUserPageVariables.alert2.setTitle("Error");
		assignUserPageVariables.alert2.setContentText("Book ID Is Booked Today !!!");
		
		assignUserPageVariables.alert3 = new Alert(AlertType.ERROR);
		
		assignUserPageVariables.alert3.setTitle("Error");
		assignUserPageVariables.alert3.setContentText("PC ID Does Not Exists !!!");
	}

	private void setStyle(AssignUserPageVariables assignUserPageVariables) {
		assignUserPageVariables.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		assignUserPageVariables.bookedPCTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	private void assignUserHandler(AssignUserPageVariables assignUserPageVariables) {
		pcBookController.assignUserHandling(assignUserPageVariables);
	}

	public Scene startAssignUserPage() {
		AssignUserPageVariables assignUserPageVariables = new AssignUserPageVariables();

		initializeAssignUserPage(assignUserPageVariables);
		setStyle(assignUserPageVariables);
		assignUserHandler(assignUserPageVariables);
		initializeAlert(assignUserPageVariables);

		Main.stage.setTitle("Assign User To Another PC Page");

		return assignUserPageVariables.scene;
	}
}
