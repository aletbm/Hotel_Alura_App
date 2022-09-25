package hotel.model;

public class ValidarCampo {
	private String nombreCampo;
	private String contenido;
	private String error = "";
	private boolean valid = false;
	
	public ValidarCampo(String nombreCampo, String contenido, boolean isNumeric, boolean isOnlyLetters) {
		this.nombreCampo = nombreCampo;
		this.contenido = contenido;
		this.valid = validar(isNumeric, isOnlyLetters);
	}
	
	private boolean validar(boolean isNumeric, boolean isOnlyLetters) {
		
		if(this.contenido == null) {
			this.error = this.error + "- " + this.nombreCampo + " es nulo.\n";
		}
		else if(this.contenido != null && this.contenido.length() == 0) {
			this.error = this.error + "- " + this.nombreCampo + " se encuentra vacio.\n";
		}
		else {
			if(isNumeric && !isOnlyLetters) {
				if(!this.contenido.matches("[0-9]+")) {
					this.error = this.error + "- " + this.nombreCampo + " debe contener solo numeros.\n";
				}
			}
			else if(isNumeric && isOnlyLetters) {
				if(!this.contenido.matches("[0-9A-Za-z ._-]+")) {
					this.error = this.error + "- " + this.nombreCampo + " solo puede contener numeros, letras, espacios y los siguientes simbolos [._-].\n";
				}
			}
			else if(!isNumeric && isOnlyLetters) {
				if(!this.contenido.matches("[A-Za-z ]+")) {
					this.error = this.error + "- " + this.nombreCampo + " debe contener solo letras.\n";
				}
			}
		}
		if(this.error.compareTo("") != 0)
			return false;
		return true;
	}
	
	public String getError() {
		return this.error;
	}

	public boolean isValid() {
		return valid;
	}
}
