package hotel.model;

import javafx.beans.property.SimpleStringProperty;

public class Reserva {

	private SimpleStringProperty id;
	private SimpleStringProperty fechaIn;
	private SimpleStringProperty fechaOut;
	private SimpleStringProperty valor;
	private SimpleStringProperty pago;
	
	public Reserva(String id, String fechaIn, String fechaOut, String valor, String pago) {
		this.setId(new SimpleStringProperty(id));
		this.setFechaIn(new SimpleStringProperty(fechaIn));
		this.setFechaOut(new SimpleStringProperty(fechaOut));
		this.setValor(new SimpleStringProperty(valor));
		this.setPago(new SimpleStringProperty(pago));
	}

	public String getId() {
		return id.get();
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public String getFechaIn() {
		return fechaIn.get();
	}

	public void setFechaIn(SimpleStringProperty fechaIn) {
		this.fechaIn = fechaIn;
	}

	public String getFechaOut() {
		return fechaOut.get();
	}

	public void setFechaOut(SimpleStringProperty fechaOut) {
		this.fechaOut = fechaOut;
	}

	public String getValor() {
		return valor.get();
	}

	public void setValor(SimpleStringProperty valor) {
		this.valor = valor;
	}

	public String getPago() {
		return pago.get();
	}

	public void setPago(SimpleStringProperty pago) {
		this.pago = pago;
	}
	
}
