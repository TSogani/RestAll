package com.wwe.exception;

public class CardAlreadyIssuedException extends Throwable{
	public CardAlreadyIssuedException() {
		super();
	}
	public CardAlreadyIssuedException(String arg) {
		// TODO Auto-generated constructor stub
		super(arg);
	}
	public CardAlreadyIssuedException(Throwable te) {
		super(te);
	}
}
