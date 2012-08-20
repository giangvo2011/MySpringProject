package mobion.point.dao.impl;


import mobion.point.bean.SnapshotTransactionsType;
import mobion.point.dao.SnapshotTransactionsTypeDAO;


public class SnapshotTransactionsTypeDAOImpl extends MobionAbstractDAO<String,SnapshotTransactionsType> implements SnapshotTransactionsTypeDAO{
	public SnapshotTransactionsTypeDAOImpl() {
		super(String.class, SnapshotTransactionsType.class);
	}
}
