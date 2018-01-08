package com.cpc.convertor;

import javax.ws.rs.ext.ParamConverter;

import com.cpc.dto.Cheque;

public class ChequeParamConvertor implements ParamConverter<Cheque>{

	@Override
	public Cheque fromString(String param) {
		System.out.println("fromString");
		String[] tokens = null;
		Cheque cheque = null;
		
		tokens = param.split("-");
		cheque = new Cheque();
		
		cheque.setIfscCode(tokens[0]);
		cheque.setChequeNo(Integer.parseInt(tokens[1]));
		
		return cheque;
	}

	@Override
	public String toString(Cheque cheque) {
		System.out.println("toString");
		return cheque.getIfscCode()+" : "+cheque.getChequeNo();
	}
}
