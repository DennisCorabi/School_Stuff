package com.company;

public class Main {

    public static void main(String[] args) {
        int i;
        Classroom classe1 = new Classroom(10);
        Classroom classe2 = new Classroom(10);

        classe1.InsertUsers("dennis",17);
        classe1.InsertUsers("nadir",24);
        classe1.InsertUsers("vittorio",18);
        classe2.InsertUsers("giovanni",17);
        classe1.GetUsers();
        classe2.GetUsers();
    }
}
