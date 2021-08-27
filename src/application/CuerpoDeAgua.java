package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ListIterator;

public class CuerpoDeAgua extends ObjetoGeografico{
	private String name;
	private float idName;
	private float irca;
	private String municipio;
	private String cuerpoAgua;
	private String tipoAgua;
	private static float biggerIrca = 0;
	private static float counter = 0;
	private static ArrayList<String> listName = new ArrayList<>();
	private static String url = "/home/juank/eclipse-workspace/Reto4/src/application/base_datos.db";


	public CuerpoDeAgua(String nombre, float id, float irca, String municipio, String cuerpoAgua, String tipoAgua) {
		super(nombre, id, irca, municipio, cuerpoAgua, tipoAgua);
		name = nombre;
		idName = id;
		this.irca = irca;
		this.municipio = municipio;
		this.cuerpoAgua = cuerpoAgua;
		this.tipoAgua = tipoAgua;
	}

	public String nivel() {

		String nonViable = "INVIABLE SANITARIAMENTE";
		String highValue = "ALTO";
		String middleValue = "MEDIO";
		String lowValueString = "BAJO";
		String riskFree = "SIN RIESGO";
		String idFrmt = String.format("%.02f", idName);
		String risk = "";

		if (irca >= 80.1 && irca <= 100) {
			risk = nonViable;
		}
		else if (irca >= 35.1 && irca <= 80) {
			risk = highValue;
		}
		else if (irca >= 14.1 && irca <= 35) {
			risk = middleValue;
		}
		else if (irca > 5 && irca <= 14) {
			counter++;
			listName.add(name);
			risk = lowValueString;
		}
		else if (irca >= 0 && irca <= 5) {
			counter++;
			risk = riskFree;
		}
		if (biggerIrca < irca) {
			biggerIrca = irca;			
		}
		return idFrmt + " " + risk;
	}

	public static String ratingIrca() {
		String counterFrmt = String.format("%.02f", counter);
		return counterFrmt;
	}

	public static String lowRisk() {
		String respuesta = "";
		if (listName.isEmpty()) {
			return "NA";
		}
		else {
			if (listName.size() > 1);{
	            ListIterator<String>
                iterator = listName.listIterator();
	            while (iterator.hasNext()){
					respuesta = respuesta + iterator.next() + " ";
				}
			}
		}
		return "".concat(respuesta) + "\n";
	}

	public static String printBiggerIrca() {
		String aux = String.format("%.02f", biggerIrca);
		return aux;
	}
	
	public static void saveIngresar(String name, float idName, String municipio, String cuerpoAgua, String tipoAgua, float irca) {
        Conexion con = new Conexion(url);
        con.conectar();
        Connection conexion = con.getConexion();
        PreparedStatement stmt;
        String query = "INSERT INTO cuerpodeagua "
                     + "(id, nombre, municipio, tipoCuerpoAgua, tipoAgua, irca) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
		try {
            stmt = conexion.prepareStatement(query);
            stmt.setFloat(1, idName);
            stmt.setString(2, name);
            stmt.setString(3, municipio);
            stmt.setString(4, cuerpoAgua);
            stmt.setString(5, tipoAgua);
            stmt.setFloat(6, irca);

            stmt.executeUpdate();
            con.cerrarConexion();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ArrayList<CuerpoDeAgua> getRegistros(){
        Conexion con = new Conexion(url);
        con.conectar();
        Connection conexion = con.getConexion();
        Statement stmt;
		ArrayList<CuerpoDeAgua> listado = new ArrayList<CuerpoDeAgua>();
		String query = "SELECT * FROM cuerpodeagua";
		try {
			stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	CuerpoDeAgua data = new CuerpoDeAgua(rs.getString("nombre"),
                                     rs.getFloat("id"),
                                     rs.getFloat("irca"),
                                     rs.getString("municipio"),
                                     rs.getString("tipoCuerpoAgua"),
                                     rs.getString("tipoAgua"));
            	listado.add(data);
            }
            con.cerrarConexion();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return listado;
	}
	
	public static String getObjeto(float id) {
		
        Conexion con = new Conexion(url);
        con.conectar();
        Connection conexion = con.getConexion();
        Statement stmt;
        String objeto = "";
		String query = "SELECT * FROM cuerpodeagua WHERE id="+ id;
		try {
			stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            objeto =  rs.getString("nombre") + " " + rs.getFloat("id") + " " + rs.getFloat("irca") + " " + rs.getString("municipio") + " " +
                                     rs.getString("tipoCuerpoAgua") + " " + rs.getString("tipoAgua");
            con.cerrarConexion();
		} catch (Exception e) {
			System.out.println(e);
		}
		return objeto;
	}
	
    public static void deleteObjeto(String id) {
        Conexion con = new Conexion(url);
        con.conectar();
        Connection conexion = con.getConexion();
        String query = "DELETE FROM cuerpodeagua WHERE id =" + id;

        try {
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.executeUpdate();
            con.cerrarConexion();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void editObjeto(String name, float idName, String municipio, String cuerpoAgua, String tipoAgua, float irca, float idBusqueda) {
        Conexion con = new Conexion(url);
        con.conectar();
        Connection conexion = con.getConexion();
        PreparedStatement stmt;
        String query = "UPDATE cuerpodeagua SET "
                     + "id = ?, nombre = ?, municipio = ?, tipoCuerpoAgua = ?, tipoAgua = ?, irca = ? "
                     + "WHERE id= ?";
		try {
            stmt = conexion.prepareStatement(query);
            stmt.setFloat(1, idName);
            stmt.setString(2, name);
            stmt.setString(3, municipio);
            stmt.setString(4, cuerpoAgua);
            stmt.setString(5, tipoAgua);
            stmt.setFloat(6, irca);
            stmt.setFloat(7, idBusqueda);

            stmt.executeUpdate();
            con.cerrarConexion();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
	
	/* GET AND SET METHODS */

	public String getNombre() {
		return name;
	}
	
	public float getID() {
		return idName;
	}

	public float getIRCA() {
		return irca;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public String getCuerpoAgua() {
		return cuerpoAgua;
	}
	
	public String getTipoAgua() {
		return tipoAgua;
	}
}