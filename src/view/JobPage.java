package view;

import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Job;

public class JobPage extends Application {
	
	public class JobVariable {
		TableView<Job> tableView;
	}
	
	private void initializeJobPage(JobVariable jobVariable) {
		jobVariable.tableView = new TableView<>();
		
		// Table Columns
        TableColumn<Job, String> pcIDColumn = new TableColumn<>("PC ID");
        

        TableColumn<Job, String> jobStatusColumn = new TableColumn<>("Job Status");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
