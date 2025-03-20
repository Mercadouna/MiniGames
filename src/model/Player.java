package model;

public class Player {
	private String nombre;
	private String password;
	private int points;


	public Player() {
		this.nombre="";
		this.password="";
		this.points=0;
	}
	public Player(String nom, String pass, int point) {
		this.nombre=nom;
		this.password=pass;
		this.points=point;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "Player [nombre=" + nombre + ", password=" + password + ", points= " + points + "]";
	}
}
