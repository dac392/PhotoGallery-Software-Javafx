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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ArrayList;
import model.serialController;

/**
 * Class that acts as the controller for the PhotoEdit.fxml file.
 * @author DiegoCastellanos dac392
 * @author AbidAzad aa2177
 */
public class PhotoEditController {
	
	private Stage a;
	
    @FXML
    private ImageView imageContainer;

    @FXML
    private TextField newCaptionField;

    @FXML
    private Label photoName;

    @FXML
    void update(ActionEvent event) {

    }
	/**
	 * Method that initializes the controller.
	 * @param for the Main Stage window.
	 * @author AbidAzad aa2177
	 */
    public void start(Stage mainStage) {
    	a = mainStage;
    }

}