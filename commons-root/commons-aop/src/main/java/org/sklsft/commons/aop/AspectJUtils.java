package org.sklsft.commons.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class AspectJUtils {

	public static Method getProxiedMethod(ProceedingJoinPoint joinPoint) {
		return ((MethodSignature) joinPoint.getSignature()).getMethod();
	}
	
	public static Method getProxiedMethodImplementation(ProceedingJoinPoint joinPoint) {
		Method proxiedMethod = getProxiedMethod(joinPoint);
		Object proxied = joinPoint.getTarget();
		if (proxiedMethod.getDeclaringClass().equals(proxied.getClass())) {
			return proxiedMethod;
		}
		Method[] methods = proxied.getClass().getMethods();
		for (Method method:methods) {
			if (method.getName().equals(proxiedMethod.getName()) && Arrays.equals(method.getParameterTypes(), proxiedMethod.getParameterTypes())) {
				return method;
			}
		}
		return null;
	}
}
