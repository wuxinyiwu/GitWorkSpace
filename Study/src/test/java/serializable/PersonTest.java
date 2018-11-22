/**
 * 
 */
package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 *
 */
@Slf4j
public class PersonTest {
    public void outPut(Person person) {
	try {

	    System.out.println("Person Serial" + person);
	    FileOutputStream fos = new FileOutputStream("Person.txt");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(person);
	    oos.flush();
	    oos.close();
	} catch (Exception e) {
	   e.printStackTrace();
	   log.error("单个对象序列化错误",e);
	}
    }

    /***
     * 序列化单个对象
     */
    @Test
    public void outPutObject() {
	Person person = new Person(1234, "wang");
	outPut(person);
    }

    /**
     * 反序列化单个对象
     */
    @Test
    public void inPutobject() {
	try {
	    FileInputStream fis = new FileInputStream("Person.txt");
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    System.out.println(fis.available()+"==="+fis.getChannel().position());
	    Person person = (Person) ois.readObject();
	    System.out.println(fis.available()+"==="+fis.getChannel().position());
	    ois.close();
	    System.out.println(person);
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public void outPuts(List<Person> persons) {
	FileOutputStream fo=null;
	ObjectOutputStream oos = null;
	File file = new File("Persons.txt");
	boolean isexist = false;// 定义一个用来判断文件是否需要截掉头aced 0005的
	try {
	    if (file.exists()) { // 文件是否存在
		isexist = true;
	    }
	    for (Person person : persons) {
		long pos = 0;
		if (isexist) {
		    // true表示是是否追加
		    fo = new FileOutputStream(file, true);
		    oos = new ObjectOutputStream(fo);
		    pos = fo.getChannel().position() - 4;// 追加的时候去掉头部aced 0005的    
		    fo.getChannel().truncate(pos);
		    oos.writeObject(person);// 进行序列化
		 
		} else {
		    // 文件不存在
		    file.createNewFile();
		    isexist = true;
		    fo = new FileOutputStream(file);
		    oos = new ObjectOutputStream(fo);
		    oos.writeObject(person);// 进行序列化
		    System.out.println("首次对象序列化成功！");
		}
		
	    }
	    oos.flush();
	    oos.close();
	    fo.close();
	   

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 序列化多个对象
     */
    @Test
    public void outPutObjects() {
	List<Person> persons = new ArrayList<Person>();
	persons.add(new Person(5678, "wang") );
	persons.add( new Person(4567, "li"));
	persons.add(new Person(1234, "zhang"));
	
	
	outPuts(persons);

    }

    /**
     * 反序列化多个对象
     */
    @Test
    public void inPutObjects() {
	File file = new File("Persons.txt");
	if (file.exists()) {
	    ObjectInputStream ois;
	    try {
		FileInputStream fn = new FileInputStream(file);
		ois = new ObjectInputStream(fn);
		while (fn.available() > 0) {// 代表文件还有内容
		    Person p = (Person) ois.readObject();// 从流中读取对象
		    System.out.println(p.getName());
		}
		
		ois.close();
		fn.close();
		// 注意在循环外面关闭
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }
	}
    }
     
    /**
     * 直接存储多个对象
     * @param persons
     */
    public void outPutss(List<Person> persons) {
	FileOutputStream fo=null;
	ObjectOutputStream oos = null;
	File file = new File("Personss.txt");
	boolean isexist = false;// 定义一个用来判断文件是否需要截掉头aced 0005的
	try {
	    if (file.exists()) { // 文件是否存在
		isexist = true;
	    }
	    for (Person person : persons) {
		long pos = 0;
		if (isexist) {
		    // true表示是是否追加
		    fo = new FileOutputStream(file, true);
		    oos = new ObjectOutputStream(fo);
		    oos.writeObject(person);// 进行序列化
		 
		} else {
		    // 文件不存在
		    file.createNewFile();
		    isexist = true;
		    fo = new FileOutputStream(file);
		    oos = new ObjectOutputStream(fo);
		    oos.writeObject(person);// 进行序列化
		    System.out.println("首次对象序列化成功！");
		}
		
	    }
	    oos.flush();
	    oos.close();
	    fo.close();
	   

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * 序列化多个对象
     */
    @Test
    public void outPutObjectss() {
	List<Person> persons = new ArrayList<Person>();
	persons.add(new Person(5678, "wang") );
	persons.add( new Person(4567, "li"));
	persons.add(new Person(1234, "zhang"));
	
	
	outPutss(persons);

    }
    
    
    
    /**
     * 反序列化多个对象
     */
    @Test
    public void inPutObjectss() {
	File file = new File("Personss.txt");
	if (file.exists()) {
	    ObjectInputStream ois;
	    try {
		FileInputStream fn = new FileInputStream(file);
		ois = new ObjectInputStream(fn);
		while (fn.available() > 0) {// 代表文件还有内容
		    Person p = (Person) ois.readObject();// 从流中读取对象
		    fn.getChannel().position(fn.getChannel().position()+4);
		    System.out.println(p.getName());
		}
		
		ois.close();
		fn.close();
		// 注意在循环外面关闭
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }
	}
    }
}
