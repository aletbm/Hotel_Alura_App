package hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import hotel.model.DatabaseManager;
import hotel.model.Huesped;
import hotel.model.Nacionalidades;
import hotel.model.Reserva;
import hotel.model.ValidarHuesped;
import hotel.model.ValidarReserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditorPopupController implements Initializable {
	
	@FXML
	private TextField nameHuespedTF, lnameHuespedTF, telefonoHuespedTF, precioTextField, nroReservaTF;
	
	@FXML
	private DatePicker fechaIn, fechaOut, fechaNac;
	
	@FXML
	private ComboBox<String> comboBoxMpago, comboBoxNacionalidades;
	
	@FXML
	private Button CancelarBtn, GuardarBtn;
	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private ObservableList<String> comboLista = FXCollections.observableArrayList("Tarjeta de crédito", "Tarjeta de débito", "Dinero en efectivo");

	private DatabaseManager db = new DatabaseManager();
	private String idHuesped;

	private MessagePopup info = new MessagePopup(AlertType.INFORMATION);
	
	@Override 
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	
	public void handlerActualizarReserva(ActionEvent event) throws IOException{
		event.consume();
		String id = this.nroReservaTF.getText();
		LocalDate dateIn_LD = this.fechaIn.getValue();
		LocalDate dateOut_LD = this.fechaOut.getValue();
		String precio = this.precioTextField.getText();
		String formaPago = this.comboBoxMpago.getValue();
		
		ValidarReserva vr = new ValidarReserva(dateIn_LD, dateOut_LD, precio, formaPago);
		if(vr.isValid()) {
			String dateIn = dateIn_LD.toString();
			String dateOut = dateOut_LD.toString();
			this.db.updateReserva(id, dateIn, dateOut, precio, formaPago);
			Stage stage = (Stage) GuardarBtn.getScene().getWindow();
			info.succesAlert();
			info.setMessage("Reserva actualizada exitosamente.");
			info.setMessageContext("Gracias por confiar en nosotros.");
			info.show();
			stage.close();
		}
		else {
			info.setMessage("Se hallaron los siguiente errores:\n" + vr.getError());
			info.setMessageContext("Revise los datos ingresados.");
			info.show();
		}
	}
	
	public void handlerActualizarHuesped(ActionEvent event) throws IOException{
		event.consume();
		String nameHuesped = this.nameHuespedTF.getText();
		String lnameHuesped = this.lnameHuespedTF.getText();
		LocalDate dateNac_LD = this.fechaNac.getValue();
		String nacionalidad = this.comboBoxNacionalidades.getValue();
		String telefono = this.telefonoHuespedTF.getText();
		String nroReserva = this.nroReservaTF.getText();
		
		ValidarHuesped vh = new ValidarHuesped(nameHuesped, lnameHuesped, dateNac_LD, nacionalidad, telefono, nroReserva);
		
		if(vh.isValid()) {
			String dateNac = dateNac_LD.toString();
			this.db.updateHuesped(this.idHuesped, nameHuesped, lnameHuesped, dateNac, nacionalidad, telefono, nroReserva);
			Stage stage = (Stage) GuardarBtn.getScene().getWindow();
			info.succesAlert();
			info.setMessage("Huesped actualizado exitosamente.");
			info.setMessageContext("Gracias por confiar en nosotros.");
			info.show();
			stage.close();
		}
		else {
			info.setType(AlertType.INFORMATION);
			info.setMessage("Se hallaron los siguiente errores:\n" + vh.getError());
			info.setMessageContext("Revise los datos ingresados.");
			info.show();
		}
	}
	
	public void handlerCancelar(ActionEvent event) throws IOException{
		event.consume();
		Stage stage = (Stage) CancelarBtn.getScene().getWindow();
		stage.close();
	}

	public void setHuesped(Huesped huesped) {
		this.idHuesped = huesped.getId();
		this.nameHuespedTF.setText(huesped.getNombre());
		this.lnameHuespedTF.setText(huesped.getApellido());
		this.fechaNac.setValue(LocalDate.parse(huesped.getFnac(), this.dateFormatter));
		Nacionalidades na = new Nacionalidades();
		List<String> nacionalidades = na.getNacionalidades("src/hotel/sources/Nacionalidades.txt");
		this.comboBoxNacionalidades.setItems(FXCollections.observableList(nacionalidades));
		this.comboBoxNacionalidades.setValue(huesped.getNacion());
		this.telefonoHuespedTF.setText(huesped.getTel());
		this.nroReservaTF.setText(huesped.getId_reserva());
	}

	public void setReserva(Reserva reserva) {
		this.fechaIn.setValue(LocalDate.parse(reserva.getFechaIn(), this.dateFormatter));
		this.fechaOut.setValue(LocalDate.parse(reserva.getFechaOut(), this.dateFormatter));
		this.precioTextField.setText(reserva.getValor());
		this.comboBoxMpago.setItems(comboLista);
		this.comboBoxMpago.setValue(reserva.getPago());
		this.nroReservaTF.setText(reserva.getId());
	}
}
