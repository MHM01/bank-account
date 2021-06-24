package com.socgen.kata.bankaccount.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

	private Account account;

	@BeforeEach
	public void init() {
		account = new Account();

	}

	@Test
	public void test_deposit_of_3000CENTS_nominal_case() {
		account.deposit(new Amount(3000L));
		final Balance expectedBalance = new Balance(3000L);
		assertEquals(expectedBalance, account.getBalance());
	}

	@Test
	public void test_deposit_of_2000CENTS_AND_1000CENTS_nominal_case() {
		account.deposit(new Amount(2000L));
		account.deposit(new Amount(1000L));
		final Balance expectedBalance = new Balance(3000L);
		assertEquals(expectedBalance, account.getBalance());
	}

	
	@Test
	public void test_getBalance_for_an_account_without_operations_yet() {
		final Balance actualBalance = account.getBalance();
		final Balance expectedBalance = new Balance(0L);
		assertEquals(expectedBalance, actualBalance);
	}

}