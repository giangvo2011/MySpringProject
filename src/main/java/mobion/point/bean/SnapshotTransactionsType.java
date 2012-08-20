package mobion.point.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "snapshot_transactions_type")
public class SnapshotTransactionsType extends BaseBean {

	private static final long serialVersionUID = -1959204665477033546L;

	@Id
	@Column(name = "snap_trans_type_id", unique = true, nullable = false)
	private Integer snapTransTypeId;
	
	@Column(name = "snap_trans_type_name", nullable = false, length = 50)
	private String snapTransTypeName;

	public Integer getSnapTransTypeId() {
		return snapTransTypeId;
	}

	public void setSnapTransTypeId(Integer snapTransTypeId) {
		this.snapTransTypeId = snapTransTypeId;
	}

	public String getSnapTransTypeName() {
		return snapTransTypeName;
	}

	public void setSnapTransTypeName(String snapTransTypeName) {
		this.snapTransTypeName = snapTransTypeName;
	}
}
