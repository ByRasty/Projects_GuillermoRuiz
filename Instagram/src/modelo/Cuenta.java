package modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Cuenta {

	private String nombreDeCuenta;
	private ArrayList<Publicacion> publicaciones;
	private Scanner sc = new Scanner(System.in);
	
	public Cuenta(String nombreDeCuenta) {
		this.nombreDeCuenta = nombreDeCuenta;
		this.publicaciones = new ArrayList<Publicacion>();
		menu();
	}
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		while(!salir) {
			System.out.println("Cuenta " + this.nombreDeCuenta
					+ "\n1- Nueva publicacion"
					+ "\n2- Acceder a la publicacion"
					+ "\n3- Mostrar estadisticas"
					+ "\n0- Volver al menu principal");
			int seleccion = sc.nextInt();
			switch(seleccion) {
			case 1:
				nuevaPublicacion();
				break;
			case 2:
				accederPublicacion();
				break;
			case 3: 
				mostrarEstadisticas();
				break;
			case 0:
				salir = true;
				break;
			default:
					System.out.println("No has escogido una opcion valida");
					break;
			}
		}
		
	}
	
	private void nuevaPublicacion() {
		int index;
		if(this.publicaciones.size() == 0) {
			index = 1;
		} else {
			index = this.publicaciones.indexOf(this.publicaciones.size());
		}
		
		System.out.println("Indica el titulo de la publicacion");
		String titulo = sc.next();
		System.out.println("Se ha creado una nueva publicacion");
		this.publicaciones.add(new Publicacion(index, titulo));
	}
	
	private void accederPublicacion() {
		if(this.publicaciones.size() >= 1) {
			System.out.println("Indica a que publicacion quieres acceder");
			for(int i=0; i < this.publicaciones.size(); i++) {
				System.out.print(i+1 + ", ");
			} System.out.println();
			int indicePublicacion = sc.nextInt() - 1;
			publicaciones.get(indicePublicacion).menu();
		} else System.out.println("No hay ninguna publicacion, crea una!");
	}

	private void mostrarEstadisticas() {
		toString();
	}
	
	public String getNombreDeCuenta() {
		return this.nombreDeCuenta;
	}
	
	public ArrayList<Publicacion> getPublicaciones(){
		return this.publicaciones;
	}

	@Override
	public String toString() {
		return "Nombre de la cuenta: " + this.nombreDeCuenta + ", Publicaciones: " + this.publicaciones.size();
	}
	
	
	
}
