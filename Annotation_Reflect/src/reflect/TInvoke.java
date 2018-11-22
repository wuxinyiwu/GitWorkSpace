package reflect;

import java.lang.reflect.Method;


public class TInvoke {
	 /** 
     * 获取clazz 的methodName 方法， 该方法可能是私有方法 
     */  
    public Method getMethod(Class clazz, String methodName, Class ... parameterTypes) {  
        //注意这个循环里的内容！！！ 
        for (; clazz != Object.class; clazz = clazz.getSuperclass()){  
            try {  
                return clazz.getDeclaredMethod(methodName, parameterTypes);  
            } catch (Exception e) { 
     			e.printStackTrace();
            }  
        }  
        return null;  
    }  
   /** 
     * @param className  某个类的全类名 
     * @param methodName 类的一个方法的方法名，该方法也可能是私有方法 
     * @param args  调用该方法需要传入的参数 ...可变参数的意思 
     * @return 调用方法后的返回值 
     */  
    public Object invoke(String className, String methodName, Object ... args) {  
        Object obj = null;  
        try {  
            obj = Class.forName(className).newInstance();  
            return invoke(obj, methodName, args);  
     
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
        return invoke(null, methodName, args);  
    }  
    /** 
     * @param obj  方法执行的那个对象 
     * @param methodName 类的一个方法的方法名，该方法也可能是私有方法,还可能是该方法在父类中定义的私有方法 
     * @param args  调用该方法需要传入的参数 ...可变参数的意思 
     * @return 调用方法后的返回值 
     */  
    public Object invoke(Object obj, String methodName, Object ... args) {  
        //1、获取Method对象  
        Class [] parameterTypes = new Class[args.length];  
        for (int i=0 ; i<args.length; i++){  
            parameterTypes[i] = args[i].getClass();  
        }  
     
        try {  
            //2、执行Method方法  
        
            Method method = getMethod(obj.getClass(), methodName,parameterTypes);  
     
            //通过反射执行private方法  
            method.setAccessible(true);  
     
            //3、返回方法的返回值  
            return method.invoke(obj,args);  
     
        } catch (Exception e) {  
     
        }  
     
        return null;  
    }  
    public void  testInvoke(){
    	Object obj = new TMethod();
    	
    	   invoke(obj, "test3","test3",10);  
		  
		    Object result = invoke(obj, "test2","test2");  
		    System.out.println(result); 
    }
     public static void main(String[] args) {
    	 try {
    		
    		 TInvoke obj = new TInvoke();  
    		   obj.testInvoke();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}  
 	}
	
}
