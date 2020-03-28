package tw.STSProject.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.STSProject.bean.Users;
import tw.STSProject.service.UsersService;
import tw.STSProject.utility.HibernateUtil;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processLogin(request,response);
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response){
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			HttpSession session = request.getSession();
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			session.setAttribute("errorMsgMap", errorMsgMap);

			String userAccount=request.getParameter("userName");
			String userPwd=request.getParameter("userPassword");
			String rm = request.getParameter("rememberMe");
			
			if (userAccount == null || userAccount.trim().length() == 0) {
				errorMsgMap.put("AccountEmptyError", "User account should not be empty");
			}
			
			if (userPwd == null || userPwd.trim().length() == 0) {
				errorMsgMap.put("PasswordEmptyError", "User password should not be empty");
			}

			if (!errorMsgMap.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("TAIEXController");
				rd.forward(request, response);
				return;
			}
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session hibernateSession = factory.getCurrentSession();
			UsersService us=new UsersService(hibernateSession);
			List<Users> loginResult = us.findUsers(userAccount, userPwd);

			RequestDispatcher rd;
			if(loginResult.size()>0) {
				Iterator<Users> loginResultIT = loginResult.iterator();
				Users uTemp = loginResultIT.next();
				session.setAttribute("userID", uTemp.getUserID());
				session.setAttribute("userMoney", uTemp.getTotalMoney());
				Cookie cookieUser = null;
				Cookie cookiePassword = null;
				Cookie cookieRememberMe = null;
				
				if (rm != null) {
					cookieUser = new Cookie("aaa", userAccount); //userAccountCookie
					cookieUser.setMaxAge(30 * 24 * 60 * 60);
					cookieUser.setPath(request.getContextPath());

					
					cookiePassword = new Cookie("ppp", userPwd); //userAccountCookie
					cookiePassword.setMaxAge(30 * 24 * 60 * 60);
					cookiePassword.setPath(request.getContextPath());

					cookieRememberMe = new Cookie("rm", "true");
					cookieRememberMe.setMaxAge(30 * 24 * 60 * 60);
					cookieRememberMe.setPath(request.getContextPath());
				} else { 
					cookieUser = new Cookie("aaa", userAccount);
					cookieUser.setMaxAge(0); 
					cookieUser.setPath(request.getContextPath());

					cookiePassword = new Cookie("ppp", userPwd);
					cookiePassword.setMaxAge(0);
					cookiePassword.setPath(request.getContextPath());

					cookieRememberMe = new Cookie("rm", "true");
					cookieRememberMe.setMaxAge(0);
					cookieRememberMe.setPath(request.getContextPath());
				}
				response.addCookie(cookieUser);
				response.addCookie(cookiePassword);
				response.addCookie(cookieRememberMe);
				
				session.setAttribute("LoginOK", uTemp);
				
				response.sendRedirect("MainPageTableController");
			}
			else {
				errorMsgMap.put("LoginError", "Account doesn't exit or password wrong");
				rd=request.getRequestDispatcher("TAIEXController");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		
	}

}
