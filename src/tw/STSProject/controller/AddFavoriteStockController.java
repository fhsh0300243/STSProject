package tw.STSProject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.STSProject.bean.StockInformation;
import tw.STSProject.service.FavoriteStockService;
import tw.STSProject.service.OutputService;
import tw.STSProject.service.StockInformationService;
import tw.STSProject.utility.HibernateUtil;
import tw.STSProject.utility.STSNecessaryTools;

@WebServlet("/AddFavoriteStockController")
public class AddFavoriteStockController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session=request.getSession();
			int uID=(int)session.getAttribute("userID");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String sCode=request.getParameter("Scode");
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session hibernateSession = factory.getCurrentSession();
			STSNecessaryTools nt= new STSNecessaryTools();
			FavoriteStockService fss=new FavoriteStockService(hibernateSession);
			StockInformationService sis = new StockInformationService(hibernateSession);
			OutputService os=new OutputService(hibernateSession);
//			List<FavoriteStock> fsSearchResult = fss.findAllUserFavoriteStock(uID);
//			Iterator<FavoriteStock> fsSearchResultIT =fsSearchResult.iterator();
			
			StockInformation stock = nt.getStockInfoFromInternet(sCode);
			Thread.sleep(1500);
			sis.insertOrUpdateStockInformation(stock.getStockCode(), stock.getStockName(), stock.getTradePrice(),stock.getChange(), stock.getTradeVolume(), stock.getAccTradeVolume(), stock.getOpeningPrice(), stock.getHighestPrice(), stock.getLowestPrice());
			fss.insertFavoriteStock(uID, sCode);
			
			os.outputStockInformationToJSON(uID);


			request.getRequestDispatcher("MainPageTableController").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
}
