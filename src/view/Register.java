package view;

import controller.UserController;
import javafx.application.Application;
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
import javafx.stage.Stage;

public class Register extends Application{
	
	public class RegisterVar{
		public Stage stage;
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
		public Alert alert;
	}
	
	private void initializeAlert(RegisterVar registerVar) {
		registerVar.alert = new Alert(AlertType.ERROR);
		registerVar.alert.setTitle("Register");
		registerVar.alert.setContentText("All Fields must be fill!"); 
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
		registerVar.scene = new Scene(registerVar.bp, 600, 600);
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		RegisterVar registerVar = new RegisterVar();
		
		initialize(registerVar);
		initializeAlert(registerVar);
		handleRegister(registerVar);
		setStyle(registerVar);
		
		registerVar.stage = primaryStage;
		registerVar.stage.setScene(registerVar.scene);
		registerVar.stage.setResizable(true); 
		registerVar.stage.show();
		
	}

}