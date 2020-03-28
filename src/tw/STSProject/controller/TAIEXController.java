package tw.STSProject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tw.STSProject.utility.STSNecessaryTools;


@WebServlet("/TAIEXController")
public class TAIEXController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAction(request, response);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, ServletException {
		STSNecessaryTools stsnt= new STSNecessaryTools();
		String TAIEX=stsnt.getTAIEXFromInternet();


		request.setAttribute("TAIEX", TAIEX);
		request.getRequestDispatcher("UserLogin.jsp").forward(request, response);
	}


}
