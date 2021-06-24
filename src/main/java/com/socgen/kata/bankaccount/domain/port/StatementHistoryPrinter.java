package com.socgen.kata.bankaccount.domain.port;

import com.socgen.kata.bankaccount.domain.StatementHistoryEntry;

/**
 * @author mohamedmassmoudi
 *
 */
public interface StatementHistoryPrinter {
	
	/**
	 * @param statementHistoryEntry
	 */
	void print(StatementHistoryEntry statementHistoryEntry);
}
