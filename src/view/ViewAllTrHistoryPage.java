package view;

import java.sql.Date;
import java.util.ArrayList;

import app.Main;
import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Transaction;

public class ViewAllTrHistoryPage {
	
	TransactionController transactionController = new TransactionController();
	
	public class ViewAllTrHistoryPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, trTableVB, contentVB;
		TableView<Transaction> trTable;
		Label titleLabel;
	}

	private void setAllTransactionTableData(ViewAllTrHistoryPageVariables viewAllTrHistoryPageVariables) {
		viewAllTrHistoryPageVariables.trTable = new TableView<>();
		viewAllTrHistoryPageVariables.trTableVB = new VBox();

		TableColumn<Transaction, Integer> trIDColumn = new TableColumn<>("Transaction ID");
		TableColumn<Transaction, Integer> staffIDColumn = new TableColumn<>("Staff ID");
		TableColumn<Transaction, String> staffNameColumn = new TableColumn<>("Staff Name");
		TableColumn<Transaction, Date> trDateColumn = new TableColumn<>("Transaction Date");
		TableColumn<Transaction, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Transaction, String> custNameColumn = new TableColumn<>("Customer Name");
		TableColumn<Transaction, Date> bookDateColumn = new TableColumn<>("Booked Date");

		viewAllTrHistoryPageVariables.trTable.getColumns().addAll(trIDColumn, staffIDColumn, staffNameColumn,
				trDateColumn, pcIDColumn, custNameColumn, bookDateColumn);
		
		ArrayList<Transaction> allTransactionList = transactionController.getTrHistoryDataHandling();
		
		for (Transaction transaction : allTransactionList) {
			viewAllTrHistoryPageVariables.trTable.getItems().add(transaction);
		}
		
		trIDColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		staffIDColumn.setCellValueFactory(new PropertyValueFactory<>("staffID"));
		staffNameColumn.setCellValueFactory(new PropertyValueFactory<>("staffName"));
		trDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactiondate"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		bookDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookeddate"));
		
		viewAllTrHistoryPageVariables.trTable.setMinHeight(70);
		staffNameColumn.setMinWidth(350);
		trDateColumn.setMinWidth(150);
		custNameColumn.setMinWidth(350);
		bookDateColumn.setMinWidth(150);
		
		viewAllTrHistoryPageVariables.trTableVB.getChildren().add(viewAllTrHistoryPageVariables.trTable);

		viewAllTrHistoryPageVariables.trTableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeViewAllTrHistoryPage(ViewAllTrHistoryPageVariables viewAllTrHistoryPageVariables) {
		setAllTransactionTableData(viewAllTrHistoryPageVariables);
		
		viewAllTrHistoryPageVariables.borderPane = new BorderPane();
		viewAllTrHistoryPageVariables.titleVB = new VBox();
		viewAllTrHistoryPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		viewAllTrHistoryPageVariables.borderPane.setTop(menuBar);
		
		viewAllTrHistoryPageVariables.titleLabel = new Label("ALL TRANSACTION HISTORY");
		
		viewAllTrHistoryPageVariables.titleVB.getChildren().add(viewAllTrHistoryPageVariables.titleLabel);
		viewAllTrHistoryPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		viewAllTrHistoryPageVariables.gridPane.add(viewAllTrHistoryPageVariables.titleVB, 0, 0);
		viewAllTrHistoryPageVariables.gridPane.add(viewAllTrHistoryPageVariables.trTableVB, 0, 1);
		
		viewAllTrHistoryPageVariables.borderPane.setCenter(viewAllTrHistoryPageVariables.gridPane);

		viewAllTrHistoryPageVariables.scene = new Scene(viewAllTrHistoryPageVariables.borderPane, 1280, 720);
	}
	
	private void setStyle(ViewAllTrHistoryPageVariables viewAllTrHistoryPageVariables) {
		viewAllTrHistoryPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		viewAllTrHistoryPageVariables.trTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}

	public Scene startViewAllTrHistoryPage() {
		ViewAllTrHistoryPageVariables viewAllTrHistoryPageVariables = new ViewAllTrHistoryPageVariables();
		
		initializeViewAllTrHistoryPage(viewAllTrHistoryPageVariables);
		setStyle(viewAllTrHistoryPageVariables);
		
		Main.stage.setTitle("Transaction History Page");
		
		return viewAllTrHistoryPageVariables.scene;
	}
	
}
