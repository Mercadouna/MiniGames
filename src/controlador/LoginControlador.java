package controlador;



import model.Player;
import model.PlayerDAO;

public class LoginControlador {
	PlayerDAO dao = (PlayerDAO) new model.ImplementationBD();
	public boolean comprobarPlayer(Player player) {
		return dao.compareplayer(player);
	}
}