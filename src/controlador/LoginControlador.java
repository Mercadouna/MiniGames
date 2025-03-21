package controlador;



import model.Player;
import model.PlayerDAO;
import view.Login_Window;

public class LoginControlador {
	PlayerDAO dao = (PlayerDAO) new model.DBImplementation();
	public boolean compareplayer(Player player) {
		return dao.compareplayer(player);
	}
	public void visualizeWindow() {
		Login_Window frame = new Login_Window(this);
		frame.setVisible(true);
	}
}