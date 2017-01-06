package com.truncate.bean.inner;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 20:35
 */
@XStreamAlias("item")
public class Article
{

	@XStreamAlias("Title")
	private String title;

	@XStreamAlias("Description")
	private String description;

	@XStreamAlias("PicUrl")
	private String picUrl;

	@XStreamAlias("Url")
	private String url;

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
