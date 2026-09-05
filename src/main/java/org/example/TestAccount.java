package org.example;

public class TestAccount {

    // Helper method to display account details
    public static void displayAccount(Account account) {
        System.out.println(
                "Account #" + account.getAccountNumber()
                        + " | " + account.getName()
                        + " (" + account.getAge() + " yrs)"
                        + " | " + account.getAccountType()
                        + " | ₹" + account.getBalance()
                        + " | " + account.getStatus()
                        + " | PIN: " + (account.hasPin() ? "Yes" : "No")
        );
    }

    public static void main(String[] args) {

        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================");

        // ========================================================
        // Test 1: Valid Account Creation
        // ========================================================
        System.out.println("\n>>> Test 1: Valid Account Creation");

        Account account1 = null;

        try {
            account1 = new Account(
                    1001,
                    "John Doe",
                    25,
                    1000.0,
                    "Savings"
            );

            System.out.print("SUCCESS: ");
            displayAccount(account1);

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 2: Invalid Age
        // ========================================================
        System.out.println(">>> Test 2: Invalid Age (under 18)");

        try {
            Account account2 = new Account(
                    1002,
                    "Young User",
                    16,
                    1000.0,
                    "Savings"
            );

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 3: Invalid Account Type
        // ========================================================
        System.out.println(">>> Test 3: Invalid Account Type");

        try {
            Account account3 = new Account(
                    1003,
                    "Test User",
                    25,
                    1000.0,
                    "Invalid"
            );

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 4: Minimum Balance on Creation
        // ========================================================
        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println("\nCreating Savings account with ₹300");

        try {
            Account account4 = new Account(
                    1004,
                    "Low Balance User",
                    25,
                    300.0,
                    "Savings"
            );

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 5: Valid Deposit and Withdrawal
        // ========================================================
        System.out.println("\n>>> Test 5: Valid Deposit and Withdrawal");

        Account account5 = null;

        try {
            account5 = new Account(
                    1005,
                    "Alice Brown",
                    30,
                    1000.0,
                    "Current"
            );

            System.out.print("Account: ");
            displayAccount(account5);

            System.out.print("Setting PIN 1234: ");

            try {
                account5.setPin(1234);
                System.out.println("SUCCESS");
            } catch (IllegalArgumentException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Depositing ₹500.0: ");

            try {
                account5.deposit(500.0);
                System.out.println("SUCCESS");
                System.out.println("Balance after deposit: ₹"
                        + account5.getBalance());
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Withdrawing ₹200.0: ");

            try {
                account5.withdraw(200.0, 1234);
                System.out.println("SUCCESS");
                System.out.println("Balance after withdrawal: ₹"
                        + account5.getBalance());
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            displayAccount(account5);

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 6: Invalid Deposit
        // ========================================================
        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");
        System.out.println("Attempting to deposit ₹-100.0");

        try {
            account5.deposit(-100.0);
            System.out.println("SUCCESS");
        } catch (AccountException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 7: Insufficient Balance
        // ========================================================
        System.out.println(">>> Test 7: Insufficient Balance");

        Account account6 = null;

        try {
            account6 = new Account(
                    1006,
                    "Charlie Green",
                    35,
                    500.0,
                    "Savings"
            );

            account6.setPin(1234);

            System.out.print("Account: ");
            displayAccount(account6);

            System.out.println("Attempting to withdraw ₹1000.0");

            try {
                account6.withdraw(1000.0, 1234);
                System.out.println("SUCCESS");
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 8: Minimum Balance Violation
        // ========================================================
        System.out.println(">>> Test 8: Minimum Balance Violation");

        Account account7 = null;

        try {
            account7 = new Account(
                    1007,
                    "Diana Prince",
                    28,
                    1000.0,
                    "Savings"
            );

            account7.setPin(1234);

            System.out.print("Account: ");
            displayAccount(account7);

            System.out.println("Attempting to withdraw ₹600.0");

            try {
                account7.withdraw(600.0, 1234);
                System.out.println("SUCCESS");
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 9: Inactive Account Operations
        // ========================================================
        System.out.println(">>> Test 9: Inactive Account Operations");

        Account account8 = null;

        try {
            account8 = new Account(
                    1008,
                    "Eve Wilson",
                    32,
                    2000.0,
                    "Current"
            );

            System.out.print("Account: ");
            displayAccount(account8);

            System.out.print("Closing account: ");

            try {
                account8.closeAccount();
                System.out.println("SUCCESS");
            } catch (IllegalStateException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.println(
                    "Attempting to deposit ₹100.0 on closed account"
            );

            try {
                account8.deposit(100.0);
                System.out.println("SUCCESS");
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Reopening account: ");

            try {
                account8.reopenAccount();
                System.out.println("SUCCESS");
            } catch (IllegalStateException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Depositing ₹100.0 after reopen: ");

            try {
                account8.deposit(100.0);
                System.out.println("SUCCESS");
                System.out.println("Balance after deposit: ₹"
                        + account8.getBalance());
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 10: PIN Verification
        // ========================================================
        System.out.println(">>> Test 10: PIN Verification");

        Account account9 = null;

        try {
            account9 = new Account(
                    1009,
                    "Frank Miller",
                    40,
                    1500.0,
                    "Savings"
            );

            System.out.print("Account: ");
            displayAccount(account9);

            System.out.print("Setting PIN 1234: ");

            try {
                account9.setPin(1234);
                System.out.println("SUCCESS");
            } catch (IllegalArgumentException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.print("Withdrawing ₹200.0 with correct PIN: ");

            try {
                account9.withdraw(200.0, 1234);
                System.out.println("SUCCESS");
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            System.out.println("\nBalance: ₹" + account9.getBalance());

            System.out.println(
                    "Attempting to withdraw ₹100.0 with incorrect PIN (9999)"
            );

            try {
                account9.withdraw(100.0, 9999);
                System.out.println("SUCCESS");
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

            // Separate account with no PIN
            Account noPinAccount = new Account(
                    1010,
                    "No Pin User",
                    30,
                    1000.0,
                    "Savings"
            );

            System.out.println(
                    "Attempting to withdraw ₹100.0 without PIN set"
            );

            try {
                noPinAccount.withdraw(100.0, 1234);
                System.out.println("SUCCESS");
            } catch (AccountException e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        // ========================================================
        // Test 11: All Accounts Summary
        // ========================================================
        System.out.println(">>> Test 11: All Accounts Summary");

        displayAccount(account1);
        displayAccount(account5);
        displayAccount(account6);
        displayAccount(account7);
        displayAccount(account8);
        displayAccount(account9);

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
}