package com.gongzong.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gongzong.bean.ImageMessage;
import com.gongzong.bean.TextMessage;
import com.gongzong.bean.WechatMessage;
import com.gongzong.util.PropertiesUtil;

public class ImageMessageHandler implements MessageHandler {
	private static final Logger logger = Logger.getLogger(ImageMessageHandler.class);
	@Override
	public void execute(WechatMessage message, HttpServletRequest request, HttpServletResponse response) {
		WechatMessage resMsg = getMessageForResponse( (ImageMessage)message);
		responese( request,  response, resMsg);
	}
	
	
	private WechatMessage getMessageForResponse(ImageMessage msg) {
		logger.info("String to get response message.");
		
		String fromUserName = msg.getFromUserName();
		String toUserName = msg.getToUserName();
		ImageMessage responseMessage = new ImageMessage();
		responseMessage.setFromUserName(toUserName);
		responseMessage.setToUserName(fromUserName);
		responseMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
		responseMessage.setMsgType(msg.getMsgType());
		responseMessage.setPicUrl(msg.getPicUrl());
		responseMessage.setMediaId(msg.getMediaId());
		responseMessage.setMsgId(msg.getMsgId());
		logger.info("Get response message end. " + responseMessage);
		return responseMessage;
	}
	
	
	private void responese(HttpServletRequest request, HttpServletResponse response,WechatMessage responseMsg) {
		String result = responseMsg.convertToXml();
		logger.info("Strat to responese. xml : " + result);
		PrintWriter pw = null;
		String charSet = response.getCharacterEncoding();
		response.setCharacterEncoding(PropertiesUtil.getCharSet());
		try { 
			pw = response.getWriter();
			pw.println(result);
			pw.flush();
		} catch (IOException e) {
			logger.error(e.getMessage() + " \r\n error when responese ImageMessage." + responseMsg);
		}finally {
			response.setCharacterEncoding(charSet);
			if(pw != null) {
				pw.close();
			}
		}
	}
	
	
}
