package model;

import java.util.Random;

public class Bandolero extends model.Character{
	
	private String tipo;
	private int maxRange, minRange;
	private int maxSalud, maxEstres;
	private Random rand = new Random();

	public Bandolero () {
		super(5, 4);
		this.tipo = "Bandolero";
		this.minRange = 2;
		this.maxRange = 4;
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
		return new Bandolero();
	}
	
	public String menu () {
		return "1 - Habilidad 1 - " + descripcionHabilidad1()+ 
		     "\n2 - Habilidad 2 - " + descripcionHabilidad2()+
			 "\n3 - Habilidad 3 - " + descripcionHabilidad3();
	}
	
	public void descansar () {
		this.salud = 5;
		this.estres = 4;
	}
	
	public int habilidad1 () {
		//Bandolero recupera 2 de salud y hace 1 de daño.
		super.salud += 2;
		return 1;
	}
	
	public int habilidad2 () {
		//Bandolero hace -1 de daño.
		return -1;
	}
	
	public int habilidad3 () {
		//Bandolero hace 3 de de daño si tirando un dado de 6 caras sale 4, 5 o 6.
		int r1 = rand.nextInt(6) + 1;
		if (r1 == 4 || r1 == 5 || r1 == 6) {
			return 3;
		} else return 0;
	}
	
	public String descripcionHabilidad1 () {
		return "Bandolero recupera 2 de salud y hace 1 de daño.";
	}
	
	public String descripcionHabilidad2 () {
		return "Bandolero hace -1 de daño.";
	}
	
	public String descripcionHabilidad3 () {
		return "Bandolero hace 3 de de daño si tirando un dado de 6 caras sale 4, 5 o 6.";
	}
	
}
