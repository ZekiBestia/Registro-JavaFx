package aragon.unam.mx.controlador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aragon.unam.mx.modelo.*;
import javafx.css.PseudoClass;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
public class BaseController {
	protected boolean verificacion = false;
	protected boolean cuentaValido = true;
	protected boolean identificacionValida = true;
	protected boolean rfcValido = true;
	protected boolean curpValido = true;
	protected boolean celularValido = true;
	protected boolean montoValido=true;
	
	private String [] expresiones = {
		"(\\d){9}","(\\w+)","(\\w){13}","^[A-Z]{1}[AEIOU]{1}[A-Z]{2}\n"
				+ "[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])\n"
				+ "[HM]{1}\n"
				+ "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)\n"
				+ "[B-DF-HJ-NP-TV-Z]{3}\n"
				+ "[0-9A-Z]{1}\n"
				+ "[0-9]{1}$","(\\d){10}","(\\d+)(\\.\\d{1,2})"
				
				
			
	};
	
	public void verificarEntrada(TextField caja, TipoError error) {
		caja.textProperty().addListener(evento -> {
			String text = caja.getText();
			if (text == null) {
				text = "";

			}
			String patron = expresiones[error.ordinal()];
			Pattern pt = Pattern.compile(patron);
			Matcher match = pt.matcher(text);
			caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());
			
			if(error==TipoError.CUENTA) {
				this.cuentaValido=match.matches();	
			}else if (error==TipoError.IDENTIFICACION) {
				this.identificacionValida=match.matches();
			}else if(error==TipoError.RFC) {
				this.rfcValido=match.matches();
			}else if(error==TipoError.CURP) {
				this.curpValido=match.matches();
			}else if(error==TipoError.CELULAR) {
				this.celularValido=match.matches();
			}else if(error==TipoError.MONTO) {
				this.montoValido=match.matches();
			}
		});
	}
	public void ventanaEmergente(String titulo, String encabezado, String contenido) {
		Alert alerta;
		alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();

	}
	public void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();

	}
	
}
