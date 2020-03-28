package tw.STSProject.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import tw.STSProject.bean.StockInformation;

public class STSNecessaryTools {
	
	public StockInformation getStockInfoFromInternet(String stockCode) throws IOException, InterruptedException {
		Runtime.getRuntime().exec("python GetStockInfoFromWeb.py "+stockCode);
		Thread.sleep(3500);
		StockInformation si=null;
		String response="";
		String fileName="stock_"+stockCode+".txt";
		Scanner scanner = new Scanner( new File(fileName) );
		while(scanner.hasNext()) {
			response += scanner.next();
		}
		List<String> newResponse=new ArrayList<String>();
		newResponse=Arrays.asList(response.split("'"));
		
		si=new StockInformation();
		si.setStockCode(newResponse.get(newResponse.indexOf("c")+2));
		si.setStockName(newResponse.get(newResponse.indexOf("n")+2));
		si.setTradePrice(Float.valueOf(newResponse.get(newResponse.indexOf("z")+2)));
		si.setTradeVolume(Integer.valueOf(newResponse.get(newResponse.indexOf("tv")+2)));
		si.setAccTradeVolume(Integer.valueOf(newResponse.get(newResponse.indexOf("v")+2)));
		si.setOpeningPrice(Float.valueOf(newResponse.get(newResponse.indexOf("y")+2)));
		si.setHighestPrice(Float.valueOf(newResponse.get(newResponse.indexOf("h")+2)));
		si.setLowestPrice(Float.valueOf(newResponse.get(newResponse.indexOf("l")+2)));
		si.setChange((Float.valueOf(newResponse.get(newResponse.indexOf("z")+2))-(Float.valueOf(newResponse.get(newResponse.indexOf("y")+2)))));
		
		
		scanner.close();
		return si;
	}
	
	public void removePerStockInfoFile(String stockCode) {
		String fileName="stock_"+stockCode+".txt";
		File f1 = new File(fileName);
		if(f1.exists()) {
			f1.delete();
		}
		f1=null;
	}
	
	public void removeStockInformationFile() {
		File f1 = new File("StockInformation.json");
		if(f1.exists()) {
			f1.delete();
		}
		f1=null;
	}
	
	public void removeInventoryFile() {
		File f1 = new File("Inventory.json");
		if(f1.exists()) {
			f1.delete();
		}
		f1=null;
	}
	
	public void removeTransanctionRecordFile() {
		File f1 = new File("TransactionRecord.json");
		if(f1.exists()) {
			f1.delete();
		}
		f1=null;
	}
		
	public String getTAIEXFromInternet () throws IOException, InterruptedException {
		Runtime.getRuntime().exec("python GetTAIEXFromWeb.py");
		Thread.sleep(3500);
		String responseForTAIEX="";
		String fileNameForTAIEX="TAIEX.txt";
		Scanner scannerForTAIEX = new Scanner( new File(fileNameForTAIEX) );
		while(scannerForTAIEX.hasNext()) {
			responseForTAIEX += scannerForTAIEX.next();
		}
		List<String> newResponseForTAIEX=new ArrayList<String>();
		newResponseForTAIEX=Arrays.asList(responseForTAIEX.split("\""));
		String TAIEX=newResponseForTAIEX.get(newResponseForTAIEX.indexOf("tidx.tw")+8);
				
		scannerForTAIEX.close();
		return TAIEX;
	}
	
}
