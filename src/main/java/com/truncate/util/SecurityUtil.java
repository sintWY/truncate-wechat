package com.truncate.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月03日
 * 创建时间: 10:25
 */
public class SecurityUtil
{

	private static final Logger logger = Logger.getLogger(SecurityUtil.class);

	public static String sha1Encrypt(String data)
	{
		if(!StringUtils.isEmpty(data))
		{
			try
			{
				MessageDigest digest = MessageDigest.getInstance("SHA1");
				digest.update(data.getBytes());
				byte[] bytes = digest.digest();
				if(bytes != null && bytes.length > 0)
				{
					StringBuilder sb = new StringBuilder();
					for(byte aByte : bytes)
					{
						int a = aByte;
						if(a < 0)
						{
							a += 256;
						}
						if(a < 16)
						{
							sb.append("0");
						}
						sb.append(Integer.toHexString(a));
					}
					return sb.toString();
				}
			}
			catch(NoSuchAlgorithmException e)
			{
				logger.error("", e);
			}
		}
		return "";
	}
}
