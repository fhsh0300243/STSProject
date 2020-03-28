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

import tw.STSProject.service.FavoriteStockService;
import tw.STSProject.service.OutputService;
import tw.STSProject.utility.HibernateUtil;

@WebServlet("/DeleteFavoriteStockController")
public class DeleteFavoriteStockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session hibernateSession = factory.getCurrentSession();
			FavoriteStockService fss=new FavoriteStockService(hibernateSession);
			OutputService os=new OutputService(hibernateSession);
			HttpSession session=request.getSession();
			int uID=(int)session.getAttribute("userID");
			String sCode=request.getParameter("Scode");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			fss.deleteFavoriteStock(uID, sCode);
			
			os.outputStockInformationToJSON(uID);
			
			response.sendRedirect("MainPageTableController");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
}


