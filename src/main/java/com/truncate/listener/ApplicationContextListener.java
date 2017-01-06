package com.truncate.listener;

import com.truncate.Application;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 描述:	监听器
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 15:19
 */
public class ApplicationContextListener implements ServletContextListener
{

	private static final Logger logger = Logger.getLogger(ApplicationContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent)
	{
		logger.info("Application is starting...");
		ServletContext context = servletContextEvent.getServletContext();
		logger.info("Application contextPath：" + context.getRealPath("/"));
		Application.setRootPath(context.getRealPath("/"));
		Application.setServletContext(context);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent)
	{
		logger.info("Application is stopping...");
	}
}
