package modelo;

public class Objeto {

	private String tipo;
	private int cantidad;
	
	public Objeto(String tipo, int cantidad) {
		this.tipo = tipo;
		this.cantidad = cantidad;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public String toString() {
		return "Objeto -> " + this.tipo + " (" + this.cantidad + ")";
	}
	
}
