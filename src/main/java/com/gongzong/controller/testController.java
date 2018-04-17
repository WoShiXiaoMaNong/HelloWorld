package com.gongzong.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gongzong.bean.MessageFactory;
import com.gongzong.bean.WechatMessage;
import com.gongzong.handler.MessageHandler;
import com.gongzong.handler.MessageHandlerFactory;
import com.gongzong.util.SignUtil;
import com.gongzong.util.XmlUtil;


@Controller
public class testController {
	private Logger logger = Logger.getLogger(testController.class);
	private static final String TIMESTAMP = "timestamp";
    private static final String ECHOSTR = "echostr";
    private static final String NONCE = "nonce";
    private static final String SIGNATURE = "signature";
    
	@RequestMapping(value = "/mrkkk")
	public void hello(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter(SIGNATURE);
        String timestamp = request.getParameter(TIMESTAMP);
        String nonce = request.getParameter(NONCE);
        String echostr = request.getParameter(ECHOSTR);
		String method = request.getMethod();
		if(!checkcalidata( signature, timestamp, nonce)) {
			logger.warn("validate error! please check!");
			return;
		}
		if("get".equalsIgnoreCase(method)) {
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				responseGet(echostr, response);
			}
		} else if ("post".equalsIgnoreCase(method)) {
			logger.info("Start to reciver xml from request...");
			String xml = XmlUtil.getXmlFromRequest(request);
			logger.info("Reciver xml from request end");
			WechatMessage message = MessageFactory.buildMessageBean(xml);
			MessageHandler handler = MessageHandlerFactory.buildHandler(message);
			handler.execute(message, request, response);
		}
		
       
		
	}
	        

	
	
	private boolean checkcalidata(String signature,String timestamp,String nonce) {
		
		return SignUtil.checkSignature(signature, timestamp, nonce);
	}

	
	public void responseGet(String echostr,HttpServletResponse response) {
		 PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(echostr);
			out.flush();
			logger.info("微信公众号对接成功！");
		} catch (IOException e) {
             logger.info(e.getMessage() + "微信公众号对接出现异常！");
         } finally {
        	 if(out != null) {
        		 out.close();
        	 }
             
         }
	}

	
}
