package com.truncate.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 描述: Json工具类
 * 版权: Copyright (c) 2016
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2016年12月29日
 * 创建时间: 15:38
 */
public class JsonUtil
{

	/**
	 *@描述：将对象转为json字符输出
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/12/29
	 *@时间:15:43
	 */
	public static String toString(Object object)
	{
		return JSON.toJSONString(object);
	}

	/**
	 *@描述：将json字符转为map
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/12/29
	 *@时间:15:43
	 */
	public static Map<String, String> toMap(String json)
	{
		List<Map<String, String>> list = toList(json);
		Map<String, String> resultMap = new HashMap<String, String>();
		if(list != null && !list.isEmpty())
		{
			resultMap = list.get(0);
		}
		return resultMap;
	}

	/**
	 *@描述：将json字符转为list
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:15:48
	 */
	public static List<Map<String, String>> toList(String json)
	{
		Object object = JSON.parse(json);
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		if(object != null)
		{
			JSONArray jsonArray = null;
			if(object instanceof JSONObject)
			{
				jsonArray = new JSONArray();
				jsonArray.add(object);
			}
			else if(object instanceof JSONArray)
			{
				jsonArray = (JSONArray) object;
			}
			if(jsonArray != null && !jsonArray.isEmpty())
			{
				for(Object obj : jsonArray)
				{
					JSONObject jsonObject = (JSONObject) obj;
					if(jsonObject != null)
					{
						Map<String, String> resultMap = new HashMap<String, String>();
						Set<String> keySet = jsonObject.keySet();
						for(String key : keySet)
						{
							resultMap.put(key, jsonObject.getString(key));
						}
						resultList.add(resultMap);
					}
				}
			}
		}
		return resultList;
	}

	public static void main(String[] args)
	{
		System.out.println(toList("[{'a':'1'},{'b':'2'}]"));
	}
}
