/**
 * 
 */
package listen.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Administrator
 *
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener{

    
    @Override
    public void requestDestroyed(ServletRequestEvent request) {
	System.out.println("请求Resquest销毁");
    }

    
    @Override
    public void requestInitialized(ServletRequestEvent response) {
	System.out.println("请求Resquest创建");
    }

}
