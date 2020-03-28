package tw.STSProject.service;

import java.util.List;

import org.hibernate.Session;

import tw.STSProject.bean.Inventory;
import tw.STSProject.dao.InventoryDAO;

public class InventoryService implements IInventoryService {
	private InventoryDAO iDAO;
	
	public InventoryService(Session session) {
		iDAO=new InventoryDAO(session);
	}
	
	public boolean insertOrUpadteInventory(int userID, String stockCode, int quantity) {
		return iDAO.insertOrUpadteInventory(userID, stockCode, quantity);
	}
	
	public int deleteInventory(int userID, String stockCode) {
		return iDAO.deleteInventory(userID, stockCode);
	}
	
	public List<Inventory> findAllUserInventory(int userID) {
		return iDAO.findAllUserInventory(userID);
	}
}