package mobion.point.bean;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions_type")
public class TransactionsType extends BaseBean{

	private static final long serialVersionUID = -1660667626145141596L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trans_type_id", unique = true, nullable = false)
	private Integer transTypeId;
	
	@Column(name = "trans_type_name", nullable = false, length = 50)	
	private String transTypeName;

	public Integer getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
	}

	public String getTransTypeName() {
		return transTypeName;
	}

	public void setTransTypeName(String transTypeName) {
		this.transTypeName = transTypeName;
	}
}
