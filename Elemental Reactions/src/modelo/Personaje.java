package modelo;

public class Personaje {
	
	private String nombre;
	private String elemento;

	public Personaje (String[] infoP) {
		this.nombre = infoP[0];
		this.elemento = infoP[1];
	}

	public String getNombre() {
		return nombre;
	}

	public String getElemento() {
		return elemento;
	}
	
}
