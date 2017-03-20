package com.truncate.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 微信所有的请求工具类
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
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
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("grant_type", grantType);
		paraMap.put("appid", appid);
		paraMap.put("secret", secret);
		String result = HttpUtil.doGet(URLConstant.GET_ACCESS_TOKEN, null, paraMap);
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
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("access_token", accessToken);
		paraMap.put("type", type);
		String result = HttpUtil.doPost(URLConstant.UPLOAD_TEMP_MEDIA, null, paraMap, new File(filePath));
		return JsonUtil.toMap(result);
	}

	/**
	 *@描述：获取openid列表
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/6
	 *@时间:10:20
	 */
	public static Map<String, Object> getUserOpenidList(String accessToken, String nextOpenid)
	{
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("access_token", accessToken);
		paraMap.put("next_openid", nextOpenid);
		String result = HttpUtil.doGet(URLConstant.GET_USER_OPENID_LIST, null, paraMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> temp = JsonUtil.toMap(result);
		resultMap.put("total", temp.get("total"));
		resultMap.put("count", temp.get("count"));
		resultMap.put("next_openid", temp.get("next_openid"));
		JSONArray openids = (JSONArray) JSON.parse(JsonUtil.toMap(temp.get("data")).get("openid"));
		resultMap.put("openids", openids);
		return resultMap;
	}

	/**
	 *@描述：获取用户信息
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/6
	 *@时间:10:22
	 */
	public static Map<String, String> getUserInfo(String accessToken, String openid, String lang)
	{
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("access_token", accessToken);
		paraMap.put("openid", openid);
		paraMap.put("lang", lang);
		String result = HttpUtil.doGet(URLConstant.GET_USER_INFO, null, paraMap);
		return JsonUtil.toMap(result);
	}

	/**
	 *@描述：创建菜单
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/12
	 *@时间:11:23
	 */
	public static Map<String, String> createWechatMenu(String accessToken, String json)
	{
		return null;
	}

	public static void main(String[] args)
	{
		HttpUtil.doGet("www.baidu.com", null, null);
	}
}

class URLConstant
{

	//获取AccessToken
	static final String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

	//上传临时素材
	static final String UPLOAD_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";

	//获取用户信息
	static final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";

	//获取用户列表
	static final String GET_USER_OPENID_LIST = "https://api.weixin.qq.com/cgi-bin/user/get";

	//删除自定义菜单
	static final String DELETE_WECHAT_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete";

	//创建自定义菜单
	static final String CREATE_WECHAT_MENU = " https://api.weixin.qq.com/cgi-bin/menu/create";
}
