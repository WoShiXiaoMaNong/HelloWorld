package com.gongzong.bean;

import org.dom4j.Element;

public class ImageMessage extends WechatMessage {
	private String PicUrl;
	private String MediaId;
	private String msgId;
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public ImageMessage() {
	}
	public ImageMessage(Element root) {
		this.init(root);
	}
	@Override
	public String convertToXml() {
		return String.format(MessageFactory.BASE_XML_IMAGE, 
				this.getToUserName(), 
				this.getFromUserName(), 
				this.getCreateTime(), 
				this.getMediaId()
				);
	}
	private void init(Element root) {
		Element elementToUserName = root.element("ToUserName");
		Element elementFromUserName = root.element("FromUserName");
		Element elementCreateTime = root.element("CreateTime");
		Element elementPicUrl = root.element("PicUrl");
		Element elementMediaId = root.element("MediaId");
		Element elementMsgId = root.element("MsgId");
		
		this.setToUserName(elementToUserName.getText());
		this.setFromUserName(elementFromUserName.getText());
		this.setCreateTime(elementCreateTime.getText());
		this.setMsgType(WechatMessage.MESSAGE_TYPE_IMAGE);
		this.setPicUrl(elementPicUrl.getText());
		this.setMediaId(elementMediaId.getText());
		this.setMsgId(elementMsgId.getText());
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	@Override
	public String toString() {
		
		return String.format("TextMessage[ToUserName:%s;FromUserName:%s;CreateTime:%s;MsgType:%s;PicUrl:%s;]",
				this.getToUserName(),
				this.getFromUserName(),
				this.getCreateTime(),
				this.getMsgType(),
				this.getPicUrl());
	}
}
