package com.gongzong.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gongzong.bean.TextMessage;
import com.gongzong.bean.WechatMessage;
import com.gongzong.util.PropertiesUtil;

public class TextMessageHandler implements MessageHandler{
	private static final Logger logger = Logger.getLogger(TextMessageHandler.class);
	@Override
	public void execute(WechatMessage message, HttpServletRequest request, HttpServletResponse response) {
		WechatMessage resMsg = getMessageForResponse( (TextMessage)message);
		responese( request,  response, resMsg);
	}
	
	
	private WechatMessage getMessageForResponse(TextMessage msg) {
		logger.info("String to get response message.");
		
		String fromUserName = msg.getFromUserName();
		String toUserName = msg.getToUserName();
		TextMessage responseMessage = new TextMessage();
		responseMessage.setFromUserName(toUserName);
		responseMessage.setToUserName(fromUserName);
		responseMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
		responseMessage.setMsgType(msg.getMsgType());
		responseMessage.setContent("你好,公众号还在建设中。。。");
		logger.info("Get response message end. " + responseMessage);
		return responseMessage;
	}
	
	
	private void responese(HttpServletRequest request, HttpServletResponse response,WechatMessage resMsg) {
		String result = resMsg.convertToXml();
		logger.info("Strat to responese. xml : " + result);
		PrintWriter pw = null;
		String charSet = response.getCharacterEncoding();
		response.setCharacterEncoding(PropertiesUtil.getCharSet());
		try { 
			pw = response.getWriter();
			pw.println(result);
			pw.flush();
		} catch (IOException e) {
			logger.error(e.getMessage() + " \r\n error when writer resMsg in to httpServletResponse." + resMsg);
		}finally {
			response.setCharacterEncoding(charSet);
			if(pw != null) {
				pw.close();
			}
		}
	}
	

}
