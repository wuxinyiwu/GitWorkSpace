/**
 * 
 */
package listen.bean;

import java.io.Serializable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@WebListener
public class SerializListener implements HttpSessionActivationListener,  Serializable{
    private String name;
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
	System.out.println(name+"和session一起被序列化(钝化)到硬盘了，session的id是："+se.getSession().getId());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
	  System.out.println(name+"和session一起从硬盘反序列化(活化)回到内存了，session的id是："+se.getSession().getId());
    }

}
