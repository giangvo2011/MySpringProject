package mobion.point.bean;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "original_system")
public class OriginalSystem extends BaseBean {

	private static final long serialVersionUID = -7322363372206114663L;

	public enum Type {
		MINE(1),
		MOBION_COM(2),
		EC(3);
		
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
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "org_sys_id", unique = true, nullable = false)
	private Integer orgSysId;
	
	@Column(name = "org_sys_name", nullable = false, length = 50)
	private String orgSysName;

	public Integer getOrgSysId() {
		return orgSysId;
	}

	public void setOrgSysId(Integer orgSysId) {
		this.orgSysId = orgSysId;
	}

	public String getOrgSysName() {
		return orgSysName;
	}

	public void setOrgSysName(String orgSysName) {
		this.orgSysName = orgSysName;
	}
}
