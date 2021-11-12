package model;

import java.util.ArrayList;

public class Zombie extends Humanoide {

	private int movimiento;
	private int danyo;
	private String tipo;
	
	public Zombie(String tipo, int salud, int movimiento, int danyo) {
		super(salud);
		this.movimiento = movimiento;
		this.danyo = danyo;
		this.tipo = tipo;
	}
	
	
	public String toString() {
		return this.tipo;
	}
	
	public int getMovimiento() {
		return this.movimiento;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void habilidadEspecial (ArrayList<Zombie> zombies, int index) {
		System.out.println("No hay habilidad especial");
	}
}
