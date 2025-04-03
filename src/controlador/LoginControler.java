package controlador;

import model.DBImplementation;
import model.InsufficientPointsException;
import model.Player;
import model.Plays;

import java.sql.SQLException;
import java.util.ArrayList; 
public class LoginControler {
    DBImplementation dao = new DBImplementation();

    public void show_window() {
        view.Login_Window frame = new view.Login_Window(this);
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

    public void deletePlayer(Player player) {
        dao.deleteplayer(player);
    }

    public ArrayList<String> getBoughtTrophies(Player player) { 
        return dao.getBoughtTrophies(Integer.parseInt(dao.ReturnID(player)));
    }
    public boolean buyTrophy(Player player, String trophyName, int trophyPrice) throws InsufficientPointsException {
        try {
            return dao.buyTrophy(player, trophyName, trophyPrice);
        } catch (SQLException e) {
            System.err.println("Error de base de datos al intentar comprar trofeo '" + trophyName + "': " + e.getMessage());
            
            return false; 
        }
    }
    public ArrayList<Plays> getPlays(Player player){	
		return dao.getPlays(player);
	}
}
