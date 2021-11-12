package model;

import java.util.ArrayList;

public class Corredor extends Zombie{

	public Corredor () {
		super("Corredor", 1, 2, 1);
	}
	
	//Es la habilidad especial del zombie caminante, mata todos los corredores aun vivos
	public void habilidadEspecial(ArrayList<Zombie> zombies, int index) {
		zombies.remove(index);
		for(Zombie z : zombies) {
			if(z.getTipo().equals("Corredor")) {
				zombies.remove(z);
			}
		} System.out.println("Se han eliminado todos los corredor vivos que habia gracias a la habilidad especial del zombie corredor que acabas de matar");
	}
	
}
