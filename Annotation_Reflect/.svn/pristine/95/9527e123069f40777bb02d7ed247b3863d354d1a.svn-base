package anno;

@Filter("filter1")
@Filter("filter2")
public class Annotest {

	public static void main(String args[]) {
		try {
			//使用类加载器加载类
			//Class<?> clazz=Class.forName("anno.Annotest");
			Class<?> clazz=Class.forName("test.Hello");
			Class<?> dd=ClassLoader.getSystemClassLoader().loadClass("anno.Test");
			
				//拿到注解实例
				Filter[] annotations = clazz.getAnnotationsByType(Filter.class); 
				if (annotations != null) {

					for (Filter filter : annotations) {

					System.out.println(filter.value());

					}
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
