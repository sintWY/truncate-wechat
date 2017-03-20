package com.truncate.template;

import com.truncate.bean.OutMessage;
import org.apache.log4j.Logger;

/**
 * 描述: 默认消息模板
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 13:59
 */
public class DefaultMessageTemplate extends BaseMessageTemplate
{

	public static final Logger logger = Logger.getLogger(DefaultMessageTemplate.class);

	@Override
	public OutMessage execute()
	{
		return getTextOutMessage("暂不支持的消息类型");
	}
}
