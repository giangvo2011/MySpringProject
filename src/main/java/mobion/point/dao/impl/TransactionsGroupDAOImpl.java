package mobion.point.dao.impl;

import mobion.point.bean.TransactionsGroup;
import mobion.point.dao.TransactionsGroupDAO;

public class TransactionsGroupDAOImpl extends MobionAbstractDAO<Long,TransactionsGroup> implements TransactionsGroupDAO{
	public TransactionsGroupDAOImpl() {
		super(Long.class, TransactionsGroup.class);
	}
}