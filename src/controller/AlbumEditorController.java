package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlbumEditorController {
	
	@FXML private Button done_btn;
    @FXML private Button cancel_btn;
    @FXML private TextField albumName;
    
	@FXML
    void cancel(ActionEvent event) throws IOException{
//    	System.out.println("cancel");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/HomeScreen.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
	}

    @FXML
    void done(ActionEvent event) throws IOException{
//    	System.out.println("done");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/HomeScreen.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
	}
}
