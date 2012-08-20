package mobion.point.rest.utils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import mobion.point.exception.MobionException;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jettison.json.JSONObject;

@Provider
public class MobionExceptionMapper implements ExceptionMapper<Exception>{
	
	public Response toResponse(Exception exception) {
		Status status=toStatusError(exception);
		return Response.ok(status).status(200)
				.header(RestConstants.HEADER_X_MP_RESPONSE_STATUS,RestConstants.STATUS_FAILURE)
				.header(RestConstants.HEADER_X_MP_RESPONSE_CODE,status.errorCode)
				.header("Access-Control-Allow-Origin", "*")
				.type(MediaType.APPLICATION_JSON).build();		
	}
	
	public Status toStatusError(Throwable e){
		Status status=new Status();
		
		if (e instanceof MobionException) {
			MobionException ex=(MobionException) e;
			status.setErrorCode(ex.getErrorCode());
			status.setErrorCause(e.getMessage());
			
		}else if (e instanceof javax.validation.ValidationException ){		
			status.setErrorCode(MobionException.INVALID_PARAM);
			status.setErrorCause(e.getMessage());
			
		}else {			
			status.setErrorCode(MobionException.UNKNOW_ERROR);
			status.setErrorDetail(e.toString());
		}
		return status;
	}
	
		
	@JsonPropertyOrder({"status","error_code","error_detail","error_cause"})
	@JsonSerialize(include=Inclusion.NON_NULL)
	public static class Status{
		public static final String STATUS_FAILURE="failure";
		
		private String status=STATUS_FAILURE;
				
		private String errorCode;
				
		private String	errorDetail;
		
		private String	errorCause;
		
		private JSONObject retObj;
		
		public Status() {

		}
		
		public Status(String errorCode,String errorDetail) {
			this.errorCode=errorCode;
			this.errorDetail=errorDetail;
			this.errorCause=errorDetail;
		}
		
		public String getStatus() {
			return status;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		
		@JsonProperty("error_code")
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorDetail(String errorDetail) {
			this.errorDetail = errorDetail;
		}
		@JsonProperty("error_detail")
		public String getErrorDetail() {
			return errorDetail;
		}
		@JsonProperty("error_cause")
		public String getErrorCause() {
			return errorCause;
		}
		public void setErrorCause(String errorCause) {
			this.errorCause = errorCause;
		}
		
		@JsonProperty("error_description")
		public JSONObject getRetObj() {
			return retObj;
		}
		
		public void setRetObj(JSONObject retObj) {
			this.retObj = retObj;
		}
	}
}
