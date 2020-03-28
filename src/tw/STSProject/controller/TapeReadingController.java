package tw.STSProject.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.STSProject.bean.StockInformation;
import tw.STSProject.service.OutputService;
import tw.STSProject.service.StockInformationService;
import tw.STSProject.utility.HibernateUtil;
import tw.STSProject.utility.STSNecessaryTools;


@WebServlet("/TapeReadingController")
public class TapeReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hibernateSession = factory.getCurrentSession();
		OutputService os = new OutputService(hibernateSession);
		STSNecessaryTools nt = new STSNecessaryTools();
		StockInformationService sis = new StockInformationService(hibernateSession);
		HttpSession session = request.getSession();
		int uID = (int) session.getAttribute("userID");
		response.setContentType("text/html;charset=UTF-8");
		response.setIntHeader("Refresh", 1);

		try {
			request.setCharacterEncoding("UTF-8");
			os.outputStockInformationToJSON(uID);
			List<String> newResponse = new ArrayList<String>();
			int lineCount = 0;

			BufferedReader br = new BufferedReader(new FileReader("StockInformation.json"));
			while (br.ready()) {
				br.readLine();
				lineCount++;
			}
			br.close();
			
			String[] favoriteStockNum = new String[lineCount];
			int index = 0;
			BufferedReader br1 = new BufferedReader(new FileReader("StockInformation.json"));
			while (br1.ready()) {
				String brOneString = br1.readLine();
				newResponse = Arrays.asList(brOneString.split("\""));
				favoriteStockNum[index] = newResponse.get(3);
				index++;
			}
			br1.close();

			for (int i = 0; i < favoriteStockNum.length; i++) {
				System.out.println(favoriteStockNum[i]);
				StockInformation si= nt.getStockInfoFromInternet(favoriteStockNum[i]);
				sis.insertOrUpdateStockInformation(si.getStockCode(), si.getStockName(), si.getTradePrice(), si.getChange(), si.getTradeVolume(), si.getAccTradeVolume(), si.getOpeningPrice(), si.getHighestPrice(), si.getLowestPrice());
			}
			os.outputStockInformationToJSON(uID);

			String[] favoriteStockInfo = new String[lineCount * 6];
			index = 0;
			BufferedReader br2 = new BufferedReader(new FileReader("StockInformation.json"));
			while (br2.ready()) {
				String brOneString = br2.readLine();
				newResponse = Arrays.asList(brOneString.split("\""));
		    	favoriteStockInfo[index]=newResponse.get(3);
		    	index++;
		    	favoriteStockInfo[index]=newResponse.get(7);
		    	index++;
		    	favoriteStockInfo[index]=newResponse.get(15);
		    	index++;
		    	favoriteStockInfo[index]=newResponse.get(11);
		    	index++;
		    	favoriteStockInfo[index]=newResponse.get(31);
		    	index++;
		    	favoriteStockInfo[index]=newResponse.get(35);
		    	index++;
			}
			br2.close();
			
		    List<String> newResponseForI=new ArrayList<String>();
		    int lineCountForI=0;
			BufferedReader bs = new BufferedReader(new FileReader("Inventory.json"));
			while(bs.ready()) {
				bs.readLine();
				lineCountForI++;		
			}
			bs.close();
			String[] InventoryData = new String[lineCountForI*5]; 
			int indexForI=0;
			BufferedReader bs1 = new BufferedReader(new FileReader("Inventory.json"));
		    while(bs1.ready()) {
		    	String brOneStringForI = bs1.readLine();
		    	newResponseForI=Arrays.asList(brOneStringForI.split("\""));
		    	InventoryData[indexForI]=newResponseForI.get(3);
		    	indexForI++;
		    	InventoryData[indexForI]=newResponseForI.get(7);
		    	indexForI++;
		    	InventoryData[indexForI]=newResponseForI.get(11);
		    	indexForI++;
		    	InventoryData[indexForI]=newResponseForI.get(15);
		    	indexForI++;
		    	InventoryData[indexForI]=newResponseForI.get(19);
		    	indexForI++;
		    }
		    bs1.close();
		    
		    List<String> newResponseForTR=new ArrayList<String>();
		    int lineCountForTR=0;
			BufferedReader bt = new BufferedReader(new FileReader("TransactionRecord.json"));
			while(bt.ready()) {
				bt.readLine();
				lineCountForTR++;		
			}
			bt.close();
			String[] transactionRecordData = new String[lineCountForTR*6]; 
			int indexForTR=0;
			BufferedReader bt1 = new BufferedReader(new FileReader("TransactionRecord.json"));
		    while(bt1.ready()) {
		    	String brOneStringForTR = bt1.readLine();
		    	newResponseForTR=Arrays.asList(brOneStringForTR.split("\""));
		    	transactionRecordData[indexForTR]=newResponseForTR.get(3);
		    	indexForTR++;
		    	transactionRecordData[indexForTR]=newResponseForTR.get(7);
		    	indexForTR++;
		    	transactionRecordData[indexForTR]=newResponseForTR.get(11);
		    	indexForTR++;
		    	transactionRecordData[indexForTR]=newResponseForTR.get(15);
		    	indexForTR++;
		    	transactionRecordData[indexForTR]=newResponseForTR.get(19);
		    	indexForTR++;
		    	transactionRecordData[indexForTR]=newResponseForTR.get(23);
		    	indexForTR++;
		    }
		    bt1.close();
		    
		    int userMoney=(int)session.getAttribute("userMoney");
		    request.setAttribute("userMoney", userMoney);
		    request.setAttribute("favoriteStockInfo", favoriteStockInfo);
		    request.setAttribute("InventoryData", InventoryData);
		    request.setAttribute("transactionRecordData", transactionRecordData);
			
			request.getRequestDispatcher("MainPageWithTape.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
