package manager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.*;

public class Manager {
	
	private Scanner sc = new Scanner (System.in);
	private Random rand = new Random();
	private ArrayList<model.Character> enemigos;
	private ArrayList<model.Character> characters;
	private model.Character[] personajes;
	
	public Manager () {
		this.enemigos = new ArrayList<model.Character>();
		this.characters = new ArrayList<model.Character>();
		this.personajes = new model.Character[4];
	}

	public void init () {
		this.personajes[0] = new Abominacion();
		this.personajes[1] = new Cazarecompensas();
		this.personajes[2] = new Asaltatumbas();
		this.personajes[3] = new Bandolero();
		menuInicial();
		showMenu();
	}
	
	private void menuInicial() {
		System.out.println("\t¡¡¡BIENVENIDO A DARKEST DUNGEON!!!\t\n");
		System.out.println("Escoge 4 personajes\n");
		for (int i=0; i<=3; i++) {
			boolean salir = false;
			showPersonajes(i+1);
			while (!salir) {
				if(sc.hasNextInt()) {
					int eleccion = sc.nextInt() - 1;
					if (eleccion <= 3 && eleccion >= 0) {
						this.characters.add(this.personajes[eleccion]);
						salir = true;
					} else System.err.println("Ese numero no es valido, intenta otro!!!");
				} else {
					 System.err.println("Intentalo de nuevo, no has introducido un parametro valido!!!");
					 sc.next();
				}
			}
			
		}
	}
	
	private void showMenu () {
		System.out.println("\n--> INICIANDO PARTIDA <--\n\nPersonajes elegidos: ");
		showCharacters(false);
		generarEnemigos();
		boolean salir = false, acabarPartida = false;
		while (!salir && !acabarPartida) {
			System.out.println("\nElige una opcion del menu:\n"
					+ "1 - Explorar mazmorra\n"
					+ "2 - Descansar\n"
					+ "3 - Reordenar equipo\n"
					+ "0 - Finalizar partida");
			System.out.print("Opción: ");
			if(sc.hasNextInt()) {
				switch(sc.nextInt()) {
				case 1: 
					if(newDungeon()) acabarPartida = true;
					break;
				case 2: 
					descansar();
					break;
				case 3: 
					reordenar();
					break;
				case 0:
					finalizarPartida();
					salir = true;
					break;
				default:
					System.err.println("Ese numero no corresponde a ninguna opción!!");
					break;
				}
				
			} else {
				 System.err.println("Intentalo de nuevo, no has introducido un parametro valido");
				 sc.next();
			}
			
		}
	}
	
	private int aleatorio () {
		Random rand = new Random();
		return rand.nextInt(4);
	}
	
	private void showCharacters (boolean conNumeros) {
		if(conNumeros) for (int i=0; i<this.characters.size(); i++) System.out.println((i+1) + " - " + this.characters.get(i));
		else for (int i=0; i<this.characters.size(); i++) System.out.println(this.characters.get(i));
	}
	
	private void showEnemigos(int maxRange, int minRange) {
		if(this.enemigos.size() >= maxRange) {
			for (int i=minRange;i<=maxRange; i++) System.out.println((i) + " - " + this.enemigos.get(i));	
			System.out.print("Opción: ");
		} else System.err.println("No hay enemigos a rango!"); 
	}
	
	private void showPersonajes (int num) {
		for (int i=0; i<this.personajes.length; i++) {
			System.out.println(i+1 + " - " + this.personajes[i]);
		} System.out.print("\nPersonaje " + num + ": ");
	}
	
	private void generarEnemigos () {
		int rand = aleatorio()+1;
		for (int i=0; i<rand; i++) this.enemigos.add(personajes[aleatorio()].copy());
		System.out.println("\nSe han generado " + rand + " enemigo/s: ");
		for (model.Character enemy : this.enemigos) System.out.println(enemy);
	}
	
