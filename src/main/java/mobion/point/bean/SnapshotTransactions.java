package mobion.point.bean;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "snapshot_transactions")
public class SnapshotTransactions extends BaseBean {

	public enum Type {
		BEFORE_TRANS_GROUP(1), AFTER_TRANS_GROUP(4);

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
	
	private static final long serialVersionUID = -7470964417808853931L;

	public SnapshotTransactions(){}
	
	public SnapshotTransactions(Date snapDate, Date datetime,
			Integer snapTransTypeId, Long transGroupId, String memId,
			long snapshotTotalPoint, long snapshotAvailablePoint) {
		this.snapDate = snapDate;
		this.datetime = datetime;
		this.snapTransTypeId = snapTransTypeId;
		this.transGroupId = transGroupId;
		this.memId = memId;
		this.snapshotTotalPoint = snapshotTotalPoint;
		this.snapshotAvailablePoint = snapshotAvailablePoint;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "snap_trans_id", unique = true, nullable = false)
	private Long snapTransId;
	
	@Column(name = "snap_date", nullable = false, length = 10)
	private Date snapDate;
	
	@Column(name = "datetime", nullable = false, length = 19)
	private Date datetime;
	
	@Column(name = "snap_trans_type_id", nullable = false)
	private Integer snapTransTypeId;
	
	@Column(name = "trans_group_id", nullable = false)
	private Long transGroupId;
	
	@Column(name = "mem_id", nullable = false)
	private String memId;
	
	@Column(name = "snapshot_total_point", nullable = false, precision = 10, scale = 0)
	private long snapshotTotalPoint;
	
	@Column(name = "snapshot_available_point", nullable = false, precision = 10, scale = 0)
	private long snapshotAvailablePoint;

	public Long getSnapTransId() {
		return snapTransId;
	}

	public void setSnapTransId(Long snapTransId) {
		this.snapTransId = snapTransId;
	}

	public Date getSnapDate() {
		return snapDate;
	}

	public void setSnapDate(Date snapDate) {
		this.snapDate = snapDate;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getSnapTransTypeId() {
		return snapTransTypeId;
	}

	public void setSnapTransTypeId(Integer snapTransTypeId) {
		this.snapTransTypeId = snapTransTypeId;
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

	public long getSnapshotTotalPoint() {
		return snapshotTotalPoint;
	}

	public void setSnapshotTotalPoint(long snapshotTotalPoint) {
		this.snapshotTotalPoint = snapshotTotalPoint;
	}

	public long getSnapshotAvailablePoint() {
		return snapshotAvailablePoint;
	}

	public void setSnapshotAvailablePoint(long snapshotAvailablePoint) {
		this.snapshotAvailablePoint = snapshotAvailablePoint;
	}
}
