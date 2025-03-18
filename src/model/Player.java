package model;

public class Player {
	private String nombre;
	private String password;
	private int points;
	private int id;

	public Player() {
		this.nombre="";
		this.password="";
		this.points=0;
		this.id=0;
	}
	public Player(String nom, String pass, int point, int id) {
		this.nombre=nom;
		this.password=pass;
		this.points=point;
		this.id=id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Player [nombre=" + nombre + ", password=" + password +", id= "+ id + ", points= " + points + "]";
	}
}
