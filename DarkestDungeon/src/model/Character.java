package model;

public class Character {

	protected int salud;
	protected int estres;
	protected boolean vivo;
	
	public Character (int salud, int estres) {
		this.salud = salud;
		this.estres = estres;
		this.vivo = true;
	}
	
	public String getTipo() {
		return null;
	}
	
	public int getSalud() {
		return salud;
	}

	public int getEstres() {
		return estres;
	}
	
	public int getMaxRange () {
		return 0;
	}
	
	public int getMinRange () {
		return 0;
	}
	
	public int getMaxSalud () {
		return 0;
	}
	
	public int getMaxEstres () {
		return 0;
	}
	
	public boolean estaVivo () {
		if (this.vivo) return true;
		else return false;
	}
	
	public String toString () {
		return "[salud = " + salud + ", estres = " + estres + "]";
	}
	
	public model.Character copy() {
		return this;
	}
	
	public String menu () {
		return "1 - Habilidad 1 - " + descripcionHabilidad1()+ 
			 "\n2 - Habilidad 2 - " + descripcionHabilidad2()+
		     "\n3 - Habilidad 3 - " + descripcionHabilidad3();
	}

	public int habilidadRandom (int n) {
		if (n==0) return habilidad1();
		else if (n==1) return habilidad2();
		else if (n==2) return habilidad3();
		else return 0;
	}
	
	public void descansar () {
		this.salud = this.getMaxSalud();
		this.estres = this.getMaxEstres();
	}
	
	public boolean checkEstres() {
		if (this.estres <= 0) return false;
		else return true;
	}
	
	public void danar (int dano) {
		if (dano >=0) {
			this.salud -= dano;
			if (this.salud <= 0) vivo = false;
		} else if (dano < 0) {
			this.estres += dano;
			if (this.estres <= 0) vivo = false;
		}
	}
	
	public int habilidad1 () {
		System.out.println("\nHabilidad basica");
		return 1;
	}
	
	public int habilidad2 () {
		System.out.println("\nHabilidad basica");
		return 1;
	}
	
	public int habilidad3 () {
		System.out.println("\nHabilidad basica");
		return 1;
	}
	
	public String descripcionHabilidad1 () {
		return "No tiene efecto";
	}
	
	public String descripcionHabilidad2 () {
		return "No tiene efecto";
	}
	
	public String descripcionHabilidad3 () {
		return "No tiene efecto";
	}
}
