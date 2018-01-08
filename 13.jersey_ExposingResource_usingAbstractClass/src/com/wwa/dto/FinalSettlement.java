package com.wwa.dto;

public class FinalSettlement {
	private int walletID;
	private String username;
	private float balance;
	private String status;

	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
