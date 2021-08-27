package application;
import java.sql.*;
public class Conexion {
	private Connection conexion;
	private String url;
	
	public Conexion(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	public void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + url);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
