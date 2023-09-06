package com.flagcamp.ehub.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @EmbeddedId
    private CartItemKey cartItemKey;

    @ManyToOne
    @JoinColumn(name = "username")
    private User buyer;

    private int count;

    public CartItem(){}

    public CartItem(CartItemKey cartItemKey, User buyer, int count) {
        this.cartItemKey = cartItemKey;
        this.buyer = buyer;
        this.count = count;
    }

    public CartItemKey getCartItemKey() {
        return cartItemKey;
    }

    public void setCartItemKey(CartItemKey cartItemKey) {
        this.cartItemKey = cartItemKey;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
