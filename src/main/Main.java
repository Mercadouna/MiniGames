package main;

import controlador.LoginControler;
import view.Login_Window;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginControler controler= new LoginControler();
		Login_Window frame = new Login_Window(controler);
		frame.setVisible(true);
	}

}