package mobion.point.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "member_info")
public class MemberInfo extends BaseBean{

	public enum Type {
		MEM_TYPE_USER(1),
		MEM_TYPE_CLIENT(2),
		MEM_TYPE_GNT_SYSTEM(3);		
		
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
	
	private static final long serialVersionUID = 388075490814274031L;
	
	@Id
	@Column(name = "mem_id", unique = true, nullable = false, length = 50)
	@FormParam("id")
	private String memId;

	@Column(name = "mem_name")
	@FormParam("mem_name")
	private String memName;

	@Column(name = "mem_type_id")
	@FormParam("mem_type_id")
	@DefaultValue("1")
	private Integer memTypeId;

	@Column(name = "org_sys_id")
	@FormParam("org_sys_id")
	private Integer orgSystemId;

	@Column(name = "org_user_id")
	@FormParam("org_user_id")
	private String orgUserId;

	@Column(name = "total_point", nullable = false,  columnDefinition = "DECIMAL(10,0) default 0")
	private Long totalPoint = new Long(0);
	
	@Column(name = "available_point", nullable = false, columnDefinition = "DECIMAL(10,0) default 0")
	private Long availablePoint = new Long(0);

	@Column(name = "lasted_trans_id")
	private String lastTransId;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateddDate;

	
	
	public MemberInfo(Integer memTypeId) {
		this.memTypeId = memTypeId;
	}

	public MemberInfo(){
		this.memTypeId = Type.MEM_TYPE_USER.getValue();
		this.updateddDate = new Date();
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public Integer getMemTypeId() {
		return memTypeId;
	}

	public void setMemTypeId(Integer memTypeId) {
		this.memTypeId = memTypeId;
	}

	public Integer getOrgSystemId() {
		return orgSystemId;
	}

	public void setOrgSystemId(Integer orgSystemId) {
		this.orgSystemId = orgSystemId;
	}

	public String getOrgUserId() {
		return orgUserId;
	}

	public void setOrgUserId(String orgUserId) {
		this.orgUserId = orgUserId;
	}

	public Long getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Long totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	public Long getAvailablePoint() {
		return availablePoint;
	}

	public void setAvailablePoint(Long availablePoint) {
		this.availablePoint = availablePoint;
	}

	public String getLastTransId() {
		return lastTransId;
	}

	public void setLastTransId(String lastTransId) {
		this.lastTransId = lastTransId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateddDate() {
		return updateddDate;
	}

	public void setUpdateddDate(Date updateddDate) {
		this.updateddDate = updateddDate;
	}
}
