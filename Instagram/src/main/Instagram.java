package main;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Cuenta;
import modelo.Publicacion;

public class Instagram {
	
	private static ArrayList<Cuenta> cuentas;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		cuentas = new ArrayList<Cuenta>();
		System.out.println("Bienvenido a Instagram");
		menu();
		acabarPartida();
	}
	
	private static void menu() {
		boolean salir = false;
		while(!salir) {
			System.out.println("\nINSTAGRAM"
					+ "\n1- Nueva Cuenta"
					+ "\n2- Acceder Cuenta"
					+ "\n3- Mostrar estadisticas"
					+ "\n4- Mostrar estadisticas completas"
					+ "\n0- Salir");
			int seleccion = sc.nextInt();
			switch(seleccion) {
			case 1:
				nuevaCuenta();
				break;
			case 2:
				accederCuenta();
				break;
			case 3: 
				mostrarEstadisticas();
				break;
			case 4: 
				mostrarEstadisticasCompletas();
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
	
	private static void nuevaCuenta() {
		System.out.println("Indica el nombre de usuario de la nueva cuenta");
		String usuario = sc.next();
		System.out.println("Se ha creado una nueva cuenta");
		cuentas.add(new Cuenta(usuario));
	}
	
	private static void accederCuenta() {
		if(cuentas.size() >= 1) {
			System.out.println("Indica a que cuenta quieres acceder");
			for(int i=0; i < cuentas.size(); i++) {
				System.out.print(i+1 + "- " + cuentas.get(i).getNombreDeCuenta());
			} System.out.println();
			int indiceCuenta = sc.nextInt() - 1;
			cuentas.get(indiceCuenta).menu();
		} else System.out.println("No hay ninguna cuenta, crea una!");
	}

	private static void mostrarEstadisticas() {
		System.out.println("Cuentas: " + cuentas.size());
	}
	
	private static void mostrarEstadisticasCompletas() {
		if(cuentas.size() >= 1) {
			for(int i=0; i<cuentas.size(); i++) {
				System.out.println(cuentas.get(i).toString());
				for(int j=0; j<cuentas.get(i).getPublicaciones().size(); j++) {
					System.out.println("\t" + cuentas.get(i).getPublicaciones().get(j).toString());
					for(int k=0; k<cuentas.get(i).getPublicaciones().get(j).getComentarios().size(); k++) {
						System.out.println("\t\t" + cuentas.get(i).getPublicaciones().get(j).getComentarios().get(k).toString());
					}
				}
			}
		} else System.out.println("No hay ningun tipo de informacion a mostar, primero crea las cuentas, las publicaciones y los comentarios");
		
	}
	
	public static void acabarPartida() {
		System.out.println("Gracias por usar Instagram, hasta la proxima...");
		sc.close();
	}
	
}
