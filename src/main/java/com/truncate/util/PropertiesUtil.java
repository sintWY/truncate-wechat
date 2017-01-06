package com.truncate.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 描述: properties配置文件读取工具类
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0
 * 创建日期: 2017年01月03日
 * 创建时间: 12:43
 */
public class PropertiesUtil
{

	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	public static Properties loadProperties(String fileName)
	{
		if(!StringUtils.isEmpty(fileName))
		{
			String realPath = PropertiesUtil.class.getResource("/").getPath() + fileName;
			logger.info("real path：" + realPath);
			Properties properties = new Properties();
			FileInputStream fis = null;
			try
			{
				fis = new FileInputStream(new File(realPath));
				properties.load(fis);
				return properties;
			}
			catch(IOException e)
			{
				logger.error("读取配置文件[" + realPath + "]失败！", e);
			}
			finally
			{
				try
				{
					if(fis != null)
					{
						fis.close();
					}
				}
				catch(IOException e)
				{
					logger.error("", e);
				}
			}
		}
		return null;
	}
}
