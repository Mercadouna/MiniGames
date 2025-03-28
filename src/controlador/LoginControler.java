package controlador;



import model.Player;
import model.PlayerDAO;
import view.Login_Window;

public class LoginControler {
	PlayerDAO dao = (PlayerDAO) new model.DBImplementation();
	public void show_window() {
		Login_Window frame = new Login_Window(this);
		frame.setVisible(true);
	}
	public boolean compareplayer(Player player) {
		return dao.compareplayer(player);
	}
	public void addplayer(Player player) {
		dao.addplayer(player);
	}
	public boolean checkPL(Player player) {
		return dao.checkPL(player);
	}
	
	
}