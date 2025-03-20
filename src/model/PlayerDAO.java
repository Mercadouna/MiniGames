package model;

public interface PlayerDAO {
	public boolean comprobarplayer(Player player);
	public boolean eliminarplayer(Player player);
	public boolean modificarpuntos(Player player);
	public boolean eliminarhist(Player player);
	public boolean visualizarhist(Player player);
}
