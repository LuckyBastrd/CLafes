package view;

import java.util.ArrayList;

import app.Main;
import controller.PCController;
import database.UserDataSingleton;
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
import model.PC;
import model.User;

public class HomePage {
	
	PCController pcController = new PCController();
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public class HomePageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, tableVB, contentVB;
		TableView<PC> allPCTable;
		Label titleLabel, subTitleLabel, myProfileLabel, userNameLabel, userRoleLabel;
	}
	
	private void setPCTableData(HomePageVariables homePageVariables) {
		homePageVariables.allPCTable = new TableView<>();
		homePageVariables.tableVB = new VBox();
		
		homePageVariables.subTitleLabel = new Label("All PC on Internet CLafe");
		homePageVariables.subTitleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 15px;");
		
		TableColumn<PC, Integer> pcIDColumn = new TableColumn<>("PC_ID");
		TableColumn<PC, String> pcConditionColumn = new TableColumn<>("PC_Condition");
		
		homePageVariables.allPCTable.getColumns().addAll(pcIDColumn, pcConditionColumn);
		
		ArrayList<PC> allPCList = pcController.getAllPCDataHandling();
		
		for (PC pc : allPCList) {
			homePageVariables.allPCTable.getItems().add(pc);
		}
		
		pcIDColumn.setCellValueFactory(new PropertyValueFactory<>("pcID"));
		pcConditionColumn.setCellValueFactory(new PropertyValueFactory<>("pcCondition"));
		
		homePageVariables.allPCTable.setMaxHeight(270);
		pcIDColumn.setMinWidth(600);
		pcConditionColumn.setMinWidth(600);
		
		homePageVariables.tableVB.getChildren().addAll(homePageVariables.subTitleLabel, homePageVariables.allPCTable);
		
		homePageVariables.tableVB.setPadding(new Insets(20, 30, 30, 30));
	}
	
	private void initializeHomePage(HomePageVariables homePageVariables) {
		setPCTableData(homePageVariables);
		
		homePageVariables.borderPane = new BorderPane();
		homePageVariables.gridPane = new GridPane();
		homePageVariables.titleVB = new VBox();
		homePageVariables.contentVB = new VBox();

		//Label
		homePageVariables.titleLabel = new Label("HOME PAGE");
		
		homePageVariables.myProfileLabel = new Label("My Profile");
		homePageVariables.myProfileLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 27px;");
		
		homePageVariables.userNameLabel = new Label("Name : " + currentUser.getUsername());
		homePageVariables.userRoleLabel = new Label("Role   : " + currentUser.getUserrole());
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		homePageVariables.borderPane.setTop(menuBar);
		
		homePageVariables.titleVB.getChildren().add(homePageVariables.titleLabel);
		homePageVariables.titleVB.setAlignment(Pos.CENTER);
		
		homePageVariables.contentVB.getChildren().addAll(homePageVariables.myProfileLabel, homePageVariables.userNameLabel, homePageVariables.userRoleLabel);
		homePageVariables.contentVB.setPadding(new Insets(0, 1000, 0, 30));
		homePageVariables.contentVB.setSpacing(13);
		
		homePageVariables.gridPane.add(homePageVariables.titleVB, 0, 0);
		homePageVariables.gridPane.add(homePageVariables.tableVB, 0, 1);
		homePageVariables.gridPane.add(homePageVariables.contentVB, 0, 2);
		
		homePageVariables.gridPane.setVgap(10);

		homePageVariables.borderPane.setCenter(homePageVariables.gridPane);
		
		homePageVariables.scene = new Scene(homePageVariables.borderPane, 1280, 720);
	}
	
	private void setStyle(HomePageVariables homePageVariables) {
		homePageVariables.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		homePageVariables.contentVB.setStyle("-fx-font-family: Serif;" + "-fx-font-size: 20px;");
		homePageVariables.allPCTable.getColumns().forEach(column -> column.setStyle("-fx-alignment: CENTER;"));
	}
	
	
	public Scene startHomePage() {
		HomePageVariables homePageVariables = new HomePageVariables();
		
		initializeHomePage(homePageVariables); 
		setStyle(homePageVariables);
		
		Main.stage.setTitle("Home Page");
		
		return homePageVariables.scene;
	}
	
}