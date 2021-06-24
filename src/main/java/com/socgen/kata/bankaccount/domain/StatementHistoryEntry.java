package com.socgen.kata.bankaccount.domain;

import java.time.Instant;

/**
 * @author mohamedmassmoudi
 *
 */
public record StatementHistoryEntry(EOperationType operationType, Instant operationDate, Amount amount, Balance balance) {
	
}
