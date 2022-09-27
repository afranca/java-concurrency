package com.alexandrefranca.challenge3;

import com.alexandrefranca.ThreadColor;

public class Challenge3 {

    public static void main(String[] args) {

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

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(ThreadColor.ANSI_BLUE+"Balance:"+ account.getBalance());

    }

}