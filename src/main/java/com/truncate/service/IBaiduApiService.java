package com.truncate.service;

import java.util.Map;

/**
 * 描述: 百度公用API接口
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 16:44
 */
public interface IBaiduApiService
{

	/**
	 *@描述：查询手机号码归属地
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:16:45
	 */
	Map<String, String> queryMobileBelongs(String mobileNo);

	/**
	 *@描述：查询城市天气预报
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:17:20
	 */
	Map<String, String> queryAreaWeather(String cityName);
}
