package com.example.gardenry;

public class MyPlant {
    private String name;
    private int qty;

    public MyPlant() {
    }

    public MyPlant(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public MyPlant(String name) {
        this.name = name;
        this.qty = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
