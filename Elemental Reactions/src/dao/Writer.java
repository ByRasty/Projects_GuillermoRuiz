package dao;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	
	private FileWriter fr;
	
	public Writer (String file) {
		try {
			fr = new FileWriter(file);
		} catch (IOException e) {
			System.err.println("Error con el archivo");
		}
	}
	
	public String write (String s) {
		try {
			fr.write(s);
			return "SI FUNCIONA";
		} catch (IOException e) {
			return "NO FUNCIONA";
		}
	}
	
	public void close () {
		try {
			this.fr.close();
		} catch (IOException e) {
			System.err.println("Problema cerrando el reader");
		}
	}

	
}
