/**
 * 
 */
package test;

import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class Tests {
    
    @Test
    public void test(){
	String contextPath="/sss";
	String uri="/sss/index";
	
	
	String requestServletName = uri.substring(contextPath.length(),uri.length());
    
	System.out.println(uri.lastIndexOf("."));
	System.out.println(requestServletName);
    }
}
