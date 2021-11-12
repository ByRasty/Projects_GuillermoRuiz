package modelo;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import main.Recursos;

public class Arma {
	private String nombre;
	private String tipo;
	private ArrayList<Habilidad> habilidades;

	public Arma() {
		this.habilidades = new ArrayList<Habilidad>();
		this.nombre = Recursos.getNombreArma();
		this.tipo = Recursos.getTipoArma();
	}
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
	
		boolean salir = false;
		while(!salir) {
			System.out.println("\n--Menú Arma--"
					+ "\n1: Mostrar habilidades"
					+ "\n2: Crear nueva habilidad"
					+ "\n0: Salir\n");
			if(sc.hasNextInt()) {
				int opcion = sc.nextInt();
				switch(opcion) {
				case 1: 
					mostrarHabilidades();
					break;
					
				case 2: 
					nuevaHabilidad();
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
	
	public ArrayList<Habilidad> getHabilidades() {
		return this.habilidades;
	}
	
	private void mostrarHabilidades() {
		if(habilidades.size() >= 1) {
			for(int i=0; i<habilidades.size(); i++) {
				System.out.println(habilidades.get(i).toString());
			}	
		} else System.err.println("Aun no hay habilidades a mostrar, crea una primero");
		
	}

	private void nuevaHabilidad() {
		System.out.println("Se ha creado una nueva habilidad");
		Habilidad h = new Habilidad();
		habilidades.add(h);
		
	}
	
	@Override
	public String toString() {
		return "Arma: " + this.nombre + " (" + this.tipo + ")";
	}
}
