package model;

public class Games {
	private String name;

	public Games() {
		this.name="";
	}
	public Games(String nam) {
		this.name=nam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Trophy [nombre=" + name + "]";
	}
}
