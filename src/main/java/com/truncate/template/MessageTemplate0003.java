package com.truncate.template;

import com.truncate.Application;
import com.truncate.bean.OutMessage;
import com.truncate.util.QRCodeUtil;
import com.truncate.util.WechatRequestUtil;
import com.truncate.util.WechatUtil;

import java.io.File;
import java.util.Map;

/**
 * 描述: 返回链接的二维码
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: truncate(wy940407@163.com)
 * 版本: 1.0 
 * 创建日期: 2017年01月05日
 * 创建时间: 15:06
 */
public class MessageTemplate0003 extends BaseMessageTemplate
{

	private static final String QRCODE_MAKE_DIR = Application.getRootPath() + File.separator + "statics" + File.separator + "upload";

	@Override
	protected OutMessage execute()
	{
		String content = inMessage.getContent();
		String md5 = WechatUtil.makeMD5(content);
		String filePath = QRCODE_MAKE_DIR + File.separator + md5 + ".jpg";
		File file = new File(filePath);
		if(!file.exists())
		{
			QRCodeUtil.encode(content, filePath);
		}

		String accessToken = WechatUtil.getAccessToken("truncate");
		Map<String, String> resultMap = WechatRequestUtil.uploadTempMedia(accessToken, "image", filePath);
		String mediaId = resultMap.get("media_id");
		return getImageOutMessage(mediaId);

	}
}
