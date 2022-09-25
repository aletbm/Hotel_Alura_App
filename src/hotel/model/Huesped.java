package hotel.model;

import javafx.beans.property.SimpleStringProperty;

public class Huesped {

	private SimpleStringProperty id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty apellido;
	private SimpleStringProperty fnac;
	private SimpleStringProperty nacion;
	private SimpleStringProperty tel;
	private SimpleStringProperty id_reserva;
	
	public Huesped(String id, String nombre, String apellido, String fnac, String nacion, String tel, String id_reserva) {
		this.setId(new SimpleStringProperty(id));
		this.setNombre(new SimpleStringProperty(nombre));
		this.setApellido(new SimpleStringProperty(apellido));
		this.setFnac(new SimpleStringProperty(fnac));
		this.setNacion(new SimpleStringProperty(nacion));
		this.setTel(new SimpleStringProperty(tel));
		this.setId_reserva(new SimpleStringProperty(id_reserva));
	}

	public String getId() {
		return id.get();
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(SimpleStringProperty nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido.get();
	}

	public void setApellido(SimpleStringProperty apellido) {
		this.apellido = apellido;
	}

	public String getFnac() {
		return fnac.get();
	}

	public void setFnac(SimpleStringProperty fnac) {
		this.fnac = fnac;
	}

	public String getNacion() {
		return nacion.get();
	}

	public void setNacion(SimpleStringProperty nacion) {
		this.nacion = nacion;
	}

	public String getTel() {
		return tel.get();
	}

	public void setTel(SimpleStringProperty tel) {
		this.tel = tel;
	}

	public String getId_reserva() {
		return id_reserva.get();
	}

	public void setId_reserva(SimpleStringProperty id_reserva) {
		this.id_reserva = id_reserva;
	}
	
}
