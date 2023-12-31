package com.flagcamp.ehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ItemImage {

    @Id
    private String url;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Item item;

    public ItemImage() {}

    public ItemImage(String url, Item item) {
        this.url = url;
        this.item = item;
    }

    public String getUrl() {
        return url;
    }

    public ItemImage setUrl(String url) {
        this.url = url;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
