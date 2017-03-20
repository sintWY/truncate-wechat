package com.truncate.bean;

import com.truncate.util.WechatRequestUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 描述: 微信公众账号
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0
 * 创建日期: 2017年01月03日
 * 创建时间: 12:50
 */
public class WechatPublicAccount
{

	//appid
	private String appid;

	//密钥
	private String secret;

	//token
	private String accessToken;

	//token最后更新时间
	private long updateTime;

	//过期时间
	private int expiresTime;

	public WechatPublicAccount(String appid, String secret)
	{
		this.appid = appid;
		this.secret = secret;
	}

	public String getAppid()
	{
		return appid;
	}

	public String getSecret()
	{
		return secret;
	}

	public String getAccessToken()
	{
		if(isNeedUpdateToken())
		{
			updateToken();
		}
		return accessToken;
	}

	/**
	 *@描述：是否需要更新token
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:16:02
	 */
	public boolean isNeedUpdateToken()
	{
		return StringUtils.isEmpty(accessToken) || (System.currentTimeMillis() - updateTime) / 1000 > (expiresTime - 10 * 60);
	}

	/**
	 *@描述：更新token
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/3
	 *@时间:16:02
	 */
	public void updateToken()
	{
		Map<String, String> resultMap = WechatRequestUtil.getAccessToken(appid, secret, "");
		this.accessToken = resultMap.get("access_token");
		this.expiresTime = Integer.valueOf(resultMap.get("expires_in"));
		this.updateTime = System.currentTimeMillis();
	}
}
