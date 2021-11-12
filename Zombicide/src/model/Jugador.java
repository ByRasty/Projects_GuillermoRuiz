package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jugador extends Humanoide {
	
	private Arma armaActiva;
	private ArrayList<Arma> armas;
	private boolean rondaPasada;
	
	public Jugador(String nombre) {
		super(nombre, 5);
		this.armaActiva = new Arma();
		this.armas = new ArrayList<>();
	}
	
	public Jugador(String nombre, int salud, String nombreArma, int danyoArma, int alcanceArma, int aciertoArma) {
		super(nombre, salud);
		this.armaActiva = new Hechizo(nombreArma, danyoArma, alcanceArma, aciertoArma);
		this.armas = new ArrayList<>();
		this.armas.add(this.armaActiva);
	}
	
	public Arma getArmaActiva() {
		return this.armaActiva;
	}
	
	public ArrayList<Arma> getArmas() {
		return this.armas;
	}
	
	public void setArmaActiva(Arma arma) {
		this.armaActiva = arma;
	}
	
	//Muestra un menu con las armas disponibles dentro de la arraylist de armas y el usuario puede elegir cual de esas ponerse como arma activa
	public void cambiarArma() {
		Scanner sc = new Scanner(System.in);
		if(this.armas.size() > 1) {
			System.out.println("Que arma quieres poner como tu arma activa?");
			for(int i=0; i<this.armas.size(); i++) {
				System.out.println(i + 1 +  "- " + this.armas.get(i).toString());
			}
			int eleccion = sc.nextInt() - 1;
			this.setArmaActiva(this.armas.get(eleccion));
			System.out.println("Has seleccionado " + this.armas.get(eleccion).getNombre() + " como tu arma activa");
		} else System.err.println("No puedes cambiar tu arma, solo tienes una, que es " + this.armaActiva.getNombre());
		
	}
	
	public String toString() {
		return "JUGADOR: " + super.getNombre() + " S:" + super.getSalud() + " Arma[" + armaActiva.getNombre() + " Dmg:" + armaActiva.getDanyo() + " Dist:" + armaActiva.getAlcance() + " Acc:" + armaActiva.getAcierto() + "]";
	}

}
