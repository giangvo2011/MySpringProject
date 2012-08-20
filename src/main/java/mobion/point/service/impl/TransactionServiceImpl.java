package mobion.point.service.impl;

import java.util.Date;

import mobion.point.bean.BaseBean;
import mobion.point.bean.MemberInfo;
import mobion.point.bean.SnapshotTransactions;
import mobion.point.bean.Transactions;
import mobion.point.bean.TransactionsGroup;
import mobion.point.exception.AccountNotFoundException;
import mobion.point.exception.MobionException;
import mobion.point.service.TransactionService;

public class TransactionServiceImpl extends AbstractService implements TransactionService {

	public BaseBean addPoint(String memId, long point, int systemId) throws MobionException {
		MemberInfo gnt = memberInfoDAO.getGNTMemberInfo();

		transferPoint(gnt.getMemId(), memId, point, 0, systemId, TransactionsGroup.Type.ADD, false, "","");

		return new BaseBean();
	}

	public BaseBean subtractPoint(String memId, long point, int systemId) throws MobionException {

		MemberInfo gnt = memberInfoDAO.getGNTMemberInfo();

		transferPoint(memId, gnt.getMemId(), point, 0, systemId, TransactionsGroup.Type.SUBTRACT, false, "","");

		return new BaseBean();
	}

	public BaseBean transferPoint(String memIdFrom, String memIdTo, long point, long extendPoint, int systemId, String desc, String exDesc) throws MobionException {

		transferPoint(memIdFrom, memIdTo, point, extendPoint, systemId, TransactionsGroup.Type.TRANSFER, false, desc, exDesc);

		return new BaseBean();
	}

	public TransactionsGroup transferPointAndWait(String memIdFrom, String memIdTo, long point, long extendPoint, int systemId,String desc, String exDesc)
			throws MobionException {

		long transGroupId = transferPoint(memIdFrom, memIdTo, point, extendPoint, systemId, TransactionsGroup.Type.TRANSFER_WAIT, true, desc, exDesc);

		return new TransactionsGroup(transGroupId);

	}

	public BaseBean confirmTransferPointAndWait(String sourceMemId, long transGroupId) throws MobionException {
		TransactionsGroup group = transactionsGroupDAO.findById(transGroupId);
		if (group == null) {
			throw new MobionException(MobionException.INVALID_PARAM, "transactionGroupId not found");
		}

		if (!sourceMemId.equals(group.getSourceMemId())) {
			throw new MobionException(MobionException.INVALID_PARAM, "Invalid source member");
		}
		
		if(group.getTransStatus() != TransactionsGroup.Status.WAIT.getValue()){
			throw new MobionException(MobionException.TRANSACTION_PROCESSED);
		}

		// Get source member
		MemberInfo sourceMember = memberInfoDAO.findById(sourceMemId);
		if (sourceMember == null) {
			throw new AccountNotFoundException();
		}

		long totalSourcePoint = sourceMember.getTotalPoint();
		long availableSourcePoint = sourceMember.getAvailablePoint();

		long point = group.getTransPoint();

		Date now = new Date();
		// save snapshot of A before execute transaction
		SnapshotTransactions snapShotSourceBefore = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.BEFORE_TRANS_GROUP.getValue(), transGroupId, sourceMemId, totalSourcePoint,
				availableSourcePoint);
		snapshotTransactionsDAO.save(snapShotSourceBefore);

		// update total point of A
		long newTotalSourcePoint = totalSourcePoint - point;
		sourceMember.setTotalPoint(newTotalSourcePoint);
		memberInfoDAO.save(sourceMember);

		// save transactions of A
		Transactions transactionSource = new Transactions(now, now,
				Transactions.Type.TRANSFER_POINT_WAIT_SEND_CONFIRM.getValue(), transGroupId, sourceMemId, "", point,
				totalSourcePoint, newTotalSourcePoint, availableSourcePoint, availableSourcePoint);
		transactionsDAO.save(transactionSource);

