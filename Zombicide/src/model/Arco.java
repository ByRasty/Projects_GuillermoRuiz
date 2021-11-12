package model;

import java.util.ArrayList;

public class Arco extends Arma {

	public Arco(String nombre, int danyo, int alcance, int acierto) {
		super(nombre, danyo, alcance, acierto);
	}
	
	//Mara a un corredor si hay alguno vivo aun
	
	@Override
	public void ataqueEspecial(ArrayList<Zombie> zombies) {
		for (Zombie z : zombies) {
			if(z.getTipo().equals("Corredor")) {
				zombies.remove(z);
				System.out.println("Has matado a un corredor gracias a la habildad especial");
				break;
			}
		}
	}
	
}
