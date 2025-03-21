package model;

public class Player {
	private String name;
	private String password;
	private int points;


	public Player() {
		this.name="";
		this.password="";
		this.points=0;
	}
	public Player(String nom, String pass, int point) {
		this.name=nom;
		this.password=pass;
		this.points=point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Player [name=" + name + ", password=" + password + ", points= " + points + "]";
	}
}
