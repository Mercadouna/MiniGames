
package controler;



import java.sql.SQLException;
import java.util.ArrayList;

import model.InsufficientPointsException;
import model.Player;
import model.PlayerDAO;
import model.Plays;
import view.Login_Window;

public class LoginControler {
	private PlayerDAO dao = (PlayerDAO) new model.DBImplementation();
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
    public ArrayList<String> getBoughtTrophies(Player player) { 
        return dao.getBoughtTrophies(Integer.parseInt(dao.ReturnID(player)));
    }
	   public boolean buyTrophy(Player player, String trophyName, int trophyPrice) throws InsufficientPointsException {
	        try {
	            return dao.buyTrophy(player, trophyName, trophyPrice);
	        } catch (SQLException e) {
	            System.err.println("Error on the database when buying trophy '" + trophyName + "': " + e.getMessage());
	            
	            return false; 
	        }
	    }
	   public void addPoints(Player player, int pointsToAdd) {
		    try {
		        dao.addPoints(player, pointsToAdd);
		    } catch (SQLException e) {
		        System.err.println("Error from the DB when adding points: " + e.getMessage());
		        // Manejar el error como consideres apropiado
		    }
		}
	   public void savePlayResult(Player player, String gameName, int score) {
	        // Validación básica de parámetros (opcional pero recomendable)
	        if (player == null || gameName == null || gameName.trim().isEmpty()) {
	            System.err.println("Error in savePlayResult: Invalidentry data (Username or Game Blank/null).");
	            return; // No intentar guardar si falta información esencial
	        }
	        if (dao == null) {
	             System.err.println("Critical error in savePlayResult: The DAO object is not initialized.");
	             return; // No se puede continuar sin DAO
	        }

	        try {
	            // Llama al método correspondiente en la instancia del DAO (dao)
	            // Este método dao.recordPlay es el que debe estar corregido (manejo de ID y conexión)
	            System.out.println("Controller: Calling dao.recordPlay for Player ID (obtained in DAO): "
	                               + player.getName() + ", Game: " + gameName + ", Score: " + score); // Mensaje Debug
	            dao.recordPlay(player, gameName, score);

	        } catch (SQLException e) {

	            System.err.println("Controller error when trying to save game result for "
	                               + player.getName() + ". SQLException: " + e.getMessage());
	

	        } catch (Exception ex) {
	            // Capturar cualquier otra excepción inesperada para que no detenga la aplicación
	             System.err.println("Unexpected error in savePlayResult for " + player.getName() + ": " + ex.getMessage());
	             ex.printStackTrace(); // Imprimir traza completa para errores inesperados
	        }
	    }
}