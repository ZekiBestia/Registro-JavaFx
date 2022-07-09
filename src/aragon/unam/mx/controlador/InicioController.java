package aragon.unam.mx.controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import aragon.unam.mx.modelo.Persona;
import aragon.unam.mx.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.scene.control.TextField;

public class InicioController extends BaseController implements Initializable {
	private static final String RadioButton = null;
	private String mensajes = "";

	private ObservableList<Persona> datos = FXCollections.observableArrayList();

	@FXML
	private ToggleGroup estadoCivil;

	@FXML
	private RadioButton rdbCasado;

	@FXML
	private RadioButton rdbFemenino;

	@FXML
	private RadioButton rdbSoltero;

	@FXML
	private RadioButton rdbUnionLibre;

	@FXML
	private RadioButton rdbMasculino;
	@FXML
	private TextField txtCurp;
	@FXML
	private ComboBox<String> cbbEjemplo;
	@FXML
	private Button btnAlmacenar;

	@FXML
	private Button btnSalir;

	@FXML
	private ChoiceBox<String> chcCredito;

	@FXML
	private ChoiceBox<String> chcEstudio;

	@FXML
	private ChoiceBox<String> chcNacionalidad;

	@FXML
	private ChoiceBox<String> chcSexo;

	@FXML
	private DatePicker dtpFechaNacimiento;

	@FXML
	private TableColumn<Persona, String> tbcAccion;

	@FXML
	private TableColumn<Persona, String> tbcCuenta;

	@FXML
	private TableColumn<Persona, Float> tbcMonto;

	@FXML
	private TableColumn<Persona, String> tbcNombre;

	@FXML
	private TableView<Persona> tbwTabla;

	@FXML
	private TextField txtApellidoMaterno;

	@FXML
	private TextField txtApellidoPaterno;

	@FXML
	private TextField txtCasa;

	@FXML
	private TextField txtCelular;

	@FXML
	private TextField txtCuenta;

	@FXML
	private TextField txtMonto;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtRfc;

	@FXML
	void almacenar(ActionEvent event) {
		if(this.verificar()) {
		Persona captura = new Persona();
		captura.nombreProperty().set(txtNombre.getText());
		captura.montoProperty().set(Float.parseFloat(txtMonto.getText()));
		captura.cuentaProperty().set(txtCuenta.getText());
		captura.getCredito().set(chcCredito.getValue());
		captura.getApellidoPaterno().set(txtApellidoPaterno.getText());
		captura.getApellidoMaterno().set(txtApellidoMaterno.getText());
		captura.getRfc().set(txtRfc.getText());
		captura.getNacionalidad().set(chcNacionalidad.getValue());
		captura.getTelefonoCelular().set(txtCelular.getText());
		datos.add(captura);
		this.limpiar();
		this.cerrarVentana(btnSalir);
		}else {
			this.ventanaEmergente("ERROR", "Coloque bien los datos", this.mensajes);
		}
	}

	@SuppressWarnings("unchecked")
	private void limpiar() {
		this.txtNombre.clear();
		this.chcCredito.getItems().clear();
		this.txtApellidoMaterno.clear();
		this.txtApellidoPaterno.clear();
		this.txtCuenta.clear();
		this.txtCurp.clear();
		this.txtRfc.clear();
		this.txtCelular.clear();
		this.txtMonto.clear();
		this.chcCredito.getItems().clear();
		this.chcNacionalidad.getItems().clear();
		this.dtpFechaNacimiento.getEditor().clear();
		this.rdbCasado.setSelected(false);
		this.rdbCasado.requestFocus();
		this.rdbCasado.getTypeSelector();

	}

	@FXML
	void salir(ActionEvent event) {
		this.cerrarVentana(btnSalir);
	}
	
