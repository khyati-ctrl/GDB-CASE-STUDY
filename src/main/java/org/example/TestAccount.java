package org.example;

public class TestAccount {

    public static void main(String[] args) {

        System.out.println("=".repeat(50));
        System.out.println("GLOBAL DIGITAL BANK - ACCOUNT TEST");
        System.out.println("=".repeat(50));

        System.out.println(">>> 1. Creating Account");

        Account account1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");

        System.out.println("Account created!");
        System.out.println("Account #" + account1.getAccountNumber()
                + " | " + account1.getName()
                + " (" + account1.getAge() + " yrs)"
                + " | " + account1.getAccountType()
                + " | ₹" + account1.getBalance()
                + " | " + account1.getStatus());


        System.out.println(">>> 2. Deposit Money");

        double depositAmount = 500.0;
        boolean depositResult = account1.deposit(depositAmount);

        if (depositResult) {
            System.out.println("Depositing ₹" + depositAmount + ": SUCCESS");
            System.out.println("New balance: ₹" + account1.getBalance());
        } else {
            System.out.println("Depositing ₹" + depositAmount + ": FAILED (Invalid amount)");
        }

        depositAmount = -100.0;
        depositResult = account1.deposit(depositAmount);

        if (depositResult) {
            System.out.println("Depositing ₹" + depositAmount + ": SUCCESS");
            System.out.println("New balance: ₹" + account1.getBalance());
        } else {
            System.out.println("Depositing ₹" + depositAmount + ": FAILED (Invalid amount)");
        }


        System.out.println(">>> 3. Withdraw Money");

        double withdrawAmount = 200.0;
        boolean withdrawResult = account1.withdraw(withdrawAmount);

        if (withdrawResult) {
            System.out.println("Withdrawing ₹" + withdrawAmount + ": SUCCESS");
            System.out.println("New balance: ₹" + account1.getBalance());
        } else {
            System.out.println("Withdrawing ₹" + withdrawAmount + ": FAILED (Insufficient balance)");
        }

        withdrawAmount = 2000.0;
        withdrawResult = account1.withdraw(withdrawAmount);

        if (withdrawResult) {
            System.out.println("Withdrawing ₹" + withdrawAmount + ": SUCCESS");
            System.out.println("New balance: ₹" + account1.getBalance());
        } else {
            System.out.println("Withdrawing ₹" + withdrawAmount
                    + ": FAILED (Insufficient balance)");
        }
        System.out.println("Current balance: ₹" + account1.getBalance());


        System.out.println(">>> 4. Creating Another Account");

        Account account2 = new Account(1002, "Jane Smith", 30, 2000.0, "Current");

        System.out.println("Account #" + account2.getAccountNumber()
                + " | " + account2.getName()
                + " (" + account2.getAge() + " yrs)"
                + " | " + account2.getAccountType()
                + " | ₹" + account2.getBalance()
                + " | " + account2.getStatus());


        System.out.println(">>> 5. All Accounts");

        System.out.println("Account #" + account1.getAccountNumber()
                + " | " + account1.getName()
                + " (" + account1.getAge() + " yrs)"
                + " | " + account1.getAccountType()
                + " | ₹" + account1.getBalance()
                + " | " + account1.getStatus());

        System.out.println("Account #" + account2.getAccountNumber()
                + " | " + account2.getName()
                + " (" + account2.getAge() + " yrs)"
                + " | " + account2.getAccountType()
                + " | ₹" + account2.getBalance()
                + " | " + account2.getStatus());

        System.out.println("=".repeat(50));
        System.out.println("TEST COMPLETED!");
        System.out.println("=".repeat(50));
    }
}
