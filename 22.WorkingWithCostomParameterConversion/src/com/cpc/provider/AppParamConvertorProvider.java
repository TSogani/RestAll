package com.cpc.provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.cpc.convertor.ChequeParamConvertor;
import com.cpc.dto.Cheque;

@Provider
public class AppParamConvertorProvider implements ParamConverterProvider{

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> classType, Type rawType, Annotation[] annotations) {
		if(classType.isAssignableFrom(Cheque.class)){
			return (ParamConverter<T>)new ChequeParamConvertor();
		}
		return null;
	}

}
