package com.truncate.template;

import com.truncate.bean.OutMessage;
import com.truncate.constant.WechatConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述: //TODO 类描述
 * 版权: Copyright (c) 2017
 * 公司: 思迪科技 
 * 作者: 王功俊(wanggj@thinkive.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 21:08
 */
public class MessageTemplate0005 extends BaseMessageTemplate
{

	public static final Logger logger = Logger.getLogger(MessageTemplate0005.class);

	private static Map<String, String> questionMap = new HashMap<String, String>();

	@Override
	protected OutMessage execute()
	{
		Map<String, String> resultMap = new HashMap<String, String>();
		String input = inMessage.getContent().trim();
		Set<String> keySet = questionMap.keySet();
		for(String key : keySet)
		{
			if(key.contains(input))
			{
				resultMap.put(key, questionMap.get(key));
			}
		}

		keySet = resultMap.keySet();
		StringBuilder content = new StringBuilder("匹配到的题目：\n\n");
		int index = 0;
		for(String key : keySet)
		{
			content.append("\n");
			content.append("-------------").append(++index).append("------------\n");
			content.append("Q：");
			content.append(key);
			content.append("\n");
			content.append("A：");
			content.append(resultMap.get(key));
			content.append("\n");
		}
		return getTextOutMessage(content.toString());
	}

	static
	{
		loadQuestionLib();
	}

	private static void loadQuestionLib()
	{
		String libPath = MessageTemplate0005.class.getResource("/").getPath() + "keju.txt";
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(libPath)), WechatConstant.Encoding.UTF_8));
			String temp;
			while(StringUtils.isNotEmpty((temp = reader.readLine())))
			{
				String[] arr = temp.split("\\|");
				String question = arr[0];
				String answer = arr[1];
				questionMap.put(question, answer);
			}
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			if(reader != null)
			{
				try
				{
					reader.close();
				}
				catch(Exception e)
				{
					logger.error("", e);
				}
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.println(questionMap);
	}
}
