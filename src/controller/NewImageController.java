package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import model.serialController;
import util.Manager;
import util.Parser;

/**
 * Class that acts as the controller for the NewImage.fxml file.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class NewImageController {
	//Labels
    @FXML private ImageView dragged;
    
    //Buttons
    @FXML private Button OK;
    @FXML private Button CANCEL;
    
    //Textfield
    @FXML private TextField name;
    @FXML private TextField Caption; //havent done anything with tags yet.
    @FXML private TextField Tags;
    
    
    
	private Stage a;
	private File newPhoto;
	private Album album;
	private boolean nothingDragged = false;
	boolean canceled = false;
	/**
	 * Method that initializes the controller.
	 * @param for the Main Stage window.
	 * @param for the Album that contains all the images.
	 * @author AbidAzad aa2177
	 */	
    public void start(Stage mainStage, Album album) {
    	a = mainStage;
    	this.album = album;
    	dragged.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
        });
    	
        dragged.setOnDragDropped(event -> {
        	
        	
            Dragboard r = event.getDragboard();
            if(event.getDragboard().hasFiles() && !nothingDragged){
            	
                File draggedFile = r.getFiles().get(0);
                boolean isAccepted = draggedFile.getName().toLowerCase().endsWith(".png")
                		||draggedFile.getName().toLowerCase().endsWith(".bmp")
                		||draggedFile.getName().toLowerCase().endsWith(".gif")
                		||draggedFile.getName().toLowerCase().endsWith(".jpeg")
                		||draggedFile.getName().toLowerCase().endsWith(".jpg");
                
                if(isAccepted) {
                	Image a = new Image(draggedFile.toURI().toString());
                	dragged.setImage(a);
                	nothingDragged = true;
                	newPhoto = draggedFile;
                	
                }
                
            }
        });
    	
    }
   
    /**
	 * Method that determines whether an image is added. If there is no photo input or a duplicate detected, it will send an alert. Otherwise, it will add the photo to the album.
	 * @param for the ActionEvent for the button click.
	 * @author AbidAzad aa2177
	 */	    
    @FXML void OK(ActionEvent event) {
		if(newPhoto == null) {
			showAlert("WARNING", "No photo inputted", "Please drag a photo in.");
			return;
		}
		
		for(Photo x : album.getPhotos()) {
			if(x.getFilePath().equals(newPhoto.getAbsolutePath())){
				System.out.println(x.getFilePath());
				System.out.println(newPhoto.getAbsolutePath());
				showAlert("WARNING", "Duplicate Photo", "Cancel Operation and Try Again.");
				return;
			}
		}
		
		String title = "Unnamed";
		String caption = "Unnamed";
		HashMap<String,String> tags = null;
		
		if(!name.getText().isBlank())
			title = name.getText();
		
		if(!Caption.getText().isBlank())
			caption = Caption.getText();
		
		if(!Tags.getText().isBlank()) {
			tags = Parser.getTags(Tags.getText().trim());
			if(tags == null) {
				showAlert("WARNING", "Invalid tag format", "While attempting to add tags, please use the format <tag-type> = <tag-name> and use ',' for adding multiple tags at once\n\n(i.e. person=John , location = Rutgers)");
			}
		}
		Date date = Manager.getTime();
		
    	album.addPhoto(title, caption, date, tags, newPhoto.getAbsolutePath());
    	a.hide();
   }  
    /**
   	 * Method that closes the stage. Keeps referenced of whether a cancel operation occured.
   	 * @param for the ActionEvent for the button click.
   	 * @author AbidAzad aa2177
   	 */	      
    @FXML void CANCEL(ActionEvent event) {
		canceled = true;
		a.hide();
    	
    }  
    /**
   	 * Helper Method that throws an alert.
   	 * @param for the title of alert, String.
   	 * @param for the header of alert, String.
   	 * @param for the content of alert, String.
   	 * @author DiegoCastellanos dac392
   	 */	  
    private void showAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
		
}
    public boolean isCanceled() {
    	return canceled;
    }
    
    public Album getAlbum() {
    	return album;
    }
}
