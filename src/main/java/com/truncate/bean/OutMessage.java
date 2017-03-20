package com.truncate.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.truncate.constant.WechatConstant;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2016
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2016年10月28日
 * 创建时间: 11:08
 */
@XStreamAlias("xml")
public class OutMessage extends BaseMessage
{

	public OutMessage()
	{
		this(WechatConstant.MessageType.TEXT_MESSAGE);
	}

	public OutMessage(String messageType)
	{
		this.setMessageType(messageType);
	}
}
