package com.alexandrefranca.challenge1;

class BankAccount {

    private double balance;
    private String accountNumber;
 
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
 
    public void deposit(double amount) {
        synchronized(this){
            balance += amount;
        }
    }
 
    public synchronized void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}