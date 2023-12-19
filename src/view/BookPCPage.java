package view;

import java.util.ArrayList;

import app.Main;
import controller.PCBookController;
import controller.PCController;
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
import javafx.scene.layout.VBox;
import model.PC;


public class BookPCPage {
	
	PCController pcController = new PCController();
	PCBookController pcBookController = new PCBookController();
	
	public class BookPCPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, tableVB, contentVB;
		TableView<PC> allPCTable;
		Label titleLabel, subTitleLabel, pcIDLabel, bookDateLabel;
		public TextField pcIDTF;
		public DatePicker bookDateDP;
		public Button bookPCButton;
		public Alert alert1, alert2;
	}
	
	private void setPCTableData(BookPCPageVariables bookPCPageVariables) {
		bookPCPageVariables.allPCTable = new TableView<>();
		bookPCPageVariables.tableVB = new VBox();
		
		bookPCPageVariables.subTitleLabel = new Label("All PC on Internet CLafe");
		bookPCPageVariables.subTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 15px;");
		
		TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");
		
		bookPCPageVariables.allPCTable.getColumns().addAll(pcIDColumn, pcConditionColumn);
		
		ArrayList<PC> allPCList = pcController.getAllPCDataHandling();
		
		for (PC pc : allPCList) {
			bookPCPageVariables.allPCTable.getItems().add(pc);
		}
		
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));
		
		bookPCPageVariables.allPCTable.setMaxHeight(270);
		pcIDColumn.setMinWidth(600);
		pcConditionColumn.setMinWidth(600);
		
		bookPCPageVariables.tableVB.getChildren().addAll(bookPCPageVariables.subTitleLabel, bookPCPageVariables.allPCTable);
		
		bookPCPageVariables.tableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeBookPCPage(BookPCPageVariables bookPCPageVariables) {
		setPCTableData(bookPCPageVariables);
		
		bookPCPageVariables.borderPane = new BorderPane();
		bookPCPageVariables.gridPane = new GridPane();
		bookPCPageVariables.titleVB = new VBox();
		bookPCPageVariables.contentVB = new VBox();

		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		bookPCPageVariables.borderPane.setTop(menuBar);
		
		bookPCPageVariables.titleLabel = new Label("BOOK PC");
		
		bookPCPageVariables.pcIDLabel = new Label("PC ID");
		bookPCPageVariables.pcIDTF = new TextField();
		
		bookPCPageVariables.bookDateLabel = new Label("Book Date");
		bookPCPageVariables.bookDateDP = new DatePicker();
		
		bookPCPageVariables.bookPCButton = new Button("Book PC");
		
		bookPCPageVariables.titleVB.getChildren().add(bookPCPageVariables.titleLabel);
		bookPCPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		bookPCPageVariables.contentVB.getChildren().addAll(bookPCPageVariables.pcIDLabel,
				bookPCPageVariables.pcIDTF, bookPCPageVariables.bookDateLabel,
				bookPCPageVariables.bookDateDP, bookPCPageVariables.bookPCButton);
		bookPCPageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		bookPCPageVariables.contentVB.setSpacing(13);
		
		bookPCPageVariables.gridPane.add(bookPCPageVariables.titleVB, 0, 0);
		bookPCPageVariables.gridPane.add(bookPCPageVariables.tableVB, 0, 1);
		bookPCPageVariables.gridPane.add(bookPCPageVariables.contentVB, 0, 2);
		
		bookPCPageVariables.borderPane.setCenter(bookPCPageVariables.gridPane);
		
		bookPCPageVariables.scene = new Scene(bookPCPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(BookPCPageVariables bookPCPageVariables) {
		bookPCPageVariables.alert1 = new Alert(AlertType.ERROR);

		bookPCPageVariables.alert1.setTitle("Error");
		bookPCPageVariables.alert1.setContentText("PC ID Does Not Exist !!!");
		
		bookPCPageVariables.alert2 = new Alert(AlertType.ERROR);

		bookPCPageVariables.alert2.setTitle("Error");
		bookPCPageVariables.alert2.setContentText("PC ID Already Booked In Selected Date !!!");
	}
	
	private void BookPCHandler(BookPCPageVariables bookPCPageVariables) {
		pcBookController.bookPCHandling(bookPCPageVariables);
	}
	
	private void setStyle(BookPCPageVariables bookPCPageVariables) {
		bookPCPageVariables.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		bookPCPageVariables.allPCTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public Scene startBookPCPage() {
		BookPCPageVariables bookPCPageVariables = new BookPCPageVariables();
		
		initializeBookPCPage(bookPCPageVariables); 
		setStyle(bookPCPageVariables);
		BookPCHandler(bookPCPageVariables);
		initializeAlert(bookPCPageVariables);
		
		Main.stage.setTitle("Book PC Page");
		
		return bookPCPageVariables.scene;
	}
	
}
