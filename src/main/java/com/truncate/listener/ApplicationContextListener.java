package com.truncate.listener;

import com.truncate.Application;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 描述:	监听器
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 15:19
 */
public class ApplicationContextListener implements ServletContextListener
{

	private static final Logger logger = Logger.getLogger(ApplicationContextListener.class);

	private static ServletContext context;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		context = servletContextEvent.getServletContext();
		init();
	}

	private void init()
	{
		logger.info("Application is starting...");
		logger.info("Application contextPath：" + context.getRealPath("/"));
		//初始化应用上下文和根路径
		Application.setRootPath(context.getRealPath("/"));
		Application.setServletContext(context);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		logger.info("Application is stopping...");
	}
}
