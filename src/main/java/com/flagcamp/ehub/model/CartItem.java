package com.flagcamp.ehub.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @EmbeddedId
    private CartItemKey cartItemKey;

//    @ManyToOne
//    @JoinColumn(name = "id")
//    private Item item;

    private int count;

    public CartItem(){}

    public CartItem(CartItemKey cartItemKey, Item item, int count) {
        this.cartItemKey = cartItemKey;
//        this.item = item;
        this.count = count;
    }

    public CartItemKey getCartItemKey() {
        return cartItemKey;
    }

    public void setCartItemKey(CartItemKey cartItemKey) {
        this.cartItemKey = cartItemKey;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