	private boolean newDungeon () {
		int personajesMuertos = 0;
		System.out.println("\nTurno de los personajes: ");
		for (model.Character personaje : this.characters) {
			boolean salir = false;
			if (personaje.estaVivo() && !this.enemigos.isEmpty()) {
				int dano = 0;
				System.out.println("Turno de: " + personaje);
				System.out.print("\nEscoge una habilidad:\n" + personaje.menu() + "\nOpción: ");
				
				while(!salir) {
					if(sc.hasNextInt()) {
						switch(sc.nextInt()) {
						case 1: 
							dano = personaje.habilidad1();
							salir = true;
							break;
						case 2:
							dano = personaje.habilidad2();
							salir = true;
							break;
						case 3: 
							dano = personaje.habilidad3();
							salir = true;
							break;
						default:
							System.err.println("Ese numero no corresponde a ninguna habilidad!!");
							break;
						}	
					} else {
						System.err.println("Eso no es un número, escoge otro");
						sc.next();
					}
				} salir = false;
				System.out.println(personaje.getTipo() + " hará " + dano + " de daño\n\nEstos son los enemigos a rango. Escoge uno!");
				showEnemigos(personaje.getMaxRange(), personaje.getMinRange());
				
				while (!salir) {
					if (sc.hasNextInt()) {
						int eleccion = sc.nextInt();
						if (eleccion <= personaje.getMaxRange() && eleccion >= personaje.getMinRange()) {
							this.enemigos.get(eleccion).danar(dano);
							System.out.println("\nEl enemigo " + this.enemigos.get(eleccion).getTipo() + " ha sido atacado.");
							
							if (this.enemigos.get(eleccion).getSalud() <= 0) {
								System.out.println(this.enemigos.get(eleccion).getTipo() + " ha muerto" + "\n");
								this.characters.remove(this.enemigos.get(eleccion));
							}
							else System.out.println("Su salud restante es " + this.enemigos.get(eleccion).getSalud() + "\n");
							
							if (this.enemigos.get(eleccion).getSalud() <= 0) this.enemigos.remove(eleccion);
							salir = true;
						} else {
							System.err.println("Ese numero no corresponde a ningun enemigo!!");
						}
					} else {
						System.err.println("Eso no es un número, escoge otro");
						sc.next();
					} 
				}
			} 
		}
		
		System.out.println("Turno de los enemigos: ");
		for (model.Character enemigo : this.enemigos) {
			if (enemigo.estaVivo()) {
				int dano = enemigo.habilidadRandom(rand.nextInt(3) + 1);
				System.out.println("El enemigo " + enemigo.getTipo() + " hará " + dano + " de daño a un personaje aleatorio\n ");
				model.Character danado = this.characters.get(rand.nextInt(this.characters.size()));
				danado.danar(dano);
				System.out.println("El personaje " + danado.getTipo() + " ha sido atacado.");
				if (danado.getSalud() <= 0) {
					System.out.println(danado.getTipo() + " ha muerto" + "\n");
					this.characters.remove(danado);
					personajesMuertos++;
				}
				else System.out.println("Su salud restante es " + danado.getSalud() + "\n");

			}
		}
		if (this.enemigos.isEmpty()) {
			System.out.println("Todos los enemigos han muerto!\n¡Felicidades, has ganado!");
			return true;
		} else if (this.characters.isEmpty()) {
			System.out.println("Todos los personajes han muerto, has perdido la partida!");
			return true;
		}
		
		if (!(personajesMuertos <= 0)) {
			System.out.println("Como ha muerto algun personaje, elige de que tipo quieres el siguiente personaje!");
			for (int i=1; i<=personajesMuertos; i++) {
				showPersonajes(i);
				boolean salir = false;
				while (!salir) {
					if (sc.hasNextInt()) {
						int eleccion = sc.nextInt();
						if(eleccion <= 4 && eleccion>= 1 ) {
							this.characters.add(personajes[eleccion].copy());
							salir = true;
						} else {
							System.err.println("Ese numero no corresponde a ningun personaje!");
							sc.next();
						}
					} else {
						System.err.println("Eso no es numero!");
						sc.next();
					}
				}
			}
		}
		
		System.out.println("¡SIGUIENTE RONDA!\n");
		return false;
	}
	
	private void descansar () {
		for (model.Character personaje : this.characters) personaje.descansar();
		System.out.println("\nLos valores de salud y estres de los personajes se han restablecido al valor maximo");
	}
	
	private void reordenar() {
		System.out.println();
		showCharacters(true);
		System.out.print("\n¿Que personaje quieres mover? ");
		int characterIndex = sc.nextInt();
		model.Character personajeElegido = characters.get(characterIndex);
		System.out.print("¿A que posicion? ");
		int posicion = sc.nextInt();
		characters.remove(characterIndex);
		characters.add(posicion, personajeElegido);
		System.out.println("\nPersonajes reordandos correctamente!");
		showCharacters(true);
	}
	
	private void finalizarPartida() {
		sc.close();
		System.out.println("\n¡¡¡GRACIAS POR JUGAR!!!");
	}
}
