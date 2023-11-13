package com.flagcamp.ehub.model;

import java.util.UUID;

public class CheckedItem {
    private UUID id;
    private int count;

    private String owner;

    private float price;

    private String name;

    public CheckedItem(UUID id, int count, String owner, String name, float price) {
        this.id = id;
        this.count = count;
        this.owner = owner;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getName() {return name;}

    public String getOwner() {return owner;}

    public float price() {return  price;}
}
