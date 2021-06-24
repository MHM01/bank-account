package com.socgen.kata.bankaccount.infrastructure;

import java.util.StringJoiner;

import com.socgen.kata.bankaccount.domain.StatementHistoryEntry;
import com.socgen.kata.bankaccount.domain.port.StatementHistoryPrinter;

/**
 * @author mohamedmassmoudi
 *
 */
public class StdStatementHistoryPrinter implements StatementHistoryPrinter{

	
	@Override
	public void print(StatementHistoryEntry statementHistoryEntry) {
	     System.out.println(formatStatementHistoryEntry(statementHistoryEntry));
	}

	/**
	 * @param statementHistoryEntry
	 * @return
	 */
	private static String formatStatementHistoryEntry(StatementHistoryEntry statementHistoryEntry) {
		return new StringJoiner(" | ").add("Operation='" + statementHistoryEntry.operationType() + "'")
				.add("Date='" + statementHistoryEntry.operationDate() + "'")
				.add("Amount='" + statementHistoryEntry.amount().valueInCents() + "'")
				.add("Balance='" + statementHistoryEntry.balance().valueInCents() + "'").toString();
	}
}
