package modelo;

import java.util.ArrayList;
import java.util.Scanner;

public class Publicacion {
	
	private int nombre;
	private String titulo;
	private ArrayList<Comentario> comentarios;
	private Scanner sc = new Scanner(System.in);
	
	public Publicacion(int index, String titulo) {
		this.nombre = index;
		this.titulo = titulo;
		this.comentarios = new ArrayList<Comentario>();
		menu();
	}
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		while(!salir) {
			System.out.println("Publicacion " + this.nombre
				+ "\n1- Nuevo comentario"
				+ "\n2- Acceder al comentario"
				+ "\n3- Mostrar estadisticas"
				+ "\n0- Volver al menu principal");
			int seleccion = sc.nextInt();
			switch(seleccion) {
			case 1:
				nuevoComentario();
				break;
			case 2:
				accederComentario();
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
	
	private void nuevoComentario() {
		System.out.println("Indica el comentario: ");
		String comentario = sc.next();
		System.out.println("Indica el Usuario: ");
		String usuario = sc.next();
		System.out.println("Se ha creado un nuevo comentario");
		this.comentarios.add(new Comentario(comentario, usuario));
	}
	
	private void accederComentario() {
		if(this.comentarios.size() >= 1) {
			System.out.println("Indica a que comentario quieres acceder");
			for(int i=0; i < this.comentarios.size(); i++) {
				System.out.print(i+1 + ", ");
			} System.out.println();
			int indiceComentario = sc.nextInt() - 1;
			System.out.println(comentarios.get(indiceComentario).toString());
		} else System.out.println("No hay ningun comentario, crea uno!");
	}

	private void mostrarEstadisticas() {
		toString();
	}
	
	public ArrayList<Comentario> getComentarios(){
		return this.comentarios;
	}

	@Override
	public String toString() {
		return "Titulo: " + this.titulo + "Comentarios: " + this.comentarios.size();
	}
	
	

	
	
	
	
}
