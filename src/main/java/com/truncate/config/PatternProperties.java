package com.truncate.config;

import com.truncate.util.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 15:56
 */
public class PatternProperties
{

	private static Map<String, String> patternMap = new HashMap<String, String>();

	private static Set<String> patternSet;

	private static final Properties PATTERN_PROPERTIES;

	static
	{
		PATTERN_PROPERTIES = PropertiesUtil.loadProperties("pattern.properties");
		if(PATTERN_PROPERTIES != null)
		{
			Set<Object> keySet = PATTERN_PROPERTIES.keySet();
			for(Object objKey : keySet)
			{
				String key = objKey.toString();
				String value = PATTERN_PROPERTIES.getProperty(key);
				patternMap.put(key, value);
			}
			patternSet = patternMap.keySet();
		}
	}

	/**
	 *@描述：根据内容查询匹配的类
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:16:01
	 */
	public static String getPatternClazz(String content)
	{
		for(String key : patternSet)
		{
			Pattern pattern = Pattern.compile(key);
			Matcher matcher = pattern.matcher(content);
			if(matcher.find())
			{
				String clazz = patternMap.get(key);
				return clazz;
			}
		}
		return "";
	}
}
