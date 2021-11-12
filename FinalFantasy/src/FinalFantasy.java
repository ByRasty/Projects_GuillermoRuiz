import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

import main.Recursos;
import modelo.Arma;
import modelo.Partida;
import modelo.Personaje;

public class FinalFantasy {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Partida> partidas;
	
	public static void main(String[] args) {
		partidas = new ArrayList<Partida>();
		menu();
	}
	
	private static void menu() {
		long start = System.currentTimeMillis();
		System.out.println("BIENVENIDO A FINAL FANTASY");
		
		boolean salir=false;
		while (!salir) {
			System.out.println("\n--Menú Final Fantasy--"
					+ "\n1: Mostrar partidas guardadas"
					+ "\n2: Mostrar info partidas"
					+ "\n0: Salir del programa\n");
			if(sc.hasNextInt()) {
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					mostrarPartidasGuardadas();
					break;
				case 2:
					mostrarInfo();
					break;
				case 0:
					salir = true;
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
		finPrograma(start);
	}
	
	private static void mostrarInfo() {
		for(int i=0; i<partidas.size(); i++) {
			System.out.println("\nPartida: " + partidas.get(i));
			for(int j=0; j<partidas.get(i).getPersonajes().size(); j++) {
				System.out.println("\t" + partidas.get(i).getPersonajes().get(j));
				for(int k=0; k<partidas.get(i).getPersonajes().get(j).getArmas().size(); k++) {
					System.out.println("\t\t" + partidas.get(i).getPersonajes().get(j).getArmas().get(k));
					for(int p=0; p<partidas.get(i).getPersonajes().get(j).getArmas().get(k).getHabilidades().size(); p++) {
						System.out.println("\t\t\t" + partidas.get(i).getPersonajes().get(j).getArmas().get(k).getHabilidades().get(p));
					}
				}
			}
		}
			
	}
	
	private static void mostrarPartidasGuardadas() {
		if(partidas.size() >= 1) {
			int partida = JOptionPane.showOptionDialog(null, "Selecciona Partida", "PARTIDAS", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, partidas.toArray(), null);
			if(partida == -1) {
				System.out.println("Se ha creado una nueva partida");
				Partida p1 = new Partida();
				partidas.add(p1);
				p1.menu();
			} else {
				System.out.println("Accediendo al menu de la partida que acabas de seleccionar");
				partidas.get(partida).menu();
			}
		} else {
			System.out.println("Como no habia ninguna partida ya creada, se ha creado una nueva");
			Partida p = new Partida();
			partidas.add(p);
			p.menu();
			
		}
			
	}
	
	private static void finPrograma(long start) {
		System.out.println("Cerrando programa...");
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		long tiempoReal = timeElapsed / 1000;
		System.out.println("Han pasado " + tiempoReal + " segundos desde que empezaste");
		sc.close();
	}
}
