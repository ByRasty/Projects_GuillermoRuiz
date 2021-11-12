package modelo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Personaje {

	private String nombre;
	private int salud;
	private int hambre;
	private int suenyo;
	private String habilidad;
	private ArrayList <Objeto> objetos;
	
	public Personaje(String nombre, String habilidad) {
		this.nombre = nombre;
		this.salud = 10;
		this.hambre = 1;
		this.suenyo = 1;
		this.habilidad = habilidad;
		this.objetos = new ArrayList<Objeto>();
	}
	
	public void vigilar(int nivel) {
		this.suenyo += 1;
		
		Random rand = new Random();
		int num1 = rand.nextInt(51) - nivel;
		
		if(num1 >= 0 && num1 <= 5) {
			this.salud -= 2;
			System.out.println("Mientras " + this.nombre +" vigilaba, ha sido asaltado y ha perdido 2 de salud\n");
		}
		else if(num1 >= 40 && num1 <= 50) {
			if(this.habilidad.equalsIgnoreCase("ELOCUENCIA")) {
				this.objetos.add(new Objeto("Comida", 1));
				this.objetos.add(new Objeto("Comida", 1));
				this.objetos.add(new Objeto("Comida", 1));
				this.objetos.add(new Objeto("Componentes", 1));
				this.objetos.add(new Objeto("Componentes", 1));
				this.objetos.add(new Objeto("Componentes", 1));
				System.out.println("Mientras " + this.nombre +" vigilaba, ha aparecido un comerciante\nque le ha regalado 3 de comida y 3 de componentes por tener la habilidad ELOCUENCIA\n");
			} else {
				this.objetos.add(new Objeto("Comida", 1));
				this.objetos.add(new Objeto("Comida", 1));
				this.objetos.add(new Objeto("Componentes", 1));
				this.objetos.add(new Objeto("Componentes", 1));
				System.out.println("Mientras " + this.nombre +" vigilaba, ha aparecido un comerciante\nque le ha regalado 2 de comida y 2 de componentes\n");
			}
			
		}
	}
	
	public void dormir(Casa casa) {
		if(casa.getCama()) {
			this.suenyo -= 2;
			System.out.println(this.nombre + " ha dormido y ha perdido 2 de suenyo\n");
		}
		else System.out.println("No hay cama contruida para dormir, tendras que comprarla al final de dia\n");
	}
	
	public void explorar(Ubicacion ubi) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		this.suenyo += 2;
		this.hambre += 1;
		
		if(ubi.getObjetos().size() > 0) {
			System.out.println("De los siguientes objetos, elige hasta cinco para guardartelos en tu mochila");
			showObjetos(ubi);
			int a = 0, max = 5; boolean salir = false;
			
			if(this.habilidad.equalsIgnoreCase("RAPIDEZ")) max = 7;
			
			do{
				int b = sc.nextInt();
				if(b <= ubi.getObjetos().size() && b > 0) {
					this.objetos.add(ubi.getObjetos().get(b-1));
					System.out.println(this.nombre + " se ha guardado en la mochila un objeto de " + ubi.getObjetos().get(b-1).toString());
					ubi.getObjetos().remove(b-1);
					showObjetos(ubi);
					a++;
					if(ubi.getObjetos().size() <= 0) salir = true;
				} else System.out.println("El objeto deseado no existe");
			} while(a < max && !salir);
			System.out.println();
			
			int danyos = rand.nextInt(ubi.getPeligrosidad() + 1);
		
			if(this.habilidad.equalsIgnoreCase("SIGILO")) {
				danyos -= 2;
				System.out.println("Gracias a la habilidad SIGILO, " + this.nombre + " ha mitigado 2 de daño de los que iba a sufrir por la peligrosidad de la ubicacion\n");
			}
			
			if(danyos > 0) {
				this.salud -= danyos;
				System.out.println("Debido a la peligrosidad de la ubicacion,\n" + this.getNombre() + " a perdido " + danyos + " de salud, actualmente tiene " + this.getSalud() + " de salud\n");
			}
		}
	}
	
	public void showObjetos(Ubicacion ubi) {
		for(int i=0;i<ubi.getObjetos().size(); i++) {
			System.out.println((i+1) + "- " + ubi.getObjetos().get(i).getTipo());
		}
	}

	public int getSuenyo() {
		return suenyo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getSalud() {
		return salud;
	}

	public int getHambre() {
		return hambre;
	}

	public String getHabilidad() {
		return habilidad;
	}
	
	public ArrayList<Objeto> getObjetos(){
		return this.objetos;
	}
	
	public void aumentarHambre(int valor) {
		this.hambre += valor;
	}
	
	public void restarHambre() {
		if(this.habilidad.equalsIgnoreCase("COCINERO"))  this.hambre -= 2;
		else this.hambre -= 1;
	}
	
	public void recuperarSalud (int valor) {
		this.salud += valor;
	}
}

