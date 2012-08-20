package mobion.point.rest.impl;

import mobion.point.bean.BaseBean;
import mobion.point.bean.MemberInfo;
import mobion.point.exception.MobionException;
import mobion.point.rest.AccountRest;

public class AccountRestImpl extends AbstractRest implements AccountRest{

	
	public MemberInfo createAccount(MemberInfo memberInfo) throws MobionException {
		return accountService.createAccount(memberInfo) ;
	}

	public MemberInfo getAccount(String id)
			throws MobionException {
		return accountService.getAccount(id);
	}

	public MemberInfo updateAccount(MemberInfo data) throws MobionException {
		return accountService.updateAccount(data);
	}

	public BaseBean deleteAccount(String id) throws MobionException {
		return accountService.deleteAccount(id);
	}
}
