package manager;

import java.util.ArrayList;
import dao.*;
import modelo.*;

public class Manager {
	private ArrayList<Personaje> personajes;
	private ArrayList<String> elementosUnicos;
	private ArrayList<Integer> nElementos;
	private ArrayList<String> elementosTotales;
	private Reader r1, r2;
	private Writer w1;
	
	public Manager () { /*Aqui inicializo las variables  */
		this.personajes = new ArrayList<Personaje>();
		this.elementosUnicos = new ArrayList<String>();
		this.nElementos = new ArrayList<Integer>();
		this.elementosTotales = new ArrayList<String>();
		this.r1 = new Reader("files/personajes.txt");
		this.r2 = new Reader("files/elementos.txt");
		this.w1 = new Writer("files/resultadoElemental.txt");
	}
	
	/*Desde aqui voy llamando a los diferentes metodos del manager*/
	public void init () { 
		volcado();
		totalPersonajes();
		totalElementos();
		totalPersonajeXElemento();
		reacciones();
		close();
	}
	
	/*Aqui hago un volcado del contenido de personajes.txt en una ArrayList de tipo Personaje. 
	  El resultado del .split lo separo y lo almaceno en "nombre" y "elemento" dentro de personaje.java*/
	private void volcado () { 
		String linea = "";
		while ((linea = this.r1.readLine()) != null) 
			this.personajes.add(new Personaje(linea.split(" ")));
	}
	
	/*Aqui hago la primera escritura del fichero. Pongo el total 
	  de personajes cogiendo el size de la ArrayList personajes*/
	private void totalPersonajes () { 
		this.w1.write("Total de personajes: " + this.personajes.size() + "\n");
	}
	
	/*Aqui hago la segunda escritura. Almaceno en una ArrayList los elementos uno por uno y si ya esta en la array no lo vuelvo a almacenar
	  para no repetir y tener en la array solo los elementos una vez y asi poder decir cuantos elementos diferentes se usan*/
	private void totalElementos() {
		for (int i=0; i<this.personajes.size(); i++) 
			if (!this.elementosUnicos.contains(this.personajes.get(i).getElemento())) 
				this.elementosUnicos.add(this.personajes.get(i).getElemento());
		this.w1.write("Total de elementos utilizados: " + this.elementosUnicos.size() + "\n");
	}
	
	/*Aqui escribo en fichero el numero de personajes que usan cada elemento. Lo hago almcenando todos los elementos uno por uno en una ArrayList
	  y en otra ArrayList añadiendo cuantas veces se ha usado ese elemento */
	private void totalPersonajeXElemento () {
		this.w1.write("Total de personajes por elemento:\n");
		
		for (int i=0; i<this.personajes.size(); i++) 
			this.elementosTotales.add(this.personajes.get(i).getElemento());
		
		for (int i=0; i<this.elementosUnicos.size(); i++) {
			int count = 0;
			for (int j=0; j<this.elementosTotales.size(); j++) 
				if(this.elementosTotales.get(j).equals(this.elementosUnicos.get(i))) count++;   
			this.nElementos.add(count);
		}
		
		for (int i=0; i<6; i++) 
			this.w1.write("\t- " + this.elementosUnicos.get(i) + " : " + this.nElementos.get(i) + "\n");
	}
	
	/*Este apartado es para escribir en fichero las reacciones elementales. Primero leo dos caracteres y los junto en una String. 
	  Despues hago un switch con esa String y en los case pongo todas las posibilidades de que haya reaccion. Si hay reaccion vuelvo a leer 
	  dos caracteres nuevos, pero si no hay reaccion el segundo caracter pasa a ser el primero y solo hago una nueva lectura en el segundo caracter*/
	private void reacciones () {
		int e1=0, e2=0;
		String reaccion = "";
		boolean entrar = true;
		do {
			if (entrar) e1 = this.r2.read(); 
			e2 = this.r2.read();
			switch(Character.toString((char) e1) + Character.toString((char) e2)) {
			case "FA": case "AF":	
				reaccion = "Evaporacion";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "FH": case "HF":
				reaccion = "Derretido";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "FR": case "RF":
				reaccion = "Sobrecarga";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "RA": case "AR":
				reaccion = "Electro-carga";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "RH": case "HR":
				reaccion = "Superconductor";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "AH": case "HA":
				reaccion = "Congelar";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "VF": case "FV": case "VR": case "RV": case "VA": case "AV": case "VH": case "HV":
				reaccion = "Torbellino";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "TF": case "FT": case "TR": case "RT": case "TA": case "AT": case "TH": case "HT":
				reaccion = "Cristalizar";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			case "FN": case "NF":
				reaccion = "Quemadura";
				this.w1.write(reaccion + "\n");
				entrar = true;
				break;
			default:
				e1 = e2;
				entrar = false;
			}
		} while (e1 != -1 && e2 != -1);
	}
	
	/*Aqui simplemente cierro los readers y writer*/
	private void close () {
		this.r1.close();
		this.r2.close();
		this.w1.close();
	}

}
