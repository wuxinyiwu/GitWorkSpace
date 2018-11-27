/**
 * 
 */
package servlet3.Annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServlet {
    //servlet的访问url
    String value();
    String[] urlPattens() default{""};
    
    //Servlet的描述
    String desription() default "";
    
    //Servlet的显示名称
    String displayName() default "";
    
    //Servletd的名字
    String name() default "";
    
    //Servlet的init参数
    WebInitParam[] initParams() default {};
    
}
