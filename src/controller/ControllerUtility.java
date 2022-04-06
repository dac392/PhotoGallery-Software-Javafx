package controller;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.User;

public class ControllerUtility {
	private static ArrayList<User> users;
	public static void init() {
		users = new ArrayList<>();
	}
	
    public static void showAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
		
	}
}
