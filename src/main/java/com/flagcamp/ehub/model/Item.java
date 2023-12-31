package com.flagcamp.ehub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@JsonDeserialize(builder = Item.Builder.class)
public class Item {

    @Id
    @GeneratedValue
    @JsonProperty("id")
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
    @ManyToOne
    @JoinColumn(name = "username")
    private User owner;

    @JsonProperty("Images")
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemImage> images;

    public Item() {}

    public Item(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
        this.owner = builder.owner;
        this.images = builder.images;
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

    public String toString(){
        return this.id.toString() + "," + this.name + "," + this.description + "," +
                this.price + "," + this.owner.getUsername() + "," + this.images
                .stream()
                .map(image -> "https://ehub-images.s3.us-west-1.amazonaws.com/"+image.getUrl()+",")
                .collect(Collectors.joining());
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

        public Item build(){return new Item(this);}
    }
}
