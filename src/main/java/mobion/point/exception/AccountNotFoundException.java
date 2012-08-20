package mobion.point.exception;

public class AccountNotFoundException extends MobionException{

	
	private static final long serialVersionUID = -3154094913595790857L;
	
	String errorCode;
	String desc;
	Exception e;

	public AccountNotFoundException() {
		super(ACCOUNT_NOT_FOUND);
		
	}

	public AccountNotFoundException(String desc) {
		super(ACCOUNT_NOT_FOUND,desc);
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
