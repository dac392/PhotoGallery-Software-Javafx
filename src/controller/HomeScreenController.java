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

public class HomeScreenController {

    @FXML
    private Button deleteAlbum_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button newAlbum_btn;

    @FXML
    private Button renameAlbum_btn;

    @FXML
    private TextField searchbar;

    @FXML
    void DeleteAlbum(ActionEvent event) {

    }

    @FXML
    void Logout(ActionEvent event) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }

    @FXML
    void NewAlbum(ActionEvent event) throws IOException{
//    	System.out.println("new album");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/AlbumEditor.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }

    @FXML
    void RenameAlbum(ActionEvent event) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("/views/AlbumEditor.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	stage.show();
    	
    }

}
