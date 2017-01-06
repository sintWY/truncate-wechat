package com.truncate.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述: Spring工具类
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 14:06
 */
public class SpringUtil
{

	private static ApplicationContext context;

	/**
	 *@描述：懒加载spring的上下文
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:14:11
	 */
	private static ApplicationContext getContext()
	{
		if(context == null)
		{
			synchronized(SpringUtil.class)
			{
				if(context == null)
				{
					context = new ClassPathXmlApplicationContext("spring-bean.xml");
				}
			}
		}
		return context;
	}

	/**
	 *@描述：获取指定的bean
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:14:12
	 */
	public static Object getBean(String id)
	{
		return getContext().getBean(id);
	}

	/**
	 *@描述：获取指定类的bean
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:14:15
	 */
	public static <T extends Object> T getBean(String id, Class<?> clazz)
	{
		return (T) getContext().getBean(id, clazz);
	}
}
