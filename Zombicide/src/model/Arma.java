package model;

import java.util.ArrayList;
import java.util.Random;

public class Arma {
	
	private String nombre;
	private int danyo;
	private int alcance;
	private int acierto;
	
	public Arma() {
		this.nombre = "Daga";
		this.danyo = 1;
		this.alcance = 1;
		this.acierto = 4;
	}
	
	public Arma(String nombre, int danyo, int alcance, int acierto) {
		this.nombre = nombre;
		this.danyo = danyo;
		this.alcance = alcance;
		this.acierto = acierto;
	}
	
	public String toString() {
		return "Nombre: " + this.nombre + ", Danyo: " + this.danyo + ", Alcance: " + this.alcance + ", Acierto: " + this.acierto;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getAlcance() {
		return this.alcance;
	}

	public int getDanyo() {
		return this.danyo;
	}
	
	public int getAcierto() {
		return this.acierto;
	}
	
	public void ataqueEspecial(ArrayList<Zombie> zombies) {
		System.out.println("No hay habilidad especial");
	}
	
	//Este metodo hace atacar a un zombie y si lo matas se elimina de la arraylist, pero antes de morir si se tiene suerte se activa su habilidad especial
	public void atacar(ArrayList<Zombie> zombies) {
		Random rand = new Random();
		for(int i=0; i<this.alcance; i++) {
			int z = rand.nextInt(zombies.size());
			Zombie zombie = zombies.get(z);
			if(this.danyo >= zombie.getSalud()) {
				int rand1 = rand.nextInt(6) + 1;
				if(rand1 >= this.acierto){
					System.out.println("Has matado a un " + zombie.getTipo());
					int ratioHabilidad = rand.nextInt(100) + 1;
					//Cambio el tipo del zombie muerto para que no interfiera con la habilidad espeical y despues lo elimino definitivamente
					zombies.get(z).setTipo("MUERTO");
					if(ratioHabilidad >= 95) zombies.get(z).habilidadEspecial(zombies, z);
					zombies.remove(z);
				} else System.out.println("Ha fallado tu ataque con un " + rand1);
			} else System.out.println(zombie.getTipo() + " ha esquivado el ataque");
		}
	}

}
