package com.truncate.template;

import com.truncate.bean.OutMessage;
import com.truncate.bean.inner.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 20:37
 */
public class MessageTemplate0004 extends BaseMessageTemplate
{

	@Override
	protected OutMessage execute()
	{
		List<Article> articles = new ArrayList<Article>();
		Article one = new Article();
		one.setTitle("独家 | 习近平首次点评“95后”大学生");
		one.setDescription("去年12月7日，全国高校思想政治工作会议上习近平发表讲话，对高校教育工作作了指导。讲话还指明了高校思想政治工作关系高校培养什么样的人、如何培养人以及为谁培养人这个根本问题。");
		one.setPicUrl("http://himg2.huanqiu.com/attachment2010/2017/0102/20170102040951167.jpg");
		one.setUrl("http://china.huanqiu.com/article/2017-01/9894175.html?from=bdwz");

		Article two = new Article();
		two.setTitle("韩媒曝朴槿惠2013年清华大学演讲系崔顺实修改");
		two.setDescription("去年10月，韩国JTBC电视台曾报道崔顺实在总统演讲前修改过演讲稿。近日，韩媒“TV朝鲜”又爆料称，朴槿惠2013年在中国清华大学的演讲，也是经崔顺实修改过的。");
		two.setPicUrl("http://himg2.huanqiu.com/attachment2010/2017/0105/12/53/20170105125323567.png");
		two.setUrl("http://world.huanqiu.com/exclusive/2017-01/9908211.html?from=bdwz");

		articles.add(one);
		articles.add(two);
		return getNewsOutMessage(articles);
	}
}

