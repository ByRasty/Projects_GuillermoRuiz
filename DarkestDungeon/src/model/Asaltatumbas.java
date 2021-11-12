package model;

import java.util.Random;

public class Asaltatumbas extends model.Character{
	
	private String tipo;
	private int maxRange, minRange;
	private int maxSalud, maxEstres;
	private Random rand = new Random();

	public Asaltatumbas () {
		super(4, 5);
		this.tipo = "Asaltatumbas";
		this.minRange = 1;
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
		return new Asaltatumbas();
	}
	
	public String menu () {
		return "1 - Habilidad 1 - " + descripcionHabilidad1()+ 
		     "\n2 - Habilidad 2 - " + descripcionHabilidad2()+
			 "\n3 - Habilidad 3 - " + descripcionHabilidad3();
	}
	
	public void descansar () {
		this.salud = 4;
		this.estres = 5;
	}
	
	public int habilidad1 () {
		//Asaltatumbas hace 3 de daño si tirando un dado de 6 caras sale 1, 2 o 3. 
		int r1 = rand.nextInt(6) + 1;
		if (r1 == 1 || r1 == 2 || r1 == 3) {
			return 3;
		} else return 0;
	}
	
	public int habilidad2 () {
		//Asaltatumbas hace 2 de daño.
		return 2;
	}
	
	public int habilidad3 () {
		//Asaltatumbas hace -2 de daño si tirando un dado de 6 caras sale 5 o 6.
		int r1 = rand.nextInt(6) + 1;
		if (r1 == 5 || r1 == 6) {
			return -2;
		} else return 0;
	}
	
	public String descripcionHabilidad1 () {
		return "Asaltatumbas hace 3 de daño si tirando un dado de 6 caras sale 1, 2 o 3. ";
	}
	
	public String descripcionHabilidad2 () {
		return "Asaltatumbas hace 2 de daño.";
	}
	
	public String descripcionHabilidad3 () {
		return "Asaltatumbas hace -2 de daño si tirando un dado de 6 caras sale 5 o 6.";
	}
	
}
