/**
 * 
 */
package web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class ServletContexts extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletContext context = this.getServletContext();
	String contextPath = context.getContextPath();
	String realPath = context.getRealPath("/WEB-INF/web.xml");
	System.out.println("获取web.xml的绝对路径"+realPath);
	// 统一资源定位符path必须是/开头，代表当前web应用程序的根目录。返回返回的一个代表某个资源的URL对象。
	URL url = context.getResource("/index.jsp");

	Set set = context.getResourcePaths("/WEB-INF");
	Iterator iterator = set.iterator();
	System.out.println("WEB-INF文件下的所有文件：");
	while (iterator.hasNext()) {
	    System.out.println(iterator.next());

	}
	
	context.setAttribute("contex", "contexValue");
	System.out.println(context.getAttribute("contex"));
	context.removeAttribute("contex");

	System.out.println(context.getAttribute("contex") == null ? "属性删除成功" : "属性删除失败");

	Enumeration<String> enumer = context.getInitParameterNames();
	System.out.println("所有的context参数：");
	while (enumer.hasMoreElements()) {
	    String string = (String) enumer.nextElement();
	    System.out.println(context.getInitParameter(string));
	}

	InputStream is = context.getResourceAsStream("/index.jsp");

	Reader read = new InputStreamReader(is);
	BufferedReader br = new BufferedReader(read);
	byte[] buffer = new byte[1024];
	String length;
	System.out.println("读取index.jsp文件的内容如下：");
	while ((length = br.readLine()) != null) {
	    System.out.println(length);
	}
	br.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response);
    }

}
