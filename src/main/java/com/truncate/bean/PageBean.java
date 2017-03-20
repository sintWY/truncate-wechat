package com.truncate.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 分页对象
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年03月13日
 * 创建时间: 14:24
 */
public class PageBean
{

	//当前页码
	private int currentPage;

	//每页的数量
	private int numPerPage;

	//总页数
	private int totalPages;

	//总条数
	private int totalRows;

	private List dataList;

	public PageBean(int currentPage, int numPerPage, int totalRows)
	{
		this.currentPage = currentPage;
		this.numPerPage = numPerPage;
		this.totalRows = totalRows;
		this.totalPages = (totalRows % numPerPage) == 0 ? totalRows / numPerPage : totalRows / numPerPage + 1;
		this.dataList = new ArrayList();
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

	public int getNumPerPage()
	{
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage)
	{
		this.numPerPage = numPerPage;
	}

	public int getTotalPages()
	{
		return totalPages;
	}

	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	public int getTotalRows()
	{
		return totalRows;
	}

	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

	public List getDataList()
	{
		return dataList;
	}

	public void setDataList(List dataList)
	{
		this.dataList = dataList;
	}
}
