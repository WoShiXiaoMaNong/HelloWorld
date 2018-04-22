package com.gongzong.custom.menu.tool.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class AccessToken {
	private String token;  
	
    private int expiresIn;  
    
    private Date endDate;
    
    
    public AccessToken() {
    	endDate = new Date();
    }
    
    private void refrushEndDate() {
    	Calendar calendar = Calendar.getInstance();    
        calendar.setTime(new Date());    
    	calendar.add(Calendar.SECOND, this.getExpiresIn()); 
    	this.endDate = calendar.getTime();
    }
    
    public boolean isAvaliable() {
    	boolean isTimeOut = this.endDate.compareTo(new Date()) < 0;
    	boolean isInitialized = !StringUtils.isEmpty(this.token);
    	
    	return  isInitialized && !isTimeOut;
    }
    
    
    public String getToken() {  
        return token;  
    }  
  
    public void setToken(String token) {  
        this.token = token;  
    }  
  
    public int getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn; 
        this.refrushEndDate();
    }
    
    public String getEndDateByFormat(String format) {
    	SimpleDateFormat dateFormat=new SimpleDateFormat(format); 
    	return dateFormat.format(this.endDate); 
    }
    
}
