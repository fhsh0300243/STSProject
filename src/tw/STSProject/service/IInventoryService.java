package tw.STSProject.service;

import java.util.List;

import tw.STSProject.bean.Inventory;

public interface IInventoryService {
	public boolean insertOrUpadteInventory(int userID, String stockCode, int quantity);
	public int deleteInventory(int userID, String stockCode);
	public List<Inventory> findAllUserInventory(int userID);
}
