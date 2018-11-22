package serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Person implements Serializable{

    /**
     * serialVersionUID,序列化版本，当不指定时，会自动生成，当文件不发生变化（类名，方法名，空格，换行，注释）
     * 其值也不会变，在读取序列化对象时会比较序列化id是否一致，不一致时会出错
     */
    private static final long serialVersionUID = 123456789L;

    private int id;
    //private int age;
    private  String name;
    public Person(int id,String name){
      this.id=id;
      this.name=name;
  }
    public String toString() {
	return "Person: " + id + " " + name;
    }
    
   //下面两个方法的作用是通过反射覆盖ObjectInputStream和ObjectOututputStream的读写方法
  /*
    private void writeObject(ObjectOutputStream out) throws IOException{ 
	    
	    out.defaultWriteObject();// 使定制的writeObject()方法可以利用自动序列化中内置的逻辑。 
	    out.writeInt(id);      // 若要保存“int类型的值”，则使用writeInt()
	    out.writeObject(new Person(1,"李"));    // 若要保存“Object对象”，则使用writeObject()
	}
    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{ 
	    in.defaultReadObject();       // 使定制的readObject()方法可以利用自动序列化中内置的逻辑。 
	    id = in.readInt();      // 若要读取“int类型的值”，则使用readInt()
	    Person per = (Person)in.readObject(); // 若要读取“Object对象”，则使用readObject()
	}
	*/
}
