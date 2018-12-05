/**
 * 
 */
package listen.general;

/**
 * @author Administrator
 *
 */
public class PersonListener {
    /**
     * 
     */
    public void doeat(Event e) {
	System.out.println(e+"：人吃东西");
    }
    
    /**
     * 
     */
    public void dorun(Event e) {
	System.out.println(e+"：人在奔跑");
    }
}
