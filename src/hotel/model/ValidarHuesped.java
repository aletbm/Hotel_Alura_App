package hotel.model;

import java.time.LocalDate;

public class ValidarHuesped {
	private String nameHuesped = null;
	private String lnameHuesped = null;
	private String nacionalidad = null;
	private String telefono = null;
	private String nroReserva = null;
	private String dateNac = null;
	private String error = "";
	private boolean valid = false;
	
	public ValidarHuesped(String nameHuesped, String lnameHuesped, LocalDate dateNac, String nacionalidad, String telefono, String nroReserva) {
		this.nameHuesped = nameHuesped;
		this.lnameHuesped = lnameHuesped;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.nroReserva = nroReserva;
		if(dateNac != null)
			this.dateNac = dateNac.toString();
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
		if(this.nameHuesped == null) {
			error = error + "- Ingrese el nombre de huesped.\n";
		}
		if(this.lnameHuesped == null) {
			error = error + "- Ingrese el apellido del huesped.\n";
		}
		if(this.dateNac == null) {
			error = error + "- Seleccione una fecha de nacimiento.\n";
		}
		if(this.nacionalidad == null) {
			error = error + "- Seleccione una nacionalidad.\n";
		}
		if(this.telefono == null) {
			error = error + "- Ingrese un telefono.\n";
		}
		if(this.nroReserva == null) {
			error = error + "- Vuelva atras y genere el numero de reserva.\n";
		}
		return error;
	}
	
	private String vacios() {
		String error = "";
		if(this.nameHuesped != null && this.nameHuesped.replace(" ", "").length() == 0) {
			error = error + "- Ingrese el nombre de huesped.\n";
		}
		if(this.lnameHuesped != null && this.lnameHuesped.replace(" ", "").length() == 0) {
			error = error + "- Ingrese el apellido del huesped.\n";
		}
		if(this.telefono != null && this.telefono.replace(" ", "").length() == 0) {
			error = error + "- Ingrese un telefono.\n";
		}
		if(this.nroReserva != null && this.nroReserva.replace(" ", "").length() == 0) {
			error = error + "- Vuelva atras y genere el numero de reserva.\n";
		}
		return error;
	} 
	
	private String regexs() {
		String error = "";
		if(this.nameHuesped != null && this.nameHuesped.replace(" ", "").length() > 0 && !this.nameHuesped.matches("[A-Za-z ]+")) {
			error = error + "- El nombre solo debe contemplar letras y espacios.\n";
		}
		if(this.lnameHuesped != null && this.lnameHuesped.replace(" ", "").length() > 0 && !this.lnameHuesped.matches("[A-Za-z ]+")) {
			error = error + "- El apellido solo debe contemplar letras y espacios.\n";
		}
		if(this.telefono != null && this.telefono.replace(" ", "").length() > 0 && (!this.telefono.matches("[0-9]+") || this.telefono.replace(" ", "").length() > 15)) {
			error = error + "- El telefono puede contemplar hasta 15 caracteres numericos.\n";
		}
		if(this.nroReserva != null && this.nroReserva.replace(" ", "").length() > 0 && !this.nroReserva.matches("[0-9]+")) {
			error = error + "- Vuelva atras y genere el numero de reserva.\n";
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
