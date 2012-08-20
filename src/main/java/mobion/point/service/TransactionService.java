package mobion.point.service;

import mobion.point.bean.BaseBean;
import mobion.point.bean.TransactionsGroup;
import mobion.point.exception.MobionException;

public interface TransactionService {
	/**
	 * When add point of A
	 * - Check if A is user account -> check if A is enough point to send
	 * - Add transaction group with status DONE
	 * 
	 * - Snapshot A's balance before update
	 * - Update A's balance (+total point & +available point)
	 * - Add A's transaction
	 * - Snapshot A's balance after update
	 * 
	 * - Snapshot GNT's balance before update
	 * - Update GNT's balance (-total point & -available point)
	 * - Add GNT's transaction
	 * - Snapshot GNT's balance after update
	 * 
	 * @param memId
	 * @param point
	 * @param systemId
	 * @return
	 * @throws MobionException
	 */
	public BaseBean addPoint(String memId, long point, int systemId) throws MobionException;
	
	/**
	 * When substractPoint of A
	 * - Check if A is user account -> check if A is enough point to send
	 * - Add transaction group with status DONE
	 * 
	 * - Snapshot A's balance before update
	 * - Update A's balance (-total point & -available point)
	 * - Add A's transaction
	 * - Snapshot A's balance after update
	 * 
	 * - Snapshot GNT's balance before update
	 * - Update GNT's balance (+total point & +available point)
	 * - Add GNT's transaction
	 * - Snapshot GNT's balance after update
	 * 
	 * @param memId
	 * @param point
	 * @param systemId
	 * @return
	 * @throws MobionException
	 */
	public BaseBean subtractPoint(String memId, long point, int systemId) throws MobionException;
	
	/**
	 * 
	 * When transfer point from A -> B we go through these steps:
	 * - Check if A is user account -> check if A is enough point to send
	 * - Add transaction group with status DONE
	 * 
	 * - Snapshot A's balance before update
	 * - Update A's balance (-total point & -available point)
	 * - Add A's transaction
	 * - Snapshot A's balance after update
	 * 
	 * - Snapshot B's balance before update
	 * - Update B's balance (+total point & +available point)
	 * - Add B's transaction
	 * - Snapshot B's balance after update
	 *  
	 * @param memIdFrom
	 * @param memIdTo
	 * @param point
	 * @param systemId
	 * 
	 * @return
	 * @throws MobionException
	 */
	public BaseBean transferPoint(String memIdFrom, String memIdTo, long point, long extendPoint, int systemId, String desc, String exDesc) throws MobionException;
	
	
	/**
	 * 
	 * When transfer point & wait from A -> B we go through these steps:
	 * - Check if A is user account -> check if A is enough point to send
	 * - Add transaction group with status = WAIT
	 * 
	 * - Snapshot A's balance before update
	 * - Update A's balance ( just -available point)
	 * - Add A's transaction
	 * - Snapshot A's balance after update
	 *  
	 * @param memIdFrom
	 * @param memIdTo
	 * @param point
	 * @param systemId
	 * @return TransactionsGroup with id;
	 * 
	 * @throws MobionException
	 */
	public TransactionsGroup transferPointAndWait(String memIdFrom, String memIdTo, long point, long extendPoint, int systemId, String desc, String exDesc) throws MobionException;
	
	
	/**
	 * 
	 * When confirm transfer point & wait from A -> B we go through these steps:
	 * - Get source/des/point infos from transGroupId
	 * 
	 * - Snapshot A's balance before update
	 * - Update A's balance ( -totalpoint)
	 * - Add A's transaction
	 * - Snapshot A's balance after update
	 *  
	 * - Snapshot B's balance before update
	 * - Update B's balance (+total point & +available point)
	 * - Add B's transaction
	 * - Snapshot B's balance after update
	 * 
	 * - Update status of transaction group -> DONE
	 * @param sourceMemId
	 * @param transGroupId
	 * @return
	 * @throws MobionException
	 */
	public BaseBean confirmTransferPointAndWait(String sourceMemId, long transGroupId) throws MobionException;
	
	/**
	 * When cancel transfer point & wait from A -> B we go through these steps:
	 * - Get source/des/point infos from transGroupId
	 * 
	 * - Snapshot A's balance before update
	 * - Update A's balance ( revert available point : +available point)
	 * - Add A's transaction
	 * - Snapshot A's balance after update
	 * 
	 * - Update status of transaction group -> CANCEL
	 * @param sourceMemId
	 * @param transGroupId
	 * @return
	 * @throws MobionException
	 */
	public BaseBean cancelTransferPointAndWait(String sourceMemId, long transGroupId) throws MobionException;
}
