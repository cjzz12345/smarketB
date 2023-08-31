package com.flagcamp.ehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@JsonDeserialize(builder = Item.Builder.class)
public class Item {

    @Id
    @GeneratedValue
    private UUID item_id;

    @JsonProperty("Title")
    private String item_name;

    @JsonProperty("Description")
    private String item_description;

    @JsonProperty("Price")
    private float item_price;

    @JsonProperty("Inventory")
    private int item_stock;

    @JsonProperty("Owner")
    @ManyToOne
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
        this.item_id = builder.item_id;
        this.item_name = builder.item_name;
        this.item_description = builder.item_description;
        this.item_price = builder.item_price;
        this.item_stock = builder.item_stock;
        this.owner = builder.owner;
        this.images = builder.images;
        this.carts = builder.carts;
    }

    public UUID getItem_id() {
        return item_id;
    }

    public void setItem_id(UUID item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public int getItem_stock() {
        return item_stock;
    }

    public void setItem_stock(int item_stock) {
        this.item_stock = item_stock;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<ItemImage> getImages() {
        return images;
    }

    public void setImages(List<ItemImage> images) {
        this.images = images;
    }

    public List<CartItem> getCarts() {return carts;}

    public void setCarts(List<CartItem> carts) {this.carts = carts;}

    public static class Builder{

        @JsonProperty("Id")
        private UUID item_id;

        @JsonProperty("Title")
        private String item_name;

        @JsonProperty("Description")
        private String item_description;

        @JsonProperty("Price")
        private float item_price;

        @JsonProperty("Inventory")
        private int item_stock;

        @JsonProperty("Owner")
        private User owner;

        @JsonProperty("Images")
        private List<ItemImage> images;

        @JsonProperty("Carts")
        private List<CartItem> carts;

        public Builder setItem_id(UUID item_id) {
            this.item_id = item_id;
            return this;
        }

        public Builder setItem_name(String item_name) {
            this.item_name = item_name;
            return this;
        }

        public Builder setItem_description(String item_description) {
            this.item_description = item_description;
            return this;
        }

        public Builder setItem_price(float item_price) {
            this.item_price = item_price;
            return this;
        }

        public Builder setItem_stock(int item_stock) {
            this.item_stock = item_stock;
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
