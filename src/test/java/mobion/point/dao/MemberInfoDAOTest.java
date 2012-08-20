package mobion.point.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import mobion.point.AbstractServiceTestBase;
import mobion.point.bean.MemberInfo;
import mobion.point.exception.MobionException;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoDAOTest extends AbstractServiceTestBase {
	Logger log = LoggerFactory.getLogger(MemberInfoDAOTest.class);
	
	@Autowired
	MemberInfoDAO memberInfoDAO;
	

	@Test
	@Ignore
	public void getAllMember() throws MobionException {
		List<MemberInfo> list = memberInfoDAO.findAll();
		
		assertTrue("there are at least 1 member",list.size() > 0);
		for(MemberInfo mem : list){
			log.debug(mem.toString());
		}
		
	}
	
	@Test
	@Ignore
	public void getGNTMemberInfo() throws MobionException {
		MemberInfo gnt = memberInfoDAO.getGNTMemberInfo();
		
		assertNotNull(gnt);
		log.info(gnt.toString());
		assertTrue(gnt.getMemTypeId() == MemberInfo.Type.MEM_TYPE_GNT_SYSTEM.getValue());
		
	}
}
