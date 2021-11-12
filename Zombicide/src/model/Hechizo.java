package model;

import java.util.ArrayList;

public class Hechizo extends Arma {
	
	public Hechizo(String nombre, int danyo, int alcance, int acierto) {
		super(nombre, danyo, alcance, acierto);
	}
	
	//Recorre la array de zombies y si hay dos vivos aun los mata
	@Override
	public void ataqueEspecial(ArrayList<Zombie> zombies) {
		int ok = 0;
		for (int i=0; i<zombies.size(); i++) {
			if(i<2 && zombies.get(i).getTipo().equals("Corredor")) {
				zombies.remove(zombies.get(i));	
				ok++;
			}
		} System.out.println("Has eliminado " + ok + " corredores aleatorios");
	}
	
}
