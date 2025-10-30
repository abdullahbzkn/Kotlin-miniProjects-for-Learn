package com.abdbzkn.superheroinfoappwithjava;

import java.io.Serializable;
public class Superhero implements Serializable {
    String name;
    String info;
    int image;
    public Superhero(String name, String info,int image){
        this.name = name;
        this.info = info;
        this.image = image;
    }
}
