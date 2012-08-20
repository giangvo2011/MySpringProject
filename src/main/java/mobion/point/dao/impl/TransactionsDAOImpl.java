package mobion.point.dao.impl;

import mobion.point.bean.Transactions;
import mobion.point.dao.TransactionsDAO;

public class TransactionsDAOImpl extends MobionAbstractDAO<String,Transactions> implements TransactionsDAO{
	public TransactionsDAOImpl() {
		super(String.class, Transactions.class);
	}
	
}