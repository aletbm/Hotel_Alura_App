package hotel.model;

import java.time.LocalDate;

public class ValidarReserva {
	private String dateIn = null;
	private String dateOut = null;
	private String precio = null;
	private String formaPago = null;
	private String error = "";
	private boolean valid = false;
	
	public ValidarReserva(LocalDate dateIn, LocalDate dateOut, String precio, String formaPago) {
		if(dateIn != null)
			this.dateIn = dateIn.toString();
		if(dateOut != null)
			this.dateOut = dateOut.toString();
		this.precio = precio;
		this.formaPago = formaPago;
		this.valid = validar();
	}
	
	private boolean validar() {
		String error = "";
		error = error + nulos();
		error = error + vacios();
		error = error + regexs();
		this.error = error;
		if(error.compareTo("") != 0)
			return false;
		else
			return true;
	}
	
	private String nulos() {
		String error = "";
		if(this.dateIn == null) {
			error = error + "- Seleccione una fecha de check in.\n";
		}
		if(this.dateOut == null) {
			error = error + "- Seleccione una fecha de check out.\n";
		}
		if(this.precio == null) {
			error = error + "- Ingrese el valor de la reserva.\n";
		}
		if(this.formaPago == null) {
			error = error + "- Ingrese la forma de pago.\n";
		}
		return error;
	}
	
	private String vacios() {
		String error = "";
		if(this.precio != null && this.precio.replace(" ", "").length() == 0) {
			error = error + "- Ingrese el valor de la reserva.\n";
		}
		return error;
	} 
	
	private String regexs() {
		String error = "";
		if(this.precio != null && this.precio.replace(" ", "").length() > 0 && !this.precio.matches("[0-9]+")) {
			error = error + "- El valor de la reserva debe contener solo numeros.\n";
		}
		return error;
	}

	public String getError() {
		return error;
	}

	public boolean isValid() {
		return valid;
	}
}
