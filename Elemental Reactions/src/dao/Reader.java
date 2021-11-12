package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	private FileReader fr; 
	private BufferedReader br;
	
	public Reader(String file) {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			System.err.println("Error con el archivo");
		}
		
	}
	
	public String readLine() {
		try {
			return this.br.readLine();
		} catch (IOException e){
			return null;
		}
	}
	
	public int read() {
		try {
			return this.fr.read();
		} catch (IOException e) {
			return -1;
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
