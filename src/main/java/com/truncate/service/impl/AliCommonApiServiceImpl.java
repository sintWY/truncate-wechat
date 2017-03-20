package com.truncate.service.impl;

import com.truncate.bean.PageBean;
import com.truncate.bean.ResultVo;
import com.truncate.service.ICommonApiService;
import com.truncate.util.HttpUtil;
import com.truncate.util.JsonUtil;
import com.truncate.util.SpringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 阿里云通用服务服务类
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年02月22日
 * 创建时间: 15:23
 */
public class AliCommonApiServiceImpl implements ICommonApiService
{

	private static final String APP_CODE = "APPCODE 46165fc10e6f4a368c468d5d1afa7932";

	private static final Map<String, String> headerParam = new HashMap<String, String>();

	static
	{
		headerParam.put("Authorization", APP_CODE);
	}

	@Override
	public ResultVo queryMobileBelongs(String mobileNo)
	{
		String url = "http://ali-mobile.showapi.com/6-1";
		Map<String, String> requestParam = new HashMap<String, String>();
		requestParam.put("num", mobileNo);
		String result = HttpUtil.doGet(url, headerParam, requestParam);
		Map<String, String> resultMap = JsonUtil.toMap(result);
		ResultVo resultVo = new ResultVo();
		resultVo.setErrorNo(Integer.valueOf(resultMap.get("showapi_res_code")));
		resultVo.setErrorMsg(resultMap.get("showapi_res_error"));
		resultVo.setResult(JsonUtil.toMap(resultMap.get("showapi_res_body")));
		return resultVo;
	}

	@Override
	public ResultVo queryAreaWeather(String cityName)
	{
		return null;
	}

	@Override
	public ResultVo analysisVideoAddress(String videoUrl)
	{
		return null;
	}

	@Override
	public ResultVo queryTechnologyNews(int currentPage, int pageSize, String keyword)
	{
		return null;
	}

	@Override
	public ResultVo queryNetworkStore(String keyword, int page)
	{
		String url = "http://netdisk.market.alicloudapi.com/search";
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("page", String.valueOf(page));
		requestMap.put("q", keyword);
		String result = HttpUtil.doGet(url, headerParam, requestMap);
		Map<String, String> resultMap = JsonUtil.toMap(result);

		ResultVo resultVo = new ResultVo();
		resultVo.setErrorNo(Integer.valueOf(resultMap.get("showapi_res_code")));
		resultVo.setErrorMsg(resultMap.get("showapi_res_error"));

		String responseBody = resultMap.get("showapi_res_body");
		Map<String, String> pageInfo = JsonUtil.toMap(JsonUtil.toMap(responseBody).get("pagebean"));
		List dataList = JsonUtil.toList(pageInfo.get("contentlist"));
		int currentPage = Integer.valueOf(pageInfo.get("currentPage"));
		int totalRows = Integer.valueOf(pageInfo.get("allNum"));
		int numPerPage = Integer.valueOf(pageInfo.get("maxResult"));
		PageBean pageBean = new PageBean(currentPage, numPerPage, totalRows);
		pageBean.setDataList(dataList);
		resultVo.setResult(pageBean);
		return resultVo;
	}

	public static void main(String[] args)
	{
		ICommonApiService commonApiService = SpringUtil.getBean("ICommonApiService", ICommonApiService.class);
		for(int i = 0; i < 10; i++)
		{
			ResultVo resultVo = commonApiService.queryNetworkStore("PowerDesigner", i + 1);
			System.out.println(JsonUtil.toString(resultVo));
		}
	}
}
