package mobion.point.rest.utils;


import mobion.point.exception.MobionException;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public final class RestUtils {
	public static ServletRequestAttributes getCurentServletRequest() throws MobionException{
		ServletRequestAttributes sevletRequest=(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		if (sevletRequest==null){
			throw new MobionException(MobionException.SYSTEM_ERROR, "no http request!");
		}
		
		return sevletRequest;
	}
	
	
	public static String getCurrentRequesetHeader(String header,String defaultValue) throws MobionException{
		String s=getCurentServletRequest().getRequest().getHeader(header);
		if (s==null){		
			s=(String)getCurentServletRequest().getRequest().getAttribute(header);
			if (s==null){
				s= defaultValue;
			}
		}
		return s;
	}
	
	public static String getCurrentRequesetHeader(String header) throws MobionException{
		return getCurrentRequesetHeader(header, null);
	}
	public static String getCurrentRequesetParam(String key) throws MobionException{
		return getCurentServletRequest().getRequest().getParameter(key);
	}
	public static void setCurrentRequestAttribute(String key,Object value) throws MobionException{
		getCurentServletRequest().setAttribute(key,value,RequestAttributes.SCOPE_REQUEST);
	}
	public static Object getCurrentRequestAttribute(String key) throws MobionException{
		return getCurentServletRequest().getAttribute(key,RequestAttributes.SCOPE_REQUEST);
	}
	
	public static String getCurrentSystem() throws MobionException{
		
		return getCurrentRequesetHeader(RestConstants.HEADER_X_MP_SYSTEM_ID);
	}
}
