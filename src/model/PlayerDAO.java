package model;

public interface PlayerDAO {

	public boolean compareplayer(Player player);
	public void deleteplayer(Player player);
	public boolean modificarpuntos(Player player);
	public boolean eliminarhist(Player player);
	public boolean visualizarhist(Player player);


}
