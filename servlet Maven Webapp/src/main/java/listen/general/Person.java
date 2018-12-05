/**
 * 
 */
package listen.general;

/**
 * @author Administrator
 *
 */
public class Person {
    private PersonListener listen;

    public void eat() {
	if (listen != null) {
	    listen.doeat(new Event(this));
	}
    }

    public void run() {
	if (listen != null) {
	    listen.dorun(new Event(this));
	}
    }

    public void registListener(PersonListener listen) {
	this.listen = listen;
    }
}
