package view;

import controller.ReportController;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import view.ManageStaffPage.ManageStaffPageVariables;

public class MakeReportPage {
	
	public class MakeReportPageVariables {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, contentVB;
		Label titleLabel, pcIDLabel, reportNoteLabel;
		public TextField pcIDTF, reportNoteTF;
		Button reportButton;
		public Alert alert1, alert2;
	}
	
	private void initializeMakeReportPage(MakeReportPageVariables makeReportPageVariables, Stage stage,User user) {
		makeReportPageVariables.borderPane = new BorderPane();
		makeReportPageVariables.titleVB = new VBox();
		makeReportPageVariables.contentVB = new VBox();
		makeReportPageVariables.gridPane = new GridPane();
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar(stage, user);
		makeReportPageVariables.borderPane.setTop(menuBar);
		
		makeReportPageVariables.titleLabel = new Label("MAKE REPORT");
		
		makeReportPageVariables.pcIDLabel = new Label("PC ID");
		makeReportPageVariables.pcIDTF = new TextField();

		makeReportPageVariables.reportNoteLabel = new Label("Report Note");
		makeReportPageVariables.reportNoteTF = new TextField();
		
		makeReportPageVariables.reportButton = new Button("Make Report");
		
		makeReportPageVariables.titleVB.getChildren().add(makeReportPageVariables.titleLabel);
		makeReportPageVariables.titleVB.setAlignment(Pos.CENTER);
		
		makeReportPageVariables.contentVB.getChildren().addAll(makeReportPageVariables.pcIDLabel,
				makeReportPageVariables.pcIDTF, makeReportPageVariables.reportNoteLabel,
				makeReportPageVariables.reportNoteTF, makeReportPageVariables.reportButton);
		makeReportPageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		makeReportPageVariables.contentVB.setSpacing(13);
		
		makeReportPageVariables.gridPane.add(makeReportPageVariables.titleVB, 0, 0);
		makeReportPageVariables.gridPane.add(makeReportPageVariables.contentVB, 0, 1);
		
		makeReportPageVariables.borderPane.setCenter(makeReportPageVariables.gridPane);

		makeReportPageVariables.scene = new Scene(makeReportPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(MakeReportPageVariables makeReportPageVariables) {
		makeReportPageVariables.alert1 = new Alert(AlertType.ERROR);

		makeReportPageVariables.alert1.setTitle("Error");
		makeReportPageVariables.alert1.setContentText("PC ID Doesn't Exist !!!");
		
		makeReportPageVariables.alert2 = new Alert(AlertType.ERROR);

		makeReportPageVariables.alert2.setTitle("Error");
		makeReportPageVariables.alert2.setContentText("Report Note Cannot Be Empty!!!");
	}
	
	private void MakeReportHandler(MakeReportPageVariables makeReportPageVariables, Stage stage, User user) {
		ReportController reportController = new ReportController();
		
		makeReportPageVariables.reportButton.setOnAction(e -> {
			reportController.handlingMakeReport(makeReportPageVariables, user);
			new MakeReportPage(stage, user);
		});
	}
	
	private void setStyle(MakeReportPageVariables makeReportPageVariables) {
		makeReportPageVariables.titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
	}
	
	public MakeReportPage(Stage stage, User user) {
		MakeReportPageVariables makeReportPageVariables = new MakeReportPageVariables();
		initializeMakeReportPage(makeReportPageVariables, stage, user);
		initializeAlert(makeReportPageVariables);
		MakeReportHandler(makeReportPageVariables, stage, user);
		setStyle(makeReportPageVariables);
		
		makeReportPageVariables.stage = stage;
		makeReportPageVariables.stage.setResizable(false);
		makeReportPageVariables.stage.setScene(makeReportPageVariables.scene);
		makeReportPageVariables.stage.show();
	}
}
