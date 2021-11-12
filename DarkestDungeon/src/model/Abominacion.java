package model;

import java.util.Random;

public class Abominacion extends model.Character{
	
	private String tipo;
	private int maxRange, minRange;
	private int maxSalud, maxEstres;
	private Random rand = new Random();
	
	public Abominacion () {
		super(3, 4);
		this.tipo = "Abominacion";
		this.minRange = 1;
		this.maxRange = 2;
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
		return new Abominacion();
	}
	
	public String menu () {
		return "1 - Habilidad 1 - " + descripcionHabilidad1()+ 
		     "\n2 - Habilidad 2 - " + descripcionHabilidad2()+
			 "\n3 - Habilidad 3 - " + descripcionHabilidad3();
	}
	
	public void descansar () {
		this.salud = 3;
		this.estres = 4;
	}
	
	public int habilidad1 () {
		//Abominacion hace 3 de daño.
		return 3;
	}
	
	public int habilidad2 () {
		//Abominacion devuelve el resultado de la suma de 2 dados de 6 caras como daño.
		return (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1);
	}
	
	public int habilidad3 () {
		//Abominacion hace -1 de daño.
		return -1;
	}
	
	public String descripcionHabilidad1 () {
		return "Abominacion hace 3 de daño.";
	}
	
	public String descripcionHabilidad2 () {
		return "Abominacion devuelve el resultado de la suma de 2 dados de 6 caras como daño.";
	}
	
	public String descripcionHabilidad3 () {
		return "Abominacion hace -1 de daño.";
	}	
	
}
