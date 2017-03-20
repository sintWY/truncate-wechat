package com.truncate.util;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0
 * 创建日期: 2017年01月03日
 * 创建时间: 16:45
 */
public class IOUtil
{

	private static final Logger logger = Logger.getLogger(IOUtil.class);

	/**
	 *@描述：将请求转为字符串
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:16:50
	 */
	public static String getTextContent(InputStream is, String charset)
	{
		StringBuilder content = new StringBuilder();
		if(is != null)
		{
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new InputStreamReader(is, charset));
				char[] cache = new char[4 * 1024];
				int temp;
				while((temp = reader.read(cache)) != -1)
				{
					content.append(cache, 0, temp);
				}

			}
			catch(Exception e)
			{
				logger.error("", e);
			}
			finally
			{
				try
				{
					if(reader != null)
					{
						reader.close();
					}
				}
				catch(Exception e)
				{
					logger.error("", e);
				}
			}
		}
		return content.toString();
	}

}
