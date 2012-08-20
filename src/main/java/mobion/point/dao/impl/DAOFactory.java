package mobion.point.dao.impl;

import mobion.point.service.impl.AbstractService;

public class DAOFactory extends AbstractService{
	
	
	private static DAOFactory instance;
	
	private DAOFactory(){
		
	}
	
	public DAOFactory init(){
		if(instance == null){
			instance = new DAOFactory();
		}
		
		return instance;
	}
	public DAOFactory getInstance(){
		return instance;
	}
	
}
