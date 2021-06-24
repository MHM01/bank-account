package com.socgen.kata.bankaccount.domain;

import java.time.Clock;
import java.time.Instant;

import com.socgen.kata.bankaccount.domain.port.StatementHistoryPrinter;


/**
 * @author mohamedmassmoudi
 *
 */
public class Account {
	
	private final Clock clock;
	private final StatementsHistory history = new StatementsHistory();
	private Balance balance = new Balance(0L);
	
	
	/**
	 * @param clock
	 */
	public Account(Clock clock) {
		super();
		this.clock = clock;
	}
	

	/**
	 * @param amount
	 */
	public void deposit(Amount amount) {
		balance = balance.update(EOperationType.DEPOSIT, amount);
		addToHistory(EOperationType.DEPOSIT, clock.instant(), amount, balance);
	}
	
	/**
	 * @param amount
	 */
	public void withdraw(Amount amount) {
		balance = balance.update(EOperationType.WITHDRAWAL, amount);
		addToHistory(EOperationType.WITHDRAWAL, clock.instant(), amount, balance);
	}
	
	/**
	 * @param statementHistoryPrinter
	 */
	public void printHistory(StatementHistoryPrinter statementHistoryPrinter) {
		throw new UnsupportedOperationException("This operation isn't implemented yet"); 
	}
	
	
	/**
	 * @param operationType
	 * @param operationDate
	 * @param amount
	 * @param balance
	 */
	private void addToHistory(EOperationType operationType, Instant operationDate,Amount amount, Balance balance) {
		history.addEntry(operationType, operationDate, amount, balance);
	}	
	
	
	/**
	 * @return
	 */
	public Balance getBalance() {
		return this.balance;
	}
	
}
