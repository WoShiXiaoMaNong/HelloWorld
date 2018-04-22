package com.gongzong.custom.menu.tool.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;


public class HttpHelper {
	private static final Logger logger = Logger.getLogger(HttpHelper.class);
	public static final String HTTP_METHOD = "GET";

	
	public static JSONObject httpRequestGet(String requestUrl) {
		return httpRequest( requestUrl ,HTTP_METHOD ,null);
	}
	
	
	private static JSONObject httpRequest(String requestUrl, String requestMethod, String dataForOutput) {  
        JSONObject jsonObject = null;  
        HttpsURLConnection httpUrlConn = null ;
        try {  
        	httpUrlConn = getHttpUrlConn(requestUrl, requestMethod);
        	sendIfDataIsNotNull(httpUrlConn,dataForOutput);
        	jsonObject = getResult(httpUrlConn);
        } catch (ConnectException ce) {  
        	logger.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
        	logger.error("https request error:{}", e);  
        }finally {
        	if(httpUrlConn != null) {
       			 httpUrlConn.disconnect();  
        	}
        }
        return jsonObject;  
    }  
	
	
	private static JSONObject getResult(HttpsURLConnection httpUrlConn) {
		InputStream inputStream = null;
		StringBuffer buffer = new StringBuffer(); 
		JSONObject result = null;
		try {
			inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	        String str = null;  
	        while ((str = bufferedReader.readLine()) != null) {  
	            buffer.append(str);  
	        }  
	        bufferedReader.close();  
	        inputStreamReader.close();  
	        result = JSONObject.fromObject(buffer.toString());  
		} catch (IOException e) {
			logger.error("get result error." + e);
		} finally {
			closeInputStream(inputStream);
		}
        
        return result;
	}
	
	private static void sendIfDataIsNotNull(HttpsURLConnection httpUrlConn,String dataForOutput) {
		 if (null != dataForOutput) {  
            OutputStream outputStream = null;
			try {
				outputStream = httpUrlConn.getOutputStream();
				outputStream.write(dataForOutput.getBytes("UTF-8"));  
			} catch (IOException e) {
				logger.error("send message error .message is " + dataForOutput,e);
			}finally {
				closeOutputStream(outputStream);
			}
         }  
	}
	
	

/*
 * Copy from baidu
 */
	private static HttpsURLConnection getHttpUrlConn(String requestUrl,String requestMethod) throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, IOException {
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
        TrustManager[] tm = { new MyX509TrustManager() };  
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
        sslContext.init(null, tm, new java.security.SecureRandom());  
        // 从上述SSLContext对象中得到SSLSocketFactory对象  
        SSLSocketFactory ssf = sslContext.getSocketFactory();  

        URL url = new URL(requestUrl);  
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
        httpUrlConn.setSSLSocketFactory(ssf);  

        httpUrlConn.setDoOutput(true);  
        httpUrlConn.setDoInput(true);  
        httpUrlConn.setUseCaches(false);  
        
        httpUrlConn.setRequestMethod(requestMethod);  
        
        if ("GET".equalsIgnoreCase(requestMethod))  {
        	 httpUrlConn.connect();  
        }
        return httpUrlConn;
	}
	
	
	private static void closeOutputStream(OutputStream os) {
		if(os != null) {
			 try {
				os.close();
			} catch (IOException e) {
				logger.error("Close outputstream error" + e);
			}
		}
	}

	private static void closeInputStream(InputStream in) {
		if(in != null) {
			 try {
				in.close();
			} catch (IOException e) {
				logger.error("Close inputstream error" + e);
			}
		}
	}
	
}
