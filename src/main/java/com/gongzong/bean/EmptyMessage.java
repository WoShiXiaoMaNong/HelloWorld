package com.gongzong.bean;

public class EmptyMessage extends WechatMessage{

	
	protected EmptyMessage() {
		
	}
	
	@Override
	public String convertToXml() {
		return WechatMessage.MESSAGE_TYPE_EMPTY;
	}

	@Override
	public String getMsgType() {
		// TODO Auto-generated method stub
		return null;
	}

}
