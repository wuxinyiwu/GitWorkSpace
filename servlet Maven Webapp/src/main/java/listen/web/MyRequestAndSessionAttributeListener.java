/**
 * 
 */
package listen.web;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Administrator
 *
 */
@WebListener
public class MyRequestAndSessionAttributeListener implements ServletRequestAttributeListener {

   
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
	//System.out.println("Request域添加属性");
    }

   
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
	//System.out.println("Request域删除属性");
    }

   
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
	//System.out.println("Request域替换属性值");
    }

}
