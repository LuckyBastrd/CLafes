package view;

import java.util.ArrayList;

import controller.JobController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Job;
import model.User;

public class JobManagement {
	
	public class JobManagementPageVariables {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, jobTableVB, contentVB;
		TableView<Job> jobTable;
	}
	
	private void setJobsTableData(JobManagementPageVariables jobManagementPageVariables, User user) {
		jobManagementPageVariables.jobTable = new TableView<>();
		jobManagementPageVariables.jobTableVB = new VBox();
		
		TableColumn<Job, Integer> jobIDColumn = new TableColumn<>("Job ID");
		TableColumn<Job, Integer> userIDColumn = new TableColumn<>("User ID");
		TableColumn<Job, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		
		jobManagementPageVariables.jobTable.getColumns().addAll(jobIDColumn, userIDColumn, pcIDColumn, jobStatusColumn);
		
		JobController jobController = new JobController();
		
		ArrayList<Job> jobList = jobController.getAllJobData();
		
		for (Job job : jobList) {
			jobManagementPageVariables.jobTable.getItems().add(job);
		}
		
		jobIDColumn.setCellValueFactory(new PropertyValueFactory<>("jobID"));
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobstatus"));
		
		jobManagementPageVariables.jobTable.setMinHeight(50);
		jobIDColumn.setMinWidth(100);
		userIDColumn.setMinWidth(100);
		pcIDColumn.setMinWidth(100);
		jobStatusColumn.setMinWidth(100);
		
		jobManagementPageVariables.jobTableVB.getChildren().add(jobManagementPageVariables.jobTable);
		
		jobManagementPageVariables.jobTableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeJobListPage(JobManagementPageVariables jobManagementPageVariables, User user) {
		setJobsTableData(jobManagementPageVariables, user);
		
		jobManagementPageVariables.borderPane = new BorderPane();
		
		jobManagementPageVariables.borderPane.setTop(jobManagementPageVariables.jobTableVB);
		
		jobManagementPageVariables.scene = new Scene(jobManagementPageVariables.borderPane, 1280, 720);
	}
	
	
	public JobManagement(Stage stage, User user) {
		JobManagementPageVariables jobManagementPageVariables = new JobManagementPageVariables();
		initializeJobListPage(jobManagementPageVariables, user);
		
		jobManagementPageVariables.stage = stage;
		jobManagementPageVariables.stage.setResizable(false);
		jobManagementPageVariables.stage.setScene(jobManagementPageVariables.scene);
		jobManagementPageVariables.stage.show();
	}
}
