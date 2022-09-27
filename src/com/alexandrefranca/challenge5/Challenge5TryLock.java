package com.alexandrefranca.challenge5;

import com.alexandrefranca.ThreadColor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Challenge5TryLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        BankAccount account = new BankAccount("12345-678", 1000.00, lock);

        new Thread((Runnable) () -> {
            account.deposit(300);
            System.out.println(ThreadColor.ANSI_RED+"deposit: 300");
//            try {
//                Thread.sleep(1_000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            account.withdraw(50);
            System.out.println(ThreadColor.ANSI_RED+"withdraw: 50");
        }).start();

        new Thread((Runnable) () -> {
            account.deposit(203.75);
            System.out.println(ThreadColor.ANSI_YELLOW+"deposit: 203.75");
            account.withdraw(100);
            System.out.println(ThreadColor.ANSI_YELLOW+"withdraw: 100");
        }).start();

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ThreadColor.ANSI_BLUE+"Balance:"+ account.getBalance());

    }

}