package com.example.donasi2;

import java.io.Serializable;

public class Teman implements Serializable {
    private String name;
    private String alamat;
    private String telp;

    public Teman(String name, String alamat, String telp) {
        this.name = name;
        this.alamat = alamat;
        this.telp = telp;
    }

    public String getName() {
        return name;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelp() {
        return telp;
    }
}
