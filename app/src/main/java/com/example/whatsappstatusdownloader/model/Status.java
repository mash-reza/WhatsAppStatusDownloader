package com.example.whatsappstatusdownloader.model;

public class Status {
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_VIDEO = 1;


    private int id;
    private int type;
    private String address;

    public Status(int id, int type, String address) {
        this.id = id;
        this.type = type;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
