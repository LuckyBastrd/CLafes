package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RegisterPage;

public class Main extends Application {
	
	public static Stage stage;
	
	public static void setScene(Scene scene) {
		
		if(stage == null) {
			return;
		}
		
		stage.setScene(scene);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Main.setScene(new RegisterPage().startRegisterPage());
		
		stage.setResizable(false);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}