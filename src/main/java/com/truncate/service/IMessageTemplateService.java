package com.truncate.service;

import com.truncate.bean.InMessage;
import com.truncate.template.BaseMessageTemplate;

/**
 * 描述: 消息模板服务类
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 13:58
 */
public interface IMessageTemplateService
{

	/**
	 *@描述：根据输入的消息找到对应的模板
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/4
	 *@时间:14:05
	 */
	BaseMessageTemplate findMatchTemplate(InMessage inMessage);
}
