package com.truncate.template;

import com.truncate.bean.OutMessage;
import com.truncate.bean.ResultVo;
import com.truncate.service.ICommonApiService;
import com.truncate.util.SpringUtil;

import java.util.Map;

/**
 * 描述: 查询手机归属地
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
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
		ICommonApiService commonApiService = SpringUtil.getBean("ICommonApiService", ICommonApiService.class);
		ResultVo resultVo = commonApiService.queryMobileBelongs(mobileNo);
		Map<String, String> resultMap = resultVo.getMap();
		String content = "手机号码：" + mobileNo + "\n地区：" + resultMap.get("prov") + resultMap.get("city") + "\n运营商：" + resultMap.get("name");
		return getTextOutMessage(content);
	}
}
