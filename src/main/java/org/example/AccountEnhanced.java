package org.example;

public class AccountEnhanced {

    // Constants
    private static final double MIN_BALANCE_SAVINGS=500.0;
    private static final double MIN_BALANCE_CURRENT=1000.0;
    private static final int MIN_AGE=18;
    private static final int MIN_PIN=1000;
    private static final int MAX_PIN=9999;

    // Private fields
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin; //null if not set

    // Constructor
    public AccountEnhanced(int accountNumber, String name, int age,
                           double initialBalance, String accountType) {

        this.accountNumber = accountNumber;
        this.name = name;

        // Age validation
        this.age=(age>=MIN_AGE)?age:MIN_AGE;

        // Account type validation
        if (accountType.equals("Savings") || accountType.equals("Current")) {
            this.accountType = accountType;
        } else {
            this.accountType = "Savings";
        }

        // Set minimum balance
        double minimumBalance;

        if (this.accountType.equals("Savings")) {
            minimumBalance = 500.0;
        } else {
            minimumBalance = 1000.0;
        }

        this.balance=(initialBalance < minimumBalance)?minimumBalance:initialBalance;

        // Account is active by default
        this.status = "Active";

        // PIN is not set initially
        this.pin = null;
    }

    // Deposit money
    public boolean deposit(double amount) {

        // Account must be active
        if (!status.equals("Active")) {
            return false;
        }

        // Amount must be positive
        if (amount <= 0) {
            return false;
        }

        balance = balance + amount;
        return true;
    }

    // Withdraw money with PIN
    public boolean withdraw(double amount, int pin) {

        // Account must be active
        if (!status.equals("Active")) {
            return false;
        }

        // PIN must be correct
        if (!verifyPin(pin)) {
            return false;
        }

        // Amount must be positive
        if (amount <= 0) {
            return false;
        }

        // Determine minimum balance
        double minimumBalance;

        if (accountType.equals("Savings")) {
            minimumBalance = MIN_BALANCE_SAVINGS;
        } else {
            minimumBalance = MIN_BALANCE_CURRENT;
        }

        // Balance must not fall below minimum
        if (balance - amount < minimumBalance) {
            return false;
        }

        balance = balance - amount;
        return true;
    }

    // Close account
    public boolean closeAccount() {

        if (status.equals("Inactive")) {
            return false;
        }
        status = "Inactive";
        return true;
    }

    // Reopen account
    public boolean reopenAccount() {

        if (status.equals("Active")) {
            return false;
        }
        status = "Active";
        return true;
    }

    // Set PIN
    public boolean setPin(int pin) {

        // A valid PIN must be exactly 4 digits
        if (pin < MIN_PIN || pin > MAX_PIN) {
            return false;
        }
        this.pin = pin;
        return true;
    }

    // Verify PIN
    public boolean verifyPin(int pin) {

        if (this.pin == null) {
            return false;
        }
        return this.pin == pin;
    }

    // Check whether PIN is set
    public boolean hasPin() {
        return pin != null;
    }

    // Getter methods
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getStatus() {
        return status;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}