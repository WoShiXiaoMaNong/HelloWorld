package com.gongzong.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class XmlUtil {
	private static final Logger logger = Logger.getLogger(XmlUtil.class);
	
	public static String getXmlFromRequest(HttpServletRequest request) {
		StringBuilder xml = new StringBuilder();
		String encoding = request.getCharacterEncoding();
		BufferedReader br = null;
		try {
			request.setCharacterEncoding(PropertiesUtil.getCharSet());
			 br = request.getReader();
			String line = br.readLine();
			while(!StringUtils.isEmpty(line)) {
				xml.append(line);
				logger.debug(line);
				line = br.readLine();
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage() + "\r\n error when to set character encoding. Character Encoding is :" + encoding);
		} catch (IOException e) {
			logger.error(e.getMessage() + "\r\n error when to read xml info from http request!");
		}finally {
			if( br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getMessage() + "\r\n error when to close the buffered reader.");
				}
			}
		}
		
		
		return xml.toString();
	}
}
