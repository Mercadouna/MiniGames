package model;

public class Trophy {
	private String name;
	private int price;


	public Trophy() {
		this.name="";
		this.price=0;
	}
	public Trophy(String nombre, int price) {
		this.name=nombre;
		this.price=price;
	}

	public String getNombre() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Trophy [name=" + name + ", price=" + price + "]";
	}
}
