package com.truncate.service;

import com.truncate.bean.ResultVo;

/**
 * 描述: 百度公用API接口
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 16:44
 */
public interface ICommonApiService
{

	/**
	 *@描述：查询手机号码归属地
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:16:45
	 */
	ResultVo queryMobileBelongs(String mobileNo);

	/**
	 *@描述：查询城市天气预报
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:17:20
	 */
	ResultVo queryAreaWeather(String cityName);

	/**
	 *@描述：查询奇闻轶事
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/12
	 *@时间:10:37
	 */
	ResultVo analysisVideoAddress(String videoUrl);

	/**
	 *@描述：查询最新科技新闻
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/12
	 *@时间:11:01
	 */
	ResultVo queryTechnologyNews(int currentPage, int pageSize, String keyword);

	/**
	 *@描述：根据关键字查询网盘
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/3/9
	 *@时间:16:15
	 */
	ResultVo queryNetworkStore(String keyword, int page);
}
