package application;

public class ObjetoGeografico {
	
	private String name;
	private float idName;
	private float irca;
	private String municipio;
	private String cuerpoAgua;
	private String tipoAgua;

	public ObjetoGeografico(String nombre, float id, float irca, String municipio, String cuerpoAgua, String tipoAgua) {
		name = nombre;
		idName = id;
		this.irca = irca;
		this.municipio = municipio;
		this.cuerpoAgua = cuerpoAgua;
		this.tipoAgua = tipoAgua;
	}
}
