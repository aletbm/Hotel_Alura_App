package hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import hotel.model.Huesped;
import hotel.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class BusquedaController extends Controller{
	
	@FXML
	private TextField buscarTF;
	
	@FXML
	private TableView<Huesped> huespedesTableView;
	
	@FXML
	private TableView<Reserva> reservasTableView;
	
	@FXML
	private TableColumn <Huesped, String> idHuespedCol, nameHuespedCol, apellidoHuespedCol, fnacHuespedCol, nacionHuespedCol, telHuespedCol, nroResHuespedCol;
	
	@FXML
	private TableColumn <Reserva, String> nroResReservasCol, fechaInReservasCol, fechaOutReservasCol, valorReservasCol, pagoReservasCol;
	
	@FXML
	private Tab ReservasTab, HuespedesTab;
	
	private ObservableList<Huesped> dataHuesped = FXCollections.observableArrayList();
	private ObservableList<Reserva> dataReserva = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			idHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("id"));
			nameHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("nombre"));
			apellidoHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("apellido"));
			fnacHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("fnac"));
			nacionHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("nacion"));
			telHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("tel"));
			nroResHuespedCol.setCellValueFactory(new PropertyValueFactory<Huesped, String>("id_reserva"));
			
			nroResReservasCol.setCellValueFactory(new PropertyValueFactory<Reserva, String>("id"));
			fechaInReservasCol.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fechaIn"));
			fechaOutReservasCol.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fechaOut"));
			valorReservasCol.setCellValueFactory(new PropertyValueFactory<Reserva, String>("valor"));
			pagoReservasCol.setCellValueFactory(new PropertyValueFactory<Reserva, String>("pago"));
			
			updateTVHuespedes(null, true, false);
			updateTVReservas(null, true, false);
	}
	
	public void handlerBuscarTF(KeyEvent event) throws IOException {
		event.consume();
		buscador();
	}
	
	public void handlerEditBtn(ActionEvent event) throws IOException {
		event.consume();
		if(ReservasTab.isSelected()) {
			Reserva reserva = this.reservasTableView.getSelectionModel().getSelectedItem();
			if(reserva != null) {
				EditorPopup editor = new EditorPopup(reserva);
				editor.createPopup();
				buscador();
			}
			else {
				info.setType(AlertType.INFORMATION);
				info.setMessage("Ninguna reserva para editar.");
				info.setMessageContext("Seleccione la reserva que desea editar.");
				info.show();
			}
		}
		else if(HuespedesTab.isSelected()) {
			Huesped huesped = this.huespedesTableView.getSelectionModel().getSelectedItem();
			if(huesped != null) {
				EditorPopup editor = new EditorPopup(huesped);
				editor.createPopup();
				buscador();
			}
			else {
				info.setType(AlertType.INFORMATION);
				info.setMessage("Ningun huesped para editar.");
				info.setMessageContext("Seleccione el huesped que desea editar.");
				info.show();
			}
		}
	}
	
	public void handlerEliminarBtn(ActionEvent event) throws IOException{
		event.consume();
		if(ReservasTab.isSelected()) {
			Reserva reserva = this.reservasTableView.getSelectionModel().getSelectedItem();
			if(reserva != null) {
				this.db.deleteHuespedXnroReserva(reserva.getId());
				this.db.deleteReserva(reserva.getId());
				info.succesAlert();
				info.setMessage("Huesped eliminado exitosamente.");
				info.setMessageContext("Gracias por confiar en nosotros.");
				info.show();
				buscador();
			}
			else {
				info.setType(AlertType.INFORMATION);
				info.setMessage("Ninguna reserva para eliminar.");
				info.setMessageContext("Seleccione la reserva que desea eliminar.");
				info.show();
			}
		}
		else if(HuespedesTab.isSelected()) {
			Huesped huesped = this.huespedesTableView.getSelectionModel().getSelectedItem();
			if(huesped != null) {
				this.db.deleteHuesped(huesped.getId());
				this.db.deleteReserva(huesped.getId_reserva());
				info.succesAlert();
				info.setMessage("Huesped eliminado exitosamente.");
				info.setMessageContext("Gracias por confiar en nosotros.");
				info.show();
				buscador();
			}
			else {
				info.setType(AlertType.INFORMATION);
				info.setMessage("Ningun huesped para eliminar.");
				info.setMessageContext("Seleccione el huesped que desea eliminar.");
				info.show();
			}
		}
	}
	
	private List<String> updateTVHuespedes(String busqueda, boolean clear, boolean list) {
		if(clear) {
			this.huespedesTableView.getItems().clear();
		}
		List<String> ids_reserva = new ArrayList<>();
		List<Map<String, String>> huespedes;
		if(busqueda == null && !list) {
			huespedes = this.db.getHuespedes();
		}
		else if(busqueda != null && !list) {
			huespedes = this.db.getHuespedes(busqueda);
		}
		else {
			huespedes = this.db.getHuespedesXnroReserva(busqueda);
		}
		
		for(int i = 0; i < huespedes.size(); i++){
			Map<String, String> huesped = huespedes.get(i);
			dataHuesped.add(new Huesped(
					huesped.get("ID"),
					huesped.get("NOMBRE"),
					huesped.get("APELLIDO"),
					huesped.get("FECHA_DE_NACIMIENTO"),
					huesped.get("NACIONALIDAD"),
					huesped.get("TELEFONO"),
					huesped.get("ID_RESERVA")
			));
			ids_reserva.add(huesped.get("ID_RESERVA"));
		};
		this.huespedesTableView.setItems(dataHuesped);
		return ids_reserva;
	}
	
	private List<String> updateTVReservas(String id, boolean clear, boolean list) {
		if(clear) {
			this.reservasTableView.getItems().clear();
		}
		List<String> ids_reserva = new ArrayList<>();
		List<Map<String, String>> reservas;
		if(id == null && !list) {
			reservas = this.db.getReservas();
		}
		else if(id != null && !list) {
			reservas = this.db.getReservas(id);
		}
		else {
			reservas = this.db.getReservasList(id);
		}
		
		for(int i = 0; i < reservas.size(); i++){
			Map<String, String> reserva = reservas.get(i);
			dataReserva.add(new Reserva(
					reserva.get("ID"),
					reserva.get("FECHAENTRADA"),
					reserva.get("FECHASALIDA"),
					reserva.get("VALOR"),
					reserva.get("FORMAPAGO")
			));

			ids_reserva.add(reserva.get("ID"));
		};
		this.reservasTableView.setItems(dataReserva);
		return ids_reserva;
	}
	
	private void buscador() {
		String busqueda = this.buscarTF.getText();
		if(busqueda.length() != 0) {
			if(busqueda.matches("[0-9]+")) {
				List<String> ids_reserva = updateTVReservas(busqueda, true, false);
				if(ids_reserva.size() > 0) {
					String list_ids = ids_reserva.toString().replace("[", "(").replace("]", ")");
					updateTVHuespedes(list_ids, true, true);
				}
				else {
					updateTVHuespedes("", true, false);
				}
			}
			if(busqueda.matches("[A-Za-z ]+")) {
				List<String> ids_reserva = updateTVHuespedes(busqueda, true, false);
				if(ids_reserva.size() > 0) {
					String list_ids = ids_reserva.toString().replace("[", "(").replace("]", ")");
					updateTVReservas(list_ids, true, true);
				}
				else {
					updateTVReservas("-1", true, false);
				}
			}
		}
		else {
			updateTVHuespedes(null, true, false);
			updateTVReservas(null, true, false);
		}
	}
}
