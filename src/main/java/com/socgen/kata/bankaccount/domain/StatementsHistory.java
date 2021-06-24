package com.socgen.kata.bankaccount.domain;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mohamedmassmoudi
 *
 */
public class StatementsHistory {

	private List<StatementHistoryEntry> entries = new LinkedList<>();

	/**
	 * @param operationType
	 * @param operationDate
	 * @param amount
	 * @param balance
	 */
	void addEntry(EOperationType operationType, Instant operationDate, Amount amount, Balance balance) {
		this.entries.add(new StatementHistoryEntry(operationType, operationDate, amount, balance));
	}

}
