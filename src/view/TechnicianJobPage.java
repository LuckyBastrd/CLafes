//package view;
//
//import java.util.ArrayList;
//
//import database.JobModel;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//import model.Job;
//
//public class TechnicianJobPage {
//	
//	public class JobVariable {
//		Stage stage;
//		Scene scene;
//		Label TitleLabel;
//		TableView<Job> tableView;
//	}
//	
//	private void setJobTable(JobVariable jobVariable, Integer userID) {
//		jobVariable.tableView = new TableView<>();
//		
//		// Table Columns
//        TableColumn<Job, Integer> pcIDColumn = new TableColumn<>("PC ID");
//        TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
//        
//        jobVariable.tableView.getColumns().addAll(pcIDColumn, jobStatusColumn);
//        
//        JobModel jobModel = new JobModel();
//        
//        ArrayList<Job> jobList = jobModel.getJobData(userID);
//        
//        for (Job job : jobList) {
//			jobVariable.tableView.getItems().add(job);
//		}
//        
//        pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
//        jobStatusColumn.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));
//        
//        jobVariable.tableView.setMaxHeight(150);
//        pcIDColumn.setMinWidth(200);
//        jobStatusColumn.setPrefWidth(200);
//        
//	}
//	
//	private void initializeTechnicianJobPage(Integer userID) {
//		JobVariable jobVariable = new JobVariable();
//		setJobTable(jobVariable, userID);
//	}
//	
//	public TechnicianJobPage(Stage stage, Integer UserID, String Role) {
//		
//	}
//}
