package model;

import java.util.ArrayList;

public class Caminante extends Zombie {

	public Caminante () {
		super("Caminante", 1, 1, 1);
	}
	
	//Es la habilidad especial del zombie caminante, mata todos los caminantes que haya aun vivos
	public void habilidadEspecial(ArrayList<Zombie> zombies, int index) {
		zombies.remove(index);
		int contador = 0;
		for(Zombie z : zombies) {
			if(z.getTipo().equals("Caminante")) {
				zombies.add(new Caminante());
				contador++;
			}
		} System.out.println("Se han invocado " + contador + " caminantes mas debido a la habilidad especial del zombie caminante que acabas de matar");
	}
	
}
