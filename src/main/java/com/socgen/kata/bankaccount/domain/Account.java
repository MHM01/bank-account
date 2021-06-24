package com.socgen.kata.bankaccount.domain;

/**
 * @author mohamedmassmoudi
 *
 */
public class Account {
	
	private Balance balance = new Balance(0L);
	
	/**
	 * @param amount
	 */
	public void deposit(Amount amount) {
		balance = balance.update(EOperationType.DEPOSIT, amount);
	}
	
	/**
	 * @return
	 */
	public Balance getBalance() {
		return this.balance;
	}
	
}
