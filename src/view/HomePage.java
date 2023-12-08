package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {
	
	public class HomePageVariable {
		Stage stage;
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, contentVB;
		Label titleLabel, userNameLabel, userRoleLabel;
	}
	
	private void initializeHomePage(HomePageVariable homePageVariable, String username, String userrole) {
		homePageVariable.borderPane = new BorderPane();
		homePageVariable.gridPane = new GridPane();
		homePageVariable.titleVB = new VBox();
		homePageVariable.contentVB = new VBox();

		//Label
		homePageVariable.titleLabel = new Label("HOME PAGE");
		
		homePageVariable.userNameLabel = new Label("Name : " + username);
		homePageVariable.userRoleLabel = new Label("Role : " + userrole);
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar(userrole);
		
		
		homePageVariable.titleVB.getChildren().add(homePageVariable.titleLabel);
		homePageVariable.titleVB.setAlignment(Pos.CENTER);
		
		homePageVariable.contentVB.getChildren().addAll(homePageVariable.userNameLabel, homePageVariable.userRoleLabel);
		homePageVariable.contentVB.setAlignment(Pos.CENTER);
		
		homePageVariable.gridPane.add(homePageVariable.titleVB, 0, 1);
		homePageVariable.gridPane.add(homePageVariable.contentVB, 0, 2);
		
		homePageVariable.gridPane.setVgap(10);
		
		homePageVariable.gridPane.setAlignment(Pos.CENTER);
		
		homePageVariable.borderPane.setTop(menuBar);
		
		homePageVariable.borderPane.setCenter(homePageVariable.gridPane);
		
		homePageVariable.scene = new Scene(homePageVariable.borderPane, 1280, 720);
	}
	
	private void setStyle(HomePageVariable homePageVariable) {
		homePageVariable.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		homePageVariable.contentVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
	}
	
	public HomePage(Stage stage, Integer userID, String username, String userrole) {
		HomePageVariable homePageVariable = new HomePageVariable();
		
		initializeHomePage(homePageVariable, username, userrole); 
		setStyle(homePageVariable);
		
		homePageVariable.stage = stage;
		homePageVariable.stage.setResizable(false);
		homePageVariable.stage.setScene(homePageVariable.scene);
		homePageVariable.stage.show();
	}
}
