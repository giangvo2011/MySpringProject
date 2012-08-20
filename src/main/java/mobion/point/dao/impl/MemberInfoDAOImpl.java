package mobion.point.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import mobion.point.bean.MemberInfo;
import mobion.point.bean.MemberInfo.Type;
import mobion.point.dao.MemberInfoDAO;
import mobion.point.exception.MobionException;

public class MemberInfoDAOImpl extends MobionAbstractDAO<String, MemberInfo> implements MemberInfoDAO{

	public MemberInfoDAOImpl() {
		super(String.class, MemberInfo.class);
	}

	@Override
	public Object save(MemberInfo t) {
		t.setUpdateddDate(new Date());
		return super.save(t);
	}

	public MemberInfo getGNTMemberInfo() throws MobionException {
		MemberInfo mem = new MemberInfo(Type.MEM_TYPE_GNT_SYSTEM.getValue());
		mem.setAvailablePoint(null);
		mem.setTotalPoint(null);
		
		List<MemberInfo> list = findByExample(mem);
		if(list.size() > 0){
			return list.get(0);
		}
		
		throw new MobionException(MobionException.SYSTEM_ERROR, "GNT System account not found!");
	}

}
