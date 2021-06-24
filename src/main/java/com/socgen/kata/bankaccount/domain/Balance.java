package com.socgen.kata.bankaccount.domain;

/**
 * @author mohamedmassmoudi
 *
 */
public record Balance(long valueInCents) {
	
	/**
	 * @param operationType
	 * @param amount
	 * @return
	 */
	public Balance update( EOperationType operationType, Amount amount) {
		if (EOperationType.DEPOSIT.equals(operationType)) {
			return new Balance(valueInCents + amount.valueInCents());
		} else if (EOperationType.WITHDRAWAL.equals(operationType)) {
			return new Balance(valueInCents - amount.valueInCents());
		}
		throw new IllegalArgumentException(operationType + " isn't a supported operation.");
	}
}
