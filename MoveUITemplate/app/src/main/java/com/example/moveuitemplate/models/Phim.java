package com.example.moveuitemplate.models;

public class Phim {
    public Phim(String ID) {
        this.ID = ID;
    }

    public Phim(String ID, String tenPhim) {
        this.ID = ID;
        this.tenPhim = tenPhim;
    }

    public String ID;
    public String tenPhim;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }
}
