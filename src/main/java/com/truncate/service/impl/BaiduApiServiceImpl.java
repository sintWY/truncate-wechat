package com.truncate.service.impl;

import com.truncate.service.IBaiduApiService;
import com.truncate.util.HttpUtil;
import com.truncate.util.JsonUtil;
import com.truncate.util.SpringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 百度公用API接口实现类
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 16:44
 */
public class BaiduApiServiceImpl implements IBaiduApiService
{

	//百度apikey
	private static final String BAIDU_APIKEY = "7214eecbe87d269ab2e3790fcf164ab6";

	//成功码
	private static final String SUCCESS_CODE = "0";

	//成功状态
	private static final String SUCCESS_STATUS = "ok";

	//请求头参数
	private static final Map<String, String> BAIDU_HEADER_PARAM = new HashMap<String, String>();

	static
	{
		BAIDU_HEADER_PARAM.put("apikey", BAIDU_APIKEY);
	}

	@Override
	public Map<String, String> queryMobileBelongs(String mobileNo)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		String url = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone?tel=" + mobileNo;
		String result = HttpUtil.sendGetRequest(url, BAIDU_HEADER_PARAM);
		if(StringUtils.isNotEmpty(result))
		{
			Map<String, String> tempMap = JsonUtil.toMap(result);
			if(tempMap != null && SUCCESS_CODE.equals(tempMap.get("errNum")))
			{
				tempMap = JsonUtil.toMap(tempMap.get("retData"));
				resultMap = tempMap != null && !tempMap.isEmpty() ? tempMap : resultMap;
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, String> queryAreaWeather(String cityName)
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		String url = "http://apis.baidu.com/heweather/weather/free?city=" + cityName;
		String result = HttpUtil.sendGetRequest(url, BAIDU_HEADER_PARAM);
		if(StringUtils.isNotEmpty(result))
		{
			Map<String, String> tempMap = JsonUtil.toMap(result);
			tempMap = JsonUtil.toMap(tempMap.get("HeWeather data service 3.0"));
			if(tempMap != null && SUCCESS_STATUS.equals(tempMap.get("status")))
			{
				Map<String, String> basicMap = JsonUtil.toMap(tempMap.get("basic"));
				List<Map<String, String>> dailyList = JsonUtil.toList(tempMap.get("daily_forecast"));
				resultMap.put("longitude", basicMap.get("lon"));
				resultMap.put("latitude", basicMap.get("lat"));
				resultMap.put("weather", JsonUtil.toMap(JsonUtil.toMap(tempMap.get("now")).get("cond")).get("txt"));
				resultMap.put("tmp", JsonUtil.toMap(tempMap.get("now")).get("tmp"));
				resultMap.put("h_tmp", JsonUtil.toMap(dailyList.get(0).get("tmp")).get("max"));
				resultMap.put("l_tmp", JsonUtil.toMap(dailyList.get(0).get("tmp")).get("min"));
				resultMap.put("WD", JsonUtil.toMap(dailyList.get(0).get("wind")).get("dir"));
				resultMap.put("WS", JsonUtil.toMap(dailyList.get(0).get("wind")).get("spd"));
				resultMap.put("sunrise", JsonUtil.toMap(dailyList.get(0).get("astro")).get("sr"));
				resultMap.put("sunset", JsonUtil.toMap(dailyList.get(0).get("astro")).get("ss"));
				resultMap.put("update_time", JsonUtil.toMap(basicMap.get("update")).get("loc"));
			}
		}
		return resultMap;
	}
}
