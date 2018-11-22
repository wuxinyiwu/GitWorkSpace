/**
 * 
 */
package io.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import utils.DateUtils;

/**
 * @author Administrator
 *
 */
public class FileTest {
    static void p(String s) {
	System.out.println(s);
    }

    @Test
    /**
     * 构造File路径的三种方法
     */
    public void test1() {
	File file1 = new File("E:\\workspace\\a.txt");
	File file2 = new File("E:/workspace/a.txt");
	p(System.getProperty("file.separator"));
	System.out.println(File.separator);
	File file3 = new File("E:" + File.separator + "workspace" + File.separator + "a.txt");
	System.out.println(file1.getName() + "\n" + file2.getName() + "\n" + file3.getName());

    }

    @Test
    /**
     * File对象的三种构造函数
     */
    public void test2() {
	File dir = new File("E:/workspace");
	File file1 = new File("E:/workspace", "a.txt");
	File file2 = new File(dir, "a.txt");
	File file3 = new File("E:/workspace/a.txt");
	System.out.println(file1.getName() + "\n" + file2.getName() + "\n" + file3.getName());

    }

    @Test
    /**
     * File对象的属性类方法
     */
    public void test3() {
	File f1 = new File("E:/workspace", "a.txt");
	p("File Name: " + f1.getName());
	p("Path: " + f1.getPath());
	p("Abs Path: " + f1.getAbsolutePath());
	p("Parent: " + f1.getParent());
	p(f1.exists() ? "exists" : "does not exist");
	p(f1.canWrite() ? "is writeable" : "is not writeable");
	p(f1.canRead() ? "is readable" : "is not readable");
	p("is " + (f1.isDirectory() ? "" : "not" + " a directory"));
	p(f1.isFile() ? "is normal file" : "might be a named pipe");
	p(f1.isAbsolute() ? "is absolute" : "is not absolute");
	p("File last modified: " + DateUtils.dateToStr(new Date(f1.lastModified()), "yyyy-MM-dd HH:mm:ss"));
	p("File size: " + f1.length() + " Bytes");

    }

    @Test
    /**
     * File对象的文件目录操作
     */
    public void test4() {
	File dir = new File("E:/workspace");
	File[] filelist = dir.listFiles();
	for (File f : filelist) {
	    if (f.isFile()) {
		p("目录下的文件名：" + f.getName());
		p("目录文件的绝对路径：" + f.getAbsolutePath());
	    }
	}

	File[] filelist2 = File.listRoots();
	for (File f : filelist2) {
	    p("根目录下的文件名：" + f.getName());
	    p("根目录文件的绝对路径：" + f.getAbsolutePath());
	}
	String[] filelist3 = dir.list();
	p("file.list()结果：===================================================");
	for (String f : filelist3) {
	    p(f);
	}
    }
    
    @Test
    /**
     * File过滤器FilenameFilter匿名实现
     */
    public void test5() {
	File dir = new File("E:/workspace");
	File[] filelist = dir.listFiles(new FilenameFilter() {
	    
	    @Override
	    public boolean accept(File dir, String name) {
		if(name.endsWith(".txt")){
		    return true;
		}
		return false;
	    }
	}
	    
	    );
	
	for (File f : filelist) {
	    if (f.isFile()) {
		p("目录下的txt结尾文件名：" + f.getName());
		p("目录文件的绝对路径：" + f.getAbsolutePath());
	    }
	}
    }
    
    
    @Test
    /**
     * File过滤器FileFilter匿名实现
     */
    public void test6() {
	File dir = new File("E:/workspace");
	File[] filelist = dir.listFiles(new FileFilter() {
	    
	    @Override
	    public boolean accept(File pathname) {
		if(pathname.getName().endsWith(".txt")){
		    return true;
		}
		return false;
	    }
	});
	
	for (File f : filelist) {
	    if (f.isFile()) {
		p("目录下的txt结尾文件名：" + f.getName());
		p("目录文件的绝对路径：" + f.getAbsolutePath());
	    }
	}
    }
    
    
    @Test
    /**
     * 文件及文件夹的创建
     */
    public void test7() {
	File dir = new File("E:\\workspace\\fileTest\\test");
	//创建多层目录
	dir.mkdirs();
	File file = new File("E:\\workspace\\fileTest\\test\\a.txt");
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		
		e.printStackTrace();
	    }
    }
    
    @Test
    /**
     * 文件及文件夹的删除
     */
    public void test8() {
	File dir = new File("E:\\workspace\\fileTest\\test");
	//创建多层目录
	dir.mkdirs();
	File file = new File("E:\\workspace\\fileTest\\test\\b.txt");
	if(file.delete()){
	    p("文件删除成功");
	}else{
	    p("文件删除失败");
	}
	
	if(dir.delete()){
	    p("目录删除成功");
	}else{
	    p("目录删除失败");
	}
    }
    
    @Test
    /**
     * 文件及文件夹的重命名
     */
    public void test9() {
	File dir = new File("E:\\workspace\\fileTest\\test");
	//创建多层目录
	dir.mkdirs();
	File file = new File("E:\\workspace\\fileTest\\test\\a.txt");
	if(file.renameTo(new File("E:\\workspace\\fileTest\\test\\b.txt"))){
	    p("文件重命名成功");
	}else{
	    p("文件重命名失败");
	}
	
	if(dir.renameTo(new File("E:\\workspace\\fileTest\\test2"))){
	    p("目录重命名成功");
	}else{
	    p("目录重命名失败");
	}
    }
    
    @Test
    /**
     * 文件及文件夹的移动
     */
    public void test10() {
	
	File file = new File("E:\\workspace\\fileTest\\test2\\b.txt");
	if(file.renameTo(new File("E:\\workspace\\fileTest\\a.txt"))){
	    p("文件移动成功");
	}else{
	    p("文件移动失败");
	}
	
    }
}
