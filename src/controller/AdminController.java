package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Database;

public class AdminController {
	
    @FXML private TextField adminText;
    @FXML private ListView<String> userList;
    private ObservableList<String> obsList;
    private ArrayList<String> usernames;
    
    public void start() {
    	usernames = Database.getUsernames();
    	obsList = FXCollections.observableArrayList();
    	userList.setItems(obsList);
    	setList();
    }
    
    @FXML void addUser(ActionEvent event) {
    	if(adminText.getText().isBlank()) {
    		ControllerUtility.showAlert("Error", "Missing Info", "Text box is empty");
    		return;
    	}
    	String newUsername = adminText.getText();
    	for(String x : usernames) {
    		if(x.equals(newUsername)){
    			ControllerUtility.showAlert("Error", "Douplicate Item", "The username that you are trying to add already exists");
    			return;
    		}
    	}
    	usernames.add(newUsername);
    	Database.addNewUser(newUsername);
    	setList();
    }
    

	@FXML void logout(ActionEvent event) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
	}
    
	
	/*
	 * 
	 *  Helper methods
	 * 
	 * */
    private void setList() {
    	obsList.clear();
    	for(String user : usernames) {
    		obsList.add(user);
    	}
    	userList.setItems(obsList);
	}

	
}
