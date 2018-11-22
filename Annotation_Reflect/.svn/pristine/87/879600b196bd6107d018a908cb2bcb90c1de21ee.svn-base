package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class Constructors {
	public Constructors() {
		System.out.println("无参构造函数");
	}

	
	public Constructors(String s) {
		System.out.println("有1个参构造函数");
		System.out.println(s);
	}

	public Constructors(final String s, int num) {
		System.out.println("有2个参构造函数");
		System.out.println(s);
	}

	public static void main(String[] args) {
		try {

			// 1、获取指定构造方法
			Class clazz = Class.forName("reflect.Constructors");
			// 无参构造函数生成对象
			clazz.newInstance();
			Constructor con = clazz.getConstructor(String.class, int.class);
			Constructors cons = (Constructors) con.newInstance("获取构造器实例化对象", 4);
			// 2、 获取全部构造方法
			Constructor[] conarr = clazz.getConstructors();
			for (Constructor constructor : conarr) {
				// 获取每个构造函数的参数字节码对象
				Class[] parameterTypes = constructor.getParameterTypes();
				for (Class pclass : parameterTypes) {
					System.out.print(pclass.getName() + ", ");
				}
				System.out.println();
				// 获取构造参数对象
				Parameter[] parameters = constructor.getParameters();
				for (Parameter parameter : parameters) {
					 System.out.println("通过parameter.getModifiers()获取参数修饰符:" + Modifier.toString(parameter.getModifiers()));
					 System.out.println("通过parameter.getName()获取参数名：" + parameter.getName()); 
					 System.out.println("通过parameter.getParameterizedType()获取参数化类型(泛型)：" + parameter.getParameterizedType()); 
					 System.out.println("通过parameter.toString()获取参数的字符串描述：" + parameter.toString());
					 System.out.println("通过parameter.isSynthetic()判断参数是否是合成的：" + parameter.isSynthetic()); 
					 System.out.println("通过parameter.isImplicit()判断参数是否是隐式的：" + parameter.isImplicit()); 
					 System.out.println("通过parameter.isNamePresent()判断参数是否存在可用：" + parameter.isNamePresent()); 
					 System.out.println("通过parameter.isVarArgs()判断参数是否是参数列表：" + parameter.isVarArgs() + "\n");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
