package view;

import java.util.ArrayList;

import controller.JobController;
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
import model.Job;
import model.User;

public class TechnicianJobPage {
	
	public class TechnicianJobPageVariables {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, techJobTableVB, contentVB;
		TableView<Job> techJobTable;
		Label titleLabel, pcIDLabel;
		public TextField pcIDTF;
		public Button completeButton;
		public Alert alert1, alert2;
	}
	
	private void setTechJobTableData(TechnicianJobPageVariables technicianJobPageVariables, User user) {
		technicianJobPageVariables.techJobTable = new TableView<>();
		technicianJobPageVariables.techJobTableVB = new VBox();
		
		TableColumn<Job, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		
		technicianJobPageVariables.techJobTable.getColumns().addAll(pcIDColumn, jobStatusColumn);
		
		JobController jobController = new JobController();
		
		ArrayList<Job> jobList = jobController.getTechJobDataHandling(user);
		
		for (Job job : jobList) {
			technicianJobPageVariables.techJobTable.getItems().add(job);
		}
		
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobstatus"));
		
		technicianJobPageVariables.techJobTable.setMinHeight(50);
		pcIDColumn.setMinWidth(30);
		jobStatusColumn.setMinWidth(1100);
		
		technicianJobPageVariables.techJobTableVB.getChildren().add(technicianJobPageVariables.techJobTable);
		
		technicianJobPageVariables.techJobTableVB.setPadding(new Insets(30, 30, 30, 30));
	}
	
	private void initializeTechJobPage(TechnicianJobPageVariables technicianJobPageVariables, Stage stage, User user) {
		setTechJobTableData(technicianJobPageVariables, user);
		
		technicianJobPageVariables.borderPane = new BorderPane();
		technicianJobPageVariables.titleVB = new VBox();
		technicianJobPageVariables.contentVB = new VBox();
		technicianJobPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar(stage, user);
		technicianJobPageVariables.borderPane.setTop(menuBar);
		
		technicianJobPageVariables.titleLabel = new Label("TECHNICIAN JOB PAGE");
		
		technicianJobPageVariables.pcIDLabel = new Label("PC ID");
		technicianJobPageVariables.pcIDTF = new TextField();
		
		technicianJobPageVariables.completeButton = new Button("Job Complete");
		
		technicianJobPageVariables.titleVB.getChildren().add(technicianJobPageVariables.titleLabel);
		technicianJobPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		technicianJobPageVariables.contentVB.getChildren().addAll(technicianJobPageVariables.pcIDLabel, technicianJobPageVariables.pcIDTF,
																	technicianJobPageVariables.completeButton);
		technicianJobPageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		technicianJobPageVariables.contentVB.setSpacing(13);
		
		technicianJobPageVariables.gridPane.add(technicianJobPageVariables.titleVB, 0, 0);
		technicianJobPageVariables.gridPane.add(technicianJobPageVariables.techJobTableVB, 0, 1);
		technicianJobPageVariables.gridPane.add(technicianJobPageVariables.contentVB, 0, 2);
		
		technicianJobPageVariables.borderPane.setCenter(technicianJobPageVariables.gridPane);
		
		technicianJobPageVariables.scene = new Scene(technicianJobPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(TechnicianJobPageVariables technicianJobPageVariables) {
		technicianJobPageVariables.alert1 = new Alert(AlertType.ERROR);

		technicianJobPageVariables.alert1.setTitle("Error");
		technicianJobPageVariables.alert1.setContentText("PC ID Invalid !!!");
		
		technicianJobPageVariables.alert2 = new Alert(AlertType.ERROR);

		technicianJobPageVariables.alert2.setTitle("Error");
		technicianJobPageVariables.alert2.setContentText("PC ID Already Completed !!!");
	}
	
	private void UpdateJobStatusHandler(TechnicianJobPageVariables technicianJobPageVariables, Stage stage, User user) {
		JobController jobController = new JobController();
		
		technicianJobPageVariables.completeButton.setOnAction(e -> {
			jobController.updateJobStatusHandling(technicianJobPageVariables, user);
			new TechnicianJobPage(stage, user);
		});
	}
	
	private void setStyle(TechnicianJobPageVariables technicianJobPageVariables) {
		technicianJobPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		technicianJobPageVariables.techJobTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public TechnicianJobPage(Stage stage, User user) {
		TechnicianJobPageVariables technicianJobPageVariables = new TechnicianJobPageVariables();
		initializeTechJobPage(technicianJobPageVariables, stage, user);
		initializeAlert(technicianJobPageVariables);
		UpdateJobStatusHandler(technicianJobPageVariables, stage, user);
		setStyle(technicianJobPageVariables);
		
		technicianJobPageVariables.stage = stage;
		technicianJobPageVariables.stage.setResizable(false);
		technicianJobPageVariables.stage.setScene(technicianJobPageVariables.scene);
		technicianJobPageVariables.stage.setTitle("Technician Job Page");
		technicianJobPageVariables.stage.show();
	}
}
