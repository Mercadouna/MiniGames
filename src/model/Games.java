package model;

public class Games {
	private String nombre;

	public Games() {
		this.nombre="";
	}
	public Games(String nom) {
		this.nombre=nom;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Trophy [nombre=" + nombre + "]";
	}
}
