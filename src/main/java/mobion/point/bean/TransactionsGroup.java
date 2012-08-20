package mobion.point.bean;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@Entity
@Table(name = "transactions_group")
@JsonSerialize(include=Inclusion.NON_NULL)
public class TransactionsGroup extends BaseBean {

	private static final long serialVersionUID = 8374673871855478903L;

	public enum Type {
		ADD(1),
		SUBTRACT(2),
		TRANSFER(3),
		TRANSFER_WAIT(4);
		
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
	
	public enum Status {
		DONE(1),
		WAIT(2),
		CANCELED(3);

		private int value;
		
		private Status(int value){
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}
	


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trans_group_id", unique = true, nullable = false)
	
	private Long transGroupId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "trans_group_date", nullable = false, length = 10)
	private Date transGroupDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datetime", nullable = false, length = 19)
	private Date datetime;

	@Column(name = "trans_group_type_id", nullable = false)
	private Integer transGroupTypeId;
	
	@Column(name = "trans_point", nullable = false, precision = 10, scale = 0)
	private long transPoint;
	
	@Column(name = "extend_point", nullable = false, precision = 10, scale = 0)
	private long extendPoint;
	
	@Column(name = "description", nullable = false, length = 500)
	private String description;
	
	@Column(name = "extend_point_desc", nullable = false, length = 500)
	private String extendPointDesc;
	
	@Column(name = "source_mem_id", nullable = false, length = 50)
	private String sourceMemId;
	
	@Column(name = "destination_mem_id", nullable = false, length = 50)
	private String destinationMemId;
	
	@Column(name = "org_sys_id", nullable = false)
	private Integer orgSysId;
	
	@Column(name = "status", nullable = false)
	private Integer transStatus = Status.WAIT.getValue();

	public TransactionsGroup(Date transGroupDate,
			Date datetime, Integer transGroupTypeId, long transPoint,long extendPoint,
			String description, String extendPointDesc, String sourceMemId, String destinationMemId,
			Integer orgSysId, Integer transStatus) {
		
		this.transGroupDate = transGroupDate;
		this.datetime = datetime;
		this.transGroupTypeId = transGroupTypeId;
		this.transPoint = transPoint;
		this.extendPoint = extendPoint;
		this.extendPointDesc = extendPointDesc;
		this.description = description;
		this.sourceMemId = sourceMemId;
		this.destinationMemId = destinationMemId;
		this.orgSysId = orgSysId;
		this.transStatus = transStatus;
	}

	
	public TransactionsGroup() {
		
	}

	public TransactionsGroup(Long transGroupId) {
		this.transGroupId = transGroupId;
	}
	
	public Long getTransGroupId() {
		return transGroupId;
	}

	public void setTransGroupId(Long transGroupId) {
		this.transGroupId = transGroupId;
	}

	public Date getTransGroupDate() {
		return transGroupDate;
	}

	public void setTransGroupDate(Date transGroupDate) {
		this.transGroupDate = transGroupDate;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getTransGroupTypeId() {
		return transGroupTypeId;
	}

	public void setTransGroupTypeId(Integer transGroupTypeId) {
		this.transGroupTypeId = transGroupTypeId;
	}

	public long getTransPoint() {
		return transPoint;
	}

	public void setTransPoint(long transPoint) {
		this.transPoint = transPoint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSourceMemId() {
		return sourceMemId;
	}

	public void setSourceMemId(String sourceMemId) {
		this.sourceMemId = sourceMemId;
	}

	public String getDestinationMemId() {
		return destinationMemId;
	}

	public void setDestinationMemId(String destinationMemId) {
		this.destinationMemId = destinationMemId;
	}

	public Integer getOrgSysId() {
		return orgSysId;
	}

	public void setOrgSysId(Integer orgSysId) {
		this.orgSysId = orgSysId;
	}

	public Integer getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(Integer transStatus) {
		this.transStatus = transStatus;
	}

	public long getExtendPoint() {
		return extendPoint;
	}

	public void setExtendPoint(long extendPoint) {
		this.extendPoint = extendPoint;
	}

	public String getExtendPointDesc() {
		return extendPointDesc;
	}

	public void setExtendPointDesc(String extendPointDesc) {
		this.extendPointDesc = extendPointDesc;
	}
}
