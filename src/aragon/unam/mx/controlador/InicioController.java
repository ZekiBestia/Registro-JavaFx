package aragon.unam.mx.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import aragon.unam.mx.modelo.Persona;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.scene.control.TextField;

public class InicioController implements Initializable {
	private ObservableList<Persona> datos = FXCollections.observableArrayList();

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
		Persona captura = new Persona();
		captura.nombreProperty().set(txtNombre.getText());
		captura.montoProperty().set(Float.parseFloat(txtMonto.getText()));
		captura.cuentaProperty().set(txtCuenta.getText());
		captura.getCredito().set(chcCredito.getValue());
		captura.getApellidoPaterno().set(txtApellidoPaterno.getText());
		captura.getApellidoMaterno().set(txtApellidoMaterno.getText());
		//captura.getFechaNacimiento().set(dtpFechaNacimiento.getAccessibleText());
		//captura.getNacionalidad().set(chcNacionalidad.getValue());
		//captura.getSexo().set(chcSexo.getValue());
		//captura.getGradoEstudios().set(chcEstudio.getValue());
		//captura.getTelefonoCasa().set(txtCasa.getText());
		captura.getTelefonoCelular().set(txtCelular.getText());
		captura.getRfc().set(txtRfc.getText());
		datos.add(captura);
		this.limpiar();

	}

	@SuppressWarnings("unchecked")
	private void limpiar() {
		this.txtNombre.clear();
		this.chcCredito.getItems().clear();
		this.txtApellidoMaterno.clear();
		this.txtApellidoPaterno.clear();
		this.txtCuenta.clear();
		//this.txtCasa.clear();
		this.txtCelular.clear();
		this.txtMonto.clear();
		//this.chcCredito.getItems().clear();
		//this.chcEstudio.getItems().clear();
		//this.chcNacionalidad.getItems().clear();
		//this.dtpFechaNacimiento.getEditor().clear();
		this.txtRfc.clear();
		//this.chcSexo.getItems().clear();

	}

	@FXML
	void salir(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.chcCredito.getItems().addAll("12","6","28");
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
			//this.tbwTabla.refresh();
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
						Image img=new Image(getClass().getResourceAsStream("/aragon/unam/mx/recursos/boton.jpg"));
						Button button = new Button();
						button.setGraphic(new ImageView(img));						
						button.setOnMouseClicked((MouseEvent evento) -> {							
							int fila=tbwTabla.getSelectionModel().getSelectedIndex();
							if(fila!=-1) {
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

}
