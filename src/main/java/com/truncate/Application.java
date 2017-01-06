package com.truncate;

import javax.servlet.ServletContext;

/**
 * 描述: 应用基础路径
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 15:15
 */
public class Application
{

	//应用耿路径
	private static String rootPath;

	//servlet上下文
	private static ServletContext servletContext;

	public static String getRootPath()
	{
		return rootPath;
	}

	public static void setRootPath(String rootPath)
	{
		Application.rootPath = rootPath;
	}

	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext)
	{
		Application.servletContext = servletContext;
	}
}
