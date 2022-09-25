package hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hotel.model.ValidarCampo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class RegistroUserController extends Controller {

	@FXML
	private TextField nameRegTextField, userRegTextField, apellidoRegTextField;
	
	@FXML
	private PasswordField passwordRegTextField;
	
	@FXML
	private Button registroBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void handlerRegistroBtn(ActionEvent event) throws IOException {
		event.consume();
		String user = this.userRegTextField.getText();
		String password = this.passwordRegTextField.getText();
		String name = this.nameRegTextField.getText();
		String apellido = this.apellidoRegTextField.getText();
		
		ValidarCampo vUser = new ValidarCampo("Usuario", user, true, true);
		ValidarCampo vPass = new ValidarCampo("Contraseña", password, true, true);
		ValidarCampo vName = new ValidarCampo("Nombre", name, false, true);
		ValidarCampo vApellido = new ValidarCampo("Apellido", apellido, false, true);
		
		if(vUser.isValid() && vPass.isValid() && vName.isValid() && vApellido.isValid()){
			String passEncrypt = this.spwd.encriptar(password);
			if(this.db.insertUser(name, apellido, user, passEncrypt)) {
				info.succesAlert();
				info.setMessage("Usuario registrado exitosamente.");
				info.setMessageContext("Por favor intente iniciar sesion.");
				info.show();
				changePage(this.registroBtn, "/hotel/view/login.fxml");
			}
			else {
				info.setType(AlertType.ERROR);
				info.setMessage("Oops! Hubo un error.");
				info.setMessageContext("El usuario ingresado ya existe.");
				info.show();
			}
		}
		else {
			String errores = vUser.getError() + vPass.getError() + vName.getError() + vApellido.getError();
			info.setType(AlertType.INFORMATION);
			info.setMessage("Se hallaron los siguiente errores:\n" + errores);
			info.setMessageContext("Revise los datos ingresados.");
			info.show();
		}
	}

}
