package mobion.point.bean;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions_group_type")
public class TransactionsGroupType extends BaseBean {

	private static final long serialVersionUID = 3774045873854713915L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trans_group_type_id", unique = true, nullable = false)
	private Integer transGroupTypeId;
	
	@Column(name = "trans_group_type_name", nullable = false, length = 50)
	private String transGroupTypeName;

	public Integer getTransGroupTypeId() {
		return transGroupTypeId;
	}

	public void setTransGroupTypeId(Integer transGroupTypeId) {
		this.transGroupTypeId = transGroupTypeId;
	}

	public String getTransGroupTypeName() {
		return transGroupTypeName;
	}

	public void setTransGroupTypeName(String transGroupTypeName) {
		this.transGroupTypeName = transGroupTypeName;
	}
}
