package hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuUserController extends Controller{
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private HBox sisReservaBtn, busquedaBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			this.dateLabel.setText("Hoy es " + dtf.format(calendar.getTime()));
	}
	
	public void handlerSisReservaBtn(MouseEvent event) throws IOException {
		event.consume();
		Parent loader = FXMLLoader.load(getClass().getResource("/hotel/view/registroReserva.fxml"));
        Stage stage = (Stage) this.sisReservaBtn.getScene().getWindow();
        setMoveAndDragWindow(loader, stage);
        Scene scene = new Scene(loader);
        stage.setScene(scene);
	}
	
	public void handlerBusquedaBtn(MouseEvent event) throws IOException {
		event.consume();
		Parent loader = FXMLLoader.load(getClass().getResource("/hotel/view/busqueda.fxml"));
        Stage stage = (Stage) this.busquedaBtn.getScene().getWindow();
        setMoveAndDragWindow(loader, stage);
        Scene scene = new Scene(loader);
        stage.setScene(scene); 
	}
	
	public void handlerLoginBtn(ActionEvent event) throws IOException {
		event.consume();
		changePage(this.loginBtn, "/hotel/view/login.fxml");
	}

}
