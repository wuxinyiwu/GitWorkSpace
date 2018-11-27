/**
 * 
 */
package servlet3.AnnotaionTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet3.Annotaion.WebServlet;
import util.ScanClassUtil;

/**
 * @ClassNmae AnnotationHandleFilter
 * @Description:使用Filter作为注解的处理器
 * @author Administrator
 *
 */
public class AnnotationHandleFilte implements Filter {
    private ServletContext servletContex = null;

    /**
     * 初始化时扫描项目指定包下面的使用WebService注解的类 将注解的映射地址和类的class对象以键值对的形式放在Map中
     * 然后将map设置为ServletContext的属性，在doFilter时获取
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	System.out.println("========AnnotationHandleFilter处理器初始化开始");
	servletContex = filterConfig.getServletContext();
	
	Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

	// 获取web.xml中配置的需要扫描的包
	String basePackage = filterConfig.getInitParameter("basepack");
	// 有，就是多个包分割符
	if (basePackage.indexOf(",") > 0) {
	    String[] packageNames = basePackage.split(",");
	    for (String packageName : packageNames) {
		addServletClassToServletContex(packageName, classMap);
	    }
	} else {
	    addServletClassToServletContex(basePackage, classMap);
	}
    }

    /**
     * @Description:添加ServletClass到ServletContext中
     * @param packageName
     * @param classMap
     */
    private void addServletClassToServletContex(String packageName, Map<String, Class<?>> classMap) {
	Set<Class<?>> setclasses = ScanClassUtil.getClasses(packageName);
	
	
	for (Class<?> clazz : setclasses) {
	  
	    if (clazz.isAnnotationPresent(WebServlet.class)) {
		//System.out.println("==========有WebServlet注解的类");
		Object obj;
		try {
		    obj = clazz.newInstance();
		    Method initMethod = clazz.getDeclaredMethod("init");
		    if (initMethod != null) {
			// 有初始化方法时，先初始化
			initMethod.invoke(obj);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		WebServlet webServletInstance = clazz.getAnnotation(WebServlet.class);
		// 获取注解的value属性
		String annotationAttrValue = webServletInstance.value();
		if (!annotationAttrValue.equals("")) {
		    classMap.put(annotationAttrValue, clazz);

		}

		// 获取注解实例的urlPattens属性的值
		String[] urlPatterns = webServletInstance.urlPattens();
		for (String urlpattern : urlPatterns) {
		    classMap.put(urlpattern, clazz);
		}

		servletContex.setAttribute("servletClassMap", classMap);

		System.out.println("annotationAttrValue：" + annotationAttrValue);
		String targetClassName = annotationAttrValue.substring(annotationAttrValue.lastIndexOf("/") + 1);
		System.out.println("targetClassName：" + targetClassName);
		System.out.println(clazz);
	    }
	}
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	System.out.println("==========WebServlet注解处理器开始处理=========");
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;
	// 取出初始化设置的ServletContext属性
	Map<String, Class<?>> classMap = (Map<String, Class<?>>) servletContex.getAttribute("servletClassMap");

	// 获取contexpath，也就是项目路径，例如http://localhost:8080/servlet的/servlet
	String contextPath = req.getContextPath();

	// 获取类的URI（统一资源标识符），例如http://localhost:8080/servlet/test的/servlet/test
	String uri = req.getRequestURI();

	/*
	 * 模拟servlet，没有继承HTTPServlet，所以就不会自己执行doGet和doPost方法 需要自己指定，指定方法为!
	 */
	// 如果没有指定调用servlet的哪个方法
	if (uri.indexOf("!") == -1) {
	    // 获取用户的请求方式
	    String reqMethod = req.getMethod();
	    // 获取请求的servlet的路径
	    String requestServletName;
	    int lastReg = uri.lastIndexOf(".do");
	    // 以.do结尾的或者直接就是不带.的uri
	    requestServletName = (lastReg != -1) ? uri.substring(contextPath.length(), lastReg)
		    : uri.substring(contextPath.length(), uri.length());

	    // 获取请求的类的Class对象
	    Class<?> clazz = classMap.get(requestServletName);
	    if(clazz==null){
		res.sendRedirect("404.jsp");
		return;
	    }
	    // 创建类的实例
	    Object obj = null;
	    try {
		obj = clazz.newInstance();
	    } catch (InstantiationException e) {
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    }
	    Method targetMethod = null;
	    if (reqMethod.equalsIgnoreCase("get")) {
		try {
		    targetMethod = clazz.getDeclaredMethod("doGet", HttpServletRequest.class,
			    HttpServletResponse.class);

		} catch (NoSuchMethodException e) {
		    e.printStackTrace();
		} catch (SecurityException e) {
		    e.printStackTrace();
		}
	    } else {
		try {
		    targetMethod = clazz.getDeclaredMethod("doPost", HttpServletRequest.class,
			    HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
		    e.printStackTrace();
		} catch (SecurityException e) {
		    e.printStackTrace();
		}
	    }
	    // 反射调用对应的doGet或者doPost方法
	    try {
		targetMethod.invoke(obj, req, res);
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		e.printStackTrace();
	    }

	} else {
	    // 获取要请求的servlet路径
	    String requestServletName = uri.substring(contextPath.length(), uri.lastIndexOf("!"));
	    // 获取要调用的servlet的方法
	    int lastReg = uri.lastIndexOf(".do");
	    String invokeMethodName = (lastReg != -1) ? uri.substring(uri.lastIndexOf("!") + 1, lastReg)
		    : uri.substring(uri.lastIndexOf("!") + 1, uri.length());

	    // 获取要使用的类
	    Class<?> clazz = classMap.get(requestServletName);
	    if(clazz==null){
		res.sendRedirect("404.jsp");
		return;
	    }
	    // 创建类的实例
	    Object obj = null;
	    try {
		obj = clazz.newInstance();
	    } catch (InstantiationException e1) {
		e1.printStackTrace();
	    } catch (IllegalAccessException e1) {
		e1.printStackTrace();
	    }
	    try {
		Method methodDo = clazz.getDeclaredMethod(invokeMethodName, HttpServletRequest.class,
			HttpServletResponse.class);
		methodDo.invoke(obj, req, res);

	    } catch (NoSuchMethodException e) {
		 res.sendRedirect("404.jsp");
		    return;
		
	    } catch (SecurityException e) {
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		e.printStackTrace();
	    }

	}
    }

    @Override
    public void destroy() {

    }

}
