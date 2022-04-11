package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.Event;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ArrayList;
import model.serialController;
import util.Album;
import util.User;
import util.albumReader;
/**
 * Class that acts as the controller for the HomeScreen.fxml file.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class HomeScreenController {
	//Buttons
    @FXML private Button New_Album;
    @FXML private Button Remame_Album;
    @FXML private Button Log_Out;
    @FXML private Button delete;
    
    //Labels
    @FXML private Label name;
    
    //Table Stuff
    @FXML private TableView<albumReader> albumList;
    @FXML private TableColumn<albumReader, String> albumName;
    @FXML private TableColumn<albumReader, String> date;
    @FXML private TableColumn<albumReader, Integer> photoCount;
    
	private Stage a;
	private User user;
	private ObservableList<albumReader> obsList = FXCollections.observableArrayList(); 
	/**
	 * Initializes the controller and displays the list of album names the user holds. It also detect if user double clicked on any of the elements, which then prompts to
	 * close the primary stage and open its respective Image View Stage.
	 * @param for the Main Stage Window.
	 * @param for the user that holds the album data.
	 * @author AbidAzad aa2177
	 */	
	public void start(Stage mainStage, User user) {
    	a = mainStage;
    	this.user = user;
    	name.setText(user.getUsername());
    	
    	albumName.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        photoCount.setCellValueFactory(new PropertyValueFactory<>("photoCount"));
        
 
        for(int i = 0; i < user.getAlbums().size(); i++)
        {
        	obsList.add(new albumReader(user.getAlbums().get(i)));
        }
        
        
        albumList.setItems(obsList);
      
        
        albumList.setRowFactory(R ->{
        	TableRow<albumReader> row = new TableRow<>();
        	row.setOnMouseClicked(event ->{
        		if(event.getClickCount() == 2 && !(row.isEmpty())) {
        			try {		
        				FXMLLoader loader = new FXMLLoader();   
        				loader.setLocation(getClass().getResource("/views/ImageView.fxml"));
        				
        				AnchorPane root = (AnchorPane)loader.load();
        				ImageViewController listController = loader.getController();
        				for(Album x: user.getAlbums()) {
        					if(x.getName().equals(albumList.getSelectionModel().getSelectedItem().getTitle())) {
        						listController.start(a, x, user);
        						break;
        					}
        						
        					
        				}
        				a.close();

        				Scene scene = new Scene(root);
        				a.setScene(scene);
        				a.show();
        				
        			} catch(Exception e) {
        				e.printStackTrace();
        			
        		}
        	}});
        	return row;
        });
	
    }
	/**
	 * Adds a new album for the user, checking for duplicates and updates user data. Done by opening a secondary stage for the album editor, awaits for input, and updates user data.
	 * @param for the ActionEvent of the button being pushed.
	 * @author AbidAzad aa2177
	 */		
	 @FXML void New_Album(ActionEvent event) {
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/AlbumEditor.fxml"));
				
				AnchorPane root = (AnchorPane)loader.load();
				AlbumEditorController listController = loader.getController();
				
				
					
				Stage b = new Stage();
				b.initOwner(a.getScene().getWindow());
				b.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(root);
				b.setScene(scene);
				listController.start(b, false, user, null);
				b.showAndWait();

					
					
					
				
					obsList.clear();
					for(int i = 0; i < user.getAlbums().size(); i++)
		        	{
		        			obsList.add(new albumReader(user.getAlbums().get(i)));
		        	}				
		        	albumList.setItems(obsList);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
	 }
	 /**
		 * Renames an existing album for the user, checking for duplicates and updates user data.  Done by opening a secondary stage for the album editor, awaits for input, and updates user data.
		 * @param for the ActionEvent of the button being pushed.
		 * @author AbidAzad aa2177
		 */		
	 @FXML void Rename_Album(ActionEvent event) {
		 if(albumList.getSelectionModel().isEmpty()) {
			 showAlert("Error", "No Album Selected", "Select the one you would like to rename, then try again.");
			 return;
		 }
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/AlbumEditor.fxml"));
				
				AnchorPane root = (AnchorPane)loader.load();
				AlbumEditorController listController = loader.getController();
				
				
					
				Stage b = new Stage();
				b.initOwner(a.getScene().getWindow());
				b.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(root);
				b.setScene(scene);
				albumReader a = albumList.getSelectionModel().getSelectedItem();
				Album x = new Album(a.getTitle());
				listController.start(b, true, user, x);
				b.showAndWait();
				user = listController.getUser();
				obsList.clear();
				for(int i = 0; i < user.getAlbums().size(); i++)
		        {
		        	obsList.add(new albumReader(user.getAlbums().get(i)));
		        }				
		        albumList.setItems(obsList);			
			} catch(Exception e) {
				e.printStackTrace();
			}
	 }
	 /**
		 * Deletes the selected album and updates user data.
		 * @param for the ActionEvent of the button being pushed.
		 * @author AbidAzad aa2177
		 */		
	 @FXML void delete(ActionEvent event) {
	    	if(!albumList.getSelectionModel().isEmpty()) {
	    		user.getAlbums().remove(albumList.getSelectionModel().getSelectedIndex());
	    		serialController cereal = new serialController();
	    		try {
					ArrayList<User> data = cereal.data();
					for(User x : data) {
						if(x.getUsername().equals(user.getUsername())) {
							x.getAlbums().remove(albumList.getSelectionModel().getSelectedIndex());
							break;
						}
					}
					cereal.update(data);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		obsList.clear();
				for(int i = 0; i < user.getAlbums().size(); i++)
		        {
		        	obsList.add(new albumReader(user.getAlbums().get(i)));
		        }				
		        albumList.setItems(obsList);	
		        	        
	    		
	    	}
	    
	    }
	 /**
		 * Returns user back to log in screen.
		 * @param for the ActionEvent of the button being pushed.
		 * @author AbidAzad aa2177
		 */			 
	 @FXML void Log_Out(ActionEvent event) {
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/Login.fxml"));
				
				AnchorPane root = (AnchorPane)loader.load();
				LoginController listController = loader.getController();
				listController.start(a);
				a.close();

				Scene scene = new Scene(root);
				a.setScene(scene);
				a.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
	 }	
	 private void showAlert(String title, String header, String content) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			alert.showAndWait();
			
		}   
}
