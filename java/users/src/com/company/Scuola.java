package com.company;

import java.util.Vector;

public class Scuola {
    public Vector<Classroom> classi = new Vector<Classroom>();

    public void add(Classroom classe){
        this.classi.add(classe);
    }
}
