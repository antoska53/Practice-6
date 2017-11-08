package com.company;

import java.util.concurrent.Semaphore;

public class Writer implements Runnable {
    private String name;
    private Database db;
    private Thread t;

    Writer(String nameThread, Database d){
        name = nameThread;
        db = d;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 2; i++){
        try {
            Thread.sleep((int)(Math.random()*5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        db.startWrite(name);
            db.endWrite(name);
        }

    }
}
