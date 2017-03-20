package com.truncate.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 通用结果集
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月09日
 * 创建时间: 15:45
 */
public class ResultVo
{

	private int errorNo;

	private String errorMsg;

	private Map<String, List<Map<String, String>>> resultMap;

	private static final String DEFAULT_RESULT_NAME = "default_result_list";

	private static final String DEFAULT_PAGE_INFO_NAME = "split_page_info";

	public ResultVo()
	{
		this.errorNo = 0;
		this.errorMsg = "";
		this.resultMap = new HashMap<String, List<Map<String, String>>>();
	}

	/**
	 *@描述：打包默认结果集
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/3/9
	 *@时间:15:55
	 */
	public void setResult(Object object)
	{
		setResult(DEFAULT_RESULT_NAME, object);
	}

	/**
	 *@描述：打包指定名称的结果集
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/3/9
	 *@时间:15:55
	 */
	public void setResult(String resultName, Object object)
	{
		if(object == null)
		{
			throw new IllegalArgumentException("打包的对象不能为空！");
		}
		if(object instanceof Map)
		{
			List<Map<String, String>> tempList = new ArrayList<Map<String, String>>();
			tempList.add((Map) object);
			resultMap.put(resultName, tempList);
		}
		else if(object instanceof List)
		{
			resultMap.put(resultName, (List) object);
		}
		else if(object instanceof PageBean)
		{
			PageBean pageBean = (PageBean) object;
			resultMap.put(resultName, pageBean.getDataList());

			Map<String, String> pageMap = new HashMap<String, String>();
			pageMap.put("total_pages", String.valueOf(pageBean.getTotalPages()));
			pageMap.put("total_rows", String.valueOf(pageBean.getTotalRows()));
			pageMap.put("current_page", String.valueOf(pageBean.getCurrentPage()));
			pageMap.put("num_per_page", String.valueOf(pageBean.getNumPerPage()));
			this.setResult(DEFAULT_PAGE_INFO_NAME, pageMap);
		}
		else
		{
			throw new IllegalArgumentException("不支持的打包类型！");
		}
	}

	public Map<String, String> getMap(String name)
	{
		List<Map<String, String>> tempList = resultMap.get(name);
		if(tempList != null && !tempList.isEmpty())
		{
			return tempList.get(0);
		}
		return null;
	}

	public List<Map<String, String>> getList(String name)
	{
		List<Map<String, String>> tempList = resultMap.get(name);
		return tempList;
	}

	public Map<String, String> getMap()
	{
		return getMap(DEFAULT_RESULT_NAME);
	}

	public List<Map<String, String>> getList()
	{
		return getList(DEFAULT_RESULT_NAME);
	}

	public int getErrorNo()
	{
		return errorNo;
	}

	public void setErrorNo(int errorNo)
	{
		this.errorNo = errorNo;
	}

	public String getErrorMsg()
	{
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
}
