package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Database DB = new Database();


        Reader reader1 = new Reader("Reader1", DB);
        Reader reader2 = new Reader("Reader2", DB);
        Reader reader3 = new Reader("Reader3", DB);
        Writer writer1 = new Writer("Writer1", DB);
        Writer writer2 = new Writer("Writer2", DB);


    }
}
