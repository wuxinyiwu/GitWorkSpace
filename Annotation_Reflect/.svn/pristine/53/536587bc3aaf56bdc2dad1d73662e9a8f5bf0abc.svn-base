package reflect;

public class Instance {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		 Class clazz;
		try {

		    //使用Class类的newInstance()方法创建类的一个对象  
		    //实际调用的类的那个 无参数的构造器（这就是为什么写的类的时候，要写一个无参数的构造器，就是给反射用的）  
		    //一般的，一个类若声明了带参数的构造器，也要声明一个无参数的构造器  
			clazz = Class.forName("reflect.Instance");
			 Instance obj = (Instance) clazz.newInstance();  
			    System.out.println(obj);  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
		  
		   
	}
}
