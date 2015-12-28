package com.system.tools.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet implements WebApplicationContextAware {

	private static final long serialVersionUID = 1L;
	private WebApplicationContext mWebApplicationContext;
	private Map<String, String> handlerMap = new ConcurrentHashMap<String, String>();
	private AtomicBoolean initialize = new AtomicBoolean(false);
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		this.mWebApplicationContext = initWebApplicationContext();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	 response.setContentType("text/html; charset=UTF-8");
	 request.setCharacterEncoding("UTF-8"); 
	 //获取类的全路径以及名称 
	 String classname = "";
	 try {
		 classname = getClassname(request);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 
	 //获取方法名 
	 String methodname = request.getParameter("method"); 
	 System.out.println(classname + " : " + methodname);
		 //获取class文件 
		 Class<?> currentclass = null;
		try {
			currentclass = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 //获取该类所需求的方法 
		 Method method = null;
		try {
			method = currentclass.getMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 //方法的实现 
		 try {
			method.invoke(currentclass.newInstance(), request, response);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void setWebApplicationContext(WebApplicationContext wac) {
		// TODO Auto-generated method stub
		this.mWebApplicationContext = wac;				
	}

	public String getClassname(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		if(initialize.compareAndSet(false, true)){
			handlerMap = mWebApplicationContext.beansOfClassname();
		}		
		String classname = handlerMap.get(getHandlerName(request));
		if(classname == null){
			classname = handlerMap.get("404");
		}
		return classname;
	}
	
	protected String getHandlerName(HttpServletRequest request){
		String path = request.getServletPath();
		int index = path.lastIndexOf('/');
		String handleName =	path.substring(index+1, path.length()-3);
		return handleName;
	}
	
	private WebApplicationContext initWebApplicationContext() {		
		WebApplicationContext wac = new WebApplicationContext(getServletContext());
		wac.init();	
		return wac;		
	}

}