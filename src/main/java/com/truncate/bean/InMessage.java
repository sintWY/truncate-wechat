package com.truncate.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 描述: 推送消息基础类
 * 版权: Copyright (c) 2016
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2016年10月22日
 * 创建时间: 14:03
 */
@XStreamAlias("xml")
public class InMessage extends BaseMessage
{

	//图片媒体编号
	@XStreamAlias("MediaId")
	private String mediaId;

	//格式
	@XStreamAlias("Formart")
	private String formart;

	//视频缩略图编号
	@XStreamAlias("ThumbMediaId")
	private String thumbMediaId;

	//位置消息x坐标
	@XStreamAlias("Location_X")
	private String xLocation;

	//位置消息y坐标
	@XStreamAlias("Location_Y")
	private String yLocation;

	//地图缩放大小
	@XStreamAlias("Scale")
	private String scale;

	//地理位置信息
	@XStreamAlias("Label")
	private String label;

	public String getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(String mediaId)
	{
		this.mediaId = mediaId;
	}

	public String getFormart()
	{
		return formart;
	}

	public void setFormart(String formart)
	{
		this.formart = formart;
	}

	public String getThumbMediaId()
	{
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId)
	{
		this.thumbMediaId = thumbMediaId;
	}

	public String getxLocation()
	{
		return xLocation;
	}

	public void setxLocation(String xLocation)
	{
		this.xLocation = xLocation;
	}

	public String getyLocation()
	{
		return yLocation;
	}

	public void setyLocation(String yLocation)
	{
		this.yLocation = yLocation;
	}

	public String getScale()
	{
		return scale;
	}

	public void setScale(String scale)
	{
		this.scale = scale;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}
}
