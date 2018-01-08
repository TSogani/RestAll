package com.wwc.test;

import com.wwc.invoker.CabResourceInvoker;

public class CabResourceClientTest {
	public static void main(String[] args) {
		CabResourceInvoker invoker = CabResourceInvoker.getCabResourceInvokerInstance();
		invoker.open();
		
		//this is for send header and cookie
		/*String result = invoker.bookCab("0116", "dri123");*/
		
		//this is for send form 
		String result = invoker.bookCabForm("hyderabad", "jaipur", "sedan");
		System.out.println(result);
		invoker.close();
	}
}
