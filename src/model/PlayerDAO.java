package model;

public interface PlayerDAO {

	public boolean compareplayer(Player player);
	public void deleteplayer(Player player);
	public void modificarpuntos(Player player);
	public boolean eliminarhist(Player player);
	public void visualizarhist(Player player);
	public void addplayer(Player player);
	public boolean checkPL(Player player);
	public int RandomPoints(Player player);

}
