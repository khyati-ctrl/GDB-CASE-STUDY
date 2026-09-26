package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount {
    public static void main(String[] args) {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        // NOTE: If you completed Activity 9 successfully, paste your working domain classes into src/com/gdb/domain (replacing the provided versions).

        // TODO: Step 1 - Create an array/portfolio of AbstractAccount objects (SavingsAccount, CurrentAccount, SalaryAccount)

        AbstractAccount savings = new SavingsAccount(
                "SA001",
                "Khyati Sharma",
                20,
                10000,
                "ACTIVE",
                "1234"
        );

        AbstractAccount current = new CurrentAccount(
                "CA001",
                "Rahul Sharma",
                21,
                5000,
                "ACTIVE",
                "5678",
                5000
        );

        AbstractAccount salary = new SalaryAccount(
                "SAL001",
                "Ananya Singh",
                22,
                30000,
                "ACTIVE",
                "9999",
                "ABC Technologies"
        );

        AbstractAccount[] accounts = {
                savings,
                current,
                salary
        };


        System.out.println("=== Account Portfolio ===");

        for (AbstractAccount account : accounts) {
            account.displayAccountInfo();
            System.out.println();
        }


        // TODO: Step 2 - Implement and test secure fund transfer from Savings to Current account with PIN authentication

        System.out.println("=== Step 2: Secure Fund Transfer ===");

        double transferAmount = 2000;
        String savingsPin = "1234";

        System.out.println("Before Transfer:");
        System.out.println("Savings Balance: Rs " + savings.getBalance());
        System.out.println("Current Balance: Rs " + current.getBalance());

        try {

            // Authenticate Savings Account PIN first
            if (!savings.validatePin(savingsPin)) {
                throw new InvalidPinException("Invalid PIN entered");
            }

            // Withdraw from Savings
            savings.withdraw(transferAmount, savingsPin);

            // Deposit into Current
            current.deposit(transferAmount);

            System.out.println("\nTransfer successful!");

        } catch (AccountException e) {

            System.out.println("Transfer failed: " + e.getMessage());
        }

        System.out.println("\nAfter Transfer:");
        System.out.println("Savings Balance: Rs " + savings.getBalance());
        System.out.println("Current Balance: Rs " + current.getBalance());


        // TODO: Step 3 - Test failed transfer with wrong PIN and verify no balance was credited/debited

        System.out.println("\n=== Step 3: Failed Transfer with Wrong PIN ===");

        double failedTransferAmount = 1000;

        double savingsBalanceBefore =
                savings.getBalance();

        double currentBalanceBefore =
                current.getBalance();

        String wrongPin = "0000";

        try {

            // Attempt transfer using WRONG PIN
            savings.withdraw(failedTransferAmount, wrongPin);

            // This line should never execute
            current.deposit(failedTransferAmount);

            System.out.println("ERROR: Transfer should have failed!");

        } catch (AccountException e) {

            System.out.println(
                    "Transfer correctly rejected: "
                            + e.getMessage()
            );
        }


        // Verify balances remained unchanged
        double savingsBalanceAfter =
                savings.getBalance();

        double currentBalanceAfter =
                current.getBalance();

        System.out.println("\nBalance Verification:");

        System.out.println(
                "Savings Before: Rs " + savingsBalanceBefore
        );

        System.out.println(
                "Savings After : Rs " + savingsBalanceAfter
        );

        System.out.println(
                "Current Before: Rs " + currentBalanceBefore
        );

        System.out.println(
                "Current After : Rs " + currentBalanceAfter
        );


        if (savingsBalanceBefore == savingsBalanceAfter
                && currentBalanceBefore == currentBalanceAfter) {

            System.out.println(
                    "PASS: No balance was debited or credited."
            );

        } else {

            System.out.println(
                    "FAIL: Balance changed unexpectedly."
            );
        }

        // TODO: Step 4 - Process monthly cycle applying interest to every SavingsAccount and checking each SalaryAccount's inactive months

        System.out.println("\n=== Step 4: Monthly Cycle ===");

        for (AbstractAccount account : accounts) {

            // Apply interest to SavingsAccount
            if (account instanceof SavingsAccount) {

                SavingsAccount savingsAccount =
                        (SavingsAccount) account;

                double balanceBefore =
                        savingsAccount.getBalance();

                savingsAccount.applyInterest();

                double balanceAfter =
                        savingsAccount.getBalance();

                System.out.println(
                        "Savings Account "
                                + savingsAccount.getAccountNumber()
                                + ": Interest applied."
                );

                System.out.println(
                        "Balance before interest: Rs "
                                + balanceBefore
                );

                System.out.println(
                        "Balance after interest : Rs "
                                + balanceAfter
                );
            }


            // Check SalaryAccount inactive months
            if (account instanceof SalaryAccount) {

                SalaryAccount salaryAccount =
                        (SalaryAccount) account;

                System.out.println(
                        "\nSalary Account "
                                + salaryAccount.getAccountNumber()
                );

                System.out.println(
                        "Employer: "
                                + salaryAccount.getEmployerName()
                );

                System.out.println(
                        "Inactive months: "
                                + salaryAccount.getInactiveMonths()
                );

                // For demonstration, increment inactive months
                salaryAccount.incrementInactiveMonths();

                System.out.println(
                        "After monthly cycle - inactive months: "
                                + salaryAccount.getInactiveMonths()
                );
            }
        }
        System.out.println("\n=== Final Account Balances ===");

        for (AbstractAccount account : accounts) {

            System.out.println(
                    account.getAccountType()
                            + " Account "
                            + account.getAccountNumber()
                            + " : Rs "
                            + account.getBalance()
            );
        }
        System.out.println("=== Complete the test suite and verify all banking operations ===");
    }
}
