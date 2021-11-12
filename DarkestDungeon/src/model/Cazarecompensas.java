package model;

import java.util.Random;

public class Cazarecompensas extends model.Character{
	
	private String tipo;
	private int maxRange, minRange;
	private int maxSalud, maxEstres;
	private Random rand = new Random();

	public Cazarecompensas () {
		super(3, 3);
		this.tipo = "Cazarecompensas";
		this.minRange = 2;
		this.maxRange = 3;
		this.maxSalud = 3;
		this.maxEstres = 4;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public int getMaxRange () {
		return this.maxRange;
	}
	
	public int getMinRange () {
		return this.minRange;
	}
	
	public int getMaxSalud() {
		return this.maxSalud;
	}
	
	public int getMaxEstres() {
		return this.maxEstres;
	}
	
	public String toString () {
		return "[tipo = " + this.tipo + ", salud = " + super.salud + ", estres = " + super.estres + "]";
	}
	
	public model.Character copy() {
		return new Cazarecompensas();
	}
	
	public String menu () {
		return "1 - Habilidad 1 - " + descripcionHabilidad1()+ 
		     "\n2 - Habilidad 2 - " + descripcionHabilidad2()+
			 "\n3 - Habilidad 3 - " + descripcionHabilidad3();
	}
	
	public void descansar () {
		this.salud = 3;
		this.estres = 3;
	}
	
	public int habilidad1 () {
		//Cazarecompensas recupera 1 de estres y hace 1 daño.
		super.estres += 1;
		return 1;
	}
	
	public int habilidad2 () {
		//Cazarecompensas lanza dos dados de 6 caras, si la suma es da 7, hace -5 de daño.
		int r1 = rand.nextInt(6) + 1, r2 = rand.nextInt(6) + 1;
		if (r1+r2 == 7) return -5;
		else return 0;
	}
	
	public int habilidad3 () {
		//Cazarecompensas hace -1 de daño.
		return -1;
	}
	
	public String descripcionHabilidad1 () {
		return "Cazarecompensas recupera 1 de estres y hace 1 daño.";
	}
	
	public String descripcionHabilidad2 () {
		return "Cazarecompensas lanza dos dados de 6 caras, si la suma es da 7, hace -5 de daño.";
	}
	
	public String descripcionHabilidad3 () {
		return "Cazarecompensas hace -1 de daño.";
	}
	
}
