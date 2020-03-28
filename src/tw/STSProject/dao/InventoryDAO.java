package tw.STSProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tw.STSProject.bean.Inventory;

public class InventoryDAO implements IInventoryDAO {
	private Session session;
	
	public InventoryDAO(Session session) {
		this.session=session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public boolean insertOrUpadteInventory(int userID, String stockCode, int quantity) {	
		Query<Inventory> query = session.createQuery("from Inventory where UserID = :userID and StockCode = :stockCode", Inventory.class);
		query.setParameter("userID", userID);
		query.setParameter("stockCode", stockCode);
		List<Inventory> list = query.list();
		if(list.size()<=0) {
			Inventory iTemp = new Inventory();
			iTemp.setUserID(userID);;
			iTemp.setStockCode(stockCode);;
			iTemp.setQuantity(quantity);;
			session.save(iTemp);
			return true;
		}
		Query<?> query1 = session.createQuery("update Inventory set Quantity=:quantity where UserID = :userID and  StockCode = :stockCode");
		query1.setParameter("quantity", quantity);
		query1.setParameter("userID", userID);
		query1.setParameter("stockCode", stockCode);
		query1.executeUpdate();
		return false;
	}
	
	public int deleteInventory(int userID, String stockCode) {
		Query<?> query = session.createQuery("delete from Inventory where UserID = :userID and StockCode = :stockCode");
		query.setParameter("userID", userID);
		query.setParameter("stockCode", stockCode);
		int numData=query.executeUpdate();
		return numData;
	}
	
	public List<Inventory> findAllUserInventory(int userID) {
		Query<Inventory> query = session.createQuery("from Inventory where UserID = :userID", Inventory.class);
		query.setParameter("userID", userID);
		List<Inventory> list = query.list();
		return list;
	}
}
	
	
	
	
	
	
	
	
	
	
	
