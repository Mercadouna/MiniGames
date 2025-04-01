package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlayerDAO {

	public boolean compareplayer(Player player);
	public void deleteplayer(Player player);
	public boolean eliminarhist(Player player);
	public boolean visualizarhist(Player player);
	public void addplayer(Player player);
	public boolean checkPL(Player player);
	public int RandomPoints();
	public int obtpoints(Player player);
	public void modifypoints(Player player);
	public ArrayList<String> getBoughtTrophies(int userId);
	public boolean buyTrophy(Player player, String trophyName, int trophyPrice)
			throws InsufficientPointsException, SQLException;
	}
 
