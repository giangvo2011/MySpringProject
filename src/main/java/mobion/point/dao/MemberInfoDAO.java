package mobion.point.dao;

import mobion.point.bean.MemberInfo;
import mobion.point.exception.MobionException;

public interface MemberInfoDAO extends MobionDAO<String,MemberInfo>{
	public MemberInfo getGNTMemberInfo() throws MobionException;
}
