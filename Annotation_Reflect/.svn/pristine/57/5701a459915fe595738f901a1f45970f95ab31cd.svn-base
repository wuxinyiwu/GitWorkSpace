package test;

import com.sun.org.glassfish.gmbal.Description;

import anno.Filter;

/**
 * 简述：这是一个hello类.
 * 这是一个纯粹hello测试类，用来测试Javadoc的
 * @author shadow
 * @version 1.0
 *
 */
@Filter("filter1")
@Filter("filter2")
public class Hello {
	/** 
	 * 由于triger和表用一个DMSource，所以要区分和表的迁移成功标记 
	 * 
	 */
	static boolean isTrigerSuccess = false; 
	public static void main(String[] args) {
		if(isTrigerSuccess){
			
		}

	}
	/**
	 * 输出传入的字符串的值
	 * @param name 字符串，用来输出
	 */
	@Description("I am method annotation")
	public void hello(String name){
		System.out.println(name);
	}
	public void usehello(){
		hello("name");
		
	}
}
