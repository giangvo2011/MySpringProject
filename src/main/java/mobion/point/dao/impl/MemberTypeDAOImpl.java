package mobion.point.dao.impl;

import mobion.point.bean.MemberType;
import mobion.point.dao.MemberTypeDAO;

public class MemberTypeDAOImpl extends MobionAbstractDAO<String,MemberType> implements MemberTypeDAO{

	public MemberTypeDAOImpl() {
		super(String.class, MemberType.class);
	}

}
