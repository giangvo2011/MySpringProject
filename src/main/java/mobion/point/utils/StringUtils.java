package mobion.point.utils;

import java.util.UUID;

public class StringUtils {
	public static String genId(){
		return UUID.randomUUID().toString();
	}
}
