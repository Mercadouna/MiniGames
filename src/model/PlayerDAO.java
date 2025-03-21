package model;

public interface PlayerDAO {
	public boolean modificarpuntos(Player player);
	public boolean eliminarhist(Player player);
	public boolean visualizarhist(Player player);
	public void deleteplayer(Player player);
	public boolean compareplayer(Player player);
}
