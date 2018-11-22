package anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;



@Test("This is Class Annotation")
public class TestAnno {
	@Test("This is method1 Annotation")
	public  void test() {}
	@Test("This is method2 Annotation")
	public static void main(String[] args) {
		try {
			Class<?> clazz=Class.forName("anno.TestAnno");
		
			//找到类上的注解
			if(clazz.isAnnotationPresent(Test.class)){
				Test test=clazz.getAnnotation(Test.class);
				System.out.println(test.value());
			}
			
			//方法1：找到方法上的注解 
			Method[] methods = clazz.getMethods();
			for (Method method : methods) { 
				//检测方法上面的Test注解是否存在
				boolean isExit = method.isAnnotationPresent(Test.class); 
				if (isExit) { 
					//拿到Test注解实例 
					Test annotation = method.getAnnotation(Test.class);
					String value = annotation.value(); 
					System.out.println("value==" + value);
					}
				}
			//方法2：找到方法上的注解
			for (Method method : methods) { 
				//获取方法的所有注解
					Annotation[] annos= method.getAnnotations();
						for(Annotation anno:annos){
							//逐个查询注解是否是Test注解
							if(anno instanceof Test){
								String value = ((Test) anno).value();
								System.out.println("value==" + value);
							}
					}
					
				}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
