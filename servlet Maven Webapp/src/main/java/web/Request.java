/**
 * 
 */
package web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class Request extends HttpServlet {
    public void p() {
	System.out.println("=============================================");
	System.out.println();
    }

    public void p(Object obj) {
	System.out.println(obj);

    }

    public void p(Object function, Object text, Object value) {
	System.out.println(function + "方法" + "   |  " + text + "   |  " + value);

    }

    @SuppressWarnings("deprecation")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	// 参数获取

	Enumeration<String> paramets = request.getParameterNames();

	while (paramets.hasMoreElements()) {
	    String paName = (String) paramets.nextElement();
	    System.out.println("参数" + paName + "的值为：" + request.getParameter(paName));

	}
	p();

	// 属性设置获取
	request.setAttribute("Attribute", "一个设置的属性值");
	Enumeration<String> attributs = request.getAttributeNames();
	while (attributs.hasMoreElements()) {
	    String attrName = (String) attributs.nextElement();
	    System.out.println("属性" + attrName + "的值为：" + request.getAttribute(attrName));
	}
	p();
	// 路径和信息的获取
	// 统一资源定位符
	String url = request.getRequestURL().toString();
	p("getRequestURL()", "获取统一资源定位符", url);

	// 统一资源标识符
	String uri = request.getRequestURI().toString();
	p("getRequestURI()", "获取统一资源标识符", uri);
	
	//协议和版本
	p("getProtocol()", "获取协议和版本号", request.getProtocol());
	
	//协议
	p("getScheme()", "获取协议", request.getScheme());
	
	//主机（域名） 如localhost，当用ip访问时，获取的是ip
	p("getServerName()", "获取主机（域名）", request.getServerName());
	
	//获取在tomcat下的项目名称
	p("getContextPath()", "tomcat下的项目名称", request.getContextPath());
	
	//获取所有请求参数？之后的
	String queryString = request.getQueryString();
	if(queryString!=null)
	p("getQueryString()", "获取主机（域名）", URLDecoder.decode(request.getQueryString()));

	//获取远程的ip地址
	p("getRemoteAddr", "获取远程主机ip", request.getRemoteAddr());
	

	//获取远程的主机(ip)地址
	p("getRemoteHost()", "获取远程主机ip", request.getRemoteHost());
	
	
	p("getHeader(String name)", "获取请求头", request.getHeader("Accept-Language"));
	
	Enumeration<String> headers = request.getHeaderNames();
	p("所有请求头信息：");
	while (headers.hasMoreElements()) {
	    String name = (String) headers.nextElement();
	    p("获取请求头"+name+"：         "+request.getHeader(name));
	}

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response);
    }

}
