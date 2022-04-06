package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Database;

public class LoginController {

    @FXML private Button login_btn;
    @FXML private Button signup_btn;
    @FXML private TextField username;

    @FXML void login(ActionEvent event) throws IOException{
    	Stage stage = null;
    	if(username.getText().isEmpty()) {
    		ControllerUtility.showAlert("Error!", "Username Missing", "Please enter a valid username.");
    		return;
    	}
    	if(username.getText().equals("admin")) {
    		//to admin
    		stage = getAdminScreen(event, "/views/AdminView.fxml");	
    	}else {
    		// to home screen
        	ArrayList<String> usernames = Database.getUsernames();
        	if(usernames.contains(username.getText())) {
        		/*
        		 *	needs to get the User object. right now it just blindly transitions to the next screen 
        		 */
        		stage = getHomeScreen(event, "/views/HomeScreen.fxml");
        	}else {
        		ControllerUtility.showAlert("Error!", "Username Missing", "Please enter a valid username.");
        		return;	
        	}
    	}

    	
    	if(stage!=null)
    			stage.show();
    	
    }
	@FXML void signup(ActionEvent event) throws IOException{
    	if(username.getText().isBlank()) {
    		ControllerUtility.showAlert("Error", "Missing Info", "Text box is empty");
    		return;
    	}
    	String newUsername = username.getText();
    	ArrayList<String> usernames = Database.getUsernames();
    	for(String x : usernames) {
    		if(x.equals(newUsername)){
    			ControllerUtility.showAlert("Error", "Douplicate Item", "The username that you are trying to add already exists");
    			return;
    		}
    	}
    	usernames.add(newUsername);
    	Database.addNewUser(newUsername);
		login(event);
    	
    }

    private Stage getAdminScreen(ActionEvent event, String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(getClass().getResource(path));
		
		AnchorPane root = (AnchorPane)loader.load();
		AdminController listController = loader.getController();
		listController.start();
//    	Parent root = FXMLLoader.load(getClass().getResource(path));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
		return stage;
	}
    private Stage getHomeScreen(ActionEvent event, String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(getClass().getResource(path));
		
		AnchorPane root = (AnchorPane)loader.load();
//    	Parent root = FXMLLoader.load(getClass().getResource(path));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
		return stage;
	}


    
 

}
