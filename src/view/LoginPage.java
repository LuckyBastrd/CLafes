package view;

import app.Main;
import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginPage {

	public class LoginVar{
		Scene scene;
		VBox vb;
		BorderPane bp;
		GridPane gp;
		MenuBar menuBar;
		Menu menu;
		public MenuItem menuItemRegister;
		Label title, usernameLbl, passwordLbl;
		public TextField usernameTf;
		public PasswordField passwordPf;
		public Button loginSubmit;
		public Alert alert1, alert2;
	}
	
	private void initializeAlert(LoginVar loginVar) {
		loginVar.alert1 = new Alert(AlertType.ERROR);
		loginVar.alert1.setTitle("Error");
		loginVar.alert1.setContentText("All Fields must be fill!");
		
		loginVar.alert2 = new Alert(AlertType.ERROR);
		loginVar.alert2.setTitle("Error");
		loginVar.alert2.setContentText("Username or Password is incorrect!!!");
	}
	
	private void initializeMenu(LoginVar loginVar) {
		loginVar.menuBar = new MenuBar();
		loginVar.menu = new Menu("Menu");
		loginVar.menuItemRegister = new MenuItem("Register");
		loginVar.menuBar.getMenus().add(loginVar.menu);
		loginVar.menu.getItems().add(loginVar.menuItemRegister);
	}
	
	private void initialize(LoginVar loginVar) {
		loginVar.bp = new BorderPane();
		loginVar.vb = new VBox();
		loginVar.gp = new GridPane();
		
		loginVar.title = new Label("LOGIN");
		loginVar.usernameLbl = new Label("Username");
		loginVar.usernameTf = new TextField();
		loginVar.passwordLbl = new Label("Password");
		loginVar.passwordPf = new PasswordField();
		loginVar.loginSubmit = new Button("LOGIN");
		
		initializeMenu(loginVar);
		
		loginVar.vb.getChildren().add(loginVar.title);
		loginVar.vb.setAlignment(Pos.CENTER);
		loginVar.gp.add(loginVar.vb, 0, 1);
		loginVar.gp.add(loginVar.usernameLbl, 0, 2);
		loginVar.gp.add(loginVar.usernameTf, 0, 3);
		loginVar.gp.add(loginVar.passwordLbl, 0, 4);
		loginVar.gp.add(loginVar.passwordPf, 0, 5);
		loginVar.gp.add(loginVar.loginSubmit, 0, 6);
		loginVar.gp.setVgap(10);
		loginVar.gp.setAlignment(Pos.CENTER);
		loginVar.bp.setTop(loginVar.menuBar);
		loginVar.bp.setCenter(loginVar.gp);
		loginVar.scene = new Scene(loginVar.bp, 1280, 720);
	}
	
	private void handleLogin(LoginVar loginVar) {
		UserController userController = new UserController();
		userController.loginHandle(loginVar);
	}
	
	private void setStyle(LoginVar loginVar) {
		loginVar.title.setStyle("-fx-font-weight: bold;" + "-fx-font-family: Times-New-Roman;" + "-fx-font-size: 30px;");
		loginVar.loginSubmit.setStyle("-fx-background-color: grey;" + "-fx-text-fill: white;" + "-fx-min-width: 480px;" + "-fx-font-weight: bold;");
	}
	
	public Scene startLoginPage() {
		LoginVar loginVar = new LoginVar();
		initialize(loginVar);
		initializeAlert(loginVar);
		handleLogin(loginVar);
		setStyle(loginVar);
		
		Main.stage.setTitle("Login Page");
		
		return loginVar.scene;
	}
}
