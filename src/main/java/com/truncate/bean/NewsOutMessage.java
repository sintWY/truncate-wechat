package com.truncate.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.truncate.bean.inner.Article;
import com.truncate.constant.WechatConstant;
import com.truncate.util.XmlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 20:08
 */
@XStreamAlias("xml")
public class NewsOutMessage extends OutMessage
{

	@XStreamAlias("ArticleCount")
	private int articleCount;

	@XStreamAlias("Articles")
	private List<Article> articles;

	public NewsOutMessage()
	{
		super(WechatConstant.MessageType.NEWS_MESSAGE);
	}

	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
		this.articleCount = articles.size();
	}

}

