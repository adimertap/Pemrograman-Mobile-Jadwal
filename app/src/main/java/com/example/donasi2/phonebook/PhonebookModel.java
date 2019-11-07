package com.example.donasi2.phonebook;

import java.io.Serializable;

public class PhonebookModel implements Serializable {
    private String nama;
    private String nohp;
    private String alamat;
    private String hubungan;
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }
    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan  = hubungan;
    }
}
