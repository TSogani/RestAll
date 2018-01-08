package com.wwc.test;

import javax.xml.bind.JAXBException;

import com.wwc.dto.Receipt;
import com.wwc.dto.Subscriber;
import com.wwc.invoker.IdeaProviderResourceInvoker;

public class IdeaProviderResourceClientTest {
	public static void main(String[] args) throws JAXBException {
		Subscriber subscriber = null;
		IdeaProviderResourceInvoker ideaProviderResourceInvoker = null;
		subscriber = new Subscriber();
		
		subscriber.setMobile("9090909090");
		subscriber.setPlan("199-plan");
		subscriber.setAmount("199");
		
		ideaProviderResourceInvoker = ideaProviderResourceInvoker.getIdeaProviderResourceInvokerInstance();
		
		ideaProviderResourceInvoker.open();
		Receipt receipt = ideaProviderResourceInvoker.doRecharge(subscriber);
		System.out.println("ReceiptNo : "+receipt.getReceiptNo()+"\nMobile : "+receipt.getMobile()+"\nBalance : "+receipt.getBalance()+"\nStatus : "+receipt.getStatus());
		ideaProviderResourceInvoker.close();
	}
}
