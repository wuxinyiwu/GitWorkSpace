package reflect;

import java.lang.reflect.Field;

class Person {  
    public String name;  
    private Integer age;  
  
    public Person() {  
    }  
  
    public Person(String name, Integer age) {  
        this.name = name;  
        this.age = age;  
    }  
    public Integer getAge() {
		return age;
	}  
} 

class Student extends Person{  
    
} 
public class TField {

/** 
 * Field: 封装了字段的信息 
 */  
public void testField() throws  
        ClassNotFoundException, NoSuchFieldException, IllegalAccessException {  
  
    Class clazz = Class.forName("reflect.Person");  
  
    //1、获取字段  
    //1.1 获取Field的数组,私有字段也能获取  
    Field[] fields = clazz.getDeclaredFields();  
    for (Field field: fields) {  
        System.out.print(field.getName() + ", ");  
    }  
  
    //1.2 获取指定名字的Field（如果是私有的，见下面的4)  
    Field field = clazz.getDeclaredField("name");  
    System.out.println("\n获取指定Field名=: " + field.getName());  
  
    Person person = new Person("ABC", 12);  
    //2、获取指定对象的Field的值  
    Object val = field.get(person);  
    System.out.println("获取指定对象字段'name'的Field的值=： " + val);  
  
    //3、设置指定对象的Field的值  
    field.set(person, "changwen2");  
    System.out.println("设置指定对象字段'name'的Field的值=: " + person.name);  
  
    //4、若该字段是私有的，需要调用setAccessible(true)方法  
    Field field2 = clazz.getDeclaredField("age");  
    field2.setAccessible(true); 
    System.out.println("获取指定私有字段名=: " + field2.getName());  
}  


/** 
 * 一个实例： 
 * 反射获取一个继承Person2的Student类 
 * 设置字段"age"=20(该字段可能为私有，可能在其父类中） 
 */  
public void testClassField() throws ClassNotFoundException, IllegalAccessException, InstantiationException {  
    String className = "reflect.Student";  
    String fieldName = "age";  //可能为私有，可能在其父类中  
    Object val = 20;  
  
    //创建className 对应类的对象，并为其fieldName赋值为val  
    Class clazz = Class.forName(className);  
    Field field = null;  
    for (Class clazz2 = clazz; clazz2 != Object.class; clazz2 = clazz2.getSuperclass()){  
        try {  
            field = clazz2.getDeclaredField(fieldName);  
        } catch (Exception e) {  
  
        }  
    }  
  
    Object obj = clazz.newInstance();  
    assert field != null;  
    field.setAccessible(true);  
    field.set(obj, val);  
  
    Student stu = (Student) obj;  
    System.out.println("age = " + stu.getAge());  
}  

	public static void main(String[] args) {
		TField field=new TField();
		try {
			field.testField();
			
			System.out.println("===============================s");
			field.testClassField();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
