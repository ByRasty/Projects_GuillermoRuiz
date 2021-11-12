package manager;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.*;

public class Manager {

	private ArrayList<Jugador> personajesPrevio;
	private ArrayList<Jugador> personajesFinal;
	private ArrayList<Arma> armas;
	private Scanner sc = new Scanner(System.in);
	
	public Manager() {
		this.personajesPrevio = new ArrayList<>();
		this.personajesFinal = new ArrayList<>();
		this.armas = new ArrayList<>();
	}
	
	private void init() {
		initPersonajes();
		initObjetos();
	}
	
	//Hace los news de los personajes personalizados
	private void initPersonajes() {
		this.personajesPrevio.add(new Jugador("James", 7, "Bola de Fuego", 2, 1, 4));
		this.personajesPrevio.add(new Jugador("Marie"));
		this.personajesPrevio.add(new Jugador("Jaci"));
	}
	
	//Haces los news de los objetos personalizados
	private void initObjetos() {
		this.armas.add(new Arco("Arco Largo", 1, 2, 3));
		this.armas.add(new Hacha("Hacha Doble", 2, 1, 3));
		this.armas.add(new Hechizo("Bola de fuego", 1, 3, 4));
		this.armas.add(new Espada("Espada Corta", 1, 1, 4));
	}
	
	//El menu del manager para crear jugadores y meterlos en la partida
	public void menu () {
		init();
		System.out.println("BIENVENIDO A ZOMBICIDE");
		boolean salir = false;
		while(!salir) {
			System.out.println("\n1- Nueva partida"
					+ "\n2- Nuevo personaje"
					+ "\n0- Salir");
			if(sc.hasNextInt()) {
				int seleccion = sc.nextInt();
				switch(seleccion) {
				case 1:
					nuevaPartida();
					break;
				case 2:
					nuevoPersonaje();
					break;
				case 0:
					salir = true;
					salirPrograma();
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
	
	//Cuando se cierra el programa
	private void salirPrograma() {
		System.out.println("Adios");
		sc.close();
	}
	
	
	//Muestra los personajes de la ArrayList que se le pase por parametro, lo uso para encapsular este codigo ya que lo uso varias veces
	private void showPersonajes(ArrayList<Jugador> personajes) {
		System.out.println();
		for(int i=0; i<personajes.size(); i++) {
			System.out.println(i+1 + "- " + personajes.get(i));
		}
	}
	
	//Inicia una nueva partida dependiendo de los jugadores que haya en la array, si solo hay 3 porque no se ha hecho
	//ningun new se inica con los 3 jugadores del initJugadores, si se han creado de mas si le dice con cuales quiere empezar la partida
	private void nuevaPartida() {
		if(this.personajesPrevio.size() == 3) {
			System.out.println("Todos tus personajes han sido seleccionados\n");
			for(int i=0; i<3; i++) this.personajesFinal.add(personajesPrevio.get(i));
			System.out.println("Jugaras con estos personajes: ");
			showPersonajes(personajesFinal);
			Partida p = new Partida(this.personajesFinal, 3);
			
		} else {
			System.out.println("Las partidas son de 3 a 6 jugadores, elige hasta 6 jugadores de la siguiente lista para jugar");
			boolean salir = false;
			while(!salir) {
				if(sc.hasNextInt()) {
					int seleccion = sc.nextInt();
					if(seleccion <= 6 && seleccion >= 3 ) {
						if(seleccion <= personajesPrevio.size()) {
							showPersonajes(personajesPrevio);
							int i=0;
							while(i<seleccion){
								int numJ = sc.nextInt();
								if(numJ <= personajesPrevio.size()) {
									this.personajesFinal.add(personajesPrevio.get(numJ-1));
									i++;
								} else System.out.println("Ese jugador no existe");
							}
							System.out.println("Partida empezada con " + this.personajesFinal.size() + " jugadores");
							Partida p = new Partida(this.personajesFinal, seleccion);
							salir = true;
							
						} else System.err.println("No tienes tantos personajes");
						
					} else System.err.println("Numero no valido, intentalo otra vez");
					
				} else {
					System.err.println("Intentalo de nuevo, no has introducido un parametro valido");
					sc.next();
				}
			}
		}
	}
	
	//Crea los nuevos personajes con nombre personalizado si los jugadores no son 10 ya
	private void nuevoPersonaje() {
		if(this.personajesPrevio.size() < 10) {
			System.out.print("Que nombre le quieres poner al nuevo personaje: ");
			Jugador j = new Jugador(sc.next());
			personajesPrevio.add(j);
			System.out.println("\nPersonaje creado correctamente");
			System.out.println("Nº de personajes: " + this.personajesPrevio.size());
		} else System.err.println("No puedes añadir mas personajes, ya has creado 10");
	}
	
	//Este metodo añade a la arraylist de armas del jugador que se le pase como entrada el arma que le toque aleatoriamente, dependiendo del random
	public static void getArma(Jugador jugador) {
		Random rand = new Random();
		int num = rand.nextInt(100) + 1;
		if(num <= 30) {
			System.out.println("Mala suerte, no te ha tocado nada ");
		} else if(num <= 60) {
			System.out.println("Mala suerte, te ha tocado una daga");
		} else if(num <= 70) {
			System.out.println("Que suerte, te ha tocado un ARCO LARGO");
			jugador.getArmas().add(new Arco("Arco Largo", 1, 2, 3));
		} else if(num <= 80) {
			System.out.println("Que suerte, te ha tocado una ESPADA CORTA");
			jugador.getArmas().add(new Espada("Espada Corta", 1, 1, 4));
		} else if(num <= 90) {
			System.out.println("Que suerte, te ha tocado un HACHA DOBLE");
			jugador.getArmas().add(new Hacha("Hacha Doble", 2, 1, 3));
		} else {
			System.out.println("Que suerte, te ha tocado una BOLA DE FUEGO");
			jugador.getArmas().add(new Hechizo("Bola de fuego", 1, 3, 4));
		}
	}
}




