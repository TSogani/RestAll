package com.wwa.dto;

public class AddMoney {
	private int walletID;
	private String fromAccount;
	private float amount;

	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
