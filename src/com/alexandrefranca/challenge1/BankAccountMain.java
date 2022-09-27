package com.alexandrefranca.challenge1;

import com.alexandrefranca.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccountMain {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        BankAccount account = new BankAccount("12345-678", 1000.00);

        new Thread((Runnable) () -> {
            account.deposit(300);
            System.out.println(ThreadColor.ANSI_RED+"deposit: 300");
            account.withdraw(50);
            System.out.println(ThreadColor.ANSI_RED+"withdraw: 50");
        }).start();

        new Thread((Runnable) () -> {
            account.deposit(203.75);
            System.out.println(ThreadColor.ANSI_YELLOW+"deposit: 203.75");
            account.withdraw(100);
            System.out.println(ThreadColor.ANSI_YELLOW+"withdraw: 100");
        }).start();

        System.out.println(ThreadColor.ANSI_BLUE+"Balance:"+ account.getBalance());

    }

}