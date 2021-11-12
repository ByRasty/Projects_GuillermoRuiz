package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import manager.Manager;

public class Partida {

	private int ronda;
	private int nivel;
	private ArrayList<Zombie> zombies;
	private ArrayList<Jugador> personajes;
	
	public Partida(ArrayList<Jugador> jugadores, int nivel) {
		this.zombies = new ArrayList<Zombie>();
		this.personajes = new ArrayList<Jugador>();
		this.personajes = jugadores;
		this.nivel = nivel;
		System.out.println("\n!!!Bienvenido a una nueva partida!!!");
		manager();
	}
	
	//Controla la partida y solo sale cuando un jugador muere y se acaba la partida
	private void manager() {
		boolean salir = false;
		while(!salir) {
			initZombies();
			menu();
			ataqueZombie(this.personajes);
			if(personajes.size() == 0) salir = true;
			ronda++;
		} acabarPartida();
		//TODO acabarPartida();
	}
	
	//Genera los 3 zombies de la ronda aleatoriamente
	public void initZombies() {
		this.zombies.clear();
		Random rand = new Random();
		for(int i=0; i<nivel; i++) {
			int z = rand.nextInt(3) + 1;
			if(z==1) this.zombies.add(new Caminante());
			else if(z==2) this.zombies.add(new Gordo());
			else if(z==3) this.zombies.add(new Corredor());
		}
	}
	
	//Imprime por pantalla los zombies que hay vivos
	private void showZombies(ArrayList<Zombie> zombies) {
		System.out.print("==| ");
		for(Zombie z : zombies) {
			System.out.print(z + " ");
		} System.out.print("|==\n");
	}
	
	//Menu de la partida en bucle para cada jugador, tambien muestra la informacion de la ronda y la del jugador que esta jugando
	public void menu () {
		Scanner sc = new Scanner(System.in);
		for(Jugador j : personajes) {
			System.out.println("\n|----- Nivel: " + this.nivel + " - " + this.ronda + " -----|");
			showZombies(zombies);
			System.out.println(j.toString());
			System.out.println("1- Atacar"
					+ "\n2- Habilidad Especial"
					+ "\n3- Buscar"
					+ "\n4- Cambiar arma"
					+ "\n0- Pasar");
				
			if(sc.hasNextInt()) {
				int seleccion = sc.nextInt();
				switch(seleccion) {
				case 1:
					j.getArmaActiva().atacar(zombies);
					break;
				case 2:
					j.getArmaActiva().ataqueEspecial(zombies);
					break;
				case 3:
					Manager.getArma(j);
					break;
				case 4:
					j.cambiarArma();
					break;
				case 0:
					System.out.println("\n" + j + " ha pasado esta ronda\n");
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
	
	//Este metodo hace que cada uno de los zombies vivos ataquen aleatoriamente a un jugador y le quita los puntos de salud equivalentes
	// a los puntos de movimiento del zombie que ataca, si ese jugador muere, se muestra un mensaje y se le elimina de la arraylist de jugadores
	public void ataqueZombie(ArrayList<Jugador> jugadores) {
		Random rand = new Random();
		System.out.println("Es el turno de los zombies para atacar, ten cuidado");
		for(Zombie z : this.zombies) { 
				Jugador j = jugadores.get(rand.nextInt(jugadores.size()));
				System.out.println(j.getNombre() + " ha perdido " + j.restarSalud(z.getMovimiento()) + " puntos de salud");
				if(j.getSalud() <= 0) {
					String nombreMuerto = j.getNombre();
					jugadores.remove(j);
					System.out.println("\nUn zombie ha matado a " + nombreMuerto + " , quedan " + jugadores.size() + " jugadores restantes");
					
				}
		}
		
	}
	
	//Este metodo es llamado cuando no queda ningun jugador vivo
	public void acabarPartida() {
		System.out.println("\nHAS PERDIDO, gracias por jugar a Zombicide");
	}
}
