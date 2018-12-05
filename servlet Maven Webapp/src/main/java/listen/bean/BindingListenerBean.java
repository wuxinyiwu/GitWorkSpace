/**
 * 
 */
package listen.bean;


import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Data 该注解能够自动创建出来get、set、toString、equals、hashCode等方法
 * @NoArgsConstructor 这个是无参构造器
 * @AllArgsConstructor 全参构造器
 * @Setter set方法
 * @Getter get方法
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BindingListenerBean implements HttpSessionBindingListener{
    private String name;
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
	System.out.println("对象被绑定到session域");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
	System.out.println("对象从session域解绑");
    }

}
