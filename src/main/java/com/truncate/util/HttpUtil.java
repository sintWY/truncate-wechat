package com.truncate.util;

import com.alibaba.fastjson.JSON;
import com.truncate.constant.WechatConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

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
			SSLContext sslContext = SSLContexts.custom().useTLS().build();
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

	public static String sendGetRequest(String url, String encoding)
	{
		return sendGetRequest(url, DEFAULT_TIMEOUT_TIME, encoding, null);
	}

	public static String sendGetRequest(String url)
	{
		return sendGetRequest(url, WechatConstant.Encoding.UTF_8);
	}

	public static String sendGetRequest(String url, Map<String, String> headerMap)
	{
		return sendGetRequest(url, DEFAULT_TIMEOUT_TIME, WechatConstant.Encoding.UTF_8, headerMap);
	}

	/**
	 *@描述：发送get请求
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/10/21
	 *@时间:20:43
	 */
	public static String sendGetRequest(String url, int timeout, String encoding, Map<String, String> headerMap)
	{
		if(checkUrl(url))
		{
			return "";
		}
		HttpGet get = null;
		String responseString = "";
		HttpEntity entity = null;
		CloseableHttpResponse response = null;
		try
		{
			get = new HttpGet(url);
			if(headerMap != null && !headerMap.isEmpty())
			{
				Set<String> keySet = headerMap.keySet();
				for(String key : keySet)
				{
					get.addHeader(key, headerMap.get(key));
				}
			}
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).build();
			logger.info("@@@@@@ request url：" + url);
			get.setConfig(requestConfig);
			response = httpClient.execute(get);
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, encoding);
			logger.info("@@@@@@ request result：" + responseString);
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			releaseResource(get, null, response, entity);
		}
		return responseString;
	}

	public static String sendPostRequest(String url, Map<String, String> param, String encoding)
	{
		return sendPostRequest(url, param, DEFAULT_TIMEOUT_TIME, encoding);
	}

	public static String sendPostRequest(String url, Map<String, String> param)
	{
		return sendPostRequest(url, param, WechatConstant.Encoding.UTF_8);
	}

	/**
	 *@描述：发送post请求
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2016/10/21
	 *@时间:20:58
	 */
	public static String sendPostRequest(String url, Map<String, String> requestParam, int timeout, String encoding)
	{
		if(checkUrl(url))
		{
			return "";
		}
		HttpPost post = null;
		CloseableHttpResponse response = null;
		String responseString = "";
		HttpEntity entity = null;
		try
		{
			post = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setExpectContinueEnabled(false).build();
			post.setConfig(requestConfig);
			String requestParamStr = JSON.toJSONString(requestParam);
			logger.info("@@@@@@ request url：" + url + "，request param：" + requestParamStr);
			post.setEntity(new StringEntity(requestParamStr));
			response = httpClient.execute(post);
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, encoding);
			logger.info("@@@@@@ request result：" + responseString);
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			releaseResource(null, post, response, entity);
		}
		return responseString;
	}

	/**
	 *@描述：上传文件
	 *@作者:王功俊(wanggj@thinkive.com)
	 *@日期:2017/1/5
	 *@时间:19:28
	 */
	public static String uploadFile(String url, String filePath)
	{
		if(checkUrl(url))
		{
			return "";
		}
		HttpPost post = null;
		CloseableHttpResponse response = null;
		String responseString = "";
		HttpEntity entity = null;
		try
		{
			post = new HttpPost(url);
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
			entity = MultipartEntityBuilder.create().addPart("bin", new FileBody(new File(filePath))).addPart("comment", comment).build();
			post.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(DEFAULT_TIMEOUT_TIME).setConnectTimeout(DEFAULT_TIMEOUT_TIME).setConnectionRequestTimeout(DEFAULT_TIMEOUT_TIME)
					.setExpectContinueEnabled(false).build();
			post.setConfig(requestConfig);
			logger.info("@@@@@@ request line：" + post.getRequestLine());
			response = httpClient.execute(post);
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, WechatConstant.Encoding.UTF_8);
			logger.info("@@@@@@ request result：" + responseString);
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			releaseResource(null, post, response, entity);
		}
		return responseString;
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

	private static boolean checkUrl(String url)
	{
		return StringUtils.isEmpty(url) || (!url.startsWith(WechatConstant.WebProtol.HTTP_PROTOL) && !url.startsWith(WechatConstant.WebProtol.HTTPS_PROTOL));
	}

	public static void main(String[] args)
	{
		uploadFile(
				"http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=8ngv-U9jyltDYxMhIrYOrmDdM2X4AId5nbnDHX4O2BZTNj6g20C62JRgs1kHfzaaY4dRcOEMVFfScAeT5P4sldrPJGoAAz2ldqazyyccd_9HdwnxKwGlHBn3OI2L922FZMEeADALYQ&type=image",
				"C:\\Users\\lumia\\Desktop\\QQ截图20170105193421.jpg");
	}

}