package hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import hotel.model.Nacionalidades;
import hotel.model.ValidarHuesped;
import hotel.model.ValidarReserva;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class SistemaReservasController extends Controller{
	
	@FXML
	private Button reservarBtn;

	@FXML
	private DatePicker fechaIn, fechaOut, fechaNac;
	
	@FXML
	private TextField precioTextField, nameHuespedTF, lnameHuespedTF, telefonoHuespedTF, nroReservaTF;

	@FXML
	private Accordion accordionSis;
	
	@FXML
	private TitledPane SisResTitledPane, SisHuespedTitledPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.comboBoxMpago.setItems(this.comboLista);
		this.accordionSis.setExpandedPane(this.SisResTitledPane);
		Nacionalidades na = new Nacionalidades();
		List<String> nacionalidades = na.getNacionalidades("src/hotel/sources/Nacionalidades.txt");
		this.comboBoxNacionalidades.setItems(FXCollections.observableList(nacionalidades));
	}
	
	public void handlerContinuarRegistro(ActionEvent event) throws IOException{
		event.consume();
		LocalDate dateIn_LD = this.fechaIn.getValue();
		LocalDate dateOut_LD = this.fechaOut.getValue();
		String precio = this.precioTextField.getText();
		String formaPago = this.comboBoxMpago.getValue();
		
		ValidarReserva vr = new ValidarReserva(dateIn_LD, dateOut_LD, precio, formaPago);
		
		if(vr.isValid()) {
			Integer nroRes = this.db.getCantidadReservas() + 1;
			this.nroReservaTF.setText(nroRes.toString());
			this.accordionSis.setExpandedPane(this.SisHuespedTitledPane);
		}
		else {
			info.setType(AlertType.INFORMATION);
			info.setMessage("Se hallaron los siguiente errores:\n" + vr.getError());
			info.setMessageContext("Revise los datos ingresados.");
			info.show();
		}
	}
	
	public void handlerRegistrarReserva(ActionEvent event) throws IOException{
		event.consume();
		LocalDate dateIn_LD = this.fechaIn.getValue();
		LocalDate dateOut_LD = this.fechaOut.getValue();
		String precio = this.precioTextField.getText();
		String formaPago = this.comboBoxMpago.getValue();
		
		String nameHuesped = this.nameHuespedTF.getText();
		String lnameHuesped = this.lnameHuespedTF.getText();
		LocalDate dateNac_LD = this.fechaNac.getValue();
		String nacionalidad = this.comboBoxNacionalidades.getValue();
		String telefono = this.telefonoHuespedTF.getText();
		String nroReserva = this.nroReservaTF.getText();
		
		ValidarReserva vr = new ValidarReserva(dateIn_LD, dateOut_LD, precio, formaPago);
		ValidarHuesped vh = new ValidarHuesped(nameHuesped, lnameHuesped, dateNac_LD, nacionalidad, telefono, nroReserva);
		
		if(vr.isValid() && vh.isValid()) {
			String dateIn = dateIn_LD.toString();
			String dateOut = dateOut_LD.toString();
			String dateNac = dateNac_LD.toString();
			if(this.db.insertReserva(nroReserva, dateIn, dateOut, precio, formaPago) && this.db.insertHuesped(nameHuesped, lnameHuesped, dateNac, nacionalidad, telefono, Integer.parseInt(nroReserva))) {
				info.succesAlert();
				info.setMessage("Reserva registrada exitosamente.");
				info.setMessageContext("Gracias por confiar en nosotros.");
				info.show();
				changePage(this.reservarBtn, "/hotel/view/registroReserva.fxml");
			}
		}
		else {
			info.setType(AlertType.INFORMATION);
			info.setMessage("Se hallaron los siguiente errores:\n" + vr.getError() + vh.getError());
			info.setMessageContext("Revise los datos ingresados.");
			info.show();
		}
		 
	}
	
	public void handlerPaneReserva(MouseEvent event) throws IOException {
		event.consume();
		this.SisHuespedTitledPane.setExpanded(false);
		this.accordionSis.setExpandedPane(this.SisResTitledPane);
	}
	
	public void handlerPaneHuesped(MouseEvent event) throws IOException {
		event.consume();
		this.SisResTitledPane.setExpanded(false);
		this.accordionSis.setExpandedPane(this.SisHuespedTitledPane);
	}
}
