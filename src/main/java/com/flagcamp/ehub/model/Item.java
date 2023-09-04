package com.flagcamp.ehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@JsonDeserialize(builder = Item.Builder.class)
public class Item {

    @Id
    @GeneratedValue
//    @Column(columnDefinition = "BINARY(32)")
    private UUID id;

    @JsonProperty("Title")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Price")
    private float price;

    @JsonProperty("Inventory")
    private int stock;

    @JsonProperty("Owner")
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User owner;

    @JsonProperty("Images")
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemImage> images;

    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> carts;

    public Item() {}

    public Item(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
        this.owner = builder.owner;
        this.images = builder.images;
        this.carts = builder.carts;
    }

    public UUID getId() {
        return id;
    }

    public Item setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Item setPrice(float price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Item setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Item setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public List<ItemImage> getImages() {
        return images;
    }

    public Item setImages(List<ItemImage> images) {
        this.images = images;
        return this;
    }

    public List<CartItem> getCarts() {
        return carts;
    }

    public Item setCarts(List<CartItem> carts) {
        this.carts = carts;
        return this;
    }

    public static class Builder{

        @JsonProperty("Id")
        private UUID id;

        @JsonProperty("Title")
        private String name;

        @JsonProperty("Description")
        private String description;

        @JsonProperty("Price")
        private float price;

        @JsonProperty("Inventory")
        private int stock;

        @JsonProperty("Owner")
        private User owner;

        @JsonProperty("Images")
        private List<ItemImage> images;

        @JsonIgnore
        private List<CartItem> carts;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder setImages(List<ItemImage> images) {
            this.images = images;
            return this;
        }

        public Builder setCarts(List<CartItem> carts) {
            this.carts = carts;
            return this;
        }

        public Item build(){return new Item(this);}
    }
}
