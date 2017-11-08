package com.company;



import static java.lang.Thread.sleep;

public class Database {
    private volatile int numReader = 0;
    private volatile boolean numWriter = false;

    public void startRead(String name){
        System.out.println(name + " wants to read");
        semaphore("Reader");
            numReader++;
            System.out.println(name + " is reading. Count reader = " + numReader);
            readWrite();

    }
    public void endRead(String name){
        numReader--;
        System.out.println(name + " is done reading. Count reader = " + numReader);
       // numReader--;
        //notifyAll();
        semaphore("noti");
        System.out.println(name + " is slipping");
        readWrite();

        //semaphore("Reader");
        //if(numReader == 0) notify();

    }
    public void startWrite(String name){
        System.out.println(name + " want to write");
        semaphore("Writer");
        numWriter = true;
        System.out.println(name + " is writing");
        readWrite();


    }
    public void endWrite(String name){
        System.out.println(name + " is done writing");
        numWriter = false;
        //System.out.println(name + " is done writing");
        //numWriter = false;
        //notifyAll();

        System.out.println(name + " is slipping");
        semaphore("noti");
        readWrite();
        //semaphore("Writer");
        //notify();
        //notify();
    }
    public void readWrite(){
        try {
            sleep((int)(Math.random() * 5000));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized void semaphore(String name) {
        switch (name) {
            case "Writer":
                if (numWriter) {
                    while (numWriter) {
                        System.out.println("уже есть читающий writer");
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                   // notifyAll();

                } else if (numReader > 0) {
                    while (numReader > 0) {
                        System.out.println("WRITER ЖДЕТ READER'ов");
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                   //notifyAll();
                }
                numWriter = true;
               // notify();
                break;

            case "Reader":
                if (numWriter) {
                    while (numWriter) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //notifyAll();
                }

                notify();
                break;

            case "noti" :
                notify();
        }


    }
}
