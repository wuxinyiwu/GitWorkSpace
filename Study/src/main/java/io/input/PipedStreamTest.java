/**
 * 
 */
package io.input;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author Administrator
 *
 */
public class PipedStreamTest {
    public static void main(String[] args) {
	Sender sender = new Sender();
	Receiver receiver = new Receiver();

	PipedOutputStream outStream = sender.getOutStream();
	PipedInputStream inStream = receiver.getInStream();

	try {
	    // inStream.connect(outStream); // 与下一句一样
	    outStream.connect(inStream);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	receiver.start();
	sender.start();

    }
}

class Sender extends Thread {
    private PipedOutputStream outStream = new PipedOutputStream();

    public PipedOutputStream getOutStream() {
	return outStream;
    }

    public void run() {
	 // writeShortMessage();
	writeLongMessage();
    }

    // 向“管道输出流”中写入一则较简短的消息："this is a short message"
    private void writeShortMessage() {
	String strInfo = "this is a short message";
	try {
	    outStream.write(strInfo.getBytes());
	    outStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 向“管道输出流”中写入一则较长的消息
    private void writeLongMessage() {
	StringBuilder sb = new StringBuilder();
	// 通过for循环写入1020个字节
	for (int i = 0; i < 102; i++)
	    sb.append("0123456789");
	// 再写入26个字节。
	sb.append("abcdefghijklmnopqrstuvwxyz");
	// str的总长度是1020+26=1046个字节
	String str = sb.toString();
	try {
	    // 将1046个字节写入到“管道输出流”中
	    outStream.write(str.getBytes());
	    outStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

class Receiver extends Thread {
    private PipedInputStream inStream = new PipedInputStream();

    public PipedInputStream getInStream() {
	return inStream;
    }

    public void run() {
	 //readMessageOnce() ;
	 readMessageContinue() ;
    }

    // 从“管道输入流”中读取1次数据
    public void readMessageOnce() {
	// 虽然buf的大小是2048个字节，但最多只会从“管道输入流”中读取1024个字节。
	// 因为，“管道输入流”的缓冲区大小默认只有1024个字节。
	byte[] buf = new byte[2048];
	try {
	    int len = inStream.read(buf);
	    System.out.println(new String(buf, 0, len));
	    inStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    // 从“管道输入流”读取>1024个字节时，就停止读取
    public void readMessageContinued() {
	int total = 0;
	while (true) {
	    byte[] buf = new byte[1024];
	    try {
		int len = inStream.read(buf);
		total += len;
		System.out.println(new String(buf, 0, len));
		// 若读取的字节总数>1024，则退出循环。
		System.out.println("total:"+total);
		if (total > 1024)
		    break;
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	try {
	    inStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    

    // 从“管道输入流”读取所有内容
    public void readMessageContinue() {
	int total = 0;
	
	    byte[] buf = new byte[1024];
	    try {
		int len ;
		while((len=inStream.read(buf))!=-1){
		    System.out.println(new String(buf, 0, len));
		}
		
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	

	try {
	    inStream.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
