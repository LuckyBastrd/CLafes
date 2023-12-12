package view;

import app.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.User;
import model.UserDataSingleton;

public class HomePage {
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public class HomePageVariables {
		Scene scene;
		BorderPane borderPane;
		GridPane gridPane;
		VBox titleVB, contentVB;
		Label titleLabel, userNameLabel, userRoleLabel;
	}
	
	private void initializeHomePage(HomePageVariables homePageVariables) {
		homePageVariables.borderPane = new BorderPane();
		homePageVariables.gridPane = new GridPane();
		homePageVariables.titleVB = new VBox();
		homePageVariables.contentVB = new VBox();

		//Label
		homePageVariables.titleLabel = new Label("HOME PAGE");
		
		homePageVariables.userNameLabel = new Label("Name : " + currentUser.getUsername());
		homePageVariables.userRoleLabel = new Label("Role : " + currentUser.getUserrole());
		
		MenuBar menuBar = MenuBarBuilder.createMenuBar();
		homePageVariables.borderPane.setTop(menuBar);
		
		homePageVariables.titleVB.getChildren().add(homePageVariables.titleLabel);
		homePageVariables.titleVB.setAlignment(Pos.CENTER);
		
		homePageVariables.contentVB.getChildren().addAll(homePageVariables.userNameLabel, homePageVariables.userRoleLabel);
		homePageVariables.contentVB.setAlignment(Pos.CENTER);
		
		homePageVariables.gridPane.add(homePageVariables.titleVB, 0, 1);
		homePageVariables.gridPane.add(homePageVariables.contentVB, 0, 2);
		
		homePageVariables.gridPane.setVgap(10);
		
		homePageVariables.gridPane.setAlignment(Pos.CENTER);
		
		homePageVariables.borderPane.setCenter(homePageVariables.gridPane);
		
		homePageVariables.scene = new Scene(homePageVariables.borderPane, 1280, 720);
	}
	
	private void setStyle(HomePageVariables homePageVariables) {
		homePageVariables.titleVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
		homePageVariables.contentVB.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Serif;" + "-fx-font-size: 35px;");
	}
	
	
	public Scene startHomePage() {
		HomePageVariables homePageVariables = new HomePageVariables();
		
		initializeHomePage(homePageVariables); 
		setStyle(homePageVariables);
		
		Main.stage.setTitle("Home Page");
		
		return homePageVariables.scene;
	}
	
}