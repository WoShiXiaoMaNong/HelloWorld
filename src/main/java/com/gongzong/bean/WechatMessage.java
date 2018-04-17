package com.gongzong.bean;

public abstract class WechatMessage {
	public static final String MESSAGE_TYPE_TEXT = "text";
	public static final String MESSAGE_TYPE_IMAGE = "image";
	public static final String MESSAGE_TYPE_EMPTY = "empty";
	public static final EmptyMessage EMPTY_MESSAGE = new EmptyMessage();
	
	protected String toUserName;
	protected String fromUserName;
	protected String CreateTime;
	protected String msgType;
	protected  String content;
	

	public abstract String convertToXml();
	
	
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

	
	

}
