package tw.STSProject.service;

import java.util.List;

import tw.STSProject.bean.StockInformation;

public interface IStockInformationService {
	public boolean insertOrUpdateStockInformation(String stockCode, String stockName, float tradePrice, float change, int tradeVolume, int accTradeVolume, float openingPrice, float highestPrice, float lowestPrice);
	public int deleteStockInformation(String stockCode);
	public List<StockInformation>  findStockInformation(String stockCode);
}
