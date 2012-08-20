package mobion.point.exception;

public class MobionException extends Exception{

	public static final String UNKNOW_ERROR = "9999";
	public static final String SYSTEM_ERROR = "0001";
	public static final String INVALID_PARAM = "0002";
	public static final String ACCOUNT_NOT_FOUND = "0003";
	public static final String NOT_ENOUGH_TOTAL_POINT = "0004";
	public static final String NOT_ENOUGH_AVAILABLE_POINT = "0004";
	public static final String INVALID_TRANS_TYPE = "0005";
	
	public static final String TRANSACTION_PROCESSED = "0010";
	private static final long serialVersionUID = -3154094913595790857L;
	
	String errorCode;
	String desc;
	Exception e;

	public MobionException(Exception e) {
		this.e = e;
	}
	
	public MobionException(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public MobionException(String errorCode, String desc) {
		this.errorCode = errorCode;
		this.desc = desc;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
