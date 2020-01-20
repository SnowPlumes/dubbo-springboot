package me.lv.common.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	private static Logger log = LoggerFactory.getLogger(RequestUtil.class);

	public static String getRemouteIp(HttpServletRequest request){
		 String ip  =request.getHeader("X-Real-IP");
		 log.debug("X-Real-IP ip: {}",ip);
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
			ip = request.getHeader( "x-forwarded-for" ); 
			log.debug("x-forwarded-for: {}",ip);
	     } 
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader( "Proxy-Client-IP" ); 
            log.debug("Proxy-Client-IP: {}",ip);
         } 
         if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) { 
            ip = request.getHeader( "WL-Proxy-Client-IP" ); 
            log.debug("WL-Proxy-Client-IP: {}",ip);
         } 
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
            log.debug("request.getRemoteAddr(): {}",ip);
         }
         
         log.debug("return IP: {}",ip);
         return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}
