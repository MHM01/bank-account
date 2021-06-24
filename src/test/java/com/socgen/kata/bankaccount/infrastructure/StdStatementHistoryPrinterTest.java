package com.socgen.kata.bankaccount.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.socgen.kata.bankaccount.domain.Amount;
import com.socgen.kata.bankaccount.domain.Balance;
import com.socgen.kata.bankaccount.domain.EOperationType;
import com.socgen.kata.bankaccount.domain.StatementHistoryEntry;

public class StdStatementHistoryPrinterTest {

	private PrintStream sysOut;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	private static final Instant INSTANT = Instant.parse("2021-06-24T03:00:00.123Z");

	@BeforeEach
	public void setUpStreams() {
		sysOut = System.out;
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void revertStreams() {
		System.setOut(sysOut);
	}

	@Test
	public void test_print_nominal_case() {
		final StatementHistoryEntry statementHistoryEntry = new StatementHistoryEntry(EOperationType.DEPOSIT, INSTANT,
				new Amount(1000L), new Balance(2000L));

		new StdStatementHistoryPrinter().print(statementHistoryEntry);

		assertEquals(outContent.toString().trim(),
				"Operation='DEPOSIT' | Date='2021-06-24T03:00:00.123Z' | Amount='1000' | Balance='2000'");
	}

}
