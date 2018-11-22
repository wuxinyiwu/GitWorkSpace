package anno;

public class GetClass {

	public static void main(String[] args) {
		Class clazz = null;

		// 1 直接通过类名.Class的方式得到
		clazz = GetClass.class;
		System.out.println("通过类名: " + clazz);

		// 2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）
		Object obj = new GetClass();
		clazz = obj.getClass();
		System.out.println("通过getClass(): " + clazz);

		// 3 通过全类名获取，用的比较多，但可能抛出ClassNotFoundException异常
		try {
			clazz = Class.forName("anno.GetClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("通过全类名获取: " + clazz);
	}

}
