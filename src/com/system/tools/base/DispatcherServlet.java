package com.system.tools.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DispatcherServlet extends FrameworkServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class);
	private static final String DEFAULT_STRATEGIES_PATH = "DispatcherServlet.properties";
	private static final Properties defaultStrategies = new Properties();

	private List<HandlerMapping> handlerMappings;
	private List<HandlerAdapter> handlerAdapters;
	private List<ViewResolver> viewResolvers;

	static {
		try {
			defaultStrategies.load(DispatcherServlet.class.getResourceAsStream(DEFAULT_STRATEGIES_PATH));
		} catch (IOException ex) {
			throw new IllegalStateException("Could not load 'DispatcherServlet.properties': " + ex.getMessage());
		}
	}

	@Override
	protected void onRefresh(WebApplicationContext wac) {
		initHandlerMappings(wac);
		initHandlerAdapters(wac);
		initViewResolvers(wac);
	}

	private void initHandlerMappings(WebApplicationContext wac) {
		Map<String, HandlerMapping> map = wac.beansOfType(HandlerMapping.class);
		if (!map.isEmpty()) {
			this.handlerMappings = new ArrayList<HandlerMapping>(map.values());
		}
		if (this.handlerMappings == null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("No HandlerMappings found in servlet '" + getServletName() + "': using default");
			}
			this.handlerMappings = getDefaultStrategies(wac, HandlerMapping.class);
		}
	}

	private void initHandlerAdapters(WebApplicationContext wac) {
		Map<String, HandlerAdapter> map = wac.beansOfType(HandlerAdapter.class);
		if (!map.isEmpty()) {
			this.handlerAdapters = new ArrayList<HandlerAdapter>(map.values());
		}
		if (this.handlerAdapters == null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("No HandlerAdapters found in servlet '" + getServletName() + "': using default");
			}
			this.handlerAdapters = getDefaultStrategies(wac, HandlerAdapter.class);
		}
	}

	private void initViewResolvers(WebApplicationContext wac) {
		Map<String, ViewResolver> map = wac.beansOfType(ViewResolver.class);
		if (!map.isEmpty()) {
			this.viewResolvers = new ArrayList<ViewResolver>(map.values());
		}
		if (this.viewResolvers == null) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("No ViewResolvers found in servlet '" + getServletName() + "': using default");
			}
			this.viewResolvers = getDefaultStrategies(wac, ViewResolver.class);
		}
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("DispatcherServlet with name '" + getServletName() + "' received request for ["
					+ request.getRequestURI() + "]");
		}
		doDispatch(request, response);
	}

	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Bound request context to thread: " + request);
		}

		Object handler = getHandler(request);
		HandlerAdapter ha = getHandlerAdapter(handler);
		ModelAndView mv = ha.handle(request, response, handler);

		// Do we need view name translation?
		if (mv != null && !mv.hasView()) {
			mv.setViewName(getDefaultViewName(request));
		}

		// Did the handler return a view to render?
		if (mv != null && !mv.wasCleared()) {
			render(mv, request, response);
		}
	}

	protected <T> List<T> getDefaultStrategies(WebApplicationContext wac, Class<T> strategyInterface) {
		String key = strategyInterface.getName();
		List<T> strategies = new ArrayList<T>();
		String value = defaultStrategies.getProperty(key);
		if (value != null) {
			StringTokenizer token = new StringTokenizer(value, ",");
			while (token.hasMoreTokens()) {
				String className = token.nextToken();
				try {
					Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
					strategies.add((T) wac.createBean(clazz));
				} catch (Exception e) {
					LOGGER.error("Can't load class " + className + "", e);
				}
			}
		} else {
			strategies = Collections.emptyList();
		}
		return strategies;
	}

	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		View view = null;
		if (mv.isReference()) {
			// We need to resolve the view name.
			view = resolveViewName(mv.getViewName(), mv.getModelInternal(), request);
			if (view == null) {
				throw new ServletException("Could not resolve view with name '" + mv.getViewName()
						+ "' in servlet with name '" + getServletName() + "'");
			}
		} else {
			// No need to lookup: the ModelAndView object contains the actual
			// View object.
			view = mv.getView();
			if (view == null) {
				throw new ServletException("ModelAndView [" + mv + "] neither contains a view name nor a "
						+ "View object in servlet with name '" + getServletName() + "'");
			}
		}
		// Delegate to the View object for rendering.
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Rendering view [" + view + "] in DispatcherServlet with name '" + getServletName() + "'");
		}
		view.render(mv.getModelInternal(), request, response);
	}

	protected View resolveViewName(String viewName, Map<String, Object> model, HttpServletRequest request)
			throws Exception {
		for (Iterator<ViewResolver> it = this.viewResolvers.iterator(); it.hasNext();) {
			ViewResolver viewResolver = it.next();
			View view = viewResolver.resolveViewName(viewName);
			if (view != null) {
				return view;
			}
		}
		return null;
	}

	protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
		Iterator<HandlerAdapter> it = this.handlerAdapters.iterator();
		while (it.hasNext()) {
			HandlerAdapter ha = it.next();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Testing handler adapter [" + ha + "]");
			}
			if (ha.supports(handler)) {
				return ha;
			}
		}
		throw new ServletException("No adapter for handler [" + handler
				+ "]: Does your handler implement a supported interface like Controller?");
	}

	protected Object getHandler(HttpServletRequest request) throws Exception {
		Iterator<HandlerMapping> it = this.handlerMappings.iterator();
		while (it.hasNext()) {
			HandlerMapping hm = it.next();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Testing handler map [" + hm + "] in DispatcherServlet with name '" + getServletName()
						+ "'");
			}
			return hm.getHandler(request);
		}
		return null;
	}
	
	private String getDefaultViewName(HttpServletRequest request) {
		String url = request.getServletPath();
		url = url.replaceAll("/", "");
		url = url.replaceAll(".html", "");
		url = url.replaceAll(".htm", "");

		url = "WEB-INF/" + url + ".jsp";

		return url;
	}

}
