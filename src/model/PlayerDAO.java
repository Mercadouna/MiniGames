package model;

public interface PlayerDAO {

	public boolean compareplayer(Player player);
	public boolean deleteplayer(Player player);
	public boolean eliminarhist(Player player);
	public boolean visualizarhist(Player player);
	public void addplayer(Player player);
	public boolean checkPL(Player player);
	public int RandomPoints();
	public int obtpoints(Player player);
	public void modifypoints(Player player);
}