	void accionVerificar(ActionEvent event) {
		if (this.verificar() && verificacion) {
			System.out.println("Valido");
		} else {
			this.mensajes += "Ningun campo debe estar en rojo";
			this.ventanaEmergente("Datos no validos", "Por favor valida la información,", mensajes);
			this.mensajes = "";
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.verificarEntrada(txtRfc, TipoError.RFC);
		this.verificarEntrada(txtNombre, TipoError.IDENTIFICACION);
		this.verificarEntrada(txtApellidoPaterno, TipoError.IDENTIFICACION);
		this.verificarEntrada(txtApellidoMaterno, TipoError.IDENTIFICACION);
		this.verificarEntrada(txtCelular, TipoError.CELULAR);
		this.verificarEntrada(txtCurp, TipoError.CURP);
		this.verificarEntrada(txtCuenta, TipoError.CUENTA);
		this.verificarEntrada(txtMonto, TipoError.MONTO);
		this.chcNacionalidad.getItems().addAll("Canadiense", "Estadounidense", "Alemana", "Rusa");
		this.chcCredito.getItems().addAll("12", "6", "28");
		this.rdbFemenino.setUserData(new RadioButton("Ana"));
		this.rdbMasculino.setUserData(new RadioButton("Pedro"));
		this.rdbCasado.setUserData(new RadioButton("casado"));
		this.rdbSoltero.setUserData(new RadioButton("soltero"));
		this.rdbUnionLibre.setUserData(new RadioButton("libre"));
		// this.dtpFechaNacimiento.getEditor();
		// this.dtpFechaNacimiento.getValue(new DatePicker(LocalDate.now());
		this.tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.tbcNombre.setCellFactory(TextFieldTableCell.forTableColumn());
		this.tbcNombre.setOnEditCommit(t -> {
			((Persona) t.getTableView().getItems().get(t.getTablePosition().getRow())).nombreProperty()
					.set(t.getNewValue());
		}

		);
		this.tbcCuenta.setCellValueFactory(new PropertyValueFactory<>("cuenta"));
		this.tbcCuenta.setCellFactory(TextFieldTableCell.forTableColumn());
		this.tbcCuenta.setOnEditCancel(t -> {
			((Persona) t.getTableView().getItems().get(t.getTablePosition().getRow())).cuentaProperty()
					.set(t.getNewValue());
			// this.tbwTabla.refresh();
		});

		this.tbcMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
		this.tbcMonto.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		this.tbcMonto.setOnEditCommit(t -> {
			((Persona) t.getTableView().getItems().get(t.getTablePosition().getRow())).montoProperty()
					.set(t.getNewValue());
			this.tbwTabla.refresh();
		});

		this.tbwTabla.setRowFactory(tv -> {

			TableRow<Persona> row = new TableRow<>();
			row.itemProperty().addListener((obs, entradaAnterior, entradaActual) -> {
				if (entradaActual != null) {
					row.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected-row"),
							entradaActual.montoProperty().get() <= 0.0f);
				}
			});
			return row;
		});

		Callback<TableColumn<Persona, String>, TableCell<Persona, String>> celda = (
				TableColumn<Persona, String> parametros) -> {
			final TableCell<Persona, String> cel = new TableCell<Persona, String>() {

				@Override
				protected void updateItem(String arg0, boolean arg1) {
					// TODO Auto-generated method stub
					super.updateItem(arg0, arg1);
					if (arg1) {
						setGraphic(null);
						setText(null);
					} else {
						Image img = new Image(getClass().getResourceAsStream("/aragon/unam/mx/recursos/boton.jpg"));
						Button button = new Button();
						button.setGraphic(new ImageView(img));
						button.setOnMouseClicked((MouseEvent evento) -> {
							int fila = tbwTabla.getSelectionModel().getSelectedIndex();
							if (fila != -1) {
								datos.remove(fila);
							}

						});

						HBox hbox = new HBox(button);
						hbox.setStyle("-fx-alignment:center");
						HBox.setMargin(button, new Insets(2, 2, 0, 3));
						setGraphic(hbox);
						setText(null);
					}
				}

			};
			return cel;
		};

		this.tbcAccion.setCellFactory(celda);
		this.tbwTabla.setItems(datos);

	}
	private boolean verificar() {
		boolean valido = true;
		if (this.txtCuenta.getText() == null || this.txtCuenta.getText().trim().isEmpty()) {
			valido = false;
			this.mensajes += "Número de Cuenta no valido\n";
		}
		
		if ((this.txtCuenta.getText() == null)
				|| (this.txtCuenta.getText() != null && !this.txtCuenta.getText().isEmpty())) {
			if (!this.cuentaValido) {
				this.mensajes += "La cuenta no es valida, tienen que ser 9 digitos\n";
				valido = false;
			}
		}
		
		
		if (this.chcCredito.getSelectionModel().getSelectedIndex() == -1) {
			valido = false;
			this.mensajes += "Seleccione un plazo\n";
		}
		
		if (!this.rdbFemenino.isSelected() && !this.rdbMasculino.isSelected()) {
			valido = false;
			this.mensajes += "Seleccione un sexo\n";
		}
		
		
		if (!this.rdbCasado.isSelected() && !this.rdbSoltero.isSelected() && !this.rdbUnionLibre.isSelected()) {
			valido = false;
			this.mensajes += "Seleccione un sexo\n";
		}
		if ((this.txtNombre.getText() == null)
				|| (this.txtNombre.getText() != null && this.txtNombre.getText().isEmpty())) {
			this.mensajes += "El nombre no es valido.Complete el espacio\n";
			valido = false;
		}

		if ((this.txtApellidoPaterno.getText() == null) || (this.txtApellidoPaterno.getText().isEmpty())) {
			this.mensajes += "El apellido paterno no es valido , complete el espacio\n";
			valido = false;
		}

		if ((this.txtApellidoPaterno.getText() == null)
				|| (this.txtApellidoPaterno.getText() != null && !this.txtApellidoPaterno.getText().isEmpty())) {
			if (!this.identificacionValida) {
				this.mensajes += "El Apellido paterno no es valido.Solo escribe un apellido y no se permiten numeros\n";
				valido = false;
			}
		}

		if ((this.txtApellidoMaterno.getText() == null)
				|| (this.txtApellidoMaterno.getText() != null && this.txtApellidoMaterno.getText().isEmpty())) {
			this.mensajes += "El apellido materno no es valido , complete el espacio\n";
			valido = false;
		}
		if ((this.txtApellidoMaterno.getText() == null)
				|| (this.txtApellidoMaterno.getText() != null && !this.txtApellidoMaterno.getText().isEmpty())) {
			if (!this.identificacionValida) {
				this.mensajes += "El apellido, esta mal estructurado.Solo escribe un apellido y no se permiten numeros\n";
				valido = false;
			}
		}
		
		if ((this.txtRfc.getText() == null) || (this.txtRfc.getText() != null && this.txtRfc.getText().isEmpty())) {
			this.mensajes += "El RFC no es valido , complete el espacio\n";
			valido = false;
		}
		if ((this.txtRfc.getText() == null) || (this.txtRfc.getText() != null && !this.txtRfc.getText().isEmpty())) {
			if (!this.rfcValido) {
				this.mensajes += "El RFC no es valido , minimo=13 , maximo=13 caracteres\n";
				valido = false;
			}

		}
		
		if ((this.txtMonto.getText() == null) || (this.txtMonto != null && this.txtMonto.getText().isEmpty())) {
			this.mensajes += "El monto no es valido, complete el campo\n";
			valido = false;
		}
		if ((this.txtMonto.getText() == null) || (this.txtMonto != null && !this.txtMonto.getText().isEmpty())) {
			try {
				if (!this.montoValido) {
					throw new NumberFormatException();
				}
				Float.parseFloat(this.txtMonto.getText());
			} catch (NumberFormatException ex) {
				this.mensajes += "El monto no es valido, debe de contener decimales\n";
				valido = false;
			}

		}
		
		if ((this.txtCelular.getText() == null)
				|| (this.txtCelular.getText() != null && this.txtCelular.getText().isEmpty())) {
			this.mensajes += "El celular no es valido , complete el espacio\n";
			valido = false;
		}
		if ((this.txtCelular.getText() == null)
				|| (this.txtCelular.getText() != null && !this.txtCelular.getText().isEmpty())) {
			if (!this.celularValido) {
				this.mensajes += "El celular no es valido, minimo 10 digitos , maximo 10 digitos\n";
				valido = false;
			}
		}
		if ((this.txtCurp.getText() == null)
				|| (this.txtCurp.getText() != null && this.txtCurp.getText().isEmpty())) {
			this.mensajes += "El celular no es valido , complete el espacio\n";
			valido = false;
		}
		if ((this.txtCurp.getText() == null)
				|| (this.txtCurp.getText() != null && !this.txtCurp.getText().isEmpty())) {
			if (!this.curpValido) {
				this.mensajes += "La curp no es valida, EJEMPLO: SIHC400128HDFLLR01\n";
				valido = false;
			}
		}
		
		
		return valido;
	}
	

}
