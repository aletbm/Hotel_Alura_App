package hotel.controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MessagePopup {
	private Alert a = null;
	private String message;
	private String messageContext;
	private AlertType type;
	private boolean decisionOK = false;
	
	public void setType(AlertType type) {
		this.type = type;
		this.a.setAlertType(this.type);
	}
	
	public MessagePopup(AlertType type) {
		this.type = type;
		this.a = new Alert(this.type);
		DialogPane dialogPane = this.a.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/hotel/view/App.css").toExternalForm());
	    dialogPane.getStyleClass().add("dialog");
	    Image icon = new Image(String.valueOf(this.getClass().getResource("/hotel/sources/campana.png")));
	    Stage stage = (Stage) dialogPane.getScene().getWindow();
	    stage.getIcons().add(icon);
	}
	public void show() {
        
		this.a.setTitle("Atencion");
		this.a.setHeaderText(message);
		this.a.setContentText(messageContext);
		Optional<ButtonType> result = this.a.showAndWait();
		if (result.get() == ButtonType.OK){
		    this.decisionOK = true;
		} else if(result.get() == ButtonType.CANCEL){
			this.decisionOK = false;
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
	}
	
	public void succesAlert() {
		ImageView icon = new ImageView(new Image(String.valueOf(this.getClass().getResource("/hotel/sources/tick.png"))));
	    icon.setFitHeight(48);
	    icon.setFitWidth(48);
	    this.a.getDialogPane().setGraphic(icon);
	}

	public boolean isDecisionOK() {
		return decisionOK;
	}
}
