package modelo;
import java.util.ArrayList;

public class Casa {

	private boolean cama;
	private ArrayList <Objeto> objetos;
	
	public Casa() {
		this.cama = false;
		this.objetos = new ArrayList<Objeto>();
	}
	
	public boolean getCama() {
		return this.cama;
	}
	
	public void generarCama() {
		this.cama = true;
	}
	
	public void mostrarObjetosCasa() {
		
		/*int arma=0, comida=0, medicamento=0, componente=0; 
		
		for(int j=0; j<this.objetos.size(); j++) {
			if(this.objetos.get(j).getTipo().equalsIgnoreCase("ARMA")) arma++;
			else if(this.objetos.get(j).getTipo().equalsIgnoreCase("COMIDA")) comida++;
			else if(this.objetos.get(j).getTipo().equalsIgnoreCase("MEDICAMENTO")) medicamento++;
			else if(this.objetos.get(j).getTipo().equalsIgnoreCase("COMPONENTE")) componente++;
			
		}
		
		if(arma>0 && objetos.size()>0) {
			System.out.println(objetos.get(0).toString("Armas", arma));
		}
		
		if(comida>0 && objetos.size()>0) {
			System.out.println(objetos.get(0).toString("Comida", comida));
		}
		
		if(medicamento>0 && objetos.size()>0) {
			System.out.println(objetos.get(0).toString("Medicamentos", medicamento));
		}
		
		if(componente>0 && objetos.size()>0) {
			System.out.println(objetos.get(0).toString("Componentes", componente));;
		}*/
		
		if(objetos.size() >= 1) {
			System.out.println("Estos son los objetos almacenados en la casa\n");
			for(int i=0; i<objetos.size();i++) {
				System.out.println(objetos.get(i).toString());
			}
			System.out.println();
		} else System.out.println("La casa no tiene objetos de momento\n");
	}
	
	public ArrayList <Objeto> getObjetos(){
		return this.objetos;
	}
	
	public void generarObjetos(String tipo, int cantidad){
		this.objetos.add(new Objeto (tipo, cantidad));
	}
	
	public void add (Objeto objeto) {
		this.objetos.add(objeto);
	}
	
	
}
