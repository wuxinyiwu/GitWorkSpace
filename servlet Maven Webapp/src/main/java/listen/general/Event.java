/**
 * 
 */
package listen.general;

/**
 * @author Administrator
 *
 */
public class Event {
    private Object source;

    public Event() {

    }

    public Event(Object source) {
	this.source = source;
    }

    public void setSource(Object source) {
	this.source = source;
    }

    public Object getSource() {
	return source;
    }

}
