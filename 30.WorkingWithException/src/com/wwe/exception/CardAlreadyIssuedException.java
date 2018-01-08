package com.wwe.exception;

public class CardAlreadyIssuedException extends Throwable{
	
	public CardAlreadyIssuedException(){
		super();
	}
	
	public CardAlreadyIssuedException(String arg){
		super(arg);
	}
	
	public CardAlreadyIssuedException(Throwable arg){
		super(arg);
	}
}
