package model;

import java.util.ArrayList;

public class Hacha extends Arma {

	public Hacha(String nombre, int danyo, int alcance, int acierto) {
		super(nombre, danyo, alcance, acierto);
	}
	
	//Recorre la array de zombies en busca de un zombie gordo y si lo hay lo mata
	@Override
	public void ataqueEspecial(ArrayList<Zombie> zombies) {
		for (Zombie z : zombies) {
			if(z.getTipo().equals("Gordo")) {
				zombies.remove(z);
				System.out.println("Has matado a un gordo gracias a la habildad especial");
				break;
			}
		}
	}
	
}
