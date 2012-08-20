package mobion.point.rest.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class RestPreprocessor {

	@Pointcut("execution(public * mobion.point.rest.*.*(..))")
	void pointcutAllRestFunctions(){}


	@Around(value = "pointcutAllRestFunctions()")
	public Object intercepRestApi(ProceedingJoinPoint point) throws Throwable{
		//do auth here
		return point.proceed();
	}
	
}
