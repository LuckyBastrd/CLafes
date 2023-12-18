package view;

import app.Main;
import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RegisterPage{
	
	public class RegisterVar{
		Scene scene;
		VBox vb;
		BorderPane bp;
		GridPane gp;
		MenuBar menuBar;
		Menu menu;
		public MenuItem menuItemLogin;
		Label title, usernameLbl, passwordLbl, confirmPassLbl, ageLbl;
		public TextField usernameTf;
		public PasswordField passworPf, confirmPassPf;
		public Spinner<Integer> ageSpin;
		public Button registerSubmit;
		public Alert alert1, alert2, alert3, alert4, alert5, alert6, alert7, alert8, alert9;
	}
	
	private void initializeAlert(RegisterVar registerVar) {
        registerVar.alert1 = new Alert(AlertType.ERROR);
        registerVar.alert1.setTitle("Error");
        registerVar.alert1.setContentText("Please input the username!");
        
        registerVar.alert2 = new Alert(AlertType.ERROR);
        registerVar.alert2.setTitle("Error");
        registerVar.alert2.setContentText("Username must be at least 7 characters");
        
        registerVar.alert3 = new Alert(AlertType.ERROR);
        registerVar.alert3.setTitle("Error");
        registerVar.alert3.setContentText("Username must be unique!");
        
        registerVar.alert4 = new Alert(AlertType.ERROR);
        registerVar.alert4.setTitle("Error");
        registerVar.alert4.setContentText("Please input the password!");
        
        registerVar.alert5 = new Alert(AlertType.ERROR);
        registerVar.alert5.setTitle("Error");
        registerVar.alert5.setContentText("Password must be at least 6 characters!");
        
        registerVar.alert6 = new Alert(AlertType.ERROR);
        registerVar.alert6.setTitle("Error");
        registerVar.alert6.setContentText("Password must contain alphanumeric characters!");
        
        registerVar.alert7 = new Alert(AlertType.ERROR);
        registerVar.alert7.setTitle("Error");
        registerVar.alert7.setContentText("Please input the confirm password!");
        
        registerVar.alert8 = new Alert(AlertType.ERROR);
        registerVar.alert8.setTitle("Error");
        registerVar.alert8.setContentText("Password doesn't match!");
        
        registerVar.alert9 = new Alert(AlertType.ERROR);
        registerVar.alert9.setTitle("Error");
        registerVar.alert9.setContentText("Age must be between 13-65!");
    }
	
	private void initializeMenu(RegisterVar registerVar) {
		registerVar.menuBar = new MenuBar();
		registerVar.menu = new Menu("Menu");
		registerVar.menuItemLogin = new MenuItem("Login");
		registerVar.menuBar.getMenus().add(registerVar.menu);
		registerVar.menu.getItems().add(registerVar.menuItemLogin);
	}
	
	
	private void initialize(RegisterVar registerVar) {
		registerVar.bp = new BorderPane();
		registerVar.vb = new VBox();
		registerVar.gp = new GridPane();
		
		registerVar.title = new Label("REGISTRATION");
		registerVar.usernameLbl = new Label("Username");
		registerVar.usernameTf = new TextField();
		registerVar.passwordLbl = new Label("Password");
		registerVar.passworPf = new PasswordField();
		registerVar.confirmPassLbl = new Label("Confirm Password");
		registerVar.confirmPassPf = new PasswordField();
		registerVar.ageLbl = new Label("Age");
		registerVar.ageSpin = new Spinner<>(13, 65, 13);
		registerVar.registerSubmit = new Button("REGISTER");
		
		initializeMenu(registerVar);
		registerVar.vb.getChildren().add(registerVar.title);
		registerVar.vb.setAlignment(Pos.CENTER);
		registerVar.gp.add(registerVar.vb, 0, 1);
		registerVar.gp.add(registerVar.usernameLbl, 0, 2);
		registerVar.gp.add(registerVar.usernameTf, 0, 3);
		registerVar.gp.add(registerVar.passwordLbl, 0, 4);
		registerVar.gp.add(registerVar.passworPf, 0, 5);
		registerVar.gp.add(registerVar.confirmPassLbl, 0, 6);
		registerVar.gp.add(registerVar.confirmPassPf, 0, 7);
		registerVar.gp.add(registerVar.ageLbl, 0, 8);
		registerVar.gp.add(registerVar.ageSpin, 0, 9);
		registerVar.gp.add(registerVar.registerSubmit, 0, 10);
		registerVar.gp.setVgap(10);
		registerVar.gp.setAlignment(Pos.CENTER);
		registerVar.bp.setTop(registerVar.menuBar);
		registerVar.bp.setCenter(registerVar.gp);
		registerVar.scene = new Scene(registerVar.bp, 1280, 720);
	}
	
	private void handleRegister(RegisterVar registerVar) {
		UserController userController = new UserController();
		userController.registHandle(registerVar);
	}
	
	private void setStyle(RegisterVar registerVar) {
		registerVar.title.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Times-New-Roman;" + "-fx-font-size: 25px;");
		registerVar.registerSubmit.setStyle("-fx-background-color: grey;" + "-fx-text-fill: white;" + "-fx-min-width: 480px;" + "-fx-font-weight: bold;");
		registerVar.ageSpin.setStyle("-fx-min-width: 480px");
	}

	public Scene startRegisterPage() {
		RegisterVar registerVar = new RegisterVar();
		
		initialize(registerVar);
		initializeAlert(registerVar);
		handleRegister(registerVar);
		setStyle(registerVar);
		
		Main.stage.setTitle("Register Page");
		
		return registerVar.scene;
		
	}

}
