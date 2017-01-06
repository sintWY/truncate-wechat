package com.truncate.util;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 描述: 微信所有的请求工具类
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月03日
 * 创建时间: 15:16
 */
public class WechatRequestUtil
{

	/**
	 *@描述：获取accessToken
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:15:18
	 * @param appid app编号
	 * @param secret 密钥
	 * @param grantType 类型
	 */
	public static Map<String, String> getAccessToken(String appid, String secret, String grantType)
	{
		grantType = StringUtils.isEmpty(grantType) ? "client_credential" : grantType;
		String result = HttpUtil.sendGetRequest(URLConstant.GET_ACCESS_TOKEN + "?grant_type=" + grantType + "&appid=" + appid + "&secret=" + secret);
		return JsonUtil.toMap(result);
	}

	/**
	 *@描述：上传临时素材
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:18:48
	 */
	public static Map<String, String> uploadTempMedia(String accessToken, String type, String filePath)
	{
		String result = HttpUtil.uploadFile(URLConstant.UPLOAD_TEMP_MEDIA + "?access_token=" + accessToken + "&type=" + type, filePath);
		return JsonUtil.toMap(result);
	}
}

class URLConstant
{

	static final String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

	static final String UPLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";
}
