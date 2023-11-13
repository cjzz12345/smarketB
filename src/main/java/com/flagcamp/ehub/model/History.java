package com.flagcamp.ehub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class History {

    @Id
    @GeneratedValue
    private UUID order_id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User buyer;


    private String owner;

    private float price;

    private String name;

    private int count;


    private String checkOutTime;

    public History(){}

    public UUID getOrder_id() {
        return order_id;
    }

    public History setOrder_id(UUID order_id) {
        this.order_id = order_id;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public History setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }



    public String getCheckOutTime() {
        return checkOutTime;
    }

    public History setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
        return this;
    }

    public History setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {return name;}

    public History setOwner(String owner) {
        this.owner = owner;
        return this;
    }
    public String getOwner() {return owner;}

    public History setPrice(float price) {
        this.price = price;
        return this;
    }

    public History setCount(int count) {
        this.count = count;
        return this;
    }

    public int getCount() {return count;}

    public float getPrice() {return  price;}
}
