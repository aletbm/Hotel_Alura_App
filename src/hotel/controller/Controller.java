package hotel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hotel.model.DatabaseManager;
import hotel.model.SecretPassword;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Controller implements Initializable{
	
	@FXML
	private MediaView mediaView;
	
	@FXML
	public Button loginBtn, backWindow;
	
	@FXML
	private Label welcomeText;
	
	@FXML
	public ComboBox<String> comboBoxMpago, comboBoxNacionalidades;
	
	public SecretPassword spwd = new SecretPassword();
	public DatabaseManager db = new DatabaseManager();
	public MessagePopup info = new MessagePopup(AlertType.INFORMATION);
	public ObservableList<String> comboLista = FXCollections.observableArrayList("Tarjeta de crédito", "Tarjeta de débito", "Dinero en efectivo");
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/hotel/sources/preview.mp4").toURI().toString()));
			this.mediaView.setMediaPlayer(mediaPlayer);
			mediaPlayer.setMute(true);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.play();
			
			this.welcomeText.setFont(Font.loadFont(new File("src/hotel/sources/MonteCarlo-Regular.ttf").toURI().toString(), 28));
	}
	
	public void handlerCloseWindow(ActionEvent event) throws IOException {
		event.consume();
		info.setType(AlertType.CONFIRMATION);
		info.setMessage("Esta seguro que desea salir?.");
		info.setMessageContext("Esta apunto de cerrar el sistema de reserva.");
		info.show();
		if(info.isDecisionOK()) {
			Platform.exit();
		}
	}
	
	public void handlerBackMenu(ActionEvent event) throws IOException {
		event.consume();
		changePage(this.backWindow, "/hotel/view/menuUser.fxml");
	}
	
	public void handlerLoginBtn(ActionEvent event) throws IOException {
		event.consume();
		changePage(this.loginBtn, "/hotel/view/login.fxml");
	}
	
	public void changePage(Button btn, String url) {
		try {
			Parent loader = FXMLLoader.load(getClass().getResource(url));
			
	        Stage stage = (Stage) btn.getScene().getWindow();
	        setMoveAndDragWindow(loader, stage);
	        
	        Scene scene = new Scene(loader);
	        stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMoveAndDragWindow(Parent loader, Stage stage) {
		class Delta { double x, y; }

        final Delta dragDelta = new Delta();
        
		loader.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = stage.getX() - mouseEvent.getScreenX();
            dragDelta.y = stage.getY() - mouseEvent.getScreenY();
          }
        });
		
		loader.setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            stage.setX(mouseEvent.getScreenX() + dragDelta.x);
            stage.setY(mouseEvent.getScreenY() + dragDelta.y);
          }
        });
	}
}
