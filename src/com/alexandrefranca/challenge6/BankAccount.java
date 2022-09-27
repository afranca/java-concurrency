package com.alexandrefranca.challenge6;

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
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("deposit: Could not get lock");
            }
        } catch (InterruptedException e) {
            System.out.println("deposit: InterruptedException");
        }
        System.out.println("deposit: Status of the transaction = "+status);
    }
 
    public void withdraw(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)){
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }

            } else {
                System.out.println("withdraw: Could not get lock");
            }

        } catch (InterruptedException e) {
            System.out.println("withdraw: InterruptedException");
        }
        System.out.println("withdraw: Status of the transaction = "+status);
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