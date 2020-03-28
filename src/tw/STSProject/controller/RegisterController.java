package tw.STSProject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.STSProject.service.UsersService;
import tw.STSProject.utility.HibernateUtil;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hibernateSession = factory.getCurrentSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Map<String, String> msgMapFromRegister = new HashMap<String, String>();
		session.setAttribute("msgMapFromRegister", msgMapFromRegister);
		String userAccount=request.getParameter("usrname");
		String userPwd=request.getParameter("psw");
		UsersService us = new UsersService(hibernateSession);
		boolean userRegister=us.insertUsers(userAccount, userPwd, 100000);
		
		if(userRegister) {
			msgMapFromRegister.put("registerOK", "註冊成功 請重新登入");
			response.sendRedirect("TAIEXController");
		}
		else {
			msgMapFromRegister.put("registerError", "註冊失敗 請重新註冊");
			request.getRequestDispatcher("Register.jsp");
		}
	}

}
