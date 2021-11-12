package modelo;
import java.util.ArrayList;
import java.util.Random;

public class Ubicacion {

	private ArrayList <Objeto> objetos = new ArrayList<Objeto>();
	private int peligrosidad;
	
	public Ubicacion() {
		
	}
	
	public int getPeligrosidad() {
		return peligrosidad;
	}
	
	public int determinarPeligrosidad(int nivel) {
		Random rand = new Random();
		this.peligrosidad = rand.nextInt(nivel) + 1;
		return this.peligrosidad;
	}
	
	public ArrayList<Objeto> objetosDisponibles(int nivel) {
		Random rand = new Random();
		int numeroDeObjetos = (rand.nextInt(10) + 1) - nivel;
		
		for (int i=0; i<numeroDeObjetos; i++) {
			int a = rand.nextInt(99) + 1;
			if(a >= 1 && a <= 9) {
				objetos.add(new Objeto("Armas", 1));
			} else if(a >= 10 && a < 40) {
				objetos.add(new Objeto("Comida", 1));
			} else if(a >= 40 && a < 55) {
				objetos.add(new Objeto("Medicamentos", 1));
			} else if(a >= 55 && a <= 100) {
				objetos.add(new Objeto("Componentes", 1));
			}
		}
		
		return objetos;
	}
	
	public ArrayList<Objeto> getObjetos() { 
		return objetos;
	}

	
	
}
