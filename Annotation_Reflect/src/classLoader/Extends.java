package classLoader;

public class Extends {
	public static void main(String[] args) {
		 //application class loader
        System.out.println(ClassLoader.getSystemClassLoader());
        //extensions class loader
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        //bootstrap class loader
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
	}
}
