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

public class ImageViewController {
	@FXML private Button add_btn;
    @FXML private Button move_btn;
    @FXML private Button copy_btn;
    @FXML private Button delete_btn;
    @FXML private Button previous_btn;
    @FXML private Button edit_btn;
    @FXML private Button next_btn;
    
    @FXML void add(ActionEvent event) throws IOException{
//    	System.out.println("add photo");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/NewImage.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }
    
    @FXML void move(ActionEvent event) throws IOException{
//    	System.out.println("move photo");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/ImageMove.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }
    
    @FXML void copy(ActionEvent event) throws IOException{
//    	System.out.println("copy photo");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/ImageCopy.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }
    
    @FXML void edit(ActionEvent event) throws IOException{
//    	System.out.println("edit photo");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/PhotoEdit.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }

}
