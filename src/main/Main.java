package main;

import controlador.LoginControlador;
import view.Login_Window;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginControlador controler= new LoginControlador();
		Login_Window frame = new Login_Window(controler);
		frame.setVisible(true);
	}

}
