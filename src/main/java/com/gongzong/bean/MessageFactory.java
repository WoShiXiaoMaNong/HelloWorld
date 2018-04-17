package com.gongzong.bean;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MessageFactory {
	private static final Logger logger = Logger.getLogger(MessageFactory.class);
	public static String BASE_XML_IMAGE = "<xml> "
			+ "<ToUserName><![CDATA[%s]]></ToUserName> "
			+ "<FromUserName><![CDATA[%s]]></FromUserName> "
			+ "<CreateTime>%s</CreateTime>"
			+ " <MsgType><![CDATA[image]]></MsgType> "
			+ "<Image>"
			+ "<MediaId><![CDATA[%s]]></MediaId>"
			+ "</Image>"
			+ "</xml>";
	public static String BASE_XML_TEXT = "<xml> "
			+ "<ToUserName><![CDATA[%s]]></ToUserName> "
			+ "<FromUserName><![CDATA[%s]]></FromUserName> "
			+ "<CreateTime>%s</CreateTime>"
			+ " <MsgType><![CDATA[text]]></MsgType> "
			+ "<Content><![CDATA[%s]]></Content> "
			+ "</xml>";
	
	public static WechatMessage buildMessageBean(String xml ) {
		WechatMessage message = WechatMessage.EMPTY_MESSAGE;
		logger.info("Start to build message bean!");
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			message = convertToWechatMessage(root);
			logger.info("build message end.Message is " + message.convertToXml());
		} catch (DocumentException e) {
			logger.error(e.getMessage() + "\r\n error when parsText ,xml is: " + xml);
		}
		return message;
	}
	
	
	private static WechatMessage convertToWechatMessage(Element root) {
		WechatMessage message =  WechatMessage.EMPTY_MESSAGE;
		logger.info("Start to convert message.");
		Element elementMsgType = root.element("MsgType");
		logger.info("message type is " + elementMsgType.getText());
		if(WechatMessage.MESSAGE_TYPE_TEXT.equals(elementMsgType.getText())) {
			message = new TextMessage(root);
		}else if(WechatMessage.MESSAGE_TYPE_IMAGE.equals(elementMsgType.getText())) {
			message = new ImageMessage(root);
		}
		logger.info("Convert message end.");
		return message;
	}
}
 