package com.gongzong.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongzong.bean.WechatMessage;

public interface MessageHandler {
	public static final MessageHandler EMPTY_HANDLER = new EmptyHandler();
	public void execute(WechatMessage message,HttpServletRequest request, HttpServletResponse response);
}
