package com.truncate.util;

import com.thoughtworks.xstream.XStream;
import com.truncate.bean.BaseMessage;
import com.truncate.bean.InMessage;
import org.apache.log4j.Logger;

/**
 * 描述: xml工具类
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2016年10月22日
 * 创建时间: 14:11
 */
public class XmlUtil
{

	public static final Logger logger = Logger.getLogger(XmlUtil.class);

	/**
	 *@描述：xml转换成对象
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/10/28
	 *@时间:10:53
	 */
	public static <T extends Object> T xml2Bean(String xml, Class[] clazzs)
	{
		XStream xstream = new XStream();
		xstream.processAnnotations(clazzs);
		return (T) xstream.fromXML(xml);
	}

	/**
	 *@描述：对象转换成xml
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/10/28
	 *@时间:12:38
	 */
	public static String bean2Xml(Object object)
	{
		if(object == null)
		{
			return "";
		}
		else
		{
			Class clazz = object.getClass();
			XStream xstream = new XStream();
			xstream.processAnnotations(clazz);
			return xstream.toXML(object);
		}
	}
}
