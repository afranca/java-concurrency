package com.alexandrefranca.challenge5;

import java.util.concurrent.TimeUnit;
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
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance += amount;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("deposit: Could not get lock");
            }
        } catch (InterruptedException e) {
            System.out.println("deposit: InterruptedException");
        }

    }
 
    public void withdraw(double amount) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)){
                try {
                    balance -= amount;
                } finally {
                    lock.unlock();
                }

            } else {
                System.out.println("withdraw: Could not get lock");
            }

        } catch (InterruptedException e) {
            System.out.println("withdraw: InterruptedException");
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