package com.truncate.bean.inner;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 20:35
 */
public class Image
{

	@XStreamAlias("MediaId")
	private String media_id;

	public Image(String media_id)
	{
		this.media_id = media_id;
	}
}
