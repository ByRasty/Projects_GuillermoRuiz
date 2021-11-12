package model;

import java.util.ArrayList;

public class Espada extends Arma {

	public Espada(String nombre, int danyo, int alcance, int acierto) {
		super(nombre, danyo, alcance, acierto);
	}
	
	//Elimina dos zombies aleatorios random
	@Override
	public void ataqueEspecial(ArrayList<Zombie> zombies) {
		int ok = 0;
		for (int i=0; i<zombies.size(); i++) {
			if(i<2) {
				zombies.remove(zombies.get(i));	
				ok++;
			}
		} System.out.println("Has eliminado " + ok + " zombies aleatorios");
	}
	
	
	
}
