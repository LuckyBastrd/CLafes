package view;

import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {
	
	 String userRole = "Customer";
	 
	 MenuBar menuBar = MenuBarBuilder.createMenuBar(userRole);
	
	public class LoginVariable {
		public Stage stage;
		Scene scene;
		BorderPane bp;
		GridPane gp;
		VBox vb1, vb2;
		Label TitleLabel, UsernameLabel, PasswordLabel;
		public TextField UsernameTF;
		public PasswordField PasswordPF;
		public Button LoginButton;
	}
	
	private void initialize(LoginVariable loginVariable) {
		loginVariable.bp = new BorderPane();
		loginVariable.gp = new GridPane();
		loginVariable.vb1 = new VBox();
		loginVariable.vb2 = new VBox();

		loginVariable.TitleLabel = new Label("Login");

		loginVariable.UsernameLabel = new Label("Username");
		loginVariable.UsernameTF = new TextField();

		loginVariable.PasswordLabel = new Label("Password");
		loginVariable.PasswordPF = new PasswordField();

		loginVariable.LoginButton = new Button("LOGIN");

		loginVariable.vb1.getChildren().add(loginVariable.TitleLabel);

		loginVariable.vb2.getChildren().addAll(loginVariable.UsernameLabel, loginVariable.UsernameTF,
				loginVariable.PasswordLabel, loginVariable.PasswordPF, loginVariable.LoginButton);

		loginVariable.gp.add(loginVariable.vb1, 0, 0);
		loginVariable.gp.add(loginVariable.vb2, 0, 1);

		loginVariable.bp.setCenter(loginVariable.gp);
		
		loginVariable.bp.setTop(menuBar);

		loginVariable.scene = new Scene(loginVariable.bp, 666, 666);
	}
	
	private void handle(LoginVariable loginVariable) {
		UserController userController = new UserController();
		userController.handlingLogin(loginVariable);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginVariable loginVariable = new LoginVariable();
		initialize(loginVariable);
		handle(loginVariable);
		
		loginVariable.stage = primaryStage;
		loginVariable.stage.setScene(loginVariable.scene);
		loginVariable.stage.setResizable(false);
		loginVariable.stage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
