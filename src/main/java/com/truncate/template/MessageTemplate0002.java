package com.truncate.template;

import com.truncate.bean.OutMessage;
import com.truncate.service.IBaiduApiService;
import com.truncate.util.SpringUtil;

import java.util.Map;

/**
 * 描述: 查询城市天气预报
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月04日
 * 创建时间: 16:43
 */
public class MessageTemplate0002 extends BaseMessageTemplate
{

	@Override
	protected OutMessage execute()
	{
		String cityName = inMessage.getContent();
		IBaiduApiService baiduApiService = SpringUtil.getBean("IBaiduApiService", IBaiduApiService.class);
		Map<String, String> resultMap = baiduApiService.queryAreaWeather(cityName);
		String content =
				"城市：" + cityName + "\n经度：" + resultMap.get("longitude") + "\n纬度：" + resultMap.get("latitude") + "\n天气：" + resultMap.get("weather") + "\n平均气温：" + resultMap.get("tmp") + "\n最高气温："
						+ resultMap.get("h_tmp") + "\n最低气温：" + resultMap.get("l_tmp") + "\n风向：" + resultMap.get("WD") + "\n风力：" + resultMap.get("WS") + "\n日出时间：" + resultMap.get("sunrise") + "\n日落时间："
						+ resultMap.get("sunset") + "\n更新时间：" + resultMap.get("update_time");
		return getTextOutMessage(content);
	}
}
