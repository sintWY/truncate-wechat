package com.truncate.service.impl;

import com.truncate.bean.InMessage;
import com.truncate.config.PatternProperties;
import com.truncate.constant.WechatConstant;
import com.truncate.service.IMessageTemplateService;
import com.truncate.template.BaseMessageTemplate;
import com.truncate.template.DefaultMessageTemplate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 描述: 消息模板接口实现类
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 14:05
 */
public class MessageTemplateServiceImpl implements IMessageTemplateService
{

	public static final Logger logger = Logger.getLogger(MessageTemplateServiceImpl.class);

	@Override
	public BaseMessageTemplate findMatchTemplate(InMessage inMessage)
	{
		String messageType = inMessage.getMessageType();
		//文本消息
		if(WechatConstant.MessageType.TEXT_MESSAGE.equals(messageType))
		{
			String content = inMessage.getContent();
			String clazz = PatternProperties.getPatternClazz(content);
			if(StringUtils.isNotEmpty(clazz))
			{
				try
				{
					Object object = Class.forName(clazz).newInstance();
					if(object instanceof BaseMessageTemplate)
					{
						return (BaseMessageTemplate) object;
					}
				}
				catch(Exception e)
				{
					logger.error("", e);
				}
			}
		}
		//图片消息
		else if(WechatConstant.MessageType.IMAGE_MESSAGE.equals(messageType))
		{

		}
		//声音消息
		else if(WechatConstant.MessageType.VOICE_MESSAGE.equals(messageType))
		{

		}
		//视频消息
		else if(WechatConstant.MessageType.VIDEO_MESSAGE.equals(messageType))
		{

		}
		//音乐消息
		else if(WechatConstant.MessageType.MUSIC_MESSAGE.equals(messageType))
		{

		}
		//图文消息
		else if(WechatConstant.MessageType.NEWS_MESSAGE.equals(messageType))
		{

		}
		return new DefaultMessageTemplate();
	}

	public static void main(String[] args)
	{
	}
}
