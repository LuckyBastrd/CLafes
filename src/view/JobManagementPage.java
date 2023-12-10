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

public class JobManagementPage {
	
	public class JobManagementPageVariables {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, allJobTableVB, contentVB;
		TableView<Job> allJobTable;
		Label titleLabel, pcIDLabel, userIDLabel, jobStatusLabel;
		public TextField pcIDTF, userIDTF, jobStatusTF;
		Button addJobButton;
		public Alert alert1, alert2, alert3;
	}
	
	private void setJobTableData(JobManagementPageVariables jobManagementPageVariables, User user) {
		jobManagementPageVariables.allJobTable = new TableView<>();
		jobManagementPageVariables.allJobTableVB = new VBox();
		
		TableColumn<Job, Integer> jobIDColumn = new TableColumn<>("Job ID");
		TableColumn<Job, Integer> userIDColumn = new TableColumn<>("User ID");
		TableColumn<Job, Integer> pcIDColumn = new TableColumn<>("PC ID");
		TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
		
		jobManagementPageVariables.allJobTable.getColumns().addAll(jobIDColumn, userIDColumn, pcIDColumn, jobStatusColumn);
		
		JobController jobController = new JobController();
		
		ArrayList<Job> allJobList = jobController.getTechJobDataHandling(user);
		
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
	
	private void initializeJobListPage(JobManagementPageVariables jobManagementPageVariables, Stage stage, User user) {
		setJobTableData(jobManagementPageVariables, user);
		
		jobManagementPageVariables.borderPane = new BorderPane();
		jobManagementPageVariables.titleVB = new VBox();
		jobManagementPageVariables.contentVB = new VBox();
		jobManagementPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar(stage, user);
		jobManagementPageVariables.borderPane.setTop(menuBar);
		
		jobManagementPageVariables.titleLabel = new Label("JOB MANAGEMENT");
		
		jobManagementPageVariables.userIDLabel = new Label("User ID");
		jobManagementPageVariables.userIDTF = new TextField();
		
		jobManagementPageVariables.pcIDLabel = new Label("PC ID");
		jobManagementPageVariables.pcIDTF = new TextField();
		
		jobManagementPageVariables.addJobButton = new Button("Add Job");
		
		jobManagementPageVariables.titleVB.getChildren().add(jobManagementPageVariables.titleLabel);
		jobManagementPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		jobManagementPageVariables.contentVB.getChildren().addAll(jobManagementPageVariables.userIDLabel,
				jobManagementPageVariables.userIDTF, jobManagementPageVariables.pcIDLabel,
				jobManagementPageVariables.pcIDTF, jobManagementPageVariables.addJobButton);
		jobManagementPageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		jobManagementPageVariables.contentVB.setSpacing(13);
		
		jobManagementPageVariables.gridPane.add(jobManagementPageVariables.titleVB, 0, 0);
		jobManagementPageVariables.gridPane.add(jobManagementPageVariables.allJobTableVB, 0, 1);
		jobManagementPageVariables.gridPane.add(jobManagementPageVariables.contentVB, 0, 2);
		
		jobManagementPageVariables.borderPane.setCenter(jobManagementPageVariables.gridPane);
		
		jobManagementPageVariables.scene = new Scene(jobManagementPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.alert1 = new Alert(AlertType.ERROR);

		jobManagementPageVariables.alert1.setTitle("Error");
		jobManagementPageVariables.alert1.setContentText("User ID Not Computer Technician !!!");
		
		jobManagementPageVariables.alert2 = new Alert(AlertType.ERROR);

		jobManagementPageVariables.alert2.setTitle("Error");
		jobManagementPageVariables.alert2.setContentText("PC ID Doesn't Exist !!!");
		
		jobManagementPageVariables.alert3 = new Alert(AlertType.ERROR);

		jobManagementPageVariables.alert3.setTitle("Error");
		jobManagementPageVariables.alert3.setContentText("Other Technician Already Doing This Job !!!");
	}
	
	private void addJobHandler(JobManagementPageVariables jobManagementPageVariables, Stage stage, User user) {
		JobController jobController = new JobController();
		
		jobManagementPageVariables.addJobButton.setOnAction(e -> {
			jobController.addStaffJobHandling(jobManagementPageVariables);;
			new JobManagementPage(stage, user);
		});
	}
	
	private void setStyle(JobManagementPageVariables jobManagementPageVariables) {
		jobManagementPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		jobManagementPageVariables.allJobTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	
	public JobManagementPage(Stage stage, User user) {
		JobManagementPageVariables jobManagementPageVariables = new JobManagementPageVariables();
		initializeJobListPage(jobManagementPageVariables, stage, user);
		initializeAlert(jobManagementPageVariables);
		addJobHandler(jobManagementPageVariables, stage, user);
		setStyle(jobManagementPageVariables);
		
		jobManagementPageVariables.stage = stage;
		jobManagementPageVariables.stage.setResizable(false);
		jobManagementPageVariables.stage.setScene(jobManagementPageVariables.scene);
		jobManagementPageVariables.stage.show();
	}
}
