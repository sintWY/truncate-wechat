package com.truncate.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.truncate.bean.inner.Image;
import com.truncate.constant.WechatConstant;

/**
 * 描述:
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 17:14
 */
@XStreamAlias("xml")
public class ImageOutMessage extends OutMessage
{

	@XStreamAlias("Image")
	private Image image;

	public ImageOutMessage()
	{
		super(WechatConstant.MessageType.IMAGE_MESSAGE);
	}

	public void setMediaId(String mediaId)
	{
		this.image = new Image(mediaId);
	}
}


