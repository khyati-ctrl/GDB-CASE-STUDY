package org.example;

public class Account {

    // ===== Constants =====
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    // ===== Fields =====
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    // ===== Constructor =====
    public Account(int accountNumber, String name, int age,
                   double initialBalance, String accountType)
            throws IllegalArgumentException {

        // Validate age
        if (age < MIN_AGE) {
            throw new IllegalArgumentException(
                    "Account holder must be at least 18 years old."
            );
        }

        // Validate account type
        if (!accountType.equals("Savings")
                && !accountType.equals("Current")) {

            throw new IllegalArgumentException(
                    "Invalid account type. Must be Savings or Current."
            );
        }

        // Determine minimum balance
        double minimumBalance;

        if (accountType.equals("Savings")) {
            minimumBalance = MIN_BALANCE_SAVINGS;
        } else {
            minimumBalance = MIN_BALANCE_CURRENT;
        }

        // Validate initial balance
        if (initialBalance < minimumBalance) {
            throw new IllegalArgumentException(
                    "Initial balance must be at least ₹" + minimumBalance
            );
        }

        // Initialize fields
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = accountType;

        // Account active by default
        this.status = "Active";

        // PIN not set initially
        this.pin = null;
    }

    // ===== Business Methods =====

    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {

        // Check account status
        validateActive();

        // Check amount
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );
        }

        // Add amount
        balance = balance + amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException,
            InsufficientBalanceException,
            MinimumBalanceViolationException,
            InactiveAccountException,
            InvalidPinException {

        // Check account status
        validateActive();

        // Check if PIN has been set
        if (!hasPin()) {
            throw new InvalidPinException(
                    "PIN has not been set."
            );
        }

        // Verify PIN
        if (!verifyPin(pin)) {
            throw new InvalidPinException(
                    "Incorrect PIN."
            );
        }

        // Check amount
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        // Check sufficient balance
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance."
            );
        }

        // Check minimum balance
        double minimumBalance = getMinimumBalance();

        if (balance - amount < minimumBalance) {
            throw new MinimumBalanceViolationException(
                    "Withdrawal would violate the minimum balance of ₹"
                            + minimumBalance
            );
        }

        // Deduct amount
        balance = balance - amount;
    }

    // ===== Account Status Management =====

    public void closeAccount() throws IllegalStateException {

        // Check if already closed
        if (status.equals("Inactive")) {
            throw new IllegalStateException(
                    "Account is already closed."
            );
        }

        // Close account
        status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {

        // Check if already active
        if (status.equals("Active")) {
            throw new IllegalStateException(
                    "Account is already active."
            );
        }

        // Reopen account
        status = "Active";
    }

    // ===== PIN Management =====

    public void setPin(int pin) throws IllegalArgumentException {

        // Validate 4-digit PIN
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException(
                    "PIN must be a 4-digit number."
            );
        }

        // Set PIN
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {

        // If PIN is not set
        if (this.pin == null) {
            return false;
        }

        // Check whether PIN matches
        return this.pin == pin;
    }

    public boolean hasPin() {

        return pin != null;
    }

    // ===== Helper Methods =====

    private double getMinimumBalance() {

        if (accountType.equals("Savings")) {
            return MIN_BALANCE_SAVINGS;
        } else {
            return MIN_BALANCE_CURRENT;
        }
    }

    private void validateActive()
            throws InactiveAccountException {

        if (!status.equals("Active")) {
            throw new InactiveAccountException(
                    "Account is inactive."
            );
        }
    }

    // ===== Getters =====

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
}