package hotel.controller;

import java.io.IOException;

import hotel.model.Huesped;
import hotel.model.Reserva;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditorPopup {
	private String url;
	
	private Stage popupStage = null;
	
	private Huesped huesped = null;
	private Reserva reserva = null;
	
	public EditorPopup(Reserva reserva) {
		this.url = "/hotel/view/EditReserva.fxml";
		this.reserva = reserva;
	}
	
	public EditorPopup(Huesped huesped) {
		this.url = "/hotel/view/EditHuesped.fxml";
		this.huesped = huesped;
	}
	
	public void createPopup() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(this.url));
			Parent root = loader.load();
			EditorPopupController controller = loader.getController();
			if(this.huesped != null) {
				controller.setHuesped(this.huesped);
			}
			if(this.reserva != null) {
				controller.setReserva(this.reserva);
			}
			Scene scene = new Scene(root);
			this.popupStage = new Stage();
			this.popupStage.initModality(Modality.APPLICATION_MODAL);
			this.popupStage.initStyle(StageStyle.UNDECORATED);
			this.popupStage.setScene(scene);
			this.popupStage.showAndWait();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
