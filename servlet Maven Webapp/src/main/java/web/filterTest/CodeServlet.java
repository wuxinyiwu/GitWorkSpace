/**
 * 
 */
package web.filterTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class CodeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String query = URLDecoder.decode(request.getQueryString());
	System.out.println(query);
	PrintWriter out = response.getWriter();
	out.println("中文字符过滤器过滤后");
	out.flush();
	out.close();
	
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response);
    }

}
