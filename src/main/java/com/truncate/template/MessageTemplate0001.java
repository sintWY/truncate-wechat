package com.truncate.template;

import com.truncate.bean.OutMessage;
import com.truncate.service.IBaiduApiService;
import com.truncate.util.SpringUtil;

import java.util.Map;

/**
 * 描述: 查询手机归属地
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 15:52
 */
public class MessageTemplate0001 extends BaseMessageTemplate
{

	@Override
	protected OutMessage execute()
	{
		String mobileNo = inMessage.getContent();
		IBaiduApiService baiduApiService = SpringUtil.getBean("IBaiduApiService", IBaiduApiService.class);
		Map<String, String> resultMap = baiduApiService.queryMobileBelongs(mobileNo);
		String province = resultMap.get("province");
		String provider = resultMap.get("carrier");
		String content = "手机号码：" + mobileNo + "\n所属省份：" + province + "\n运营商：" + provider;
		return getTextOutMessage(content);
	}

}
