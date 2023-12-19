package view;

import java.util.ArrayList;

import app.Main;
import controller.PCController;
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
import model.PC;
import view.JobManagementPage.JobManagementPageVariables;

public class PCManagementPage {

	PCController pcController = new PCController();

	public class PCManagementPageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, tableVB, contentVB1, contentVB2, contentVB3;
		HBox mainContenctHB;
		TableView<PC> allPCTable;
		Label titleLabel, subTitleLabel, addPCTitleLabel, addPCIDLabel, updatePCTitleLabel, updatePCIDLabel,
				updatePCConditionLabel, deletePCTitleLabel, deletePCIDLabel;
		public TextField addPCIDTF, updatePCIDTF, updatePCConditionTF, deletePCIDTF;
		public Button addPCButton, updatePCButton, deletePCButton;
		public Alert alert1, alert2, alert3, alert4, alert5;
	}

	private void setPCTableData(PCManagementPageVariables pcManagementPageVariables) {
		pcManagementPageVariables.allPCTable = new TableView<>();
		pcManagementPageVariables.tableVB = new VBox();

		pcManagementPageVariables.subTitleLabel = new Label("All PC on Internet CLafe");
		pcManagementPageVariables.subTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 15px;");

		TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");

		pcManagementPageVariables.allPCTable.getColumns().addAll(pcIDColumn, pcConditionColumn);

		ArrayList<PC> allPCList = pcController.getAllPCDataHandling();

		for (PC pc : allPCList) {
			pcManagementPageVariables.allPCTable.getItems().add(pc);
		}

		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));

		pcManagementPageVariables.allPCTable.setMaxHeight(270);
		pcIDColumn.setMinWidth(600);
		pcConditionColumn.setMinWidth(600);

		pcManagementPageVariables.tableVB.getChildren().addAll(pcManagementPageVariables.subTitleLabel,
				pcManagementPageVariables.allPCTable);

		pcManagementPageVariables.tableVB.setPadding(new Insets(20, 30, 30, 30));
	}

	private void initializePCManagementPage(PCManagementPageVariables pcManagementPageVariables) {
		setPCTableData(pcManagementPageVariables);

		pcManagementPageVariables.borderPane = new BorderPane();
		pcManagementPageVariables.titleVB = new VBox();
		pcManagementPageVariables.contentVB1 = new VBox();
		pcManagementPageVariables.contentVB2 = new VBox();
		pcManagementPageVariables.contentVB3 = new VBox();
		pcManagementPageVariables.mainContenctHB = new HBox();
		pcManagementPageVariables.gridPane = new GridPane();

		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		pcManagementPageVariables.borderPane.setTop(menuBar);

		pcManagementPageVariables.titleLabel = new Label("PC MANAGEMENT");

		// Add PC Section
		pcManagementPageVariables.addPCTitleLabel = new Label("Add PC");
		pcManagementPageVariables.addPCTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");

		pcManagementPageVariables.addPCIDLabel = new Label("PC ID");
		pcManagementPageVariables.addPCIDTF = new TextField();

		pcManagementPageVariables.addPCButton = new Button("Add PC");

		// Update PC Section
		pcManagementPageVariables.updatePCTitleLabel = new Label("Update PC");
		pcManagementPageVariables.updatePCTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");

		pcManagementPageVariables.updatePCIDLabel = new Label("PC ID");
		pcManagementPageVariables.updatePCIDTF = new TextField();

		pcManagementPageVariables.updatePCConditionLabel = new Label("PC Condition");
		pcManagementPageVariables.updatePCConditionTF = new TextField();

		pcManagementPageVariables.updatePCButton = new Button("Update PC");

		// Delete PC Section
		pcManagementPageVariables.deletePCTitleLabel = new Label("Delete PC");
		pcManagementPageVariables.deletePCTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 20px;");

		pcManagementPageVariables.deletePCIDLabel = new Label("PC ID");
		pcManagementPageVariables.deletePCIDTF = new TextField();

		pcManagementPageVariables.deletePCButton = new Button("Delete PC");

		pcManagementPageVariables.titleVB.getChildren().add(pcManagementPageVariables.titleLabel);
		pcManagementPageVariables.titleVB.setAlignment(Pos.CENTER);

		pcManagementPageVariables.contentVB1.getChildren().addAll(pcManagementPageVariables.addPCTitleLabel,
				pcManagementPageVariables.addPCIDLabel, pcManagementPageVariables.addPCIDTF,
				pcManagementPageVariables.addPCButton);
		pcManagementPageVariables.contentVB1.setPadding(new Insets(0, 130, 0, 0));
		pcManagementPageVariables.contentVB1.setSpacing(13);

		pcManagementPageVariables.contentVB2.getChildren().addAll(pcManagementPageVariables.updatePCTitleLabel,
				pcManagementPageVariables.updatePCIDLabel, pcManagementPageVariables.updatePCIDTF,
				pcManagementPageVariables.updatePCConditionLabel, pcManagementPageVariables.updatePCConditionTF,
				pcManagementPageVariables.updatePCButton);
		pcManagementPageVariables.contentVB2.setSpacing(13);

		pcManagementPageVariables.contentVB3.getChildren().addAll(pcManagementPageVariables.deletePCTitleLabel,
				pcManagementPageVariables.deletePCIDLabel, pcManagementPageVariables.deletePCIDTF,
				pcManagementPageVariables.deletePCButton);
		pcManagementPageVariables.contentVB3.setPadding(new Insets(0, 0, 0, 130));
		pcManagementPageVariables.contentVB3.setSpacing(13);

		pcManagementPageVariables.mainContenctHB.getChildren().addAll(pcManagementPageVariables.contentVB1,
				pcManagementPageVariables.contentVB2, pcManagementPageVariables.contentVB3);
		pcManagementPageVariables.mainContenctHB.setPadding(new Insets(0, 0, 0, 30));

		pcManagementPageVariables.gridPane.add(pcManagementPageVariables.titleVB, 0, 0);
		pcManagementPageVariables.gridPane.add(pcManagementPageVariables.tableVB, 0, 1);
		pcManagementPageVariables.gridPane.add(pcManagementPageVariables.mainContenctHB, 0, 2);

		pcManagementPageVariables.borderPane.setCenter(pcManagementPageVariables.gridPane);

		pcManagementPageVariables.scene = new Scene(pcManagementPageVariables.borderPane, 1280, 720);
	}
	
	private void initializeAlert(PCManagementPageVariables pcManagementPageVariables) {
		pcManagementPageVariables.alert1 = new Alert(AlertType.ERROR);

		pcManagementPageVariables.alert1.setTitle("Error");
		pcManagementPageVariables.alert1.setContentText("PC ID Cannot Be Empty !!!");
		
		pcManagementPageVariables.alert2 = new Alert(AlertType.ERROR);

		pcManagementPageVariables.alert2.setTitle("Error");
		pcManagementPageVariables.alert2.setContentText("PC ID Already Exists !!!");
		
		pcManagementPageVariables.alert3 = new Alert(AlertType.ERROR);
		
		pcManagementPageVariables.alert3.setTitle("Error");
		pcManagementPageVariables.alert3.setContentText("PC ID Does Not Exists !!!");
		
		pcManagementPageVariables.alert4 = new Alert(AlertType.ERROR);

		pcManagementPageVariables.alert4.setTitle("Error");
		pcManagementPageVariables.alert4.setContentText("Invalid PC Condition !!!. Please choose between Usable, Maintenance or Broken(Case Sensitive)");
		
		pcManagementPageVariables.alert5 = new Alert(AlertType.ERROR);
		
		pcManagementPageVariables.alert5.setTitle("Error");
		pcManagementPageVariables.alert5.setContentText("PC ID Is Booked(Cannot Delete) !!!");
	}
	
	private void allButtonHandler(PCManagementPageVariables pcManagementPageVariables) {
		pcController.addPCHandling(pcManagementPageVariables);
		
		pcController.updatePCHandling(pcManagementPageVariables);
		
		pcController.deletePCHandling(pcManagementPageVariables);
	}
	
	private void setStyle(PCManagementPageVariables pcManagementPageVariables) {
		pcManagementPageVariables.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		pcManagementPageVariables.allPCTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	public Scene startPCManagementPageVariables() {
		PCManagementPageVariables pcManagementPageVariables = new PCManagementPageVariables();
		
		initializePCManagementPage(pcManagementPageVariables);
		initializeAlert(pcManagementPageVariables);
		allButtonHandler(pcManagementPageVariables);
		setStyle(pcManagementPageVariables);
		
		Main.stage.setTitle("PC Management Page");
		
		return pcManagementPageVariables.scene;
	}

}
