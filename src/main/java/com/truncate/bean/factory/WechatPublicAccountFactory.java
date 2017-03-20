package com.truncate.bean.factory;

import com.truncate.bean.WechatPublicAccount;
import com.truncate.constant.WechatConstant;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 公众账号工厂类
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月03日
 * 创建时间: 12:50
 */
public class WechatPublicAccountFactory
{

	private static final Logger logger = Logger.getLogger(WechatPublicAccountFactory.class);

	private static Map<String, WechatPublicAccount> accountMap = new HashMap<String, WechatPublicAccount>();

	private static String _default;

	static
	{
		loadConfig();
	}

	/**
	 *@描述：加载配置文件
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:15:12
	 */
	private static void loadConfig()
	{
		SAXReader reader = new SAXReader();
		FileInputStream fis = null;
		try
		{
			String realPath = WechatPublicAccountFactory.class.getResource("/").getPath() + WechatConstant.WECHAT_ACCOUNT_XML_NAME;
			logger.info("@@@@@@ realPath=" + realPath);
			fis = new FileInputStream(new File(realPath));
			Document document = reader.read(fis);
			Element root = document.getRootElement();
			_default = root.attributeValue("default");
			List accountElements = root.elements();
			if(accountElements != null && !accountElements.isEmpty())
			{
				for(Object object : accountElements)
				{
					Element accountElement = (Element) object;
					String id = accountElement.attributeValue("id");
					String appid = accountElement.elementText("appid");
					String secret = accountElement.elementText("secret");
					WechatPublicAccount account = new WechatPublicAccount(appid, secret);
					accountMap.put(id, account);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("加载配置文件失败！", e);
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
			catch(Exception e)
			{
				logger.error("", e);
			}
		}
	}

	/**
	 *@描述：获取指定的publicAccount
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:15:12
	 */
	public static WechatPublicAccount getWechatPublicAccount(String appId)
	{
		return accountMap.get(appId);
	}

	/**
	 *@描述：获取默认的publicAccount
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:15:12
	 */
	public static WechatPublicAccount getWechatPublicAccount()
	{
		return accountMap.get(_default);
	}
}

