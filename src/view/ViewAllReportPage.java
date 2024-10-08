package view;

import java.sql.Date;
import java.util.ArrayList;

import app.Main;
import controller.ReportController;
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
import javafx.stage.Stage;
import model.Report;

public class ViewAllReportPage {

	public class ViewAllReportPageVariables {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, tableVB;
		TableView<Report> allReportTable;
		Label titleLabel;
	}
	
	private void setAllReportTableData(ViewAllReportPageVariables viewAllReportPageVariables) {
		viewAllReportPageVariables.allReportTable = new TableView<>();
		viewAllReportPageVariables.tableVB = new VBox();
		
		TableColumn<Report, Integer> reportIDColumn = new TableColumn<>("Job ID");
		TableColumn<Report, String> userRoleColumn = new TableColumn<>("User Role");
		TableColumn<Report, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Report, String> reportNoteColumn = new TableColumn<>("Report Note");
		TableColumn<Report, Date> reportDateColumn = new TableColumn<>("Report Date");
		
		viewAllReportPageVariables.allReportTable.getColumns().addAll(reportIDColumn, userRoleColumn, pcIDColumn, reportNoteColumn, reportDateColumn);
		
		ReportController reportController = new ReportController();
		
		ArrayList<Report> allJobList = reportController.getAllReportDataHandling();
		
		for (Report report : allJobList) {
			viewAllReportPageVariables.allReportTable.getItems().add(report);
		}
	 	
		reportIDColumn.setCellValueFactory(new PropertyValueFactory<>("reportID"));
		userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userrole"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		reportNoteColumn.setCellValueFactory(new PropertyValueFactory<>("reportnote"));
		reportDateColumn.setCellValueFactory(new PropertyValueFactory<>("reportdate"));
			
		viewAllReportPageVariables.tableVB.setMinHeight(50);
	    userRoleColumn.setMinWidth(100);
	    reportNoteColumn.setMinWidth(910);
	    reportDateColumn.setMinWidth(100);
	    
	    viewAllReportPageVariables.tableVB.getChildren().add(viewAllReportPageVariables.allReportTable);
		
	    viewAllReportPageVariables.tableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeViewAllReportPage(ViewAllReportPageVariables viewAllReportPageVariables) {
		setAllReportTableData(viewAllReportPageVariables);
		
		viewAllReportPageVariables.borderPane = new BorderPane();
		viewAllReportPageVariables.titleVB = new VBox();
		viewAllReportPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		viewAllReportPageVariables.borderPane.setTop(menuBar);
		
		viewAllReportPageVariables.titleLabel = new Label("REPORT FROM CUSTOMER & OPERATOR");
		
		viewAllReportPageVariables.titleVB.getChildren().add(viewAllReportPageVariables.titleLabel);
		viewAllReportPageVariables.titleVB.setAlignment(Pos.CENTER);

		viewAllReportPageVariables.gridPane.add(viewAllReportPageVariables.titleVB, 0, 0);
		viewAllReportPageVariables.gridPane.add(viewAllReportPageVariables.tableVB, 0, 1);
		
		viewAllReportPageVariables.borderPane.setCenter(viewAllReportPageVariables.gridPane);

		viewAllReportPageVariables.scene = new Scene(viewAllReportPageVariables.borderPane, 1280, 720);
	}
	
	private void setStyle(ViewAllReportPageVariables viewAllReportPageVariables) {
		viewAllReportPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		viewAllReportPageVariables.allReportTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public Scene startViewAllReportPageVariables() {
		ViewAllReportPageVariables viewAllReportPageVariables = new ViewAllReportPageVariables();
		
		initializeViewAllReportPage(viewAllReportPageVariables);
		setStyle(viewAllReportPageVariables);
		
		Main.stage.setTitle("Report Page");
		
		return viewAllReportPageVariables.scene;
	}
	
}
