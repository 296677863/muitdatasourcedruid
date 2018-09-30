package com.lei.muitdatasourcedruid.dynamic.datasource.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import com.lei.muitdatasourcedruid.dynamic.datasource.annotation.DS;
import com.lei.muitdatasourcedruid.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

	public DynamicDataSourceAnnotationInterceptor() {
		super();
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
	    try {
            DynamicDataSourceContextHolder.setDataSourceLookupKey(determineDatasource(invocation));
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceLookupKey();
        }
	}

	private String determineDatasource(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		Class<?> declaringClass = method.getDeclaringClass();
		DS ds = method.isAnnotationPresent(DS.class) ? method.getAnnotation(DS.class)
				: AnnotationUtils.findAnnotation(declaringClass, DS.class);
		return ds.value();
	}

}
