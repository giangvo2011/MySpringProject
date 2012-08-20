package mobion.point.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_type")
public class MemberType extends BaseBean {

	private static final long serialVersionUID = 617783387167978476L;
	
	@Id
	@Column(name = "mem_type_id", unique = true, nullable = false)
	private byte memTypeId;
	
	@Column(name = "mem_type_name", nullable = false, length = 50)
	private String memTypeName;

	public byte getMemTypeId() {
		return memTypeId;
	}

	public void setMemTypeId(byte memTypeId) {
		this.memTypeId = memTypeId;
	}

	public String getMemTypeName() {
		return memTypeName;
	}

	public void setMemTypeName(String memTypeName) {
		this.memTypeName = memTypeName;
	}
}
