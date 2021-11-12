package modelo;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import main.Recursos;

public class Personaje {

	private ArrayList<Arma> armas;
	private String nombre;
	private String clase;
	private int nivel;
	private int poder;
	
	public Personaje() {
		this.armas = new ArrayList<Arma>();
		this.nombre = Recursos.getNombre();
		this.clase = Recursos.getClase();
		this.nivel = 1;
		this.poder = 1;
	}
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
		
		
		boolean salir = false;
		while(!salir) {
			System.out.println("\n--Menú Personaje--"
					+ "\n1: Mostrar armas"
					+ "\n2: Crear nueva arma"
					+ "\n3: Seleccionar arma"
					+ "\n0: Salir\n");
			if(sc.hasNextInt()) {
				int opcion = sc.nextInt();
				switch(opcion) {
				case 1: 
					mostrarArmas();
					break;
					
				case 2: 
					nuevaArma();
					break;
					
				case 3: 
					seleccionarArma();
					break;
					
				case 0: 
					salir=true;
					break;
					
				default:
					System.err.println("No has escogido una opcion valida");
					break;
				}
			} else {
				System.err.println("Intentalo de nuevo, no has introducido un parametro valido");
				sc.next();
			}
		}
			
		
	}
	
	public ArrayList<Arma> getArmas() {
		return this.armas;
	}

	private void nuevaArma() {
		System.out.println("Se ha creado un nuevo arma");
		Arma h = new Arma();
		armas.add(h);
		h.menu();
	}

	private void mostrarArmas() {
		if(this.armas.size() >= 1) {
			for(int i=0; i<this.armas.size(); i++) {
				System.out.println(this.armas.get(i).toString());
			}	
		} else System.err.println("Aun no hay armas a mostrar, crea una primero");
		
	}
	
	private void seleccionarArma() {
		if(this.armas.size() >= 1) {
			Object[] arrayAux = new Object[armas.size()+1];
			for(int i=0; i<this.armas.size(); i++) {
				arrayAux[i] = this.armas.get(i);
			}
			arrayAux[this.armas.size()] = "Cancelar";
			boolean salir = false;
			while(!salir) {
				int arma = JOptionPane.showOptionDialog(null, "Seleccionar Armas", "Armas", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, arrayAux, null);
				if(arma == -1) {
					System.err.println("No cierres la ventana, selecciona una opcion");
				} else if(arma == arrayAux.length-1){
					System.out.println("Has cancelado la eleccion de armas");
					salir = true;
				} else {
					System.out.println("Accediendo al menu del arma que acabas de seleccionar");
					salir = true;
				}
			}
			
		} else System.err.println("Primero crea un personaje!");
			
	}

	@Override
	public String toString() {
		return this.nombre + " (" + clase + ")";
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public int getNivel() {
		return this.nivel;
	}	   	
	
	public int getPoder() {
		return this.poder;
	}
	
}
