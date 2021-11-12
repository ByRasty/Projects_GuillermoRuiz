package model;

public class Humanoide {

	private String nombre;
	private int salud;
	private int saludMaxima;
	private boolean estaVivo;
	
	public Humanoide(int salud) {
		this.salud = salud;
	}
	
	public Humanoide(String nombre, int salud) {
		this.nombre = nombre;
		this.salud = salud;
		this.saludMaxima = salud;
		this.estaVivo = true;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getSalud() {
		return this.salud;
	}
	
	//Resta puntos de salud de un jugador 
	public int restarSalud(int a) {
		this.salud -= a;
		return a;
	}
	
}
