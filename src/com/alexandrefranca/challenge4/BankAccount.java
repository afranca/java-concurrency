package com.alexandrefranca.challenge4;

import java.util.concurrent.locks.Lock;

class BankAccount {

    private double balance;
    private String accountNumber;
    private Lock lock;
 
    public BankAccount(String accountNumber, double initialBalance, Lock lock) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = lock;
    }
 
    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }

    }
 
    public void withdraw(double amount) {
        lock.lock();
        try{
            balance -= amount;
        } finally {
            lock.unlock();
        }

    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account Number: "+accountNumber);
    }
}