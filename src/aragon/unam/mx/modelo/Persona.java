package aragon.unam.mx.modelo;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
	private final FloatProperty monto = new SimpleFloatProperty();
	private final StringProperty cuenta = new SimpleStringProperty();
	private final StringProperty nombre = new SimpleStringProperty();
	private final StringProperty apellidoPaterno = new SimpleStringProperty();
	private final StringProperty apellidoMaterno = new SimpleStringProperty();
	private final StringProperty fechaNacimiento = new SimpleStringProperty();
	private final StringProperty pais = new SimpleStringProperty();
	private final StringProperty nacionalidad = new SimpleStringProperty();
	private final StringProperty rfc = new SimpleStringProperty();
	private final StringProperty curp = new SimpleStringProperty();
	private final StringProperty gradoEstudios = new SimpleStringProperty();
	private final StringProperty telefonoCasa = new SimpleStringProperty();
	private final StringProperty telefonoCelular = new SimpleStringProperty();
	private final StringProperty sexo = new SimpleStringProperty();
	private final StringProperty credito = new SimpleStringProperty();
	
	public final StringProperty getCredito() {
		return credito;
	}
	public final FloatProperty montoProperty() {
		return monto;
	}
	public final StringProperty cuentaProperty() {
		return cuenta;
	}
	public final StringProperty nombreProperty() {
		return nombre;
	}
	public final StringProperty getApellidoPaterno() {
		return apellidoPaterno;
	}
	public final StringProperty getApellidoMaterno() {
		return apellidoMaterno;
	}
	public final StringProperty getFechaNacimiento() {
		return fechaNacimiento;
	}
	public final StringProperty getPais() {
		return pais;
	}
	public final StringProperty getNacionalidad() {
		return nacionalidad;
	}
	public final StringProperty getRfc() {
		return rfc;
	}
	public final StringProperty getCurp() {
		return curp;
	}
	public final StringProperty getGradoEstudios() {
		return gradoEstudios;
	}
	public final StringProperty getTelefonoCasa() {
		return telefonoCasa;
	}
	public final StringProperty getTelefonoCelular() {
		return telefonoCelular;
	}
	public final StringProperty getSexo() {
		return sexo;
	}


}
