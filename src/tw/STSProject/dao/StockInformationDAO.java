package tw.STSProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tw.STSProject.bean.StockInformation;

public class StockInformationDAO implements IStockInformationDAO{
	private Session session;
	
	public StockInformationDAO(Session session) {
		this.session=session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public boolean insertOrUpdateStockInformation(String stockCode, String stockName, float tradePrice, float change, int tradeVolume, int accTradeVolume, float openingPrice, float highestPrice, float lowestPrice) {
		Query<StockInformation> query = session.createQuery("from StockInformation where StockCode = :stockCode", StockInformation.class);
		query.setParameter("stockCode", stockCode);
		List<StockInformation> list = query.list();
		if(list.size()<=0) {
			StockInformation siTemp = new StockInformation();
			siTemp.setStockCode(stockCode);
			siTemp.setStockName(stockName);
			siTemp.setTradePrice(tradePrice);
			siTemp.setChange(change);
			siTemp.setTradeVolume(tradeVolume);
			siTemp.setAccTradeVolume(accTradeVolume);
			siTemp.setOpeningPrice(openingPrice);
			siTemp.setHighestPrice(highestPrice);
			siTemp.setLowestPrice(lowestPrice);
			session.save(siTemp);
			return true;
		}
		Query<?> query2 = session.createQuery("update StockInformation set TradePrice = :tradePrice, Change = :change, "
								  + "TradeVolume = : tradeVolume, AccTradeVolume = :accTradeVolume, OpeningPrice = :openingPrice, "
								  + "HighestPrice = :highestPrice, LowestPrice = :lowestPrice"
								  + "  where StockCode = :stockCode");
		query2.setParameter("tradePrice", tradePrice);
		query2.setParameter("change", change);
		query2.setParameter("tradeVolume", tradeVolume);
		query2.setParameter("accTradeVolume", accTradeVolume);
		query2.setParameter("highestPrice", highestPrice);
		query2.setParameter("lowestPrice", lowestPrice);
		query2.setParameter("stockCode", stockCode);
		query2.setParameter("openingPrice", openingPrice);
		query2.executeUpdate();
		return false;
	}
	
	public int deleteStockInformation(String stockCode) {
		Query<StockInformation> query = session.createQuery("delete from StockInformation where StockCode = :stockCode", StockInformation.class);
		query.setParameter("stockCode", stockCode);
		int numData=query.executeUpdate();
		return numData;
	}
	
	public List<StockInformation> findStockInformation(String stockCode) {
		Query<StockInformation> query = session.createQuery("from StockInformation where StockCode = :stockCode", StockInformation.class);
		query.setParameter("stockCode", stockCode);
		List<StockInformation> result =query.list();
		return result;
	}
	
}
