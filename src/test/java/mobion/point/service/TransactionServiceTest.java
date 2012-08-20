package mobion.point.service;

import static org.junit.Assert.assertTrue;
import mobion.point.AbstractServiceTestBase;
import mobion.point.bean.MemberInfo;
import mobion.point.bean.OriginalSystem;
import mobion.point.bean.TransactionsGroup;
import mobion.point.dao.MemberInfoDAO;
import mobion.point.exception.MobionException;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class TransactionServiceTest extends AbstractServiceTestBase {
	Logger log = LoggerFactory.getLogger(TransactionServiceTest.class);

	@Autowired
	TransactionService transactionService;
	
	@Autowired
	MemberInfoDAO memberInfoDAO;

	String memAId = "19f203ba-db17-4225-87fe-2233595ac2ec";
	String memBId = "1c3196c4-70cf-484d-b96f-5502188a801d";
	int systemId = OriginalSystem.Type.MINE.getValue();
	int point = 5000;

	@Test
//	@Ignore
	public void testlogger() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}

	@Test
	@Ignore
	public void transferCointNotEnoughtTotalPoint() {

		try {
			transactionService.transferPoint(memAId, memBId, 2000000, 0, systemId, "","");
		} catch (MobionException e) {
			assertTrue(e.getErrorCode().equals(
					MobionException.NOT_ENOUGH_TOTAL_POINT));
		}

	}

	@Test()
	@Ignore
	public void addCoint() {
		try {
			MemberInfo mem = memberInfoDAO.findById(memAId);
			long totalPoint = mem.getTotalPoint();
			long availablePoint = mem.getAvailablePoint();
			
			transactionService.addPoint(memAId, point, systemId);
			
			mem = memberInfoDAO.findById(memAId);
			
			assertTrue("Total point must be added : " + point,totalPoint + point == mem.getTotalPoint());
			assertTrue("Available point must be added : " + point, availablePoint + point == mem.getAvailablePoint());
			
		} catch (Exception e) {
			log.error("",e);
			assertTrue("Exception ", false);
		}
	}
	
	@Test()
	@Ignore
	public void subtractCoint() {
		try {
			MemberInfo mem = memberInfoDAO.findById(memAId);
			long totalPoint = mem.getTotalPoint();
			long availablePoint = mem.getAvailablePoint();
			
			transactionService.subtractPoint(memAId, point, systemId);
			
			mem = memberInfoDAO.findById(memAId);
			
			assertTrue("Total point must be subtracted : " + point,totalPoint - point == mem.getTotalPoint());
			assertTrue("Available point must be subtracted : " + point, availablePoint - point == mem.getAvailablePoint());
			
		} catch (Exception e) {
			log.error("",e);
			assertTrue("Exception ", false);
			
		}
	}
	
	@Test
	@Ignore
	public void transferCoint() {
		try {
			
			MemberInfo sourceMem = memberInfoDAO.findById(memAId);
			long totalSourcePoint = sourceMem.getTotalPoint();
			long availableSourcePoint = sourceMem.getAvailablePoint();
			
			MemberInfo desMem = memberInfoDAO.findById(memBId);
			long totaldesPoint = desMem.getTotalPoint();
			long availabledesPoint = desMem.getAvailablePoint();
			
			transactionService.transferPoint(memAId, memBId, point,0, systemId,"","");

			sourceMem = memberInfoDAO.findById(memAId);
			assertTrue("Total point of source must be subtract by : " + point, totalSourcePoint - point == sourceMem.getTotalPoint());
			assertTrue("Available point of source must be subtract by : " + point, availableSourcePoint - point == sourceMem.getAvailablePoint());
			
			desMem = memberInfoDAO.findById(memBId);
			assertTrue("Total point of des must be added by : " + point, totaldesPoint + point == desMem.getTotalPoint());
			assertTrue("Available point of des must be added by : " + point, availabledesPoint + point == desMem.getAvailablePoint());
		} catch (Exception e) {
			log.error("",e);
			assertTrue("exception occur!!!",false);
		}
	}
	
	@Test
	@Ignore
	public void transferCointAndWaitConfirm() {
		try {
			//get startup point
			MemberInfo sourceMem = memberInfoDAO.findById(memAId);
			long totalSourcePoint = sourceMem.getTotalPoint();
			long availableSourcePoint = sourceMem.getAvailablePoint();
			
			MemberInfo desMem = memberInfoDAO.findById(memBId);
			long totaldesPoint = desMem.getTotalPoint();
			long availabledesPoint = desMem.getAvailablePoint();
			
			//execute transferPointAndWait (A -> B)
			TransactionsGroup group = transactionService.transferPointAndWait(memAId, memBId, point, 0, systemId,"","");

			sourceMem = memberInfoDAO.findById(memAId);
			assertTrue("Total point of source must be same ", totalSourcePoint == sourceMem.getTotalPoint());
			assertTrue("Available point of source must be subtract by : " + point, availableSourcePoint - point == sourceMem.getAvailablePoint());
			
			desMem = memberInfoDAO.findById(memBId);
			assertTrue("Total point of des must be same ", totaldesPoint == desMem.getTotalPoint());
			assertTrue("Available point of des must be same ", availabledesPoint == desMem.getAvailablePoint());
			
			//execute confirmTransferPointAndWait
			transactionService.confirmTransferPointAndWait(memAId, group.getTransGroupId());
			
			sourceMem = memberInfoDAO.findById(memAId);
			assertTrue("Total point of source must be subtract by : " + point, totalSourcePoint - point == sourceMem.getTotalPoint());
			assertTrue("Available point of source must be subtract by : " + point, availableSourcePoint - point == sourceMem.getAvailablePoint());
			
			desMem = memberInfoDAO.findById(memBId);
			assertTrue("Total point of des must be added by : " + point, totaldesPoint + point == desMem.getTotalPoint());
			assertTrue("Available point of des must be added by : " + point, availabledesPoint + point == desMem.getAvailablePoint());

			//execute confirmTransferPointAndWait again -> exception must be occur
			try{
				transactionService.confirmTransferPointAndWait(memAId, group.getTransGroupId());
			}catch(MobionException ex){
				assertTrue(ex.getErrorCode().equals(MobionException.TRANSACTION_PROCESSED));
			}
		} catch (Exception e) {
			log.error("",e);
			assertTrue("exception occur!!!",false);
		}
	}
	
	@Test
	@Ignore
	public void transferCointAndWaitCancel() {
		try {
			//get startup point
			MemberInfo sourceMem = memberInfoDAO.findById(memAId);
			long totalSourcePoint = sourceMem.getTotalPoint();
			long availableSourcePoint = sourceMem.getAvailablePoint();
			
			MemberInfo desMem = memberInfoDAO.findById(memBId);
			long totaldesPoint = desMem.getTotalPoint();
			long availabledesPoint = desMem.getAvailablePoint();
			
			//execute transferPointAndWait (A -> B)
			TransactionsGroup group = transactionService.transferPointAndWait(memAId, memBId, point,0, systemId, "","");

			sourceMem = memberInfoDAO.findById(memAId);
			assertTrue("Total point of source must be same ", totalSourcePoint == sourceMem.getTotalPoint());
			assertTrue("Available point of source must be subtract by : " + point, availableSourcePoint - point == sourceMem.getAvailablePoint());
			
			desMem = memberInfoDAO.findById(memBId);
			assertTrue("Total point of des must be same ", totaldesPoint == desMem.getTotalPoint());
			assertTrue("Available point of des must be same ", availabledesPoint == desMem.getAvailablePoint());
			
			//execute confirmTransferPointAndWait
			transactionService.cancelTransferPointAndWait(memAId, group.getTransGroupId());
			
			sourceMem = memberInfoDAO.findById(memAId);
			assertTrue("Total point of source must be same ", totalSourcePoint == sourceMem.getTotalPoint());
			assertTrue("Available point of source must be same ", availableSourcePoint == sourceMem.getAvailablePoint());
			
			desMem = memberInfoDAO.findById(memBId);
			assertTrue("Total point of des must be same ", totaldesPoint == desMem.getTotalPoint());
			assertTrue("Available point of des must be same ", availabledesPoint == desMem.getAvailablePoint());

			//execute cancelTransferPointAndWait again -> exception must be occur
			try{
				transactionService.cancelTransferPointAndWait(memAId, group.getTransGroupId());
			}catch(MobionException ex){
				assertTrue(ex.getErrorCode().equals(MobionException.TRANSACTION_PROCESSED));
			}
			
		} catch (Exception e) {
			log.error("",e);
			assertTrue("exception occur!!!",false);
		}
	}
}
