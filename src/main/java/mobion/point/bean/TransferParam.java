package mobion.point.bean;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

public class TransferParam extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3348138880981930487L;

	@FormParam("source_id")
	@NotNull
	String sourceId;
	
	@FormParam("des_id")
	@NotNull
	String desId;
	
	@FormParam("point")
	@NotNull(message="point canot be null")
	Long point;
	
	@FormParam("extend_point")
	@DefaultValue("0")
	Long extendPoint;
	
	@FormParam("desc")
	@DefaultValue("")
	String desc;

	@FormParam("ex_desc")
	@DefaultValue("")
	String exDesc;
	
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getDesId() {
		return desId;
	}

	public void setDesId(String desId) {
		this.desId = desId;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Long getExtendPoint() {
		return extendPoint;
	}

	public void setExtendPoint(Long extendPoint) {
		this.extendPoint = extendPoint;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExDesc() {
		return exDesc;
	}

	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}
	
}
