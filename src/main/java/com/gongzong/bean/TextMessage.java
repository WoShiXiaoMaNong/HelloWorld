package com.gongzong.bean;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TextMessage extends WechatMessage{
	private static final Logger logger = Logger.getLogger(TextMessage.class);
	public static String BASE_XML_TEXT = "<xml> "
			+ "<ToUserName><![CDATA[%s]]></ToUserName> "
			+ "<FromUserName><![CDATA[%s]]></FromUserName> "
			+ "<CreateTime>%s</CreateTime>"
			+ " <MsgType><![CDATA[text]]></MsgType> "
			+ "<Content><![CDATA[%s]]></Content> "
			+ "</xml>";
	
	public TextMessage() {
	}
	public TextMessage(Element root) {
		this.init(root);
	}
	
	@Override
	public String convertToXml() {
		return String.format(BASE_XML_TEXT, 
				this.getToUserName(), 
				this.getFromUserName(),  
				this.getCreateTime(),  
				this.getContent());
	}
	private void init(Element root) {
		Element elementToUserName = root.element("ToUserName");
		Element elementFromUserName = root.element("FromUserName");
		Element elementCreateTime = root.element("CreateTime");
		Element elementContent = root.element("Content");
		
		this.setToUserName(elementToUserName.getText());
		this.setFromUserName(elementFromUserName.getText());
		this.setCreateTime(elementCreateTime.getText());
		this.setMsgType(WechatMessage.MESSAGE_TYPE_TEXT);
		this.setContent(elementContent.getText());
	}
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		
		return String.format("TextMessage[ToUserName:%s;FromUserName:%s;CreateTime:%s;MsgType:%s;Content:%s;]",
				this.getToUserName(),
				this.getFromUserName(),
				this.getCreateTime(),
				this.getMsgType(),
				this.getContent());
	}

	
	
	
}
