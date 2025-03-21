package model;

public interface PlayerDAO {

	public boolean compareplayer(Player player);
	public boolean modificarpuntos(Player player);
	public boolean visualizarhist(Player player);
	public void deleteplayer(Player player);
	boolean eliminarhist(Player player);


}
