package main;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Database;

public class Photos extends Application{

	public static void main(String[] args) {
		
		launch(args);
	}
	
	public void start(Stage primaryStage){
		try {		
			Database.init();
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("/views/Login.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
//			LoginController controller = loader.getController();
		
		
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
