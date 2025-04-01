
package controlador;



import java.util.ArrayList;

import model.Player;
import model.PlayerDAO;
import model.Plays;
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
	public int obtpoints(Player player) {
		return dao.obtpoints(player);
	}
	public void modifypoints(Player player, String gname) {
		dao.modifypoints(player, gname);
	}
	public void deletePlayer(Player player){	
		 dao.deleteplayer(player);
	}
	public ArrayList<Plays> getPlays(Player player){
		return dao.getPlays(player);
	}

}