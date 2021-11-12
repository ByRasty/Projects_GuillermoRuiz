package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Partida {
	private ArrayList<Personaje> personajes;
	private String nombre;
	private long milis;
	
	public Partida() {
		this.personajes = new ArrayList<Personaje>();
		Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        this.nombre = formatoFecha.format(fecha);
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);
		
		long tiempoInicial = System.currentTimeMillis();
		
		boolean salir = false;
		while(!salir) {
			System.out.println("\n--Menú Partida--"
					+ "\n1: Mostrar personajes"
					+ "\n2: Crear nuevo personaje"
					+ "\n3: Seleccionar personaje"
					+ "\n4: Iniciar Batalla"
					+ "\n0: Salir\n");
			if(sc.hasNextInt()) {
				int opcion = sc.nextInt();
				switch(opcion) {
				case 1: 
					mostrarPersonajes();
					break;
					
				case 2: 
					nuevoPersonaje();
					break;
					
				case 3: 
					seleccionarPersonaje();
					break;
					
				case 4:
					iniciarBatalla();
					break;
					
				case 0: 
					setTiempo(finPartida(tiempoInicial));
					System.out.println("Tiempo Jugado: " + milis + " segundos");
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
	
	private void iniciarBatalla() {
		Random rand = new Random();
		if(personajes.size() >= 1) {
			for(int i=0; i<personajes.size();i++) {
                int random = rand.nextInt(personajes.get(i).getPoder()+1);
                if(random<=personajes.get(i).getNivel()) {
                    Victoria(i,personajes.get(i).getNivel(),personajes.get(i).getPoder());
                    System.out.println(personajes.get(i).toString() + " ha aumentado su nivel en 1 y su poder en 2!!!!\n");
                    System.out.println("Nivel actual de " + personajes.get(i).toString() + ": " + personajes.get(i).getNivel());
                    System.out.println("Poder actual de " + personajes.get(i).toString() + ": " + personajes.get(i).getPoder() + "\n");
                }
            }
		} else System.err.println("No hay personajes suficientes para iniciar una batalla");
	}
	
	private void Victoria(int i, int nivel, int poder) {
        nivel+=1;
        poder+=2;
        personajes.get(i).setNivel(nivel);
        personajes.get(i).setPoder(poder);
    }
	
	public ArrayList<Personaje> getPersonajes() {
		return this.personajes;
	}
	
	private void mostrarPersonajes() {
		if(this.personajes.size() >= 1) {
			for(int i=0; i<this.personajes.size(); i++) {
				System.out.println(this.personajes.get(i).toString());
			}	
		} else System.err.println("Aun no hay personajes a mostrar, crea uno primero");
	}

	private void seleccionarPersonaje() {
		if(this.personajes.size() >= 1) {
			Object[] arrayAux = new Object[personajes.size()+1];
			for(int i=0; i<this.personajes.size(); i++) {
				arrayAux[i] = this.personajes.get(i);
			}
			arrayAux[this.personajes.size()] = "Cancelar";
			boolean salir = false;
			while(!salir) {
				int arma = JOptionPane.showOptionDialog(null, "Seleccionar Personajes", "Personajes", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, arrayAux, null);
				if(arma == -1) {
					System.err.println("No cierres la ventana, selecciona una opcion");
				} else if(arma == arrayAux.length-1){
					System.out.println("Has cancelado la eleccion de personajes");
					salir = true;
				} else {
					System.out.println("Accediendo al menu del personaje que acabas de seleccionar");
					salir = true;
				}
			}
			
		} else System.err.println("Primero crea un personaje!");
			
	}

	private void nuevoPersonaje() {
		System.out.println("Se ha creado un nuevo personaje");
		Personaje p = new Personaje();
		personajes.add(p);
		p.menu();
	}
	
	private void setTiempo(long tiempo) {
		this.milis = tiempo;
	}
	
	private long finPartida(long tiempoInicial) {
		long tiempoTotal = (System.currentTimeMillis() - tiempoInicial) / 1000;
		return tiempoTotal;
	}

	@Override
	public String toString() {
		return nombre + " de " + milis + " segundos";
	}
	
}
