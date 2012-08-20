package mobion.point.service.impl;

import mobion.point.rest.impl.AbstractRest;

public class ServiceFactory extends AbstractRest{
	private ServiceFactory instance;
	
	private ServiceFactory(){
		
	}
	
	public ServiceFactory init(){
		if(instance == null){
			instance = new ServiceFactory();
		}
		
		return instance;
	}	
	
	public ServiceFactory getInstance(){
		return instance;
	}
}
