/**
 * 
 */
package servlet3.AnnotaionTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet3.Annotaion.WebServlet;

/**
 * @author Administrator
 *
 */
@WebServlet("/annoServlet")
public class AnnoServelt {
    public void init(){
	//System.out.println("Servlet的初始化方法");
    }
    
    /**
     * @param request
     * @param response
     */
    private void doPost(HttpServletRequest request, HttpServletResponse response) {
	//System.out.println("do***处理请求");
    }
    
    /**
     * 
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request,HttpServletResponse response){
	doPost(request,response);
    }

    public void test(HttpServletRequest request,HttpServletResponse response){
	//System.out.println("！请求访问@webServlet的方法成功");
    }
   
}
