package modelo;

import javax.swing.JOptionPane;

import main.Recursos;

public class Habilidad {

	private String nombre;
	private String descripcion;
	
	public Habilidad() {
		this.descripcion = Recursos.getDescripcionHabilidad();
		this.nombre = JOptionPane.showInputDialog(descripcion);
	}
	
	@Override
	public String toString() {
		return this.nombre + " (" + this.descripcion + ")";
	}
	
}
