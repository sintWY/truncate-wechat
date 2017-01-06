package com.truncate.template;

import com.truncate.bean.*;
import com.truncate.bean.inner.Article;
import com.truncate.constant.WechatConstant;
import com.truncate.util.WechatUtil;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 描述: 消息模板
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 13:53
 */
public abstract class BaseMessageTemplate
{

	protected Logger logger = Logger.getLogger(getClass());

	//输入消息
	protected InMessage inMessage;

	public OutMessage absExecute(InMessage inMessage)
	{
		this.inMessage = inMessage;
		before();
		OutMessage outMessage = execute();
		after();
		return outMessage;
	}

	/**
	 *@描述：获取图片消息
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:17:54
	 */
	protected ImageOutMessage getImageOutMessage(String mediaId)
	{
		ImageOutMessage imageOutMessage = WechatUtil.in2Out(inMessage, ImageOutMessage.class);
		imageOutMessage.setMessageType(WechatConstant.MessageType.IMAGE_MESSAGE);
		imageOutMessage.setMediaId(mediaId);
		return imageOutMessage;
	}

	/**
	 *@描述：获取文本消息
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:18:00
	 */
	protected TextOutMessage getTextOutMessage(String content)
	{
		TextOutMessage textOutMessage = WechatUtil.in2Out(inMessage, TextOutMessage.class);
		textOutMessage.setMessageType(WechatConstant.MessageType.TEXT_MESSAGE);
		textOutMessage.setContent(content);
		return textOutMessage;
	}

	/**
	 *@描述：获取图文消息
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:20:40
	 */
	protected NewsOutMessage getNewsOutMessage(List<Article> articles)
	{
		NewsOutMessage newsOutMessage = WechatUtil.in2Out(inMessage, NewsOutMessage.class);
		newsOutMessage.setMessageType(WechatConstant.MessageType.NEWS_MESSAGE);
		newsOutMessage.setArticles(articles);
		return newsOutMessage;
	}

	protected abstract OutMessage execute();

	/**
	 *@描述：执行具体业务之前调用的犯法
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:14:31
	 */
	protected void before()
	{
	}

	protected void after()
	{
	}
}
