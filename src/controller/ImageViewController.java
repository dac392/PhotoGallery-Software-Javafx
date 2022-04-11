package controller;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Event;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ArrayList;
import model.serialController;
import util.Album;
import util.Photo;
import util.PhotoListItem;
import util.User;

/**
 * Class that acts as the controller for the ImageView.fxml file.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class ImageViewController {
	//Buttons
	@FXML private Button add;
	@FXML private Button move;
	@FXML private Button copy;
	@FXML private Button next;
	@FXML private Button edit;
	@FXML private Button previous;
	@FXML private Button delete;
	
	//Label
	@FXML private Label album_Name;
	@FXML private Label caption;
	@FXML private Label date;
	@FXML private Label tags;
	

	
	//List
	@FXML private ListView<Photo> photoList;
	private ObservableList<Photo> obsList = FXCollections.observableArrayList(); 
	
	//Image
	@FXML private ImageView display;
	private Stage a;
	private Album gallery;
	private User user;
	serialController cereal = new serialController();
	
	/**
	 * Method that initializes the controller. Populates the list with the album photo's captions. Also selects the first item if possible.
	 * @param for the Main Stage window.
	 * @param for the Album that holds the photos.
	 * @param for the User that holds the album.
	 * @author AbidAzad aa2177
	 * @author diegocastellanos
	 */	   
    public void start(Stage mainStage, Album gallery, User user) {
    	a = mainStage;
    	this.user = user;
    	this.gallery = gallery;
    	album_Name.setText(gallery.getName());
    	
    	for(Photo x : gallery.getPhotos()) {
    		obsList.add(x);
    	}
    	photoList.setItems(obsList);
    	photoList		
    	.getSelectionModel()
		.selectedIndexProperty()
		.addListener( (obs, oldVal, newVal) -> select(mainStage));
    	
        photoList.setCellFactory(new Callback< ListView<Photo>, ListCell<Photo> >() {
            @Override 
            public ListCell<Photo> call(ListView<Photo> list) {
                return new PhotoListItem();
            }
        });
    	
    	if(gallery.getPhotos().size() > 0) {
    		photoList.getSelectionModel().select(0);
    	}
    	

    	
    
    
    }
	/**
	 * Method that opens another stage and prompts for a new Image. Primary stage is disabled.
	 * When the secondary stage is closed, it checks for User input. If user input canceled or closed the window, nothing occurs.
	 * Else, the method updates album, and then updates user data.
	 * @param event ActionEvent for when the button is clicked.
	 * @author AbidAzad aa2177
	 * @author diegocastellanos
	 */	     
    @FXML void add(ActionEvent event) {
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/NewImage.fxml"));
				
				DialogPane root = (DialogPane)loader.load();
				NewImageController listController = loader.getController();
			
				Stage b = new Stage();
				b.initOwner(a.getScene().getWindow());
				b.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(root);
				b.setScene(scene);
				listController.start(b, gallery);
				b.showAndWait();
				
				if(!listController.canceled) {
					for(int i = 0; i < user.getAlbums().size(); i++) {
						if(user.getAlbums().get(i).equals(gallery.getName())){
							user.getAlbums().remove(i);
							user.getAlbums().add(i, listController.getAlbum());
							break;
						}
					}
					obsList.clear();
					
					for(Photo x : listController.getAlbum().getPhotos())
			        {
			        	obsList.add(x);
			        	
			        }				
			        photoList.setItems(obsList);	
			        int indexOfNewImage = obsList.size()-1;
			        photoList.getSelectionModel().select(indexOfNewImage);
			        ArrayList<User> data = cereal.data();
			        
			        for(int i = 0; i < data.size(); i++) {
			        	if(data.get(i).getUsername().equals(user.getUsername())) {
			        		data.remove(i);
			        		data.add(i, user);
			        		break;
			        	}
			        }
			        cereal.update(data);
					
				}
							

			} catch(Exception e) {
				e.printStackTrace();
			}
	 }
    /**
	 * Method that opens another stage and prompts to move an image to a different album.  //NEEEDS WORKS
	 * @param for the ActionEvent for when the button is clicked.
	 * @author AbidAzad aa2177
	 */	    
    @FXML void move(ActionEvent event) {
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/ImageMove.fxml"));
				
				DialogPane root = (DialogPane)loader.load();
				ImageMoveController listController = loader.getController();
				
				
					
				Stage b = new Stage();
				b.initOwner(a.getScene().getWindow());
				b.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(root);
				b.setScene(scene);
				listController.start(b, user.getAlbums(), photoList.getSelectionModel().getSelectedItem(), gallery);
				b.showAndWait();
				
				if(!listController.canceled) {
					user.setAlbums(listController.albums);
				}
				obsList.clear();
				for(Photo x : gallery.getPhotos())
		        {
		        	obsList.add(x);
		        }				
		        photoList.setItems(obsList);	
				 ArrayList<User> data = cereal.data();
			        
			        for(int i = 0; i < data.size(); i++) {
			        	if(data.get(i).getUsername().equals(user.getUsername())) {
			        		data.remove(i);
			        		data.add(i, user);
			        		break;
			        	}
			        }
			        cereal.update(data);
				

			} catch(Exception e) {
				e.printStackTrace();
			}
	 }
    /**
   	 * Method that opens another stage and prompts to copy an image to a different album.  //NEEEDS WORKS
   	 * @param for the ActionEvent for when the button is clicked.
   	 * @author AbidAzad aa2177
   	 */	     
    @FXML void copy(ActionEvent event) {
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/ImageCopy.fxml"));
				
				DialogPane root = (DialogPane)loader.load();
				ImageCopyController listController = loader.getController();
				
				
					
				Stage b = new Stage();
				b.initOwner(a.getScene().getWindow());
				b.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(root);
				b.setScene(scene);
				listController.start(b);
				b.showAndWait();

			} catch(Exception e) {
				e.printStackTrace();
			}
	 }
    /**
   	 * Method that selects the previous photo.
   	 * @author AbidAzad aa2177
   	 */	        
    @FXML void previous(ActionEvent event) {
    	photoList.getSelectionModel().selectPrevious();
	 }  
    /**
   	 * Method that opens another stage and prompts to edit the selected photo.  //NEEEDS WORKS
   	 * @param for the ActionEvent for when the button is clicked.
   	 * @author AbidAzad aa2177
   	 */	         
    @FXML void edit(ActionEvent event) {
		 try {		
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/views/PhotoEdit.fxml"));
				
				AnchorPane root = (AnchorPane)loader.load();
				PhotoEditController listController = loader.getController();	
				Stage b = new Stage();
				b.initOwner(a.getScene().getWindow());
				b.initModality(Modality.WINDOW_MODAL);
				Scene scene = new Scene(root);
				b.setScene(scene);
				listController.start(b);
				b.showAndWait();

			} catch(Exception e) {
				e.printStackTrace();
			}    	
    }
    /**
   	 * Method that selects the next photo.
   	 * @author AbidAzad aa2177
   	 */	       
    @FXML void next(ActionEvent event) {
    	photoList.getSelectionModel().selectNext();
	 }
    
    /**
     *	Event handler which deletes the currently selected Photo in the list view
     *	@param event ActionEvent which triggered the event handler
     *	@return void
     *	@author diegocastellanos 
     */
	 @FXML void delete(ActionEvent event) {
	    	if(!photoList.getSelectionModel().isEmpty()) {
	    		
	    		gallery.getPhotos().remove(photoList.getSelectionModel().getSelectedIndex());
	    		serialController cereal = new serialController();
	    		try {
					ArrayList<User> data = cereal.data();
					for(User x : data) {
						if(x.getUsername().equals(user.getUsername())) {
							for(Album y : x.getAlbums()) {
								
								if(y.getName().equals(gallery.getName()))
								{
									y.getPhotos().remove(photoList.getSelectionModel().getSelectedIndex());
									break;
								}
								
							}							
							
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
				for(int i = 0; i < gallery.getPhotoCount(); i++)
		        {
		        	obsList.add(gallery.getPhotos().get(i));
		        }				
		        photoList.setItems(obsList);
		        
		        photoList.getSelectionModel().select(0);
		        	        
	    		
	    	}
	    
	    }
    
	 /**
	  *	Event handler that brings the user back to the home screen
	  *	@param event ActionEvent that triggered event handler
	  *	@return void
	  * @author diegocastellanos
	  */
	 @FXML void backToHome(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("/views/HomeScreen.fxml"));
			
			AnchorPane root = (AnchorPane)loader.load();
			HomeScreenController homescreen = loader.getController();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
	 
	    	
	    	Scene scene = new Scene(root);
	    	stage.setScene(scene);
	    	homescreen.start(stage, user);
	 }
	 
	 
	 /**
   	 * Helper Method that displays the photo and details.
   	 * @param for main Stage Window.
   	 * @author AbidAzad aa2177
   	 */	    
    private void select(Stage mainStage) {
        Photo selected = photoList.getSelectionModel().getSelectedItem();
        
        
		if(selected != null) {
	    	Image a = new Image(selected.getFilePath());
	    	display.setImage(a);
	    	caption.setText("Caption: "+selected.getCaption());
	    	date.setText("Date: "+selected.getDate());
	    	tags.setText("Tags: "+selected.getStringTags());
		}	


	}
}
