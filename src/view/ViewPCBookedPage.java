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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PCBook;

public class ViewPCBookedPage {

	PCBookController pcBookController = new PCBookController();

	public class ViewPCBookedPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, tableVB, contentVB1, contentVB2;
		HBox mainContenctHB;
		TableView<PCBook> bookedPCTable;
		Label titleLabel, subTitleLabel, 
		cancelBookTitleLabel, cancelBookIDLabel,
		finishBookTitleLabel, finishBookDateLabel;
		public TextField cancelBookIDTF;
		public DatePicker bookDateDP;
		public Button cancelButton, finishButton;
		public Alert alert1, alert2, alert3, alert4;
	}

	private void setBookedPCTableData(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		viewPCBookedPageVariables.bookedPCTable = new TableView<>();
		viewPCBookedPageVariables.tableVB = new VBox();

		viewPCBookedPageVariables.subTitleLabel = new Label("All Booked PC on Internet CLafe");
		viewPCBookedPageVariables.subTitleLabel
				.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 15px;");

		TableColumn<PCBook, Integer> bookIDColumn = new TableColumn<>("Book ID");
		TableColumn<PCBook, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<PCBook, Integer> userIDColumn = new TableColumn<>("User ID");
		TableColumn<PCBook, Date> bookedDateColumn = new TableColumn<>("Boked Date");

		viewPCBookedPageVariables.bookedPCTable.getColumns().addAll(bookIDColumn, pcIDColumn, userIDColumn,
				bookedDateColumn);

		ArrayList<PCBook> bookedPCList = pcBookController.getBookedPCDataHandling();

		for (PCBook pcBook : bookedPCList) {
			viewPCBookedPageVariables.bookedPCTable.getItems().add(pcBook);
		}

		bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
		bookedDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookedDate"));

		viewPCBookedPageVariables.bookedPCTable.setMaxHeight(270);
		bookIDColumn.setMinWidth(20);
		pcIDColumn.setMinWidth(20);
		userIDColumn.setMinWidth(20);
		bookedDateColumn.setMinWidth(970);

		viewPCBookedPageVariables.tableVB.getChildren().addAll(viewPCBookedPageVariables.subTitleLabel,
				viewPCBookedPageVariables.bookedPCTable);

		viewPCBookedPageVariables.tableVB.setPadding(new Insets(20, 30, 30, 30));
	}

	private void initializeViewPCBookedPage(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		setBookedPCTableData(viewPCBookedPageVariables);

		viewPCBookedPageVariables.borderPane = new BorderPane();
		viewPCBookedPageVariables.titleVB = new VBox();
		viewPCBookedPageVariables.contentVB1 = new VBox();
		viewPCBookedPageVariables.contentVB2 = new VBox();
		viewPCBookedPageVariables.mainContenctHB = new HBox();
		viewPCBookedPageVariables.gridPane = new GridPane();

		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		viewPCBookedPageVariables.borderPane.setTop(menuBar);

		viewPCBookedPageVariables.titleLabel = new Label("BOOKED PC");

		// Cancel Book
		viewPCBookedPageVariables.cancelBookTitleLabel = new Label("Cancel Book");
		viewPCBookedPageVariables.cancelBookTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");

		viewPCBookedPageVariables.cancelBookIDLabel = new Label("Book ID");
		viewPCBookedPageVariables.cancelBookIDTF = new TextField();

		viewPCBookedPageVariables.cancelButton = new Button("Cancel Book");

		// Finish Book
		viewPCBookedPageVariables.finishBookTitleLabel = new Label("Finish Book");
		viewPCBookedPageVariables.finishBookTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");

		viewPCBookedPageVariables.finishBookDateLabel = new Label("Transaction Date");
		viewPCBookedPageVariables.bookDateDP = new DatePicker();

		viewPCBookedPageVariables.finishButton = new Button("Finish Book");
		
		viewPCBookedPageVariables.titleVB.getChildren().add(viewPCBookedPageVariables.titleLabel);
		viewPCBookedPageVariables.titleVB.setAlignment(Pos.CENTER);

		viewPCBookedPageVariables.contentVB1.getChildren().addAll(viewPCBookedPageVariables.cancelBookTitleLabel,
				viewPCBookedPageVariables.cancelBookIDLabel, viewPCBookedPageVariables.cancelBookIDTF, viewPCBookedPageVariables.cancelButton);
		viewPCBookedPageVariables.contentVB1.setPadding(new Insets(0, 130, 0, 0));
		viewPCBookedPageVariables.contentVB1.setSpacing(13);
		
		viewPCBookedPageVariables.contentVB2.getChildren().addAll(viewPCBookedPageVariables.finishBookTitleLabel, viewPCBookedPageVariables.finishBookDateLabel,
				viewPCBookedPageVariables.bookDateDP, viewPCBookedPageVariables.finishButton);
		viewPCBookedPageVariables.contentVB2.setSpacing(13);
		
		viewPCBookedPageVariables.mainContenctHB.getChildren().addAll(viewPCBookedPageVariables.contentVB1, viewPCBookedPageVariables.contentVB2);
		viewPCBookedPageVariables.mainContenctHB.setPadding(new Insets(0, 0, 0, 30));
		
		viewPCBookedPageVariables.gridPane.add(viewPCBookedPageVariables.titleVB, 0, 0);
		viewPCBookedPageVariables.gridPane.add(viewPCBookedPageVariables.tableVB, 0, 1);
		viewPCBookedPageVariables.gridPane.add(viewPCBookedPageVariables.mainContenctHB, 0, 2);
		
		viewPCBookedPageVariables.borderPane.setCenter(viewPCBookedPageVariables.gridPane);
		
		viewPCBookedPageVariables.scene = new Scene(viewPCBookedPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		viewPCBookedPageVariables.alert1 = new Alert(AlertType.ERROR);

		viewPCBookedPageVariables.alert1.setTitle("Error");
		viewPCBookedPageVariables.alert1.setContentText("Book ID Does Not Exist !!!");
		
		viewPCBookedPageVariables.alert2 = new Alert(AlertType.ERROR);

		viewPCBookedPageVariables.alert2.setTitle("Error");
		viewPCBookedPageVariables.alert2.setContentText("The Date For The Book With ID [Book ID] Has Already Passed !!!");
		
		viewPCBookedPageVariables.alert3 = new Alert(AlertType.ERROR);
		
		viewPCBookedPageVariables.alert3.setTitle("Error");
		viewPCBookedPageVariables.alert3.setContentText("Date Cannot be Empty !!!");
		
		viewPCBookedPageVariables.alert4 = new Alert(AlertType.ERROR);
		
		viewPCBookedPageVariables.alert4.setTitle("Error");
		viewPCBookedPageVariables.alert4.setContentText("The Date Has Not Passed !!!");
	}
	
	private void bookButtonHandler(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		pcBookController.cancelBookHandling(viewPCBookedPageVariables);
		
		pcBookController.finishBookHandling(viewPCBookedPageVariables);
	}
	
	private void setStyle(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		viewPCBookedPageVariables.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		viewPCBookedPageVariables.bookedPCTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public Scene startViewPCBookedPage() {
		ViewPCBookedPageVariables viewPCBookedPageVariables = new ViewPCBookedPageVariables();

		initializeViewPCBookedPage(viewPCBookedPageVariables);
		setStyle(viewPCBookedPageVariables);
		bookButtonHandler(viewPCBookedPageVariables);
		initializeAlert(viewPCBookedPageVariables);

		Main.stage.setTitle("Assign User To Another PC Page");

		return viewPCBookedPageVariables.scene;
	}
}
