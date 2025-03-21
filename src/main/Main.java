package main;

import controlador.LoginControlador;
import view.Login_Window;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginControlador controlador= new LoginControlador();
		Login_Window frame = new Login_Window(controlador);
		frame.setVisible(true);
	}

}
