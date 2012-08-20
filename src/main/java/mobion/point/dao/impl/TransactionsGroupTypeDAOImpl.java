package mobion.point.dao.impl;


import mobion.point.bean.TransactionsGroupType;
import mobion.point.dao.TransactionsGroupTypeDAO;

public class TransactionsGroupTypeDAOImpl extends MobionAbstractDAO<String,TransactionsGroupType> implements TransactionsGroupTypeDAO{
	public TransactionsGroupTypeDAOImpl() {
		super(String.class, TransactionsGroupType.class);
	}
}