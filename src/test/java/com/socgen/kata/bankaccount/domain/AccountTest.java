package com.socgen.kata.bankaccount.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.time.Clock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.socgen.kata.bankaccount.domain.port.StatementHistoryPrinter;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

	private Account account;

	@Mock
	private StatementHistoryPrinter statementHistoryPrinter;

	@Captor
	private ArgumentCaptor<StatementHistoryEntry> entryArgumentCapture;

	@BeforeEach
	public void init() {
		account = new Account(Clock.systemDefaultZone());

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
	public void test_withDraw_of_1000CENTS_nominal_case() {
		account.deposit(new Amount(2000L));
		account.withdraw(new Amount(1000L));
		final Balance expectedBalance = new Balance(1000L);
		assertEquals(expectedBalance, account.getBalance());
	}

	@Test
	public void test_withDraw_of_2000CENTS_AND_1000CENTS_nominal_case() {
		account.deposit(new Amount(5000L));
		account.withdraw(new Amount(1000L));
		account.withdraw(new Amount(2000L));
		final Balance expectedBalance = new Balance(2000L);
		assertEquals(expectedBalance, account.getBalance());
	}

	@Test
	public void test_writeTo_when_two_entries_are_expected() {
		account.deposit(new Amount(5000L));
		account.withdraw(new Amount(2000L));
		account.printHistory(statementHistoryPrinter);
		Mockito.verify(statementHistoryPrinter, times(2)).print(ArgumentMatchers.any(StatementHistoryEntry.class));
	}

	@Test
	public void test_writeTo_when_one_entry_is_expected() {
		account.deposit(new Amount(5000L));
		account.printHistory(statementHistoryPrinter);
		Mockito.verify(statementHistoryPrinter, times(1)).print(entryArgumentCapture.capture());
		final StatementHistoryEntry actualStatementHistoryEntry = entryArgumentCapture.getValue();
		assertNotNull(actualStatementHistoryEntry);
		assertEquals(EOperationType.DEPOSIT, actualStatementHistoryEntry.operationType());
		assertNotNull(actualStatementHistoryEntry.operationDate());
		assertEquals(new Amount(5000L), actualStatementHistoryEntry.amount());
		assertEquals(new Balance(5000L), actualStatementHistoryEntry.balance());
	}

	@Test
	public void test_writeTo_when_no_entry_found() {
		account.printHistory(statementHistoryPrinter);
		Mockito.verifyNoMoreInteractions(statementHistoryPrinter);
	}

	@Test
	public void test_getBalance_for_an_account_without_operations_yet() {
		final Balance actualBalance = account.getBalance();
		final Balance expectedBalance = new Balance(0L);
		assertEquals(expectedBalance, actualBalance);
	}

}
