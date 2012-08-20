package mobion.point.rest.impl;

import mobion.point.bean.BaseBean;
import mobion.point.bean.TransactionsGroup;
import mobion.point.bean.TransferParam;
import mobion.point.exception.MobionException;
import mobion.point.rest.TransactionRest;

public class TransactionRestImpl extends AbstractRest implements TransactionRest {

	public BaseBean addPoint(String memId, Long point) throws MobionException {

		return transactionService.addPoint(memId, point, getCurrentSystemId());
	}

	public BaseBean subtractPoint(String memId, Long point) throws MobionException {

		return transactionService.subtractPoint(memId, point, getCurrentSystemId());
	}

	public BaseBean transferPoint(TransferParam transferParam) throws MobionException {

		return transactionService.transferPoint(transferParam.getSourceId(), transferParam.getDesId(),
				transferParam.getPoint(), transferParam.getExtendPoint(), getCurrentSystemId(),
				transferParam.getDesc(), transferParam.getExDesc());
	}

	public TransactionsGroup transferPointAndWait(TransferParam transferParam) throws MobionException {

		return transactionService.transferPointAndWait(transferParam.getSourceId(), transferParam.getDesId(),
				transferParam.getPoint(), transferParam.getExtendPoint(), getCurrentSystemId(),
				transferParam.getDesc(), transferParam.getExDesc());
	}

	public BaseBean confirmTransferPointAndWait(String sourceMemId, Long transGroupId) throws MobionException {

		return transactionService.confirmTransferPointAndWait(sourceMemId, transGroupId);
	}

	public BaseBean cancelTransferAndWait(String sourceMemId, Long transGroupId) throws MobionException {

		return transactionService.cancelTransferPointAndWait(sourceMemId, transGroupId);
	}

}
