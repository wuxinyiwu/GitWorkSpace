/**
 * 
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class Response extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*ServletContext context = this.getServletContext();
	Object count = context.getAttribute("count");
	int i =0;
	if(count==null){
	    context.setAttribute("count", new Integer(0));
	    
	}else{
	    i = Integer.parseInt(count.toString());
	    i++;
	    context.setAttribute("count", new Integer(i));
	}
	
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.print("你是第"+(i++)+"位访客");
	if(i>3){
	    
	    //302表示重定向，等价于sendRedirect
	    response.setStatus(302);
	    response.setHeader("location","http://www.baidu.com");
	    
	    //response.sendRedirect("request");
	    response.sendRedirect("/servlet/request");
	}else{
	    response.setHeader("Refresh","3");
	}*/
	request.getRequestDispatcher("request").forward(request, response);
	
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response);
    }

}
