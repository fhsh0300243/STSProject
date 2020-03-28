package tw.STSProject.service;

import java.util.List;

import tw.STSProject.bean.FavoriteStock;

public interface IFavoriteStockService {
	public boolean insertFavoriteStock(int userID, String stockCode);
	public int deleteFavoriteStock(int userID, String stockCode);
	public List<FavoriteStock> findAllUserFavoriteStock(int userID);
	public FavoriteStock findUserFavoriteStock(int userID, String stockCode);
}
