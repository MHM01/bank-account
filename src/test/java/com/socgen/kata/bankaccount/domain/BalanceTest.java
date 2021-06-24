package com.socgen.kata.bankaccount.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BalanceTest {

	@Test
	public void test_update_after_withdrawal_operation_of_1000CENTS() {
		final Balance initialBalance = new Balance(5000L);
		final Balance actualBalance = initialBalance.update(EOperationType.WITHDRAWAL, new Amount(1000L));
		final Balance expectedBalance = new Balance(4000L);
		assertEquals(expectedBalance, actualBalance);
	}

	@Test
	public void test_update_after_deposit_operation_of_5000CENTS() {
		final Balance initialBalance = new Balance(1000L);
		final Balance actualBalance = initialBalance.update(EOperationType.DEPOSIT, new Amount(5000L));
		final Balance expectedBalance = new Balance(6000L);
		assertEquals(expectedBalance, actualBalance);
	}

	@Test
	public void test_update_when_null_operation_type_was_set() {
		final Balance initialBalance = new Balance(1000L);
		final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> initialBalance.update(null, new Amount(5000L)),
				"Expected update to throw IllegalArgumentException, but it didn't");

		assertTrue(thrown.getMessage().contains("isn't a supported operation."));
	}
}
