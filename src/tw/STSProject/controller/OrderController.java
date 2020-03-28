package tw.STSProject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
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

import tw.STSProject.bean.Inventory;
import tw.STSProject.bean.StockInformation;
import tw.STSProject.service.InventoryService;
import tw.STSProject.service.StockInformationService;
import tw.STSProject.service.TransactionRecordService;
import tw.STSProject.service.UsersService;
import tw.STSProject.utility.HibernateUtil;
import tw.STSProject.utility.STSNecessaryTools;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, InterruptedException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hibernateSession = factory.getCurrentSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String stockCode=request.getParameter("stockCode");
		int qantity=Integer.valueOf(request.getParameter("qantity"));
		String buyOrSell=request.getParameter("buyOrSell");
		int userMoney=(int) session.getAttribute("userMoney");
		int uID=(int) session.getAttribute("userID");
		
		Map<String, String> errorMsgMapFromOrder = new HashMap<String, String>();
		request.setAttribute("errorMsgKeyFromOrder", errorMsgMapFromOrder);

		StockInformationService sis =new StockInformationService(hibernateSession);
		InventoryService is=new InventoryService(hibernateSession);
		UsersService us=new UsersService(hibernateSession);
		TransactionRecordService trs= new TransactionRecordService(hibernateSession);
		STSNecessaryTools stsnt= new STSNecessaryTools();
		StockInformation siBean=stsnt.getStockInfoFromInternet(stockCode);
		sis.insertOrUpdateStockInformation(siBean.getStockCode(), siBean.getStockName(), siBean.getTradePrice(), (siBean.getTradePrice()-siBean.getOpeningPrice()), siBean.getTradeVolume(), siBean.getAccTradeVolume(), siBean.getOpeningPrice(), siBean.getHighestPrice(), siBean.getLowestPrice());
		List<StockInformation> siList =sis.findStockInformation(stockCode);
		StockInformation si=siList.get(0);
		int quantityInInventory=0;
		List<Inventory> iList=is.findAllUserInventory(uID);
		Iterator<Inventory> iListIT=iList.iterator();
		if(buyOrSell.equals("buy")) {
			if(si.getTradePrice()*qantity>userMoney) {
				errorMsgMapFromOrder.put("moneyError", "你的模擬幣不夠");
				request.getRequestDispatcher("MainPageTableController").forward(request, response);
			}
			else {
				while(iListIT.hasNext()) {
					Inventory in= iListIT.next();
					if(in.getStockCode().equals(stockCode))
						quantityInInventory=in.getQuantity();	
				}
				is.insertOrUpadteInventory(uID, stockCode, (qantity+quantityInInventory));
				trs.insertTransactionRecord(qantity, buyOrSell, si.getTradePrice(), stockCode, uID);
				
				
				us.updateUserMoney(uID, (int)(userMoney-(si.getTradePrice()*qantity)));
				
				
				session.setAttribute("userMoney", (int)(userMoney-(si.getTradePrice()*qantity)));
				
			}
		}
		else if(buyOrSell.equals("sell")) {
			while(iListIT.hasNext()) {
				Inventory in= iListIT.next();
				if(in.getStockCode().equals(stockCode))
					quantityInInventory=in.getQuantity();	
			}
			if(qantity>quantityInInventory) {
				errorMsgMapFromOrder.put("inventoryError", "你的庫存不夠");
				request.getRequestDispatcher("MainPageTableController").forward(request, response);
			}
			else if(qantity==quantityInInventory) {
				is.deleteInventory(uID, stockCode);
			}
			else {
				is.insertOrUpadteInventory(uID, stockCode, (quantityInInventory-qantity));
			}
			userMoney=(int) (userMoney+(si.getTradePrice()*qantity));
			trs.insertTransactionRecord(qantity, buyOrSell, si.getTradePrice(), stockCode, uID);
			session.setAttribute("userMoney",userMoney);
			us.updateUserMoney(uID, userMoney);
		}
		response.sendRedirect("MainPageTableController");
	}

}
