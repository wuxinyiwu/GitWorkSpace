/**
 * 
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.Filter.Chain;

/**
 * @author Administrator
 *
 */
public class CodeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	String filterName = filterConfig.getFilterName();
	System.out.println("过滤器的名字为："+filterName);
    }
   

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest request=(HttpServletRequest)req;
	HttpServletResponse response = (HttpServletResponse)res;
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	/*这句代码其实有两个作用：通知response以UTF-8输出和浏览器以UTF-8打开。
	  即等价于response.setHeader("content-type", "text/html;charset=UTF-8");
	  和response.setCharacterEncoding("UTF-8");两句代码。
	 */
	
	System.out.println("编码过滤器放行前");
	//放行请求资源
	chain.doFilter(request, response);
	System.out.println("编码过滤器放行后");
	
    }

    
    @Override
    public void destroy() {
	
    }
}
