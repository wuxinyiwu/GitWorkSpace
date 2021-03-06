package reflect;

import java.lang.reflect.Method;

public class TMethod {
	private void test1(){
		System.out.println("私有测试方法1");
	}
	private void test2(String s){
		System.out.println("私有测试方法2，参数String="+s);
	}
	public void test3(String s,int num){
		System.out.println("公有测试方法3，参数String="+s+":num="+num);
	}
	public static void main(String[] args) {
		try {
			Class<?> clazz = Class.forName("reflect.TMethod");
			  //1、得到clazz 对应的类中有哪些方法,不能获取private方法  
		    Method[] methods =clazz.getMethods();  
		    System.out.print("自身及继承Object类的公有方法: ");  
		    for (Method method : methods){  
		        System.out.print(method.getName() + ", ");  
		    }  
		  
		    //2、获取所有的方法(且只获取当着类声明的方法，包括private方法）  
		    Method[] methods2 = clazz.getDeclaredMethods();  
		    System.out.print("\n自身所有方法（公有，私有）: ");  
		    for (Method method : methods2){  
		        System.out.print(method.getName() + ", "); 
		        
		    }  
		  
		    //3、获取指定的方法  
		    Method method = clazz.getDeclaredMethod("test2",String.class);//第一个参数是方法名，后面的是方法里的参数  
		    System.out.println("\nmethod2 : " + method);  
		  
		    Method method2 = clazz.getDeclaredMethod("test3",String.class ,int.class);//第一个参数是方法名，后面的是方法里的参数  
		    System.out.println("method3: " + method2);  
		  
		    //4、执行方法！  
		    Object obj = clazz.newInstance();  
		    method2.invoke(obj, "changwen", 22); 
		    method.invoke(obj,"dddd");
		  //这是本类中调用，不必设置 setAccessible(true);如果是其他类中必须设置，不然method是私有的，无法在其他类中执行
		    method.setAccessible(true);
 		    method.invoke(obj,"dddd");
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
