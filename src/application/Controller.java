package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.control.TextArea;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    private TextField tab_1_txt_nombre;

    @FXML
    private TextField tab_1_txt_ID;

    @FXML
    private TextField tab_1_txt_CuerpoAgua;

    @FXML
    private TextField tab_1_txt_Municipio;

    @FXML
    private TextField tab_1_txt_IRCA;

    @FXML
    private TextField tab_1_txt_TipoAgua;

    @FXML
    private Button btn_Ingresar;

    @FXML
    private Button btn_ObtenerDatos;

    @FXML
    private Button btn_ProcesarDatos;
    
    @FXML
    private TextArea tab_2_taMostrarDatos;

    @FXML
    private TextArea tab_2_taProcesarDatos;

    @FXML
    private TextField tab_3_txt_IDBuscar;

    @FXML
    private TextField tab_3_txt_ID;

    @FXML
    private TextField tab_3_txt_Nombre;

    @FXML
    private TextField tab_3_txt_Municipio;

    @FXML
    private TextField tab_3_txt_CuerpoAgua;

    @FXML
    private TextField tab_3_txt_TipoAgua;

    @FXML
    private TextField tab_3_txt_IRCA;

    @FXML
    private Button btn_buscar;

    @FXML
    private Button btn_Editar;

    @FXML
    private Button btn_Eliminar;

    @FXML
    void Tab_1_on_clic_btn_Ingresar(ActionEvent event) {
    	
    	try {
    		
	    	String name = tab_1_txt_nombre.getText();
	    	float idName = Float.parseFloat(tab_1_txt_ID.getText());
	    	float irca = Float.parseFloat(tab_1_txt_IRCA.getText());
	    	String municipio = tab_1_txt_Municipio.getText();
	    	String cuerpoAgua = tab_1_txt_CuerpoAgua.getText();
	    	String tipoAgua = tab_1_txt_TipoAgua.getText();

	    	if (name.isEmpty() == false && municipio.isEmpty() == false && cuerpoAgua.isEmpty() == false && tipoAgua.isEmpty() == false ) {
	    		CuerpoDeAgua.saveIngresar(name, idName, municipio, cuerpoAgua, tipoAgua, irca);
	    		tab_1_txt_nombre.setText("");
	        	tab_1_txt_ID.setText("");
	        	tab_1_txt_IRCA.setText("");
	        	tab_1_txt_Municipio.setText("");
	        	tab_1_txt_CuerpoAgua.setText("");
	        	tab_1_txt_TipoAgua.setText("");
	        	
	    	} else {
	    		System.out.println("Error: Campos nulos, o campos con datos errados");
	    	}
		} catch (Exception e) {
			System.out.println(e);
		}
    }
    

    @FXML
    void Tab_2_on_clic_btn_Obtener(ActionEvent event) {
        ArrayList<CuerpoDeAgua> objetos = new ArrayList<CuerpoDeAgua>();
        objetos = CuerpoDeAgua.getRegistros();
        for (CuerpoDeAgua objeto : objetos) {
            String line = String.join(" ", objeto.getNombre(), String.valueOf(objeto.getID()), objeto.getMunicipio(), objeto.getCuerpoAgua(), objeto.getTipoAgua(), String.valueOf(objeto.getIRCA()));
            line = line + "\n";
            tab_2_taMostrarDatos.appendText(line);
        }
    }

    @FXML
    void Tab_2_on_clic_btn_Procesar(ActionEvent event) {
        ArrayList<CuerpoDeAgua> objetos = new ArrayList<CuerpoDeAgua>();
        objetos = CuerpoDeAgua.getRegistros();
        for (CuerpoDeAgua objeto : objetos) {
        	tab_2_taProcesarDatos.appendText(objeto.nivel()+ "\n");
        }
		
		tab_2_taProcesarDatos.appendText(CuerpoDeAgua.ratingIrca() + "\n");
		tab_2_taProcesarDatos.appendText(CuerpoDeAgua.lowRisk());
		tab_2_taProcesarDatos.appendText(CuerpoDeAgua.printBiggerIrca() + "\n");
    }

    @FXML
    void Tab_3_on_clic_btn_Buscar(ActionEvent event) {
    	float id = Float.parseFloat(tab_3_txt_IDBuscar.getText());
    	String objeto = CuerpoDeAgua.getObjeto(id);
    	String [] data = objeto.split(" ");
    	tab_3_txt_Nombre.setText(data[0]);
    	tab_3_txt_ID.setText(data[1]);
    	tab_3_txt_IRCA.setText(data[2]);
    	tab_3_txt_Municipio.setText(data[3]);
    	tab_3_txt_CuerpoAgua.setText(data[4]);
    	tab_3_txt_TipoAgua.setText(data[5]);
    }

    @FXML
    void Tab_3_on_clic_btn_Editar(ActionEvent event) {
    	try {
    		
	    	String name = tab_3_txt_Nombre.getText();
	    	float idBusqueda = Float.parseFloat(tab_3_txt_IDBuscar.getText());
	    	float idName = Float.parseFloat(tab_3_txt_ID.getText());
	    	float irca = Float.parseFloat(tab_3_txt_IRCA.getText());
	    	String municipio = tab_3_txt_Municipio.getText();
	    	String cuerpoAgua = tab_3_txt_CuerpoAgua.getText();
	    	String tipoAgua = tab_3_txt_TipoAgua.getText();

	    	if (name.isEmpty() == false && municipio.isEmpty() == false && cuerpoAgua.isEmpty() == false && tipoAgua.isEmpty() == false ) {
	    		CuerpoDeAgua.editObjeto(name, idName, municipio, cuerpoAgua, tipoAgua, irca, idBusqueda);
	    		tab_3_txt_IDBuscar.setText("");
	        	tab_3_txt_Nombre.setText("");
	        	tab_3_txt_ID.setText("");
	        	tab_3_txt_IRCA.setText("");
	        	tab_3_txt_Municipio.setText("");
	        	tab_3_txt_CuerpoAgua.setText("");
	        	tab_3_txt_TipoAgua.setText("");
	    	} else {
	    		System.out.println("Error: Campos nulos, o campos con datos errados");
	    	}
		} catch (Exception e) {
			System.out.println(e);
		}
    }

    @FXML
    void Tab_3_on_clic_btn_Eliminar(ActionEvent event) {
    	String id = tab_3_txt_IDBuscar.getText();
    	CuerpoDeAgua.deleteObjeto(id);
    	tab_3_txt_IDBuscar.setText("");
    	tab_3_txt_Nombre.setText("");
    	tab_3_txt_ID.setText("");
    	tab_3_txt_IRCA.setText("");
    	tab_3_txt_Municipio.setText("");
    	tab_3_txt_CuerpoAgua.setText("");
    	tab_3_txt_TipoAgua.setText("");
    }

}
