/**
 * 
 */
package io.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class ByteStreamTest {
    static void p(Object s) {
	System.out.println(s);
    }

    @Test
    /**
     * FileInputStream复制图片
     */
    public void test1() {
	InputStream is;
	OutputStream os;
	try {
	    is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\md\\1.png");
	    os = new FileOutputStream("E:\\workspace\\fileTest\\copy.jpg");
	    int length = 0;
	    byte[] buffer = new byte[512];
	    while (-1 != (length = is.read(buffer))) {
		os.write(buffer, 0, length);
	    }
	    is.close();
	    os.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    /**
     * FileInputStream和FileOutputStream复制文件
     */
    public void test2() {
	InputStream is;
	OutputStream os;
	try {
	    is = new FileInputStream("E:\\workspace\\fileTest\\a.txt");
	    os = new FileOutputStream("E:\\workspace\\fileTest\\test2\\b.txt");
	    int length = 0;
	    byte[] buffer = new byte[512];
	    while (-1 != (length = is.read(buffer))) {
		String str = new String(buffer, "gbk");
		p(str);
		os.write(buffer, 0, length);
	    }
	    is.close();
	    os.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    /**
     * BufferedInputStream和BufferedOutputStream
     */
    public void test3() {
	OutputStream os;
	InputStream is;
	try {
	    is = new FileInputStream("E:\\workspace\\fileTest\\a.txt");
	    os = new FileOutputStream("E:\\workspace\\fileTest\\test2\\b.txt");
	    InputStream bis = new BufferedInputStream(is);
	    OutputStream bos = new BufferedOutputStream(os);

	    byte[] buffer = new byte[512];
	    while (bis.read(buffer) != -1) {
		bos.write(buffer);
	    }

	    bos.close();
	    os.close();
	    bis.close();
	    is.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    /**
     * BufferedInputStream和BufferedOutputStream 没有调用close方法
     * 结果为，复制文件的内容为空，因为在缓存数组中没有close调用时flush掉
     */
    public void test4() {
	OutputStream os;
	InputStream is;
	try {
	    is = new FileInputStream("E:\\workspace\\fileTest\\a.txt");
	    os = new FileOutputStream("E:\\workspace\\fileTest\\test2\\b.txt");
	    InputStream bis = new BufferedInputStream(is);
	    OutputStream bos = new BufferedOutputStream(os);

	    byte[] buffer = new byte[512];
	    while (bis.read(buffer) != -1) {
		bos.write(buffer);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    @Test
    /**
     * DataInputStream和 DataOutputStream 直接读写数据类型
     */
    public void test5() {
	OutputStream os;
	InputStream is;
	try {
	    is = new FileInputStream("E:\\workspace\\fileTest\\test2\\b.txt");
	    os = new FileOutputStream("E:\\workspace\\fileTest\\test2\\b.txt");
	    DataInputStream dis = new DataInputStream(is);
	    DataOutputStream dos = new DataOutputStream(os);
	    byte by = 1;
	    String str = "String中";
	    int i = 100;
	    dos.writeByte(by);
	    dos.writeUTF(str);
	    dos.writeInt(i);

	    // 读取的顺序不能乱，必须和写入的数据类型一致
	    p(dis.readByte());
	    p(dis.readUTF());
	    p(dis.readInt());

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void transform(InputStream in, OutputStream out) {
	int ch = 0;

	try {
	    while ((ch = in.read()) != -1) {

		out.write(ch);
	    } // close while
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    /**
     * ByteArrayInputStream和ByteArrayOutputStream用法测试
     */
    public void test6() {
	String str = "abcdef";

	ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	transform(in, out);

	byte[] result = out.toByteArray();

	System.out.println(out);
	System.out.println(new String(result));

	transform(System.in, System.out); // 从键盘读，输出到显示器
    }

    @Test
    /**
     * ByteArrayInputStream和ByteArrayOutputStream 真正的巧妙用法
     */
    public void test7() {
	try {
	    InputStream input = new FileInputStream("E:\\workspace\\fileTest\\test2\\b.txt");

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int len;
	    while ((len = input.read(buffer)) != -1) {
		baos.write(buffer, 0, len);
	    }
	    baos.flush();

	    // TODO:显示到前台

	    InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
	    OutputStream out = System.out;
	    byte[] buffer2 = new byte[1024];
	    int count = 0;

	    while ((count = stream1.read(buffer2)) != -1) {
		out.write(buffer2, 0, count);
	    }

	    // TODO:本地缓存
	    InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
	    OutputStream out2 = new FileOutputStream("E:\\workspace\\fileTest\\a.txt");
	    byte[] buffer3 = new byte[1024];
	    int count2 = 0;

	    while ((count2 = stream2.read(buffer2)) != -1) {
		out2.write(buffer2, 0, count2);
	    }
	    out2.close();
	    out.close();

	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}
    }

    @Test
    /**
     * 退回输入流PushbackInputStream
     */
    public void test8() {
	String str = "abcdef";

	try {
	    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());

	    PushbackInputStream pbin = new PushbackInputStream(in);
	    int n;
	    int i = 0;
	    while ((n = pbin.read()) != -1) {
		i++;
		System.out.println((char) n + ":" + i);
		if ('b' == n)
		    pbin.unread('U');
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }

    @Test
    /**
     * 退回输入流PushbackInputStream
     */
    public void test9() {
	String str = "abcdefg";

	try {
	    /*
	     * PushbackInputStream pbin = new PushbackInputStream(in,3)
	     * 这个构造函数创建的对象一次可以回推一个缓存
	     */
	    ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
	    PushbackInputStream pbin = new PushbackInputStream(in, 3);
	    int n;
	    int i = 0;
	    byte[] buffer = new byte[3];
	    while ((n = pbin.read(buffer)) != -1) {
		i++;
		String read = new String(buffer);
		System.out.println(read + ":" + i);
		if (read.equals("abc")) {
		    pbin.unread(new byte[] { 'M', 'N', 'O' });
		}
		buffer = new byte[3];
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }

    @Test
    /**
     * 退回输入流PushbackInputStream
     */
    public void test10() {
	String s = "abcdefg";
	/*
	 * PushbackInputStream pbin = new PushbackInputStream(in,4)
	 * 这个构造函数创建的对象一次可以回推一个缓存
	 */
	try {
	    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
	    PushbackInputStream pbin = new PushbackInputStream(in, 2);
	    int n;
	    int i = 0;
	    byte[] buffer = new byte[4];
	    while ((n = pbin.read(buffer)) != -1) {
		i++;
		System.out.println(new String(buffer) + ":" + i);
		// 取回推缓存中的一部分数据
		if (new String(buffer).equals("abcd")) {
		    pbin.unread(buffer, 0, 3);
		}
		buffer = new byte[4];
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    /**
     * 合并流SequenceInputStream进而SequenceOutputStream
     */
    public void test11() {
	String str1 = "你好，";
	String str2 = "中国，";
	String str3 = "我爱你";

	ByteArrayInputStream bis1 = new ByteArrayInputStream(str1.getBytes());
	ByteArrayInputStream bis2 = new ByteArrayInputStream(str2.getBytes());
	ByteArrayInputStream bis3 = new ByteArrayInputStream(str3.getBytes());
	SequenceInputStream seq = new SequenceInputStream(bis1, bis2);
	SequenceInputStream seq2 = new SequenceInputStream(seq, bis3);
	try {
	    int len = 0;
	    byte buf[] = new byte[10];
	    while ((len = seq2.read(buf)) != -1) {
		System.out.println(new String(buf));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    @Test
    /**
     * 合并流SequenceInputStream进而SequenceOutputStream
     */
    public void test12() {
	String str1 = "你好，";
	String str2 = "中国，";
	String str3 = "我爱你";

	ByteArrayInputStream bis1 = new ByteArrayInputStream(str1.getBytes());
	ByteArrayInputStream bis2 = new ByteArrayInputStream(str2.getBytes());
	ByteArrayInputStream bis3 = new ByteArrayInputStream(str3.getBytes());
	ArrayList<InputStream> c = new ArrayList<InputStream>();
	c.add(bis1);
	c.add(bis2);
	c.add(bis3);
	Enumeration<InputStream> em = Collections.enumeration(c);
	
	SequenceInputStream seq = new SequenceInputStream(em);
	try {
	    int len = 0;
	    byte buf[] = new byte[10];
	    while ((len = seq.read(buf)) != -1) {
		System.out.println(new String(buf));
	    }
	   
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    @Test
    /**
     * 合并流SequenceInputStream进而SequenceOutputStream
     */
    public void test13() {
	String str1 = "你好，";
	String str2 = "中国，";
	String str3 = "我爱你";

	ByteArrayInputStream bis1 = new ByteArrayInputStream(str1.getBytes());
	ByteArrayInputStream bis2 = new ByteArrayInputStream(str2.getBytes());
	ByteArrayInputStream bis3 = new ByteArrayInputStream(str3.getBytes());
	
	Vector<InputStream> v = new Vector<InputStream>();
	 
	v.add(bis1);
	v.add(bis2);
	v.add(bis3);

	Enumeration<InputStream> en = v.elements();
	
	SequenceInputStream seq = new SequenceInputStream(en);
	try {
	    int len = 0;
	    byte buf[] = new byte[10];
	    while ((len = seq.read(buf)) != -1) {
		System.out.println(new String(buf));
	    }
	   
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
