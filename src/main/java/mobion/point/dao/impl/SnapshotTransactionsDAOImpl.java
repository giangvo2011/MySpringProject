package mobion.point.dao.impl;

import mobion.point.bean.SnapshotTransactions;
import mobion.point.dao.SnapshotTransactionsDAO;


public class SnapshotTransactionsDAOImpl extends MobionAbstractDAO<String,SnapshotTransactions> implements SnapshotTransactionsDAO{
	public SnapshotTransactionsDAOImpl() {
		super(String.class, SnapshotTransactions.class);
	}
}
