package model.entities;

import model.exceptions.DomainException;

public class Account {

	private Integer number;
	private String holder;
	private Double balance;
	private Double withdrawLimit;

	public Account() {
	}

	public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	public Integer getNumber() {
		return number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return balance;
	}

	public Double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void deposit(Double amount) {
		if (amount < 0) {
			throw new DomainException("This amount is invalid");
		}
		balance += amount;
	}

	public void withdraw(Double amount) {
		if (amount > withdrawLimit) {
			throw new DomainException("This amount exceeds withdraw limit");
		}
		if (amount > balance) {
			throw new DomainException("This amount exceeds balance quantity");
		}
		balance -= amount;

	}

	@Override
	public String toString() {
		return "New balance: "+ String.format("%.2f", balance);
	}
	
	

}
