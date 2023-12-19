package view;

import java.util.ArrayList;

import app.Main;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Job;

public class JobManagementPage {
	
	JobController jobController = new JobController();
	
	public class JobManagementPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, allJobTableVB, contentVB1, contentVB2;
		HBox mainContenctHB;
		TableView<Job> allJobTable;
		Label titleLabel, addJobTitle, updateStatusLabel, pcIDLabel, userIDLabel, pcID2Label, jobStatusLabel;
		public TextField pcIDTF, userIDTF, pcID2TF, jobStatusTF;
		public Button addJobButton, updateStatusButton;
		public Alert alert1, alert2, alert3, alert4, alert5;
	}
	
	private void setJobTableData(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.allJobTable = new TableView<>();
		jobManagementPageVariables.allJobTableVB = new VBox();
		
		TableColumn<Job, Integer> jobIDColumn = new TableColumn<>("Job ID");
		TableColumn<Job, Integer> userIDColumn = new TableColumn<>("User ID");
		TableColumn<Job, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		
		jobManagementPageVariables.allJobTable.getColumns().addAll(jobIDColumn, userIDColumn, pcIDColumn, jobStatusColumn);
		
		ArrayList<Job> allJobList = jobController.getTechJobDataHandling();
		
		for (Job job : allJobList) {
			jobManagementPageVariables.allJobTable.getItems().add(job);
		}
		
		jobIDColumn.setCellValueFactory(new PropertyValueFactory<>("jobID"));
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobstatus"));
		
		jobManagementPageVariables.allJobTable.setMaxHeight(150);
		jobIDColumn.setMinWidth(20);
		userIDColumn.setMinWidth(20);
		pcIDColumn.setMinWidth(20);
		jobStatusColumn.setMinWidth(970);
		
		jobManagementPageVariables.allJobTableVB.getChildren().add(jobManagementPageVariables.allJobTable);
		
		jobManagementPageVariables.allJobTableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeJobListPage(JobManagementPageVariables jobManagementPageVariables) {
		setJobTableData(jobManagementPageVariables);
		
		jobManagementPageVariables.borderPane = new BorderPane();
		jobManagementPageVariables.titleVB = new VBox();
		jobManagementPageVariables.contentVB1 = new VBox();
		jobManagementPageVariables.contentVB2 = new VBox();
		jobManagementPageVariables.mainContenctHB = new HBox();
		jobManagementPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		jobManagementPageVariables.borderPane.setTop(menuBar);
		
		jobManagementPageVariables.titleLabel = new Label("JOB MANAGEMENT");
		
		//Add Job Section
		jobManagementPageVariables.addJobTitle = new Label("Add Job");
		jobManagementPageVariables.addJobTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");
		
		jobManagementPageVariables.userIDLabel = new Label("User ID");
		jobManagementPageVariables.userIDTF = new TextField();
		
		jobManagementPageVariables.pcIDLabel = new Label("PC ID");
		jobManagementPageVariables.pcIDTF = new TextField();
		
		jobManagementPageVariables.addJobButton = new Button("Add Job");
		
		
		//Update Job Status Section
		jobManagementPageVariables.updateStatusLabel = new Label("Update Job Status");
		jobManagementPageVariables.updateStatusLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");
		
		jobManagementPageVariables.pcID2Label = new Label("PC ID");
		jobManagementPageVariables.pcID2TF = new TextField();
		
		jobManagementPageVariables.jobStatusLabel = new Label("Job Status");
		jobManagementPageVariables.jobStatusTF = new TextField();
		
		jobManagementPageVariables.updateStatusButton = new Button("Update");
		
		
		jobManagementPageVariables.titleVB.getChildren().add(jobManagementPageVariables.titleLabel);
		jobManagementPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		jobManagementPageVariables.contentVB1.getChildren().addAll(jobManagementPageVariables.addJobTitle, jobManagementPageVariables.userIDLabel,
				jobManagementPageVariables.userIDTF, jobManagementPageVariables.pcIDLabel,
				jobManagementPageVariables.pcIDTF, jobManagementPageVariables.addJobButton);
		jobManagementPageVariables.contentVB1.setPadding(new Insets(0, 130, 0, 0));
		jobManagementPageVariables.contentVB1.setSpacing(13);
		
		jobManagementPageVariables.contentVB2.getChildren().addAll(jobManagementPageVariables.updateStatusLabel, jobManagementPageVariables.pcID2Label,
				jobManagementPageVariables.pcID2TF, jobManagementPageVariables.jobStatusLabel,
				jobManagementPageVariables.jobStatusTF, jobManagementPageVariables.updateStatusButton);
		jobManagementPageVariables.contentVB2.setSpacing(13);
		
		jobManagementPageVariables.mainContenctHB.getChildren().addAll(jobManagementPageVariables.contentVB1, jobManagementPageVariables.contentVB2);
		jobManagementPageVariables.mainContenctHB.setPadding(new Insets(0, 0, 0, 30));
		
		jobManagementPageVariables.gridPane.add(jobManagementPageVariables.titleVB, 0, 0);
		jobManagementPageVariables.gridPane.add(jobManagementPageVariables.allJobTableVB, 0, 1);
		jobManagementPageVariables.gridPane.add(jobManagementPageVariables.mainContenctHB, 0, 2);
		
		jobManagementPageVariables.borderPane.setCenter(jobManagementPageVariables.gridPane);
		
		jobManagementPageVariables.scene = new Scene(jobManagementPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.alert1 = new Alert(AlertType.ERROR);

		jobManagementPageVariables.alert1.setTitle("Error");
		jobManagementPageVariables.alert1.setContentText("User ID Not Computer Technician !!!");
		
		jobManagementPageVariables.alert2 = new Alert(AlertType.ERROR);

		jobManagementPageVariables.alert2.setTitle("Error");
		jobManagementPageVariables.alert2.setContentText("PC ID Is In Usable Condition or Is Not Reported Yet !!! (Please Check It First)");
		
		jobManagementPageVariables.alert3 = new Alert(AlertType.ERROR);
		
		jobManagementPageVariables.alert3.setTitle("Error");
		jobManagementPageVariables.alert3.setContentText("Other Technician Already Doing This Job !!!");
		
		jobManagementPageVariables.alert4 = new Alert(AlertType.ERROR);

		jobManagementPageVariables.alert4.setTitle("Error");
		jobManagementPageVariables.alert4.setContentText("PC ID Does Not Exist In The Job List !!!");
		
		jobManagementPageVariables.alert5 = new Alert(AlertType.ERROR);
		
		jobManagementPageVariables.alert5.setTitle("Error");
		jobManagementPageVariables.alert5.setContentText("Invalid job status !!!. Please choose between Complete or UnComplete(Case Sensitive)");
	}
	
	private void updateJobStatusHandler(JobManagementPageVariables jobManagementPageVariables) {
		jobController.addStaffJobHandling(jobManagementPageVariables);
		
		jobController.amdinUpdateJobStatusHandling(jobManagementPageVariables);
	}
	
	private void setStyle(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		jobManagementPageVariables.addJobTitle.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");
		jobManagementPageVariables.updateStatusLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");
		jobManagementPageVariables.allJobTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public Scene startJobManagementPage() {
		JobManagementPageVariables jobManagementPageVariables = new JobManagementPageVariables();
		
		initializeJobListPage(jobManagementPageVariables);
		initializeAlert(jobManagementPageVariables);
		updateJobStatusHandler(jobManagementPageVariables);
		setStyle(jobManagementPageVariables);
		
		Main.stage.setTitle("Jog Management Page");
		
		return jobManagementPageVariables.scene;
	}
}
