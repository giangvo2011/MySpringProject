package mobion.point.rest.utils;

import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@ServerInterceptor
public class RestPostInterceptor implements	PostProcessInterceptor{
	Logger log = LoggerFactory.getLogger(RestPostInterceptor.class);

	
	public void postProcess(ServerResponse ctx) {
		if (ctx.getResourceMethod()!=null){
			if (ctx.getEntity()==null || ctx.getResourceMethod().getReturnType().equals(Void.class)){
				ctx.setStatus(200);			
				ctx.setEntity("{\"status\":\"success\"}");
				
				log.debug("Return success status for void method "+ctx.getResourceMethod());			
			}
		}
	}
	
	
	

}
