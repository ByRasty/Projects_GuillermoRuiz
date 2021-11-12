package model;

import java.util.ArrayList;

public class Gordo extends Zombie{

	public Gordo () {
		super("Gordo", 2, 1, 1);
	}
	
	//Es la habilidad especial del zombie caminante, mata un zombie gordo si algun hay alguno vivo
	public void habilidadEspecial(ArrayList<Zombie> zombies, int index) {
		zombies.remove(index);
		for(Zombie z : zombies) {
			if(z.getTipo().equals("Gordo")) {
				zombies.remove(z);
				break;
			}
		} System.out.println("Se ha eliminado otro Gordo gracias a la habilidad especial del zombie gordo que acabas de matar");
	}
}
