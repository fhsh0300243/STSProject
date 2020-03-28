package tw.STSProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tw.STSProject.bean.FavoriteStock;

public class FavoriteStockDAO implements IFavoriteStockDAO{
	
	private Session session;
	
	public FavoriteStockDAO(Session session) {
		this.session=session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public boolean insertFavoriteStock(int userID, String stockCode) {	
		Query<FavoriteStock> query = session.createQuery("from FavoriteStock where UserID = :userID and StockCode = :stockCode", FavoriteStock.class);
		query.setParameter("userID", userID);
		query.setParameter("stockCode", stockCode);
		List<FavoriteStock> list = query.list();
		if(list.size()<=0) {
			FavoriteStock fsTemp = new FavoriteStock();
			fsTemp.setUserID(userID);
			fsTemp.setStockCode(stockCode);
			session.save(fsTemp);
			return true;
		}
		return false;
	}
	
	public int deleteFavoriteStock(int userID, String stockCode) {
		Query<?> query = session.createQuery("delete from FavoriteStock where UserID = :userID and StockCode = :stockCode");
		query.setParameter("userID", userID);
		query.setParameter("stockCode", stockCode);
		int numData=query.executeUpdate();
		return numData;
	}
	
	public List<FavoriteStock> findAllUserFavoriteStock(int userID) {
		Query<FavoriteStock> query = session.createQuery("from FavoriteStock where UserID = :userID", FavoriteStock.class);
		query.setParameter("userID", userID);
		List<FavoriteStock> list = query.list();
		return list;
	}
	
	public FavoriteStock findUserFavoriteStock(int userID, String stockCode) {
		Query<FavoriteStock> query = session.createQuery("from FavoriteStock where UserID = :userID and StockCode = :stockCode", FavoriteStock.class);
		query.setParameter("userID", userID);
		query.setParameter("stockCode", stockCode);
		FavoriteStock result =(FavoriteStock) query;
		return result;
	}
}
