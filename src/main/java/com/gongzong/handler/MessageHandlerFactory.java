package com.gongzong.handler;

import com.gongzong.bean.WechatMessage;

public class MessageHandlerFactory {
	
	
	
	public static MessageHandler buildHandler(WechatMessage message) {
		MessageHandler handler = MessageHandler.EMPTY_HANDLER;
		
		if(message == WechatMessage.EMPTY_MESSAGE) {
			return MessageHandler.EMPTY_HANDLER;
		}
		String msgType = message.getMsgType();
		if(WechatMessage.MESSAGE_TYPE_TEXT.equals(msgType)) {
			handler = new TextMessageHandler();
		}else if(WechatMessage.MESSAGE_TYPE_IMAGE.equals(msgType)) {
			handler = new ImageMessageHandler();
		}
		
		
		return handler;
	}
}
