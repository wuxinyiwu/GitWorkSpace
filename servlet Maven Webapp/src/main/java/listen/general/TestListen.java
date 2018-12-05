/**
 * 
 */
package listen.general;


/**
 * @author Administrator
 *
 */
public class TestListen {
    public static void main(String[] args) {
	PersonListener listen = new PersonListener();
	Person person = new Person();
	person.registListener(listen);
	person.eat();
	person.run();
    }
}
