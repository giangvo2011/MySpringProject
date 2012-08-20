package mobion.point.service;

import mobion.point.bean.BaseBean;
import mobion.point.bean.MemberInfo;
import mobion.point.exception.MobionException;

public interface AccountService {

	public MemberInfo getAccount(String id) throws MobionException;

	public MemberInfo createAccount(MemberInfo memberInfo) throws MobionException;

	public MemberInfo updateAccount(MemberInfo data) throws MobionException;
	
	public BaseBean deleteAccount(String id) throws MobionException;

}
