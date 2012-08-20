package mobion.point.dao.impl;


import mobion.point.bean.TransactionsType;
import mobion.point.dao.TransactionsTypeDAO;

public class TransactionsTypeDAOImpl extends MobionAbstractDAO<String,TransactionsType> implements TransactionsTypeDAO{
	public TransactionsTypeDAOImpl() {
		super(String.class, TransactionsType.class);
	}
}