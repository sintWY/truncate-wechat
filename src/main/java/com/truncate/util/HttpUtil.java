package com.truncate.util;

import com.truncate.constant.WechatConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil
{

	private final static Logger logger = Logger.getLogger(HttpUtil.class);

	//http client对象
	private static CloseableHttpClient httpClient = null;

	//timeout默认时间
	private static final int DEFAULT_TIMEOUT_TIME = 5000;

	static
	{
		try
		{
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { new X509TrustManager()
			{

				public X509Certificate[] getAcceptedIssuers()
				{
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType)
				{
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType)
				{
				}
			} }, null);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", new SSLConnectionSocketFactory(sslContext)).build();
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			httpClient = HttpClients.custom().setConnectionManager(connManager).build();
			// Create socket configuration
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
			connManager.setDefaultSocketConfig(socketConfig);
			// Create message constraints
			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
			// Create connection configuration
			ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE)
					.setCharset(Charset.forName(WechatConstant.Encoding.UTF_8)).
							setMessageConstraints(messageConstraints).build();
			connManager.setDefaultConnectionConfig(connectionConfig);
			connManager.setMaxTotal(200);
			connManager.setDefaultMaxPerRoute(20);
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
	}

	/**
	 *@描述：是否是http连接
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/2/22
	 *@时间:15:15
	 */
	public static boolean isHttpUrl(String url)
	{
		return StringUtils.isNotEmpty(url) && (url.startsWith(WechatConstant.WebProtol.HTTP_PROTOL));
	}

	/**
	 *@描述：是否是https连接
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/2/22
	 *@时间:15:16
	 */
	public static boolean isHttpsUrl(String url)
	{
		return StringUtils.isNotEmpty(url) && url.startsWith(WechatConstant.WebProtol.HTTPS_PROTOL);
	}

	/**
	 *@描述：发送post请求
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/2/22
	 *@时间:10:23
	 */
	public static String doGet(String url, Map<String, String> headers, Map<String, String> querys)
	{
		if(!isHttpUrl(url) && !isHttpsUrl(url))
		{
			throw new RuntimeException(String.format("无效的连接地址： %s", url));
		}
		String result = "";
		HttpGet request = new HttpGet(buildUrl(url, querys));
		if(headers != null && !headers.isEmpty())
		{
			for(Map.Entry<String, String> e : headers.entrySet())
			{
				request.addHeader(e.getKey(), e.getValue());
			}
		}
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try
		{
			response = httpClient.execute(request);
			entity = response.getEntity();
			result = IOUtil.getTextContent(entity.getContent(), WechatConstant.Encoding.UTF_8);

			if(logger.isDebugEnabled())
			{
				logger.debug("@@@@@@ request result：" + result);
			}
		}
		catch(IOException e)
		{
			logger.error("请求异常！", e);
		}
		finally
		{
			releaseResource(request, null, response, entity);
		}
		return result;
	}

	/**
	 *@描述：发送post请求
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/2/22
	 *@时间:10:44
	 * @param url 请求地址
	 * @param header 请求头
	 * @param querys 参数
	 * @param body 请求体
	 */
	public static String doPost(String url, Map<String, String> headers, Map<String, String> querys, Object body)
	{
		if(!isHttpUrl(url) && !isHttpsUrl(url))
		{
			throw new RuntimeException(String.format("无效的连接地址： %s", url));
		}
		String result = "";
		HttpPost request = new HttpPost(buildUrl(url, querys));
		if(headers != null && !headers.isEmpty())
		{
			for(Map.Entry<String, String> e : headers.entrySet())
			{
				request.addHeader(e.getKey(), e.getValue());
			}
		}
		if(body != null)
		{
			if(body instanceof String)
			{
				request.setEntity(new StringEntity((String) body, WechatConstant.Encoding.UTF_8));
			}
			else if(body instanceof byte[])
			{
				request.setEntity(new ByteArrayEntity((byte[]) body));
			}
			else if(body instanceof Map)
			{
				Map bodyMap = (Map) body;
				List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
				for(Object key : bodyMap.keySet())
				{
					String innerKey = (String) key;
					String innerValue = (String) bodyMap.get(key);
					nameValuePairList.add(new BasicNameValuePair(innerKey, innerValue));
				}
				try
				{
					UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
					formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
					request.setEntity(formEntity);
				}
				catch(UnsupportedEncodingException e)
				{
					logger.error("", e);
				}
			}
			else if(body instanceof File)
			{
				MultipartEntityBuilder builder = MultipartEntityBuilder.create();
				builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
				builder.addBinaryBody("media", (File) body);
				request.setEntity(builder.build());
			}
		}
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		try
		{
			response = httpClient.execute(request);
			entity = response.getEntity();
			result = IOUtil.getTextContent(entity.getContent(), WechatConstant.Encoding.UTF_8);
			if(logger.isDebugEnabled())
			{
				logger.debug("@@@@@@ request result：" + result);
			}
		}
		catch(IOException e)
		{
			logger.error("请求异常！", e);
		}
		finally
		{
			releaseResource(null, request, response, entity);
		}
		return result;
	}

	private static String buildUrl(String url, Map<String, String> querys)
	{
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(url);
		if(null != querys)
		{
			StringBuilder sbQuery = new StringBuilder();
			for(Map.Entry<String, String> query : querys.entrySet())
			{
				if(0 < sbQuery.length())
				{
					sbQuery.append("&");
				}
				if(StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue()))
				{
					sbQuery.append(query.getValue());
				}
				if(!StringUtils.isBlank(query.getKey()))
				{
					sbQuery.append(query.getKey());
					if(!StringUtils.isBlank(query.getValue()))
					{
						sbQuery.append("=");
						try
						{
							sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
						}
						catch(UnsupportedEncodingException e)
						{
							logger.error("", e);
						}
					}
				}
			}
			if(0 < sbQuery.length())
			{
				sbUrl.append("?").append(sbQuery);
			}
		}
		String converUrl = sbUrl.toString();
		if(logger.isDebugEnabled())
		{
			logger.debug("@@@@@@ request url：" + converUrl);
		}
		return converUrl;
	}

	/**
	 *@描述：释放资源
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/10/21
	 *@时间:20:58
	 */

	private static void releaseResource(HttpGet get, HttpPost post, CloseableHttpResponse response, HttpEntity entity)
	{
		try
		{
			if(entity != null)
			{
				if(!(entity instanceof MultipartEntityBuilder))
				{
					entity.getContent().close();
				}
			}
			if(response != null)
			{
				response.close();
			}
			if(post != null)
			{
				post.releaseConnection();
			}
			if(get != null)
			{
				get.releaseConnection();
			}
		}
		catch(Exception e)
		{
			logger.error("释放资源失败!", e);
		}
	}

}