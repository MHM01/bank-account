package com.socgen.kata.bankaccount;

import java.time.Clock;

import com.socgen.kata.bankaccount.domain.Account;
import com.socgen.kata.bankaccount.domain.Amount;
import com.socgen.kata.bankaccount.domain.port.StatementHistoryPrinter;
import com.socgen.kata.bankaccount.infrastructure.StdStatementHistoryPrinter;

/**
 * @author mohamedmassmoudi
 *
 */
public class BankAccountApplication {

	public static void main(String[] args) {
		//Instantiate hexagon
		Account accountPort = new Account(Clock.systemDefaultZone());
		//Perform some operations
		accountPort.deposit(new Amount(5000L));
		accountPort.deposit(new Amount(1500L));
		accountPort.withdraw(new Amount(1200L));
		accountPort.withdraw(new Amount(500L));
		//Instantiate infrastructure
		StatementHistoryPrinter statementHistoryPrinter = new StdStatementHistoryPrinter();
		accountPort.printHistory(statementHistoryPrinter);

	}

}
