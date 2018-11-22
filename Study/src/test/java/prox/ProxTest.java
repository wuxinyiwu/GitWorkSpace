package prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxTest {
	/**
	 * 静态代理，代理类继续被代理类，在初始化时生成被代理类对象
	 * 实现被代理对象的方法，客户端调用的代理的方法，代理对象调用被代理对象同样的方法
	 * 在调用的前后可以对被代理对象进行操作
	 */
	@Test
	public void prox(){
		Car car = new CarProx();
		car.run();
	}
	@Test
	public void movePorxy(){
		LogSuvCar logHandler=new LogSuvCar();  
        Car suvcar=(Car)logHandler.newProxyInstance(new SUVCar());  
        suvcar.run();  
	}
	@Test
	public void movePorxy2(){
		SUVCar suvcar=new SUVCar();
        Car car=(Car)Proxy.newProxyInstance(suvcar.getClass().getClassLoader(), suvcar.getClass().getInterfaces()
        		, new InvocationHandler(){
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						 System.out.println("start------------>>");
						 	if(args!=null){
						        for(int i=0;i<args.length;i++){  
						            System.out.println(args[i]);  
						        }  
						 	}
					        Object ret=null;  
					        try{  
					            /*原对象方法调用前处理日志信息*/  
					            System.out.println("satrt-->>");  
					              
					            //调用目标方法  
					            ret=method.invoke(suvcar, args);  
					            /*原对象方法调用后处理日志信息*/  
					            System.out.println("success-->>");  
					        }catch(Exception e){  
					            e.printStackTrace();  
					            System.out.println("error-->>");  
					            throw e;  
					        }  
					        return ret;  
					}
        	
				});
        car.run();  
	}
}
