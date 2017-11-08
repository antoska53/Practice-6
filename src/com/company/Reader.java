package com.company;

import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    private String name; //имя потока
    private Database db;
    private Thread t;

    Reader(String nameThread, Database d){
        name = nameThread;
        db = d;
        t = new Thread(this, name);
        t.start();
    }
    @Override
    public void run() {
        for(int i = 0; i < 2; i++){
        try {
            Thread.sleep((int)(Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        db.startRead(name);
            db.endRead(name);
        }
    }
}
