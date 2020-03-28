package tw.STSProject.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import tw.STSProject.bean.FavoriteStock;
import tw.STSProject.bean.Inventory;
import tw.STSProject.bean.StockInformation;
import tw.STSProject.bean.TransactionRecord;
import tw.STSProject.service.InventoryService;
import tw.STSProject.service.StockInformationService;
import tw.STSProject.service.TransactionRecordService;
import tw.STSProject.utility.STSNecessaryTools;

public class OuputDAO implements IOutputDAO {
	
	private Session session;
	
	public OuputDAO(Session session) {
		this.session=session;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void outputFavoriteStockToCsv(int userID) throws IOException, SQLException {
		FavoriteStockDAO fsDAO=new FavoriteStockDAO(session);
		StockInformationDAO siDAO=new StockInformationDAO(session);
		
		List<FavoriteStock> fsSearchResult=fsDAO.findAllUserFavoriteStock(userID);
		Iterator<FavoriteStock> fsSearchResultIT =fsSearchResult.iterator();
		
		List<StockInformation> siList = new ArrayList<StockInformation>();
		while(fsSearchResultIT.hasNext()) {
			FavoriteStock fsBean = fsSearchResultIT.next();
			List<StockInformation> siListTemp = siDAO.findStockInformation(fsBean.getStockCode());
			siList.add(siListTemp.get(0));
	    }
		
		Iterator<StockInformation> siListIT =siList.iterator();
		BufferedWriter bw = new BufferedWriter(new FileWriter("StockInformation.csv",true));		
		while(siListIT.hasNext()) {
			StockInformation siBean=siListIT.next();
			bw.append(
					  siBean.getStockCode()+","+
					  Float.toString(siBean.getTradePrice())+","+
					  Float.toString(siBean.getChange())+","+
					  Integer.toString(siBean.getTradeVolume())+","+
					  Integer.toString(siBean.getAccTradeVolume())+","+
					  Float.toString(siBean.getOpeningPrice())+","+
					  Float.toString(siBean.getHighestPrice())+","+
					  Float.toString(siBean.getLowestPrice()));
			bw.newLine();
		}
		bw.close();
	}
	
	public void outputStockInformationToJSON(int userID) throws IOException, SQLException {
		FavoriteStockDAO fsDAO=new FavoriteStockDAO(session);
		StockInformationDAO siDAO=new StockInformationDAO(session);
		STSNecessaryTools stsnt=new STSNecessaryTools();
		stsnt.removeStockInformationFile();
		
		List<FavoriteStock> fsSearchResult=fsDAO.findAllUserFavoriteStock(userID);
		System.out.println("fsSearchResult: "+fsSearchResult);
		Iterator<FavoriteStock> fsSearchResultIT =fsSearchResult.iterator();
		
		List<StockInformation> siList = new ArrayList<StockInformation>();
		while(fsSearchResultIT.hasNext()) {
			FavoriteStock fsBean = fsSearchResultIT.next();
			List<StockInformation> siListTemp = siDAO.findStockInformation(fsBean.getStockCode());
			siList.add(siListTemp.get(0));
	    }
		
		Iterator<StockInformation> siListIT =siList.iterator();	
		BufferedWriter bw = new BufferedWriter(new FileWriter("StockInformation.json",true));
		while(siListIT.hasNext()) {
			StockInformation siBean=siListIT.next();
			bw.append("{"+
					"\"StockCode\""+":"+"\""+siBean.getStockCode()+"\""+","+
					"\"StockName\""+":"+"\""+siBean.getStockName()+"\""+","+
					"\"TradePrice\""+":"+"\""+Float.toString(siBean.getTradePrice())+"\""+","+
					"\"Change\""+":"+"\""+Float.toString(siBean.getChange())+"\""+","+
					"\"TradeVolume\""+":"+"\""+Integer.toString(siBean.getTradeVolume())+"\""+","+
					"\"AccTradeVolume\""+":"+"\""+Integer.toString(siBean.getAccTradeVolume())+"\""+","+
					"\"OpeningPrice\""+":"+"\""+Float.toString(siBean.getOpeningPrice())+"\""+","+
					"\"HighestPrice\""+":"+"\""+Float.toString(siBean.getHighestPrice())+"\""+","+
					"\"LowestPrice\""+":"+"\""+Float.toString(siBean.getLowestPrice())+"\""+
					"}");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	public void outputInventoryToJSON(int userID) throws IOException, SQLException {
		InventoryService iis= new InventoryService(session);
		StockInformationService sis=new StockInformationService(session);
		STSNecessaryTools stsnt=new STSNecessaryTools();
		stsnt.removeInventoryFile();
		
		List<Inventory> inventoryList =iis.findAllUserInventory(userID);
		Iterator<Inventory> ilIT = inventoryList.iterator();
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("Inventory.json",true));
		while(ilIT.hasNext()) {
			Inventory iBean=ilIT.next();
			List<StockInformation> siList =sis.findStockInformation(iBean.getStockCode());
			Iterator<StockInformation> siListIT= siList.iterator();
			StockInformation siBean=siListIT.next();
			bw1.append("{"+
					   "\"StockCode\""+":"+"\""+iBean.getStockCode()+"\""+","+
					   "\"StockName\""+":"+"\""+siBean.getStockName()+"\""+","+
					   "\"Quantity\""+":"+"\""+String.valueOf(iBean.getQuantity())+"\""+","+
					   "\"TradePrice\""+":"+"\""+String.valueOf(siBean.getTradePrice())+"\""+","+
					   "\"MarketCap\""+":"+"\""+String.valueOf(iBean.getQuantity()*siBean.getTradePrice())+"\""+","+
					   "}");
			bw1.newLine();
		}
		bw1.flush();
		bw1.close();
	}
	
	public void outputTransactionRecordToJSON(int userID) throws IOException, SQLException{
		TransactionRecordService trs = new TransactionRecordService(session);
		StockInformationService sis=new StockInformationService(session);
		STSNecessaryTools stsnt=new STSNecessaryTools();
		stsnt.removeTransanctionRecordFile();
		
		List<TransactionRecord> trList =trs.findAllUserTransactionRecord(userID);
		Iterator<TransactionRecord> trListIT=trList.iterator();
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("TransactionRecord.json",true));
		while(trListIT.hasNext()) {
			TransactionRecord trBean=trListIT.next();
			List<StockInformation> siList =sis.findStockInformation(trBean.getStockCode());
			Iterator<StockInformation> siListIT= siList.iterator();
			StockInformation siBean=siListIT.next();
			bw2.append("{"+
					"\"StockCode\""+":"+"\""+trBean.getStockCode()+"\""+","+
					"\"StockName\""+":"+"\""+siBean.getStockName()+"\""+","+
					"\"SellOrBuy\""+":"+"\""+trBean.getSellOrBuy()+"\""+","+
					"\"Quantity\""+":"+"\""+String.valueOf(trBean.getQuantity())+"\""+","+
					"\"TradePrice\""+":"+"\""+String.valueOf(trBean.getPrice())+"\""+","+
					"\"TradeDay\""+":"+"\""+trBean.getRecordDay()+"\""+","+
					"}");
			bw2.newLine();
		}
		bw2.flush();
		bw2.close();
	}
}
