/**
 * 
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class ServletConfigs extends HttpServlet {
    /*
     * ①：Tomcat将http请求文本接收并解析，然后封装成HttpServletRequest类型的
     * request对象，所有的HTTP头数据读可以通过request对象调用对应的方法查询到。
     * 
     * ②：Tomcat同时会要响应的信息封装为HttpServletResponse类型的response对象，
     * 通过设置response属性就可以控制要输出到浏览器的内容，然后将response交给tomcat，
     * tomcat就会将其变成响应文本的格式发送给浏览器.
     * 
     * Java Servlet API 是Servlet容器(tomcat)和servlet之间的接口，
     * 它定义了serlvet的各种方法，还定义了Servlet容器传送给Servlet的对象类，
     * 其中最重要的就是ServletRequest和ServletResponse。
     * 所以说我们在编写servlet时，需要实现Servlet接口，按照其规范进行操作。
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	ServletConfig config = this.getServletConfig();
	String sname = config.getServletName();
	Enumeration<String> initNames = config.getInitParameterNames();
	out.println("Servlet的名字是：" + sname + "<br>");
	while (initNames.hasMoreElements()) {
	    String name = initNames.nextElement();
	    out.println("参数 " + name + "的值为：" + config.getInitParameter(name) + "<br>");
	}
	out.flush();
	out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);

    }

}
