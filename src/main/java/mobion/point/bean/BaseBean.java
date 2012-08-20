package mobion.point.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import mobion.point.exception.MobionException;

@JsonSerialize(include=Inclusion.NON_NULL)
public class BaseBean implements Serializable {

	private static final long serialVersionUID = -5537934927558510506L;
	String status = "success";
	String errorCode;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append("\n{");
		result.append(newLine);

		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			field.setAccessible(true);
			
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			try {
				if(field.get(this) != null){
					result.append("\t" + field.getName());
					result.append(":");
					result.append(field.get(this));
					result.append(newLine);
				}
				
			} catch (IllegalAccessException ex) {
				
			}
			
		}
		result.append("}");
		
		return result.toString();
	}
	
	public static <T extends BaseBean> T update(T input, T target) throws MobionException{
		try {
			Field[] fields = input.getClass().getDeclaredFields();

			// print field names paired with their values
			for (Field field : fields) {
				field.setAccessible(true);

				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}

				Object value = field.get(input);
				if (value != null) {
					Field tmp = target.getClass().getDeclaredField(field.getName());
					tmp.setAccessible(true);
					tmp.set(target, value);
				}
			}
			
			return target;
		} catch (Exception e) {
			throw new MobionException(e);
		}
	}
	

}
