package mobion.point.service.impl;

import mobion.point.dao.MemberInfoDAO;
import mobion.point.dao.MemberTypeDAO;
import mobion.point.dao.OriginalSystemDAO;
import mobion.point.dao.SnapshotTransactionsDAO;
import mobion.point.dao.SnapshotTransactionsTypeDAO;
import mobion.point.dao.TransactionsDAO;
import mobion.point.dao.TransactionsGroupDAO;
import mobion.point.dao.TransactionsGroupTypeDAO;
import mobion.point.dao.TransactionsTypeDAO;

public class AbstractService {

	MemberInfoDAO memberInfoDAO;
	MemberTypeDAO memberTypeDAO;
	OriginalSystemDAO originalSystemDAO;
	SnapshotTransactionsDAO snapshotTransactionsDAO;
	SnapshotTransactionsTypeDAO snapshotTransactionsTypeDAO;
	TransactionsDAO transactionsDAO;
	TransactionsGroupDAO transactionsGroupDAO;
	TransactionsGroupTypeDAO transactionsGroupTypeDAO;
	TransactionsTypeDAO transactionsTypeDAO;
	
	public MemberInfoDAO getMemberInfoDAO() {
		return memberInfoDAO;
	}

	public void setMemberInfoDAO(MemberInfoDAO memberInfoDAO) {
		this.memberInfoDAO = memberInfoDAO;
	}

	public MemberTypeDAO getMemberTypeDAO() {
		return memberTypeDAO;
	}

	public void setMemberTypeDAO(MemberTypeDAO memberTypeDAO) {
		this.memberTypeDAO = memberTypeDAO;
	}

	public OriginalSystemDAO getOriginalSystemDAO() {
		return originalSystemDAO;
	}

	public void setOriginalSystemDAO(OriginalSystemDAO originalSystemDAO) {
		this.originalSystemDAO = originalSystemDAO;
	}

	public SnapshotTransactionsDAO getSnapshotTransactionsDAO() {
		return snapshotTransactionsDAO;
	}

	public void setSnapshotTransactionsDAO(
			SnapshotTransactionsDAO snapshotTransactionsDAO) {
		this.snapshotTransactionsDAO = snapshotTransactionsDAO;
	}

	public SnapshotTransactionsTypeDAO getSnapshotTransactionsTypeDAO() {
		return snapshotTransactionsTypeDAO;
	}

	public void setSnapshotTransactionsTypeDAO(
			SnapshotTransactionsTypeDAO snapshotTransactionsTypeDAO) {
		this.snapshotTransactionsTypeDAO = snapshotTransactionsTypeDAO;
	}

	public TransactionsDAO getTransactionsDAO() {
		return transactionsDAO;
	}

	public void setTransactionsDAO(TransactionsDAO transactionsDAO) {
		this.transactionsDAO = transactionsDAO;
	}

	public TransactionsGroupDAO getTransactionsGroupDAO() {
		return transactionsGroupDAO;
	}

	public void setTransactionsGroupDAO(TransactionsGroupDAO transactionsGroupDAO) {
		this.transactionsGroupDAO = transactionsGroupDAO;
	}

	public TransactionsGroupTypeDAO getTransactionsGroupTypeDAO() {
		return transactionsGroupTypeDAO;
	}

	public void setTransactionsGroupTypeDAO(
			TransactionsGroupTypeDAO transactionsGroupTypeDAO) {
		this.transactionsGroupTypeDAO = transactionsGroupTypeDAO;
	}

	public TransactionsTypeDAO getTransactionsTypeDAO() {
		return transactionsTypeDAO;
	}

	public void setTransactionsTypeDAO(TransactionsTypeDAO transactionsTypeDAO) {
		this.transactionsTypeDAO = transactionsTypeDAO;
	}
}
