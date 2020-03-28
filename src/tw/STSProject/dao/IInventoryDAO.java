package tw.STSProject.dao;

import java.util.List;

import tw.STSProject.bean.Inventory;

public interface IInventoryDAO {
	public boolean insertOrUpadteInventory(int userID, String stockCode, int quantity);
	public int deleteInventory(int userID, String stockCode);
	public List<Inventory> findAllUserInventory(int userID);
}
