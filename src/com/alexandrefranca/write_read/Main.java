package com.alexandrefranca.write_read;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Message message = new Message();
        new Thread(new Writer(message)).start();
        new Thread(new Reader(message)).start();


    }
}

class Message {
    private String message;
    private boolean empty = true;

    public synchronized String read(){
        while(empty){
             // wait until message is not empty
            try {
                wait();
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void write(String msg){
        while(!empty){
            // wait until message is empty
            try {
                wait();
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }
        empty = false;
        this.message = msg;
        notifyAll();
    }
}

class Writer implements Runnable{
    private Message message;

    public Writer(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        String messages[] = {
                "Message I",
                "Message II",
                "Message III",
                "Message IV"
        };
        Random random = new Random();
        for (int i=0; i< messages.length;i++){
            String msg= messages[i];
            System.out.println("Message write: " + msg);
            message.write(msg);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
                System.out.println("InterruptedException Writer");
            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable{
    private Message message;

    public Reader(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(String latestMessage = message.read();
            !latestMessage.equals("Finished");
            latestMessage = message.read()){

            System.out.println("Message read: " + latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
                System.out.println("InterruptedException Reader");
            }
        }
    }
}

