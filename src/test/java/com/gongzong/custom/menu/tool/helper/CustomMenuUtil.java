package com.gongzong.custom.menu.tool.helper;

import org.apache.log4j.Logger;

import com.gongzong.custom.menu.tool.bean.AccessToken;

import net.sf.json.JSONObject;

public class CustomMenuUtil {
	private static final Logger logger = Logger.getLogger(CustomMenuUtil.class);
	private static final String URL_FOR_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	private AccessToken accessToken;
	private String appId;
	private String secret;

	public CustomMenuUtil(String aAppId, String aSecret) {
		this.appId = aAppId;
		this.secret = aSecret;
		this.accessToken = new AccessToken();
	}
	
	
	public void addCustomMenu() {
		this.checkAndRefrushToken();
		// JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);  
	}
	
	
	private void checkAndRefrushToken() {
		logger.info("Start to check access token.");
		if(this.accessToken.isAvaliable()) {
			logger.info("[No need to update access token]Access token is avaliable before " + this.accessToken.getEndDateByFormat("yyyy-MM-dd HH:mm:ss") );
			return;
		}
		updateAccessToken();
		
	}
	
	
	private void updateAccessToken() {
		logger.info("Access token is unavailable. Start to update access token" );
		String requestUrl = String.format(URL_FOR_GET_ACCESS_TOKEN, this.appId,this.secret);
		JSONObject result =  HttpHelper.httpRequestGet(requestUrl);
		if(isSucceed(result)) {
			String accessToken = result.getString("access_token");
			String expiresIn = result.getString("expires_in");
			this.accessToken.setExpiresIn(Integer.valueOf(expiresIn));
			this.accessToken.setToken(accessToken);
			logger.info("update access Token succeed! Avaliable before " + this.accessToken.getEndDateByFormat("yyyy-MM-dd HH:mm:ss"));
		}else {
			logger.error("error when update AccessToken! error message : " +result.get("errmsg") + " error Code :" + result.get("errcode") );
		}
		
	}
	
	private boolean isSucceed(JSONObject result) {
		return  !result.containsKey("errcode");
	}
	
}
