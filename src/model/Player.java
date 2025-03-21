package model;

public class Player {
	private String Name;
	private String password;
	private int points;


	public Player() {
		this.Name="";
		this.password="";
		this.points=0;
	}
	public Player(String nom, String pass, int point) {
		this.Name=nom;
		this.password=pass;
		this.points=point;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
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
		return "Player [Name=" + Name + ", password=" + password + ", points= " + points + "]";
	}
}
