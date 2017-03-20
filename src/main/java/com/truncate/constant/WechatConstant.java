package com.truncate.constant;

/**
 * 描述: 常量类
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月03日
 * 创建时间: 10:51
 */
public class WechatConstant
{

	//微信token
	public static final String WECHAT_TOKEN = "weixin";

	public static final String WECHAT_ACCOUNT_XML_NAME = "wechat.xml";

	//网络协议
	public static class WebProtol
	{

		//https协议
		public static final String HTTPS_PROTOL = "https://";

		//http协议
		public static final String HTTP_PROTOL = "http://";
	}

	//编码
	public static class Encoding
	{

		//GBK编码
		public static final String GBK = "GBK";

		//UTF-8编码
		public static final String UTF_8 = "UTF-8";
	}

	public static class MessageType
	{

		//文本消息
		public static final String TEXT_MESSAGE = "text";

		//图片消息
		public static final String IMAGE_MESSAGE = "image";

		//语音消息
		public static final String VOICE_MESSAGE = "voice";

		//视频消息
		public static final String VIDEO_MESSAGE = "video";

		//音乐消息
		public static final String MUSIC_MESSAGE = "music";

		//图文消息
		public static final String NEWS_MESSAGE = "news";
	}

}
