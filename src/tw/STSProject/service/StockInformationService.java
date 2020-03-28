package tw.STSProject.service;

import java.util.List;

import org.hibernate.Session;

import tw.STSProject.bean.StockInformation;
import tw.STSProject.dao.StockInformationDAO;

public class StockInformationService implements IStockInformationService{
	private StockInformationDAO siDAO;
	
	public StockInformationService(Session session) {
		siDAO=new StockInformationDAO(session);
	}
	
	public boolean insertOrUpdateStockInformation(String stockCode, String stockName, float tradePrice, float change, int tradeVolume, int accTradeVolume, float openingPrice, float highestPrice, float lowestPrice) {
		return siDAO.insertOrUpdateStockInformation(stockCode, stockName, tradePrice, change, tradeVolume, accTradeVolume, openingPrice, highestPrice, lowestPrice);
	}
	
	public int deleteStockInformation(String stockCode) {
		return siDAO.deleteStockInformation(stockCode);
	}
	
	public List<StockInformation> findStockInformation(String stockCode) {
		return siDAO.findStockInformation(stockCode);
	}
}
