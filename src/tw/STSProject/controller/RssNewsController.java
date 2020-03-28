package tw.STSProject.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RssNewsController")
public class RssNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String RSS_URL="https://money.udn.com/rssfeed/news/1001/5592/12040?ch=money";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		URL url=new URL(RSS_URL);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
		StringBuilder sb=new StringBuilder(1024);
		String str;
		
		while((str=in.readLine()) != null) {
			sb.append(str);
		}
		in.close();
		response.getWriter().print(sb.toString());	
	}

}
