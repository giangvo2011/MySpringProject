package mobion.point.dao.impl;

import mobion.point.bean.OriginalSystem;
import mobion.point.dao.OriginalSystemDAO;


public class OriginalSystemDAOImpl extends MobionAbstractDAO<String,OriginalSystem> implements OriginalSystemDAO{

	public OriginalSystemDAOImpl() {
		super(String.class, OriginalSystem.class);
	}
}
