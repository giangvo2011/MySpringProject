package mobion.point.bean;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transactions extends BaseBean {

	public enum Type {
		ADD_POINT_RECEIVED(1),
		ADD_POINT_SEND(2),
		SUBTRACT_POINT_RECEIVED(3),
		SUBTRACT_POINT_SEND(4),
		TRANSFER_POINT_RECEIVED(5),
		TRANSFER_POINT_SEND(6),
		TRANSFER_POINT_WAIT_RECEIVED(7),
		TRANSFER_POINT_WAIT_SEND(8),
		TRANSFER_POINT_WAIT_SEND_CONFIRM(9),
		TRANSFER_POINT_WAIT_SEND_CANCEL(10);
		
		private int value;
		
		private Type(int value){
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}
	
	private static final long serialVersionUID = 3894688473349604811L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trans_id", unique = true, nullable = false)
	private Long transId;
	
	@Column(name = "trans_date", nullable = false, length = 10)
	private Date transDate;

	@Column(name = "datetime", nullable = false, length = 19)
	private Date datetime;
	
	@Column(name = "trans_type_id", nullable = false)
	private Integer transTypeId;
	
	@Column(name = "trans_group_id", nullable = false)
	private Long transGroupId;
	
	@Column(name = "mem_id", nullable = false)
	private String memId;
	
	@Column(name = "description", nullable = false, length = 500)
	private String description;
	
	@Column(name = "trans_point", nullable = false, precision = 10, scale = 0)
	private long transPoint;
	
	@Column(name = "old_total_point", nullable = false, precision = 10, scale = 0)
	private long oldTotalPoint;
	
	@Column(name = "new_total_point", nullable = false, precision = 10, scale = 0)
	private long newTotalPoint;
	
	@Column(name = "old_available_point", nullable = false, precision = 10, scale = 0)
	private long oldAvailablePoint;
	
	@Column(name = "new_available_point", nullable = false, precision = 10, scale = 0)
	private long newAvailablePoint;

	
	public Transactions(){
		
	}
	
	public Transactions(Date transDate, Date datetime,
			Integer transTypeId, Long transGroupId, String memId,
			String description, long transPoint, long oldTotalPoint,
			long newTotalPoint, long oldAvailablePoint, long newAvailablePoint) {
	
		this.transDate = transDate;
		this.datetime = datetime;
		this.transTypeId = transTypeId;
		this.transGroupId = transGroupId;
		this.memId = memId;
		this.description = description;
		this.transPoint = transPoint;
		this.oldTotalPoint = oldTotalPoint;
		this.newTotalPoint = newTotalPoint;
		this.oldAvailablePoint = oldAvailablePoint;
		this.newAvailablePoint = newAvailablePoint;
	}

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
	}

	public Long getTransGroupId() {
		return transGroupId;
	}

	public void setTransGroupId(Long transGroupId) {
		this.transGroupId = transGroupId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTransPoint() {
		return transPoint;
	}

	public void setTransPoint(long transPoint) {
		this.transPoint = transPoint;
	}

	public long getOldTotalPoint() {
		return oldTotalPoint;
	}

	public void setOldTotalPoint(long oldTotalPoint) {
		this.oldTotalPoint = oldTotalPoint;
	}

	public long getNewTotalPoint() {
		return newTotalPoint;
	}

	public void setNewTotalPoint(long newTotalPoint) {
		this.newTotalPoint = newTotalPoint;
	}

	public long getOldAvailablePoint() {
		return oldAvailablePoint;
	}

	public void setOldAvailablePoint(long oldAvailablePoint) {
		this.oldAvailablePoint = oldAvailablePoint;
	}

	public long getNewAvailablePoint() {
		return newAvailablePoint;
	}

	public void setNewAvailablePoint(long newAvailablePoint) {
		this.newAvailablePoint = newAvailablePoint;
	}
}
