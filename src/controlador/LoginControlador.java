package controlador;



import model.Player;
import model.PlayerDAO;

public class LoginControlador {
	PlayerDAO dao = (PlayerDAO) new model.ImplementationBD();
	public boolean compareplayer(Player player) {
		return dao.compareplayer(player);
	}
	public void addplayer(Player player) {
		dao.addplayer(player);
	}
	public boolean checkPL(Player player) {
		return dao.checkPL(player);
	}
	public int  RandomPoints() {
		return dao.RandomPoints();
	}
	public void modificarpuntos(Player player) {
		dao.modificarpuntos(player);
	}
	public int obtpoints(Player player) {
		return dao.obtpoints(player);
	}
}