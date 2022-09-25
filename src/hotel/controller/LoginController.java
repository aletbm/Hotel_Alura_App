package hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController extends Controller{

	@FXML
	private Button entrarBtn, registrarseBtn;
	
	@FXML
	private TextField userTextField;
	
	@FXML
	private PasswordField passwordTextField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void handlerEntrarBtn(ActionEvent event) throws IOException {
		event.consume();
		String user = this.userTextField.getText();
		String password = this.passwordTextField.getText();
		boolean showDialog = false;
		if(user.length() != 0 && password.length() != 0) {
			List<Map<String, String>> users = this.db.getLoginUserData(user);
			if(users.toArray().length == 0) {
				showDialog = true;
			}
			else {
				String passDB = this.spwd.desencriptar(users.get(0).get("PASSWORD"));
				if(password.compareTo(passDB) != 0) {
					showDialog = true;
				}
				else {
					changePage(this.entrarBtn, "/hotel/view/menuUser.fxml");
				}
			}
		}
		else {
			info.setType(AlertType.INFORMATION);
			info.setMessage("Usuarios o contraseña vacios.");
			info.setMessageContext("Ingrese los datos solicitados.");
			info.show();
		}
		
		if(showDialog) {
			info.setType(AlertType.INFORMATION);
			info.setMessage("Usuarios o contraseña incorrectos.");
			info.setMessageContext("Revise los datos ingresados.");
			info.show();
		}
	}
	
	public void handlerRegistrarseBtn(ActionEvent event) throws IOException {
		event.consume();
		changePage(this.registrarseBtn, "/hotel/view/register.fxml");
	}

}
