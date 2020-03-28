package tw.STSProject.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.STSProject.service.OutputService;
import tw.STSProject.utility.HibernateUtil;



@WebServlet("/MainPageTableController")
public class MainPageTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hibernateSession = factory.getCurrentSession();
		HttpSession session = request.getSession();
		int uID=(int) session.getAttribute("userID");
		OutputService os = new OutputService(hibernateSession);
		os.outputInventoryToJSON(uID);
		os.outputTransactionRecordToJSON(uID);
		os.outputStockInformationToJSON(uID);
		Map<String, String> msg = new HashMap<String, String>();
		session.setAttribute("msg", msg);
		
		List<String> newResponseForFS=new ArrayList<String>();
		int lineCountForFS=0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("StockInformation.json"));
			while(br.ready()) {
				br.readLine();
				lineCountForFS++;		
			}
			br.close();
			String[] favoriteStockInfo = new String[lineCountForFS*6]; 
			int indexForFS=0;
			BufferedReader br1 = new BufferedReader(new FileReader("StockInformation.json"));
		    while(br1.ready()) {
		    	String brOneStringForFS = br1.readLine();
		    	newResponseForFS=Arrays.asList(brOneStringForFS.split("\""));
		    	favoriteStockInfo[indexForFS]=newResponseForFS.get(3);
		    	indexForFS++;
		    	favoriteStockInfo[indexForFS]=newResponseForFS.get(7);
		    	indexForFS++;
		    	favoriteStockInfo[indexForFS]=newResponseForFS.get(15);
		    	indexForFS++;
		    	favoriteStockInfo[indexForFS]=newResponseForFS.get(11);
		    	indexForFS++;
		    	favoriteStockInfo[indexForFS]=newResponseForFS.get(31);
		    	indexForFS++;
		    	favoriteStockInfo[indexForFS]=newResponseForFS.get(35);
		    	indexForFS++;
		    }
		    br1.close();
		    
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
		    
		    if(favoriteStockInfo.length==0) {
		    	msg.put("FSnull", "尚無自選股");
		    }
		    if(InventoryData.length==0) {
		    	msg.put("Inull", "尚無庫存股");
		    }
		    if(transactionRecordData.length==0) {
		    	msg.put("TRnull", "尚無交易紀錄");
		    }
		    
		    int userMoney=(int)session.getAttribute("userMoney");
		    request.setAttribute("userMoney", userMoney);
		    request.setAttribute("favoriteStockInfo", favoriteStockInfo);
		    request.setAttribute("InventoryData", InventoryData);
		    request.setAttribute("transactionRecordData", transactionRecordData);
			request.getRequestDispatcher("MainPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
