/**
 * 
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
@WebServlet(
	name = "Annolet", 
	urlPatterns = "/anno", 
	loadOnStartup = 1, 
	initParams = {	
        	@WebInitParam(name = "name", value = "小明"),   
        	@WebInitParam(name = "pwd", value = "123456") 
	}
)
//@WebServlet("/anno")
public class ServletAnno extends HttpServlet {
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	Enumeration<String> initNames = this.getInitParameterNames();
	out.print("servlet的名字为"+this.getServletName());
	while (initNames.hasMoreElements()) {
	    String name = initNames.nextElement();
	    out.println("参数 " + name + "的值为：" + this.getInitParameter(name) + "<br>");
	}
	out.flush();
	out.close();
    }
	
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
    }
}