		// save snapshot of A after execute transaction
		SnapshotTransactions snapShotSourceAfter = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.AFTER_TRANS_GROUP.getValue(), transGroupId, sourceMemId, newTotalSourcePoint,
				availableSourcePoint);
		snapshotTransactionsDAO.save(snapShotSourceAfter);

		// -------

		// Get des member
		String desMemId = group.getDestinationMemId();
		MemberInfo desMember = memberInfoDAO.findById(desMemId);
		if (desMember == null) {
			throw new AccountNotFoundException();
		}
		
		long totaldesPoint = desMember.getTotalPoint();
		long availabledesPoint = desMember.getAvailablePoint();

		// save snapshot of B before execute transaction
		SnapshotTransactions snapShotdesBefore = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.BEFORE_TRANS_GROUP.getValue(), transGroupId, desMemId, totaldesPoint,
				availabledesPoint);
		snapshotTransactionsDAO.save(snapShotdesBefore);

		// update total point of B
		long newTotaldesPoint = totaldesPoint + point;
		long newAvailabledesPoint = availabledesPoint + point;
		desMember.setTotalPoint(newTotaldesPoint);
		desMember.setAvailablePoint(newAvailabledesPoint);
		memberInfoDAO.save(desMember);

		// save transactions of B
		Transactions transactiondes = new Transactions(now, now,
				Transactions.Type.TRANSFER_POINT_WAIT_RECEIVED.getValue(), transGroupId, desMemId, "", point,
				totaldesPoint, newTotaldesPoint, availabledesPoint, newAvailabledesPoint);
		transactionsDAO.save(transactiondes);

		// save snapshot of B after execute transaction
		SnapshotTransactions snapShotdesAfter = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.AFTER_TRANS_GROUP.getValue(), transGroupId, desMemId, newTotaldesPoint,
				newAvailabledesPoint);
		snapshotTransactionsDAO.save(snapShotdesAfter);

		//update status of transaction group
		group.setTransStatus(TransactionsGroup.Status.DONE.getValue());
		transactionsGroupDAO.save(group);
		
		return new BaseBean();
	}

	public BaseBean cancelTransferPointAndWait(String sourceMemId, long transGroupId) throws MobionException {

		TransactionsGroup group = transactionsGroupDAO.findById(transGroupId);
		if (group == null) {
			throw new MobionException(MobionException.INVALID_PARAM, "transactionGroupId not found");
		}

		if(group.getTransStatus() != TransactionsGroup.Status.WAIT.getValue()){
			throw new MobionException(MobionException.TRANSACTION_PROCESSED);
		}
		
		if (!sourceMemId.equals(group.getSourceMemId())) {
			throw new MobionException(MobionException.INVALID_PARAM, "Invalid source member");
		}

		// Get source member
		MemberInfo sourceMember = memberInfoDAO.findById(sourceMemId);
		if (sourceMember == null) {
			throw new AccountNotFoundException();
		}

		long totalSourcePoint = sourceMember.getTotalPoint();
		long availableSourcePoint = sourceMember.getAvailablePoint();

		long point = group.getTransPoint();

		Date now = new Date();
		// save snapshot of A before execute transaction
		SnapshotTransactions snapShotSourceBefore = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.BEFORE_TRANS_GROUP.getValue(), transGroupId, sourceMemId, totalSourcePoint,
				availableSourcePoint);
		snapshotTransactionsDAO.save(snapShotSourceBefore);

		// update available point of A
		long newAvailableSourcePoint = availableSourcePoint + point;
		sourceMember.setAvailablePoint(newAvailableSourcePoint);
		memberInfoDAO.save(sourceMember);

		// save transactions of A
		Transactions transactionSource = new Transactions(now, now,
				Transactions.Type.TRANSFER_POINT_WAIT_SEND_CANCEL.getValue(), transGroupId, sourceMemId, "", point,
				totalSourcePoint, totalSourcePoint, availableSourcePoint, newAvailableSourcePoint);
		transactionsDAO.save(transactionSource);

		// save snapshot of A after execute transaction
		SnapshotTransactions snapShotSourceAfter = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.AFTER_TRANS_GROUP.getValue(), transGroupId, sourceMemId, totalSourcePoint,
				newAvailableSourcePoint);
		snapshotTransactionsDAO.save(snapShotSourceAfter);

		// update status of transaction group
		group.setTransStatus(TransactionsGroup.Status.CANCELED.getValue());
		transactionsGroupDAO.save(group);
		
		return new BaseBean();
	}

	private long transferPoint(String sourceMemId, String desMemId, long point, long extendPoint, int systemId,
			TransactionsGroup.Type transgroupType, boolean wait, String desc, String exDesc) throws MobionException {

		// Get source member
		MemberInfo sourceMember = memberInfoDAO.findById(sourceMemId);
		if (sourceMember == null) {
			throw new AccountNotFoundException();
		}

		// get source available and total point and check
		long totalSourcePoint = sourceMember.getTotalPoint();
		long availableSourcePoint = sourceMember.getAvailablePoint();

		if (sourceMember.getMemTypeId() == MemberInfo.Type.MEM_TYPE_USER.getValue()) {
			if (totalSourcePoint < point) {
				throw new MobionException(MobionException.NOT_ENOUGH_TOTAL_POINT);
			}

			if (availableSourcePoint < point) {
				throw new MobionException(MobionException.NOT_ENOUGH_AVAILABLE_POINT);
			}
		}

		// Get destination member
		MemberInfo desMember = memberInfoDAO.findById(desMemId);
		if (desMember == null) {
			throw new AccountNotFoundException();
		}

		// get destination available and total point
		long totalDesPoint = desMember.getTotalPoint();
		long availableDesPoint = desMember.getAvailablePoint();

		// save transatcion group
		Date now = new Date();
		int transGroupStatus = TransactionsGroup.Status.DONE.getValue();
		if(wait){
			transGroupStatus = TransactionsGroup.Status.WAIT.getValue();
		}
		
		TransactionsGroup group = new TransactionsGroup(now, now, transgroupType.getValue(), point, 
				extendPoint,desc, exDesc, sourceMemId, desMemId, systemId, transGroupStatus);
		long transGroupId = (Long) transactionsGroupDAO.save(group);

		// save snapshot of A before execute transaction
		SnapshotTransactions snapShotSourceBefore = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.BEFORE_TRANS_GROUP.getValue(), transGroupId, sourceMemId, totalSourcePoint,
				availableSourcePoint);
		snapshotTransactionsDAO.save(snapShotSourceBefore);

		// update point of A
		long newTotalSourcePoint = totalSourcePoint - point;
		long newAvailableSourcePoint = availableSourcePoint - point;
		// if transfer and wait -> just update available point of source member
		if (!wait) {
			sourceMember.setTotalPoint(newTotalSourcePoint);
		}

		sourceMember.setAvailablePoint(newAvailableSourcePoint);
		memberInfoDAO.save(sourceMember);

		// save transactions of A
		int transSoureType = getTransactionType(true, transgroupType);
		if(transSoureType == -1){
			throw new MobionException(MobionException.INVALID_TRANS_TYPE);
		}
		
		Transactions transactionSource = new Transactions(now, now, transSoureType,
				transGroupId, sourceMemId, "", point, totalSourcePoint, newTotalSourcePoint, availableSourcePoint,
				newAvailableSourcePoint);
		transactionsDAO.save(transactionSource);

		// save snapshot of A after execute transaction
		SnapshotTransactions snapShotSourceAfter = new SnapshotTransactions(now, now,
				SnapshotTransactions.Type.AFTER_TRANS_GROUP.getValue(), transGroupId, sourceMemId, newTotalSourcePoint,
				newAvailableSourcePoint);
		snapshotTransactionsDAO.save(snapShotSourceAfter);

		// if transfer and wait -> not update dest member
		if (!wait) {
			// save snapshot of B before execute transaction
			SnapshotTransactions snapShotDesBefore = new SnapshotTransactions(now, now,
					SnapshotTransactions.Type.BEFORE_TRANS_GROUP.getValue(), transGroupId, desMemId, totalDesPoint,
					availableDesPoint);
			snapshotTransactionsDAO.save(snapShotDesBefore);

			// update point of B
			long newTotalDesPoint = totalDesPoint + point;
			long newAvailableDesPoint = availableDesPoint + point;
			desMember.setTotalPoint(newTotalDesPoint);
			desMember.setAvailablePoint(newAvailableDesPoint);
			memberInfoDAO.save(desMember);

			int transdesType = getTransactionType(false, transgroupType);
			if(transdesType == -1){
				throw new MobionException(MobionException.INVALID_TRANS_TYPE);
			}
			
			
			// save snapshot of B after execute transaction
			Transactions transactionDes = new Transactions(now, now, transdesType,
					transGroupId, desMemId, "", point, totalDesPoint, newTotalDesPoint, availableDesPoint,
					newAvailableDesPoint);
			transactionDes.setMemId(desMemId);
			transactionsDAO.save(transactionDes);

			// snapshot after
			SnapshotTransactions snapShotDesAfter = new SnapshotTransactions(now, now,
					SnapshotTransactions.Type.AFTER_TRANS_GROUP.getValue(), transGroupId, desMemId, newTotalDesPoint,
					newAvailableDesPoint);
			snapshotTransactionsDAO.save(snapShotDesAfter);
		}

		return transGroupId;
	}

	private int getTransactionType(boolean isSend, TransactionsGroup.Type transgroupType) {
		if (isSend) {
			switch (transgroupType) {
			case ADD:
				return Transactions.Type.ADD_POINT_SEND.getValue();
			case SUBTRACT:
				return Transactions.Type.SUBTRACT_POINT_SEND.getValue();
			case TRANSFER:
				return Transactions.Type.TRANSFER_POINT_SEND.getValue();
			case TRANSFER_WAIT:
				return Transactions.Type.TRANSFER_POINT_SEND.getValue();
			default:
				return -1;
			}
		} else {
			switch (transgroupType) {
			case ADD:
				return Transactions.Type.ADD_POINT_RECEIVED.getValue();
			case SUBTRACT:
				return Transactions.Type.SUBTRACT_POINT_RECEIVED.getValue();
			case TRANSFER:
				return Transactions.Type.TRANSFER_POINT_RECEIVED.getValue();
			case TRANSFER_WAIT:
				return Transactions.Type.TRANSFER_POINT_RECEIVED.getValue();
			default:
				return -1;
			}
		}
	}

}
