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
	public void modifypoints(Player player, String gname);
	public String ReturnID(Player player);
	public ArrayList<Plays> getPlays(Player player);
	public ArrayList<String> getBoughtTrophies(int userId);
	public boolean buyTrophy(Player player, String trophyName, int trophyPrice)
			throws InsufficientPointsException, SQLException;
	public void addPoints(Player player, int pointsToAdd) throws SQLException;
	void recordPlay(Player player, String gname, int score) throws SQLException;
	
} 