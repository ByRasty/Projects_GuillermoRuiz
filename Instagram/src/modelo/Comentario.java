package modelo;

public class Comentario {

	private String comentario;
	private String nombreDeUsuario;
	
	public Comentario(String comentario, String Usuario){
		this.comentario = comentario;
		this.nombreDeUsuario = Usuario;
	}

	@Override
	public String toString() {
		return "Comentario: " + this.comentario + " es del usuario: " + this.nombreDeUsuario;
	}
	
	
	
}
