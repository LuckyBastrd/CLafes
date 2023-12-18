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
import view.ViewAllTrHistoryPage.ViewAllTrHistoryPageVariables;

public class ViewCustomerTrHistoryPage {
	TransactionController transactionController = new TransactionController();
	
	public class ViewCustomerTrHistoryPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, trTableVB, contentVB;
		TableView<Transaction> trTable;
		Label titleLabel;
	}
	
	private void setCustomerTransactionTableData(ViewCustomerTrHistoryPageVariables viewCustomerTrHistoryPageVariables) {
		viewCustomerTrHistoryPageVariables.trTable = new TableView<>();
		viewCustomerTrHistoryPageVariables.trTableVB = new VBox();

		TableColumn<Transaction, Integer> trIDColumn = new TableColumn<>("Transaction ID");
		TableColumn<Transaction, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Transaction, String> custNameColumn = new TableColumn<>("Customer Name");
		TableColumn<Transaction, Date> bookDateColumn = new TableColumn<>("Booked Date");

		viewCustomerTrHistoryPageVariables.trTable.getColumns().addAll(trIDColumn, pcIDColumn, custNameColumn, bookDateColumn);
		
		ArrayList<Transaction> allTransactionList = transactionController.getTrHistoryDataHandling();
		
		for (Transaction transaction : allTransactionList) {
			viewCustomerTrHistoryPageVariables.trTable.getItems().add(transaction);
		}
		
		trIDColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		bookDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookeddate"));
		
		viewCustomerTrHistoryPageVariables.trTable.setMinHeight(70);
		custNameColumn.setMinWidth(800);
		bookDateColumn.setMinWidth(250);
		
		viewCustomerTrHistoryPageVariables.trTableVB.getChildren().add(viewCustomerTrHistoryPageVariables.trTable);

		viewCustomerTrHistoryPageVariables.trTableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeViewCustomerHistoryPage(ViewCustomerTrHistoryPageVariables viewCustomerTrHistoryPageVariables) {
		setCustomerTransactionTableData(viewCustomerTrHistoryPageVariables);
		
		viewCustomerTrHistoryPageVariables.borderPane = new BorderPane();
		viewCustomerTrHistoryPageVariables.titleVB = new VBox();
		viewCustomerTrHistoryPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		viewCustomerTrHistoryPageVariables.borderPane.setTop(menuBar);
		
		viewCustomerTrHistoryPageVariables.titleLabel = new Label("CUSTOMER TRANSACTION HISTORY");
		
		viewCustomerTrHistoryPageVariables.titleVB.getChildren().add(viewCustomerTrHistoryPageVariables.titleLabel);
		viewCustomerTrHistoryPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		viewCustomerTrHistoryPageVariables.gridPane.add(viewCustomerTrHistoryPageVariables.titleVB, 0, 0);
		viewCustomerTrHistoryPageVariables.gridPane.add(viewCustomerTrHistoryPageVariables.trTableVB, 0, 1);
		
		viewCustomerTrHistoryPageVariables.borderPane.setCenter(viewCustomerTrHistoryPageVariables.gridPane);

		viewCustomerTrHistoryPageVariables.scene = new Scene(viewCustomerTrHistoryPageVariables.borderPane, 1280, 720);
	}
	
	private void setStyle(ViewCustomerTrHistoryPageVariables viewCustomerTrHistoryPageVariables) {
		viewCustomerTrHistoryPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		viewCustomerTrHistoryPageVariables.trTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public Scene startViewCustomerTrHistoryPage() {
		ViewCustomerTrHistoryPageVariables viewCustomerTrHistoryPageVariables = new ViewCustomerTrHistoryPageVariables();
		
		initializeViewCustomerHistoryPage(viewCustomerTrHistoryPageVariables);
		setStyle(viewCustomerTrHistoryPageVariables);
		
		Main.stage.setTitle("Customer Transaction History Page");
		
		return viewCustomerTrHistoryPageVariables.scene;
	}
}
