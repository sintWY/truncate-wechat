package com.truncate.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2016
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2016年10月28日
 * 创建时间: 12:35
 */
@XStreamAlias("xml")
public class BaseMessage
{

	//收消息的人
	@XStreamAlias("ToUserName")
	private String toUserName;

	//发送消息的人
	@XStreamAlias("FromUserName")
	private String fromUserName;

	//创建时间
	@XStreamAlias("CreateTime")
	private long createTime;

	//消息类型
	@XStreamAlias("MsgType")
	private String messageType;

	//内容
	@XStreamAlias("Content")
	private String content;

	//消息编号
	@XStreamAlias("MsgId")
	private String msgId;

	public BaseMessage()
	{
		this.createTime = System.currentTimeMillis();
	}

	public String getToUserName()
	{
		return toUserName;
	}

	public void setToUserName(String toUserName)
	{
		this.toUserName = toUserName;
	}

	public String getFromUserName()
	{
		return fromUserName;
	}

	public void setFromUserName(String fromUserName)
	{
		this.fromUserName = fromUserName;
	}

	public long getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(long createTime)
	{
		this.createTime = createTime;
	}

	public String getMessageType()
	{
		return messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}
}
