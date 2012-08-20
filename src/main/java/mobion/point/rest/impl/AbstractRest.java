package mobion.point.rest.impl;

import mobion.point.bean.OriginalSystem;
import mobion.point.service.AccountService;
import mobion.point.service.TransactionService;

public class AbstractRest {
	TransactionService transactionService;
	AccountService accountService;

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	protected int getCurrentSystemId(){
		return OriginalSystem.Type.MINE.getValue();
	}
}
