package org.example;

public class TestAccountExceptions {

    // Helper method to display account details
    public static void displayAccount(Account account) {

        String pinStatus;

        if (account.hasPin()) {
            pinStatus = "Yes";
        } else {
            pinStatus = "No";
        }

        System.out.println("Account #" + account.getAccountNumber()
                + " | " + account.getName()
                + " (" + account.getAge() + " yrs)"
                + " | " + account.getAccountType()
                + " | ₹" + account.getBalance()
                + " | " + account.getStatus()
                + " | PIN: " + pinStatus);
    }

    public static void main(String[] args) {

        System.out.println("=".repeat(50));
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("=".repeat(50));
        System.out.println();

        // Test 1
        System.out.println(">>> Test 1: Valid Account Creation");

        Account account1 = null;

        try {
            account1 = new Account(
                    1001, "John Doe", 25, 1000.0, "Savings"
            );

            System.out.print("SUCCESS: ");
            displayAccount(account1);

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 2
        System.out.println(">>> Test 2: Invalid Age (under 18)");

        try {
            Account account2 = new Account(
                    1002, "Young Kid", 16, 500.0, "Savings"
            );

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 3
        System.out.println(">>> Test 3: Invalid Account Type");

        try {
            Account account3 = new Account(
                    1003, "Test User", 25, 500.0, "Invalid"
            );

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 4
        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println();

        try {
            System.out.println("Creating Savings account with ₹300");
            Account account4 = new Account(
                    1004, "Bob Wilson", 25, 300.0, "Savings"
            );

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 5
        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        Account account5 = null;

        try {
            account5 = new Account(
                    1005, "Alice Brown", 30, 1000.0, "Current"
            );

            System.out.print("Account: ");
            displayAccount(account5);

            account5.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");

            account5.deposit(500.0);
            System.out.println("Depositing ₹500.0: SUCCESS");
            System.out.println("Balance after deposit: ₹"
                    + account5.getBalance());

            account5.withdraw(200.0, 1234);
            System.out.println("Withdrawing ₹200.0: SUCCESS");
            System.out.println("Balance after withdrawal: ₹"
                    + account5.getBalance());

            displayAccount(account5);

        } catch (AccountException | IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 6
        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");

        try {
            System.out.println("Attempting to deposit ₹-100.0");

            account5.deposit(-100.0);

            System.out.println("Deposit successful");

        } catch (AccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 7
        System.out.println(">>> Test 7: Insufficient Balance");

        Account account6 = null;

        try {
            account6 = new Account(
                    1006, "Charlie Green", 35, 500.0, "Savings"
            );

            account6.setPin(1234);

            System.out.print("Account: ");
            displayAccount(account6);

            System.out.println("Attempting to withdraw ₹1000.0");

            account6.withdraw(1000.0, 1234);

        } catch (AccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 8
        System.out.println(">>> Test 8: Minimum Balance Violation");

        Account account7 = null;

        try {
            account7 = new Account(
                    1007, "Diana Prince", 28, 1000.0, "Savings"
            );

            account7.setPin(1234);

            System.out.print("Account: ");
            displayAccount(account7);

            System.out.println("Attempting to withdraw ₹600.0");

            account7.withdraw(600.0, 1234);

        } catch (AccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 9
        System.out.println(">>> Test 9: Inactive Account Operations");

        Account account8 = null;

        try {
            account8 = new Account(
                    1008, "Eve Wilson", 32, 2000.0, "Current"
            );

            System.out.print("Account: ");
            displayAccount(account8);

            account8.closeAccount();
            System.out.println("Closing account: SUCCESS");

            System.out.println(
                    "Attempting to deposit ₹100.0 on closed account"
            );

            account8.deposit(100.0);

        } catch (AccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());

            try {
                account8.reopenAccount();
                System.out.println("Reopening account: SUCCESS");

                account8.deposit(100.0);
                System.out.println(
                        "Depositing ₹100.0 after reopen: SUCCESS"
                );

                System.out.println(
                        "Balance after deposit: ₹"
                                + account8.getBalance()
                );

            } catch (AccountException | IllegalStateException ex) {
                System.out.println("EXCEPTION: " + ex.getMessage());
            }
        }

        // Test 10
        System.out.println(">>> Test 10: PIN Verification");

        Account account9 = null;

        try {
            account9 = new Account(
                    1009, "Frank Miller", 40, 1500.0, "Savings"
            );

            System.out.print("Account: ");
            displayAccount(account9);

            account9.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");

            account9.withdraw(200.0, 1234);

            System.out.println(
                    "Withdrawing ₹200.0 with correct PIN: SUCCESS"
            );

            System.out.println();
            System.out.println("Balance: ₹" + account9.getBalance());

            System.out.println(
                    "Attempting to withdraw ₹100.0 "
                            + "with incorrect PIN (9999)"
            );

            account9.withdraw(100.0, 9999);

        } catch (InvalidPinException e) {
            System.out.println("EXCEPTION: " + e.getMessage());

            try {
                // Create an account without a PIN
                Account noPinAccount = new Account(
                        1010, "No Pin User", 40, 1000.0, "Savings"
                );

                System.out.println(
                        "Attempting to withdraw ₹100.0 without PIN set"
                );

                noPinAccount.withdraw(100.0, 1234);

            } catch (AccountException ex) {
                System.out.println("EXCEPTION: " + ex.getMessage());
            }

        } catch (AccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        // Test 11
        System.out.println(">>> Test 11: All Accounts Summary");

        if (account1 != null) {
            displayAccount(account1);
        }

        if (account5 != null) {
            displayAccount(account5);
        }

        if (account6 != null) {
            displayAccount(account6);
        }

        if (account7 != null) {
            displayAccount(account7);
        }

        if (account8 != null) {
            displayAccount(account8);
        }

        if (account9 != null) {
            displayAccount(account9);
        }

        System.out.println("=".repeat(50));
        System.out.println("TEST COMPLETED!");
        System.out.println("=".repeat(50));
    }
}