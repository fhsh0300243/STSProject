package tw.STSProject.service;

import java.util.List;

import org.hibernate.Session;

import tw.STSProject.bean.FavoriteStock;
import tw.STSProject.dao.FavoriteStockDAO;

public class FavoriteStockService implements IFavoriteStockService {
	private FavoriteStockDAO fsDAO;
	
	public FavoriteStockService(Session session) {
		fsDAO=new FavoriteStockDAO(session);
	}
	
	public boolean insertFavoriteStock(int userID, String stockCode) {
		return fsDAO.insertFavoriteStock(userID, stockCode);
	}
	
	public int deleteFavoriteStock(int userID, String stockCode) {
		return fsDAO.deleteFavoriteStock(userID, stockCode);
	}
	
	public List<FavoriteStock> findAllUserFavoriteStock(int userID){
		return fsDAO.findAllUserFavoriteStock(userID);
	}
	
	public FavoriteStock findUserFavoriteStock(int userID, String stockCode) {
		return fsDAO.findUserFavoriteStock(userID, stockCode);
	}
}
