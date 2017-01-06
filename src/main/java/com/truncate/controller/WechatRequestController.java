package com.truncate.controller;

import com.truncate.bean.BaseMessage;
import com.truncate.bean.InMessage;
import com.truncate.bean.OutMessage;
import com.truncate.constant.WechatConstant;
import com.truncate.service.IMessageTemplateService;
import com.truncate.template.BaseMessageTemplate;
import com.truncate.util.IOUtil;
import com.truncate.util.SecurityUtil;
import com.truncate.util.SpringUtil;
import com.truncate.util.XmlUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 描述: 基础控制类
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2016年12月29日
 * 创建时间: 15:58
 */
@RequestMapping("/wechat")
@Controller
public class WechatRequestController
{

	private static final Logger logger = Logger.getLogger(WechatRequestController.class);

	@ResponseBody
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String checkToken(String signature, String timestamp, String nonce, String echostr)
	{
		if(logger.isDebugEnabled())
		{
			logger.debug("signature=" + signature + ",timestamp=" + timestamp + ",nonce=" + nonce + ",echostr=" + echostr);
		}
		String[] paramArr = new String[] { WechatConstant.WECHAT_TOKEN, timestamp, nonce };
		Arrays.sort(paramArr);
		String temp = SecurityUtil.sha1Encrypt(paramArr[0] + paramArr[1] + paramArr[2]);
		if(temp.equals(signature))
		{
			return echostr;
		}
		else
		{
			return "server is working!";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/request", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	public String receiveMessage(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String inputContent = IOUtil.getTextContent(request.getInputStream(), WechatConstant.Encoding.UTF_8);
			if(logger.isDebugEnabled())
			{
				logger.debug("input content：\n" + inputContent);
			}
			InMessage inMessage = XmlUtil.xml2Bean(inputContent, new Class[] { BaseMessage.class, InMessage.class });
			IMessageTemplateService templateService = SpringUtil.getBean("IMessageTemplateService", IMessageTemplateService.class);
			BaseMessageTemplate messageTemplate = templateService.findMatchTemplate(inMessage);
			OutMessage outMessage = messageTemplate.absExecute(inMessage);
			String outputContent = XmlUtil.bean2Xml(outMessage);
			return outputContent;
		}
		catch(IOException e)
		{
			logger.error("", e);
		}
		return "";
	}
}
