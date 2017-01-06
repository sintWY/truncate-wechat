package com.truncate.util;

import com.truncate.bean.InMessage;
import com.truncate.bean.OutMessage;
import com.truncate.bean.factory.WechatPublicAccountFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 描述: wechat工具类
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月03日
 * 创建时间: 21:39
 */
public class WechatUtil
{

	private static final Logger logger = Logger.getLogger(WechatUtil.class);

	/**
	 *@描述：输入消息转为输出消息
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:9:23
	 */
	public static <T extends OutMessage> T in2Out(InMessage inMessage, Class<? extends OutMessage> clazz)
	{
		try
		{
			Object object = clazz.newInstance();
			T t = (T) object;
			t.setToUserName(inMessage.getFromUserName());
			t.setFromUserName(inMessage.getToUserName());
			return t;
		}
		catch(Exception e)
		{
			logger.error("", e);
			return null;
		}
	}

	/**
	 *@描述：生成随机UUID
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:15:26
	 */
	public static String makeUUID()
	{
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 *@描述：计算MD5
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:15:37
	 */
	public static String makeMD5(String content)
	{
		if(StringUtils.isNotEmpty(content))
		{
			try
			{
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(content.getBytes());
				byte[] bytes = messageDigest.digest();
				char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] resultCharArray = new char[bytes.length * 2];
				int index = 0;
				for(byte b : bytes)
				{
					resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
					resultCharArray[index++] = hexDigits[b & 0xf];
				}
				return new String(resultCharArray);
			}
			catch(Exception e)
			{
				logger.error("", e);
			}
		}
		return "";
	}

	/**
	 *@描述：获取指定公众号的token
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:19:40
	 */
	public static String getAccessToken(String id)
	{
		return WechatPublicAccountFactory.getWechatPublicAccount(id).getAccessToken();
	}
}
