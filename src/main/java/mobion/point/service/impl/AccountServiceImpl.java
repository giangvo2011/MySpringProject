package mobion.point.service.impl;

import java.util.Date;

import mobion.point.bean.BaseBean;
import mobion.point.bean.MemberInfo;
import mobion.point.bean.TransactionsGroup;
import mobion.point.exception.MobionException;
import mobion.point.service.AccountService;
import mobion.point.utils.StringUtils;

public class AccountServiceImpl extends AbstractService implements AccountService{

	public MemberInfo getAccount(String id) throws MobionException {
		MemberInfo mem = memberInfoDAO.findById(id);
		if(mem == null){
			throw new MobionException(MobionException.INVALID_PARAM);
		}
		
		return mem;
	}
	
	public MemberInfo createAccount(MemberInfo memberInfo) throws MobionException {
		memberInfo.setMemId(StringUtils.genId());
		memberInfo.setCreatedDate(new Date());
		memberInfoDAO.save(memberInfo);
		
		return memberInfo; 

	}
	
	public BaseBean deleteAccount(String id) throws MobionException {
		MemberInfo mem = memberInfoDAO.findById(id);
		if(mem != null){
			memberInfoDAO.delete(mem);
		}
		
		return new BaseBean();
	}

	public MemberInfo updateAccount(MemberInfo data) throws MobionException {
		
		MemberInfo mem = memberInfoDAO.findById(data.getMemId());
		if(mem == null){
			throw new MobionException(MobionException.INVALID_PARAM);
		}
		
		MemberInfo updatedInfo = BaseBean.update(data, mem);
		memberInfoDAO.update(updatedInfo);
		
		return updatedInfo;
	}

	
	
}
