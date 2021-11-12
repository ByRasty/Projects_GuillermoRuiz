package main;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Casa;
import modelo.Objeto;
import modelo.Personaje;
import modelo.Ubicacion;

public class ThisWarOrMine {

	public static Casa casa;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		casa = new Casa();
		generateCharacters();
	}
	
	private static void generateCharacters() {
		ArrayList <Personaje> personajesJugables = new ArrayList<Personaje>();
		
		personajesJugables.add(new Personaje("Arica", "SIGILO"));
		personajesJugables.add(new Personaje("Bruno", "COCINERO"));
		personajesJugables.add(new Personaje("Katia", "ELOCUENCIA"));
		personajesJugables.add(new Personaje("Pavel", "RAPIDEZ"));
		
		getCharacters(personajesJugables);
	}
	
	private static void getCharacters(ArrayList<Personaje> personajesJugables) {
		ArrayList <Personaje> personajes = new ArrayList<Personaje>();
		
		System.out.println("Bienvenido a This War Of Mine");
		System.out.println("\nElige con que 3 personajes quieres jugar:");
		
		showPersonajes(personajesJugables, true);
		
		int a = 0;
		while (a < 3) {
			int j = sc.nextInt();
			if(j >= 1 && j <= personajesJugables.size()) {
				
				personajes.add(personajesJugables.get(j-1));
				personajesJugables.remove(j-1);
				System.out.println("Has escogido a "+personajes.get(a).getNombre()+" como el personaje numero " + (a+1));
				showPersonajes(personajesJugables, true);
				a++;
			}
			else System.out.println("No has escogido un numero valido");
		}
		
		nuevaPartida(personajes);

	}
	
	private static void showPersonajes(ArrayList<Personaje> personajes, boolean conHabilidad) {
		
		if(conHabilidad) {
			for (int i=0; i<personajes.size(); i++) {
				System.out.println(i+1 + ". " +personajes.get(i).getNombre() + " | " + personajes.get(i).getHabilidad());
			}
		} else {
			for (int i=0; i<personajes.size(); i++) {
				System.out.println(i+1 + ". " +personajes.get(i).getNombre());
			}
		}
		
	}

	private static void nuevaPartida(ArrayList<Personaje> personajes) {
		System.out.println("\nHA EMPEZADO UNA NUEVA PARTIDA!!\n");	
		int nivel = 1;
		
		while (checkSalud(personajes)) { //Turno
			System.out.println("Ha empezado un nuevo dia\n");
			casa.mostrarObjetosCasa(); 
			Ubicacion ubi = generateUbicacion(nivel);
			escogerRoles(personajes, nivel, ubi);
			nivel++;	
		}
		acabarPartida();
	}
	
	private static boolean checkSalud(ArrayList<Personaje> personajes) {
		for (int i=0; i<personajes.size(); i++) {
			if(personajes.get(i).getSalud() <= 0) {
				return false;
			}
		}
		return true;
	}
	
	private static Ubicacion generateUbicacion(int nivel) {
		Ubicacion ubi = new Ubicacion();
		ubi.determinarPeligrosidad(nivel);
		ubi.objetosDisponibles(nivel);
		return ubi;
	}
	
	private static void escogerRoles(ArrayList<Personaje> personajes, int nivel, Ubicacion ubi) {
		ArrayList<Personaje> roles = new ArrayList<Personaje>();
		ArrayList<Personaje> personajesEscogibles = new ArrayList<Personaje>();
		
		for(int i=0; i<3; i++) personajesEscogibles.add(personajes.get(i));
		
		System.out.print("Selecciona los roles de los personajes para hoy escribiendo 1, 2 o 3\n\n");
		showPersonajes(personajes, false);
		System.out.print("1) Personaje que se quedara vigilando: ");
		escogerRoles2 (personajes, personajesEscogibles, roles, "vigilar");
		System.out.print("2) Personaje que se quedara durmiendo: ");
		escogerRoles2 (personajes, personajesEscogibles, roles, "dormir");
		System.out.print("3) Personaje que se ira a explorar: ");
		escogerRoles2 (personajes, personajesEscogibles, roles, "explorar");
		System.out.println();
		
		roles.get(0).vigilar(nivel);
		roles.get(1).dormir(casa);
		roles.get(2).explorar(ubi);
		
		finDia(personajes);
	}
	
	private static void escogerRoles2 (ArrayList<Personaje> personajes, ArrayList<Personaje> personajesEscogibles, ArrayList<Personaje> roles, String rol) {
		boolean salir = false;
		while (!salir) {
			int p = sc.nextInt() - 1;
			if(p >= 0 && p < 3) {
				String nombre = personajes.get(p).getNombre();
				if(existeJugador(nombre, personajesEscogibles)) {
					roles.add(personajes.get(p));
					System.out.println("Se ha asigando a " + personajes.get(p).getNombre() + " para " + rol);
					salir = true;
				} else System.out.println("Ese personaje ya tiene asignado un rol");
			} else System.out.println("El numero indicado no corresponde a ningun personaje");
		}
	}
	
	private static boolean existeJugador (String nombre, ArrayList<Personaje> p) {
		for(int i=0; i<p.size(); i++) {
			if(p.get(i).getNombre().equals(nombre)) {
				p.remove(i);
				return true;
			}
		}
		return false;
	}
	
	private static void acabarPartida() {
		System.out.println("HA MUERTO UN PERSONAJE, LA PARTIDA SE HA ACABADO!!!!!!");
		sc.close();
	}
	
	private static void finDia(ArrayList<Personaje> personajes) {
		for(int i=0; i<3; i++) {
			for(int j=0;j<personajes.get(i).getObjetos().size(); j++) {
				casa.add(personajes.get(i).getObjetos().get(j));
				personajes.get(i).getObjetos().remove(j);
			}
			personajes.get(i).aumentarHambre(1);
		}
		System.out.println("Ha llegado el final del dia\n");
		casa.mostrarObjetosCasa();
		System.out.println("Ha continuacion, contesta con Si o No\n");
		
		int count = 0, count2 = 0;
		boolean salir1 = false, salir2 = false;
		 do { //Gastar 1 de comida a cambio de disminuir el hambre de un jugador
			System.out.println("Gastar 1 de comida a cambio de disminuir el hambre de un jugador (Si/No)");
			String input = sc.next();
			if(input.equalsIgnoreCase("Si")) {
				for(int i=0; i<casa.getObjetos().size(); i++) {
					if(casa.getObjetos().get(i).getTipo().equalsIgnoreCase("Comida") && !salir2) {
						count++;
						if(count == 1) {
							getHambreMasGrande(personajes).restarHambre(); //Saber que personaje tiene mas hambre para racionar la comida eficientemente
							casa.getObjetos().remove(i);
							System.out.println("Has gastado uno de comida y " + getHambreMasGrande(personajes).getNombre() + " ha restado 1 de hambre");
							salir2 = true;
						} 
					}
					
				}
				if(count < 1) System.out.println("No tienes comida suficiente");
				
				salir1 = true;
			}else if (input.equalsIgnoreCase("No")) salir1 = true;
			 else System.out.println("No has escogido una opcion valida");
		} while(!salir1);
		salir1 = false; salir2 = false;
		
		do { //Gastar 5 componentes a cambio de generar 2 de comida
			count = 0; count2 = 0;
			System.out.println("Gastar 5 componentes a cambio de generar 2 de comida (Si/No)");
			String input = sc.next();
			if(input.equalsIgnoreCase("Si")) { 
				for(int i=0; i<casa.getObjetos().size(); i++) {
					if(casa.getObjetos().get(i).getTipo().equalsIgnoreCase("Componentes") && !salir2) {
						count++;
						if(count >= 5) {
							for(int j=0; j<casa.getObjetos().size(); j++) {
								if(casa.getObjetos().get(j).getTipo().equalsIgnoreCase("Componentes") && count2<5 && !salir2) {
									casa.getObjetos().remove(j);
									count2++;
									if(count2 == 5) {
										casa.generarObjetos("Comida", 1);
										casa.generarObjetos("Comida", 1);
										System.out.println("Has gastado 5 componentes y has generado 2 de comida");
										salir2 = true;
									}
								}
							}

						}
					}
						
				}
				if(count<5) System.out.println("No tienes suficientes componentes");
				salir1 = true;
			}else if (input.equalsIgnoreCase("No")) salir1 = true;
			 else System.out.println("No has escogido una opcion valida");
		} while(!salir1);
		salir1 = false; salir2 = false;
		
		do { //Gastar 5 componentes a cambio de generar 1 de medicamentos
			count = 0; count2 = 0;
			System.out.println("Gastar 5 componentes a cambio de generar 1 de medicamentos (Si/No)");
			String input = sc.next();
			if(input.equalsIgnoreCase("Si")) { 
				for(int i=0; i<casa.getObjetos().size(); i++) {
					if(casa.getObjetos().get(i).getTipo().equalsIgnoreCase("Componentes") && !salir2) {
						count++;
						if(count == 5) {
							for(int j=0; j<casa.getObjetos().size(); j++) {
								if(casa.getObjetos().get(j).getTipo().equalsIgnoreCase("Componentes") && count2<5 && !salir2) {
									casa.getObjetos().remove(i);
									count2++;
									if(count2 == 5) {
										casa.getObjetos().add(new Objeto("Medicamentos", 1));
										System.out.println("Has gastado 5 componentes y has generado 1 de medicamentos");
										salir2 = true;
									}
								}
							}
							
						} 
					}		
				}
				if(count< 5) System.out.println("No tienes suficientes componentes");
				salir1 = true;
			}else if (input.equalsIgnoreCase("No")) salir1 = true;
			 else System.out.println("No has escogido una opcion valida");
		} while(!salir1);
		salir1 = false; salir2 = false;
		
		do { //Gastar 10 componentes para crear una cama
			count = 0; count2 = 0;
			System.out.println("Gastar 10 componentes para crear una cama (Si/No)");
			String input = sc.next();
			if(input.equalsIgnoreCase("Si")) { 
				if(!casa.getCama()) {
					for(int i=0; i<casa.getObjetos().size(); i++) {
						if(casa.getObjetos().get(i).getTipo().equalsIgnoreCase("Componentes") && !salir2) {
							count++;
							if(count == 5) {
								for(int j=0; j<casa.getObjetos().size(); j++) {
									if(casa.getObjetos().get(i).getTipo().equalsIgnoreCase("Componentes") && count2<10 && !salir2) {
										casa.getObjetos().remove(i);
									}
									if(count == 10) { //Se han borrado 10 componentes
										casa.generarCama();
										System.out.println("Has gastado 10 componenetes y has creado una cama para que tus personajes duerman");
										salir2 = true;
									}
								}
							}						
						}
					}
					if(count < 5) System.out.println("No tienes componentes suficientes");
					salir1 = true;
				} else System.out.println("Ya hay una cama creada");
				
			}else if (input.equalsIgnoreCase("No")) salir1 = true;
			 else System.out.println("No has escogido una opcion valida");
		} while(!salir1);
		salir1 = false; salir2 = false;
		
		do { //Gastar 10 componentes para crear una cama
			count = 0; count2 = 0;
			System.out.println("Gastar 1 de medicamentos y subir la salud de uno de los jugadores (Si/No)");
			String input = sc.next();
			if(input.equalsIgnoreCase("Si")) { 
				for(int i=0; i<casa.getObjetos().size(); i++) {
					if(casa.getObjetos().get(i).getTipo().equalsIgnoreCase("Medicamentos") && !salir1) {
						count++;
						if(count >= 1) {
							getSaludMasPequeña(personajes).recuperarSalud(1);
							casa.getObjetos().remove(i);
							System.out.println("Has gastado 1 de medicamentos y has subido la salud de " + getSaludMasPequeña(personajes).getNombre());
							salir1 = true;
						} 
					}
				}
				if(count < 1) System.out.println("No tienes medicamentos suficientes");
				salir1 = true;				
			}else if (input.equalsIgnoreCase("No")) salir1 = true;
			 else System.out.println("No has escogido una opcion valida");
		} while(!salir1);
		
		System.out.println();
	}
	
	private static Personaje getHambreMasGrande (ArrayList<Personaje> personajes) { //Funcion para saber que personajes tiene mas hambre
		if(personajes.get(0).getHambre() > personajes.get(1).getHambre() && personajes.get(0).getHambre() > personajes.get(2).getHambre()) return personajes.get(0);
		else if(personajes.get(1).getHambre() > personajes.get(0).getHambre() && personajes.get(1).getHambre() > personajes.get(2).getHambre()) return personajes.get(1);
		else if(personajes.get(2).getHambre() > personajes.get(1).getHambre() && personajes.get(2).getHambre() > personajes.get(0).getHambre()) return personajes.get(1);
		else return personajes.get(0); //Si todos tienen la misma hambre devuelvo el primer personaje
	}
	
	private static Personaje getSaludMasPequeña (ArrayList<Personaje> personajes) { //Funcion para saber que personajes tiene mas hambre
		if(personajes.get(0).getSalud() < personajes.get(1).getSalud() && personajes.get(0).getSalud() < personajes.get(2).getSalud()) return personajes.get(0);
		else if(personajes.get(1).getSalud() < personajes.get(0).getSalud() && personajes.get(1).getSalud() < personajes.get(2).getSalud()) return personajes.get(1);
		else if(personajes.get(2).getSalud() < personajes.get(1).getSalud() && personajes.get(2).getSalud() < personajes.get(0).getSalud()) return personajes.get(1);
		else return personajes.get(0); //Si todos tienen la misma hambre devuelvo el primer personaje
	}
}
