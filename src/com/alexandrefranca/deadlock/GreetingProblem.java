package com.alexandrefranca.deadlock;

import com.alexandrefranca.ThreadColor;

public class GreetingProblem {

    public static void main(String[] args) {
        final PolitePerson jane = new PolitePerson("Jane", ThreadColor.ANSI_BLUE);
        final PolitePerson john = new PolitePerson("John", ThreadColor.ANSI_RED);

        new Thread((Runnable) () -> { jane.sayHello(john); }).start();

        new Thread((Runnable) () -> { john.sayHello(jane); }).start();

    }

    // 1. Thread1 acquires the lock on the jane object and enters the sayHello() method.
    //      It prints to the console, then suspends.
    // 2. Thread2 acquires the lock on the john object and enters the sayHello() method.
    //      It prints to the console, then suspends.
    // 3. Thread1 runs again and wants to say hello back to the john object. It tries to call the sayHelloBack() method
    //      using the john object that was passed into the sayHello() method,
    //      but Thread2 is holding the john lock, so Thread1 suspends.
    // 4. Thread2 runs again and wants to say hello back to the jane object. It tries to call the sayHelloBack() method
    //      using the jane object that was passed into the sayHello() method,
    //      but Thread1 is holding the jane lock, so Thread2 suspends.

    static class PolitePerson {
        private final String name;
        private String colour;

        public PolitePerson(String name, String colour) {
            this.name = name;
            this.colour = colour;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person) {
            System.out.format(colour+"%s: %s" + " has said hello to me!%n", this.name, person.getName());
            System.out.format(colour+"%s: Calling %s.sayHelloBack()%n", this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format(colour+"%s: %s" + " has said hello back to me!%n", this.name, person.getName());
        }
    }
}
