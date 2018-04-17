package com.gongzong.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongzong.bean.WechatMessage;

public class EmptyHandler implements MessageHandler {

	protected EmptyHandler() {
		
	}
	
	@Override
	public void execute(WechatMessage message, HttpServletRequest request, HttpServletResponse response) {
		//do nothing;
	}

}
